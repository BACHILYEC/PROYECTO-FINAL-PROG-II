package DataAccessComponent.DTOs;

public class CategoryDTO {
    private Integer idCategory;
    private String name;
    private String description;
    private String creationDate;
    private String modificateDate;

    public CategoryDTO() {
    }

    public CategoryDTO(Integer idCategory, String name, String description) {
        this.idCategory = idCategory;
        this.name = name;
        this.description = description;
    }

    public CategoryDTO(Integer idCategory, String name, String description, String creationDate,
            String modificateDate) {
        this.idCategory = idCategory;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.modificateDate = modificateDate;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
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
                + "\n idCategory: " + getIdCategory()
                + "\n name: " + getName()
                + "\n description: " + getDescription()
                + "\n creationDate: " + getCreationDate()
                + "\n modificateDate: " + getModificateDate();
    }
}
