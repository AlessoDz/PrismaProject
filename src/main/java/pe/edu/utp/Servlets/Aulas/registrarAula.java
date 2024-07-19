package pe.edu.utp.Servlets.Aulas;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.AulaDAOImpl;
import pe.edu.utp.model.Aula;
import pe.edu.utp.repository.AulaDAO;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registrarAula")
public class registrarAula extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        PrintWriter out = response.getWriter();
        String codigo = request.getParameter("code");

        Aula aula = new Aula();
        aula.setCodigo(codigo);

        if (codigo.length() != 9 || !codigo.startsWith("A")) {
            out.println("El código debe tener 9 dígitos y comenzar con 'A'.");
            return;
        }

        AulaDAO aulaDAO = new AulaDAOImpl();
        aulaDAO.agregarAula(aula);
        response.sendRedirect("/ListarAula");

    }
}
