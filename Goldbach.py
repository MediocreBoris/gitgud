def goldbach(f, g):
    for j in range(2, (f // 2) + 1):
        if (f % j) == 0:
            break
    else:
        if g != 1:
            for k in range(2, (g // 2) + 1):
                if (g % k) == 0:
                    break
            else:
                return 500

x = int(input(""))
if x % 2 == 0:
    if x > 3:
        for y in range(2, (x // 2) + 1):
            z = x - y
            if goldbach(y, z) == 500:
                print(str(z) + "+" + str(y))
    elif x <= 3:
        print("No solution!")
elif x % 2 != 0:
    if x > 6:
        for a in range(4, x - 2):
            if a % 2 == 0:
                b = x - a
                for i in range(2, (b // 2) + 1):
                    if (b % i) == 0:
                        break
                else:
                    for y in range(2, (a // 2) + 1):
                        z = a - y
                        if goldbach(y, z) == 500 and b >= z >= y:
                            print(str(b) + "+" + str(z) + "+" + str(y))
    elif x <= 6:
        print("No solution!")