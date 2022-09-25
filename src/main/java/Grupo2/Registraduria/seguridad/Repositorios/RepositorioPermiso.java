package Grupo2.Registraduria.seguridad.Repositorios;

import Grupo2.Registraduria.seguridad.Modelos.Permiso;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RepositorioPermiso extends MongoRepository<Permiso,String> {
}
