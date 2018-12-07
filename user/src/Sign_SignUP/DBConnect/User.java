package Sign_SignUP.DBConnect;

public class User {
    private String firstName;
    private String secondName;
    private String UserName;
    private String password;
    private String gender;

    public User(String firstName, String lastName, String userName, String password, String gender) {
        this.firstName = firstName;
        this.secondName = lastName;
        UserName = userName;
        this.password = password;
        this.gender = gender;
    }

    public User() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
