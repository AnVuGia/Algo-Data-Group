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
    public static void main(String[] args) {(new Robot()).navigate();}
}


//--------------------ROBOT OBJECT-------------------------------//

class Robot {
    //declare and initialize the direction variable for the robot.
    private String _UP = "UP";
    private String _DOWN = "DOWN";
    private String _LEFT = "LEFT";
    private String _RIGHT = "RIGHT";

    public void navigate(){
        //declare the and initialize visitedMap, state Stack and maze
        VisitedMap visitedMap = new VisitedMap();
        LinkedListStack<State> stack = new LinkedListStack<>();
        Maze maze = new Maze();

        //initialize the begin_state which is the current position of the robot.
        State begin_state = new State(0,0,maze,visitedMap,"bottom");
        stack.push(begin_state); //push it the stack

        //check until robot find the escape position.
        while (!visitedMap.isClear()){
            //get the latest state of movement of the robot
            State current_state = stack.peek();
            //find the next path that the robot can go
            State next_state = searchIterative(current_state.x, current_state.y, maze,visitedMap,
                    current_state.prev_direc);

            //if the robot find the possible path to go
            //we then add the state to the stack
            if(next_state!= null){
                stack.push(next_state);
            } else { //otherwise if the robot reach the dead-end, it would
                    // need to traverse in the opposite direction of the
                    //prev_direc from the latest state.
                if(current_state.prev_direc.equals(_UP)) maze.go(_DOWN);
                else if (current_state.prev_direc.equals(_DOWN)) maze.go(_UP);
                else if (current_state.prev_direc.equals(_LEFT)) maze.go(_RIGHT);
                else if (current_state.prev_direc.equals(_RIGHT)) maze.go(_LEFT);
                // pop out the latest state out of the stack once traverse successfully
                stack.pop();
                //if the stack is empty indicating no solution was found
                //terminate the loop print out no solution statement
                if(stack.isEmpty()) {
                    System.out.println("No solution?");
                    break;
                }
            }
        }
    }
    //x: row(up, down) , y : column(left, right)
    private State searchIterative(int x, int y, Maze maze, VisitedMap visitedMap, String prev_direc) {
        //first check if the robot already visited the place below
        //the robot itself before it can go down
        if (!visitedMap.isVisited(x + 1, y)) {
            String state = maze.go(_DOWN);
            //if the robot goes down reach the escape position, clear the
            // visitedMap flag to indicate escape position was found and
            // no need for check the visitedMap further.
            if (state.equals("win")) {
                visitedMap.clear();
                return new State(x + 1, y, maze, visitedMap, _DOWN);
            } else if (state.equals("true")) {
                //if we find out the new path, then return the movement state.
                return new State((x + 1), y, maze, visitedMap, _DOWN);
            }
        }
        //if the robot hit the wall when it goes down then
        //try to go right this time and repeat the same process.
        if (!visitedMap.isVisited(x, y + 1)) { // go right
            String state = maze.go(_RIGHT);
            if (state.equals("win")) {
                visitedMap.clear();
                return new State(x, y + 1, maze, visitedMap, _RIGHT);
            } else if (state.equals("true")) {
                return new State(x, y + 1, maze, visitedMap, _RIGHT);
            }
        }
        if (!visitedMap.isVisited(x - 1, y)) { // go up
            String state = maze.go(_UP);
            if (state.equals("win")) {  //check if condition is match
                visitedMap.clear();     //set boolean state in VisitedMap
                return new State(x - 1, y, maze, visitedMap, _UP);
            } else if (state.equals("true")) {
                return new State(x - 1, y, maze, visitedMap, _UP);
            }
        }

        if (!visitedMap.isVisited(x, y - 1)) { // go left
            String state = maze.go(_LEFT);
            if (state.equals("win")) {
                visitedMap.clear();
                return new State(x, y - 1, maze, visitedMap, _LEFT);
            } else if (state.equals("true")) {
                return new State(x, y - 1, maze, visitedMap, _LEFT);
            }
        }
        //if there is no possible direction for robot to go then return null
        return null;
    }
}