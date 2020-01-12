def index(v, w, z):
    if v.index("Q") != w - z and v.index("Q") != w and v.index("Q") != w + z:
        return True

def solve(a, n, m, c):
    for j in range(0, m):
        a.append(["-"] * m)
        b = True
        for i in range(0, len(a) - 1):
            if not index(a[i], j, len(a) - i - 1):
                b = False
                break
        if b:
            a[n][j] = "Q"
            if n == m - 1:
                print(str(c[0]) + ":")
                c[0] += 1
                for i in range(0, m):
                    print(a[i])
                print(" ")
            else:
                solve(a, n + 1, m, c)
        del a[-1]

i = int(input())
a = []
b = [1]
if 1 < i < 4:
    print("No solutions possible!")
else:
    solve(a, 0, i, b)
