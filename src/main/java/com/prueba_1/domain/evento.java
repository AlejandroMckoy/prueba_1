package com.prueba_1.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name = "evento")

public class evento implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private long nombre;
    private int asistentes;
    private String rutaImagen;
    private Date fecha_evento;

    public evento() {
    }

    public boolean isActivo() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    
}
