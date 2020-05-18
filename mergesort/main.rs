use std::io;

fn main(){
    let mut x = String::new();
    io::stdin().read_line(&mut x).expect("Failed to read line");
    let mut x: Vec<i64> = x.split_whitespace()
                       .map(|s| s.parse().unwrap())
                       .collect();
    sort(&mut x);
    println!("{:?}", x);
}

fn sort(x: &mut Vec<i64>){
    if x.len() == 1{
        return;
    }
    let mut a: Vec<i64> = copy(x, 0, i64::abs(x.len() as i64 / 2));
    let mut b: Vec<i64> = copy(x, i64::abs(x.len() as i64 / 2), x.len() as i64);
    sort(&mut a);
    sort(&mut b);
    let mut y: Vec<i64> = Vec::new();
    let mut an = 0;
    let mut bn = 0;
    while an < a.len() && bn < b.len(){
        if a[an] < b[bn]{
            y.push(a[an]);
            an = an + 1;
        }
        else{
            y.push(b[bn]);
            bn = bn + 1;
        }
    }
    if an < a.len(){
        let al = a.len() as i64;
        let mut am = copy(&mut a, an as i64, al);
        y.append(&mut am);
    }
    else if bn < b.len(){
        let bl = b.len() as i64;
        let mut bm = copy(&mut b, bn as i64, bl);
        y.append(&mut bm);
    }
    for i in 0..x.len(){
        x[i] = y[i];
    }
}

fn copy(x: &mut Vec<i64>, n: i64, m: i64) -> Vec<i64>{
    let mut y: Vec<i64> = Vec::new();
    for i in n..m{
        y.push(x[i as usize]);
    }
    return y
}