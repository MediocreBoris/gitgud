def values(u, h):
    v = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz'
    if u <= 9:
        return u
    elif 9 < u < h and 10 <= h <= 62:
        return v[int(u) - 10]

l = ["0"]
x, b = input().split()
x = int(x)
g = x
b = int(b)
y = 0
if x < 0:
    print("Invalid Value!")
if b < 1 or b > 62:
    print("Invalid Base!")
    x = -1
if x >= 0:
    while 1 == 1:
        if b == 1:
            y = x - 1
            break
        if x // b**y >= b:
            y = y + 1
        else:
            s = x // b**y
            x = x - s * b**y
            l[0] = str(values(s, b))
            break
    while x != 0:
        if b == 1:
            break
        y = y - 1
        f = x // b**y
        x = x - f * b**y
        r = str(values(f,b))
        l.append(r)
    while y != 0:
        l.append(str(0))
        y = y - 1
    z = "".join(l)
    print(str(g) + " => " + z + " in Base:" + str(b))