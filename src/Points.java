public class Points {
    private int row;
    private int column;

    public Points(int x,  int y){
        row=x;
        column=y;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String toString(){
        return "("+row+", "+column+")";
    }
}
