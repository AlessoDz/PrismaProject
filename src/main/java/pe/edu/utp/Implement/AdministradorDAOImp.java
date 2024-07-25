package pe.edu.utp.Implement;

import pe.edu.utp.BaseDatos.ConexionBD;
import pe.edu.utp.model.Administrador;
import pe.edu.utp.repository.AdministradorDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDAOImp implements AdministradorDAO {

    private static final String INSERTAR_ADMINISTRADOR_SQL = "{CALL registrarAdministrador(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    private static final String LISTAR_ADMINISTRADOR_SQL = "{CALL listarAdministrador()}";
    private static final String ACTUALIZAR_ADMINISTRADOR_SQL = "{CALL actualizarAdministrador(?, ?, ?, ?, ?, ?, ?, ?)}";
    private static final String ELIMINAR_ADMINISTRADOR_SQL = "{CALL eliminarAdministrador(?)}";
    private static final String BUSCAR_ADMINISTRADOR_SQL = "{CALL buscarAdministradores(?)}";

    @Override
    public void registrarAdministrador(Administrador administrador) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             CallableStatement callableStatement = conexion.prepareCall(INSERTAR_ADMINISTRADOR_SQL)) {
            callableStatement.setString(1,administrador.getIdAdmin());
            callableStatement.setString(2, administrador.getPassword());
            callableStatement.setString(3, administrador.getProfile());
            callableStatement.setString(4, administrador.getName());
            callableStatement.setString(5, administrador.getLastName());
            callableStatement.setString(6, administrador.getBirthDate());
            callableStatement.setString(7, administrador.getDni());
            callableStatement.setString(8, administrador.getEmail());
            callableStatement.setString(9, administrador.getPhone());

            callableStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al registrar el administrador: " + ex.getMessage());
        }
    }

    @Override
    public List<Administrador> listarAdministrador() {
        List<Administrador> administradores = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
             CallableStatement callableStatement = conexion.prepareCall(LISTAR_ADMINISTRADOR_SQL);
             ResultSet resultSet = callableStatement.executeQuery()) {

            while (resultSet.next()) {
                Administrador administrador = new Administrador();
                administrador.setIdAdmin(resultSet.getString("id_admin"));
                administrador.setProfile(resultSet.getString("profile"));
                administrador.setPassword(resultSet.getString("password"));
                administrador.setName(resultSet.getString("name"));
                administrador.setLastName(resultSet.getString("last_name"));
                administrador.setBirthDate(resultSet.getString("birth_date"));
                administrador.setDni(resultSet.getString("dni"));
                administrador.setEmail(resultSet.getString("email"));
                administrador.setPhone(resultSet.getString("phone"));
                administrador.setRegistrationDate(resultSet.getDate("registration_date"));

                administradores.add(administrador);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar administradores: " + ex.getMessage());
        }
        return administradores;
    }

    @Override
    public boolean actualizarAdministrador(Administrador administrador) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             CallableStatement callableStatement = conexion.prepareCall(ACTUALIZAR_ADMINISTRADOR_SQL)) {
            callableStatement.setInt(1, Integer.parseInt(administrador.getIdAdmin()));
            callableStatement.setString(2, administrador.getPassword());
            callableStatement.setString(3, administrador.getProfile());
            callableStatement.setString(4, administrador.getName());
            callableStatement.setString(5, administrador.getLastName());
            callableStatement.setString(6, administrador.getBirthDate());
            callableStatement.setString(7, administrador.getDni());
            callableStatement.setString(8, administrador.getEmail());
            callableStatement.setString(9, administrador.getPhone());

            callableStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar el administrador: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarAdministrador(String idAdministrador) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             CallableStatement callableStatement = conexion.prepareCall(ELIMINAR_ADMINISTRADOR_SQL)) {
            callableStatement.setInt(1, Integer.parseInt(idAdministrador));

            callableStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar el administrador: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Administrador> buscarAdministrador(String query) {
        List<Administrador> administradores = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
             CallableStatement callableStatement = conexion.prepareCall(BUSCAR_ADMINISTRADOR_SQL)) {
            callableStatement.setString(1, query);

            try (ResultSet resultSet = callableStatement.executeQuery()) {
                while (resultSet.next()) {
                    Administrador administrador = new Administrador();
                    administrador.setIdAdmin(resultSet.getString("id_admin"));
                    administrador.setProfile(resultSet.getString("profile"));
                    administrador.setName(resultSet.getString("name"));
                    administrador.setLastName(resultSet.getString("last_name"));
                    administrador.setEmail(resultSet.getString("email"));
                    administrador.setPhone(resultSet.getString("phone"));

                    administradores.add(administrador);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar administradores: " + ex.getMessage());
        }
        return administradores;
    }
}
