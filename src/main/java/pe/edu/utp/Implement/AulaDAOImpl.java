package pe.edu.utp.Implement;

import pe.edu.utp.BaseDatos.ConexionBD;
import pe.edu.utp.model.Aula;
import pe.edu.utp.repository.AulaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AulaDAOImpl implements AulaDAO {

    private static final String INSERTAR_AULA_SQL = "INSERT INTO classroom (code) VALUES (?)";
    private static final String LISTAR_AULAS_SQL = "SELECT * FROM classroom";
    private static final String BUSCAR_AULAS_SQL = "SELECT * FROM classroom WHERE code LIKE ?";
    private static final String ACTUALIZAR_AULA_SQL = "UPDATE classroom SET code = ? WHERE id_classroom = ?";
    private static final String ELIMINAR_AULA_SQL = "DELETE FROM classroom WHERE id_classroom = ?";

    @Override
    public void agregarAula(Aula aula) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(INSERTAR_AULA_SQL)) {
            preparedStatement.setString(1, aula.getCodigo());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al agregar el curso: " + ex.getMessage());
        }
    }

    @Override
    public List<Aula> listarAulas() {
        List<Aula> aulas = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(LISTAR_AULAS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Aula aula = new Aula();
                aula.setIdAula(resultSet.getString("id_classroom"));
                aula.setCodigo(resultSet.getString("code"));
                aulas.add(aula);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar los cursos: " + ex.getMessage());
        }
        return aulas;
    }

    @Override
    public List<Aula> buscarAulas(String query) {
        List<Aula> aulas = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(BUSCAR_AULAS_SQL)) {
            preparedStatement.setString(1, "%" + query + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Aula aula = new Aula();
                    aula.setCodigo(resultSet.getString("code"));
                    aulas.add(aula);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar aulas: " + ex.getMessage());
        }
        return aulas;
    }

    @Override
    public boolean actualizarAula(Aula aula) {
        boolean actualizado = false;
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(ACTUALIZAR_AULA_SQL)) {
            preparedStatement.setString(1, aula.getCodigo());
            preparedStatement.setString(2, aula.getIdAula());
            int filasActualizadas = preparedStatement.executeUpdate();
            actualizado = filasActualizadas > 0;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar el aula: " + ex.getMessage());
        }
        return actualizado;
    }

    @Override
    public boolean eliminarAula(String idAula) {
        boolean eliminado = false;
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(ELIMINAR_AULA_SQL)) {
            preparedStatement.setString(1, idAula);
            int filasEliminadas = preparedStatement.executeUpdate();
            eliminado = filasEliminadas > 0;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar el aula: " + ex.getMessage());
        }
        return eliminado;
    }
}
