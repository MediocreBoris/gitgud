import java.util.Scanner;

public class Brackets {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String[] b = a.split("");
        sc.close();
        brackets(b);
    }

    public static void brackets(String[] a){
        int n = 0;
        for(int i = 0; i < a.length; i++){
            if(a[i].equals("[")){
                n++;
            }
            else if(a[i].equals("]")){
                n--;
            }
            if(n < 0){
                System.out.print("Not balanced.");
                break;
            }
        }
        if(n == 0){
            System.out.print("Balanced.");
        }
        else if (n > 0){
            System.out.print("Not balanced.");
        }
    }
}