import math as m

def merge_sort(x):
    if len(x) <= 1:
        return x
    l = []
    r = []
    l = x[:m.floor((len(x) - 1) / 2) + 1]
    r = x[m.floor((len(x) - 1) / 2) + 1:]
    l = merge_sort(l)
    r = merge_sort(r)
    return merge(l, r)

def merge(l, r):
    y = []
    nl = len(l) - 1
    nr = len(r) - 1
    il = 0
    for i in range(0, nl + nr + 2):
        if il > nl:
            y.append(r[i - il])
            continue
        elif il < i - nr:
            y.append(l[il])
            il += 1
            continue
        if l[il] < r[i-il]:
            y.append(l[il])
            il += 1
        else:
            y.append(r[i - il])
    return y

x = list(map(int, input().split()))
print(merge_sort(x))