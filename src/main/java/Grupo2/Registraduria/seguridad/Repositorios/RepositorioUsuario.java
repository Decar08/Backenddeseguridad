package Grupo2.Registraduria.seguridad.Repositorios;
import Grupo2.Registraduria.seguridad.Modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;

public interface RepositorioUsuario extends MongoRepository<Usuario,String> {

    Collection<Object> findBySeudonimo(String seudonimo);

    Collection<Object> findByCorreo(String correo);
}
