import java.util.Scanner;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Vigenere {
    public static void main(String[] args){
        java.util.Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();
        x = x.toUpperCase().replaceAll(" ", "");
        String[] eh = x.split("");
        sc.close();
        try{
            eh = encypher(eh);
            System.out.println(String.join("", eh));
        }
        catch(Exception IndexOutOfBoundsException){
            System.out.println("Numbers and symbols cannot be encyphered.");
        } 
    }
    
    public static String[] encypher(String[] ar){
        final List<String> v = Arrays.asList("A", "B", "C", "D", "E", "F", 
                        "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", 
                        "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
        final String[] d = new String[]{"S", "A", "M", "P", "L", "E", "T", "E", "X", "T"};
        LinkedList<String> arr = new LinkedList<String>(Arrays.asList(ar));
        for(int i = 0; i < arr.size(); i++){
            int z = v.indexOf(arr.get(i));
            if(z == -1){
                throw new IndexOutOfBoundsException();
            }
            int y = v.indexOf(d[i % d.length]);
            y += v.indexOf(arr.get(i));
            y %= 26;
            arr.set(i, v.get(y));
        }
        ar = arr.toArray(new String[0]);
        return ar;
    }
}