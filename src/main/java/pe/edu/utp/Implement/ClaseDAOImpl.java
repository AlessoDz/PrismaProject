package pe.edu.utp.Implement;

import pe.edu.utp.BaseDatos.ConexionBD;
import pe.edu.utp.model.Aula;
import pe.edu.utp.model.Clase;
import pe.edu.utp.model.Curso;
import pe.edu.utp.model.Docente;
import pe.edu.utp.repository.ClaseDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClaseDAOImpl implements ClaseDAO {
    private static final String REGISTRAR_CLASE_SQL = "{CALL registrar_clase(?, ?, ?, ?, ?, ?)}";
    private static final String OBTENER_AULAS_SQL = "SELECT id_classroom, code FROM classroom";
    private static final String OBTENER_CURSOS_SQL = "SELECT id_course, name FROM course";
    private static final String OBTENER_DOCENTES_SQL = "SELECT id_teacher, profile FROM teacher";
    private static final String ELIMINAR_CLASES_SQL = "DELETE FROM class WHERE id_class = ?";
    @Override
    public boolean registrarClase(Clase clase) {
        boolean registrado = false;
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(REGISTRAR_CLASE_SQL)) {
            preparedStatement.setString(1, clase.getDay());
            preparedStatement.setTime(2, clase.getStartTime());
            preparedStatement.setTime(3, clase.getEndTime());
            preparedStatement.setString(4, clase.getIdClassroom());
            preparedStatement.setString(5, clase.getIdCourse());
            preparedStatement.setString(6, clase.getIdTeacher());

            preparedStatement.execute();
            registrado = true;
        } catch (SQLException ex) {
            System.out.println("Error al registrar la clase: " + ex.getMessage());
        }
        return registrado;
    }

    @Override
    public List<Aula> obtenerAulas() {
        List<Aula> aulas = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(OBTENER_AULAS_SQL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aula aula = new Aula();
                aula.setIdAula(rs.getString("id_classroom"));
                aula.setCodigo(rs.getString("code"));
                aulas.add(aula);
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener las aulas: " + ex.getMessage());
        }
        return aulas;
    }

    @Override
    public List<Curso> obtenerCursos() {
        List<Curso> cursos = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(OBTENER_CURSOS_SQL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(rs.getString("id_course"));
                curso.setNombre(rs.getString("name"));
                cursos.add(curso);
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener los cursos: " + ex.getMessage());
        }
        return cursos;
    }

    @Override
    public List<Docente> obtenerDocentes() {
        List<Docente> docentes = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(OBTENER_DOCENTES_SQL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Docente docente = new Docente();
                docente.setIdDocente(rs.getString("id_teacher"));
                docente.setProfile(rs.getString("profile"));
                docentes.add(docente);
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener los docentes: " + ex.getMessage());
        }
        return docentes;
    }

    @Override
    public List<Clase> obtenerClases() {
        List<Clase> clases = new ArrayList<>();
        String query = "SELECT id_class, day, start_time, end_time, id_classroom, id_course, id_teacher FROM class";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Clase clase = new Clase();
                clase.setIdClase(rs.getString("id_class"));
                clase.setDay(rs.getString("day"));
                clase.setStartTime(rs.getTime("start_time"));
                clase.setEndTime(rs.getTime("end_time"));
                clase.setIdClassroom(rs.getString("id_classroom"));
                clase.setIdCourse(rs.getString("id_course"));
                clase.setIdTeacher(rs.getString("id_teacher"));
                clases.add(clase);
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener las clases: " + ex.getMessage());
        }
        return clases;
    }

    @Override
    public boolean eliminarClase(String idClase) {
        boolean eliminado = false;
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(ELIMINAR_CLASES_SQL)) {
            preparedStatement.setString(1, idClase);
            int filasEliminadas = preparedStatement.executeUpdate();
            eliminado = filasEliminadas > 0;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar el aula: " + ex.getMessage());
        }
        return eliminado;
    }

    @Override
    public List<Clase> obtenerClasesPorDocente(String idTeacher) {
        List<Clase> clases = new ArrayList<>();
        String sql = "SELECT id_class, day, start_time, end_time, id_classroom, id_course, id_teacher FROM class WHERE id_teacher = ?";

        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, idTeacher);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Clase clase = new Clase();
                    clase.setIdClase(resultSet.getString("id_class"));
                    clase.setDay(resultSet.getString("day"));
                    clase.setStartTime(resultSet.getTime("start_time"));
                    clase.setEndTime(resultSet.getTime("end_time"));
                    clase.setIdClassroom(resultSet.getString("id_classroom"));
                    clase.setIdCourse(resultSet.getString("id_course"));
                    clase.setIdTeacher(resultSet.getString("id_teacher"));

                    clases.add(clase);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las clases del docente: " + e.getMessage());
        }

        return clases;
    }


}
