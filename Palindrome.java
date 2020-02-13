import java.util.Scanner;

public class Palindrome{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] x = sc.nextLine().split("");
        sc.close();
        for(int i = 0; i < x.length; i++){
            if(!x[i].equals(x[x.length - 1 - i])){
                System.out.print("Not a palindrome!");
                break;
            }
            if(i == x.length - 1){
                System.out.print("Palindrome!");
            }
        }

    }
}