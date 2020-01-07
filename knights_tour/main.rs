#[derive(Copy, Clone)]
struct Point{
    row: i8,
    column: i8,
}

impl Point{
    pub fn new(r: i8, c: i8) -> Point{
        Point{row: r,
              column: c}
    }

    pub fn empty() -> Point{
        Point{row: -1, column: -1}
    }
}

struct Graph{
    start: Point,
    end: Point,
    rank: u8
}

impl Graph{
    pub fn new(s: Point, e: Point, r: u8) -> Graph{
        Graph{start: s,
              end: e,
              rank: r}
    }
}

#[derive(Clone, Copy)]
struct Node{
    point: Point,
    num: u8,
    graphs: [Point; 8]
}

impl Node{
    pub fn new(p: Point) -> Node{
        Node{point: p,
             num: 0,
             graphs: [Point::empty(), Point::empty(), 
                      Point::empty(), Point::empty(), 
                      Point::empty(), Point::empty(), 
                      Point::empty(), Point::empty()]}
    }
}

fn main(){
    let mut b = points();
    solve(&mut b, 0 as usize, 1);
    printing(b);
}

fn printing(b: Vec<Node>){
    for i in 0..8{
        let i = i * 8;
        println!("{:02} {:02} {:02} {:02} {:02} {:02} {:02} {:02}",
                  b[i].num, b[i+1].num, b[i+2].num, b[i+3].num,
                  b[i+4].num, b[i+5].num, b[i+6].num, b[i+7].num)
    }
}

fn solve(b: &mut Vec<Node>, x: usize, n: u8) -> bool{
    let c = false;
    b[x].num = n;
    for i in 0..8{
        if b[x].graphs[i].row != -1{
            let a = find(b[x].graphs[i], b);
            if b[a].num != 0{
                continue;
            }
            let c = solve(b, a, n + 1);
            if c{
                break;
            }
        }
    }

    if !c{
        let c = confirm(b);
        if !c{
            b[x].num = 0;
        }
    }
    c
}

fn confirm(b: &mut Vec<Node>) -> bool{
    for i in 0..64{
        if b[i].num == 0{
            return false;
        }
    }
    true
}

fn find(p: Point, b: &mut Vec<Node>) -> usize{
    let v = 0;
    for i in 0..64{
        if equals(b[i].point, p){
            return i;
        }
    }
    v
}

fn graphing(b: [Point; 64]) -> Vec<Node>{
    let mut x = Vec::new();
    let f = [1, 2, 2, 1];
    let l = [-2, -1, 1, 2];
    for i in 0..64{
        for k in 0..4{
            let i = i as usize;
            let k = k as usize;
            if 0 <= b[i].row + f[k] && b[i].row + f[k] <= 7{
                if 0 <= b[i].column + l[k] && b[i].column + l[k] <= 7{
                    let p = Point::new(b[i].row + f[k], b[i].column + l[k]);
                    let g = Graph::new(b[i], p, k as u8);
                    x.push(g);
                }
            }
        }
    }
    return nodes(b, x);
}

fn nodes(b: [Point; 64], x: Vec<Graph>) -> Vec<Node>{
    let mut f = Vec::new();
    for i in 0..64{
        f.push(Node::new(b[i]))
    }
    for v in x{
        for i in 0..64{
            if equals(f[i as usize].point, v.start){
                f[i as usize].graphs[v.rank as usize] = v.end;
            }
            else if equals(f[i].point, v.end){
                f[i as usize].graphs[(v.rank + 4) as usize] = v.start;
            }
        }
    }
    return f;
}

fn points() -> Vec<Node> {
    let mut b = [Point::empty(); 64];
    for i in 0..8{
        for j in 0..8{
            b[8 * i + j].row = i as i8;
            b[8 * i + j].column = j as i8;
        }
    }
    return graphing(b);
}

fn equals(a: Point, b: Point) -> bool{
    if a.row == b.row && a.column == b.column{
        return true;
    }
    false
}
