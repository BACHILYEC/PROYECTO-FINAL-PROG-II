package DataAccessComponent.DTOs;

public class UserPlayerDTO {
    private Integer IdPlayer;
    private Integer IdUserType;
    private String Name;
    private Integer Score;
    private String CreationDate;
    private String ModificateDate;

    public UserPlayerDTO() {
    }

    public UserPlayerDTO(Integer idPlayer, Integer idUserType, String name, Integer score) {
        IdPlayer = idPlayer;
        IdUserType = idUserType;
        Name = name;
        Score = score;
    }

    public UserPlayerDTO(Integer idPlayer, String name, Integer score) {
        IdPlayer = idPlayer;
        Name = name;
        Score = score;
    }

    public UserPlayerDTO(Integer idPlayer, Integer idUserType, String name, Integer score, String creationDate,
            String modificateDate) {
        IdPlayer = idPlayer;
        IdUserType = idUserType;
        Name = name;
        Score = score;
        CreationDate = creationDate;
        ModificateDate = modificateDate;
    }

    public Integer getIdPlayer() {
        return IdPlayer;
    }

    public void setIdPlayer(Integer IdPlayer) {
        this.IdPlayer = IdPlayer;
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

    public Integer getScore() {
        return Score;
    }

    public void setScore(Integer Score) {
        this.Score = Score;
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
                + "\n IdPlayer: " + getIdPlayer()
                + "\n IdUserType: " + getIdUserType()
                + "\n Name: " + getName()
                + "\n Score: " + getScore()
                + "\n CreationDate: " + getCreationDate()
                + "\n ModificateDate: " + getModificateDate();
    }
}
