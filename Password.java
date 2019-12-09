import java.util.Scanner;
import java.util.regex.Pattern;

public class Password {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String pass = sc.nextLine();
        if(pass.length() <= 32 && pass.length() >= 6){
            String special = "!@#$%^&*()_?¿¡.:,;/€|~¬#-+¨{}`";
            String s = ".*[" + Pattern.quote(special) + "].*";
            String number = "1234567890";
            String n = ".*[" + Pattern.quote(number) + "].*";
            String capital = "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÜÖÑÇ";
            String c = ".*[" + Pattern.quote(capital) + "].*";
            String minor = "abcdefghijklmnopqrstuvwxyzñäöü";
            String m = ".*[" + Pattern.quote(minor) + "].*";
            if(pass.matches(s) && pass.matches(n) && pass.matches(c) && pass.matches(m)){
                if (pass.indexOf(" ") == -1){
                    System.out.print("Password is secure.");
                }
                else if(pass.indexOf(" ") >= 0){
                    System.out.print("Password cannot contain spaces.");
                }
            }
            else{
                System.out.print("Password is not secure.");
            }
        }
        else if(pass.length() > 32){
            System.out.print("Password is too long.");
        }
        else{
            System.out.print("Password is too short.");
        }
        sc.close();
    }
}