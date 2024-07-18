package pe.edu.utp.Servlets.Aulas;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.AulaDAOImpl;
import pe.edu.utp.model.Aula;
import pe.edu.utp.repository.AulaDAO;
import java.io.IOException;

@WebServlet("/editarAula")
public class EditarAula extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idAula = request.getParameter("id");
        String codigoAula = request.getParameter("code");

        Aula aula = new Aula();
        aula.setIdAula(idAula);
        aula.setCodigo(codigoAula);

        AulaDAO cursoDAO = new AulaDAOImpl();
        boolean actualizado = cursoDAO.actualizarAula(aula);

        if (actualizado) {
            response.sendRedirect(request.getContextPath() + "/ListarAula");
        } else {
            response.getWriter().println("<h2>Error al actualizar el aula</h2>");
        }
    }
}
