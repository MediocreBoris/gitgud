function split(s, d)
    r = {};
    for m in (s..d):gmatch("(.-)"..d) do
        if m == "==>" then
            goto continue
        end
        table.insert(r, tonumber(m));
        ::continue::
    end
    return r;
end

x = split(io.read(), " ")
j = 0
k = 0
for i = #x, 2, -1 do
    k = k + x[i] * x[1]^j
    j = j + 1
end
print(k)