package model;

public class Nalog {
    private String username;
    private String password;
    private Integer psihoterapeut_id;

    public Nalog(String username, String password, Integer psihoterapeut_id) {
        this.username = username;
        this.password = password;
        this.psihoterapeut_id = psihoterapeut_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPsihoterapeut_id() {
        return psihoterapeut_id;
    }

    public void setPsihoterapeut_id(Integer psihoterapeut_id) {
        this.psihoterapeut_id = psihoterapeut_id;
    }
}
