package DataAccessComponent.DTOs;

public class LeaderBoardDTO {
    private Integer IdLeaderBoard;
    private Integer IdPlayer;
    private Integer IdCategory;
    private String CreationDate;
    private String ModificateDate;

    public LeaderBoardDTO() {
    }

    public LeaderBoardDTO(Integer idLeaderBoard, Integer idPlayer, Integer idCategory, String creationDate,
            String modificateDate) {
        IdLeaderBoard = idLeaderBoard;
        IdPlayer = idPlayer;
        IdCategory = idCategory;
        CreationDate = creationDate;
        ModificateDate = modificateDate;
    }

    public Integer getIdLeaderBoard() {
        return IdLeaderBoard;
    }

    public void setIdLeaderBoard(Integer IdLeaderBoard) {
        this.IdLeaderBoard = IdLeaderBoard;
    }

    public Integer getIdPlayer() {
        return IdPlayer;
    }

    public void setIdPlayer(Integer IdPlayer) {
        this.IdPlayer = IdPlayer;
    }

    public Integer getIdCategory() {
        return IdCategory;
    }

    public void setIdCategory(Integer IdCategory) {
        this.IdCategory = IdCategory;
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
                + "\n IdLeaderBoard: " + getIdLeaderBoard()
                + "\n IdPlayer: " + getIdPlayer()
                + "\n IdCategory: " + getIdCategory()
                + "\n CreationDate: " + getCreationDate()
                + "\n ModificateDate: " + getModificateDate();
    }
}
