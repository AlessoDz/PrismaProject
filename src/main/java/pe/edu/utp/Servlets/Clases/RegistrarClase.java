package pe.edu.utp.Servlets.Clases;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.ClaseDAOImpl;
import pe.edu.utp.model.Clase;
import pe.edu.utp.repository.ClaseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@WebServlet("/registrarClase")
public class RegistrarClase extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        PrintWriter out = response.getWriter();
        String day = request.getParameter("day");
        String startTimeParam = request.getParameter("start_time");
        String endTimeParam = request.getParameter("end_time");
        String idClassroomParam = request.getParameter("id_classroom");
        String idCourseParam = request.getParameter("id_course");
        String idTeacher = request.getParameter("id_teacher");

        if (day == null || day.isEmpty() ||
                startTimeParam == null || startTimeParam.isEmpty() ||
                endTimeParam == null || endTimeParam.isEmpty() ||
                idClassroomParam == null || idClassroomParam.isEmpty() ||
                idCourseParam == null || idCourseParam.isEmpty() ||
                idTeacher == null || idTeacher.isEmpty()) {

            out.println("{\"status\":\"error\",\"message\":\"Todos los campos son requeridos.\"}");
            return;
        }

        // Convertir y validar los tipos de datos
        Time startTime;
        Time endTime;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("h:mm a", Locale.ENGLISH);
            java.util.Date startDate = sdf.parse(startTimeParam);
            java.util.Date endDate = sdf.parse(endTimeParam);
            startTime = new Time(startDate.getTime());
            endTime = new Time(endDate.getTime());
        } catch (ParseException e) {
            out.println("{\"status\":\"error\",\"message\":\"Formato de hora inválido. Use 'h:mm a' (AM/PM).\"}");
            return;
        }

        // Validar que la hora de inicio sea antes de la hora de finalización
        if (startTime.after(endTime)) {
            out.println("{\"status\":\"error\",\"message\":\"La hora de inicio debe ser antes de la hora de finalización.\"}");
            return;
        }

        // Crear objeto Clase
        Clase clase = new Clase();
        clase.setDay(day);
        clase.setStartTime(startTime);
        clase.setEndTime(endTime);
        clase.setIdClassroom(idClassroomParam);
        clase.setIdCourse(idCourseParam);
        clase.setIdTeacher(idTeacher);

        // Registrar la clase
        ClaseDAO claseDAO = new ClaseDAOImpl();
        boolean registrada = claseDAO.registrarClase(clase);

        if (registrada) {
            // Mostrar mensaje de éxito
            out.println("{\"status\":\"success\",\"message\":\"Clase registrada exitosamente.\"}");
        } else {
            // Mostrar mensaje de error
            out.println("{\"status\":\"error\",\"message\":\"No se puede registrar la hora ya que hay cruce de horario\"}\n");
        }
    }
}
