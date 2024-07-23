package pe.edu.utp.Implement;

public class EstudianteDAOImpl {
    private static final String INSERTAR_ESTUDIANTE_SQL = "{CALL registrarEstudiante(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    private static final String LISTAR_ESTUDIANTES_SQL = "{CALL listarEstudiantes()}";
    private static final String ACTUALIZAR_ESTUDIANTE_SQL = "{CALL actualizarEstudiante(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    private static final String ELIMINAR_ESTUDIANTE_SQL = "{CALL eliminarEstudiante(?)}";
    private static final String BUSCAR_ESTUDIANTES_SQL = "{CALL buscarEstudiantes(?)}";

}
