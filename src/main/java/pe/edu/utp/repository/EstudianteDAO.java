package pe.edu.utp.repository;

import pe.edu.utp.model.Estudiante;
import java.util.List;

public interface EstudianteDAO {
    void registrarEstudiante(Estudiante estudiante);
    List<Estudiante> listarEstudiante() ;
    boolean actualizarEstudiante(Estudiante estudiante);
    boolean eliminarEstudiante(String idEstudiante);
    List<Estudiante> buscarEstudiante(String query);
}
