package Grupo2.Registraduria.seguridad.Repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import Grupo2.Registraduria.seguridad.Modelos.Rol;

@Repository
public interface RepositorioRol extends MongoRepository<Rol, String> {
    
}
