package DataAccessComponent.DTOs;

public class UserTypeDTO {
    private Integer idUserType;
    private String name;
    private String description;
    private String creationDate;
    private String modificateDate;

    public UserTypeDTO() {
    }

    public UserTypeDTO(Integer idUserType, String name, String description, String creationDate,
            String modificateDate) {
        this.idUserType = idUserType;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.modificateDate = modificateDate;
    }

    public Integer getIdUserType() {
        return idUserType;
    }

    public void setIdUserType(Integer idUserType) {
        this.idUserType = idUserType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getModificateDate() {
        return modificateDate;
    }

    public void setModificateDate(String modificateDate) {
        this.modificateDate = modificateDate;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "\n idUserType: " + getIdUserType()
                + "\n name: " + getName()
                + "\n description: " + getDescription()
                + "\n creationDate: " + getCreationDate()
                + "\n modificateDate: " + getModificateDate();
    }
}
