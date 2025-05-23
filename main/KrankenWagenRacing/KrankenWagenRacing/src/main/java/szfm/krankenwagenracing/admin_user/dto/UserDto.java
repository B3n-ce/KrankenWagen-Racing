package szfm.krankenwagenracing.admin_user.dto;

public class UserDto
{
    private String email;
    private String password;
    private String role;
    private String fullname;

    public UserDto(String email, String fullname, String password, String role) {
        super();
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}
