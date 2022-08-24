package br.ueg.nutshell.application.controller;

import br.ueg.nutshell.api.util.Validation;
import br.ueg.nutshell.application.dto.CasesDTO;
import br.ueg.nutshell.application.mapper.CasesMapper;
import br.ueg.nutshell.application.model.Amigo;
import br.ueg.nutshell.application.model.Cases;
import br.ueg.nutshell.application.service.CasesService;
import br.ueg.nutshell.comum.exception.MessageResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "Cases API")
@RestController
@RequestMapping(path = "${app.api.base}/cases")
public class CasesController extends AbstractController {

    @Autowired
    private CasesMapper casesMapper;

    @Autowired
    private CasesService casesService;

    //@PreAuthorize("hasRole('ROLE_CASES_INCLUIR')")
    @PostMapping
    @ApiOperation(value = "Insert Cases.",
            notes = "Insert Cases.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = CasesDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = MessageResponse.class)
    })
    public ResponseEntity<?> incluir(@ApiParam(value = "Cases informations", required = true) @Valid @RequestBody CasesDTO casesDTO) {
            Cases cases = casesMapper.toEntity(casesDTO);
            return ResponseEntity.ok(casesMapper.toDTO(casesService.salvar(cases)));
    }

    /**
     * Altera a instância de {@link CasesDTO} na base de dados.
     *
     * @param id
     * @param casesDTO
     * @return
     */
    //@PreAuthorize("hasRole('ROLE_CASES_ALTERAR')")
    @ApiOperation(value = "Ater cases informations.", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = CasesDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class)
    })
    @PutMapping(path = "/{id:[\\d]+}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> alterar(
            @ApiParam(value = "Id of Cases", required = true) @PathVariable final BigDecimal id,
            @ApiParam(value = "Cases Information", required = true) @Valid @RequestBody CasesDTO casesDTO) {
        Validation.max("id", id, 99999999L);
        Cases cases = casesMapper.toEntity(casesDTO);
        cases.setId(id.longValue());
        Cases casesSaved = casesService.salvar(cases);
        return ResponseEntity.ok(casesMapper.toDTO(casesSaved));
    }

    /**
     * Retorna a instância de {@link CasesDTO} pelo id informado.
     *
     * @param id
     * s@return
     */
    //@PreAuthorize("hasRole('ROLE_CASES_PESQUISAR')")
    @ApiOperation(value = "retrive cases information by id.", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = CasesDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = MessageResponse.class) })
    @RequestMapping(method = RequestMethod.GET, path = "/{id:[\\d]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@ApiParam(value = "id of Cases", required = true) @PathVariable final BigDecimal id) {
        Validation.max("id", id, 99999999L);
        Cases cases = casesService.getById(id.longValue());
        CasesDTO casesDTO = casesMapper.toDTO(cases);

        return ResponseEntity.ok(casesDTO);
    }

    /**
     * Retorna uma lista de {@link CasesDTO} cadastrados.
     *
     * @return
     */
    //@PreAuthorize("hasRole('ROLE_CASES_PESQUISAR')")
    @ApiOperation(value = "Retrive cases recordeds.", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = CasesDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = MessageResponse.class)
    })
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> getAmigos() {
        List<Cases> casesList = casesService.getTodos();
        List<CasesDTO> amigosDTO = new ArrayList<>();
        for (Cases cases : casesList) {
            CasesDTO amigoDTO = casesMapper.toDTO(cases);
            amigosDTO.add(amigoDTO);
        }
        return ResponseEntity.ok(amigosDTO);
    }

    /**
     * Remover o {@link Amigo} pelo 'id' informado.
     *
     * @param id
     * @return
     */
    //@PreAuthorize("hasRole('ROLE_CASES_REMOVER')")
    @ApiOperation(value = "Remove cases by id.", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = CasesDTO.class),
            @ApiResponse(code = 400, message = "Bad Request", response = MessageResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = MessageResponse.class)
    })
    @DeleteMapping(path = "/{id:[\\d]+}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> remover(@ApiParam(value = "Id of cases", required = true) @PathVariable final BigDecimal id) {
        Validation.max("id", id, 99999999L);
        Cases cases = casesService.remover(id.longValue());
        return ResponseEntity.ok(casesMapper.toDTO(cases));
    }


}
