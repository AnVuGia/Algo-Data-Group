public class VisitedMap {
    private boolean isClear = false;
    private int MAX_ROW = 1000;
    private int MAX_COLUMN = 1000;
    private int arr1[][] = new int[MAX_ROW/2][MAX_COLUMN/2]; // -x , y
    private int arr2[][] = new int[MAX_ROW/2][MAX_COLUMN/2]; //  x, y (0,0)
    private int arr3[][] = new int[MAX_ROW/2][MAX_COLUMN/2]; // x, -y
    private int arr4[][] = new int[MAX_ROW/2][MAX_COLUMN/2]; // -x, -y

    public boolean isVisited(int x, int y){
        if(isClear) return true;
        if(Math.abs(x) >= MAX_COLUMN/2 || Math.abs(y) >= MAX_COLUMN/2 ){
            outOfBound(x,y);
        }
        if(x >= 0 && y >= 0){           //section 2
            if(arr2[x][y] == 1){ // is visited
                return true;
            }
            arr2[x][y] = 1;     //is not visited
            return false;
        }
        if(x < 0 && y > 0){    // section 1
            if(arr1[x][y] == 1){
                return true;
            }
            arr1[x][y] = 1;
            return false;
        }
        if(x > 0 && y < 0){ //section 3
            if(arr3[x][y] == 1){
                return true;
            }
            arr3[x][y] = 1;
            return false;
        }
        if(x < 0 && y < 0){ //section 4
            if(arr4[x][y] == 1){
                return true;
            }
            arr4[x][y] = 1;
            return false;
        }
        return true;
    }
    private void outOfBound(int x, int y){
        int new_x = x >= 500 ?  (MAX_COLUMN - Math.abs(x))*-1 : x*-1;
        int new_y =  y >= 500 ?  (MAX_COLUMN - Math.abs(y))*-1 : y*-1;
        isVisited(new_x,new_y);
    }
    public void clear(){
        isClear = true;
    }
}
