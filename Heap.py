def sort(i):
    heapify(i, len(i))
    for e in range(len(i) - 1, 0, - 1):
        i[e], i[0] = i[0], i[e]
        sift(i, 0, e - 1)

def heapify(i, l):
    for s in range(int((l - 2) / 2), -1, -1):
        sift(i, s, l-1)

def sift(i, s, l):
    r = s
    while True:
        c = r * 2 + 1
        if c > l:
            break
        if c + 1 <= l and i[c] < i[c + 1]:
            c += 1
        if i[r] < i[c]:
            i[r], i[c] = i[c], i[r]
            r = c
        else:
            break

i = list(map(int, input().split()))
sort(i)
print(i)