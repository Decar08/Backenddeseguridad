package Grupo2.Registraduria.seguridad.Repositorios;
import Grupo2.Registraduria.seguridad.Modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioUsuario extends MongoRepository<Usuario,String> {
}
