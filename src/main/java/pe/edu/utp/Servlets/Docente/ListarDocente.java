package pe.edu.utp.Servlets.Docente;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.DocenteDAOImpl;
import pe.edu.utp.model.Docente;
import pe.edu.utp.repository.DocenteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/listarDocentes")
public class ListarDocente extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Listado de Docentes</title>");
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
        out.println("function mostrarModal(id, profile, password, speciality, name, last_name, birth_date, dni, email, phone, registration_date) {");
        out.println("    document.getElementById('modalEditar').style.display = 'block';");
        out.println("    document.getElementById('editIdDocente').value = id;");
        out.println("    document.getElementById('editProfile').value = profile;");
        out.println("    document.getElementById('editPassword').value = password;");
        out.println("    document.getElementById('editSpeciality').value = speciality;");
        out.println("    document.getElementById('editNombre').value = name;");
        out.println("    document.getElementById('editApellido').value = last_name;");
        out.println("    document.getElementById('editFechaNacimiento').value = birth_date;");
        out.println("    document.getElementById('editDni').value = dni;");
        out.println("    document.getElementById('editEmail').value = email;");
        out.println("    document.getElementById('editPhone').value = phone;");
        out.println("    document.getElementById('editRegistrationDate').value = registration_date;");
        out.println("}");
        out.println("function mostrarModalDetalles(id, profile, password, speciality, name, last_name, birth_date, dni, email, phone, registration_date) {");
        out.println("    document.getElementById('modalDetalles').style.display = 'block';");
        out.println("    document.getElementById('detailIdDocente').innerText = id;");
        out.println("    document.getElementById('detailProfile').innerText = profile;");
        out.println("    document.getElementById('detailPassword').innerText = password;");
        out.println("    document.getElementById('detailSpeciality').innerText = speciality;");
        out.println("    document.getElementById('detailNombre').innerText = name;");
        out.println("    document.getElementById('detailApellido').innerText = last_name;");
        out.println("    document.getElementById('detailFechaNacimiento').innerText = birth_date;");
        out.println("    document.getElementById('detailDni').innerText = dni;");
        out.println("    document.getElementById('detailEmail').innerText = email;");
        out.println("    document.getElementById('detailPhone').innerText = phone;");
        out.println("    document.getElementById('detailRegistrationDate').innerText = registration_date;");
        out.println("}");
        out.println("function cerrarModal() {");
        out.println("    document.getElementById('modalRegistrar').style.display = 'none';");
        out.println("    document.getElementById('modalEditar').style.display = 'none';");
        out.println("    document.getElementById('modalDetalles').style.display = 'none';");
        out.println("}");
        out.println("</script>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>Listado de Docentes</h1>");

        // Formulario de búsqueda
        out.println("<form action='/listarDocentes' method='get'>");
        out.println("<input type='text' name='query' placeholder='Buscar docentes...' class='form-input'>");
        out.println("<input type='submit' value='Buscar' class='btn'>");
        out.println("</form>");

        // Obtener lista de docentes desde la base de datos
        DocenteDAO docenteDAO = new DocenteDAOImpl();
        String query = request.getParameter("query");
        List<Docente> docentes;
        if (query != null && !query.trim().isEmpty()) {
            docentes = docenteDAO.buscarDocentes(query);
        } else {
            docentes = docenteDAO.listarDocentes();
        }

        if (docentes.isEmpty()) {
            out.println("<p>No hay docentes registrados actualmente</p>");
        } else {
            // Construir tabla HTML con los docentes
            out.println("<table border='1' id='tablaDocentes'>");
            out.println("<tr>");
            out.println("<th>ID del Docente</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Apellido</th>");
            out.println("<th>Email</th>");
            out.println("<th>Teléfono</th>");
            out.println("<th>Acciones</th>");
            out.println("</tr>");

            for (Docente docente : docentes) {
                out.println("<tr>");
                out.println("<td>" + docente.getIdDocente() + "</td>");
                out.println("<td>" + docente.getNombre() + "</td>");
                out.println("<td>" + docente.getApellido() + "</td>");
                out.println("<td>" + docente.getEmail() + "</td>");
                out.println("<td>" + docente.getTelefono() + "</td>");
                out.println("<td>");
                out.println("<button class='btn' onclick=\"mostrarModal('" + docente.getIdDocente() + "', '" + docente.getProfile() + "', '" + docente.getPassword() + "', '" + docente.getSpeciality() + "', '" + docente.getNombre() + "', '" + docente.getApellido() + "', '" + docente.getFechaNacimiento() + "', '" + docente.getDni() + "', '" + docente.getEmail() + "', '" + docente.getTelefono() + "', '" + docente.getFechaRegistro() + "')\">Editar</button>");
                out.println("<button class='btn' onclick=\"mostrarModalDetalles('" + docente.getIdDocente() + "', '" + docente.getProfile() + "', '" + docente.getPassword() + "', '" + docente.getSpeciality() + "', '" + docente.getNombre() + "', '" + docente.getApellido() + "', '" + docente.getFechaNacimiento() + "', '" + docente.getDni() + "', '" + docente.getEmail() + "', '" + docente.getTelefono() + "', '" + docente.getFechaNacimiento() + "')\">Ver detalles</button>");
                out.println("<a href='/eliminarDocente?id_teacher=" + docente.getIdDocente() + "' class='btn' onclick='return confirm(\"¿Estás seguro de eliminar este docente?\")'>Eliminar</a>");
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }

        out.println("<button class='btn' onclick=\"document.getElementById('modalRegistrar').style.display='block'\">Registrar nuevo docente</button>");

        // Modal de registrar docente
        out.println("<div id='modalRegistrar' class='modal'>");
        out.println("<div class='modal-content'>");
        out.println("<span class='close' onclick='cerrarModal()'>&times;</span>");
        out.println("<h2>Registrar Docente</h2>");
        out.println("<form action='/registrarDocente' method='post'>");
        out.println("<label class='form-label'>ID del Docente:</label><br>");
        out.println("<input type='text' name='id_teacher' class='form-input'><br>");
        out.println("<label class='form-label'>Contraseña:</label><br>");
        out.println("<input type='password' name='password' class='form-input'><br>");
        out.println("<label class='form-label'>Código:</label><br>");
        out.println("<input type='text' name='profile' class='form-input'><br>");
        out.println("<label for='speciality' class='form-label'>Especialidad</label>");
        out.println("<input type='text' id='speciality' name='speciality_name' class='form-input' required>");
        out.println("<label for='nombre' class='form-label'>Nombre</label>");
        out.println("<input type='text' id='nombre' name='name' class='form-input' required>");
        out.println("<label for='apellido' class='form-label'>Apellido</label>");
        out.println("<input type='text' id='apellido' name='last_name' class='form-input' required>");
        out.println("<label for='fechaNacimiento' class='form-label'>Fecha de Nacimiento</label>");
        out.println("<input type='date' id='fechaNacimiento' name='birth_date' class='form-input' required>");
        out.println("<label for='dni' class='form-label'>DNI</label>");
        out.println("<input type='text' id='dni' name='dni' class='form-input' required>");
        out.println("<label for='email' class='form-label'>Email</label>");
        out.println("<input type='email' id='email' name='email' class='form-input' required>");
        out.println("<label for='telefono' class='form-label'>Teléfono</label>");
        out.println("<input type='text' id='telefono' name='phone' class='form-input' required>");
        out.println("<input type='submit' value='Registrar' class='btn'>");
        out.println("</form>");
        out.println("</div>");
        out.println("</div>");

        // Ventana modal para editar docente
        out.println("<div id='modalEditar' class='modal'>");
        out.println("<div class='modal-content'>");
        out.println("<span class='close' onclick='cerrarModal()'>&times;</span>");
        out.println("<h2>Editar Docente</h2>");
        out.println("<form action='/actualizarDocente' method='post'>");
        out.println("<input type='hidden' id='editIdDocente' name='id_teacher'>");
        out.println("<label class='form-label'>Perfil:</label><br>");
        out.println("<input type='text' id='editProfile' name='profile' class='form-input'><br>");
        out.println("<label class='form-label'>Contraseña:</label><br>");
        out.println("<input type='password' id='editPassword' name='password' class='form-input'><br>");
        out.println("<label class='form-label'>Especialidad:</label><br>");
        out.println("<input type='text' id='editSpeciality' name='speciality_name' class='form-input'><br>");
        out.println("<label class='form-label'>Nombre:</label><br>");
        out.println("<input type='text' id='editNombre' name='name' class='form-input'><br>");
        out.println("<label class='form-label'>Apellido:</label><br>");
        out.println("<input type='text' id='editApellido' name='last_name' class='form-input'><br>");
        out.println("<label class='form-label'>Fecha de Nacimiento:</label><br>");
        out.println("<input type='date' id='editFechaNacimiento' name='birth_date' class='form-input'><br>");
        out.println("<label class='form-label'>DNI:</label><br>");
        out.println("<input type='text' id='editDni' name='dni' class='form-input'><br>");
        out.println("<label class='form-label'>Email:</label><br>");
        out.println("<input type='email' id='editEmail' name='email' class='form-input'><br>");
        out.println("<label class='form-label'>Teléfono:</label><br>");
        out.println("<input type='text' id='editPhone' name='phone' class='form-input'><br>");
        out.println("<input type='hidden' id='editRegistrationDate' name='registration_date'>");
        out.println("<input type='submit' value='Actualizar' class='btn'>");
        out.println("</form>");
        out.println("</div>");
        out.println("</div>");

        // Modal de detalles del docente
        out.println("<div id='modalDetalles' class='modal'>");
        out.println("<div class='modal-content'>");
        out.println("<span class='close' onclick='cerrarModal()'>&times;</span>");
        out.println("<h2>Detalles del Docente</h2>");
        out.println("<p><strong>ID del Docente:</strong> <span id='detailIdDocente'></span></p>");
        out.println("<p><strong>Perfil:</strong> <span id='detailProfile'></span></p>");
        out.println("<p><strong>Contraseña:</strong> <span id='detailPassword'></span></p>");
        out.println("<p><strong>Especialidad:</strong> <span id='detailSpeciality'></span></p>");
        out.println("<p><strong>Nombre:</strong> <span id='detailNombre'></span></p>");
        out.println("<p><strong>Apellido:</strong> <span id='detailApellido'></span></p>");
        out.println("<p><strong>Fecha de Nacimiento:</strong> <span id='detailFechaNacimiento'></span></p>");
        out.println("<p><strong>DNI:</strong> <span id='detailDni'></span></p>");
        out.println("<p><strong>Email:</strong> <span id='detailEmail'></span></p>");
        out.println("<p><strong>Teléfono:</strong> <span id='detailPhone'></span></p>");
        out.println("<p><strong>Fecha de Registro:</strong> <span id='detailRegistrationDate'></span></p>");
        out.println("</div>");
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }
}
