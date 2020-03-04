function getUserInput(T = String)
    if T == String
        return readline()
    else
    try
        return parse(T,readline())
    catch
        println("Sorry, I could not interpret your answer. Please try again")
        getUserInput(T,msg)
        end
    end
end

function brack(s, n, f)
    x = [["(", "[", "{", "<"], [")", "]", "}", ">"]];
    for i = 1:4
        if s == x[n][i]
            if f
                return s
            else
                return i
            end
        end
    end
    return " "
end

function brackets(s)
    x = String[];
    y = 0;
    for i = 1:length(s)
        a = brack(s[i], 1, true);
        if a != " "
            resize!(x, y + 1);
            x[y + 1] = a;
            y += 1;
        else
            b = brack(s[i], 2, true);
            if b != " "
                if y == 0
                    return "Brackets are uneven!"
                elseif brack(x[y], 1, false) == brack(s[i], 2, false)
                    deleteat!(x, length(x));
                    y -= 1;
                else
                    return "Brackets are uneven!"
                end
            end
        end
    end
    if y != 0
        return "Brackets are uneven!"
    else
        return "Brackets are even!"
    end
end

println(brackets(split(getUserInput(String), "")));