def cubes(x):
    y = []
    for i in range(1, x):
        if i**3 < x:
            y.append(i**3)
        else:
            return y

def taxicab(x, c):
    y = []
    for i in range(2, x):
        d = 0
        for j in range(0, len(c)):
            for k in range(0, j + 1):
                if c[j] + c[k] == i:
                    d += 1
        if d > len(y):
            y.append(i)
    print(y)

try:
    x = int(input())
    c = cubes(x)
    taxicab(x, c)
except:
    print("Please input a valid number.")