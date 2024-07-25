package pe.edu.utp.Servlets.Estudiante;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.EstudianteDAOImpl;
import pe.edu.utp.model.Estudiante;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import static java.lang.System.out;

@WebServlet("/registrarEstudiante")
public class RegistrarEstudianteServlet extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//
//        // Obtener los parámetros del formulario
//        String idStudent = request.getParameter("id_student");
//        String entryDateStr = request.getParameter("entrydate");
//        String gradeStr = request.getParameter("grade");
//        String password = request.getParameter("password");
//        String paymentStatusStr = request.getParameter("payment_status");
//        String profile = request.getParameter("profile");
//        String section = request.getParameter("section");
//        String shift = request.getParameter("shift");
//        String studyLevel = request.getParameter("study_level");
//
//        // Validar que los parámetros no sean nulos o vacíos
//        if (idStudent == null || entryDateStr == null || gradeStr == null || password == null ||
//                paymentStatusStr == null || profile == null || section == null || shift == null || studyLevel == null ||
//                idStudent.isEmpty() || entryDateStr.isEmpty() || gradeStr.isEmpty() || password.isEmpty() ||
//                paymentStatusStr.isEmpty() || profile.isEmpty() || section.isEmpty() || shift.isEmpty() || studyLevel.isEmpty()) {
//            response.getWriter().println("Todos los campos son obligatorios.");
//            return;
//        }
//
//        // Parsear los datos de entrada
//        LocalDate entryDate = LocalDate.parse(entryDateStr);
//        int grade = Integer.parseInt(gradeStr);
//        boolean paymentStatus = Boolean.parseBoolean(paymentStatusStr);
//
//        // Crear un objeto Estudiante con los datos recibidos
//        Estudiante estudiante = new Estudiante(idStudent, entryDate, grade, password, paymentStatus, profile, section, shift, studyLevel);
//
//        // Aquí puedes agregar la lógica para guardar el objeto estudiante en la base de datos
//
//        // Establecer el atributo del estudiante en la solicitud
//        request.setAttribute("estudiante", estudiante);
//
//        // Redirigir a una página de confirmación o mostrar un mensaje de éxito
//        request.getRequestDispatcher("/success.jsp").forward(request, response);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.getWriter().println("El Método HTTP GET no es soportado por esta URL");
//    }
//}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Obtener los parámetros del formulario
        PrintWriter out = response.getWriter();
        String idStudent = request.getParameter("id_student");
        String entryDateStr = request.getParameter("entry_date");
        String gradeStr = request.getParameter("grade");
        String password = request.getParameter("password");
        String paymentStatusStr = request.getParameter("payment_status");
        String profile = request.getParameter("profile");
        String section = request.getParameter("section");
        String shift = request.getParameter("shift");
        String studyLevel = request.getParameter("study_level");

        if (idStudent == null || entryDateStr == null || gradeStr == null || password == null ||
                paymentStatusStr == null || profile == null || section == null || shift == null || studyLevel == null ||
                idStudent.isEmpty() || entryDateStr.isEmpty() || gradeStr.isEmpty() || password.isEmpty() ||
                paymentStatusStr.isEmpty() || profile.isEmpty() || section.isEmpty() || shift.isEmpty() || studyLevel.isEmpty()) {
            response.getWriter().println("Todos los campos son obligatorios.");
            return;
        }
        LocalDate entryDate = LocalDate.parse(entryDateStr);
        int grade = Integer.parseInt(gradeStr);
        boolean paymentStatus = Boolean.parseBoolean(paymentStatusStr);

        if (profile.length() != 9 || !profile.startsWith("U")) {
            out.println("El perfil debe tener 9 dígitos y comenzar con 'U'.");
            return;
        }

        if (idStudent.length() != 9 || !idStudent.startsWith("U")) {
            out.println("Id del estudiante debe tener 9 dígitos y comenzar con 'U'.");
            return;
        }

        Estudiante estudiante = new Estudiante();
        estudiante.setId_student(idStudent);
        estudiante.setEntrydate(entryDate.atStartOfDay());
        estudiante.setGrade(grade);
        estudiante.setPassword(password);
        estudiante.setPayment_status(paymentStatus);
        estudiante.setProfile(profile);
        estudiante.setSection(section);
        estudiante.setShift(shift);
        estudiante.setStudy_level(studyLevel);

        EstudianteDAOImpl estudianteDAO = new EstudianteDAOImpl();
        estudianteDAO.registrarEstudiante(estudiante);
        response.sendRedirect(request.getContextPath() + "/listarEstudiante");

    }
}
