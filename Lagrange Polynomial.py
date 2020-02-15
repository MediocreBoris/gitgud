class Polynomial:
    def __init__(self, func):
        self.func = func
    def add(self, p):
        for i in range(0, max(len(self.func), len(p.func))):
            if i > len(self.func) - 1:
                self.func.append(p.func[i])
            else:
                self.func[i] += p.func[i]
    def multiply(self, p):
        x = []
        for i in range(len(self.func)):
            for j in range(len(p.func)):
                if i + j > len(x) - 1:
                    x.append(self.func[i] * p.func[j])
                else:
                    x[i + j] += self.func[i] * p.func[j]
        self.func = x
    def calculate(self, n):
        x = 0
        for i in range(len(self.func)):
            x += self.func[i] * n**i
        return x

def one(n, m):
    x = Polynomial([-n[0][0], 1])
    for i in range(1, len(n)):
        x.multiply(Polynomial([-n[i][0], 1]))
    x.multiply(Polynomial([m[1] / x.calculate(m[0])]))
    return x

def copy(n, i):
    z = []
    for j in range(len(n)):
        if j == i:
            continue
        else:
            z.append(n[j])
    return z

y = []
try:
    while True:
        x = input().split()
        if x[0] == "quit":
            break
        else:
            if len(x) != 2:
                raise Exception
            else:
                y.append(list(map(int, x)))
    f = one(copy(y, 0), y[0])
    for i in range(1, len(y)):
        f.add(one(copy(y, i), y[i]))
    x = ""
    for i in range(len(y) - 1, -1, -1):
        if i == 0 and f.func[i] != 0:
            x += str(f.func[0])
        else:
            if f.func[i] == 0:
                continue
            x += str(f.func[i]) + " * x"
            if i != 1:
                x += "^" + str(i)
            if f.func[i - 1] < 0:
                x += " - "
                f.func[i - 1] *= -1
            elif f.func[i - 1] != 0:
                x += " + "
    print(x)
except Exception:
    print("Invalid number of inputs")