package pe.edu.utp.Servlets.Administrador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.AdministradorDAOImp;
import pe.edu.utp.model.Administrador;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registrarAdministrador")
public class registrarAdministrador extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String idAdmin = request.getParameter("id_admin");
        String password = request.getParameter("password");
        String profile = request.getParameter("profile");
        String name = request.getParameter("name");
        String lastName = request.getParameter("last_name");
        String birthDate = request.getParameter("birth_date");
        String dni = request.getParameter("dni");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        if (password == null || profile == null || name == null || lastName == null ||
                birthDate == null || dni == null || email == null || phone == null ||
                password.isEmpty() || profile.isEmpty() || name.isEmpty() || lastName.isEmpty() ||
                birthDate.isEmpty() || dni.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            out.println("Todos los campos son obligatorios.");
            return;
        }

        if (profile.length() != 9 || !profile.startsWith("A")) {
            out.println("El perfil debe tener 9 dígitos y comenzar con 'A'.");
            return;
        }

        if (phone.length() != 9 || !phone.startsWith("9")) {
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

        Administrador administrador = new Administrador();
        administrador.setIdAdmin(idAdmin);
        administrador.setPassword(password);
        administrador.setProfile(profile);
        administrador.setName(name);
        administrador.setLastName(lastName);
        administrador.setBirthDate(birthDate);
        administrador.setDni(dni);
        administrador.setEmail(email);
        administrador.setPhone(phone);

        AdministradorDAOImp administradorDAO = new AdministradorDAOImp();
        administradorDAO.registrarAdministrador(administrador);
        response.sendRedirect(request.getContextPath() + "/listarAdministrador");
    }
}
