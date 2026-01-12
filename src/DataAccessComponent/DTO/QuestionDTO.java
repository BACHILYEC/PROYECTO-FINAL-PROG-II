package DataAccessComponent.DTO;

public class QuestionDTO {
    private Integer IdQuestion;
    private Integer IdCategory;
    private String Question;
    private String CreationDate;
    private String ModificateDate;

    public QuestionDTO() {}

    public QuestionDTO(Integer idQuestion, Integer idCategory, String question, String creationDate,
            String modificateDate) {
        IdQuestion = idQuestion;
        IdCategory = idCategory;
        Question = question;
        CreationDate = creationDate;
        ModificateDate = modificateDate;
    }

    public Integer getIdQuestion() {
        return IdQuestion;
    }

    public void setIdQuestion(Integer IdQuestion) {
        this.IdQuestion = IdQuestion;
    }

    public Integer getIdCategory() {
        return IdCategory;
    }

    public void setIdCategory(Integer IdCategory) {
        this.IdCategory = IdCategory;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
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
        + "\n IdQuestion: " + getIdQuestion()
        + "\n IdCategory: " + getIdCategory()
        + "\n Question: " + getQuestion()
        + "\n CreationDate: " + getCreationDate()
        + "\n ModificateDate: " + getModificateDate();
    }
    
}
