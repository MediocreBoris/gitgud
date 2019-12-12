list = []
list = input().split()
if len(list) == 1:
    v = list[0]
    w = v
elif len(list) == 2:
    v = list[0]
    w = list[1]
elif len(list) > 2 or len(list) == 0:
    v = 2
    w = 1
v = int(v)
w = int(w)
for x in range(v, w+1):
    y = int(0)
    q = int(x)
    t = int(1)
    while q != 1:
        y = y + 1
        if q % 2 ==0:
            q = q / 2
        elif q % 2 != 0:
            q = (3 * q) + 1
        if x == q:
            print("Collatz Conjecture is false at:" + str(x))
            print("Error occurs at position:" + str(y - 1))
            t = 0
            break
        if v == w:
            print(str(y) + ": " + str(int(q)))
    if t == 1 and v != w:
        print(str(x) + " => " + str(y))