package DataAccessComponent.DTOs;

public class UserPlayerDTO {
    private Integer idPlayer;
    private Integer idUserType;
    private String name;
    private Integer score;
    private String status;

    private String creationDate;
    private String modificateDate;

    public UserPlayerDTO() {
    }

    public UserPlayerDTO(String name) {
        this.name = name;
    }

    public UserPlayerDTO(Integer idPlayer, Integer idUserType, String name, Integer score) {
        this.idPlayer = idPlayer;
        this.idUserType = idUserType;
        this.name = name;
        this.score = score;
    }

    public UserPlayerDTO(Integer idPlayer, String name, Integer score) {
        this.idPlayer = idPlayer;
        this.name = name;
        this.score = score;
    }

    public UserPlayerDTO(Integer idPlayer, Integer idUserType, String name, Integer score, String creationDate) {
        this.idPlayer = idPlayer;
        this.idUserType = idUserType;
        this.name = name;
        this.score = score;
        this.creationDate = creationDate;
    }

    public UserPlayerDTO(Integer idPlayer, Integer idUserType, String name, Integer score, String creationDate,
            String modificateDate) {
        this.idPlayer = idPlayer;
        this.idUserType = idUserType;
        this.name = name;
        this.score = score;
        this.creationDate = creationDate;
        this.modificateDate = modificateDate;
    }

    public Integer getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(Integer idPlayer) {
        this.idPlayer = idPlayer;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
                + "\n idPlayer: " + getIdPlayer()
                + "\n idUserType: " + getIdUserType()
                + "\n name: " + getName()
                + "\n score: " + getScore()
                + "\n status: " + getStatus()
                + "\n creationDate: " + getCreationDate()
                + "\n modificateDate: " + getModificateDate();
    }
}
