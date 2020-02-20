extern crate num;

use std::io;
use num::pow;

fn main(){
    let mut x = String::new();
    io::stdin().read_line(&mut x).expect("Failed to read line");
    let x: Vec<i64> = x.split_whitespace()
                           .map(|s| s.parse().unwrap())
                           .collect();
    println!("{}", convert(x[0].abs(), x[1], x[0] >= 0));
}

fn convert(mut n: i64, b: i64, s: bool) -> String{
    let mut d: Vec<&str> = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("").collect();
    d.retain(|&x| x != "");
    let mut e: Vec<String> = Vec::new();
    let mut f = 0;
    loop{
        if pow(b, f) <= n{
            f += 1;
        }
        else{
            break;
        }
    }
    e.push(sign(s));
    for i in (0..f).rev(){
        let h = (n - (n % pow(b, i))) / pow(b, i);
        e.push(String::from(d[h as usize]));
        n -= pow(b, i) * h;
    }
    e.join("")
}

fn sign(b: bool) -> String{
    if b{
        return "".to_string();
    }
    "-".to_string()
}