def trunc(y, d):
    x = int(y)
    if prime(x):
        if len(y) == 1:
            if d == "l":
                return " is a left-truncatable prime."
            elif d == "r":
                return " is a right-truncatable prime."
        else:
            if d == "l":
                return trunc(y[1:len(y)], "l")
            elif d == "r":
                return trunc(y[0:len(y) - 1], "r")
    else:
        if d == "l":
            return " is not a left-truncatable prime."
        elif d == "r":
            return " is not a right-truncatable prime."

def prime(x):
    if x <= 1:
        return False
    for i in range(2, x):
        if x % i == 0:
            return False
    return True

y = input()
try:
    print(y + trunc(y, "l"))
    print(y + trunc(y, "r"))
except:
    print("Input only integers!")