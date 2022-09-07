/*
 * FornecedorController.java
 * Copyright (c) UEG.
 *
 *
 *
 *
 */
package br.ueg.nutshell.application.controller;

import br.ueg.nutshell.api.util.Validation;
import br.ueg.nutshell.application.dto.FiltroFornecedorDTO;
import br.ueg.nutshell.application.dto.FornecedorDTO;
import br.ueg.nutshell.application.enums.StatusAtivoInativo;
import br.ueg.nutshell.application.mapper.FornecedorMapper;
import br.ueg.nutshell.application.model.Fornecedor;
import br.ueg.nutshell.application.model.Usuario;
import br.ueg.nutshell.application.service.FornecedorService;
import br.ueg.nutshell.comum.exception.MessageResponse;
import io.swagger.annotations.*;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de controle referente a entidade {@link Fornecedor}.
 * 
 * @author UEG
 */
@Api(tags = "Fornecedor API")
@RestController
@RequestMapping("${app.api.base}/fornecedores")
public class FornecedorController extends AbstractController {

	@Autowired
	private FornecedorService fornecedorService;

	@Autowired
	private FornecedorMapper fornecedorMapper;

	/**
	 * Salva uma instância de {@link Fornecedor} na base de dados.
	 * 
	 * @param fornecedorDTO
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_FORNECEDOR_INCLUIR')")
	@ApiOperation(value = "Inclui um novo Fornecedor na base de dados.", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ 
			@ApiResponse(code = 200, message = "Success", response = FornecedorDTO.class),
			@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = MessageResponse.class) 
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> incluir(@ApiParam(value = "Informações do Fornecedor", required = true) @Valid @RequestBody FornecedorDTO fornecedorDTO) {
		Fornecedor fornecedor = fornecedorMapper.toEntity(fornecedorDTO);
		fornecedor = fornecedorService.salvar(fornecedor);
		fornecedorDTO = fornecedorMapper.toDTO(fornecedor);
		return ResponseEntity.ok(fornecedorDTO);
	}

    /**
	 * Altera a instância de {@link Fornecedor} na base de dados.
	 * 
	 * @param id
	 * @param fornecedorDTO
	 * @return
	 */
    @PreAuthorize("hasRole('ROLE_FORNECEDOR_ALTERAR')")
    @ApiOperation(value = "Altera as informações de um Fornecedor na base de dados.", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = FornecedorDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class)
    })
    @PutMapping(path = "/{id:[\\d]+}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> alterar(@ApiParam(value = "Código do Fornecedor", required = true) @PathVariable final BigDecimal id, @ApiParam(value = "Informações do Fornecedor", required = true) @Valid @RequestBody FornecedorDTO fornecedorDTO) {
        Validation.max("id", id, 99999999L);
        Fornecedor fornecedor = fornecedorMapper.toEntity(fornecedorDTO);
        fornecedor.setId(id.longValue());
        fornecedorService.salvar(fornecedor);
		return ResponseEntity.ok(fornecedorDTO);
    }

	@PreAuthorize("isAuthenticated()")
	@ApiOperation(value = "Retorna uma lista de Fornecedores cadastrados.", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = FornecedorDTO.class),
			@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = MessageResponse.class)
	})
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getFornecedores() {
		List<Fornecedor> fornecedores = fornecedorService.getCadastrados();
		List<FornecedorDTO> fornecedoresDTO = new ArrayList<>();
		for (Fornecedor fornecedor : fornecedores) {
			FornecedorDTO fornecedorDTO = fornecedorMapper.toDTO(fornecedor);
			fornecedoresDTO.add(fornecedorDTO);
		}
		return ResponseEntity.ok(fornecedoresDTO);
	}

	/**
	 * Retorna a instância de {@link FornecedorDTO} conforme o 'id'
	 * informado.
	 *
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_FORNECEDOR_VISUALIZAR')")
	@ApiOperation(value = "Recupera o fornecedor pela id.", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = FornecedorDTO.class),
			@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = MessageResponse.class)
	})
	@GetMapping(path = "/{id:[\\d]+}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getFornecedorById(@ApiParam(value = "Id do Fornecedor") @PathVariable final BigDecimal id) {
		Validation.max("id", id, 99999999L);
		Fornecedor fornecedor = fornecedorService.getById(id.longValue());
		FornecedorDTO fornecedorTO = new FornecedorDTO();

		if(fornecedor != null)
			fornecedorTO = fornecedorMapper.toDTO(fornecedor);

		return ResponseEntity.ok(fornecedorTO);
	}

	/**
	 * Retorna a lista de {@link FornecedorDTO} de acordo com os filtros
	 * informados.
	 * 
	 * @param filtroDTO
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_FORNECEDOR_PESQUISAR')")
	@ApiOperation(value = "Recupera os fornecedores pelo Filtro Informado.", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ 
			@ApiResponse(code = 200, message = "Success", response = FornecedorDTO.class),
			@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = MessageResponse.class) 
	})
	@GetMapping(path = "/filtro-ativo", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getFornecedorsAtivosByFiltro(@ApiParam(value = "Filtro de pesquisa", required = true) @Valid @ModelAttribute("filtroDTO") FiltroFornecedorDTO filtroDTO) {
		String idStatusFornecedor = StatusAtivoInativo.ATIVO.getId();
		filtroDTO.setSituacao(idStatusFornecedor);
		List<FornecedorDTO> fornecedoresDTO = new ArrayList<>();
		List<Fornecedor> fornecedores = fornecedorService.getFornecedoresByFiltro(filtroDTO);
		if(fornecedores != null){
			for (Fornecedor fornecedor: fornecedores) {
				fornecedoresDTO.add(fornecedorMapper.toDTO(fornecedor));
			}
		}

		return ResponseEntity.ok(fornecedoresDTO);
	}

	/**
	 * Retorna a lista de {@link FornecedorDTO} de acordo com os filtros
	 * informados.
	 * 
	 * @param filtroDTO
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_FORNECEDOR_PESQUISAR')")
	@ApiOperation(value = "Recupera os fornecedores pelo Filtro Informado.", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Success", response = FornecedorDTO.class),
		@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = MessageResponse.class) 
	})
	@GetMapping(path = "/filtro", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getFornecedorsByFiltro(@ApiParam(value = "Filtro de pesquisa", required = true) @Valid @ModelAttribute("filtroDTO") final FiltroFornecedorDTO filtroDTO) {
		List<Fornecedor> fornecedores = fornecedorService.getFornecedoresByFiltro(filtroDTO);
		List<FornecedorDTO> fornecedoresDTO = new ArrayList<>();
		for (Fornecedor fornecedor: fornecedores) {
			fornecedoresDTO.add (fornecedorMapper.toDTO(fornecedor));
		}

		return ResponseEntity.ok(fornecedoresDTO);
	}

	/**
	 * Inativa o {@link Fornecedor}.
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_FORNECEDOR_ATIVAR_INATIVAR')")
	@ApiOperation(value = "Inativa o fornecedor.", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ 
			@ApiResponse(code = 200, message = "Success", response = FornecedorDTO.class),
			@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class) 
	})
	@PutMapping(path = "/{id:[\\d]+}/inativo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> inativar(@ApiParam(value = "Código do Fornecedor", required = true) @PathVariable final BigDecimal id) {
		Validation.max("id", id, 99999999L);
		fornecedorService.inativar(id.longValue());
		return ResponseEntity.ok().build();
	}

	/**
	 * Ativa o {@link Fornecedor}.
	 *
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_FORNECEDOR_ATIVAR_INATIVAR')")
	@ApiOperation(value = "Ativa o usuário.", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
		@ApiResponse(code = 200, message = "Success", response = FornecedorDTO.class),
		@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class)
	})
	@PutMapping(path = "/{id:[\\d]+}/ativo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> ativar(@ApiParam(value = "Código do Fornecedor", required = true) @PathVariable final BigDecimal id) {
		Validation.max("id", id, 99999999L);
		fornecedorService.ativar(id.longValue());
		return ResponseEntity.ok().build();
	}

	/**
	 * Verifica se o CPF informado é válido e se está em uso.
	 * 
	 * @param cpfCnpj
	 * @return
	 */
	@ApiOperation(value = "Verifica se o CPF informado é válido e se está em uso.")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Success"),
		@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class) 
	})
	@GetMapping(path = "cpfCnpj/valido/{cpfCnpj}")
	public ResponseEntity<?> validarCpf(@ApiParam(value = "cpfCnpj") @PathVariable final String cpfCnpj, @ApiParam(value = "idFornecedor") @PathVariable final Long idFornecedor) {
		fornecedorService.validarCpf(cpfCnpj, idFornecedor);
		return ResponseEntity.ok().build();
	}

	//@PreAuthorize("isAuthenticated()")
	@ApiOperation(value = "Retorna Relatório de Fornecedors.", produces = MediaType.APPLICATION_PDF_VALUE)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = MessageResponse.class)
	})
	@GetMapping(path = "/relatorio-fornecedores/{idFornecedor}",produces = { MediaType.APPLICATION_PDF_VALUE })
	public ResponseEntity<?> getRelatorioFornecedors (
			@ApiParam(value = "Código do Fornecedor") @PathVariable final Long idFornecedor
	) throws IOException, JRException {
		return this.toPDF(fornecedorService.gerarRelatorio(idFornecedor),"Relatorio-grupo.pdf");
	}

	//@PreAuthorize("isAuthenticated()")
	@ApiOperation(value = "Retorna Relatório de Fornecedors.", produces = MediaType.APPLICATION_PDF_VALUE)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = MessageResponse.class)
	})
	@GetMapping(path = "/relatorio-fornecedores",produces = { MediaType.APPLICATION_PDF_VALUE })
	public ResponseEntity<?> getRelatorioFornecedors2 () throws IOException, JRException {
		return this.toPDF(fornecedorService.gerarRelatorio(null),"Relatorio-grupo2.pdf");
	}
}
