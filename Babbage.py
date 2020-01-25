def babbage(x):
    i = 1
    while True:
        j = list(str(i**2))
        if len(j) < len(x):
            i += 1
            continue
        for k in range (0, len(x)):
            if j[len(j) - k - 1] != x[len(x) - k - 1]:
                i += 1
                break
            elif k < len(x) - 1:
                continue
            print(str(i) + " ==> " + "".join(j))
            return

babbage(list(input()))