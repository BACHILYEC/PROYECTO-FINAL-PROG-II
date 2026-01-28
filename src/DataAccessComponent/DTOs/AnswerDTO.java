package DataAccessComponent.DTOs;

public class AnswerDTO {

    public AnswerDTO(Integer idAnswer, Integer idQuestion, String answer) {
        this.idAnswer = idAnswer;
        this.idQuestion = idQuestion;
        this.answer = answer;
    }

    public AnswerDTO(Integer idAnswer, Integer idQuestion, String answer, Integer correctAnswer, String status) {
        this.idAnswer = idAnswer;
        this.idQuestion = idQuestion;
        this.answer = answer;
        this.correctAnswer = correctAnswer;
        this.status = status;
    }

    public AnswerDTO(Integer idAnswer, String answer) {
        this.idAnswer = idAnswer;
        this.answer = answer;
    }

    private Integer idAnswer;
    private Integer idQuestion;
    private String answer;
    private Integer correctAnswer;
    private String creationDate;
    private String modificateDate;
    private String status;

    public AnswerDTO(String answer) {
        this.answer = answer;
    }

    public AnswerDTO(Integer idAnswer, Integer idQuestion, String answer, Integer correctAnswer) {
        this.idAnswer = idAnswer;
        this.idQuestion = idQuestion;
        this.answer = answer;
        this.correctAnswer = correctAnswer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

        this.idAnswer = idAnswer;
        this.idQuestion = idQuestion;
        this.answer = answer;
        this.correctAnswer = correctAnswer;
        this.status = status;
        this.creationDate = creationDate;
        this.modificateDate = modificateDate;
    }

    public Integer getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(Integer idAnswer) {
        this.idAnswer = idAnswer;
    }

    public Integer getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer = correctAnswer;
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
                + "\n idAnswer: " + idAnswer
                + "\n idQuestion: " + idQuestion
                + "\n answer: " + answer
                + "\n correctAnswer: " + correctAnswer
                + "\n status: " + status
                + "\n creationDate: " + creationDate
                + "\n modificateDate: " + modificateDate;
    }
}
