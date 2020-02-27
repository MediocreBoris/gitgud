b = {0, 9700, 39475, 84200, 160725, 204100, 513000}
t = {0.1, 0.12, 0.22, 0.24, 0.32, 0.35, 0.37}

function max(y)
    i = 0
    while true do
        i = i + 1
        if i == 7 then
            return 7
        elseif b[i + 1] > y then
            return i
        end
    end
end

function calc(n)
    i = max(n)
    z = 0
    for j = i, 1, -1 do
        z = z + t[j] * (n - b[j])
        n = b[j]
    end
    return z
end

print(calc(io.read("n")))