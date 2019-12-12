import java.util.Scanner;

public class CubeRoot
{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double y = sc.nextDouble();
        sc.close();
        double x = 0;
        double i = 0.5;
        System.out.println(cube(x, y, i));
    }
    private static double cube(double x, double y, double i){
        while(x*x*x < y){
            x++;
        }
        double z = x*x*x - y;
        while(z > 0.0001){
            if(x*x*x > y){
                x -= i;
            }
            else{
                x += i;
            }
            i /= 2;
            if(x*x*x > y){
                z = x*x*x - y;
            }
            else{
                z = y - x*x*x;
            }
        }
        return x;
    }
}
