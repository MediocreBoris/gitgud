global x = parse(Int64, readline());
global i = 0;
while x > 1
    global x = trunc(Int, ifelse(iseven(x), x / 2, 3 * x + 1));
    global i += 1;
    println(join(Int64[i, x], " ==> "));
end