package qa.eom.front.logic;

public enum UserCredentials {


    TEACHER_ASTAHOVA("biotest", "cb2282cec266ef2eb69e88239adb2dfc", "8b9f23ee94c0d34ff0c401dbeaca4d52");


    String login;
    String hashPasswordOne;
    String hashPasswordTwo;

    UserCredentials(String login, String hashPasswordOne, String hashPasswordTwo) {
        this.login = login;
        this.hashPasswordOne = hashPasswordOne;
        this.hashPasswordTwo = hashPasswordTwo;
    }

    public String getLogin() {
        return login;
    }
    public String getHashPasswordOne() {
        return hashPasswordOne;
    }
    public String getHashPasswordTwo() { return hashPasswordTwo; }
}
