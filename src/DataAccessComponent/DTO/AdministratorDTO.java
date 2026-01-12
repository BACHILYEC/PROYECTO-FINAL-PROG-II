package DataAccessComponent.DTO;

public class AdministratorDTO {
    private Integer IdAdministrator;
    private Integer IdUserType;
    private String UserName;
    private String Password;
    private String CreationDate;
    private String ModificateDate;

    public AdministratorDTO() {}

    public AdministratorDTO(Integer idAdministrator, Integer idUserType, String userName, String password,
            String creationDate, String modificateDate) {
        IdAdministrator = idAdministrator;
        IdUserType = idUserType;
        UserName = userName;
        Password = password;
        CreationDate = creationDate;
        ModificateDate = modificateDate;
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

    public String getModificateDate() {
        return ModificateDate;
    }

    public void setModificateDate(String ModificateDate) {
        this.ModificateDate = ModificateDate;
    }

    @Override
    public String toString() {
        return getClass().getName()
        + "\n IdAdministrator: " + getIdAdministrator()
        + "\n IdUserType: " + getIdUserType()
        + "\n UserName: " + getUserName()
        + "\n Password: " + getPassword()
        + "\n CreationDate: " + getCreationDate()
        + "\n ModificateDate: " + getModificateDate();
    }
}
