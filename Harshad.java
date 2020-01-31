import java.util.Scanner;

public class Harshad{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        sc.close();
        harshad(a);
    }

    public static void harshad(int a){
        int n = 0;
        int m = 1;
        while(n < a){
            int i = make(m);
            if(m % i == 0){
                System.out.print(m + "\n");
                n++;
            }
            m++;
        }
    }

    public static int make(int m){
        int j = 0;
        for(int i = 1; Math.pow(10, i - 1) <= m; i++){
            j = j + (int)(((m % Math.pow(10, i))) / Math.pow(10, i - 1));
            m = (int)(m - (m % Math.pow(10, i)));
        }
        return j;
    }
}