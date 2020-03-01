def permutate(i, n, m, s):
    z = []
    if m == 1:
        for j in range(i, n):
            z.append([j + 1])
        return z
    for j in range(i + 1, n + 1):
        s.append(j)
        h = permutate(j, n, m - 1, s)
        del(s[-1])
        for k in range(len(h)):
            h[k].append(j)
            z.append(h[k])
    return z

x = list(map(int, input().split()))
x = permutate(0, x[0], x[1], [])
for i in range(len(x)):
    print(" ".join(list(map(str, x[i]))))
