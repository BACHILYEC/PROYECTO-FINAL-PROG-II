package DataAccessComponent.DTO;

public class AnswerDTO {

    public AnswerDTO(Integer idAnswer, Integer idQuestion, String answer) {
        IdAnswer = idAnswer;
        IdQuestion = idQuestion;
        Answer = answer;
    }

    private Integer IdAnswer;
    private Integer IdQuestion;
    private String Answer;
    private Integer CorrectAnswer;
    private String CreationDate;
    private String ModificateDate;
    private String Status;

    public AnswerDTO(Integer idAnswer, Integer idQuestion, String answer, Integer correctAnswer) {
        IdAnswer = idAnswer;
        IdQuestion = idQuestion;
        Answer = answer;
        CorrectAnswer = correctAnswer;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public AnswerDTO() {
    }

    public AnswerDTO(
            Integer idAnswer,
            Integer idQuestion,
            String answer,
            Integer correctAnswer,
            String status,
            String creationDate,
            String modificateDate) {

        this.IdAnswer = idAnswer;
        this.IdQuestion = idQuestion;
        this.Answer = answer;
        this.CorrectAnswer = correctAnswer;
        this.Status = status;
        this.CreationDate = creationDate;
        this.ModificateDate = modificateDate;
    }

    public Integer getIdAnswer() {
        return IdAnswer;
    }

    public void setIdAnswer(Integer idAnswer) {
        this.IdAnswer = idAnswer;
    }

    public Integer getIdQuestion() {
        return IdQuestion;
    }

    public void setIdQuestion(Integer idQuestion) {
        this.IdQuestion = idQuestion;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        this.Answer = answer;
    }

    public Integer getCorrectAnswer() {
        return CorrectAnswer;
    }

    public void setCorrectAnswer(Integer correctAnswer) {
        this.CorrectAnswer = correctAnswer;
    }

    public String getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(String creationDate) {
        this.CreationDate = creationDate;
    }

    public String getModificateDate() {
        return ModificateDate;
    }

    public void setModificateDate(String modificateDate) {
        this.ModificateDate = modificateDate;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "\n IdAnswer: " + IdAnswer
                + "\n IdQuestion: " + IdQuestion
                + "\n Answer: " + Answer
                + "\n CorrectAnswer: " + CorrectAnswer
                + "\n Status: " + Status
                + "\n CreationDate: " + CreationDate
                + "\n ModificateDate: " + ModificateDate;
    }
}
