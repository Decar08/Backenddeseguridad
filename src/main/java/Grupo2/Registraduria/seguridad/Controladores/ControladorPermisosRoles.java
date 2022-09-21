package Grupo2.Registraduria.seguridad.Controladores;

import Grupo2.Registraduria.seguridad.Modelos.PermisosRoles;
import Grupo2.Registraduria.seguridad.Modelos.Rol;
import Grupo2.Registraduria.seguridad.Repositorios.RepositorioPermisosRoles;
import Grupo2.Registraduria.seguridad.Repositorios.RepositorioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
@RequestMapping("/administrar/permisosRoles/")
public class ControladorPermisosRoles {

    @Autowired
    private RepositorioPermisosRoles miRepositorioPermisoRoles;

    @Autowired
    private RepositorioRol miRepositorioRol;

    @GetMapping("")
    public ResponseEntity<Object> index(){

        return ResponseEntity.ok(this.miRepositorioPermisoRoles.findAll());
    }

    /**
     * Asignación rol y permiso
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("rol/{id_rol}/permiso/")
    public ResponseEntity<Object> create(@PathVariable String id_rol){
        PermisosRoles nuevo = new PermisosRoles();

        Rol rol = this.miRepositorioRol.findById(id_rol).orElse(null);
        if (rol == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol no encontrado");

        nuevo.setRol(rol);
        return ResponseEntity.ok(this.miRepositorioPermisoRoles.save(nuevo));
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> show(@PathVariable String id){
        PermisosRoles permisosRolesActual=this.miRepositorioPermisoRoles
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Permiso Rol no encontrado"));
        return ResponseEntity.ok(permisosRolesActual);
    }

    @PutMapping("{id}/rol/{id_rol}/permiso/")
    public PermisosRoles update(@PathVariable String id,@PathVariable String id_rol){

        PermisosRoles permisosRolesActual=this.miRepositorioPermisoRoles
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Permiso Rol no encontrado"));

        Rol rol=this.miRepositorioRol
                .findById(id_rol)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rol no encontrado"));

        permisosRolesActual.setRol(rol);

        return ResponseEntity.ok(this.miRepositorioPermisoRoles.save(permisosRolesActual)).getBody();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        PermisosRoles permisosRolesActual=this.miRepositorioPermisoRoles
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Permiso Rol no encontrado"));

        this.miRepositorioPermisoRoles.delete(permisosRolesActual);
    }
}
