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

try:
    x = [list(map(int, input().split()))]
    for i in range(len(x[0]) - 1):
        x.append(list(map(int, input().split())))
        if len(x[len(x) - 1]) != len(x[0]):
            raise Exception
    print(determinant(x))
except:
    print("Input is invalid.")