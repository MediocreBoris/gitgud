import math

def determinant(p):
    if len(p) == 1:
        return p[0][0]
    s = 0
    for i in range(len(p)):
        s += (-1)**i * determinant(create(p, i)) * p[0][i]
    return s

def create(p, i):
    x = []
    for j in range(1, len(p)):
        y = []
        for k in range(len(p)):
            if k == i:
                continue
            y.append(p[j][k])
        x.append(y)
    return x

def cramer(x, y, i):
    g = []
    for j in range(len(x)):
        h = []
        for k in range(len(x)):
            if k == i:
                h.append(y[j])
            else:
                h.append(x[j][k])
        g.append(h)
    return determinant(g)

def beaut(x):
    if x == 0:
        return 0
    elif x > 0 and math.floor(x) == x:
        return math.floor(x)
    elif x < 0 and math.ceil(x) == x:
        return math.ceil(x)
    return x

f = 0
g = []

try:
    x = [list(map(int, input().split()))]
    y = [x[0][-1]]
    x[0].pop(len(x[0]) - 1)
    for i in range(len(x[0]) - 1):
        x.append(list(map(int, input().split())))
        y.append(x[-1][-1])
        x[-1].pop(len(x[0]))
        if len(x[-1]) != len(x[0]):
            raise Exception
    f = determinant(x)
    if f == 0:
        raise Exception
    for i in range(len(y)):
        g.append(beaut(cramer(x, y, i) / f))
    print(" ".join(map(str, g)))
except:
    print("Input is invalid.")