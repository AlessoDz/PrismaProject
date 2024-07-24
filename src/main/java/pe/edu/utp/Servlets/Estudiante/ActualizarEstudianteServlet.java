package pe.edu.utp.Servlets.Estudiante;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.EstudianteDAOImpl;
import pe.edu.utp.model.Estudiante;
import pe.edu.utp.repository.EstudianteDAO;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/actualizarEstudiante")
public class ActualizarEstudianteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String idStudent = request.getParameter("id_student");
        String entryDateStr = request.getParameter("entrydate");
        String gradeStr = request.getParameter("grade");
        String password = request.getParameter("password");
        String paymentStatusStr = request.getParameter("payment_status");
        String profile = request.getParameter("profile");
        String section = request.getParameter("section");
        String shift = request.getParameter("shift");
        String studyLevel = request.getParameter("study_level");

        Estudiante estudiante = new Estudiante();
        estudiante.setId_student(idStudent);
        estudiante.setEntrydate(LocalDate.parse(entryDateStr));
        estudiante.setGrade(Integer.parseInt(gradeStr));
        estudiante.setPassword(password);
        estudiante.setPayment_status(Boolean.valueOf(paymentStatusStr));
        estudiante.setProfile(profile);
        estudiante.setSection(section);
        estudiante.setShift(shift);
        estudiante.setStudy_level(studyLevel);

        EstudianteDAO docenteDAO = new EstudianteDAOImpl();
        boolean actualizado = docenteDAO.actualizarEstudiante(estudiante);

        if (actualizado) {
            response.sendRedirect(request.getContextPath() + "/listarEstudiante");
        } else {
            response.getWriter().println("<h2>Error al actualizar al estudiante</h2>");
        }
    }

}

