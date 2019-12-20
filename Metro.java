/*Put in the starting station in the first line and the goal station in the second.
Put in the names exactly as in this map: http://www.urbanrail.net/as/kr/busan/busan-map.png
Exception is the Kyungsung University station. Put it in as "Kyungsung University"*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

public class Metro{
    String name;
    int no;
    int ya;
    int da;
    int mi;
    int sa;
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Metro st = station(sc.nextLine());
        while(st.no == -1 && st.ya == -1 && st.da == -1 && st.mi == -1 && st.sa == -1){
            System.out.print("Starting station is invalid. Please enter a valid station\n");
            st = station(sc.nextLine());
        }
        Metro en = station(sc.nextLine());
        while(en.no == -1 && en.ya == -1 && en.da == -1 && en.mi == -1 && en.sa == -1){
            System.out.print("Goal station is invalid. Please enter a valid station\n");
            en = station(sc.nextLine());
        }
        System.out.print("\n");
        sc.close();
        boolean boo = true;
        if(st.name == en.name){
            System.out.print("You're already at the goal station");
            boo = false;
        }
        else if(st.no > -1 && en.no > -1){
            same(st.no, en.no, "nod");
        }
        else if(st.da > -1 && en.da > -1){
            same(st.da, en.da, "das");
        }
        else if(st.mi > -1 && en.mi > -1){
            same(st.mi, en.mi, "mia");
        }
        else if(st.sa > -1 && en.sa < -1){
            same(st.sa, en.sa, "sas");
        }
        else{
            List<Node> g = makeNodes(st, en);
            Node[] gg = g.toArray(new Node[g.size()]);
            for(int i = 0; i < gg.length - 1; i++){
                samesame(gg[i], gg[i + 1]);
            }
        }
        if(boo == true){
            System.out.print(en.name);
        }
    }

    public static void samesame(Node st, Node en){
        if(st.no > -1 && en.no > -1){
            same(st.no, en.no, "nod");
        }
        else if(st.ya > -1 && en.ya > -1){
            same(st.ya, en.ya, "yaj");
        }
        else if(st.da > -1 && en.da > -1){
            same(st.da, en.da, "das");
        }
        else if(st.mi > -1 && en.mi > -1){
            same(st.mi, en.mi, "mia");
        }
        else if(st.sa > -1 && en.sa > -1){
            same(st.sa, en.sa, "sas");
        }
    }

    public static List<Node> makeNodes(Metro s, Metro e){
        Node Dongnae = new Node(Metro.station("Dongnae"));
        Node Yeonsan = new Node(Metro.station("Yeonsan"));
        Node Seomyeon = new Node(Metro.station("Seomyeon"));
        Node Deokcheon = new Node(Metro.station("Deokcheon"));
        Node Sasang = new Node(Metro.station("Sasang"));
        Node Suyeong = new Node(Metro.station("Suyeong"));
        Node Daejeo = new Node(Metro.station("Daejeo"));
        Node Minam = new Node(Metro.station("Minam"));

        Dongnae.add(Yeonsan, 2);
        Dongnae.add(Minam, 1);
        Yeonsan.add(Dongnae, 2);
        Yeonsan.add(Seomyeon, 4);
        Yeonsan.add(Suyeong, 4);
        Yeonsan.add(Minam, 4);
        Seomyeon.add(Yeonsan, 4);
        Seomyeon.add(Suyeong, 11);
        Seomyeon.add(Sasang, 8);
        Deokcheon.add(Daejeo, 4);
        Deokcheon.add(Sasang, 6);
        Deokcheon.add(Minam, 4);
        Daejeo.add(Deokcheon, 4);
        Daejeo.add(Sasang, 6);
        Sasang.add(Daejeo, 6);
        Sasang.add(Seomyeon, 8);
        Sasang.add(Deokcheon, 6);
        Suyeong.add(Yeonsan, 4);
        Suyeong.add(Seomyeon, 11);
        Minam.add(Dongnae, 1);
        Minam.add(Yeonsan, 4);
        Minam.add(Deokcheon, 4);

        Graph graph = new Graph();
        graph.addNode(Dongnae);
        graph.addNode(Yeonsan);
        graph.addNode(Seomyeon);
        graph.addNode(Deokcheon);
        graph.addNode(Sasang);
        graph.addNode(Suyeong);
        graph.addNode(Daejeo);
        graph.addNode(Minam);

        Node x = new Node(Metro.station(s.name));
        Node.adda(x, graph.getnodes());
        x = check(x, graph.getnodes());
        graph.addNode(x);

        Node y = new Node(Metro.station(e.name));
        Node.addy(y, graph.getnodes());
        y = check(y, graph.getnodes());
        graph.addNode(y);

        dijkstra(graph, x);
        y.getsh().add(y);

        return y.getsh();
    }

    public static Node check(Node y, Set<Node> tem){
        for(Node no: tem){
            if(y.name.equals(no.name)){
                return no;
            }
        }
        return y;
    }

    public static void dijkstra(Graph graph, Node b){
        b.setd(0);
        Set<Node> set = new HashSet<>();
        Set<Node> unset = new HashSet<>();
        unset.add(b);
        while(unset.size() != 0){
            Node current = lowe(unset);
            unset.remove(current);
            for(Entry<Node, Integer> adjac: current.getadjac().entrySet()){
                Node adj = adjac.getKey();
                int wei = adjac.getValue();
                if(!set.contains(adj)){
                    mini(current, wei, adj);
                    unset.add(adj);
                }
            }
            set.add(current);
        }
    }

    public static void mini(Node c, int e, Node a){
        int sd = c.getd();
        if(sd + e < a.getd()){
            a.setd(sd + e);
            LinkedList<Node> path = new LinkedList<Node>(c.getsh());
            path.add(c);
            a.setsh(path);
        }
    }

    public static Node lowe(Set<Node> u){
        Node low = null;
        int lowd = Integer.MAX_VALUE;
        for(Node node: u){
            int dist = node.getd();
            if(dist < lowd){
                lowd = dist;
                low = node;
            }
        }
        return low;
    }

    public static void same(int s, int e, String f){
        if(s > e){
            for(int i = s; i > e; i--){
                System.out.print(field(f)[i] + "\n");
            }
        }
        else if(s < e){
            for(int i = s; i < e; i++){
                System.out.print(field(f)[i] + "\n");
            }
        }
    }

    public static String[] field(String in){
        String[] nod = new String[]{
            "Nopo", "Beomeosa", "Namsan", "Dusil", "Guseo", "Jangjeon",
            "Busan National University", "Oncheonjang", "Myeongnyun", "Dongnae",
            "Busan National University of Education", "Yeonsan", "City Hall",
            "Yangseong", "Bujeon", "Seomyeon", "Beomnaegol", "Beomil", "Jwacheon",
            "Busanjin", "Choryang", "Busan Station", "Dongdaesin", "Jungang",
            "Nampo", "Jagalchi", "Toseong", "Seodaesin", "Daeti", "Goejeong",
            "Saha", "Dangni", "Hadan", "Sinpyeong", "Dongmae", "Jangnim",
            "Sinjangnim", "Natgae", "Dadaepo Harbor", "Dadaepo Beach"
        };
        String[] yaj = new String[]{
            "Yangsan", "Namyangsan", "Busan National University Yangsan Campus",
            "Jeungsan", "Hopo", "Geumgok", "Dongwon", "Yulli", "Hwamyeong", "Sujeong",
            "Deokcheon", "Gumyeong", "Gunam", "Mora", "Modeok", "Deokpo", "Sasang", 
            "Gamjeon", "Jurye", "Naengjeong", "Gaegeum", "Dongeui University", "Gaya",
            "Buam", "Seomyeon", "Jeonpo", "Busan Int'l Finance Center Busan Bank",
            "Munhyeon", "Jigegol", "Motgol", "Daeyeon",  "Kyungsung University", "Namcheon",
            "Geumnyeonsan", "Gwangan", "Suyeong", "Millak", "Centum City", "BEXCO", 
            "Dongbaek", "Haeundae", "Jung-dong", "Jangsan"
        };
        String[] das = new String[]{
            "Daejeo", "Busan Sports Park", "Gangseo-gu Office", "Gupo", "Deokcheon",
            "Sugdeung", "Namsanjeong", "Mandeog", "Minam", "Sajik", "Busan Sports Complex",
            "Geoje", "Yeonsan", "Mulmangol", "Baesan", "Mangmi", "Suyeong"
        };
        String[] mia = new String[]{
            "Minam", "Dongnae", "Suan", "Nangnim", "Chungnyeolsa", "Myeongjang", "Seodong",
            "Geumsa", "Banyeo Agricultural Market", "Seokdae", "Youngsan Univ.",
            "Dong-Pusan College", "Gochon", "Anpyeong"
        };
        String[] sas = new String[]{
            "Samgye", "Hwajeong", "Yeonji Park", "Gimhae National Museum", "Royal Tomb of King Suro",
            "Bonghwang", "Buwon", "Gimhae City Hall", "Hwalcheon", "Andong", "Jinae",
            "Buram", "Daesa", "Pyeonggang", "Daejeo", "Deunggu", "Deokdu", 
            "Gimhae International Airport", "West Busan Logistics Park", 
            "Gwaebeop Renecite", "Sasang"
        };
        switch(in){
            case "nod":
                return nod;
            case "yaj":
                return yaj;
            case "das":
                return das;
            case "mia":
                return mia;
            case "sas":
                return sas;
        }
        return sas;
    }

    public static Metro station(String x){
        Metro busa = new Metro();
        busa.name = x;
        busa.no = Arrays.asList(field("nod")).indexOf(x);
        busa.ya = Arrays.asList(field("yaj")).indexOf(x);
        busa.da = Arrays.asList(field("das")).indexOf(x);
        busa.mi = Arrays.asList(field("mia")).indexOf(x);
        busa.sa = Arrays.asList(field("sas")).indexOf(x);
        return busa;
    }
}

class Node extends Metro{
    List<Node> sh = new LinkedList<>();
    Map<Node, Integer> adjac = new HashMap<>();
    int d = Integer.MAX_VALUE;

    public Node(Metro z){
        this.name = z.name;
        this.no = z.no;
        this.ya = z.ya;
        this.da = z.da;
        this.mi = z.mi;
        this.sa = z.sa;
    }

    public Map<Node, Integer> getadjac(){
        return adjac;
    }

    public List<Node> getsh(){
        return sh;
    }

    public void setsh(LinkedList<Node> g){
        sh = g;
    }

    public void add(Node dest, int dist){
        adjac.put(dest, dist);
    }

    public int getd(){
        return d;
    }

    public void setd(int h){
        d = h;
    }

    public static int d(Node x, Node y, Set<Node> tem){
        int u = Integer.MAX_VALUE;
        int v = 0;
        String lin = "b";
        if(x.no >= 0 && y.no >= 0){
            u = x.no;
            v = y.no;
            lin = "no";
        }
        else if(x.ya >= 0 && y.ya >= 0){
            u = x.ya;
            v = y.ya;
            lin = "ya";
        }
        else if(x.da >= 0 && y.da >= 0){
            u = x.da;
            v = y.da;
            lin = "da";
        }
        else if(x.mi >= 0 && y.mi >= 0){
            u = x.mi;
            v = y.mi;
            lin = "mi";
        }
        else if(x.sa >= 0 && y.sa >= 0){
            u = x.sa;
            v = y.sa;
            lin = "sa";
        }
        boolean bo = true;
        if(u < v){
            u = -u;
            v = -v;
            bo = false;
        }
        switch(lin){
            case "no":
                for(Node no: tem){
                    int g = no.no;
                    u = dpp(bo, g, u, v);
                }
                return Math.abs(u - v);
            case "ya":
                for(Node no: tem){
                    int g = no.ya;
                    u = dpp(bo, g, u, v);
                }
                return Math.abs(u - v);
            case "da":
                for(Node no: tem){
                    int g = no.da;
                    u = dpp(bo, g, u, v);
                }
                return Math.abs(u - v);
            case "mi":
                for(Node no: tem){
                    int g = no.mi;
                    u = dpp(bo, g, u, v);
                }
                return Math.abs(u - v);
            case "sa":
                for(Node no: tem){
                    int g = no.sa;
                    u = dpp(bo, g, u, v);
                }
                return Math.abs(u - v);
        }
        return Math.abs(u - v);
    }

    public static int dpp(boolean b, int g, int u, int v){
        if(g == -1){
            return u;
        }
        if(!b){
            g = -g;
        }
        if((v < u && u < g) || (v > u && u > g)){
            return u;
        }
        if(u - v > u - g){
            return Integer.MAX_VALUE;
        }
        return u;
    }

    public static void adda(Node x, Set<Node> temp){
        for(Node nope: temp){
            int f = d(x, nope, temp);
            if(f < 1000){
                x.add(nope, f);
            }
        }
    }

    public static void addy(Node y, Set<Node> temp){
        for(Node no: temp){
            int f = d(y, no, temp);
            if(f < 1000){
                no.add(y, f);
            }
        }
    }
}

class Graph {
 
    private Set<Node> nodes = new HashSet<>();
     
    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public Set<Node> getnodes(){
        return nodes;
    }
}