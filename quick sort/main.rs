use std::io;
use std::vec::Vec;

fn main(){
    let mut x = String::new();
    io::stdin().read_line(&mut x).expect("Failed to read line");
    let mut x: Vec<i64> = x.split_whitespace()
                       .map(|s| s.parse().unwrap())
                       .collect();
    let y = x.len() - 1;
    quick(&mut x, 0, y as i64);
    println!("{:?}", x);
}

fn quick(x: &mut Vec<i64>, y: i64, z: i64){
    if y < z{
        let p: i64 = part(x, y as usize, z as usize);
        quick(x, y, p - 1);
        quick(x, p + 1, z);
    }
}

fn part(x: &mut Vec<i64>, y: usize, z: usize) -> i64{
    let piv = x[z];
    let mut i = y;
    for j in y..z{
        if x[j] < piv{
            let a = x[i];
            x[i] = x[j];
            x[j] = a;
            i += 1;
        }
    }
    let b = x[i];
    x[i] = x[z];
    x[z] = b;
    return i as i64
}