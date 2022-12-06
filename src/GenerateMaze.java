public class GenerateMaze {
    static int width = 1000;
    static int height= 1000;

    public static void main(String[] args) {
        int width = 1000;
        int height = 1000;
        System.out.print('"');
        //top row
        for (int j = 0; j < width; j++) {
            System.out.print(".");
        }
        //blank m*n maze
        System.out.print('"');
        for(int i = 1; i < height-1; i++){
            System.out.println('"' + ".");
            for (int j = 0; j < width-2; j++){
                System.out.print(' ');
            }
            System.out.print( "." +'"' + ",");
        }
        //bottom row
        System.out.print('"');
        for (int j = 0; j < width; j++) {
            System.out.print(".");
        }
        System.out.print('"');
    }
}