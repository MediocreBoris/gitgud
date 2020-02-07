import java.util.Scanner;

class Path{
    static String[][] result = new String[][]{{"A"}};

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String[] x = sc.nextLine().split(" ");
        String[][] y = new String[1][x.length];
        y[0] = x;
        while(true){
            String s = sc.nextLine();
            if(s.equals("quit")){
                break;
            }
            String[] t = s.split(" ");
            if(t.length != y[0].length){
                throw new Exception();
            }
            y = redo(y, t);
        }
        sc.close();
        int[] a = new int[]{y.length * y[0].length};
        solve(y, result(y), 0, 0, 1, a);
        if(result[0][0] == "A"){
            System.out.print("Maze is unsolvable!");
        }
        else{
            y = result;
            for(int i = 0; i < result.length; i++){
                for(int j = 0; j < result[0].length; j++){
                    System.out.print(result[i][j] + "  ");
                }
                System.out.print("\n");
            }
        }
    }
    public static void solve(String[][] z, String[][] s, int x, int y, int m, int[] n){
        int[][] d = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        if(n[0] == z.length + z[0].length - 1 || m > n[0]){
            return;
        }
        s[x][y] = "" + m;
        if(x == z.length - 1 && y == z[0].length - 1){
            if(m < n[0]){
                result = result(s);
                n[0] = m;
                s[x][y] = "0";
            }
            return;
        }
        for(int i = 0; i < 4; i++){
            if(x + d[i][0] < z.length && -1 < x + d[i][0]){
                if(y + d[i][1] < z[0].length && -1 < y + d[i][1]){
                    if(s[x + d[i][0]][y + d[i][1]].equals("0")){
                        solve(z, s, x + d[i][0], y + d[i][1], m + 1, n);
                    }
                }
            }
        }
        s[x][y] = "0";
    }

    public static String[][] result(String[][] s){
        String[][] x = new String[s.length][s[0].length];
        for(int i = 0; i < s.length; i++){
            for(int j = 0; j < s[0].length; j++){
                x[i][j] = s[i][j];
            }
        }
        return x;
    }
    public static String[][] redo(String[][] y, String[] t){
        String[][] x = new String[y.length + 1][y[0].length];
        for(int i = 0; i < y.length; i++){
            x[i] = y[i];
        }
        x[y.length] = t;
        return x;
    }
}