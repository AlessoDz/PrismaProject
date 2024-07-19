package pe.edu.utp.Implement;

import pe.edu.utp.BaseDatos.ConexionBD;
import pe.edu.utp.model.Curso;
import pe.edu.utp.repository.CursoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAOImp implements CursoDAO {
    private static final String INSERTAR_CURSO_SQL = "INSERT INTO course (name) VALUES (?)";
    private static final String LISTAR_CURSOS_SQL = "SELECT * FROM course";
    private static final String BUSCAR_CURSOS_SQL = "SELECT * FROM course WHERE name LIKE ?";
    private static final String ACTUALIZAR_CURSO_SQL = "UPDATE course SET name = ? WHERE id_course = ?";
    private static final String ELIMINAR_CURSO_SQL = "DELETE FROM course WHERE id_course = ?";

    @Override
    public void agregarCurso(Curso curso) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(INSERTAR_CURSO_SQL)) {
            preparedStatement.setString(1, curso.getNombre());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al agregar el curso: " + ex.getMessage());
        }
    }

    @Override
    public List<Curso> listarCursos() {
        List<Curso> cursos = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(LISTAR_CURSOS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(resultSet.getString("id_course"));
                curso.setNombre(resultSet.getString("name"));
                cursos.add(curso);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar los cursos: " + ex.getMessage());
        }
        return cursos;
    }

    @Override
    public List<Curso> buscarCursos(String query) {
        List<Curso> cursos = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(BUSCAR_CURSOS_SQL)) {
            preparedStatement.setString(1, "%" + query + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Curso curso = new Curso();
                    curso.setIdCurso(resultSet.getString("id_course"));
                    curso.setNombre(resultSet.getString("name"));
                    cursos.add(curso);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar cursos: " + ex.getMessage());
        }
        return cursos;
    }

    @Override
    public boolean actualizarCurso(Curso curso) {
        boolean actualizado = false;
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(ACTUALIZAR_CURSO_SQL)) {
            preparedStatement.setString(1, curso.getNombre());
            preparedStatement.setString(2, curso.getIdCurso());
            int filasActualizadas = preparedStatement.executeUpdate();
            actualizado = filasActualizadas > 0;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar el curso: " + ex.getMessage());
        }
        return actualizado;
    }

    @Override
    public boolean eliminarCurso(String idCurso) {
        boolean eliminado = false;
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(ELIMINAR_CURSO_SQL)) {
            preparedStatement.setString(1, idCurso);
            int filasEliminadas = preparedStatement.executeUpdate();
            eliminado = filasEliminadas > 0;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar el curso: " + ex.getMessage());
        }
        return eliminado;
    }
}
