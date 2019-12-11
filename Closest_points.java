import java.util.Scanner;

public class Closest_points{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[][] ar = new int[2][255];
        int i = 0;
        while(1==1){
            try{
                ar[0][i] = sc.nextInt();
                ar[1][i] = sc.nextInt();
                i++;
            }
            catch(Exception InputMismatchException){
                sc.close();
                int[][] res = comp(ar, i);
                System.out.print(res[0][0] + " " + res[1][0] + "\n");
                System.out.print(res[0][1] + " " + res[1][1]);
                break;
            }
        }
    }
    public static int[][] comp(int[][] arg, int len){
        int[][] re = new int[2][2];
        re[0][0] = arg[0][0];
        re[0][1] = arg[0][1];
        re[1][0] = arg[1][0];
        re[1][1] = arg[1][1];
        double clos = Math.sqrt(Math.pow(arg[0][0]-arg[0][1], 2)+Math.pow(arg[1][0]-arg[1][1], 2));
        for(int k = 0; k < len; k++){
            for(int j = k+1; j < len; j++){
                double c = Math.sqrt(Math.pow(arg[0][k]-arg[1][k], 2)+Math.pow(arg[0][j]-arg[1][j], 2));
                if(c < clos){
                    clos = c;
                    re[0][0] = arg[0][k];
                    re[1][0] = arg[1][k];
                    re[0][1] = arg[0][j];
                    re[1][1] = arg[1][j];
                }
            }
        }
        return re;
    }
}