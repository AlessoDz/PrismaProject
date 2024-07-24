package pe.edu.utp.Servlets.Clases;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.ClaseDAOImpl;
import pe.edu.utp.repository.ClaseDAO;
import java.io.IOException;

@WebServlet("/eliminarClase")
public class EliminarClase extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idClase = request.getParameter("id");

        ClaseDAO aulaDAO = new ClaseDAOImpl();
        boolean eliminado = aulaDAO.eliminarClase(idClase);

        if (eliminado) {
            response.sendRedirect(request.getContextPath() + "/listarClases");
        } else {
            response.getWriter().println("<h2>Error al eliminar la clase</h2>");
        }
    }
}