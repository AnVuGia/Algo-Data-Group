public class Maze {
    int rows;
    int cols;
    String[] map;
    int robotRow;
    int robotCol;
    int steps;
    int [][]wallRecord;

    public Maze() {
        map = GenerateMaze.Phuoc1000x1000;
        rows = map.length;
        cols = map[0].length();
        wallRecord = new int [rows][cols];
        robotRow = 998;
        robotCol = 998;
        steps = 0;
    }

    //return the answer in form of array of direction with stack answer input
    public static String[] arrayAnswer(LinkedListStack<State>answer)
    {
        String[]res = new String [answer.size()-1];
        int index = 0;
        while (answer.size()>1){
            res[answer.size()-index-2] = answer.peek().prev_direc;
            answer.pop();
        }
        return res;
    }
    //print out the last status of the robot with array of direction
    public void checkFinalStatus(String []arr)
    {
        String answer = "";
        for (int i = 0 ; i < arr.length;i++)
        {
            answer = this.go(arr[i]);
            if (answer.equals(false))
            {
                System.out.println("INVALID");
                System.out.println("wall has been detected on the way to the exit");
                return;
            }
        }
        System.out.println(answer);
    }
    public static void printResult (String []answer)
    {
        for (int i = 0;  i< answer.length;i++)
            System.out.print(answer[i] +" ");
        System.out.println();
    }

    // implment isValide and printWall to check if the robot hit any wall more than one
    public boolean isValid(){
        for (int i = 0; i < rows;i++)
            for (int j = 0; j < cols; j++)
                if (wallRecord[i][j] >1) return false;

        return true;
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
        System.out.println("The position  of the Exit Gate : "+ (currentRow) + " " + (currentCol));
        //System.out.println("Steps to reach the Exit gate " + steps);
        return "win";
    } else if (map[currentRow].charAt(currentCol) == '.') {
        wallRecord[currentRow][currentCol]++;
        steps++;
        return "false";
    } else {
        // Space => update robot location
        steps++;
        robotRow = currentRow;
        robotCol = currentCol;
            //System.out.println("go (real): "+ (robotRow) + " " + (robotCol));
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

    public void navigate() {
        //declare the and initialize vistedMap, state Stack and maze
        VisitedMap visitedMap = new VisitedMap();
        LinkedListStack<State> stack = new LinkedListStack<>();
        Maze maze = new Maze();
        String result = "";

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

            //if the robot find the good position to go
            //we then add the state to the stack
            if(next_state!= null){
                System.out.println("Push: " + next_state.x + " " + next_state.y);
                stack.push(next_state);
            } else { //otherwise if the robot reach the dead-end,
                    //it would need to traverse back using the
                    //prev_direc from the latest state  to traverse
                    //back to previous position.
                if(current_state.prev_direc.equals(_UP)) maze.go(_DOWN);
                else if (current_state.prev_direc.equals(_DOWN)) maze.go(_UP);
                else if (current_state.prev_direc.equals(_LEFT)) maze.go(_RIGHT);
                else if (current_state.prev_direc.equals(_RIGHT)) maze.go(_LEFT);
                System.out.println("Pop: " + current_state.x + " " + current_state.y);
                // pop out the latest state out of the stack once traverse successfully
                stack.pop();
                //escape the function when no solution found
                if(stack.isEmpty()) {
                    System.out.println("No solution?");
                    break;
                }
            }
        }
        //check to see if the output is correct
        if (visitedMap.isClear())
        {
            System.out.println("The robot position when input the result: ");
//           (new Maze()).printResult(Maze.stringAnswer(stack));
            (new Maze()).checkFinalStatus(Maze.arrayAnswer(stack));
        }
        System.out.println("Check if no wall was hit twice: "+ String.valueOf(maze.isValid()));

    }
    //x: row(up, down) , y : column(left, right)
    private State searchIterative(int x, int y, Maze maze, VisitedMap visitedMap, String prev_direc){
        //first check if the robot already visited the place below
        //the robot itself before it can go down
        if (!visitedMap.isVisited(x + 1, y )) {
            //check if the robot going down reach the escape position.
            String state = maze.go(_DOWN);
            //print out the result and clear the visitedMap flag to indicate
            //escape found and no need for check the visitedMap further.
            if (state.equals("win")) {
                visitedMap.clear();
                return new State(x + 1,y,maze,visitedMap,_DOWN);
            } else if (state.equals("true")) {
                //if we find out the new path, then add up the possible
                //direction to the result and return the movement state.
                System.out.println("go " + (x+1)  + " " + (y) );
                return new State((x+1),y,maze,visitedMap,_DOWN);
            }
        }
        //if the robot hit the wall when it goes down then
        //try to go right this time and repeat the same process.
        if (!visitedMap.isVisited(x, y + 1 )) { // go right
            String state = maze.go(_RIGHT);
            if (state.equals("win")) {
                visitedMap.clear();
                return new State(x,y+ 1 ,maze,visitedMap,prev_direc);
            } else if (state.equals("true")) {
                System.out.println("go " +x + " " + (y+1) );
                return new State(x ,y+1,maze,visitedMap,_RIGHT);
            }
        }
        if (!visitedMap.isVisited(x - 1, y )) { // go up
            String state = maze.go(_UP);
            if (state.equals("win")) {  //check if condition is match
                visitedMap.clear();     //set boolean state in VisitedMap
                return new State(x - 1,y,maze,visitedMap,_UP);
            } else if (state.equals("true")) {
                System.out.println("go " + (x-1) + " " + y);
                return new State(x - 1,y ,maze,visitedMap,_UP);
            }
        }

        if (!visitedMap.isVisited(x, y - 1)) { // go left
            String state = maze.go(_LEFT);
            if (state.equals("win")) {
                visitedMap.clear();
                return new State(x,y- 1,maze,visitedMap,_LEFT);            }
            else if (state.equals("true")) {
                System.out.println("go " +(x) + " " +( y- 1));
                return new State(x,y - 1,maze,visitedMap,_LEFT);
            }
        }
        //if there is no possible direction for robot to go then return null
        return null;
    }




}