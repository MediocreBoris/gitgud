use std::io;

fn main(){
    let mut x = String::new();
    io::stdin().read_line(&mut x).expect("Failed to read line");
    let x: Vec<i64> = x.split_whitespace()
                           .map(|s| s.parse().unwrap())
                           .collect();
    println!("{:?}", split(x))
}
fn split(x: Vec<i64>) -> bool{
    if x.len() == 1{
        return false;
    }
    let mut y = 0;
    for i in 0..x.len(){
        y += x[i];
    }
    if y % 2 != 0{
        return false;
    }
    for j in 0..x.len(){
        if calc(&x, 0, x[j], y){
            return true;
        }
    }
    false
}
fn calc(x: &Vec<i64>, n: usize, a: i64, y: i64) -> bool{
    if y == 2*a{
        return true;
    }
    for i in n + 1..x.len(){
        let m = a + x[i];
        if y >= 2*m{
            if calc(x, i, m, y){
                return true;
            }
        }
    }
    false
}