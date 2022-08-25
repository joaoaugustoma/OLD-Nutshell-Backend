/*
 * UsuarioController.java
 * Copyright (c) UEG.
 *
 *
 *
 *
 */
package br.ueg.nutshell.application.controller;

import br.ueg.nutshell.api.util.Validation;
import br.ueg.nutshell.application.dto.FiltroUsuarioDTO;
import br.ueg.nutshell.application.dto.IngredienteDTO;
import br.ueg.nutshell.application.dto.UsuarioDTO;
import br.ueg.nutshell.application.enums.StatusAtivoInativo;
import br.ueg.nutshell.application.mapper.IngredienteMapper;
import br.ueg.nutshell.application.mapper.UsuarioMapper;
import br.ueg.nutshell.application.model.Ingrediente;
import br.ueg.nutshell.application.model.Usuario;
import br.ueg.nutshell.application.service.IngredienteService;
import br.ueg.nutshell.application.service.UsuarioService;
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
 * Classe de controle referente a entidade {@link Usuario}.
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
	 * Salva uma instância de {@link Usuario} na base de dados.
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
	 * @param usuarioDTO
	 * @return
	 */
    @PreAuthorize("hasRole('ROLE_INGREDIENTE_ALTERAR')")
    @ApiOperation(value = "Altera os dados de um Ingrediente na base de dados.", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = UsuarioDTO.class),
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
	 * Retorna a instância de {@link UsuarioDTO} conforme o 'id'
	 * informado.
	 *
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_USUARIO_VISUALIZAR')")
	@ApiOperation(value = "Recupera o usuario pela id.", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = UsuarioDTO.class),
			@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = MessageResponse.class)
	})
	@GetMapping(path = "/{id:[\\d]+}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getUsuarioById(@ApiParam(value = "Id do Usuario") @PathVariable final BigDecimal id) {
		Validation.max("id", id, 99999999L);
		Usuario usuario = ingredienteService.getByIdFetch(id.longValue());
		UsuarioDTO usuarioTO = new UsuarioDTO();

		if(usuario != null)
			usuarioTO = usuarioMapper.toDTO(usuario);

		return ResponseEntity.ok(usuarioTO);
	}

	/**
	 * Retorna a lista de {@link UsuarioDTO} de acordo com os filtros
	 * informados.
	 * 
	 * @param filtroDTO
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_USUARIO_PESQUISAR')")
	@ApiOperation(value = "Recupera os usuarios pelo Filtro Informado.", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ 
			@ApiResponse(code = 200, message = "Success", response = UsuarioDTO.class),
			@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = MessageResponse.class) 
	})
	@GetMapping(path = "/filtro-ativo", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getUsuariosAtivosByFiltro(@ApiParam(value = "Filtro de pesquisa", required = true) @Valid @ModelAttribute("filtroDTO") FiltroUsuarioDTO filtroDTO) {
		String idStatusUsuario = StatusAtivoInativo.ATIVO.getId();
		filtroDTO.setIdStatus(idStatusUsuario);
		List<UsuarioDTO> usuariosDTO = new ArrayList<>();
		List<Usuario> usuarios = ingredienteService.getUsuariosByFiltro(filtroDTO);
		if(usuarios != null){
			for (Usuario usuario: usuarios) {
				usuariosDTO.add(usuarioMapper.toDTO(usuario));
			}
		}

		return ResponseEntity.ok(usuariosDTO);
	}

	/**
	 * Retorna a lista de {@link UsuarioDTO} de acordo com os filtros
	 * informados.
	 * 
	 * @param filtroDTO
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_USUARIO_PESQUISAR')")
	@ApiOperation(value = "Recupera os usuarios pelo Filtro Informado.", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "Success", response = UsuarioDTO.class),
		@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = MessageResponse.class) 
	})
	@GetMapping(path = "/filtro", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getUsuariosByFiltro(@ApiParam(value = "Filtro de pesquisa", required = true) @Valid @ModelAttribute("filtroDTO") final FiltroUsuarioDTO filtroDTO) {
		List<Usuario> usuarios = ingredienteService.getUsuariosByFiltro(filtroDTO);
		List<UsuarioDTO> usuariosDTO = new ArrayList<>();
		for (Usuario usuario: usuarios) {
			usuario.setTelefones(null);
			usuariosDTO.add (usuarioMapper.toDTO(usuario));
		}

		return ResponseEntity.ok(usuariosDTO);
	}

	/**
	 * Inativa o {@link Usuario}.
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_USUARIO_ATIVAR_INATIVAR')")
	@ApiOperation(value = "Inativa o usuario.", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({ 
			@ApiResponse(code = 200, message = "Success", response = UsuarioDTO.class),
			@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class) 
	})
	@PutMapping(path = "/{id:[\\d]+}/inativo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> inativar(@ApiParam(value = "Código do Usuário", required = true) @PathVariable final BigDecimal id) {
		Validation.max("id", id, 99999999L);
		ingredienteService.inativar(id.longValue());
		return ResponseEntity.ok().build();
	}

	/**
	 * Ativa o {@link Usuario}.
	 *
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_USUARIO_ATIVAR_INATIVAR')")
	@ApiOperation(value = "Ativa o usuário.", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses({
		@ApiResponse(code = 200, message = "Success", response = UsuarioDTO.class),
		@ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class)
	})
	@PutMapping(path = "/{id:[\\d]+}/ativo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> ativar(@ApiParam(value = "Código do Usuário", required = true) @PathVariable final BigDecimal id) {
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
	public ResponseEntity<?> validarCpfUsuario(
			@ApiParam(value = "Código do Usuário") @PathVariable final BigDecimal id,
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
	@GetMapping(path = "/relatorio-usuarios/{idGrupo}",produces = { MediaType.APPLICATION_PDF_VALUE })
	public ResponseEntity<?> getRelatorioGrupos (
			@ApiParam(value = "Código do Usuário") @PathVariable final Long idGrupo
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
	@GetMapping(path = "/relatorio-usuarios",produces = { MediaType.APPLICATION_PDF_VALUE })
	public ResponseEntity<?> getRelatorioGrupos2 () throws IOException, JRException {
		return this.toPDF(ingredienteService.gerarRelatorio(null),"Relatorio-grupo2.pdf");
	}
}
