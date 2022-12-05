public class GenerateMaze {
    static int width = 1000;
    static int height= 1000;

    public static void main(String[] args) {
         int width = 1000;
         int height= 1000;
         System.out.print("");
        for (int j = 0; j < width; j++)
            System.out.print(".");
        System.out.println("");
        for (int i = 0; i< height-2;i++)
        {
            System.out.print(".");
            for (int j = 1; j < width-1; j++)
            {
                double temp = Math.random();
                if (temp >0.3)
                    System.out.print(' ');
                else
                    System.out.print('.');
            }
            System.out.println(".");
        }
        System.out.print("");
            for (int j = 0; j < width; j++)
                System.out.print(".");
            System.out.println("");
        }
}