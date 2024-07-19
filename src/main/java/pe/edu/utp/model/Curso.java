package pe.edu.utp.model;

public class Curso {
    private String idCurso;
    private String nombre;

    public Curso(String idCurso, String nombre) {
        this.idCurso = idCurso;
        this.nombre = nombre;
    }

    public Curso() {
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "idCurso=" + idCurso +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
