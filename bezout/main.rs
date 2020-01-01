extern crate num;
use std::io;
use num::Integer;

fn main(){
    let mut a = String::new();
    let mut b = String::new();
    io::stdin()
        .read_line(&mut a)
        .expect("Failed to read input.");
    let a = a.trim().parse::<i64>().expect("Invalid input");
    io::stdin()
        .read_line(&mut b)
        .expect("Failed to read input.");
    let b = b.trim().parse::<i64>().expect("Invalid input");
    bezout(a, b, a.gcd(&b));
}

fn bezout(a: i64, b: i64, d: i64){
    let f = (b / d).abs();
    let g = (a / d).abs();
    let mut x = -f;
    while x <= f{
        let mut y = g;
        while y >= -g{
            if a * x + b * y == d{
                println!("{:?} * {:?} + {:?} * {:?} = {:?}", a, y, b, x, d);
            }
            y -= 1;
        }
        x += 1;
    }
}
