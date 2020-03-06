function split(s, d)
    r = {};
    for m in (s..d):gmatch("(.-)"..d) do
        table.insert(r, tonumber(m));
    end
    return r;
end

function quick(a, mi, ma)
    local p = mi
    if mi < ma then
        for j = mi + 1, ma do
            if a[j] < a[p] then
                a[p], a[j] = a[j], a[p]
                p = p + 1
            end
        end
        a[p], a[ma] = a[ma], a[p]
        a = quick(a, mi, p - 1)
        a = quick(a, p + 1, ma)
    end
    return a
end

r = split(io.read(), " ")
print(table.concat(quick(r, 1, #r), " "))