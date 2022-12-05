public class VisitedMap {
    public VisitedMap() {
        arr1[0][0] = 1;
        arr2[0][0] = 1;
        arr3[0][0] = 1;
        arr4[0][0] = 1;
    }

    private boolean isClear = false;
    private int MAX_ROW = 1001;
    private int MAX_COLUMN = 1001;
    private int arr1[][] = new int[MAX_ROW][MAX_COLUMN]; // -x , y
    private int arr2[][] = new int[MAX_ROW][MAX_COLUMN]; //  x, y (0,0)
    private int arr3[][] = new int[MAX_ROW][MAX_COLUMN]; // x, -y
    private int arr4[][] = new int[MAX_ROW][MAX_COLUMN]; // -x, -y

    public boolean isVisited(int x, int y){
        if(isClear) return true;
//        if(Math.abs(x) >= MAX_COLUMN/2 || Math.abs(y) >= MAX_COLUMN/2 ){
//            outOfBound(x,y);
//        }
        int abs_x = Math.abs(x);
        int abs_y = Math.abs(y);
        if(x >= 0 && y >= 0){           //section 2
            if(arr2[abs_x][abs_y] == 1){ // is visited
                return true;
            }
            arr2[abs_x][abs_y] = 1;     //is not visited
            System.out.println("Mark arr2 " + x + " " + y); //print out to test
            return false;
        }
        if(x <= 0 && y >= 0){    // section 1
            if(arr1[abs_x][abs_y] == 1){
                return true;
            }
            arr1[abs_x][abs_y] = 1;
            System.out.println("Mark arr1 " + x + " " + y);
            return false;
        }
        if(x >= 0 && y <= 0){ //section 3
            if(arr3[abs_x][abs_y] == 1){
                return true;
            }
            arr3[abs_x][abs_y] = 1;
            System.out.println("Mark arr3 " + x + " " + y);
            return false;
        }
        if(x <= 0 && y <= 0){ //section 4
            if(arr4[abs_x][abs_y] == 1){
                return true;
            }
            arr4[abs_x][abs_y] = 1;
            System.out.println("Mark arr4 " + x + " " + y);
            return false;
        }
        return true;
    }
    //leave it here just in case
//    private void outOfBound(int x, int y){  //function to calculate and re-allocate new position for the map
//        int new_x = x >= 500 ?  (MAX_COLUMN - Math.abs(x))*-1 : x*-1;
//        int new_y =  y >= 500 ?  (MAX_COLUMN - Math.abs(y))*-1 : y*-1;
//        isVisited(new_x,new_y);
//    }
    public void clear(){    //set clear state
        isClear = true;
    }
    public boolean isClear(){return isClear;};
}
