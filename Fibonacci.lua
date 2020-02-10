s = {1}
s[0] = 0
for i = 2, io.read("n") do
    s[i] = s[i - 1] + s[i - 2]
end
print(table.concat(s, " "))