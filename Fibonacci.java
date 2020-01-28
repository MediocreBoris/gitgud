import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Fibonacci{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        sc.close();
        List<Integer> b = new ArrayList<Integer>();
        b.add(0);
        b.add(1);
        fib(a, b);
        for(int i = 1; i < a + 1; i++){
            System.out.print(b.get(i) + " ");
        }
    }

    public static void fib(int a, List<Integer> b){
        for(int i = 0; i < a; i++){
            b.add(b.get(i) + b.get(i + 1));
        }
    }
}