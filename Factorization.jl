function getUserInput(T = String, msg = "")
    print("$msg ")
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

function factorize(n)
    x = String[];
    for i in 2:(n / 2)
        if n % i == 0
            n = n / i;
            resize!(x, length(x) + 1);
            x[length(x)] = string(i);
        end
    end
    return join(x, " x ");
end

println(factorize(getUserInput(Int64,"Write a number:")));