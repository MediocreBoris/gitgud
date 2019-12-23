def dat(a, b):
    z = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30]
    d = 0
    for j in range(0, a - 1):
        d += z[j]
    if b % 4 == 0 and a > 2:
        d += 1
    return d

def word(x):
    if x == 0:
        return "Monday"
    elif x == 1:
        return "Tuesday"
    elif x == 2:
        return "Wednesday"
    elif x == 3:
        return "Thursday"
    elif x == 4:
        return "Friday"
    elif x == 5:
        return "Saturday"
    elif x == 6:
        return "Sunday"

y = list(map(int, input().split()))
a = y[2] % 100
b = y[2] // 100
if a % 2 == 1:
    a += 11
a = int(a / 2)
if a % 2 == 1:
    a += 11
a = 7 - (a % 7)
b = 5*(b % 4) % 7 + 1
a = (a + b) % 7
c = y[0] + dat(y[1], y[2])
c = c % 7 - 3
c += a
if y[2] % 4 == 0 and y[1] <= 2:
    if y[2] % 400 == 0 or y[2] % 100 != 0:
        c -= 1
c = c % 7
print(word(c))