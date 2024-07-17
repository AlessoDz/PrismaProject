package pe.edu.utp.repository;

import pe.edu.utp.model.Docente;
import java.util.List;

public interface DocenteDAO {

    void registrarDocente(Docente docente);
    List<Docente> listarDocentes() ;
    boolean actualizarDocente(Docente docente);
    boolean eliminarDocente(String idDocente);
    List<Docente> buscarDocentes(String query);
}
