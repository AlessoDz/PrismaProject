package pe.edu.utp.Servlets.Clases;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.ClaseDAOImp;
import pe.edu.utp.model.Clase;
import java.io.IOException;

@WebServlet("/registrarClase")
public class RegistrarClase extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String day = request.getParameter("day");
        String startTime = request.getParameter("start_time");
        String endTime = request.getParameter("end_time");
        String idClassroom = request.getParameter("id_classroom");
        String idCourse = request.getParameter("id_course");
        String idTeacher = request.getParameter("id_teacher");

        if (day == null || startTime == null || endTime == null || idClassroom == null || idCourse == null || idTeacher == null ||
                day.isEmpty() || startTime.isEmpty() || endTime.isEmpty() || idClassroom.isEmpty() || idCourse.isEmpty() || idTeacher.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Todos los campos son obligatorios.");
            return;
        }

        Clase clase = new Clase();
        clase.setDay(day);
        clase.setStartTime(startTime);
        clase.setEndTime(endTime);
        clase.setIdClassroom(idClassroom);
        clase.setIdCourse(idCourse);
        clase.setIdTeacher(idTeacher);

        ClaseDAOImp claseDAO = new ClaseDAOImp();
        claseDAO.registrarClase(clase);

        response.sendRedirect(request.getContextPath() + "/listarClases");
    }
}
