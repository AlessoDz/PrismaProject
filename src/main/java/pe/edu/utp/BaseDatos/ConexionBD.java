package pe.edu.utp.BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/prisma?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "alesso31";

    public static Connection obtenerConexion() {
        Connection conexion = null;
        try {
            // Cargar el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Obtener conexión a la base de datos
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error en la conexión a la base de datos: " + ex.getMessage());
        }
        return conexion;
    }

    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Connection conexion = ConexionBD.obtenerConexion();

        if (conexion != null) {
            System.out.println("Conexión a la base de datos MySQL establecida con éxito.");
            ConexionBD.cerrarConexion(conexion);
        } else {
            System.err.println("No se pudo conectar a la base de datos MySQL.");
        }
    }
}
