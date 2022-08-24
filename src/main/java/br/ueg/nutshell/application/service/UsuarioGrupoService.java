package br.ueg.nutshell.application.service;

import br.ueg.nutshell.application.model.Grupo;
import br.ueg.nutshell.application.repository.UsuarioGrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UsuarioGrupoService {
    @Autowired
    private UsuarioGrupoRepository usuarioGrupoRepository;

    /***
     * Retorna Grupos vinculados a um usuário específico
     * @param userId
     * @return
     */
    public List<Grupo> getUsuarioGrupos(Long userId){
        return usuarioGrupoRepository.findByIdUsuario(userId);
    }

}
