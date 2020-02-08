function check(t)
    for i = 1, 3, 1 do
        if t[i][1] == t[i][2] and t[i][2] == t[i][3] and t[i][1] ~= "-" then
            return t[i][1]
        end
        if t[1][i] == t[2][i] and t[2][i] == t[3][i] and t[1][i] ~= "-" then
            return t[1][i]
        end
    end
    if t[1][1] == t[2][2] and t[2][2] == t[3][3] and t[1][1] ~= "-" then
        return t[1][1]
    end
    if t[1][3] == t[2][2] and t[2][2] == t[3][1] and t[1][3] ~= "-" then
        return t[1][3]
    end
    return "-"
end

function printer(t)
    for i = 1, 3, 1 do
        print(t[i][1], t[i][2], t[i][3])
        print("\n")
    end
end

function full(t)
    for i = 1, 3, 1 do
        for k = 1, 3, 1 do
            if t[i][k] == "-" then
                return false
            end
        end
    end
    return true
end

t = {{"-", "-", "-"}, {"-", "-", "-"}, {"-", "-", "-"}}
s = {"X", "O"}
j = 0
while true do
    printer(t)
    local a = io.read("n")
    if a == nil then break end
    local b = (a - 1) % 3 + 1
    a = (a - b) / 3 + 1
    if t[a][b] == "-" then
        t[a][b] = s[j % 2 + 1]
    else goto continue end
    j = j + 1
    local r = check(t)
    if r ~= "-" then
        print(r, " has won!")
        printer(t)
    elseif full(t) then
        print("The game is tied!")
        printer(t)
    break end
    ::continue::
end