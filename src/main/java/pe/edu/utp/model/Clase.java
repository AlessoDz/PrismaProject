package pe.edu.utp.model;

public class Clase {

    private String idClase;
    private String day;
    private String startTime;
    private String endTime;
    private String idClassroom;
    private String idCourse;
    private String idTeacher;

    public Clase(String idClase, String day, String startTime, String endTime,
                 String idClassroom, String idCourse, String idTeacher) {
        this.idClase = idClase;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.idClassroom = idClassroom;
        this.idCourse = idCourse;
        this.idTeacher = idTeacher;
    }

    public Clase() {
    }

    public String getIdClase() {
        return idClase;
    }

    public void setIdClase(String idClase) {
        this.idClase = idClase;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getIdClassroom() {
        return idClassroom;
    }

    public void setIdClassroom(String idClassroom) {
        this.idClassroom = idClassroom;
    }

    public String getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(String idCourse) {
        this.idCourse = idCourse;
    }

    public String getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(String idTeacher) {
        this.idTeacher = idTeacher;
    }

    @Override
    public String toString() {
        return "Clase{" +
                "idClase='" + idClase + '\'' +
                ", day='" + day + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", idClassroom='" + idClassroom + '\'' +
                ", idCourse='" + idCourse + '\'' +
                ", idTeacher='" + idTeacher + '\'' +
                '}';
    }
}
