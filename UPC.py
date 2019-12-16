u = list(map(int, input()))
if len(u) == 11:
    x = u[0] + u[2] + u[4] + u[6] + u[8] + u[10]
    x *= 3
    x += (u[1] + u[3] + u[5] + u[7] + u[9])
    x = 10 % (10 - (x % 10))
    u.append(x)
    u = list(map(str, u))
    print("".join(u))
else:
    print("Barcode is invalid")