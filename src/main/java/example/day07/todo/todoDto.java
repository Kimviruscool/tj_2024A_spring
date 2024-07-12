package example.day07.todo;

public class todoDto {
    private int tNo;
    private String toDo;
    private int tState;

    public todoDto(){}

    public todoDto(int tNo, String toDo, int tState) {
        this.tNo = tNo;
        this.toDo = toDo;
        this.tState = tState;
    }

    public int gettNo() {
        return tNo;
    }

    public void settNo(int tNo) {
        this.tNo = tNo;
    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public int gettState() {
        return tState;
    }

    public void settState(int tState) {
        this.tState = tState;
    }

    @Override
    public String toString() {
        return "todoDto{" +
                "tNo=" + tNo +
                ", toDo='" + toDo + '\'' +
                ", tState=" + tState +
                '}';
    }
}
