function spiral(y, s, xx, yy, n)
    d = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}
    y[xx][yy] = math.tointeger(n)
    if n == 1 then
        return
    elseif 0 > xx + d[s][1] or xx + d[s][1] > #y then
        s = s % 4 + 1
    elseif 0 > yy + d[s][2] or yy + d[s][2] > #y then
        s = s % 4 + 1
    elseif y[xx + d[s][1]][yy + d[s][2]] ~= 0 then
        s = s % 4 + 1
    end
    spiral(y, s, xx + d[s][1], yy + d[s][2], n - 1)
end

function conv(y, l)
    m = {y}
    n = 1
    while math.pow(l, 2) > math.pow(10, n) do
        if y ~= "-" then
            if math.pow(10, n) < y then
                goto continue2
            end
        end
        m[#m + 1] = " "
        ::continue2::
        n = n + 1
    end
    return table.concat(m, "")
end

n = io.read("n")
y = {}
for i = 1, n do
    yy = {}
    for j = 1, n do
        yy[#yy + 1] = 0
    end
    y[#y + 1] = yy
end
spiral(y, 1, 1, 1, math.pow(n, 2))
for i = 1, #y do
    for j = 1, #y do
        if y[i][j] == 1 then
            y[i][j] = "-"
            goto continue
        end
        for k = 2, y[i][j] / 2 do
            if y[i][j] % k == 0 then
                y[i][j] = "-"
                goto continue
            end
        end
        ::continue::
        y[i][j] = conv(y[i][j], #y)
    end
end
for i = 1, #y do
    print(table.concat(y[i], " "))
end