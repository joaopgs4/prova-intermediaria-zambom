package zambom.intermediaria.prova.Eventos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<Evento> listarEventos() {
        return eventoService.listar();
    }

    // Retorna todos os eventos que contenham este pedaço do nome
    @GetMapping("/{nomeEvento}")
    public List<Evento> findEvento(@PathVariable String nomeEvento) {
        return eventoService.getEvento(nomeEvento);
    }

    @PostMapping
    public Evento salvarEvento(@RequestBody Evento evento) {
        return eventoService.createEvento(evento);
    }

    @PostMapping("/{cpf}")
    public Evento adicionarConvidado(@RequestBody Evento evento, @PathVariable String cpf) {
        return eventoService.addConvidado(evento, cpf);
    }
    
}
