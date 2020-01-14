import random

m = [[0, 1], [1, 1], [1, 0], [1, -1],
     [0, -1], [-1, -1], [-1, 0], [-1, 1]]

def make(a, n, d):
    for i in range(0, n):
        while i == i:
            x = random.randint(0, len(a) - 1)
            y = random.randint(0, len(a[0]) - 1)
            if abs(x - d[0]) <= 1 and abs(y - d[1]) <= 1:
                continue
            elif a[x][y] != "X":
                a[x][y] = "X"
                break
    annotate(a)

def annotate(a):
    for x in range(0, len(a)):
        for y in range(0, len(a[0])):
            if a[x][y] == "X":
                continue
            f = 0
            for i in range(0, 8):
                if -1 < x + m[i][0] < len(a):
                    if -1 < y + m[i][1] < len(a[0]):
                        if a[x + m[i][0]][y + m[i][1]] == "X":
                            f += 1
            if f == 0:
                continue
            a[x][y] = str(f)
            
def form(i):
    if i > 9:
        return str(i)
    return("".join(["0", str(i)]))

def prin(g, a, f):
    print("      " + f)
    for i in range(0, a):
        print(form(i + 1) + ":   " + "  | ".join(g[i]))

def reveal(z, g, x, y, l):
    g[x][y] = z[x][y]
    l[0] += 1
    for i in range(0, 8):
        j = x + m[i][0]
        k = y + m[i][1]
        if -1 < j < len(z) and -1 < k < len(z[0]):
            if g[j][k] == "-" or g[j][k] == "F":
                if z[j][k] == " ":
                    if i % 2 == 0:
                        reveal(z, g, j, k, l)
                elif z[j][k] != "X" and z[x][y] == " ":
                    g[j][k] = z[j][k]
                    l[0] += 1

print("Input rows, columns and number of mines.")
a, b, c = map(int, input().split())
z = [[" " for j in range(0, b)] for i in range(0, a)]
if a * b - 9 < c:
    print("Number of mines makes this impossible to construct")
else:
    f = " | ".join([form(i) for i in range(1, b + 1)])
    g = [["-" for j in range(0, b)] for i in range(0, a)]
    l = [0]
    prin(g, a, f)
    w = True
    while True:
        print("Flag => Put flag, Unflag => Remove flag, Guess => Guess field")
        print("Input action, row and column.")
        o, d, e = input().split()
        d = int(d) - 1
        e = int(e) - 1
        if o == "guess" or o == "Guess":
            if w:
                make(z, c, [d, e])
                w = False
            reveal(z, g, d, e, l)
            if l[0] + c == a * b:
                print("You won!")
                prin(z, a, f)
                break
            elif z[d][e] == "X":
                print("You hit a mine!")
                break
        elif o == "flag" or o == "Flag":
            if g[d][e] == "-":
                g[d][e] = "F"
        elif o == "unflag" or o == "Unflag":
            if g[d][e] == "F":
                g[d][e] = "-"
        prin(g, a, f)