package Grupo2.Registraduria.seguridad.Controladores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import Grupo2.Registraduria.seguridad.Modelos.Permiso;
import Grupo2.Registraduria.seguridad.Modelos.PermisosRoles;
import Grupo2.Registraduria.seguridad.Modelos.Rol;
import Grupo2.Registraduria.seguridad.Repositorios.RepositorioPermiso;
import Grupo2.Registraduria.seguridad.Repositorios.RepositorioPermisosRoles;
import Grupo2.Registraduria.seguridad.Repositorios.RepositorioRol;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/permisosroles")
public class ControladorPermisosRoles {
    @Autowired
    private RepositorioPermisosRoles miRepositorioPermisoRoles;

    @Autowired
    private RepositorioPermiso miRepositorioPermiso;

    @Autowired
    private RepositorioRol miRepositorioRol;


    @GetMapping("")
    public List<PermisosRoles> index(){
        return this.miRepositorioPermisoRoles.findAll();
    }
    @ResponseStatus(HttpStatus.CREATED)

    @PostMapping("/rol/{id_rol}/permiso/{id_permiso}")
    public PermisosRoles create(@PathVariable String id_rol,@PathVariable String id_permiso){
        PermisosRoles nuevo=new PermisosRoles();
        Rol elRol=this.miRepositorioRol.findById(id_rol).orElse(null);
        Permiso elPermiso=this.miRepositorioPermiso.findById(id_permiso).orElse(null);
        if (elRol!=null && elPermiso!=null){
            nuevo.setPermiso(elPermiso);
            nuevo.setRol(elRol);
            return this.miRepositorioPermisoRoles.save(nuevo);
        }else{
            return null;
        }
    }

    @GetMapping("{id}")
    public PermisosRoles show(@PathVariable String id){
        PermisosRoles permisosRolesActual=this.miRepositorioPermisoRoles
                .findById(id)
                .orElse(null);
        return permisosRolesActual;
    }

    @PutMapping("{id}/rol/{id_rol}/permiso/{id_permiso}")
    public PermisosRoles update(@PathVariable String id,@PathVariable String id_rol,@PathVariable String id_permiso){
        PermisosRoles permisosRolesActual=this.miRepositorioPermisoRoles
                .findById(id)
                .orElse(null);
        Rol elRol=this.miRepositorioRol.findById(id_rol).orElse(null);
        Permiso elPermiso=this.miRepositorioPermiso.findById(id_permiso).orElse(null);
        if(permisosRolesActual!=null && elPermiso!=null && elRol!=null){
            permisosRolesActual.setPermiso(elPermiso);
            permisosRolesActual.setRol(elRol);
            return this.miRepositorioPermisoRoles.save(permisosRolesActual);
        }else{
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        PermisosRoles permisosRolesActual=this.miRepositorioPermisoRoles.findById(id).orElse(null);
        if (permisosRolesActual!=null){
            this.miRepositorioPermisoRoles.delete(permisosRolesActual);
            throw new ResponseStatusException(HttpStatus.ACCEPTED, "El Permiso-Rol fue eliminado.");
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El Id de PermisoRol ingresado no existe");
        }
    }
}
