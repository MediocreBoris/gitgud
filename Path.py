def path(l, c, x, y):
    if x == len(l) - 1 and y == len(l[0]) - 1:
        c[0] += 1
        return c[0]
    if x + 1 < len(l):
        if l[x + 1][y] == 0:
            path(l, c, x + 1, y)
    if y + 1 < len(l[0]):
        if l[x][y + 1] == 0:
            path(l, c, x, y + 1)
    return c[0]

x = list(map(int, input().split()))
x = [x]
for i in range(len(x[0]) - 1):
    y = list(map(int, input().split()))
    x.append(y)
print(path(x, [0], 0, 0))