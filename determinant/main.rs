use std::io;

fn main(){
    let mut y: Vec<Vec<i64>> = Vec::new();
    loop{
        let mut x = String::new();
        io::stdin().read_line(&mut x).expect("Failed to read line");
        let x: Vec<i64> = x.split_whitespace()
                           .map(|s| s.parse().unwrap())
                           .collect();
        if y.len() != 0{
            if x.len() != y[0].len(){
                panic!("Invalid number of scalars!");
            }
        }
        y.push(x);
        if y.len() == y[0].len(){
            break;
        }
    }
    if y.len() == 1{
        println!("{:?}", y[0][0]);
    }
    else{
        println!("{:?}", determine(&y));
    }
}

fn determine(y: &Vec<Vec<i64>>) -> i64{
    if y.len() == 2{
        return y[0][0] * y[1][1] - y[0][1] * y[1][0];
    }
    let mut s: i64 = 0;
    for i in 0..y.len(){
        let n = (-1 as i64).pow(i as u32);
        let mut z: Vec<Vec<i64>> = Vec::new();
        for j in 1..y.len(){
            let mut w: Vec<i64> = Vec::new();
            for k in 0..y.len(){
                if k == i{
                    continue;
                }
                w.push(y[j][k]);
            }
            z.push(w);
        }
        s += determine(&z) * y[0][i] * n;
    }
    s
}