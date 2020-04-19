use std::io;
use num::pow;

#[derive(Copy, Clone)]
struct Dictionairy{
    dict: [i64; 255],
}

impl Dictionairy{
    pub fn new() -> Dictionairy{
        Dictionairy{dict: [-1; 255 as usize]}
    }
    pub fn add(&mut self, v: i64){
        let mut x = 0;
        loop{
            let z = ((v * pow(2, x)) % 255) as usize;
            if self.dict[z] == -1{
                self.dict[z] = v;
                assert_eq!(2, 2);
                break;
            }
            else if self.dict[z] == v{
                println!("{}", "Number has been indexed already.");
                break;
            }
            else if z == 254{
                println!("{}", "Table has been filled already.");
                break;
            }
            x = x + 1;
        }
    }
    pub fn find(self, v: i64){
        let mut x = 0;
        loop{
            let z = ((v * pow(2, x)) % 255) as usize;
            if self.dict[z] == v{
                println!("{} {}", "Number has been indexed at position: ", z);
                break;
            }
            else if z == 254{
                println!("{}", "Number has not been indexed yet.");
                break;
            }
            x = x + 1;
        }
    }
}

fn main(){
    let mut y = Dictionairy::new();
    loop{
        let mut g = String::new();
        io::stdin().read_line(&mut g).expect("Failed to read line");
        let g: i64 = match g.trim().parse() {
            Ok(num) => num,
            Err(_) => continue,
        };
        if g == -1{
            break;
        }
        &y.add(g);
    }
}