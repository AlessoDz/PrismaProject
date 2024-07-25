package pe.edu.utp.Servlets.Estudiante;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.EstudianteDAOImpl;
import pe.edu.utp.model.Estudiante;
import pe.edu.utp.repository.EstudianteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/listarEstudiante")
public class ListarEstudianteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Listado de Estudiantes</title>");
        out.println("<style>");
        out.println("body {");
        out.println("    font-family: Arial, sans-serif;");
        out.println("    margin: 40px;");
        out.println("    padding: 0;");
        out.println("}");
        out.println("table {");
        out.println("    width: 90%;");
        out.println("    border-collapse: collapse;");
        out.println("    margin: 20px auto;");
        out.println("}");
        out.println("th, td {");
        out.println("    padding: 8px;");
        out.println("    text-align: left;");
        out.println("    border-bottom: 1px solid #ddd;");
        out.println("}");
        out.println("th {");
        out.println("    background-color: #f2f2f2;");
        out.println("}");
        out.println(".modal {");
        out.println("    display: none;");
        out.println("    position: fixed;");
        out.println("    z-index: 1;");
        out.println("    left: 0;");
        out.println("    top: 0;");
        out.println("    width: 100%;");
        out.println("    height: 100%;");
        out.println("    background-color: rgba(0,0,0,0.6);");
        out.println("    overflow: auto;");
        out.println("}");
        out.println(".modal-content {");
        out.println("    background-color: #fefefe;");
        out.println("    margin: 15% auto;");
        out.println("    padding: 20px;");
        out.println("    border: 1px solid #888;");
        out.println("    width: 50%;");
        out.println("    border-radius: 10px;");
        out.println("    box-shadow: 0 4px 8px rgba(0,0,0,0.2);");
        out.println("}");
        out.println(".close {");
        out.println("    color: #aaa;");
        out.println("    float: right;");
        out.println("    font-size: 28px;");
        out.println("    font-weight: bold;");
        out.println("}");
        out.println(".close:hover, .close:focus {");
        out.println("    color: black;");
        out.println("    text-decoration: none;");
        out.println("    cursor: pointer;");
        out.println("}");
        out.println(".form-label {");
        out.println("    font-weight: bold;");
        out.println("}");
        out.println(".form-input {");
        out.println("    width: 100%;");
        out.println("    padding: 8px;");
        out.println("    margin: 5px 0;");
        out.println("    box-sizing: border-box;");
        out.println("}");
        out.println(".btn {");
        out.println("    background-color: #4CAF50;");
        out.println("    color: white;");
        out.println("    padding: 10px 20px;");
        out.println("    border: none;");
        out.println("    cursor: pointer;");
        out.println("    border-radius: 5px;");
        out.println("    margin-right: 5px;");
        out.println("}");
        out.println(".btn:hover {");
        out.println("    background-color: #45a049;");
        out.println("}");
        out.println("</style>");
        out.println("<script>");
        out.println("function mostrarModal(id, entrydate, grade, password, payment_status, profile, section, shift, study_level) {");
        out.println("    document.getElementById('modalEditar').style.display = 'block';");
        out.println("    document.getElementById('editIdEstudiante').value = id;");
        out.println("    document.getElementById('editEntrydate').value = entrydate;");
        out.println("    document.getElementById('editGrade').value = grade;");
        out.println("    document.getElementById('editPassword').value = password;");
        out.println("    document.getElementById('editPaymentStatus').value = payment_status;");
        out.println("    document.getElementById('editProfile').value = profile;");
        out.println("    document.getElementById('editSection').value = section;");
        out.println("    document.getElementById('editShift').value = shift;");
        out.println("    document.getElementById('editStudyLevel').value = study_level;");
        out.println("}");
        out.println("function mostrarModalDetalles(id, entrydate, grade, password, payment_status, profile, section, shift, study_level) {");
        out.println("    document.getElementById('modalDetalles').style.display = 'block';");
        out.println("    document.getElementById('detailIdEstudiante').innerText = id;");
        out.println("    document.getElementById('detailEntrydate').innerText = entrydate;");
        out.println("    document.getElementById('detailGrade').innerText = grade;");
        out.println("    document.getElementById('detailPassword').innerText = password;");
        out.println("    document.getElementById('detailPaymentStatus').innerText = payment_status;");
        out.println("    document.getElementById('detailProfile').innerText = profile;");
        out.println("    document.getElementById('detailSection').innerText = section;");
        out.println("    document.getElementById('detailShift').innerText = shift;");
        out.println("    document.getElementById('detailStudyLevel').innerText = study_level;");
        out.println("}");
        out.println("function cerrarModal() {");
        out.println("    document.getElementById('modalRegistrar').style.display = 'none';");
        out.println("    document.getElementById('modalEditar').style.display = 'none';");
        out.println("    document.getElementById('modalDetalles').style.display = 'none';");
        out.println("}");
        out.println("</script>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>Listado de Estudiantes</h1>");

        // Formulario de búsqueda
        out.println("<form action='/listarEstudiantes' method='get'>");
        out.println("<input type='text' name='query' placeholder='Buscar estudiantes...' class='form-input'>");
        out.println("<input type='submit' value='Buscar' class='btn'>");
        out.println("</form>");

        // Obtener lista de estudiantes desde la base de datos
        EstudianteDAO estudianteDAO = new EstudianteDAOImpl();
        String query = request.getParameter("query");
        List<Estudiante> estudiantes;
        if (query != null && !query.trim().isEmpty()) {
            estudiantes = estudianteDAO.buscarEstudiante(query);
        } else {
            estudiantes = estudianteDAO.listarEstudiante();
        }

        if (estudiantes.isEmpty()) {
            out.println("<p>No hay estudiantes registrados actualmente</p>");
        } else {
            // Construir tabla HTML con los estudiantes
            out.println("<table border='1' id='tablaEstudiantes'>");
            out.println("<tr>");
            out.println("<th>ID del Estudiante</th>");
            out.println("<th>Fecha de Ingreso</th>");
            out.println("<th>Grado</th>");
            out.println("<th>Sección</th>");
            out.println("<th>Turno</th>");
            out.println("<th>Acciones</th>");
            out.println("</tr>");

            for (Estudiante estudiante : estudiantes) {
                out.println("<tr>");
                out.println("<td>" + estudiante.getId_student() + "</td>");
                out.println("<td>" + estudiante.getEntrydate() + "</td>");
                out.println("<td>" + estudiante.getGrade() + "</td>");
                out.println("<td>" + estudiante.getSection() + "</td>");
                out.println("<td>" + estudiante.getShift() + "</td>");
                out.println("<td>");
                out.println("<button class='btn' onclick=\"mostrarModal('" + estudiante.getId_student() + "', '" + estudiante.getEntrydate() + "', '" + estudiante.getGrade() + "', '" + estudiante.getPassword() + "', '" + estudiante.getPayment_status() + "', '" + estudiante.getProfile() + "', '" + estudiante.getSection() + "', '" + estudiante.getShift() + "', '" + estudiante.getStudy_level() + "')\">Editar</button>");
                out.println("<button class='btn' onclick=\"mostrarModalDetalles('" + estudiante.getId_student() + "', '" + estudiante.getEntrydate() + "', '" + estudiante.getGrade() + "', '" + estudiante.getPassword() + "', '" + estudiante.getPayment_status() + "', '" + estudiante.getProfile() + "', '" + estudiante.getSection() + "', '" + estudiante.getShift() + "', '" + estudiante.getStudy_level() + "')\">Ver Detalles</button>");
                out.println("</td>");
                out.println("</tr>");
            }

            out.println("</table>");
        }

        out.println("<br>");
        out.println("<button class='btn' onclick=\"document.getElementById('modalRegistrar').style.display='block'\">Registrar Estudiante</button>");

        // Modal para registrar estudiante
        out.println("<div id='modalRegistrar' class='modal'>");
        out.println("<div class='modal-content'>");
        out.println("<span class='close' onclick='cerrarModal()'>&times;</span>");
        out.println("<h2>Registrar Estudiante</h2>");
        out.println("<form action='/registrarEstudiante' method='post'>");
        out.println("<label for='id_student'class='form-label'>ID del estudiante:</label>");
        out.println("<input type='text' id='id_student' name='id_student' class='form-input'><br>");
        out.println("<label for='entrydate' class='form-label'>Fecha de Ingreso:</label>");
        out.println("<input type='date' id='entrydate' name='entrydate' class='form-input'><br>");
        out.println("<label for='grade' class='form-label'>Grado:</label>");
        out.println("<input type='number' id='grade' name='grade' class='form-input'><br>");
        out.println("<label for='password' class='form-label'>Contraseña:</label>");
        out.println("<input type='password' id='password' name='password' class='form-input'><br>");
        out.println("<label for='payment_status' class='form-label'>Estado de Pago:</label>");
        out.println("<input type='checkbox' id='payment_status' name='payment_status' class='form-input'><br>");
        out.println("<label for='profile' class='form-label'>Perfil:</label>");
        out.println("<input type='text' id='profile' name='profile' class='form-input'><br>");
        out.println("<label for='section' class='form-label'>Sección:</label>");
        out.println("<input type='text' id='section' name='section' class='form-input'><br>");
        out.println("<label for='shift' class='form-label'>Turno:</label>");
        out.println("<input type='text' id='shift' name='shift' class='form-input'><br>");
        out.println("<label for='study_level' class='form-label'>Nivel de Estudio:</label>");
        out.println("<input type='text' id='study_level' name='study_level' class='form-input'><br>");
        out.println("<input type='submit' value='Registrar' class='btn'>");
        out.println("</form>");
        out.println("</div>");
        out.println("</div>");

        // Modal para editar estudiante
        out.println("<div id='modalEditar' class='modal'>");
        out.println("<div class='modal-content'>");
        out.println("<span class='close' onclick='cerrarModal()'>&times;</span>");
        out.println("<h2>Editar Estudiante</h2>");
        out.println("<form action='/editarEstudiante' method='post'>");
        out.println("<input type='hidden' id='editIdEstudiante' name='id_student' class='form-input'>");
        out.println("<label for='editEntrydate' class='form-label'>Fecha de Ingreso:</label>");
        out.println("<input type='date' id='editEntrydate' name='entrydate' class='form-input'><br>");
        out.println("<label for='editGrade' class='form-label'>Grado:</label>");
        out.println("<input type='text' id='editGrade' name='grade' class='form-input'><br>");
        out.println("<label for='editPassword' class='form-label'>Contraseña:</label>");
        out.println("<input type='password' id='editPassword' name='password' class='form-input'><br>");
        out.println("<label for='editPaymentStatus' class='form-label'>Estado de Pago:</label>");
        out.println("<input type='text' id='editPaymentStatus' name='payment_status' class='form-input'><br>");
        out.println("<label for='editProfile' class='form-label'>Perfil:</label>");
        out.println("<input type='text' id='editProfile' name='profile' class='form-input'><br>");
        out.println("<label for='editSection' class='form-label'>Sección:</label>");
        out.println("<input type='text' id='editSection' name='section' class='form-input'><br>");
        out.println("<label for='editShift' class='form-label'>Turno:</label>");
        out.println("<input type='text' id='editShift' name='shift' class='form-input'><br>");
        out.println("<label for='editStudyLevel' class='form-label'>Nivel de Estudio:</label>");
        out.println("<input type='text' id='editStudyLevel' name='study_level' class='form-input'><br>");
        out.println("<input type='submit' value='Guardar Cambios' class='btn'>");
        out.println("</form>");
        out.println("</div>");
        out.println("</div>");

        // Modal para ver detalles del estudiante
        out.println("<div id='modalDetalles' class='modal'>");
        out.println("<div class='modal-content'>");
        out.println("<span class='close' onclick='cerrarModal()'>&times;</span>");
        out.println("<h2>Detalles del Estudiante</h2>");
        out.println("<p><strong>ID del Estudiante:</strong> <span id='detailIdEstudiante'></span></p>");
        out.println("<p><strong>Fecha de Ingreso:</strong> <span id='detailEntrydate'></span></p>");
        out.println("<p><strong>Grado:</strong> <span id='detailGrade'></span></p>");
        out.println("<p><strong>Contraseña:</strong> <span id='detailPassword'></span></p>");
        out.println("<p><strong>Estado de Pago:</strong> <span id='detailPaymentStatus'></span></p>");
        out.println("<p><strong>Perfil:</strong> <span id='detailProfile'></span></p>");
        out.println("<p><strong>Sección:</strong> <span id='detailSection'></span></p>");
        out.println("<p><strong>Turno:</strong> <span id='detailShift'></span></p>");
        out.println("<p><strong>Nivel de Estudio:</strong> <span id='detailStudyLevel'></span></p>");
        out.println("</div>");
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }
}
