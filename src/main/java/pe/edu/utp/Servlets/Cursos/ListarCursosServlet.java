package pe.edu.utp.Servlets.Cursos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.CursoDAOImp;
import pe.edu.utp.model.Curso;
import pe.edu.utp.repository.CursoDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/listarCursos")
public class ListarCursosServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Listado de Cursos</title>");
        out.println("<style>");
        out.println("body {");
        out.println("    font-family: Arial, sans-serif;");
        out.println("    margin: 40px;");
        out.println("    padding: 0;");
        out.println("}");
        out.println("table {");
        out.println("    width: 70%;");
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
        out.println("function mostrarModal(id, nombre) {");
        out.println("    document.getElementById('modalEditar').style.display = 'block';");
        out.println("    document.getElementById('editIdCurso').value = id;");
        out.println("    document.getElementById('editNombreCurso').value = nombre;");
        out.println("}");
        out.println("function cerrarModal() {");
        out.println("    document.getElementById('modalRegistrar').style.display = 'none';");
        out.println("    document.getElementById('modalEditar').style.display = 'none';");
        out.println("}");
        out.println("</script>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>Listado de Cursos</h1>");

        // Formulario de búsqueda
        out.println("<form action='/listarCursos' method='get'>");
        out.println("<input type='text' name='query' placeholder='Buscar cursos...' class='form-input'>");
        out.println("<input type='submit' value='Buscar' class='btn'>");
        out.println("</form>");

        // Obtener lista de cursos desde la base de datos
        CursoDAO cursoDAO = new CursoDAOImp();
        String query = request.getParameter("query");
        List<Curso> cursos;
        if (query != null && !query.trim().isEmpty()) {
            cursos = cursoDAO.buscarCursos(query);
        } else {
            cursos = cursoDAO.listarCursos();
        }

        if (cursos.isEmpty()) {
            out.println("<p>No hay cursos disponibles actualmente.</p>");
        }else {
            // Construir tabla HTML con los cursos
            out.println("<table border='1' id='tablaCursos'>");
            out.println("<tr><th>ID del Curso</th><th>Nombre del Curso</th><th>Acciones</th></tr>");
            for (Curso curso : cursos) {
                out.println("<tr>");
                out.println("<td>" + curso.getIdCurso() + "</td>");
                out.println("<td>" + curso.getNombre() + "</td>");
                out.println("<td>");
                out.println("<button class='btn' onclick=\"mostrarModal('" + curso.getIdCurso() + "', '" + curso.getNombre() + "')\">Editar</button>");
                out.println("<a href='/eliminarCurso?id=" + curso.getIdCurso() + "' class='btn' onclick='return confirm(\"¿Estás seguro de eliminar este curso?\")'>Eliminar</a>");
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }

        // Botón para registrar nuevo curso
        out.println("<button class='btn' onclick='document.getElementById(\"modalRegistrar\").style.display=\"block\"'>Registrar nuevo curso</button>");

        // Ventana modal para registrar nuevo curso
        out.println("<div id='modalRegistrar' class='modal'>");
        out.println("<div class='modal-content'>");
        out.println("<span class='close' onclick='cerrarModal()'>&times;</span>");
        out.println("<h2 style='text-align: center;'>Registrar nuevo curso</h2>");
        out.println("<form action='/registrarCurso' method='post'>");
        out.println("<label class='form-label' for='nombreCurso'>Nombre del curso:</label><br>");
        out.println("<input type='text' id='nombreCurso' name='name' class='form-input'><br><br>");
        out.println("<input type='submit' value='Registrar' class='btn'>");
        out.println("</form>");
        out.println("</div>");
        out.println("</div>");

        // Ventana modal para editar curso
        out.println("<div id='modalEditar' class='modal'>");
        out.println("<div class='modal-content'>");
        out.println("<span class='close' onclick='cerrarModal()'>&times;</span>");
        out.println("<h2 style='text-align: center;'>Editar curso</h2>");
        out.println("<form action='/editarCurso' method='post'>");
        out.println("<input type='hidden' id='editIdCurso' name='id'>");
        out.println("<label class='form-label' for='editNombreCurso'>Nombre del curso:</label><br>");
        out.println("<input type='text' id='editNombreCurso' name='name' class='form-input'><br><br>");
        out.println("<input type='submit' value='Actualizar' class='btn'>");
        out.println("</form>");
        out.println("</div>");
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }
}
