public class VisitedMap {
    private boolean isClear = false;
    private int MAX_ROW = 1001;
    private int MAX_COLUMN = 1001;

    //since we are creating the relative map to track the movement of the robot
    //we declare 4 boolean arrays to indicate the 4 quadrants because the initial
    //position of robot is 0,0 on our relative map. We are not sure if we have
    //to move back (decrease the columns) or go up (decrease the row) which leads to
    //negative index value and causes out of bound error.
    private boolean quad1[][] = new boolean[MAX_ROW][MAX_COLUMN]; // -x , y
    private boolean quad2[][] = new boolean[MAX_ROW][MAX_COLUMN]; //  x, y
    private boolean quad3[][] = new boolean[MAX_ROW][MAX_COLUMN]; // x, -y
    private boolean quad4[][] = new boolean[MAX_ROW][MAX_COLUMN]; // -x, -y

    public VisitedMap() {
        //initialize the construction and mark the initial position
        //of the robot on 4 different quadrant.
        quad1[0][0] = true;
        quad2[0][0] = true;
        quad3[0][0] = true;
        quad4[0][0] = true;
    }
    public boolean isVisited(int x, int y){
        if(isClear) return true;
        //get the absolute coordinate of x and y to avoid
        //accessing negative index
        int abs_x = Math.abs(x);
        int abs_y = Math.abs(y);

        //With the current value of x and y, check if it has visited
        //the relative map according to the 4 quadrants
        if(x >= 0 && y >= 0){           //section 2
            if(quad2[abs_x][abs_y]){ // is visited
                return true;
            }
            quad2[abs_x][abs_y] = true;     //is not visited
            return false;
        }
        if(x <= 0 && y >= 0){    // section 1
            if(quad1[abs_x][abs_y]){
                return true;
            }
            quad1[abs_x][abs_y] = true;
            return false;
        }
        if(x >= 0 && y <= 0){ //section 3
            if(quad3[abs_x][abs_y]){
                return true;
            }
            quad3[abs_x][abs_y] = true;
            return false;
        }
        if(x <= 0 && y <= 0){ //section 4
            if(quad4[abs_x][abs_y]){
                return true;
            }
            quad4[abs_x][abs_y] = true;
            return false;
        }
        return false;
    }
    //set the isClear to true
    public void clear(){    //set clear state
        isClear = true;
    }

    //check if isClear true or false
    public boolean isClear(){return isClear;};
}
