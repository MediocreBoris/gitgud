import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PokerHand{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] x = sc.nextLine().split(" ");
        String a = "";
        sc.close();
        if(invalid(x)){
            System.out.print("Invalid");
            return;
        }
        if(flush(x)){
            a = "Flush";
        }
        if(straight(x)){
            if(a.equals("Flush")){
                a = "Straight Flush";
            }
            else{
                a = "Straight";
            }
        }
        List<Integer> b = pair(x);
        if(b.indexOf(4) != -1){
            a = "Four of a kind";
        }
        else if(b.indexOf(3) != -1 && b.indexOf(2) != -1){
            a = "Full House";
        }
        else if(a == ""){
            if(b.indexOf(3) != -1){
                a = "Three of a kind";
            }
            else if(b.indexOf(2) != -1){
                if(b.size() == 3){
                    a = "Two pair";
                }
                else if(b.size() == 4){
                    a = "One pair";
                }
                else{
                    a = "High card";
                }
            }
        }
        System.out.print(a);
    }

    public static List<Integer> pair(String[] x){
        List<Integer> d = new LinkedList<>();
        List<Integer> b = new LinkedList<>();
        for(int i = 0; i < 5; i++){
            if(d.indexOf(i) != -1){
                continue;
            }
            int f = 1;
            String[] a = x[i].split("");
            for(int j = i + 1; j < 5; j++){
                String[] c = x[j].split("");
                if(a[a.length - 1].equals(c[c.length - 1])){
                    d.add(j);
                    f = f + 1;
                }
            }
            b.add(f);
        }
        return b;
    }

    public static boolean straight(String[] x){
        String[] c = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "0", "J", "Q", "K"};
        int y = 5;
        for(int i = 0; i < 13; i++){
            boolean e = false;
            if(y == 0){
                return true;
            }
            for(int j = 0; j < 5; j++){
                String[] a = x[j].split("");
                if(c[i].equals(a[a.length - 1])){
                    y = y - 1;
                    e = true;
                    break;
                }
            }
            if(y != 5 && !e){
                return false;
            }
        }
        return false;
    }

    public static boolean invalid(String[] x){
        for(int i = 0; i < 5; i++){
            for(int j = i + 1; j < 5; j++){
                if(x[i].equals(x[j])){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean flush(String[] x){
        for(int i = 0; i < 4; i++){
            String[] a = x[i].split("");
            if(!a[0].equals(x[i + 1].split("")[0])){
                return false;
            }
        }
        return true;
    }
}