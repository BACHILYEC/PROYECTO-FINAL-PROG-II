package DataAccessComponent.DTOs;

public class UserAdminDTO {

    private Integer idAdministrator;
    private Integer idUserType;
    private String userName;
    private String password;
    private String creationDate;
    private String lastLogin;

    public UserAdminDTO(String password) {
        this.password = password;
    }

    public UserAdminDTO() {
    }

    public UserAdminDTO(Integer idAdministrator, Integer idUserType, String userName, String password,
            String creationDate, String lastLogin) {
        setIdAdministrator(idAdministrator);
        setIdUserType(idUserType);
        setUserName(userName);
        setPassword(password);
        setCreationDate(creationDate);
        setLastLogin(lastLogin);
    }

    public UserAdminDTO(Integer idAdministrator, String userName, String lastLogin) {
        this.idAdministrator = idAdministrator;
        this.userName = userName;
        this.lastLogin = lastLogin;
    }

    public UserAdminDTO(Integer idAdministrator, String userName, String password, String lastLogin) {
        this.idAdministrator = idAdministrator;
        this.userName = userName;
        this.password = password;
        this.lastLogin = lastLogin;
    }

    public Integer getIdAdministrator() {
        return idAdministrator;
    }

    public void setIdAdministrator(Integer idAdministrator) {
        this.idAdministrator = idAdministrator;
    }

    public Integer getIdUserType() {
        return idUserType;
    }

    public void setIdUserType(Integer idUserType) {
        this.idUserType = idUserType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "\n idAdministrator: " + getIdAdministrator()
                + "\n idUserType: " + getIdUserType()
                + "\n userName: " + getUserName()
                + "\n password: " + getPassword()
                + "\n creationDate: " + getCreationDate()
                + "\n lastLogin: " + getLastLogin();
    }
}
