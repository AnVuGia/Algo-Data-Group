public class Maze {
    int rows;
    int cols;
    String[] map;
    int robotRow;
    int robotCol;
    int steps;

    public Maze() {
        // Note: in my real test, I will create much larger
        // and more complicated map
        rows = 4;
        cols = 5;
        map = new String[rows];
        map[0] = ".....";
        map[1] = ".   X";
        map[2] = ".   .";
        map[3] = ".....";
        robotRow = 2;
        robotCol = 1;
        steps = 0;
    }

    public String go(String direction) {
        if (!direction.equals("UP") &&
                !direction.equals("DOWN") &&
                !direction.equals("LEFT") &&
                !direction.equals("RIGHT")) {
            // invalid direction
            steps++;
            return "false";
        }
        int currentRow = robotRow;
        int currentCol = robotCol;
        if (direction.equals("UP")) {
            currentRow--;
        } else if (direction.equals("DOWN")) {
            currentRow++;
        } else if (direction.equals("LEFT")) {
            currentCol--;
        } else {
            currentCol++;
        }


    // check the next position
        if (map[currentRow].charAt(currentCol) == 'X') {
        // Exit gate
        steps++;
        System.out.println("Steps to reach the Exit gate " + steps);
        return "win";
    } else if (map[currentRow].charAt(currentCol) == '.') {
        // Wall
        steps++;
        return "false";
    } else {
        // Space => update robot location
        steps++;
        robotRow = currentRow;
        robotCol = currentCol;
        return "true";
    }
}
    public static void main(String[] args) {
        (new Robot()).navigate();
    }
}

class Robot {

    private String _UP = "UP";
    private String _DOWN = "DOWN";
    private String _LEFT = "LEFT";
    private String _RIGHT = "RIGHT";

    public void navigate() {
        VisitedMap visitedMap = new VisitedMap();
        Maze maze = new Maze();
        int relative_x = 0;
        int relative_y = 0;
        String result = "";
        searchRecur(relative_x,relative_y,result,maze,visitedMap);
    }

    private boolean searchRecur(int x, int y, String result, Maze maze, VisitedMap visitedMap) {

        if (!visitedMap.isVisited(x, y + 1)) { // go up
            String state = maze.go(_UP);
            if (state.equals("win")) {
                visitedMap.clear();
                System.out.println(result);
                return true;
            } else if (state.equals("true")) {
                result += _UP + " ";
                searchRecur(x, y + 1, result, maze, visitedMap);
                return true;
            }
        }
        if (!visitedMap.isVisited(x+ 1, y )) { // go right
            String state = maze.go(_RIGHT);
            if (state.equals("win")) {
                System.out.println(result);
                visitedMap.clear();
                return true;
            } else if (state.equals("true")) {
                result += _RIGHT + " ";
                searchRecur(x+ 1, y , result, maze, visitedMap);
                return true;
            }
        }
        if (!visitedMap.isVisited(x, y - 1)) { // go down
            String state = maze.go(_DOWN);
            if (state.equals("win")) {
                System.out.println(result);
                visitedMap.clear();
                return true;
            } else if (state.equals("true")) {
                result += _DOWN + " ";
                searchRecur(x, y - 1, result, maze, visitedMap);
                return true;
            }
        }
        if (!visitedMap.isVisited(x- 1, y )) { // go left
            String state = maze.go(_LEFT);
            if (state.equals("win")) {
                visitedMap.clear();
                System.out.println(result);
                return true;
            } else if (state.equals("true")) {
                result += _LEFT + " ";
                searchRecur(x- 1, y , result, maze, visitedMap);
                return true;
            }
        }
        return false;
    }
}