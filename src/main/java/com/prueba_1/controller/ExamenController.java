
package com.prueba_1.controller;
import com.prueba_1.domain.evento;
import com.prueba_1.service.eventoService;
import com.prueba_1.service.FirebaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/evento")
public class ExamenController {
    
    @Autowired
    private eventoService eventoService;
    
    @GetMapping("/listado")
    public String listado(Model model){
        var eventos = eventoService.getevento(false);
        model.addAttribute("eventos", eventos);
        model.addAttribute("totaleventos", eventos.size());
        return "/evento/listado";
    }
    
    @GetMapping("/nuevo")
    public String eventoNuevo(evento evento) {
        return "/evento/modifica";
    }

    @Autowired
    private FirebaseStorageService firebaseStorageService;
    
    @PostMapping("/guardar")
    public String eventoGuardar(evento evento,
            @RequestParam("imagenFile") MultipartFile imagenFile) {        
        if (!imagenFile.isEmpty()) {
            eventoService.save(evento);
            evento.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile, 
                            "evento", 
                            evento.getNombre()));
        }
        eventoService.save(evento);
        return "redirect:/evento/listado";
    }

    @GetMapping("/eliminar/{idevento}")
    public String eventoEliminar(evento evento) {
        eventoService.delete(evento);
        return "redirect:/evento/listado";
    }

    @GetMapping("/modificar/{idevento}")
    public String eventoModificar(evento evento, Model model) {
        evento = eventoService.getevento(evento);
        model.addAttribute("evento", evento);
        return "/evento/modifica";
    }
}
