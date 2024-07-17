package pe.edu.utp.Implement;

import pe.edu.utp.BaseDatos.ConexionBD;
import pe.edu.utp.model.Docente;
import pe.edu.utp.repository.DocenteDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocenteDAOImpl implements DocenteDAO {

    private static final String INSERTAR_DOCENTE_SQL = "{CALL registrarDocente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    private static final String LISTAR_DOCENTES_SQL = "{CALL listarDocente()}";
    private static final String ACTUALIZAR_DOCENTE_SQL = "{CALL actualizarDocente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    private static final String ELIMINAR_DOCENTE_SQL = "{CALL eliminarDocente(?)}";
    private static final String BUSCAR_DOCENTES_SQL = "{CALL buscarDocentes(?)}";

    @Override
    public void registrarDocente(Docente docente) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             CallableStatement callableStatement = conexion.prepareCall(INSERTAR_DOCENTE_SQL)) {
            callableStatement.setString(1,docente.getIdDocente());
            callableStatement.setString(2, docente.getPassword());
            callableStatement.setString(3, docente.getProfile());
            callableStatement.setString(4, docente.getNombre());
            callableStatement.setString(5, docente.getApellido());
            callableStatement.setString(6, docente.getFechaNacimiento());
            callableStatement.setString(7, docente.getDni());
            callableStatement.setString(8, docente.getEmail());
            callableStatement.setString(9, docente.getTelefono());
            callableStatement.setString(10, docente.getSpeciality());

            callableStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al registrar el docente: " + ex.getMessage());
        }
    }

    @Override
    public List<Docente> listarDocentes() {
        List<Docente> listaDocentes = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
             CallableStatement callableStatement = conexion.prepareCall(LISTAR_DOCENTES_SQL);
             ResultSet resultSet = callableStatement.executeQuery()) {
            while (resultSet.next()) {
                Docente docente = new Docente();
                docente.setIdDocente(resultSet.getString("id_teacher"));
                docente.setProfile(resultSet.getString("profile"));
                docente.setPassword(resultSet.getString("password"));
                docente.setSpeciality(resultSet.getString("speciality_name"));
                docente.setNombre(resultSet.getString("name"));
                docente.setApellido(resultSet.getString("last_name"));
                docente.setFechaNacimiento(resultSet.getString("birth_date"));
                docente.setDni(resultSet.getString("dni"));
                docente.setEmail(resultSet.getString("email"));
                docente.setTelefono(resultSet.getString("phone"));
                docente.setFechaRegistro(resultSet.getString("registration_date"));

                listaDocentes.add(docente);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar los docentes: " + ex.getMessage());
        }
        return listaDocentes;
    }

    @Override
    public boolean actualizarDocente(Docente docente) {
        boolean actualizado = false;
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareCall(ACTUALIZAR_DOCENTE_SQL)) {
            preparedStatement.setString(1, docente.getIdDocente());
            preparedStatement.setString(2, docente.getPassword());
            preparedStatement.setString(3, docente.getProfile());
            preparedStatement.setString(4, docente.getNombre());
            preparedStatement.setString(5, docente.getApellido());
            preparedStatement.setString(6, docente.getFechaNacimiento());
            preparedStatement.setString(7, docente.getDni());
            preparedStatement.setString(8, docente.getEmail());
            preparedStatement.setString(9, docente.getTelefono());
            preparedStatement.setString(10, docente.getSpeciality());

            int filasActualizadas = preparedStatement.executeUpdate();
            actualizado = filasActualizadas > 0;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar el docente: " + ex.getMessage());
        }
        return actualizado;
    }

    @Override
    public boolean eliminarDocente(String idDocente) {
        boolean eliminado = false;
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareCall(ELIMINAR_DOCENTE_SQL)) {
            preparedStatement.setString(1, idDocente);
            int filasEliminadas = preparedStatement.executeUpdate();
            eliminado = filasEliminadas > 0;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar el docente: " + ex.getMessage());
        }
        return eliminado;
    }

    @Override
    public List<Docente> buscarDocentes(String query) {
        List<Docente> docentes = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement preparedStatement = conexion.prepareStatement(BUSCAR_DOCENTES_SQL)) {
            preparedStatement.setString(1, "%" + query + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Docente docente = new Docente();
                    docente.setIdDocente(resultSet.getString("id_teacher"));
                    docente.setProfile(resultSet.getString("profile"));
                    docente.setNombre(resultSet.getString("name"));
                    docente.setApellido(resultSet.getString("last_name"));
                    docente.setEmail(resultSet.getString("email"));
                    docente.setTelefono(resultSet.getString("phone"));
                    docentes.add(docente);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar cursos: " + ex.getMessage());
        }
        return docentes;
    }
}
