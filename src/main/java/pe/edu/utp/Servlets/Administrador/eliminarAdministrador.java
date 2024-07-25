package pe.edu.utp.Servlets.Administrador;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.DocenteDAOImpl;
import pe.edu.utp.repository.DocenteDAO;
import java.io.IOException;

@WebServlet("/eliminarAdministrador")
public class eliminarAdministrador extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idDocente = request.getParameter("id_admin");

        DocenteDAO docenteDAO = new DocenteDAOImpl();
        boolean eliminado = docenteDAO.eliminarDocente(idDocente);

        if (eliminado) {
            response.sendRedirect(request.getContextPath() + "/listarAdministrador");
        } else {
            response.sendRedirect(request.getContextPath() + "/listarAdministrador");
        }
    }
}
