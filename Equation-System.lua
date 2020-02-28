function parse(s)
    local t = {}
    local s = {s:match((s:gsub(".", "(.)")))}
    local d = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "-"}
    local l = {nil, false, false}
    for i = 1, #s do
        for j = 1, 11 do
            if s[i] == d[j] then
                if l[2] then
                    l[1] = l[1] * 10 + tonumber(s[i])
                elseif l[3] == false and j == 11 then
                    l[3] = true
                else
                    l = {tonumber(s[i]), true, l[3]}
                end
                goto skip
            elseif l[2] and j == 11 then
                t[#t + 1] = l[1] * sign(l[3])
                l = {nil, false, false}
            elseif l[3] and j == 11 and s[i] ~= " " then
                l[3] = false
            end
        end
        ::skip::
    end
    if l[1] ~= nil then
        t[#t + 1] = l[1]
    end
    return t
end

function sign(b)
    if b then
        return -1
    else
        return 1
    end
end

function eliminate(g, h, i)
    local n = h[i]
    for j = i, #g do
        h[j] = h[j] - g[j] * (n / g[i])
    end
    return h
end

function diag(z)
    local x = 1
    for i = 1, #z do
        x = x * z[i][i]
    end
    return x
end

function switch(z, i)
    if z[i][i] ~= 0 then
        return
    else
        for j = i + 1, #z do
            if z[j][i] ~= 0 then
                finish(z, i, j)
                goto done
            end
        end
    end
    ::done::
end

function finish(z, i, j)
    for k = 1, #z + 1 do
        local a = z[j][k]
        z[j][k] = z[i][k]
        z[i][k] = a
    end
end

function solve(z, i, f)
    for j = i + 1, #z - 1 do
        z[#z] = z[#z] - f[j] * z[j]
    end
    z[#z] = z[#z] / z[i]
    return z[#z]
end


local y = {}
local f = {}
y[1] = parse(io.read("l"))
for _ = 1, #y[1] - 2 do
    y[#y + 1] = parse(io.read("l"))
    if #y[#y] ~= #y[1] then
        goto kill
    end
end

for i = 1, #y do
    switch(y, i)
    for j = i + 1, #y do
        y[j] = eliminate(y[i], y[j], i)
    end
end

if diag(y) == 0 then
    print("There's no unique solution!")
    goto stop
end

for i = #y, 1, -1 do
    f[i] = solve(y[i], i, f)
end

print(table.concat(f, ", "))
goto stop
::kill::
print("Input is invalid!")
goto stop
::stop::