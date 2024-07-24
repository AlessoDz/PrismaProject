package pe.edu.utp.repository;

import pe.edu.utp.model.Aula;
import pe.edu.utp.model.Clase;
import pe.edu.utp.model.Curso;
import pe.edu.utp.model.Docente;

import java.util.List;

public interface ClaseDAO {

    boolean registrarClase(Clase classDTO);
    List<Aula> obtenerAulas();
    List<Curso> obtenerCursos();
    List<Docente> obtenerDocentes();
    List<Clase> obtenerClases();
    boolean eliminarClase(String idClase);
    List<Clase> obtenerClasesPorDocente(String idTeacher);

}
