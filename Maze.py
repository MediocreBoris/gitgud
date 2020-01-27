import random

class Point:
    def __init__(self):
        self.right = True
        self.up = True
        self.visit = False
        self.number = 0

def generate(b, x, y, d, m):
    b[x][y].visit = True
    b[x][y].number = m
    g = [[1, 0], [0, 1], [-1, 0], [0, -1]]
    f = random.randint(0, 3)
    for i in range(0, 4):
        n = (i + f) % 4
        if d == (n + 2) % 4:
            continue
        elif not -1 < g[n][0] + x < len(b):
            continue
        elif not -1 < g[n][1] + y < len(b[0]):
            continue
        elif b[g[n][0] + x][g[n][1] + y].visit:
            continue
        else:
            generate(b, x + g[n][0], y + g[n][1], n, m + 1)

def mazify(b):
    for i in range(0, len(b)):
        for j in range(0, len(b[0])):
            if i - 1 > -1:
                if not abs(b[i][j].number - b[i - 1][j].number) == 1:
                    b[i][j].up = False
            elif i == 0:
                b[i][j].up = False
            if j + 1 < len(b[0]):
                if not abs(b[i][j].number - b[i][j + 1].number) == 1:
                    b[i][j].right = False

def prin(b):
    for i in range(0, len(b)):
        print(horizontal(b[i]))
        print(vertical(b[i]))
    print(horizontal(b[0]))

def horizontal(b):
    m = "+"
    for i in range(0, len(b)):
        if b[i].up:
            m += "  +"
        else:
            m += "--+"
    return m

def vertical(b):
    m = "|"
    for i in range(0, len(b)):
        if i == len(b) - 1:
            m += "  |"
        elif b[i].right:
            m += "   "
        else:
            m += "  |"
    return m

x, y = map(int, input().split())
b = [[Point() for i in range(0, x)] for j in range(0, y)]
generate(b, 0, 0, 5, 1)
mazify(b)
prin(b)