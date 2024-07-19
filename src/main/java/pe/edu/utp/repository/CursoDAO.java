package pe.edu.utp.repository;

import pe.edu.utp.model.Curso;

import java.util.List;

public interface CursoDAO {

    void agregarCurso(Curso curso);

    List<Curso> listarCursos();

    List<Curso> buscarCursos(String query);

    boolean actualizarCurso(Curso curso);

    boolean eliminarCurso(String idCurso);
}
