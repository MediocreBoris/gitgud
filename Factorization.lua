n = io.read("n")
f = {}
::continue::
for i = 2, n / 2 do
    if n % i == 0 then
        n = n / i
        f[#f + 1] = i
        goto continue
    end
end
f[#f + 1] = math.tointeger(n)
print(table.concat(f , " x "))