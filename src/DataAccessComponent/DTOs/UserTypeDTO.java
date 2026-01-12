package DataAccessComponent.DTO;

public class UserTypeDTO {
    private Integer IdUserType;
    private String Name;
    private String Description;
    private String CreationDate;
    private String ModificateDate; 

    public UserTypeDTO() {}

    public UserTypeDTO(Integer idUserType, String name, String description, String creationDate, String modificateDate) {
        IdUserType = idUserType;
        Name = name;
        Description = description;
        CreationDate = creationDate;
        ModificateDate = modificateDate;
    }

    public Integer getIdUserType() {
        return IdUserType;
    }

    public void setIdUserType(Integer IdUserType) {
        this.IdUserType = IdUserType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
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
        + "\n IdUserType: " + getIdUserType()
        + "\n Name: " + getName()
        + "\n Description: " + getDescription()
        + "\n CreationDate: " + getCreationDate()
        + "\n ModificateDate: " + getModificateDate();
    } 
}
