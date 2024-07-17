package pe.edu.utp.repository;

import pe.edu.utp.model.Usuario;
import java.util.List;

public interface UsuarioDAO {

    boolean insertarUsuario(Usuario usuario);
    boolean actualizarTipoUsuario(int idUsuario);
    List<Usuario> obtenerUsuariosPorTipo(String tipoUsuario);
}
