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

@WebServlet("/clasesPorDocente")
public class ClasesPorDocente extends HttpServlet {
    private ClaseDAO claseDAO = new ClaseDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idTeacher = request.getParameter("id_teacher");

        if (idTeacher == null || idTeacher.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        List<Clase> clases = claseDAO.obtenerClasesPorDocente(idTeacher);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // Convertir la lista de clases a formato JSON
        out.print("[");
        for (int i = 0; i < clases.size(); i++) {
            Clase clase = clases.get(i);
            out.print("{");
            out.print("\"title\":\"Clase\",");
            out.print("\"start\":\"" + clase.getDay() + "T" + clase.getStartTime() + "\",");
            out.print("\"end\":\"" + clase.getDay() + "T" + clase.getEndTime() + "\",");
            out.print("\"backgroundColor\":\"#ff9f00\",");
            out.print("\"borderColor\":\"#ff9f00\"");
            out.print("}");
            if (i < clases.size() - 1) {
                out.print(",");
            }
        }
        out.print("]");
        out.flush();
    }
}
