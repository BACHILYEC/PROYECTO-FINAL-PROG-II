package DataAccessComponent.DTO;

public class AnswerDTO {
    private Integer IdAnswer;
    private Integer IdQuestion;
    private String Answer;
    private Boolean CorrectAnswer;
    private String CreationDate;
    private String ModificateDate;
    
    public AnswerDTO() {}

    public AnswerDTO(Integer idAnswer, Integer idQuestion, String answer, Boolean correctAnswer, String creationDate,
            String modificateDate) {
        IdAnswer = idAnswer;
        IdQuestion = idQuestion;
        Answer = answer;
        CorrectAnswer = correctAnswer;
        CreationDate = creationDate;
        ModificateDate = modificateDate;
    }

    public Integer getIdAnswer() {
        return IdAnswer;
    }

    public void setIdAnswer(Integer IdAnswer) {
        this.IdAnswer = IdAnswer;
    }

    public Integer getIdQuestion() {
        return IdQuestion;
    }

    public void setIdQuestion(Integer IdQuestion) {
        this.IdQuestion = IdQuestion;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }

    public Boolean getCorrectAnswer() {
        return CorrectAnswer;
    }

    public void setCorrectAnswer(Boolean CorrectAnswer) {
        this.CorrectAnswer = CorrectAnswer;
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
        + "\n IdAnswer: " + getIdAnswer()
        + "\n IdQuestion: " + getIdQuestion()
        + "\n Answer: " + getAnswer()
        + "\n CorrectAnswer: " + getCorrectAnswer()
        + "\n CreationDate: " + getCreationDate()
        + "\n ModificateDate: " + getModificateDate();
    }
}
