package pe.edu.utp.Servlets.Cursos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.CursoDAOImp;
import pe.edu.utp.model.Curso;
import pe.edu.utp.repository.CursoDAO;
import java.io.IOException;

@WebServlet("/editarCurso")
public class EditarCursoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idCurso = request.getParameter("id");
        String nombreCurso = request.getParameter("name");

        Curso curso = new Curso();
        curso.setIdCurso(idCurso);
        curso.setNombre(nombreCurso);

        CursoDAO cursoDAO = new CursoDAOImp();
        boolean actualizado = cursoDAO.actualizarCurso(curso);

        if (actualizado) {
            response.sendRedirect(request.getContextPath() + "/listarCursos");
        } else {
            response.getWriter().println("<h2>Error al actualizar el curso</h2>");
        }
    }
}
