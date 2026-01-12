package DataAccessComponent.DTOs;

public class AdministratorDTO {

    private Integer IdAdministrator;
    private Integer IdUserType;
    private String UserName;
    private String Password;
    private String CreationDate;
    private String LastLogin;

    public AdministratorDTO() {
    }

    public AdministratorDTO(Integer idAdministrator, Integer idUserType, String userName, String password,
            String creationDate, String lastLogin) {
        setIdAdministrator(idAdministrator);
        setIdUserType(idUserType);
        setUserName(userName);
        setPassword(password);
        setCreationDate(creationDate);
        setLastLogin(lastLogin);
    }

    public AdministratorDTO(Integer idAdministrator, String userName, String lastLogin) {
        IdAdministrator = idAdministrator;
        UserName = userName;
        LastLogin = lastLogin;
    }

    public Integer getIdAdministrator() {
        return IdAdministrator;
    }

    public void setIdAdministrator(Integer IdAdministrator) {
        this.IdAdministrator = IdAdministrator;
    }

    public Integer getIdUserType() {
        return IdUserType;
    }

    public void setIdUserType(Integer IdUserType) {
        this.IdUserType = IdUserType;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(String CreationDate) {
        this.CreationDate = CreationDate;
    }

    public String getLastLogin() {
        return LastLogin;
    }

    public void setLastLogin(String LastLogin) {
        this.LastLogin = LastLogin;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "\n IdAdministrator: " + getIdAdministrator()
                + "\n IdUserType: " + getIdUserType()
                + "\n UserName: " + getUserName()
                + "\n Password: " + getPassword()
                + "\n CreationDate: " + getCreationDate()
                + "\n LastLogin: " + getLastLogin();
    }
}
