use std::io;

fn main(){
    let mut x = String::new();
    io::stdin().read_line(&mut x).expect("Failed to read line");
    loop{
        let g: i64 = match x.trim().parse() {
            Ok(num) => num,
            Err(_) => continue,
        };
        let mut v: Vec<Vec<i64>> = Vec::new();
        v.push(vec![1]);
        for i in 0..g{
            v.push(pascal(&v[i as usize]));
            println!("{:?}", v[i as usize]);
        }
        break;
    }
}

fn pascal(v: &Vec<i64>) -> Vec<i64>{
    let mut a: Vec<i64> = vec![1];
    for j in 1..v.len(){
        a.push(v[j] + v[j - 1]);
    }
    a.push(1);
    a
}