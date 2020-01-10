import numpy as np
from numpy import array

c = [[0, 1], [1, 0], [0, -1], [-1, 0]]

def solve(a, x, y, n, m, b):
    xa = a.shape
    for i in range(0, 4):
        h = x + c[i][0]
        g = y + c[i][1]
        if -1 < h < xa[0] and -1 < g < xa[1]:
            if(m < b.shape[0]):
                if not distance([n, h, g], [b[m][0], b[m][1], b[m][2]]):
                    continue
            if a[h][g] == 0:
                a[h][g] = n
                if solve(a, h, g, n + 1, m, b):
                    return True
            elif a[h][g] == n:
                if solve(a, h, g, n + 1, m + 1, b):
                    return True

    if n == a.shape[0] * a.shape[1] + 1:
        return True
    else:
        if back(a[x][y], b):
            a[x][y] = 0

def distance(n, m):
    x = abs(n[1] - m[1]) + abs(n[2] - m[2])
    z = abs(n[0] - m[0])
    if x > z:
        return False
    elif (z - x) % 2 == 1:
        return False
    if n[1] == m[1] and n[2] == m[2]:
        if n[0] != m[0]:
            return False
    return True


def back(x, b):
    for i in range(0, b.shape[0]):
        if x == b[i][0]:
            return False
    return True
                    
def one(a):
    xa = a.shape
    for x in range(0, xa[0]):
        for y in range(0, xa[1]):
            if a[x][y] == 1:
                return [x, y]
    return [-1, 0]

def brute(a, b):
    xa = a.shape
    e = one(a)
    if e[0] == -1:
        for s in range(0, xa[0]):
            for t in range(0, xa[1]):
                f = [[1, s, t], [b[0][0], b[0][1], b[0][2]]]
                if not check(f, a.shape, 2):
                    continue
                if a[s][t] == 0:
                    a[s][t] = 1
                    if solve(a, s, t, 2, 0, b):
                        return True
    else:
        return solve(a, e[0], e[1], 2, 1, b)

def check(b, a, m):
    for i in range(0, m - 1):
        if b[i][0] > a[0] * a[1]:
            return False
        if not distance(b[i], b[i + 1]):
            return False
    return True

def sure(r, b):
    for i in range(0, b.shape[0]):
        if (b[i] == r).all():
            return False
    return True

try:
    print("Input number of rows and columns")
    x, y = input().split()
    a = np.zeros((int(x), int(y)), dtype=int)
    b = np.zeros((0, 3), dtype=int)
    print("Input number, x and y value. Type quit to move on.")
    while 1 == 1:
        r = input()
        if r == "quit" or r == "Quit":
            break
        r = array(list(map(int, r.split())))
        if sure(r, b):
            b = np.append(b, [[r[0], r[1] - 1, r[2] - 1]], axis=0)
        a[r[1] - 1][r[2] - 1] = r[0]
    b = b[b[:,0].argsort()]
    if not check(b, a.shape, b.shape[0]):
        print("Puzzle is unsolvable")
    else:
        if brute(a, b):
            print(a)
        else:
            print("Puzzle is unsolvable")
except:
    print("Input is invalid")
