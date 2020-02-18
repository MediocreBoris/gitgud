import java.util.Scanner;

public class Determinant{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] eh = sc.nextLine().split(" ");
        int[] ar = new int[eh.length];
        for(int i = 0; i < eh.length; i++) {
            ar[i] = Integer.parseInt(eh[i]);
        }
        int[][] z = new int[ar.length][ar.length];
        z[0] = ar;
        for(int i = 1; i < z.length; i++){
            String[] e = sc.nextLine().split(" ");
            for(int j = 0; j < eh.length; j++) {
                z[i][j] = Integer.parseInt(e[j]);
            }
        }
        sc.close();
        System.out.println(determine(z));
    }

    public static int determine(int[][] x){
        int[] g = new int[]{1, -1};
        if(x.length == 1){
            return x[0][0];
        }
        int z = 0;
        for(int i = 0; i < x.length; i++){
            z = z + determine(create(x, i)) * x[0][i] * g[i % 2];
        }
        return z;
    }

    public static int[][] create(int[][] x, int i){
        int[][] a = new int[x.length - 1][x.length - 1];
        for(int j = 1; j < x.length; j++){
            int f = 0;
            for(int k = 0; k < x.length; k++){
                if(k == i){
                    f = -1;
                    continue;
                }
                a[j - 1][k + f] = x[j][k];
            }
        }
        return a;
    }
}