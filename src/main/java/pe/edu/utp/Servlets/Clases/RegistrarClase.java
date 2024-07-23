package pe.edu.utp.Servlets.Clases;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.ClaseDAOImp;
import pe.edu.utp.repository.ClaseDAO;
import pe.edu.utp.model.Clase;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registrarClase")
public class RegistrarClase extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        PrintWriter out = response.getWriter();
        String day = request.getParameter("day");
        String startTime = request.getParameter("start_time");
        String endTime = request.getParameter("end_time");
        String idClassroom = request.getParameter("id_classroom");
        String idCourse = request.getParameter("id_course");
        String idTeacher = request.getParameter("id_teacher");

        if (day == null || startTime == null || endTime == null || idClassroom == null || idCourse == null || idTeacher == null ||
                day.isEmpty() || startTime.isEmpty() || endTime.isEmpty() || idClassroom.isEmpty() || idCourse.isEmpty() || idTeacher.isEmpty()) {
            out.println("Todos los campos son obligatorios.");
            return;
        }

        // Usar la interfaz para la referencia y la implementación concreta para la instancia
        ClaseDAO claseDAO = new ClaseDAOImp();

        // Verificar cruce de horarios
        boolean cruceHorario = claseDAO.verificarCruceHorario(day, startTime, endTime, idTeacher);

        if (cruceHorario) {
            out.println("El horario ingresado se cruza con otro ya registrado para el mismo docente.");
            return;
        }

        // Registrar nueva clase
        Clase clase = new Clase();
        clase.setDay(day);
        clase.setStartTime(startTime);
        clase.setEndTime(endTime);
        clase.setIdClassroom(idClassroom);
        clase.setIdCourse(idCourse);
        clase.setIdTeacher(idTeacher);

        claseDAO.registrarClase(clase);

        // Redirigir a la página de listado de clases
        response.sendRedirect(request.getContextPath() + "/listarClases");
    }
}
