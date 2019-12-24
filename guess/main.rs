use std::io;
use std::cmp::Ordering;
use rand::Rng;

//Random test project I made from Rust course, so I can get to learn it a little
fn main(){
    let s = rand::thread_rng().gen_range(1, 101);
    let mut a = 1;
    loop{
        let mut g = String::new();
        io::stdin().read_line(&mut g).expect("Failed to read line");
        let g: u32 = match g.trim().parse() {
            Ok(num) => num,
            Err(_) => continue,
        };
        println!("You guessed: {}", g);
        match g.cmp(&s){
            Ordering::Less => println!("It's too small"),
            Ordering::Equal => {
                println!("You guessed correctly");
                break;
            }
            Ordering::Greater => println!("It's too big"),
        }
        a += 1;
    }
    println!("Your score is {}", a);
}