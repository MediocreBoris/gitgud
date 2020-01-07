import java.util.Scanner;
import java.util.Arrays;

public class TFGame{
    static String[] sym = {"-", "+", "/", "*"};
    static int[] ab = {0, 0, 0, 0};
    public static void main(String[] args){
        int[] x = generate();
        shuffle(x);
        for(int i = 0; i < 4; i++){
            System.out.print(x[i] + " ");
        }
        System.out.print("\n");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String [] t = s.split("");
        sc.close();
        try{
            if(prove(t)){
                if(same(ab, x)){
                    System.out.print("Correct!");
                }
                else{
                    System.out.print("Input is invalid!");
                }
            }
            else{
                System.out.print("Wrong!");
            }
        }
        catch(Exception e){
            System.out.print("Input is invalid!");
        }
    }

    public static boolean same(int[] t, int[] x){
        for(int i = 3; i >= 0; i--){
            for(int j = 0; j < t.length; j++){
                if(t[i] == x[j]){
                    int[] h = new int[x.length - 1];
                    coph(x, h, j);
                    x = h;
                    break;
                }
            }
        }
        if(x.length != 0){
            return false;
        }
        return true;
    }

    public static void shuffle(int[] x){
        int[] y = new int[20];
        int[] z = new int[20];
        for(int i = 0; i < 20; i++){
            y[i] = (int)(Math.random() * 4);
            z[i] = (int)(Math.random() * 4);
        }
        for(int j = 0; j < 20; j++){
            int a = x[y[j]];
            x[y[j]] = x[z[j]];
            x[z[j]] = a;
        }
    }

    public static boolean prove(String[] t){
        t = remove(t, " ");
        t = remove(t, ")");
        boolean[] f = parenth(t);
        t = remove(t, "(");
        int[] a = new int[4];
        for(int i = 0; i < 4; i++){
            a[i] = Integer.parseInt(t[2 * i]);
        }
        ope[] b = new ope[3];
        for(int j = 0; j < 3; j++){
            ope x = new ope();
            x.op = Arrays.asList(sym).indexOf(t[2 * j + 1]);
            if(f[j]){
                x.setpre(50);
            }
            else{
                x.setpre(x.op);
            }
            b[j] = x;
        }
        ab = a;
        return calc(b[0], b[1], b[2], a);
    }

    public static boolean[] parenth(String[] t){
        boolean[] f = new boolean[3];
        if(t[0].equals("(")){
            f[0] = true;
            if(t[5].equals("(")){
                f[2] = true;
            }
        }
        else if(t[2].equals("(")){
            f[1] = true;
        }
        else if(t[4].equals("(")){
            f[2] = true;
        }
        return f;
    }

    public static String[] remove(String[] t, String x){
        for(int i = 0; i < t.length; i++){
            if(t[i].equals(x)){
                System.arraycopy(t, i + 1, t, i, t.length - 1 - i);
                String[] g = new String[t.length - 1];
                copy(t, g);
                t = g;
                i -= 1;
            }
        }
        return t;
    }

    public static void copy(Object[] t, Object[] g){
        for(int i = 0; i < g.length; i++){
            g[i] = t[i];
        }
    }

    public static void copa(Object[] t, Object[] g, int a){
        int j = 0;
        for(int i = 0; i < g.length + 1; i++){
            if (i == a){
                continue;
            }
            g[j] = t[i];
            j++;
        }
    }

    public static void copi(int[] t, int[] g){
        for(int i = 0; i < g.length; i++){
            g[i] = t[i];
        }
    }

    public static void coph(int[] t, int[] g, int a){
        int j = 0;
        for(int i = 0; i < g.length + 1; i++){
            if (i == a){
                continue;
            }
            g[j] = t[i];
            j++;
        }
    }

    public static int[] generate(){
        boolean z = false;
        int[] x = new int[4];
        while(!z){
            for(int i = 0; i < 4; i++){
                x[i] = (int)(Math.random() * 8 + 1);
            }
            z = correct(x);
        }
        return x;
    }

    public static boolean correct(int[] x){
        int[][] z = new int[][]{{0, 0, 0}, {4, 0, 0}, {0, 4, 0},
                                {0, 0, 4}, {4, 0, 4}};
        for(int f = 0; f < 5; f++){
            for(int i = 0; i < 4; i++){
                ope io = new ope();
                io.op = i % 4;
                io.setpre(i + z[f][0]);
                for(int j = 0; j < 4; j++){
                    ope jo = new ope();
                    jo.op = j % 4;
                    jo.setpre(j + z[f][1]);
                    for(int k = 0; k < 4; k++){
                        ope ko = new ope();
                        ko.op = k % 4;
                        ko.setpre(k + z[f][2]);
                        if(calc(io, jo, ko, x) == true){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean calc(ope i, ope j, ope k, int[] y){
        int[] x = new int[4];
        for(int z = 0; z < y.length; z++){
            x[z] = y[z];
        }
        ope[] ar = new ope[]{i, j, k};
        for(int m = 3; m >= 0; m--){
            for(int n = 0; n < ar.length; n++){
                if(ar[n].pre == m){
                    int a = conv(x[n], x[n + 1], ar[n].op);
                    x = su(x, a, n);
                    ope[] h = new ope[ar.length - 1];
                    copa(ar, h, n);
                    ar = h;
                    n--;
                }
            }
        }
        if(x[0] == 24){
            return true;
        }
        return false;
    }

    public static int[] su(int[] x, int a, int n){
        int[] b = new int[x.length - 1];
        x[n] = a;
        for(int i = n + 1; i < x.length - 1; i++){
            x[i] = x[i + 1];
        }
        copi(x, b);
        return b;
    }

    public static int conv(int a, int b, int c){
        int d = Integer.MAX_VALUE;
        switch(c){
            case 0:
                d = a - b;
                break;
            case 1:
                d = a + b;
                break;
            case 2:
                if(a == 0){
                    d = 0;
                    break;
                }
                else if(b == 0){
                    d = Integer.MAX_VALUE;
                }
                else if(b % a == 0){
                    d = (int)(a / b);
                }
                break;
            case 3:
                d = a * b;
                break;
        }
        return d;
    }
}

class ope{
    int op = 0;
    int pre;

    public void setpre(int i){
        if(i >= 4){
            pre += 2;
        }
        else if(i >= 2){
            pre += 1;
        }
    }
}