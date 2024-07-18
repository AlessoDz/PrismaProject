package pe.edu.utp.Servlets.Login;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.LoginDAOImpl;
import pe.edu.utp.repository.LoginDAO;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        PrintWriter out = response.getWriter();

        String profile = request.getParameter("profile");
        String password = request.getParameter("password");

        if (profile == null || password == null || profile.isEmpty() || password.isEmpty()) {
            out.println("El perfil y la contraseña son obligatorios.");
            return;
        }

        LoginDAO loginDAO = new LoginDAOImpl();
        String tipoUsuario = loginDAO.obtenerTipoUsuario(profile, password);
        String nombreUsuario = loginDAO.obtenerIdUsuario(profile, password);

        if (tipoUsuario != null && nombreUsuario != null) {
            switch (tipoUsuario) {
                case "Administrador":
                    response.sendRedirect("/HTML/administrador/dashboardAdmin.html?id=" + nombreUsuario);
                    break;
                case "Docente":
                    response.sendRedirect("/HTML/docente/dashboardDocente.html?id=" + nombreUsuario);
                    break;
                case "Estudiante":
                    response.sendRedirect("/HTML/estudiante/dashboardEstudiante.html?id=" + nombreUsuario);
                    break;
                default:
                    out.println("Tipo de usuario no reconocido.");
                    break;
            }
        } else {
            out.println("Perfil o contraseña incorrectos. Intenta nuevamente.");
        }
    }
}
