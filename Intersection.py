def intersect(x, y):
    f = []
    for i in range(len(x)):
        for j in range(len(y)):
            if x[i] == y[j]:
                for k in range(len(f)):
                    if f[k] == x[i]:
                        break
                    elif k == len(f) - 1:
                        f.append(x[i])
                if len(f) == 0:
                    f.append(x[i])
    return f

x = list(map(int, input().split()))
y = list(map(int, input().split()))
x = intersect(x, y)
print(x)