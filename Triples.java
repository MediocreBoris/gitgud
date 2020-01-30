import java.util.Scanner;

public class Triples {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        sc.close();
        for(int i = 0; i < a; i++){
            for(int j = 0; j < i; j++){
                if(i*i + j*j == a*a){
                    System.out.print(a + "^2 = " + i +"^2 + " + j + "^2");
                    int b = reduce(a, i, j);
                    if(b != 0){
                        System.out.print(" => " + a/b + "^2 = " + i/b + "^2 + " + j/b + "^2");
                    }
                    System.out.print("\n");
                }
            }
        }
    }

    public static int reduce(int a, int i, int j){
        int n = 0;
        for(int k = 2; k <= 0.5 * j; k++){
            if(j % k == 0 && i % k == 0 && a % k == 0){
                n = k;
            }
        }
        return n;
    }
}