use std::io;

fn main(){
    let mut b = String::new();
    io::stdin().read_line(&mut b).expect("Failed to read input.");
    let b: Vec<&str> = b.split("").collect();
    brackets(b)
}

fn check(x: &str, d: &str) -> bool{
    let e = [["(", ")"], ["{", "}"], ["<", ">"], ["[", "]"]];
    for i in 0..4{
        if d == e[i][0] && x == e[i][1]{
            return false;
        }
    }
    true
}

fn brackets(b: Vec<&str>){
    let mut d: Vec<&str> = Vec::new();
    for i in 0..b.len(){
        if b[i] == "(" || b[i] == "[" || b[i] == "{" || b[i] == "<"{
            d.push(b[i]);
        }
        else if b[i] == ")" || b[i] == "]" || b[i] == "}" || b[i] ==">"{
            if d.len() == 0{
                println!("Brackets are uneven!");
                return;
            }
            if check(b[i], d[d.len() - 1]){
                println!("Brackets are uneven!");
                return;
            }
            else{
                d.pop();
            }
        }
    }
    if d.len() == 0{
        println!("Brackets are even!");
    }
    else{
        println!("Brackets are uneven!");
    }
}