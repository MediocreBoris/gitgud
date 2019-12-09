import java.util.Scanner;
import java.util.Arrays;

public class Quick{
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
        quick(ar, 0, ar.length - 1);
        System.out.println(Arrays.toString(ar)); 
    }
    private static int[] quick(int[] arr, int mi, int ma){
        if(mi < ma){
            int p = part(arr, mi, ma);
            quick(arr, mi, p - 1);
            quick(arr, p + 1, ma);
        }
        return arr;
    }
    private static int part(int[] a, int in, int ax){
        int piv = a[ax];
        int i = in;
        for(int j = in; j < ax; j++){
            if(a[j] < piv){
                int x = a[i];
                a[i] = a[j];
                a[j] = x;
                i++;
            }
        }
        int y = a[i];
        a[i] = a[ax];
        a[ax] = y;
        return i;
    }
}