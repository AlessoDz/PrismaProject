package pe.edu.utp.Implement;

import pe.edu.utp.BaseDatos.ConexionBD;
import pe.edu.utp.repository.LoginDAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        } finally {

        }

        return tipoUsuario;
    }
}
