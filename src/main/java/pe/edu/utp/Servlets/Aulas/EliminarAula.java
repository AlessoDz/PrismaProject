package pe.edu.utp.Servlets.Aulas;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.AulaDAOImpl;
import pe.edu.utp.repository.AulaDAO;
import java.io.IOException;

@WebServlet("/eliminarAula")
public class EliminarAula extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idAula = request.getParameter("id");

        AulaDAO aulaDAO = new AulaDAOImpl();
        boolean eliminado = aulaDAO.eliminarAula(idAula);

        if (eliminado) {
            response.sendRedirect(request.getContextPath() + "/ListarAula");
        } else {
            response.getWriter().println("<h2>Error al eliminar el aula</h2>");
        }
    }
}
