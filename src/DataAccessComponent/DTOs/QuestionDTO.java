package DataAccessComponent.DTOs;

public class QuestionDTO {
    public QuestionDTO(String question, Integer idQuestion) {
        this.idQuestion = idQuestion;
        this.question = question;
    }

    private Integer idQuestion;
    private Integer idCategory;
    private String question;
    private String creationDate;
    private String modificateDate;

    public QuestionDTO() {
    }

    public QuestionDTO(String question) {
        this.question = question;

    }

    public QuestionDTO(Integer idQuestion, Integer idCategory, String question) {
        this.question = question;
        this.idQuestion = idQuestion;
        this.idCategory = idCategory;

    }

    public QuestionDTO(Integer idQuestion, Integer idCategory, String question, String creationDate,
            String modificateDate) {
        this.idQuestion = idQuestion;
        this.idCategory = idCategory;
        this.question = question;
        this.creationDate = creationDate;
        this.modificateDate = modificateDate;
    }

    public Integer getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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
                + "\n idQuestion: " + getIdQuestion()
                + "\n idCategory: " + getIdCategory()
                + "\n question: " + getQuestion()
                + "\n creationDate: " + getCreationDate()
                + "\n modificateDate: " + getModificateDate();
    }

}
