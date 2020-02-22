function add(s, p)
    for i = 0, math.max(#p, #s) do
        if p[i] == nil then
            p[i] = 0
        elseif s[i] == nil then
            s[i] = 0
        end
        p[i] = s[i] + p[i]
    end
end

function new(p)
    for i = 1, #p do
        p[i - 1] = p[i]
    end
    p[#p] = nil
    return p
end

function multiply(p, s)
    z = {}
    for i = 0, #p + #s do
        z[i] = 0
    end
    for i = 0, #p do
        for j = 0, #s do
            z[i + j] = z[i + j] + s[j] * p[i]
        end
    end
    return z
end

function calculate(p, x)
    h = 0
    for i = 0, #p do
        h = h + p[i] * x^(i)
    end
    return h
end

function split(s, d)
    r = {};
    for m in (s..d):gmatch("(.-)"..d) do
        if m == "quit" then
            return m
        end
        r[#r + 1] = tonumber(m);
    end
    return r;
end

function conv(j)
    if j == 1 then
        return " * x"
    end
    return table.concat({" * x^", j}, "")
end

function make(n, m)
    l = new({-n[1][1], 1})
    for i = 2, #n do
        l = multiply(new({-n[i][1], 1}), l)
    end
    l = multiply(new({m[2] / calculate(l, m[1])}), l)
    return l
end

function tabl(n, i)
    m = {}
    for j = 1, #n do
        if j == i then
            goto continue
        end
        m[#m + 1] = n[j]
        ::continue::
    end
    return m
end

n = {}
while true do
    x = split(io.read("l"), " ")
    if x == "quit" then
        break
    elseif #x ~= 2 then
        goto kill
    end
    n[#n + 1] = x
end
z = new({0})
for i = 1, #n do
    add(z, make(tabl(n, i), n[i]))
end
for i = 1, #z do
    z[0] = table.concat({z[i], conv(i), " + ",z[0]}, "")
end
print(z[0])
goto stop
::kill::
print("Number of inputs is invalid!")
::stop::