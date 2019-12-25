use std::io;
use std::vec::Vec;

fn main(){
    let mut x = String::new();
    io::stdin().read_line(&mut x).expect("Failed to read line");
    let mut c: Vec<char> = x.chars().collect();
    let a = "!$%&/()=?¿¡'|@#~¬^*:;_`+´.,-{}[]·";
    let a: Vec<char> = a.chars().collect();
    for ah in a{
        c.retain(|&ab| ab != ah);
    }
    let a: String = c.into_iter().collect();
    println!("{}", a);
}