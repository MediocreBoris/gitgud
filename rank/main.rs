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
    if determine(&y) != 0{
        println!("The rank of the matrix is {:?}", y.len());
    }
    else{
        for i in (0..y.len()).rev(){
            if minor(&y, i as i64){
                println!("The rank of the matrix is {:?}", i);
                break;
            }
        }
    }
}

fn minor(y: &Vec<Vec<i64>>, n: i64) -> bool{
    if n == 0{
        return true;
    }
    else{
        let s = permutate(0, y.len() as i64, n);
        for i in 0..s.len(){
            for j in 0..s.len(){
                if determine(&minorize(&y, &s[i], &s[j])) != 0{
                    return true;
                }
            }
        }
    }
    false
}

fn minorize(y: &Vec<Vec<i64>>, s: &Vec<i64>, t: &Vec<i64>) -> Vec<Vec<i64>>{
    let mut z: Vec<Vec<i64>> = Vec::new();
    let mut k: [i64; 2] = [s.len() as i64 - 1, s.len() as i64 - 1];
    for i in 0..y.len(){
        if i == s[k[0] as usize] as usize{
            k[0] -= 1;
            let mut f: Vec<i64> = Vec::new();
            for j in 0..y.len(){
                if j == t[k[1] as usize] as usize{
                    k[1] -= 1;
                    f.push(y[i][j]);
                    if k[1] == -1{
                        break;
                    }
                }
            }
            z.push(f);
            if k[0] == -1{
                break;
            }
            k[1] = s.len() as i64 - 1;
        }
    }
    z
}

fn permutate(i: i64, n: i64, m: i64) -> Vec<Vec<i64>>{
    let mut z: Vec<Vec<i64>> = Vec::new();
    if m == 1{
        for j in i..n{
            z.push(vec!(j));
        }
    }
    else{
        for j in i + 1..n + 1{
            let mut h = permutate(j, n, m - 1);
            for k in 0..h.len(){
                h[k].push(j - 1);
                let mut g: Vec<i64> = Vec::new();
                for l in 0..h[k].len(){
                    g.push(h[k][l]);
                }
                z.push(g);
            }
        }
    }
    z
}

//* Deprecated method.
//fn index(s: &Vec<i64>, i: i64) -> bool{
//   for j in 0..s.len(){
//       if s[j] == i{
//            return true;
//        }
//    }
//    false
//
//}

fn determine(y: &Vec<Vec<i64>>) -> i64{
    if y.len() == 1{
        return y[0][0];
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