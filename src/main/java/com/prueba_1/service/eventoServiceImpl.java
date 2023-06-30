
package com.prueba_1.service;

import com.prueba_1.dao.eventoDao;
import com.prueba_1.domain.evento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class eventoServiceImpl {
    @Autowired
    private eventoDao eventoDao;
    
    @Transactional(readOnly = true)
    public List<evento> getevento(boolean activos){
        var lista=eventoDao.findAll();
        if(activos){ //Se deben eliminar los que no están activos...
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }
    @Transactional(readOnly = true)
    public evento getevento(evento evento) {
        return eventoDao.findById(evento.getNombre()).orElse(null);
    }

    @Transactional
    public void save(evento evento) {
        eventoDao.save(evento);
    }

    @Transactional
    public void delete(evento evento) {
        eventoDao.delete(evento);
    }
}
