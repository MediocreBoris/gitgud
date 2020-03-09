function calculate(f, g)
    local a = 0;
    for i = 2:length(f)
        a = a + f[i] * g[i];
    end
    return a;
end

function zeckendorf(j, f, g, y)
    if calculate(f, g) == y
        return (g, true);
    else
        for i = j:length(f)
            g[i] = 1;
            if zeckendorf(i + 2, f, g, y)[2]
                return (g, true);
            end
            g[i] = 0;
        end
    end
    return (g, false);
end

function printer(a)
    deleteat!(a, 1);
    if a[length(a)] == 0
        deleteat!(a, length(a));
    end
    println(join(a[end:-1:1], ""));
end

global x = parse(Int64, readline());
global f = Int64[1, 1];
while f[length(f)] < x
    resize!(f, length(f) + 1);
    f[length(f)] = f[length(f) - 1] + f[length(f) - 2];
end
g = zeros(Int64, length(f));
a = zeckendorf(2, f, g, x);
b = zeckendorf(3, f, g, x);
if a[2]
    printer(a[1]);
else
    printer(b[2]);
end