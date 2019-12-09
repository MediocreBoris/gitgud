import java.util.stream.IntStream;
import java.util.Scanner;

public class Sudoku {
    public static void main(String[] args) {
        System.out.print("Choose Difficulty setting: \n");
        System.out.print("0: Easy \n");
        System.out.print("1: Medium \n");
        System.out.print("2: Hard \n");
        Scanner sc = new Scanner(System.in);
        String dif = sc.next();
        sc.close();
        int[][] sudoku = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = (i * 3 + i / 3 + j) % (9) + 1;
            }
        }
        int ran = (int) (Math.random() * 30 + 20);
        for (int k = 0; k < ran; k++) {
            sudoku = shuffle(sudoku);
        }
        int[][] newsudo = remove(sudoku, dif);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(newsudo[j][i] == 0){
                    System.out.print("- ");
                }
                else{
                    System.out.print(newsudo[j][i] + " ");
                }
            }
            System.out.print("\n");
        }
    }

    private static int[][] shuffle(int[][] su) {
        //switching rows
        if (Math.random() > 0.5) {
            int av = (int) (Math.random() * 3);
            int az = (int) (Math.random() * 3);
            while (av == az) {
                az = (int) (Math.random() * 3);
            }
            int set = (int)(Math.random() * 3);
            av = 3 * set + av;
            az = 3 * set + az;
            for (int l = 0; l < 9; l++) {
                int ax = su[l][av];
                su[l][av] = su[l][az];
                su[l][az] = ax;
            }
        }
        //switching columns
        else{
            int av = (int) (Math.random() * 3);
            int az = (int) (Math.random() * 3);
            while (av == az) {
                az = (int) (Math.random() * 3);
            }
            int set = (int)(Math.random() * 3);
            av = 3 * set + av;
            az = 3 * set + az;
            for (int l = 0; l < 9; l++) {
                int ax = su[av][l];
                su[av][l] = su[az][l];
                su[az][l] = ax;
            }
        }
        return su;
    }

    private static int[][] remove(int[][] s, String hard) {
        boolean back = true;
        int x = 0;
        int f = 0;
        int z = 50;
        if(hard.equals("0")){
            z = 40;
        }
        else if(hard.equals("2")){
            z = 60;
        }
        while(back) {
            int av = (int) (Math.random() * 9);
            int az = (int) (Math.random() * 9);
            f = s[av][az];
            if(s[av][az] != 0){
                s[av][az] = 0;
                z--;
            }
            back = backtrack(0, 0, s, 0) == 1;
            if(!back){
                s[av][az] = f;
                z++;
            }
            if(!back && x <= 40){
                if(z > 0) {
                    x++;
                    back = true;
                }
            }
        }
        return s;
    }

    static int backtrack(int a, int b, int[][] sud, int count) {
        if (a == 9) {
            a = 0;
            if (++b == 9) {
                return 1 + count;
            }
        }
        if (sud[a][b] != 0) {
            return backtrack(a + 1, b, sud, count);
        }
        for (int v = 1; v <= 9 && count < 2; ++v) {
            sud[a][b] = v;
            if (valid(sud, a, b)) {
                count = backtrack(a+1, b, sud, count);
            }
            else{
                sud[a][b] = 0;
            }
        }
        sud[a][b] = 0;
        return count;
    }
    private static boolean valid(int[][] a, int b, int c) {
        return (rcon(a, b) && ccon(a, c) && scon(a, b, c));
    }

    private static boolean rcon(int[][] b, int row) {
        boolean[] con = new boolean[9];
        return IntStream.range(0, 9).allMatch(column -> constraint(b, row, con, column));
    }

    private static boolean ccon(int[][] b, int column) {
        boolean[] con = new boolean[9];
        return IntStream.range(0, 9).allMatch(row -> constraint(b, row, con, column));
    }
    private static boolean scon(int[][] b, int row, int column) {
        boolean[] con = new boolean[9];
        int rs = (row / 3) * 3;
        int re = rs + 3;
        int cs = (column / 3) * 3;
        int ce = cs + 3;

        for (int r = rs; r < re; r++) {
            for (int c = cs; c < ce; c++) {
                if (!constraint(b, r, con, c)) {
                    return false;
                }
            }
        }
        return true;
    }
    private static boolean constraint(int[][] sudo, int row, boolean[] constraint, int column) {
        if (sudo[row][column] != 0) {
            if (!constraint[sudo[row][column] - 1]) {
                constraint[sudo[row][column] - 1] = true;
            } else {
                return false;
            }
        }
        return true;
    }
}