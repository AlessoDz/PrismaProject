package pe.edu.utp.Servlets.Login;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.LoginDAOImpl;
import pe.edu.utp.repository.LoginDAO;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginAdmin")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        PrintWriter out = response.getWriter();

        // Obtener los parámetros de perfil (profile) y contraseña (password) desde el formulario
        String profile = request.getParameter("profile");
        String password = request.getParameter("password");

        // Validaciones de campos
        if (profile == null || password == null || profile.isEmpty() || password.isEmpty()) {
            out.println("El perfil y la contraseña son obligatorios.");
            return;
        }

        LoginDAO loginDAO = new LoginDAOImpl();
        boolean autenticado = loginDAO.autenticarUsuario(profile, password);

        if (autenticado) {
            response.sendRedirect("/HTML/administrador/dashboardAdmin.html");
        } else {
            out.println("Perfil o contraseña incorrectos. Intenta nuevamente.");
        }
    }
}
