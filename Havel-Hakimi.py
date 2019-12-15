def elim(tup):
    for i in range(0, len(tup)):
        if 0 in tup:
            tup.pop(tup.index(0))
        else:
            break
    return tup

def hav(seq):
    elim(seq)
    seq.sort(reverse = True)
    if len(seq) == 0:
        return True
    x = seq[0]
    seq.remove(seq[0])
    if x > len(seq):
        return False
    for j in range(0, x):
        seq[j] -= 1
    return hav(seq)


num = list(map(int, input().split()))
if hav(num) == True:
    print("Arrangment of graphs is possible")
else:
    print("Arrangment of graphs is impossible")