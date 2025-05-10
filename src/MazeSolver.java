import java.util.ArrayList;
import java.util.Objects;

public class MazeSolver {
    private String[][] maze;
    private ArrayList<Points> steps;
    private final Points start = new Points(0,0);
    private Points currentPosition;
    private Points end;

    public MazeSolver(int x, int y, String[][] grid){
        // bottom-right corner
        end = new Points(y-1, x-1);
        maze = grid;
        steps= new ArrayList<Points>();
        currentPosition= new Points(0,0);
    }

    public boolean canMoveUp(){
        if(currentPosition.getRow()==0){
            return false;
        }
        if(maze[currentPosition.getRow()-1][currentPosition.getColumn()].equals("#")){
            return false;
        }
        return true;
    }

    public boolean canMoveDown(){
        if(currentPosition.getRow()==maze.length-1){
            return false;
        }
        if(maze[currentPosition.getRow()+1][currentPosition.getColumn()].equals("#")){
            return false;
        }
        return true;
    }

    public boolean canMoveRight(){
        if(currentPosition.getColumn()== maze[0].length-1){
            return false;
        }
        if(maze[currentPosition.getRow()][currentPosition.getColumn()+1].equals("#")){
            return false;

        }
        return true;
    }

    public boolean canMoveLeft() {
        if (currentPosition.getColumn() == 0) {
            return false;
        }
        if (maze[currentPosition.getRow()][currentPosition.getColumn() - 1].equals("#")) {
            return false;
        }
        return true;
    }

    public String moveUp(){
        currentPosition.setRow(currentPosition.getRow()-1);
        Points move = new Points(currentPosition.getRow(),currentPosition.getColumn());
        steps.add(move);
        return (currentPosition.toString());
    }

    public String moveDown(){
        currentPosition.setRow(currentPosition.getRow()+1);
        Points move = new Points(currentPosition.getRow(),currentPosition.getColumn());
        steps.add(move);
        return (currentPosition.toString());
    }

    public String moveRight(){
        currentPosition.setColumn(currentPosition.getColumn()+1);
        Points move = new Points(currentPosition.getRow(),currentPosition.getColumn());
        steps.add(move);
        return (currentPosition.toString());
    }

    public String moveLeft(){
        currentPosition.setColumn(currentPosition.getColumn()-1);
        Points move = new Points(currentPosition.getRow(),currentPosition.getColumn());
        steps.add(move);
        return (currentPosition.toString());
    }

    public String directionFrom(){
        if (currentPosition.toString().equals(start.toString())){
            return "Nowhere";
        }
        if (steps.size() > 1) {

            if (!steps.isEmpty() && steps.get(steps.size() - 2).getColumn() == currentPosition.getColumn() - 1) {
                return "Left";
            }
            if (!steps.isEmpty() && steps.get(steps.size() - 2).getColumn() == currentPosition.getColumn() + 1) {
                return "Right";
            }
            if (!steps.isEmpty() && steps.get(steps.size() - 2).getRow() == currentPosition.getRow() - 1) {
                return "Up";
            }
            if (!steps.isEmpty() && steps.get(steps.size() - 2).getRow() == currentPosition.getRow() + 1) {
                return "Down";
            }
        }
        return "how";
    }

    public boolean isDeadEnd() {
        String directionCameFrom = directionFrom();

        if ("Up".equals(directionCameFrom)) {
            return !(canMoveLeft() || canMoveRight() || canMoveDown());
        }
        if ("Down".equals(directionCameFrom)) {
            return !(canMoveLeft() || canMoveRight() || canMoveUp());
        }
        if ("Left".equals(directionCameFrom)) {
            return !(canMoveUp() || canMoveDown() || canMoveRight());
        }
        if ("Right".equals(directionCameFrom)) {
            return !(canMoveUp() || canMoveDown() || canMoveLeft());
        }

        return !(canMoveUp() || canMoveDown() || canMoveLeft() || canMoveRight());
    }

    public String solveMaze(){
        boolean hasFork = false;

        Points start = new Points(0,0);
        boolean deadEnd =false;
        boolean win =false;

        steps.add(start);
        int count = 0;

        while (!win){

            boolean hasMoved =false;
            if(deadEnd){
                currentPosition.setRow(0);
                currentPosition.setColumn(0);
                deadEnd = false;
            }

            if(isDeadEnd()){
                deadEnd=true;
                maze[currentPosition.getRow()][currentPosition.getColumn()] = "#";
                for(int i =0 ; i<count;i++){
                    if(!steps.isEmpty()) {
                        steps.remove(steps.size()-1);
                    }
                }
                hasFork=true;
            }

            if(!hasMoved && canMoveLeft() && !directionFrom().equals("Left")&&!deadEnd){
                moveLeft();
                hasMoved =true;
                count++;
            }

            if(!hasMoved && canMoveRight()&& !directionFrom().equals("Right")&&!deadEnd){
                moveRight();
                hasMoved =true;
                count++;
            }

            if(!hasMoved && canMoveUp() && !directionFrom().equals("Up")&&!deadEnd){
                moveUp();
                hasMoved =true;
                count++;
            }

            if(!hasMoved && canMoveDown()&& !directionFrom().equals("Down")&&!deadEnd){
                moveDown();
                hasMoved =true;
                count++;
            }

            if(currentPosition.toString().equals(end.toString())){
                win=true;
            }
            hasMoved=false;
        }

        String stepsText = "";
        if(!hasFork){
            stepsText+=start.toString()+" ---> ";
        }

        for(Points coord :steps){
            stepsText+=coord.toString()+" ---> ";
        }
        return (stepsText.substring(11,stepsText.length()-5));
    }
}



