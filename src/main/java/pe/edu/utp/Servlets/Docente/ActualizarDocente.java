package pe.edu.utp.Servlets.Docente;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.Implement.DocenteDAOImpl;
import pe.edu.utp.model.Docente;
import pe.edu.utp.repository.DocenteDAO;

import java.io.IOException;

@WebServlet("/actualizarDocente")
public class ActualizarDocente extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idDocente = request.getParameter("id_teacher");
        String password = request.getParameter("password");
        String profile = request.getParameter("profile");
        String nombre = request.getParameter("name");
        String apellido = request.getParameter("last_name");
        String fechaNacimiento = request.getParameter("birth_date");
        String dni = request.getParameter("dni");
        String email = request.getParameter("email");
        String telefono = request.getParameter("phone");
        String especialidad = request.getParameter("speciality");

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

        DocenteDAO docenteDAO = new DocenteDAOImpl();
        boolean actualizado = docenteDAO.actualizarDocente(docente);

        if (actualizado) {
            response.sendRedirect(request.getContextPath() + "/listarDocentes");
        } else {
            response.getWriter().println("<h2>Error al actualizar el docente</h2>");
        }
    }
}