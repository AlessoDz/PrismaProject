package pe.edu.utp.model;

public class Aula {
    private String idAula;
    private String codigo;

    public Aula(String idAula, String codigo) {
        this.idAula = idAula;
        this.codigo = codigo;
    }

    public Aula() {
    }

    public String getIdAula() {
        return idAula;
    }

    public void setIdAula(String idAula) {
        this.idAula = idAula;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Aula{" +
                "idAula='" + idAula + '\'' +
                ", codigo='" + codigo + '\'' +
                '}';
    }
}
