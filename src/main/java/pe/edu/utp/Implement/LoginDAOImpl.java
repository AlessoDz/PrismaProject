package pe.edu.utp.Implement;

import pe.edu.utp.BaseDatos.ConexionBD;
import pe.edu.utp.repository.LoginDAO;
import java.sql.*;

public class LoginDAOImpl implements LoginDAO {

    @Override
    public String obtenerTipoUsuario(String profile, String password) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        String tipoUsuario = null;

        try {
            connection = ConexionBD.obtenerConexion();
            String sql = "{CALL loginUsuario(?, ?, ?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, profile);
            callableStatement.setString(2, password);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);

            callableStatement.execute();

            tipoUsuario = callableStatement.getString(3);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tipoUsuario;
    }

    //Cambiar para entrar como diferentes usuarios
    @Override
    public String obtenerIdUsuario(String profile, String password) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        String idUsuario = null;

        try {
            connection = ConexionBD.obtenerConexion();
            String sql = "{CALL obtenerIdUsuario(?, ?, ?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, profile);
            callableStatement.setString(2, password);
            callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);

            callableStatement.execute();

            idUsuario = callableStatement.getString(3);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return idUsuario;
    }
}