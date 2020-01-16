use std::io;

fn main(){
    let mut x = String::new();
    io::stdin().read_line(&mut x).expect("Failed to read line");
    let x: Vec<char> = x.chars().collect();
    println!("Input number.");
    let mut g = String::new();
    loop{
        io::stdin().read_line(&mut g).expect("Failed to read line");
        let g: u32 = match g.trim().parse() {
            Ok(num) => num,
            Err(_) => continue,
        };
        let mut x = cipher(x, g);
        x.pop();
        x.pop();
        println!("{:?}", x);
        break;
    }
}

fn cipher(mut x: Vec<char>, g: u32) -> String{
    let a: Vec<char> = "ABCDEFGHIJKLMONPQRSTUVWXYZ".chars().collect();
    for i in 0..x.len(){
        for j in 0..a.len(){
            if x[i] == a[j]{
                let c = (j + g as usize) % 26;
                x[i] = a[c];
                break;
            }
        }
    }
    let mut i = String::new();
    for j in 0..x.len(){
        i.push(x[j]);
    }
    i
}