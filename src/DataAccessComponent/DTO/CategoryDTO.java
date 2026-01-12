package DataAccessComponent.DTO;

public class CategoryDTO {
    private Integer IdCategory;
    private String Name;
    private String Description;
    private String CreationDate;
    private String ModificateDate;
    
    public CategoryDTO() {}

    public CategoryDTO(Integer idCategory, String name, String description, String creationDate,
            String modificateDate) {
        IdCategory = idCategory;
        Name = name;
        Description = description;
        CreationDate = creationDate;
        ModificateDate = modificateDate;
    }

    public Integer getIdCategory() {
        return IdCategory;
    }

    public void setIdCategory(Integer IdCategory) {
        this.IdCategory = IdCategory;
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
        + "\n IdCategory: " + getIdCategory()
        + "\n Name: " + getName()
        + "\n Description: " + getDescription()
        + "\n CreationDate: " + getCreationDate()
        + "\n ModificateDate: " + getModificateDate();
    }
}
