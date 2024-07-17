package pe.edu.utp.Servlets.Cursos;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.CursoDAOImp;
import pe.edu.utp.model.Curso;
import pe.edu.utp.repository.CursoDAO;

import java.io.IOException;

@WebServlet("/registrarCurso")
public class RegistrarCursoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nombreCurso = request.getParameter("name");

        Curso curso = new Curso();
        curso.setNombre(nombreCurso);

        CursoDAO cursoDAO = new CursoDAOImp();
        cursoDAO.agregarCurso(curso);

        response.sendRedirect("/listarCursos");
    }
}
