import java.util.Scanner;

//Calculates Eulers number(e) with an accuracy of an nth degree, up to 15 digits
public class Euler {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        System.out.println(euler(n));
    }
    private static double euler(int n){
        double y = 0;
        double x = 2;
        double a = 1;
        while(Math.abs(x - y) > Math.pow(0.1, n)){
            y = Math.pow(1 + 1 / a, a);
            x = Math.pow((1 + 1 / a), a + 1);
            a *= 2;
        }
        return (y + x) / 2;
    }
}
