package zambom.intermediaria.prova.Eventos;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class Evento {
    @Id
    private String id;
    private String nomeEvento; // O campo não é Unique, então mais de um evento pode ter o mesmo nome/pedaços do nome igual
    private String descricaoEvento;
    private Integer maxConvidados;
    private String cpfCriador;
    private List<String> convidadosEvento;
}
