package pe.edu.utp.Servlets.Docente;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.ClaseDAOImpl;
import pe.edu.utp.model.Clase;
import pe.edu.utp.repository.ClaseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/clasesPorDocente")
public class ClasesPorDocente extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String idTeacher = request.getParameter("id_teacher");

        if (idTeacher == null || idTeacher.isEmpty()) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println("{\"status\":\"error\",\"message\":\"ID del docente es requerido.\"}");
            return;
        }

        ClaseDAO claseDAO = new ClaseDAOImpl();
        List<Clase> clases = claseDAO.obtenerClasesPorDocente(idTeacher);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        mapper.writeValue(out, clases);
    }
}
