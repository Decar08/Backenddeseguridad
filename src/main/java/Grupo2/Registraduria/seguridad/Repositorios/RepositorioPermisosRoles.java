package Grupo2.Registraduria.seguridad.Repositorios;

import Grupo2.Registraduria.seguridad.Modelos.PermisosRoles;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface RepositorioPermisosRoles extends MongoRepository<PermisosRoles,String> {
}
