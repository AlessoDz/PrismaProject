package pe.edu.utp.Servlets.Vacantes;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.UsuarioDAOImpl;
import pe.edu.utp.repository.UsuarioDAO;
import java.io.IOException;

@WebServlet("/aceptarVacante")
public class AceptarVacanteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idUsuarioStr = request.getParameter("id_user");

        if (idUsuarioStr == null || idUsuarioStr.isEmpty()) {
            response.getWriter().println("ID de usuario no proporcionado.");
            return;
        }

        int idUsuario;
        try {
            idUsuario = Integer.parseInt(idUsuarioStr);
        } catch (NumberFormatException e) {
            response.getWriter().println("ID de usuario inválido.");
            return;
        }

        UsuarioDAO userDAO = new UsuarioDAOImpl();
        System.out.println("ID de usuario recibido: " + idUsuario);
        boolean success = userDAO.actualizarTipoUsuario(idUsuario);
        System.out.println("Resultado de la actualización: " + success);

        if (success) {
            response.getWriter().println("Vacante aceptada correctamente.");
        } else {
            response.getWriter().println("Error al aceptar la vacante.");
        }
    }
}
