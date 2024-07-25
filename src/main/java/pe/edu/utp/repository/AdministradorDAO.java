package pe.edu.utp.repository;

import pe.edu.utp.model.Administrador;
import pe.edu.utp.model.Docente;

import java.util.List;

public interface AdministradorDAO {

    void registrarAdministrador(Administrador administrador);
    List<Administrador> listarAdministrador() ;
    boolean actualizarAdministrador(Administrador administrador);
    boolean eliminarAdministrador(String idAdministrador);
    List<Administrador> buscarAdministrador(String query);

}
