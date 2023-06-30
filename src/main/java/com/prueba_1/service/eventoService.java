package com.prueba_1.service;

import com.prueba_1.domain.evento;
import java.util.List;
public interface eventoService {
    public List<evento> getevento(boolean activos);
    public evento getevento(evento evento);
    public void save(evento evento);
    public void delete(evento evento);
}
