/*Does not support names. Only works via matching integers with each other. */

use std::io;
use std::vec::Vec;

#[derive(Clone)]
struct Num{
    own : i64,
    par : i64,
    pre : Vec<i64>,
    ord : i64
}

impl Num{
    pub fn new(o: i64, p: &Vec<i64>) -> Num{
        Num{own: o, par: -1, pre: p.to_vec(), ord: 0}
    }
}

fn main(){
    let mut f: Vec<Vec<i64>> = Vec::new();
    let mut g: Vec<Vec<i64>> = Vec::new();
    println!("Type in set A. Type quit to move on.");
    let mut a = false;
    loop{
        let mut x = String::new();
        io::stdin().read_line(&mut x).expect("Failed to read line");
        let ch = x.chars().next().unwrap();
        if ch.is_alphabetic(){
            if a{
                break;
            }
            a = true;
            println!("Type in set B. Type start to move on");
            continue;
        }
        let y: Vec<i64> = x.split_whitespace()
                            .map(|s| s.parse().unwrap())
                            .collect();
        if a{
            g.push(y);
            continue;
        }
        f.push(y);
    }
    if check(&f, &g){
        let f = conv(&mut f, &mut g);
        let g = f.1;
        let f = f.0;
        solve(f, g);
    }
    else{
        println!("Input is invalid!")
    }
}

fn solve(mut f: Vec<Num>, mut g: Vec<Num>){
    loop{
        for i in 0..f.len(){
            if f[i].par == -1{
                let k = search(&g, f[i].pre[f[i].ord as usize]);
                f[i].ord += 1;
                if g[k].par == -1{
                    g[k].par = f[i].own;
                    f[i].par = g[k].own;
                    continue;
                }
                for j in 0..g.len(){
                    if g[k].pre[j] == g[k].par{
                        break;
                    }
                    else if g[k].pre[j] == f[i].own{
                        let l = search(&f, g[k].par);
                        f[l].par = -1;
                        g[k].par = f[i].own;
                        f[i].par = g[k].own;
                        break;
                    }
                }
            }
        }
        let mut a = true;
        for i in 0..f.len(){
            if f[i].par == -1 || g[i].par == -1{
                a = false;
                break;
            }
        }
        if a{
            break;
        }
    }
    for i in 0..f.len(){
        println!("{:?} --> {:?}", f[i].own, f[i].par);
    }
}

fn search(g: &Vec<Num>, f: i64) -> usize{
    for i in 0..g.len(){
        if g[i].own == f{
            return i;
        }
    }
    0
}

fn conv(f: &mut Vec<Vec<i64>>, g: &mut Vec<Vec<i64>>) -> (Vec<Num>, Vec<Num>){
    let mut fb: Vec<Num> = Vec::new();
    let mut gb: Vec<Num> = Vec::new();
    for i in 0..f.len(){
        let fa = f[i].remove(0);
        let ga = g[i].remove(0);
        fb.push(Num::new(fa, &f[i]));
        gb.push(Num::new(ga, &g[i]));
    }
    (fb, gb)
}

fn check(f: &Vec<Vec<i64>>, g: &Vec<Vec<i64>>) -> bool{
    if g.len() != f.len(){
        return false;
    }
    for i in 0..f.len(){
        if f.len() != f[i].len() - 1{
            return false;
        }
        else if g.len() != g[i].len() - 1{
            return false;
        }
    }
    true
}