package pe.edu.utp.Servlets.Aulas;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.AulaDAOImpl;
import pe.edu.utp.model.Aula;
import pe.edu.utp.repository.AulaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/ListarAula")
public class ListarAulaServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
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

        out.println("<h1>Listado de Aulas</h1>");

        // Formulario de búsqueda
        out.println("<form action='/ListarAula' method='get'>");
        out.println("<input type='text' name='query' placeholder='Buscar aulas...' class='form-input'>");
        out.println("<input type='submit' value='Buscar' class='btn'>");
        out.println("</form>");

        // Obtener lista de cursos desde la base de datos
        AulaDAO aulaDAO = new AulaDAOImpl();
        String query = request.getParameter("query");
        List<Aula> aulas;
        if (query != null && !query.trim().isEmpty()) {
            aulas = aulaDAO.buscarAulas(query);
        } else {
            aulas = aulaDAO.listarAulas();
        }

        if (aulas.isEmpty()) {
            out.println("<p>No hay cursos disponibles actualmente.</p>");
        }else {
            // Construir tabla HTML con los cursos
            out.println("<table border='1' id='tablaCursos'>");
            out.println("<tr><th>ID del Aula</th><th>Nombre del Aula</th><th>Acciones</th></tr>");
            for (Aula aula : aulas) {
                out.println("<tr>");
                out.println("<td>" + aula.getIdAula() + "</td>");
                out.println("<td>" + aula.getCodigo() + "</td>");
                out.println("<td>");
                out.println("<button class='btn' onclick=\"mostrarModal('" + aula.getIdAula() + "', '" + aula.getCodigo() + "')\">Editar</button>");
                out.println("<a href='/eliminarAula?id=" + aula.getIdAula() + "' class='btn' onclick='return confirm(\"¿Estás seguro de eliminar este curso?\")'>Eliminar</a>");
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }

        // Botón para registrar nuevo curso
        out.println("<button class='btn' onclick='document.getElementById(\"modalRegistrar\").style.display=\"block\"'>Registrar nuevo aula</button>");

        // Ventana modal para registrar nuevo curso
        out.println("<div id='modalRegistrar' class='modal'>");
        out.println("<div class='modal-content'>");
        out.println("<span class='close' onclick='cerrarModal()'>&times;</span>");
        out.println("<h2 style='text-align: center;'>Registrar nuevo aula</h2>");
        out.println("<form action='/registrarAula' method='post'>");
        out.println("<label class='form-label' for='nombreCurso'>Codigo del aula:</label><br>");
        out.println("<input type='text' id='nombreCurso' name='code' class='form-input'><br><br>");
        out.println("<input type='submit' value='Registrar' class='btn'>");
        out.println("</form>");
        out.println("</div>");
        out.println("</div>");

        // Ventana modal para editar curso
        out.println("<div id='modalEditar' class='modal'>");
        out.println("<div class='modal-content'>");
        out.println("<span class='close' onclick='cerrarModal()'>&times;</span>");
        out.println("<h2 style='text-align: center;'>Editar aula</h2>");
        out.println("<form action='/editarAula' method='post'>");
        out.println("<input type='hidden' id='editIdCurso' name='id'>");
        out.println("<label class='form-label' for='editNombreCurso'>Codigo del aula:</label><br>");
        out.println("<input type='text' id='editNombreCurso' name='code' class='form-input'><br><br>");
        out.println("<input type='submit' value='Actualizar' class='btn'>");
        out.println("</form>");
        out.println("</div>");
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }
}
