/*
 * IngredienteService.java
 * Copyright (c) UEG.
 *
 *
 *
 *
 */
package br.ueg.nutshell.application.service;

import br.ueg.nutshell.application.configuration.Constante;
import br.ueg.nutshell.application.dto.AuthDTO;
import br.ueg.nutshell.application.dto.FiltroIngredienteDTO;
import br.ueg.nutshell.application.dto.IngredienteDTO;
import br.ueg.nutshell.application.dto.IngredienteSenhaDTO;
import br.ueg.nutshell.application.enums.StatusAtivoInativo;
import br.ueg.nutshell.application.exception.SistemaMessageCode;
import br.ueg.nutshell.application.model.Ingrediente;
import br.ueg.nutshell.application.model.TelefoneIngrediente;
import br.ueg.nutshell.application.model.Ingrediente;
import br.ueg.nutshell.application.model.IngredienteGrupo;
import br.ueg.nutshell.application.repository.IngredienteRepository;
import br.ueg.nutshell.application.repository.IngredienteRepository;
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
 * Classe de négocio referente a entidade {@link Ingrediente}.
 *
 * @author UEG
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class IngredienteService {

	@Autowired
	private IngredienteRepository ingredienteRepository;

	@Autowired
	private EmailEngineService emailService;

	@Autowired
	private AuthService authService;

	@Autowired
	private DataSource dataSource;

	/**
     * Persiste os dados do {@link Ingrediente}.
     *
     * @param usuario
     * @return
     */
	public Ingrediente salvar(Ingrediente usuario) {
		validarCamposObrigatorios(usuario);
		validarIngredienteDuplicadoPorCpf(usuario);

		if (usuario.getId() == null) {

			usuario.setStatus(StatusAtivoInativo.ATIVO);
			LocalDate dataCadastro = LocalDate.now();
			usuario.setDataAtualizado(dataCadastro);
			usuario.setDataCadastrado(dataCadastro);
			usuario.setSenha(new BCryptPasswordEncoder().encode("123456"));
			//usuario.setNome(user.getFirstName().concat(user.getLastName()));

		} else {
			Ingrediente vigente = getById(usuario.getId());

			usuario.setStatus(vigente.getStatus());
			usuario.setSenha(vigente.getSenha());
			usuario.setDataCadastrado(vigente.getDataCadastrado());
			usuario.setDataAtualizado(LocalDate.now());
		}

		usuario = usuarioRepository.save(usuario);
		return usuario;
	}

	/**
	 * Configura o {@link Ingrediente} dentro de {@link IngredienteGrupo} e {@link TelefoneIngrediente} para salvar.
	 * 
	 * @param usuario
	 */
	public void configurarIngredienteGruposAndTelefones(Ingrediente usuario) {
		for (IngredienteGrupo usuarioGrupo : usuario.getGrupos()) {
			usuarioGrupo.setIngrediente(usuario);
		}

		for (TelefoneIngrediente telefoneIngrediente : usuario.getTelefones()) {
			telefoneIngrediente.setIngrediente(usuario);
		}
	}

    /**
     * Verifica a existencia de {@link Ingrediente} acordo com o 'cpf' informado.
     *
     * @param usuario
     */
	private void validarIngredienteDuplicadoPorCpf(final Ingrediente usuario) {
		Long count = usuarioRepository.countByCpf(usuario.getCpf());

		if ( (count > BigDecimal.ONE.longValue() && usuario.getId()!=null) ||
				(count > BigDecimal.ZERO.longValue() && usuario.getId()==null)
		) {
			throw new BusinessException(SistemaMessageCode.ERRO_LOGIN_DUPLICADO);
		}
	}


    /**
     * Verifica se os campos obrigatorios de {@link Ingrediente} foram preenchidos.
     *
     * @param usuario
     */
	private void validarCamposObrigatorios(final Ingrediente usuario) {
		boolean invalido = Boolean.FALSE;

		if (Util.isEmpty(usuario.getLogin())) {
			invalido = Boolean.TRUE;
		}

		if (usuario.getGrupos() == null)
			invalido = Boolean.TRUE;

		if (invalido) {
			throw new BusinessException(SistemaMessageCode.ERRO_CAMPOS_OBRIGATORIOS);
		}
	}

    /**
     * Retorna a instância do {@link Ingrediente} conforme o 'login' informado.
     * 
     * @param login
     * @return
     */
	public Ingrediente getByLogin(String login) {
		return usuarioRepository.findByLogin(login);
	}

    /**
     * Retorna a Lista de {@link IngredienteDTO} conforme o filtro pesquisado.
     * 
     * @param filtroDTO
     * @return
     */
	public List<Ingrediente> getIngredientesByFiltro(FiltroIngredienteDTO filtroDTO) {
		validarCamposObrigatoriosFiltro(filtroDTO);

		List<Ingrediente> usuarios = usuarioRepository.findAllByFiltro(filtroDTO);

		if (CollectionUtil.isEmpty(usuarios)) {
			throw new BusinessException(SistemaMessageCode.ERRO_NENHUM_REGISTRO_ENCONTRADO_FILTROS);
		}

		return usuarios;
	}

    /**
     * Verifica se pelo menos um campo de pesquisa foi informado, e se informado o
     * nome do Grupo, verifica se tem pelo meno 4 caracteres.
     *
     * @param filtroDTO
     */
	private void validarCamposObrigatoriosFiltro(final FiltroIngredienteDTO filtroDTO) {
		boolean vazio = Boolean.TRUE;

		if (!Util.isEmpty(filtroDTO.getNome())) {
			vazio = Boolean.FALSE;

			if (filtroDTO.getNome().trim().length() < Integer.parseInt(Constante.TAMANHO_MINIMO_PESQUISA_NOME)) {
				throw new BusinessException(SistemaMessageCode.ERRO_TAMANHO_INSUFICIENTE_FILTRO_NOME);
			}
		}

		if (!Util.isEmpty(filtroDTO.getLogin())) {
			vazio = Boolean.FALSE;
		}

		if (!Util.isEmpty(filtroDTO.getIdStatus())) {
			vazio = Boolean.FALSE;
		}

		if (vazio) {
			throw new BusinessException(SistemaMessageCode.ERRO_FILTRO_INFORMAR_OUTRO);
		}
	}

    /**
     * Registra o ultimo acesso do Usuário na base de dados.
     *
     * @param usuario -
     */
	public void salvarUltimoAcesso(Ingrediente usuario) {
		usuario.setUltimoAcesso(LocalDate.now());
		usuarioRepository.save(usuario);
	}

    /**
     * Retorna a instância de {@link Ingrediente} conforme o 'id' informado.
     *
     * @param id -
     * @return -
     */
	public Ingrediente getById(final Long id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	/**
	 * Retorna a instância de {@link Ingrediente} conforme o 'id' informado.
	 * 
	 * @param id
	 * @return
	 */
	public Ingrediente getByIdFetch(final Long id) {
		return usuarioRepository.findByIdFetch(id).orElse(null);
	}

    /**
     * Valida se as senhas foram preenchidas e se são iguais
     *
     * @param redefinirSenhaDTO -
     */
	private void validarConformidadeSenha(final IngredienteSenhaDTO redefinirSenhaDTO) {
		if (Util.isEmpty(redefinirSenhaDTO.getNovaSenha()) || Util.isEmpty(redefinirSenhaDTO.getConfirmarSenha())) {
			throw new BusinessException(SistemaMessageCode.ERRO_CAMPOS_OBRIGATORIOS);
		}

		if (!redefinirSenhaDTO.getNovaSenha().equals(redefinirSenhaDTO.getConfirmarSenha())) {
			throw new BusinessException(SistemaMessageCode.ERRO_SENHAS_DIFERENTES);
		}
	}

    /**
     * Valida se a senha corrente foi preenchida e corresponde a senha armazenada no
     * keycloak.
     *
     * @param usuario -
     * @param senhaAntiga -
     */
	private void validarSenhaCorrente(Ingrediente usuario, String senhaAntiga) {
		if (Util.isEmpty(senhaAntiga)) {
			throw new BusinessException(SistemaMessageCode.ERRO_CAMPOS_OBRIGATORIOS);
		}

		AuthDTO authDTO = new AuthDTO();
		authDTO.setLogin(usuario.getLogin());
		authDTO.setSenha(senhaAntiga);
		if (!authService.loginByPassword(usuario, authDTO)) {
			throw new BusinessException(SistemaMessageCode.ERRO_SENHA_ANTERIOR_INCORRETA);
		}
	}

    /**
     * Realiza a inclusão ou alteração de senha do {@link Ingrediente}.
     *
     * @param usuarioSenhaDTO -
     */
	public Ingrediente redefinirSenha(final IngredienteSenhaDTO usuarioSenhaDTO) {
		Ingrediente usuario = getById(usuarioSenhaDTO.getIdIngrediente());

		validarConformidadeSenha(usuarioSenhaDTO);

		if (!usuarioSenhaDTO.isAtivacao() && !usuarioSenhaDTO.isRecuperacao()) {
			validarSenhaCorrente(usuario, usuarioSenhaDTO.getSenhaAntiga());
		} else {
			usuario.setStatus(StatusAtivoInativo.ATIVO);
		}
		usuario.setSenha(usuarioSenhaDTO.getNovaSenha());
		return usuarioRepository.save(usuario);
	}

	/**
	 * Retorna a instância de {@link Ingrediente} conforme o 'cpf' informado.
	 * 
	 * @param cpf
	 * @return
	 */
	public Ingrediente findByCpfIngrediente(final String cpf) {
		return usuarioRepository.findByCpf(cpf);
	}

	/**
	 * Retorna a instância do {@link Ingrediente} conforme o 'cpf' informado
	 * e que não tenha o 'id' informado.
	 * 
	 * @param cpf
	 * @param id
	 * @return
	 */
	public Ingrediente findByCpfIngredienteAndNotId(final String cpf, final Long id) {
		return usuarioRepository.findByCpfAndNotId(cpf, id);
	}

    /**
     * Solicita a recuperação de senha do {@link Ingrediente}.
     *
     * @param cpf -
     * @return -
     */
	public Ingrediente recuperarSenha(final String cpf) {
		Ingrediente usuario = findByCpfIngrediente(cpf);

		if (usuario == null) {
			throw new BusinessException(SistemaMessageCode.ERRO_USUARIO_NAO_ENCONTRADO);
		}

		emailService.enviarEmailEsqueciSenha(usuario);
		return usuario;
	}

    /**
     * Inativa o {@link Ingrediente}.
     *
     * @param id
     * @return
     */
	public Ingrediente inativar(final Long id) {
		Ingrediente usuario = getById(id);
		usuario.setStatus(StatusAtivoInativo.INATIVO);
		return usuarioRepository.save(usuario);
	}

    /**
     * Ativa o {@link Ingrediente}.
     *
     * @param id
     * @return
     */
	public Ingrediente ativar(final Long id) {
		Ingrediente usuario = getById(id);
		usuario.setStatus(StatusAtivoInativo.ATIVO);
		return usuarioRepository.save(usuario);
	}

	/**
	 * Verifica se o CPF informado é válido.
	 * 
	 * @param cpf
	 * @return
	 */
	private boolean isCpfValido(final String cpf) {
		boolean valido = false;

		if (!Util.isEmpty(cpf)) {
			valido = Util.isCpfValido(cpf);
		}
		return valido;
	}

	/**
	 * Verifica se o CPF informado é válido e se está em uso.
	 * 
	 * @param cpf
	 */
	public void validarCpf(final String cpf) {
		validarCpf(cpf, null);
	}

	/**
	 * Verifica se o CPF informado é válido e se está em uso.
	 * 
	 * @param cpf
	 * @param id
	 */
	public void validarCpf(final String cpf, final Long id) {
		if (!isCpfValido(cpf)) {
			throw new BusinessException(SistemaMessageCode.ERRO_CPF_INVALIDO);
		}

		Ingrediente usuario;

		if (id == null) {
			usuario = findByCpfIngrediente(cpf);
		} else {
			usuario = findByCpfIngredienteAndNotId(cpf, id);
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
