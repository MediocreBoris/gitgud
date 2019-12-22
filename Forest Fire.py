from matplotlib import pyplot as plt

def fire(a, n):
    x = len(a)
    m = 1
    while x >= 2*m:
        f = n - a[x - m]
        g = a[x - m] - a[x - 2*m]
        if f == g:
            return False
        m += 1
    return True

z = int(input()) - 1
a = [1]
b = []
for i in range(1, z + 1):
    j = 0
    while True:
        j += 1
        if fire(a, j):
            a.append(j)
            break
for k in range(1, z + 2):
    b.append(k)
plt.scatter(b, a, s=0.1)
plt.show()