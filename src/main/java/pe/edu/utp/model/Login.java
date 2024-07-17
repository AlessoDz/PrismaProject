package pe.edu.utp.model;

public class Login {
    private String profile;
    private String password;

    public Login(String profile, String password) {
        this.profile = profile;
        this.password = password;
    }

    public Login() {
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login{" +
                "profile='" + profile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
