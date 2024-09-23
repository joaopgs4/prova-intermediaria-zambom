package zambom.intermediaria.prova.Eventos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import zambom.intermediaria.prova.Usuarios.RetornaUsuarioDTO;
import zambom.intermediaria.prova.Usuarios.UsuarioService;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UsuarioService usuarioService;

    public List<Evento> listar() {
        return eventoRepository.findAll();
    }

    // Retorna uma lista de eventos que contenham aquele trecho como nome
    public List<Evento> getEvento(String nomeEvento) {
        List<Evento> eventos = eventoRepository.findAll();
        ArrayList<Evento> resp = new ArrayList<>();
        if  (nomeEvento != null) {
            for (Evento evento : eventos) {
                if (evento.getNomeEvento().contains(nomeEvento))  {
                    resp.add(evento);
                }
            }
        }
        return resp;
    }

    public Evento createEvento(Evento evento) {
        evento.setId(UUID.randomUUID().toString());
        
        ResponseEntity<RetornaUsuarioDTO> criador = usuarioService.getUsuario(evento.getCpfCriador());

        if (criador.getStatusCode().is2xxSuccessful()) {
            return eventoRepository.save(evento);
        }
        else {
            throw new RuntimeException("Criador Invalido");
        }
    }

    public Evento addConvidado(Evento evento, String cpf) {
        ResponseEntity<RetornaUsuarioDTO> convidado = usuarioService.getUsuario(cpf);

        if (convidado.getStatusCode().is2xxSuccessful() && evento.getConvidadosEvento().size() < evento.getMaxConvidados()) {
            List<String> convidados = evento.getConvidadosEvento();
            convidados.add(cpf);
            evento.setConvidadosEvento(convidados);
            return eventoRepository.save(evento);
        }
        else {
            throw new RuntimeException("Erro ao adicionar convidado");
        }

    }
}
