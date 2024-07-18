package pe.edu.utp.utils;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")  // Este filtro se aplica a todas las URL
public class SessionFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        // Puedes realizar inicializaciones aquí si es necesario
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Verificar si la solicitud es una instancia de HttpServletRequest
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;

            // Obtener la sesión actual o crear una si no existe
            HttpSession session = httpRequest.getSession(true);

            // Continuar con la cadena de filtros y servlets
            chain.doFilter(request, response);
        } else {
            // Si no es una solicitud HTTP, simplemente continuar con la cadena
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
        // Realizar limpieza de recursos si es necesario
    }
}
