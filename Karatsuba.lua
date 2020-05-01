function size(s)
    local r = {};
    for n in (s..""):gmatch("(.-)".."") do
        r[#r + 1] = n;
    end
    return #r - 1;
end

function split_at(num, m, size)
    local r = {};
    local s = {};
    local num = tostring(num)
    for i in num:gmatch"." do
        if #r < size - m then
            r[#r + 1] = i;
        else
            s[#s + 1] = i;
        end
    end
    return tonumber(table.concat(r, "")), tonumber(table.concat(s, ""))
end


function karatsuba(num1, num2)
    if (num1 < 10) or (num2 < 10) then
        return num1 * num2
    end

    local size1 = size(num1)
    local size2 = size(num2)
    local m = math.ceil(math.min(size1, size2) / 2)

    local high1, low1 = split_at(num1, m, size1)
    local high2, low2 = split_at(num2, m, size2)

    local z0 = karatsuba(low1, low2)
    local z1 = karatsuba((low1 + high1), (low2 + high2))
    local z2 = karatsuba(high1, high2)

    return (z2 * math.pow(10, (m * 2))) + ((z1 - z2 - z0) * math.pow(10, m)) + z0
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

x = split(io.read("l"), " ")
print(math.floor(karatsuba(x[1], x[2])))