public class VisitedMap {
    private boolean isClear = false;
    private int MAX_ROW = 1001;
    private int MAX_COLUMN = 1001;

    //declare 4 int array to indicate the 4 relative map position that
    //the robot can possibly go
    private boolean quad1[][] = new boolean[MAX_ROW][MAX_COLUMN]; // -x , y
    private boolean quad2[][] = new boolean[MAX_ROW][MAX_COLUMN]; //  x, y (0,0)
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
//        if(Math.abs(x) >= MAX_COLUMN/2 || Math.abs(y) >= MAX_COLUMN/2 ){
//            outOfBound(x,y);
//        }
        //get the absolute coordinate of x and y to avoid
        //the negative index
        int abs_x = Math.abs(x);
        int abs_y = Math.abs(y);

        //With the current value of x and y, check if it has visited the
        //the relative map according to the 4 quadrants
        if(x >= 0 && y >= 0){           //section 2
            if(quad2[abs_x][abs_y]){ // is visited
                return true;
            }
            quad2[abs_x][abs_y] = true;     //is not visited
            System.out.println("Mark quad2 " + x + " " + y); //print out to test
            return false;
        }
        if(x <= 0 && y >= 0){    // section 1
            if(quad1[abs_x][abs_y]){
                return true;
            }
            quad1[abs_x][abs_y] = true;
            System.out.println("Mark quad1 " + x + " " + y);
            return false;
        }
        if(x >= 0 && y <= 0){ //section 3
            if(quad3[abs_x][abs_y]){
                return true;
            }
            quad3[abs_x][abs_y] = true;
            System.out.println("Mark quad3 " + x + " " + y);
            return false;
        }
        if(x <= 0 && y <= 0){ //section 4
            if(quad4[abs_x][abs_y]){
                return true;
            }
            quad4[abs_x][abs_y] = true;
            System.out.println("Mark quad4 " + x + " " + y);
            return false;
        }
        return false;
    }
    //leave it here just in case
//    private void outOfBound(int x, int y){  //function to calculate and re-allocate new position for the map
//        int new_x = x >= 500 ?  (MAX_COLUMN - Math.abs(x))*-1 : x*-1;
//        int new_y =  y >= 500 ?  (MAX_COLUMN - Math.abs(y))*-1 : y*-1;
//        isVisited(new_x,new_y);
//    }
    //set the isClear to true
    public void clear(){    //set clear state
        isClear = true;
    }

    //check if isClear true or false
    public boolean isClear(){return isClear;};
}
