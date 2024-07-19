package pe.edu.utp.repository;

import pe.edu.utp.model.Aula;
import java.util.List;

public interface AulaDAO {
    void agregarAula(Aula aula);

    List<Aula> listarAulas();

    List<Aula> buscarAulas(String query);

    boolean actualizarAula(Aula aula);

    boolean eliminarAula(String idAula);
}
