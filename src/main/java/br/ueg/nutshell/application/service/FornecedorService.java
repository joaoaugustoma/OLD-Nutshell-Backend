/*
 * UsuarioService.java
 * Copyright (c) UEG.
 *
 *
 *
 *
 */
package br.ueg.nutshell.application.service;

import br.ueg.nutshell.application.configuration.Constante;
import br.ueg.nutshell.application.dto.*;
import br.ueg.nutshell.application.enums.StatusAtivoInativo;
import br.ueg.nutshell.application.exception.SistemaMessageCode;
import br.ueg.nutshell.application.model.Fornecedor;
import br.ueg.nutshell.application.model.TelefoneUsuario;
import br.ueg.nutshell.application.model.Usuario;
import br.ueg.nutshell.application.model.UsuarioGrupo;
import br.ueg.nutshell.application.repository.FornecedorRepository;
import br.ueg.nutshell.application.repository.UsuarioRepository;
import br.ueg.nutshell.comum.exception.BusinessException;
import br.ueg.nutshell.comum.util.CollectionUtil;
import br.ueg.nutshell.comum.util.Util;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe de négocio referente a entidade {@link Usuario}.
 *
 * @author UEG
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;

	@Autowired
	private DataSource dataSource;

	/**
     * Persiste os dados do {@link Usuario}.
     *
     * @param fornecedor
     * @return
     */
	public Fornecedor salvar(Fornecedor fornecedor) {
		validarCamposObrigatorios(fornecedor);
		validarFornecedorDuplicadoPorCpfCnpj(fornecedor);

		if (fornecedor.getId() == null) {

			fornecedor.setSituacao(StatusAtivoInativo.ATIVO);
			LocalDate data = LocalDate.now();
			fornecedor.setDataCadastrado(data);
			fornecedor.setDataAtualizado(data);
			//usuario.setNome(user.getFirstName().concat(user.getLastName()));

		} else {
			Fornecedor fornecedorExistente = getById(fornecedor.getId());

			fornecedor.setSituacao(fornecedorExistente.getSituacao());
			fornecedor.setDataCadastrado(fornecedorExistente.getDataCadastrado());
			fornecedor.setDataAtualizado(LocalDate.now());
		}

		fornecedor = fornecedorRepository.save(fornecedor);
		return fornecedor;
	}

    /**
     * Verifica a existencia de {@link Fornecedor} acordo com o 'cpf' informado.
     *
     * @param fornecedor
     */
	private void validarFornecedorDuplicadoPorCpfCnpj(final Fornecedor fornecedor) {
		Long count = fornecedorRepository.countByCpfCnpjlAndNotId(fornecedor.getCpfCnpj(), fornecedor.getId());

		if ( (count > BigDecimal.ONE.longValue() && fornecedor.getId()!=null) ||
				(count > BigDecimal.ZERO.longValue() && fornecedor.getId()==null)
		) {
			throw new BusinessException(SistemaMessageCode.ERRO_FORNECEDOR_DUPLICADO);
		}
	}


    /**
     * Verifica se os campos obrigatorios de {@link Fornecedor} foram preenchidos.
     *
     * @param fornecedor
     */
	private void validarCamposObrigatorios(final Fornecedor fornecedor) {
		boolean invalido = Boolean.FALSE;

		if (Util.isEmpty(fornecedor.getCpfCnpj()))
			invalido = Boolean.TRUE;

		if (fornecedor.getNomeFantasia() == null)
			invalido = Boolean.TRUE;

		if (fornecedor.getRazaoSocial() == null)
			invalido = Boolean.TRUE;

		if (fornecedor.getSituacao() == null)
			invalido = Boolean.TRUE;

		if (fornecedor.getTipoPessoa() == null)
			invalido = Boolean.TRUE;

		if (fornecedor.getCpfCnpj() == null)
			invalido = Boolean.TRUE;

		if (invalido) {
			throw new BusinessException(SistemaMessageCode.ERRO_CAMPOS_OBRIGATORIOS);
		}
	}

    /**
     * Retorna a Lista de {@link UsuarioDTO} conforme o filtro pesquisado.
     * 
     * @param filtroDTO
     * @return
     */
	public List<Fornecedor> getFornecedoresByFiltro(FiltroFornecedorDTO filtroDTO) {
		validarCamposObrigatoriosFiltro(filtroDTO);

		List<Fornecedor> fornecedores = fornecedorRepository.findAllByFiltro(filtroDTO);

		if (CollectionUtil.isEmpty(fornecedores)) {
			throw new BusinessException(SistemaMessageCode.ERRO_NENHUM_REGISTRO_ENCONTRADO_FILTROS);
		}

		return fornecedores;
	}

    /**
     * Verifica se pelo menos um campo de pesquisa foi informado, e se informado o
     * nome do Grupo, verifica se tem pelo meno 4 caracteres.
     *
     * @param filtroDTO
     */
	private void validarCamposObrigatoriosFiltro(final FiltroFornecedorDTO filtroDTO) {
		boolean vazio = Boolean.TRUE;

		if (!Util.isEmpty(filtroDTO.getRazaoSocial())) {
			vazio = Boolean.FALSE;

			if (filtroDTO.getRazaoSocial().trim().length() < Integer.parseInt(Constante.TAMANHO_MINIMO_PESQUISA_NOME)) {
				throw new BusinessException(SistemaMessageCode.ERRO_TAMANHO_INSUFICIENTE_FILTRO_NOME);
			}
		}

		if (!Util.isEmpty(filtroDTO.getCpfCnpj())) {
			vazio = Boolean.FALSE;
		}

		if (!Util.isEmpty(filtroDTO.getIdStatus())) {
			vazio = Boolean.FALSE;
		}

		if (!Util.isEmpty(filtroDTO.getNomeTipoPessoa())) {
			vazio = Boolean.FALSE;
		}

		if (filtroDTO.getStatusEnum() != null) {
			vazio = Boolean.FALSE;
		}

		if (vazio) {
			throw new BusinessException(SistemaMessageCode.ERRO_FILTRO_INFORMAR_OUTRO);
		}
	}


    /**
     * Retorna a instância de {@link Usuario} conforme o 'id' informado.
     *
     * @param id -
     * @return -
     */
	public Fornecedor getById(final Long id) {
		return fornecedorRepository.findById(id).orElse(null);
	}


    /**
     * Inativa o {@link Fornecedor}.
     *
     * @param id
     * @return
     */
	public Fornecedor inativar(final Long id) {
		Fornecedor fornecedor = getById(id);
		fornecedor.setSituacao(StatusAtivoInativo.INATIVO);
		return fornecedorRepository.save(fornecedor);
	}

    /**
     * Ativa o {@link Fornecedor}.
     *
     * @param id
     * @return
     */
	public Fornecedor ativar(final Long id) {
		Fornecedor fornecedor = getById(id);
		fornecedor.setSituacao(StatusAtivoInativo.ATIVO);
		return fornecedorRepository.save(fornecedor);
	}

	/**
	 * Verifica se o CPF informado é válido.
	 * 
	 * @param cpf
	 * @return
	 */
	private boolean isCpfCnpjValido(final String cpfCnpj) {
		boolean valido = false;

		if (!Util.isEmpty(cpfCnpj)) {
			valido = Util.isCpfValido(cpfCnpj);
		}
		return valido;
	}

	/**
	 * Verifica se o CPF informado é válido e se está em uso.
	 * 
	 * @param cpf
	 */
	public void validarCpfCnpj(final String cpfCnpj) {
		validarCpf(cpfCnpj, null);
	}

	/**
	 * Verifica se o CPF informado é válido e se está em uso.
	 * 
	 * @param cpfCnpj
	 * @param id
	 */
	public void validarCpf(final String cpfCnpj, final Long id) {
		if (!isCpfCnpjValido(cpfCnpj)) {
			throw new BusinessException(SistemaMessageCode.ERRO_CPF_INVALIDO);
		}

		Usuario usuario;

		if (id == null) {
			usuario = findByCpfUsuario(cpf);
		} else {
			usuario = findByCpfUsuarioAndNotId(cpf, id);
		}

		if (usuario != null) {
			throw new BusinessException(SistemaMessageCode.ERRO_CPF_EM_USO);
		}
	}
	public JasperPrint gerarRelatorio(Long idGrupo){
		try {
			Connection connection = dataSource.getConnection();
			Map<String, Object> params = new HashMap<>();
			params.put("id_grupo",idGrupo);
			JasperDesign jd =
					JRXmlLoader.load(
							this.getClass()
									.getResourceAsStream("/relatorios/usuarios_por_grupo.jrxml"));
			JasperReport jasperReport = JasperCompileManager.compileReport(jd);
			JasperPrint jasperPrint =
					JasperFillManager.fillReport(jasperReport, params, connection);
			return jasperPrint;
			//TODO Não foi feito o tratamento correto ainda
		} catch (JRException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
