package zambom.intermediaria.prova.Usuarios;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UsuarioService {
    public ResponseEntity<RetornaUsuarioDTO> getUsuario(String cpfUsuario) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity("http://184.72.80.215:8080/usuario/" + cpfUsuario, RetornaUsuarioDTO.class);
    }
    
}
