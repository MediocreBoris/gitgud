import java.util.Scanner;
import java.util.Arrays;

public class Bubble{
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
        bubble(ar);
        System.out.println(Arrays.toString(ar)); 
    }
    private static int[] bubble(int[] arr){
        int b;
        boolean c = false;
        int x = 0;
        while (!c){
            c = true;
            for(int i = 0; i < arr.length-x-1; i++){
                if(arr[i] > arr[i+1]){
                    b = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = b;;
                    c = false;
                }
                x++;
            }
        }
        return arr;
    }
}