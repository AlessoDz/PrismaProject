package pe.edu.utp.Servlets.Vacantes;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.UsuarioDAOImpl;
import pe.edu.utp.model.Usuario;
import pe.edu.utp.repository.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/listarVacantes")
public class ListarVacantesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Listado de Vacantes</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f5f5f5; margin: 20px; }");
        out.println(".container { max-width: 80%; margin: auto; background-color: #fff; padding: 20px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
        out.println("h1 { color: #333; text-align: center; }");
        out.println("table { width: 100%; border-collapse: collapse; background-color: #fff; margin-bottom: 20px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
        out.println("table, th, td { border: 1px solid #ddd; }");
        out.println("th, td { padding: 12px; text-align: left; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println("tr:nth-child(even) { background-color: #f9f9f9; }");
        out.println("tr:hover { background-color: #f2f2f2; }");
        out.println("a { text-decoration: none; color: #007bff; }");
        out.println("a:hover { text-decoration: underline; }");
        out.println(".btn { display: inline-block; padding: 8px 16px; background-color: #007bff; color: #fff; text-align: center; border: none; border-radius: 4px; cursor: pointer; }");
        out.println(".btn:hover { background-color: #0056b3; }");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<div class='container'>");
        out.println("<h1>Listado de Vacantes</h1>");

        // Obtener la lista de usuarios que han solicitado vacante
        UsuarioDAO userDAO = new UsuarioDAOImpl();
        List<Usuario> listaUsuarios = userDAO.obtenerUsuariosPorTipo("POSTULANTE");

        if (listaUsuarios.isEmpty()) {
            out.println("<p>No hay vacantes solicitadas actualmente.</p>");
        } else {
            out.println("<table>");
            out.println("<tr><th>Nombre</th><th>Apellido</th><th>Fecha de Nacimiento</th>" +
                    "<th>DNI</th><th>Email</th><th>Teléfono</th><th>Tipo</th>" +
                    "<th>Acción</th></tr>");

            for (Usuario usuario : listaUsuarios) {
                out.println("<tr>");
                out.println("<td>" + usuario.getNombre() + "</td>");
                out.println("<td>" + usuario.getApellido() + "</td>");
                out.println("<td>" + usuario.getFechaNacimiento() + "</td>");
                out.println("<td>" + usuario.getDni() + "</td>");
                out.println("<td>" + usuario.getEmail() + "</td>");
                out.println("<td>" + usuario.getTelefono() + "</td>");
                out.println("<td>" + usuario.getTipo() + "</td>");
                out.println("<td><a href='/aceptarVacante?id_user=" + usuario.getId() + "' class='btn'>Aceptar Matricula</a></td>");
                out.println("</tr>");
            }

            out.println("</table>");
        }

        out.println("</div>");
        out.println("</body></html>");
    }
}
