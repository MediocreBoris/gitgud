import java.util.Scanner;

public class Euclid{
    public static void main(String[] args){
        java.util.Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        sc.close();
        System.out.print(euclid(x, y));
    }
    public static int euclid(int x, int y){
        while(true){
            if(x > y){
                x = x - y;
            }
            else if(y > x){
                y = y - x;
            }
            else{
                break;
            }
        }
        return x;
    }
}