package qa.eom.front.logic;

public enum UserCredentials {


    TEACHER_ASTAHOVA("biotest", "Freetree5");


    String login;
    String password;

    UserCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
