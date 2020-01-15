import java.util.Arrays;
import java.util.Scanner;

public class Factorization {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int[] a = new int[1];
        a[0] = sc.nextInt();
        sc.close();
        a = factorize(a);
        System.out.print(a[0]);
        for(int i = 1; i < a.length; i++){
            System.out.print(" x " + a[i]);
        }
    }

    public static int[] factorize(int[] a){
        for(int i = 2; i < Math.ceil(a[0] / 2) + 1; i++){
            if(a[0] % i == 0){
                a = Arrays.copyOf(a, a.length + 1);
                a[a.length - 1] = i;
                a[0] /= i;
                a = factorize(a);
                break;
            }
        }
        for(int i = 0; i < a.length - 1; i++){
            if(a[i] > a[i + 1]){
                int b = a[i];
                a[i] = a[i + 1];
                a[i + 1] = b;
            }
        }
        return a;
    }
}