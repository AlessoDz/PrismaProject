package pe.edu.utp.Implement;
import pe.edu.utp.BaseDatos.ConexionBD;
import pe.edu.utp.model.Estudiante;
import pe.edu.utp.repository.EstudianteDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAOImpl implements EstudianteDAO {
    private static final String INSERTAR_ESTUDIANTE_SQL = "{CALL registrarEstudiante(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    private static final String LISTAR_ESTUDIANTES_SQL = "{CALL listarEstudiantes()}";
    private static final String ACTUALIZAR_ESTUDIANTE_SQL = "{CALL actualizarEstudiante(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    private static final String ELIMINAR_ESTUDIANTE_SQL = "{CALL eliminarEstudiante(?)}";
    private static final String BUSCAR_ESTUDIANTES_SQL = "{CALL buscarEstudiantes(?)}";


    @Override
    public void registrarEstudiante(Estudiante estudiante) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             CallableStatement callableStatement = conexion.prepareCall(INSERTAR_ESTUDIANTE_SQL)) {
            callableStatement.setString(1, estudiante.getId_student());
            callableStatement.setTimestamp(2, Timestamp.valueOf(estudiante.getEntrydate()));
            callableStatement.setInt(3, estudiante.getGrade());
            callableStatement.setString(4, estudiante.getPassword());
            callableStatement.setBoolean(5, estudiante.getPayment_status());
            callableStatement.setString(6, estudiante.getProfile());
            callableStatement.setString(7, estudiante.getSection());
            callableStatement.setString(8, estudiante.getShift());
            callableStatement.setString(9, estudiante.getStudy_level());
            callableStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al registrar el Estudiante: " + ex.getMessage());
        }
    }

    @Override
    public List<Estudiante> listarEstudiante() {
        List<Estudiante> listaestudiantes = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
             CallableStatement callableStatement = conexion.prepareCall(LISTAR_ESTUDIANTES_SQL);
             ResultSet resultSet = callableStatement.executeQuery()) {
            while (resultSet.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setId_student(resultSet.getString("id_student"));
                estudiante.setEntrydate(resultSet.getDate("entrydate").toLocalDate().atStartOfDay());
                estudiante.setGrade(resultSet.getInt("grade"));
                estudiante.setPassword(resultSet.getString("password"));
                estudiante.setPayment_status(resultSet.getBoolean("payment_status"));
                estudiante.setProfile(resultSet.getString("profile"));
                estudiante.setSection(resultSet.getString("section"));
                estudiante.setShift(resultSet.getString("shift"));
                estudiante.setStudy_level(resultSet.getString("study_level"));
                listaestudiantes.add(estudiante);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar los Estudiantes: " + ex.getMessage());
        }
        return listaestudiantes;
    }

    @Override
    public boolean actualizarEstudiante(Estudiante estudiante) {
        boolean actualizado = false;
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareCall(ACTUALIZAR_ESTUDIANTE_SQL)) {
            preparedStatement.setString(1, estudiante.getId_student());
            preparedStatement.setDate(2, Date.valueOf(String.valueOf(estudiante.getEntrydate())));
            preparedStatement.setInt(3, estudiante.getGrade());
            preparedStatement.setString(4, estudiante.getPassword());
            preparedStatement.setString(5, estudiante.getPassword());
            preparedStatement.setBoolean(6, estudiante.getPayment_status());
            preparedStatement.setString(7, estudiante.getProfile());
            preparedStatement.setString(8, estudiante.getSection());
            preparedStatement.setString(9, estudiante.getShift());
            preparedStatement.setString(10, estudiante.getStudy_level());
            int filasActualizadas = preparedStatement.executeUpdate();
            actualizado = filasActualizadas > 0;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar el estudiante: " + ex.getMessage());
        }
        return actualizado;
    }

    @Override
    public boolean eliminarEstudiante(String idEstudiante) {
        boolean eliminado = false;
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareCall(ELIMINAR_ESTUDIANTE_SQL)) {
            preparedStatement.setString(1, idEstudiante);
            int filasEliminadas = preparedStatement.executeUpdate();
            eliminado = filasEliminadas > 0;

        } catch (SQLException ex) {
            System.out.println("Error al eliminar el estudiante: " + ex.getMessage());

        }
        return eliminado;
    }

    @Override
    public List<Estudiante> buscarEstudiante(String query) {
        List<Estudiante> estudiantes = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(BUSCAR_ESTUDIANTES_SQL)) {
            preparedStatement.setString(1, "%" + query + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Estudiante estudiante = new Estudiante();
                    estudiante.setId_student(resultSet.getString("id_student"));
                    estudiante.setEntrydate(resultSet.getDate("entrydate").toLocalDate().atStartOfDay());
                    estudiante.setGrade(resultSet.getInt("grade"));
                    estudiante.setPassword(resultSet.getString("password"));
                    estudiante.setPayment_status(resultSet.getBoolean("payment_status"));
                    estudiante.setProfile(resultSet.getString("profile"));
                    estudiante.setSection(resultSet.getString("section"));
                    estudiante.setShift(resultSet.getString("shift"));
                    estudiante.setStudy_level(resultSet.getString("study_level"));
                }
            }
        }catch (SQLException ex) {
            System.out.println("Error al buscar estudiante: " + ex.getMessage());
        }
        return estudiantes;
    }
}
