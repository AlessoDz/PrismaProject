package pe.edu.utp.Ejecucion;

import pe.edu.utp.Servlets.Aulas.EditarAula;
import pe.edu.utp.Servlets.Aulas.EliminarAula;
import pe.edu.utp.Servlets.Aulas.ListarAulaServlet;
import pe.edu.utp.Servlets.Aulas.registrarAula;
import pe.edu.utp.Servlets.Cursos.EditarCursoServlet;
import pe.edu.utp.Servlets.Cursos.EliminarCurso;
import pe.edu.utp.Servlets.Cursos.ListarCursosServlet;
import pe.edu.utp.Servlets.Cursos.RegistrarCursoServlet;
import pe.edu.utp.Servlets.Docente.ActualizarDocente;
import pe.edu.utp.Servlets.Docente.EliminarDocente;
import pe.edu.utp.Servlets.Docente.ListarDocente;
import pe.edu.utp.Servlets.Docente.RegistrarDocente;
import pe.edu.utp.Servlets.Login.LoginServlet;
import pe.edu.utp.Servlets.Vacantes.AceptarVacanteServlet;
import pe.edu.utp.Servlets.Vacantes.ListarVacantesServlet;
import pe.edu.utp.Servlets.Vacantes.SolicitarVacanteServlet;
import pe.edu.utp.utils.JettyUTP;
import java.net.URL;

public class AppPrisma {
    public static void main(String[] args) throws Exception {
        String path = "src\\main\\resources\\";

        JettyUTP webserver = new JettyUTP(8080, path);
        webserver.addServlet(SolicitarVacanteServlet.class,"/solicitarVacante");
        webserver.addServlet(LoginServlet.class,"/login");
        webserver.addServlet(AceptarVacanteServlet.class,"/aceptarVacante");
        webserver.addServlet(ListarVacantesServlet.class,"/listarVacantes");
        webserver.addServlet(ListarCursosServlet.class,"/listarCursos");
        webserver.addServlet(RegistrarCursoServlet.class,"/registrarCurso");
        webserver.addServlet(EditarCursoServlet.class,"/editarCurso");
        webserver.addServlet(EliminarCurso.class,"/eliminarCurso");
        webserver.addServlet(ListarDocente.class,"/listarDocentes");
        webserver.addServlet(RegistrarDocente.class,"/registrarDocente");
        webserver.addServlet(ActualizarDocente.class,"/actualizarDocente");
        webserver.addServlet(EliminarDocente.class,"/eliminarDocente");
        webserver.addServlet(ListarAulaServlet.class,"/ListarAula");
        webserver.addServlet(registrarAula.class,"/registrarAula");
        webserver.addServlet(EditarAula.class,"/editarAula");
        webserver.addServlet(EliminarAula.class,"/eliminarAula");


        URL myURL = new URL("http://localhost:8080");
        System.out.println("*********************************************************");
        System.out.println("CLICK AQUI PARA ABRIR LA APLICACION:" + myURL);
        System.out.println("*********************************************************");
        webserver.start();
    }

    public static String getErrorLogFile() {
        String archivo = new String("src/main/resources/errores.txt");
        return archivo;
    }
}

