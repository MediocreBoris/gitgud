use std::io;

fn main(){
    let mut g = String::new();
    io::stdin().read_line(&mut g).expect("Failed to read line");
    loop{
        let g: u128 = match g.trim().parse() {
            Ok(num) => num,
            Err(_) => continue,
        };
        let mut f: Vec<u128> = vec![0, 1];
        for i in 1..g{
            f.push(f[i as usize] + f[(i - 1) as usize]);
        }
        f.remove(0);
        println!("{:?}", f);
        break;
    }
}