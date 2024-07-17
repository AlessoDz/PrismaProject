package pe.edu.utp.Servlets.Cursos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.CursoDAOImp;
import pe.edu.utp.repository.CursoDAO;

import java.io.IOException;

@WebServlet("/eliminarCurso")
public class EliminarCurso extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCurso = Integer.parseInt(request.getParameter("id"));

        CursoDAO cursoDAO = new CursoDAOImp();
        boolean eliminado = cursoDAO.eliminarCurso(idCurso);

        if (eliminado) {
            response.sendRedirect(request.getContextPath() + "/listarCursos");
        } else {
            response.getWriter().println("<h2>Error al eliminar el curso</h2>");
        }
    }
}
