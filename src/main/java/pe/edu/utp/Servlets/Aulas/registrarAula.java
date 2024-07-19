package pe.edu.utp.Servlets.Aulas;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.AulaDAOImpl;
import pe.edu.utp.model.Aula;
import pe.edu.utp.repository.AulaDAO;
import java.io.IOException;

@WebServlet("/registrarAula")
public class registrarAula extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String codigo = request.getParameter("code");

        Aula aula = new Aula();
        aula.setCodigo(codigo);

        AulaDAO aulaDAO = new AulaDAOImpl();
        aulaDAO.agregarAula(aula);

        response.sendRedirect("/ListarAula");
    }
}
