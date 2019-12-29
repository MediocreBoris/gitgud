use std::io;

fn main(){
    let mut x = String::new();
    io::stdin().read_line(&mut x).expect("Failed to read line");
    let mut x: Vec<i64> = x.split_whitespace()
                       .map(|s| s.parse().unwrap())
                       .collect();
    heap(&mut x);
    println!("{:?}", x);
}

fn heap(x: &mut Vec<i64>){
    let l = x.len();

    for s in (0..l / 2).rev() {
        sift(x, s, l - 1);
    }

    for e in (1..l).rev(){
        x.swap(0, e);
        sift(x, 0, e - 1);
    }
}

fn sift(x: &mut Vec<i64>, s: usize, e: usize){
    let mut r = s;
    loop{
        let mut c = r * 2 + 1;
        if c > e{
            break;
        }
        if c + 1 <= e && x[c] < x[c + 1]{
            c += 1;
        }
        if x[r] < x[c]{
            x.swap(r, c);
            r = c;
        }
        else{
            break;
        }
    }
}