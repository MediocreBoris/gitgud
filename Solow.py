from matplotlib import pyplot as plt
import numpy as np

"""
Implementation of the Solow model for economic growth with population growth and technological innovation.
"""

try:
    print("input total factor productivity at t=0")
    x = int(input())
    print("input deprecaton, growth of population and productivity per year in %")
    y = list(map(float, input().split()))
    print("input output elasticity for labour and capital in %")
    z = list(map(float, input().split()))
    print("input savings rate in %")
    s = float(input())

    a = np.linspace(0, 5000, 50000)
    b = a**(z[0] * 0.01) *x**(z[1] * 0.01)
    c = (sum(y) * 0.01)*a
    t = b * s * 0.01
    d = b - c
    e = np.argmax(d)
    da = [a[e], a[e]]
    db = [a[e]**(z[0] * 0.01) *x**(z[1] * 0.01), (sum(y) * 0.01)*a[e]]
    plt.plot(da, db)

    f = -1
    i = 1
    while f == -1:
        if d[i] <= -0.2 * b[i]:
            f = i
        i += 1
    del i
    
    plt.plot(a[:f], b[:f])
    plt.plot(a[:f], c[:f])
    plt.plot(a[:f], t[:f])
    plt.show()
except:
        print("Please input valid numbers and input all numbers")