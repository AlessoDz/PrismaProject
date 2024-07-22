package pe.edu.utp.repository;

public interface LoginDAO {

    String obtenerTipoUsuario(String profile, String password);
    String obtenerIdUsuario(String profile, String password);

}
