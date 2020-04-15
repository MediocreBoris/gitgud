import java.util.Scanner;
import java.util.Arrays;

public class Insertion{
    public static void main(String[] args){
        java.util.Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();
        String[] eh = x.split(" ");
        int n = eh.length;
        eh = x.split(" ");
        int[] ar = new int[n];
        for (int i = 0; i < n; i++) {
            ar[i] = Integer.parseInt(eh[i]);
        }
        sc.close();
        insert(ar);
        System.out.println(Arrays.toString(ar));
    }

    public static void insert(int[] a){
        int g = 0;
        boolean b = true;
        while(b){
            b = false;
            for(int i = g; i < a.length; i++){
                if(a[i] < a[g]){
                    b = true;
                    int f = a[i];
                    a[i] = a[g];
                    a[g] = f;
                }
            }
            g = g + 1;
        }
    }
}