m, n = input().split()
a, b = input().split()
m = int(m)
n = int(n)
a = int(a)
b = int(b)
l = []
if n == a:
    if m <= 0 or n <= 0 or a <= 0 or b <= 0:
        print("Negative or zero amounts of rows or columns are not supported")
        n = a + 1
    else:
        matrixa = [[0 for j in range(n)] for i in range(m)]
        matrixb = [[0 for o in range(b)] for k in range(a)]
        print("")
        print("Enter number, row and column. Type set to move on to the next matrix.")
elif n != a:
    print("The number of columns of matrix A have to match the number of rows of matrix B")
while n == a:
    l = input()
    if l == "set":
        break
    try:
        z = int(l[0])
        r = int(l[2]) - 1
        c = int(l[4]) - 1
        if m > r > -1 and -1 < c < z:
            matrixa[r][c] = z
        else:
            print("")
            print("Invalid row or column")
            print("Enter number, row and column. Type set to move on to the next matrix.")
            continue
    except ValueError:
        print("")
        print("Please use integers for numbers, rows and columns or use three numbers only as input.")
        print("Enter number, row and column. Type set to move on to the next matrix.")
        continue
    print("")
    print("Enter number, row and column. Type set to move on to the next matrix.")
if n == a:
    print("")
    print("Enter number, row and column. Type set to move on to the calculation")
while n == a:
    l = input()
    if l == "set":
        break
    try:
        z = int(l[0])
        r = int(l[2]) - 1
        c = int(l[4]) - 1
        if a > r > -1 and -1 < c < b:
            matrixb[r][c] = z
        else:
            print("")
            print("Invalid row or column")
            print("Enter number, row and column. Type set to calculate the matrix.")
            continue
    except ValueError:
        print("")
        print("Please use integers for numbers, rows and columns or use three numbers only as input.")
        continue
    print("")
    print("Enter number, row and column. Type set to calculate the matrix.")
if n == a:
    matrixc = [[0 for o in range(b)] for k in range(m)]
    for i in range(len(matrixa)):
        for j in range(len(matrixb[0])):
            for k in range(len(matrixb)):
                matrixc[i][j] += matrixa[i][k] * matrixb[k][j]
if n == a:
    for h in range(len(matrixc)):
        print(matrixc[h])