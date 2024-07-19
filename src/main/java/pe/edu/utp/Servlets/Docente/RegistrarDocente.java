package pe.edu.utp.Servlets.Docente;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pe.edu.utp.Implement.DocenteDAOImpl;
import pe.edu.utp.model.Docente;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registrarDocente")
public class RegistrarDocente extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        PrintWriter out = response.getWriter();

        String idDocente = request.getParameter("id_teacher");
        String password = request.getParameter("password");
        String profile = request.getParameter("profile");
        String nombre = request.getParameter("name");
        String apellido = request.getParameter("last_name");
        String fechaNacimiento = request.getParameter("birth_date");
        String dni = request.getParameter("dni");
        String email = request.getParameter("email");
        String telefono = request.getParameter("phone");
        String especialidad = request.getParameter("speciality_name");

        if (idDocente == null || password == null || profile == null || nombre == null ||
                apellido == null || fechaNacimiento == null || dni == null || email == null ||
                telefono == null || especialidad == null ||
                idDocente.isEmpty() || password.isEmpty() || profile.isEmpty() || nombre.isEmpty() ||
                apellido.isEmpty() || fechaNacimiento.isEmpty() || dni.isEmpty() || email.isEmpty() ||
                telefono.isEmpty() || especialidad.isEmpty()) {
            out.println("Todos los campos son obligatorios.");
            return;
        }

        if (profile.length() != 9 || !profile.startsWith("D")) {
            out.println("El perfil debe tener 9 dígitos y comenzar con 'D'.");
            return;
        }

        if (telefono.length() != 9 || !telefono.startsWith("9")) {
            out.println("El teléfono debe tener 9 dígitos y comenzar con '9'.");
            return;
        }

        if (!email.endsWith("@gmail.com")) {
            out.println("El email debe terminar con '@gmail.com'.");
            return;
        }

        if (dni.length() != 8) {
            out.println("El DNI debe tener exactamente 8 dígitos.");
            return;
        }

        Docente docente = new Docente();
        docente.setIdDocente(idDocente);
        docente.setPassword(password);
        docente.setProfile(profile);
        docente.setNombre(nombre);
        docente.setApellido(apellido);
        docente.setFechaNacimiento(fechaNacimiento);
        docente.setDni(dni);
        docente.setEmail(email);
        docente.setTelefono(telefono);
        docente.setSpeciality(especialidad);

        DocenteDAOImpl docenteDAO = new DocenteDAOImpl();
        docenteDAO.registrarDocente(docente);
        response.sendRedirect(request.getContextPath() + "/listarDocentes");
    }
}
