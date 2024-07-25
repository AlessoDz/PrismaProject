package pe.edu.utp.Servlets.Estudiante;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.EstudianteDAOImpl;
import pe.edu.utp.repository.EstudianteDAO;

import java.io.IOException;

@WebServlet("/eliminarEstudiante")
public class EliminarEstudianteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id_student = request.getParameter("id_student");

        EstudianteDAO estudianteDAO = new EstudianteDAOImpl();
        boolean eliminado = estudianteDAO.eliminarEstudiante(id_student);

        if (eliminado) {
            response.sendRedirect(request.getContextPath() + "/listarEstudiantes");
        } else {
            response.sendRedirect(request.getContextPath() + "/listarEstudiantes");
        }
    }
}

