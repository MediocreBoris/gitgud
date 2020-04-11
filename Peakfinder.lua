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
y = math.ceil(#x / 2)
z = #x / 4
x[0] = -10000000
x[#x + 1] = x[0]
while true do
    if x[y] > x[y + 1] and x[y] > x[y - 1] then
        print(table.concat({y, x[y]}, " ==> "))
        goto finish
    elseif x[y] < x[y - 1] then
        y = math.ceil(y - z)
        z = z / 2
    elseif x[y] < x[y + 1] then
        y = math.floor(y + z)
        z = z / 2
    else
        goto invalid
    end
    if z < 1 then
        z = 1
    end
end
::invalid::
print("Input is invalid")
::finish::