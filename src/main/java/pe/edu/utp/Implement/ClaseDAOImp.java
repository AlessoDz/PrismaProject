package pe.edu.utp.Implement;

import pe.edu.utp.BaseDatos.ConexionBD;
import pe.edu.utp.model.Aula;
import pe.edu.utp.model.Clase;
import pe.edu.utp.model.Curso;
import pe.edu.utp.model.Docente;
import pe.edu.utp.repository.ClaseDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClaseDAOImp implements ClaseDAO {

    private static final String OBTENER_AULAS_SQL = "SELECT id_classroom, code FROM classroom";
    private static final String OBTENER_CURSOS_SQL = "SELECT id_course, name FROM course";
    private static final String OBTENER_DOCENTES_SQL = "SELECT id_teacher, profile FROM teacher";
    private static final String REGISTRAR_CLASE_SQL = "{CALL registrarClase(?, ?, ?, ?, ?, ?)}";
    private static final String BUSCAR_CLASES_SQL = "SELECT * FROM classroom WHERE code LIKE ?";
    private static final String ACTUALIZAR_CLASES_SQL = "UPDATE classroom SET code = ? WHERE id_classroom = ?";
    private static final String ELIMINAR_CLASES_SQL = "DELETE FROM class WHERE id_class = ?";
    private static final String VERIFICAR_CRUCE_HORARIO_SQL = "SELECT COUNT(*) FROM class WHERE id_teacher = ? AND day = ? AND (start_time < ? AND end_time > ?)";

    @Override
    public void registrarClase(Clase clase) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             CallableStatement stmt = conexion.prepareCall(REGISTRAR_CLASE_SQL)) {

            stmt.setString(1, clase.getDay());
            stmt.setString(2, clase.getEndTime());
            stmt.setString(3, clase.getStartTime());
            stmt.setLong(4, Long.parseLong(clase.getIdClassroom()));
            stmt.setLong(5, Long.parseLong(clase.getIdCourse()));
            stmt.setString(6, clase.getIdTeacher());

            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Error al registrar la clase: " + ex.getMessage());
        }
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
                clase.setStartTime(rs.getString("start_time"));
                clase.setEndTime(rs.getString("end_time"));
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
    public List<Aula> buscarClase(String query) {
        return List.of();
    }

    @Override
    public boolean actualizarClase(Clase clase) {
        return false;
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
    public boolean verificarCruceHorario(String day, String startTime, String endTime, String idTeacher) {
        boolean cruce = false;
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(VERIFICAR_CRUCE_HORARIO_SQL)) {

            stmt.setString(1, idTeacher);
            stmt.setString(2, day);
            stmt.setString(3, endTime);
            stmt.setString(4, startTime);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cruce = rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            System.out.println("Error al verificar cruce de horarios: " + ex.getMessage());
        }
        return cruce;
    }
}
