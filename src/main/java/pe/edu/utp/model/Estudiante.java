package pe.edu.utp.model;

import java.time.LocalDate;

public class Estudiante {
    private String id_student;
    private LocalDate entrydate;
    private int grade;
    private String password;
    private Boolean payment_status;
    private String profile;
    private String section;
    private String shift;
    private String study_level;


    public Estudiante() {
    }


    public Estudiante(String id_student, LocalDate entrydate, int grade, String password, Boolean payment_status, String profile, String section, String shift, String study_level) {
        this.id_student = id_student;
        this.entrydate = entrydate;
        this.grade = grade;
        this.password = password;
        this.payment_status = payment_status;
        this.profile = profile;
        this.section = section;
        this.shift = shift;
        this.study_level = study_level;
    }

    public String getId_student() {
        return id_student;
    }

    public void setId_student(String id_student) {
        this.id_student = id_student;
    }

    public LocalDate getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(LocalDate entrydate) {
        this.entrydate = entrydate;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(Boolean payment_status) {
        this.payment_status = payment_status;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getStudy_level() {
        return study_level;
    }

    public void setStudy_level(String study_level) {
        this.study_level = study_level;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id_student='" + id_student + '\'' +
                ", entrydate=" + entrydate +
                ", grade=" + grade +
                ", password='" + password + '\'' +
                ", payment_status=" + payment_status +
                ", profile='" + profile + '\'' +
                ", section='" + section + '\'' +
                ", shift='" + shift + '\'' +
                ", study_level='" + study_level + '\'' +
                '}';
    }


}
