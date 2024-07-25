package pe.edu.utp.Servlets.Clases;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.ClaseDAOImpl;
import pe.edu.utp.model.Aula;
import pe.edu.utp.model.Clase;
import pe.edu.utp.model.Curso;
import pe.edu.utp.model.Docente;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@WebServlet("/listarClases")
public class ListarClases extends HttpServlet {

    private ClaseDAOImpl claseDAO = new ClaseDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<Clase> clases = claseDAO.obtenerClases();
        List<Aula> aulas = claseDAO.obtenerAulas();
        List<Curso> cursos = claseDAO.obtenerCursos();
        List<Docente> docentes = claseDAO.obtenerDocentes();

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dayFormatter = new SimpleDateFormat("EEEE", new Locale("es", "ES"));

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='es'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Listado de Clases</title>");
        out.println("""
<style>
body {
    font-family: Arial, sans-serif;
    margin: 30px;
    padding: 0;
    background-color: #f4f4f4;
}

.container {
    width: 90%;
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
    background-color: #fff;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
}

h1 {
    font-size: 28px;
    color: #333;
    margin-bottom: 20px;
}

button {
    background-color: #007BFF;
    color: white;
    padding: 10px 20px;
    border: none;
    cursor: pointer;
    border-radius: 5px;
    font-size: 16px;
    margin: 10px 0;
}

button:hover {
    background-color: #0056b3;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    border-radius: 8px;
    overflow: hidden;
}

th, td {
    padding: 12px 15px;
    text-align: left;
    border-bottom: 1px solid #ddd;
}

th {
    background-color: #4CAF50;
    color: white;
    font-size: 16px;
}

tr:nth-child(even) {
    background-color: #f9f9f9;
}

tr:hover {
    background-color: #f1f1f1;
}

.modal {
    display: none;
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.6);
    overflow: auto;
}

.modal-content {
    background-color: #fff;
    margin: 5% auto;
    padding: 20px;
    border: 1px solid #888;
    width: 50%;
    max-width: 800px;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    position: relative;
}

.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}

.modal-content h2 {
    margin-top: 0;
    font-size: 24px;
    color: #333;
}

.modal-content label {
    display: block;
    margin: 10px 0 5px;
    font-weight: bold;
    color: #555;
}

.modal-content input,
.modal-content select {
    width: 100%;
    padding: 10px;
    margin: 5px 0 15px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
}

.modal-content input:focus,
.modal-content select:focus {
    border-color: #007BFF;
    outline: none;
}

.modal-content button {
    background-color: #4CAF50;
    color: white;
    padding: 10px 20px;
    border: none;
    cursor: pointer;
    border-radius: 5px;
    font-size: 16px;
}

.btn {
background-color: #4CAF50;
color: white;
padding: 10px 20px;
border: none;
cursor: pointer;
border-radius: 5px;
margin-right: 5px;
}

.btn:hover {
        background-color: #45a049
}
        
.modal-content button:hover {
    background-color: #45a049;
}

.error-message {
    color: #f00;
    font-size: 14px;
}

.success-message {
    color: #4CAF50;
    font-size: 14px;
}

@media screen and (max-width: 768px) {
    .modal-content {
        width: 90%;
    }

    table {
        font-size: 14px;
    }

    button {
        font-size: 14px;
        padding: 8px 16px;
    }
}
</style>
""");
        out.println("</head>");
        out.println("<body>");
        out.println("    <h1>Listado de Clases</h1>");
        out.println("    <button id='openModalBtn'>Registrar Clase</button>");

        // Tabla de clases
        out.println("    <table>");
        out.println("        <thead>");
        out.println("            <tr>");
        out.println("                <th>Día</th>");
        out.println("                <th>Hora Inicio</th>");
        out.println("                <th>Hora Fin</th>");
        out.println("                <th>Aula</th>");
        out.println("                <th>Curso</th>");
        out.println("                <th>Docente</th>");
        out.println("                <th>Acciones</th>");
        out.println("            </tr>");
        out.println("        </thead>");
        out.println("        <tbody>");
        for (Clase clase : clases) {
            String startTimeFormatted = new SimpleDateFormat("h:mm a").format(clase.getStartTime());
            String endTimeFormatted = new SimpleDateFormat("h:mm a").format(clase.getEndTime());
            String dayFormatted = "";

            try {
                Date date = dateFormatter.parse(clase.getDay());
                dayFormatted = dayFormatter.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            out.println("            <tr>");
            out.println("                <td>" + dayFormatted + "</td>");
            out.println("                <td>" + startTimeFormatted + "</td>");
            out.println("                <td>" + endTimeFormatted + "</td>");
            out.println("                <td>" + getAulaById(aulas, clase.getIdClassroom()) + "</td>");
            out.println("                <td>" + getCursoById(cursos, clase.getIdCourse()) + "</td>");
            out.println("                <td>" + getDocenteById(docentes, clase.getIdTeacher()) + "</td>");
            out.println("<td>");
            out.println("<button class='btn' onclick=\"mostrarModal('" + clase.getIdClase() + "', '" + clase.getDay() + "')\">Editar</button>");
            out.println("<a href='/eliminarClase?id=" + clase.getIdClase() + "' class='btn' onclick='return confirm(\"¿Estás seguro de eliminar esta clase?\")'>Eliminar</a>");
            out.println("</td>");
            out.println("            </tr>");
        }
        out.println("        </tbody>");
        out.println("    </table>");

        // Modal para registrar clase
        out.println("    <div id='myModal' class='modal'>");
        out.println("        <div class='modal-content'>");
        out.println("            <span class='close'>&times;</span>");
        out.println("            <h2>Registrar Clase</h2>");
        out.println("            <form id='registroClaseForm' action='/registrarClase' method='post'>");
        out.println("                <label for='day'>Fecha:</label>");
        out.println("                <input type='date' id='day' name='day' required><br><br>");

        out.println("                <label for='start_time'>Hora Inicio (AM/PM):</label>");
        out.println("                <input type='text' id='start_time' name='start_time' required placeholder='Ej: 9:00 AM'><br><br>");

        out.println("                <label for='end_time'>Hora Fin (AM/PM):</label>");
        out.println("                <input type='text' id='end_time' name='end_time' required placeholder='Ej: 10:30 AM'><br><br>");

        out.println("                <label for='id_classroom'>Aula:</label>");
        out.println("                <select id='id_classroom' name='id_classroom' required>");
        for (Aula aula : aulas) {
            out.println("                    <option value='" + aula.getIdAula() + "'>" + aula.getCodigo() + "</option>");
        }
        out.println("                </select><br><br>");

        out.println("                <label for='id_course'>Curso:</label>");
        out.println("                <select id='id_course' name='id_course' required>");
        for (Curso curso : cursos) {
            out.println("                    <option value='" + curso.getIdCurso() + "'>" + curso.getNombre() + "</option>");
        }
        out.println("                </select><br><br>");

        out.println("                <label for='id_teacher'>Docente:</label>");
        out.println("                <select id='id_teacher' name='id_teacher' required>");
        for (Docente docente : docentes) {
            out.println("                    <option value='" + docente.getIdDocente() + "'>" + docente.getProfile() + "</option>");
        }
        out.println("                </select><br><br>");

        out.println("                <button type='submit'>Registrar Clase</button>");
        out.println("            </form>");
        out.println("        </div>");
        out.println("    </div>");

        out.println("    <script>");
        out.println("        var modal = document.getElementById('myModal');");
        out.println("        var btn = document.getElementById('openModalBtn');");
        out.println("        var span = document.getElementsByClassName('close')[0];");

        out.println("        btn.onclick = function() {");
        out.println("            modal.style.display = 'block';");
        out.println("        }");

        out.println("        span.onclick = function() {");
        out.println("            modal.style.display = 'none';");
        out.println("        }");

        out.println("        window.onclick = function(event) {");
        out.println("            if (event.target == modal) {");
        out.println("                modal.style.display = 'none';");
        out.println("            }");
        out.println("        }");

        out.println("    </script>");
        out.println("</body>");
        out.println("</html>");
    }

    private String getAulaById(List<Aula> aulas, String id) {
        for (Aula aula : aulas) {
            if (aula.getIdAula().equals(id)) {
                return aula.getCodigo();
            }
        }
        return "";
    }

    private String getCursoById(List<Curso> cursos, String id) {
        for (Curso curso : cursos) {
            if (curso.getIdCurso().equals(id)) {
                return curso.getNombre();
            }
        }
        return "";
    }

    private String getDocenteById(List<Docente> docentes, String id) {
        for (Docente docente : docentes) {
            if (docente.getIdDocente().equals(id)) {
                return docente.getProfile();
            }
        }
        return "";
    }
}
