function determine(x)
    if #x == 1 then
        return x[1][1]
    end
    local s = 0
    local y = {}
    for i = 1, #x do
        for j = 2, #x do
            local l = 1
            local m = {}
            for k = 1, #x do
                if k == i then
                    goto continue
                end
                m[l] = x[j][k]
                l = l + 1
                ::continue::
            end
            y[j - 1] = m
        end
        s = s + determine(y) * (-1)^(i + 1) * x[1][i]
    end
    return s
end

function split(s, d)
    r = {};
    for m in (s..d):gmatch("(.-)"..d) do
        table.insert(r, tonumber(m));
    end
    return r;
end

t = {}
for i = 1, 1000000 do
    local r = split(io.read(), " ")
    t[i] = r
    if #t[i] ~= #t[1] then
        goto kill
    elseif i == #t[1] then
        break
    end
end
print(determine(t))
goto stop
::kill::
print("The number of rows does not match.")
::stop::