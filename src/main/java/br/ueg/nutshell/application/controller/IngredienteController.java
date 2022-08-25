/*
 * IngredienteController.java
 * Copyright (c) João Augusto Moreira Ananias.
 *
 *
 *
 *
 */
package br.ueg.nutshell.application.controller;

import br.ueg.nutshell.api.util.Validation;
import br.ueg.nutshell.application.dto.FiltroIngredienteDTO;
import br.ueg.nutshell.application.dto.IngredienteDTO;
import br.ueg.nutshell.application.enums.StatusAtivoInativo;
import br.ueg.nutshell.application.mapper.IngredienteMapper;
import br.ueg.nutshell.application.model.Ingrediente;
import br.ueg.nutshell.application.service.IngredienteService;
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
 * Classe de controle referente a entidade {@link Ingrediente}.
 * 
 * @author UEG
 */
@Api(tags = "Ingrediente API")
@RestController
@RequestMapping("${app.api.base}/ingredientes")
public class IngredienteController extends AbstractController {

	@Autowired
	private IngredienteService ingredienteService;

	@Autowired
	private IngredienteMapper ingredienteMapper;

	/**
	 * Salva uma instância de {@link Ingrediente} na base de dados.
	 * 
	 * @param ingredienteDTO
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_INGREDIENTE_INCLUIR')")
	@ApiOperation(value = "Inclui um novo Ingrediente na base de dados.", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ 
			@ApiResponse(code = 200, message = "Success", response = IngredienteDTO.class),
			@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = MessageResponse.class) 
	})
	@PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> incluir(@ApiParam(value = "Dados do Ingrediente", required = true) @Valid @RequestBody IngredienteDTO ingredienteDTO) {
		Ingrediente ingrediente = ingredienteMapper.toEntity(ingredienteDTO);
		ingrediente = ingredienteService.salvar(ingrediente);
		ingredienteDTO = ingredienteMapper.toDTO(ingrediente);
		return ResponseEntity.ok(ingredienteDTO);
	}

    /**
	 * Altera a instância de {@link Ingrediente} na base de dados.
	 * 
	 * @param id
	 * @param ingredienteDTO
	 * @return
	 */
    @PreAuthorize("hasRole('ROLE_INGREDIENTE_ALTERAR')")
    @ApiOperation(value = "Altera os dados de um Ingrediente na base de dados.", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = IngredienteDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class)
    })
    @PutMapping(path = "/{id:[\\d]+}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> alterar(@ApiParam(value = "Código do Ingrediente", required = true) @PathVariable final BigDecimal id, @ApiParam(value = "Dados do Ingrediente", required = true) @Valid @RequestBody IngredienteDTO ingredienteDTO) {
        Validation.max("id", id, 99999999L);
        Ingrediente ingrediente = ingredienteMapper.toEntity(ingredienteDTO);
        ingrediente.setId(id.longValue());
        ingredienteService.salvar(ingrediente);
		return ResponseEntity.ok(ingredienteDTO);
    }

	/**
	 * Retorna a instância de {@link IngredienteDTO} conforme o 'id'
	 * informado.
	 *
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_INGREDIENTE_VISUALIZAR')")
	@ApiOperation(value = "Recupera o ingrediente pela id.", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = IngredienteDTO.class),
			@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = MessageResponse.class)
	})
	@GetMapping(path = "/{id:[\\d]+}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getIngredienteById(@ApiParam(value = "Id do Ingrediente") @PathVariable final BigDecimal id) {
		Validation.max("id", id, 99999999L);
		Ingrediente ingrediente = ingredienteService.getByIdFetch(id.longValue());
		IngredienteDTO ingredienteTO = new IngredienteDTO();

		if(ingrediente != null)
			ingredienteTO = ingredienteMapper.toDTO(ingrediente);

		return ResponseEntity.ok(ingredienteTO);
	}

	/**
	 * Retorna a lista de {@link IngredienteDTO} de acordo com os filtros
	 * informados.
	 * 
	 * @param filtroDTO
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_INGREDIENTE_PESQUISAR')")
	@ApiOperation(value = "Recupera os ingredientes pelo Filtro Informado.", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ 
			@ApiResponse(code = 200, message = "Success", response = IngredienteDTO.class),
			@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = MessageResponse.class) 
	})
	@GetMapping(path = "/filtro-ativo", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getIngredientesAtivosByFiltro(@ApiParam(value = "Filtro de pesquisa", required = true) @Valid @ModelAttribute("filtroDTO") FiltroIngredienteDTO filtroDTO) {
		String idStatusIngrediente = StatusAtivoInativo.ATIVO.getId();
		filtroDTO.setStatus(idStatusIngrediente);
		List<IngredienteDTO> ingredientesDTO = new ArrayList<>();
		List<Ingrediente> ingredientes = ingredienteService.getIngredientesByFiltro(filtroDTO);
		if(ingredientes != null){
			for (Ingrediente ingrediente: ingredientes) {
				ingredientesDTO.add(ingredienteMapper.toDTO(ingrediente));
			}
		}

		return ResponseEntity.ok(ingredientesDTO);
	}

	/**
	 * Retorna a lista de {@link IngredienteDTO} de acordo com os filtros
	 * informados.
	 * 
	 * @param filtroDTO
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_INGREDIENTE_PESQUISAR')")
	@ApiOperation(value = "Recupera os ingredientes pelo Filtro Informado.", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Success", response = IngredienteDTO.class),
		@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = MessageResponse.class) 
	})
	@GetMapping(path = "/filtro", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getIngredientesByFiltro(@ApiParam(value = "Filtro de pesquisa", required = true) @Valid @ModelAttribute("filtroDTO") final FiltroIngredienteDTO filtroDTO) {
		List<Ingrediente> ingredientes = ingredienteService.getIngredientesByFiltro(filtroDTO);
		List<IngredienteDTO> ingredientesDTO = new ArrayList<>();
		for (Ingrediente ingrediente: ingredientes) {
			ingredientesDTO.add (ingredienteMapper.toDTO(ingrediente));
		}

		return ResponseEntity.ok(ingredientesDTO);
	}

	/**
	 * Inativa o {@link Ingrediente}.
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_INGREDIENTE_ATIVAR_INATIVAR')")
	@ApiOperation(value = "Inativa o ingrediente.", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ 
			@ApiResponse(code = 200, message = "Success", response = IngredienteDTO.class),
			@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class) 
	})
	@PutMapping(path = "/{id:[\\d]+}/inativo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> inativar(@ApiParam(value = "Código do Ingrediente", required = true) @PathVariable final BigDecimal id) {
		Validation.max("id", id, 99999999L);
		ingredienteService.inativar(id.longValue());
		return ResponseEntity.ok().build();
	}

	/**
	 * Ativa o {@link Ingrediente}.
	 *
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_INGREDIENTE_ATIVAR_INATIVAR')")
	@ApiOperation(value = "Ativa o usuário.", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
		@ApiResponse(code = 200, message = "Success", response = IngredienteDTO.class),
		@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class)
	})
	@PutMapping(path = "/{id:[\\d]+}/ativo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> ativar(@ApiParam(value = "Código do Ingrediente", required = true) @PathVariable final BigDecimal id) {
		Validation.max("id", id, 99999999L);
		ingredienteService.ativar(id.longValue());
		return ResponseEntity.ok().build();
	}

	/**
	 * Verifica se o CPF informado é válido e se está em uso.
	 * 
	 * @param cpf
	 * @return
	 */
	@ApiOperation(value = "Verifica se o CPF informado é válido e se está em uso.")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Success"),
		@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class) 
	})
	@GetMapping(path = "cpf/valido/{cpf}")
	public ResponseEntity<?> validarCpf(@ApiParam(value = "CPF") @PathVariable final String cpf) {
		ingredienteService.validarCpf(cpf);
		return ResponseEntity.ok().build();
	}

	/**
	 * Verifica se o CPF informado é válido e se está em uso.
	 * 
	 * @param cpf
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@ApiOperation(value = "Verifica se o CPF informado é válido e se está em uso.")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Success"),
		@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class) 
	})
	@GetMapping(path = "/{id:[\\d]+}/cpf/valido/{cpf}")
	public ResponseEntity<?> validarCpfIngrediente(
			@ApiParam(value = "Código do Ingrediente") @PathVariable final BigDecimal id,
			@ApiParam(value = "CPF") @PathVariable final String cpf) {
		Validation.max("id", id, 99999999L);
		ingredienteService.validarCpf(cpf, id.longValue());
		return ResponseEntity.ok().build();
	}

	//@PreAuthorize("isAuthenticated()")
	@ApiOperation(value = "Retorna Relatório de Grupos.", produces = MediaType.APPLICATION_PDF_VALUE)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = MessageResponse.class)
	})
	@GetMapping(path = "/relatorio-ingredientes/{idGrupo}",produces = { MediaType.APPLICATION_PDF_VALUE })
	public ResponseEntity<?> getRelatorioGrupos (
			@ApiParam(value = "Código do Ingrediente") @PathVariable final Long idGrupo
	) throws IOException, JRException {
		return this.toPDF(ingredienteService.gerarRelatorio(idGrupo),"Relatorio-grupo.pdf");
	}

	//@PreAuthorize("isAuthenticated()")
	@ApiOperation(value = "Retorna Relatório de Grupos.", produces = MediaType.APPLICATION_PDF_VALUE)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = MessageResponse.class)
	})
	@GetMapping(path = "/relatorio-ingredientes",produces = { MediaType.APPLICATION_PDF_VALUE })
	public ResponseEntity<?> getRelatorioGrupos2 () throws IOException, JRException {
		return this.toPDF(ingredienteService.gerarRelatorio(null),"Relatorio-grupo2.pdf");
	}
}
