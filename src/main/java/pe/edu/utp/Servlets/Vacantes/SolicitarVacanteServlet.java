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

@WebServlet("/solicitarVacante")
public class SolicitarVacanteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String lastName = request.getParameter("last_name");
        String birthDate = request.getParameter("birth_date");
        String dni = request.getParameter("dni");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        if (name == null || lastName == null || birthDate == null || dni == null || email == null || phone == null ||
                name.isEmpty() || lastName.isEmpty() || birthDate.isEmpty() || dni.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            out.println("Todos los campos son obligatorios.");
            return;
        }

        if (dni.length() != 8) {
            out.println("El DNI debe tener exactamente 8 dígitos.");
            return;
        }

        if (phone.length() != 9) {
            out.println("El teléfono debe tener exactamente 9 dígitos.");
            return;
        }

        if (!email.toLowerCase().endsWith("@gmail.com")) {
            out.println("El correo electrónico debe ser de Gmail (terminar en @gmail.com).");
            return;
        }

        Usuario user = new Usuario();
        user.setNombre(name);
        user.setApellido(lastName);
        user.setFechaNacimiento(birthDate);
        user.setDni(dni);
        user.setEmail(email);
        user.setTelefono(phone);
        user.setTipo("POSTULANTE");
        user.setActivo(true);

        UsuarioDAO userDAO = new UsuarioDAOImpl();
        boolean success = userDAO.insertarUsuario(user);

        if (success) {
            out.println("Solicitud de vacante enviada correctamente.");
        } else {
            out.println("Error al enviar la solicitud de vacante.");
        }
    }
}
