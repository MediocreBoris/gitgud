use std::io;
use rand::Rng;

fn main(){
    let mut a = true;
    let mut y = [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]];
    loop{
        y = rand(&mut y);
        if a{
            for i in 0..4{
                let i = 3 - i;
                println!("{:4}  {:4}  {:4}  {:4}", y[i][0], y[i][1], y[i][2], y[i][3]);
                a = false;
            }
        }
        if over(&y){
            break;
        }
        let mut f = 0;
        let mut x = String::new();
        io::stdin().read_line(&mut x).expect("Failed to read line");
        if x == "u\r\n" || x == "up\r\n" || x == "Up\r\n"{
            f = 0;
        }
        else if x == "r\r\n" || x == "right\r\n" || x == "Right\r\n"{
            f = 1;
        }
        else if x == "d\r\n" || x == "down\r\n" || x == "Down\r\n"{
            f = 2;
        }
        else if x == "l\r\n" || x == "left\r\n" || x == "Left\r\n"{
            f = 3;
        }
        else if x == "quit\r\n"{
            break;
        }
        else{
            continue;
        }
        y = make(&mut y, f as usize);
        a = true;
    }
}

fn over(y: &[[i64; 4]; 4]) -> bool{
    let b: [[i8; 2]; 4] = [[1, 0], [0, 1], [-1, 0], [0, -1]];
    for i in 0..4{
        for j in 0..4{
            if y[i][j] == 0{
                return false;
            }
            for k in 0..4{
                if (-1 as i8) < (i as i8 + b[k][0]) && i as i8 + b[k][0] < 4{
                    if (-1 as i8) < (j as i8 + b[k][1]) && j as i8 + b[k][1] < 4{
                        if y[i][j] == y[(i as i8 + b[k][0]) as usize][(j as i8 + b[k][1]) as usize]{
                            return false;
                        }
                    }
                }
            }
        }
    }
    println!("No more possible moves! Game over!");
    true
}

fn check(y: &[[i64; 4]; 4], i: usize, f: usize, j: i8) -> bool{
    for k in 0..3 - j{
        let k = k as usize;
        if f == 0{
            if y[k][i] != y[k + 1][i]{
                return true;
            }
            if y[k][i] != 0{
                return true;
            }
        }
        else if f == 1{
            if y[i][k] != y[i][k + 1]{
                return true;
            }
            if y[i][k] != 0{
                return true;
            }
        }
        else if f == 2{
            if y[3 - k][i] != y[2 - k][i]{
                return true;
            }
            if y[3 - k][i] != 0{
                return true;
            }
        }
        else if f == 3{
            if y[i][3 - k] != y[i][2 - k]{
                return true;
            }
            if y[i][3 - k] != 0{
                return true;
            }
        }
    }
    false
}

fn mov(y: &mut [[i64; 4]; 4], i: usize, j: usize, m: i8, n: i8, f: usize){
    if f % 2 == 0{
        for k in j..3{
            let k = k as i8;
            y[(m - n * k) as usize][i] = y[(m - n * (k + 1)) as usize][i];
        }
        y[(m - 3 * n) as usize][i] = 0;
    }
    if f % 2 == 1{
        for k in j..3{
            let k = k as i8;
            y[i][(m - n * k) as usize] = y[i][(m - n * (k + 1)) as usize];
        }
        y[i][(m - 3 * n) as usize] = 0;
    }
}

fn make(y: &mut [[i64; 4]; 4], f: usize) -> [[i64; 4]; 4]{
    let g: [i8; 2] = [1, -1];
    let h: [i8; 2] = [3, 0];
    let n = g[(f - (f % 2)) / 2];
    let m = h[(f - (f % 2)) / 2];
    let mut t = true;
    for i in 0..4{
        for j in 0..3{
            let j = j as i8;
            if f % 2 == 0{
                if check(&y, i, f, j){
                    while y[(m - n * j) as usize][i] == 0{
                        mov(y, i, j as usize, m, n, f);
                    }
                    if y[(m - n * j) as usize][i] == y[(m - n * (j + 1)) as usize][i]{
                        y[(m - n * j) as usize][i] *= 2;
                        y[(m - n * (j + 1)) as usize][i] = 0;
                        mov(y, i, (j + 1) as usize, m, n, f);
                        t = false;
                        continue;
                    }
                    if j > 0 && t{
                        if y[(m - n * j) as usize][i] == y[(m - n * (j - 1)) as usize][i]{
                            y[(m - n * (j - 1)) as usize][i] *= 2;
                            y[(m - n * j) as usize][i] = 0;
                            mov(y, i, (j + 1) as usize, m, n, f);
                        }
                    }
                }
                else{
                    break;
                }
            }
            else if f % 2 == 1{
                if check(&y, i, f, j){
                    while y[i][(m - n * j) as usize] == 0{
                        mov(y, i, j as usize, m, n, f);
                    }
                    if y[i][(m - n * j) as usize] == y[i][(m - n * (j + 1)) as usize]{
                        y[i][(m - n * j) as usize] *= 2;
                        y[i][(m - n * (j + 1)) as usize] = 0;
                        mov(y, i, (j + 1) as usize, m, n, f);
                        t = false;
                        continue;
                    }
                    if j > 0 && t{
                        if y[i][(m - n * j) as usize] == y[i][(m - n * (j - 1)) as usize]{
                            y[i][(m - n * (j - 1)) as usize] *= 2;
                            y[i][(m - n * j) as usize] = 0;
                            mov(y, i, (j + 1) as usize, m, n, f);
                        }
                    }
                }
                else{
                    break;
                }
            }
        }
    }
    *y
}

fn rand(y: &mut [[i64; 4]; 4]) -> [[i64; 4]; 4]{
    let mut s = rand::thread_rng().gen_range(1, 5);
    while s > 0{
        let m = rand:: thread_rng().gen_range(0, 16);
        let mut l = 2 - (s % 2);
        s -= l;
        l *= 2;
        if y[m % 4][((m / 4) as f64).floor() as usize] == 0{
            y[m % 4][((m / 4) as f64).floor() as usize] = l;
        }
        else{
            for i in 0..16{
                if y[(m + i) % 4][(((m + i) % 16 / 4) as f64).floor() as usize] == 0{
                    y[(m + i) % 4][(((m + i) % 16 / 4) as f64).floor() as usize] = l;
                    break;
                }
            }
        }
    }
    *y
}