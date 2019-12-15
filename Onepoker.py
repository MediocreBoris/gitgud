import random

x = "A234567890JQK"
ea = x[random.randint(0, 12)]
eb = x[random.randint(0, 12)]
pa = x[random.randint(0, 12)]
pb = x[random.randint(0, 12)]
y = 0

def ud(v):
    if v == "J" or "Q" or "K" or "0" or "9" or "8":
        return "up"
    elif v == "2" or "3" or "4" or "A" or "5" or "6" or "7":
        return "down"

def ten(w):
    if w != "0":
        return w
    else:
        return "10"

def win(e, f):
    if e == "A" and f == "K":
        return True
    elif e == "K" and f == "A":
        return False
    elif x.index(e) > x.index(f):
        return True
    elif x.index(f) > x.index(e):
        return False

while -6 < y < 6:
    print(ud(ea) + " " + ud(eb))
    print(ten(pa) + " " + ten(pb))
    print("Input A for card 1 and B for card 2")
    while 1==1:
        z = input()
        if z == "A":
            s = pa
            pa = x[random.randint(0, 12)]
            break
        elif z == "B":
            s = pb
            pb = x[random.randint(0, 12)]
            break
        else:
            continue
    if random.randint(0, 2) == 1:
        r = ea
        ea = x[random.randint(0, 12)]
    else:
        r = eb
        eb = x[random.randint(0, 12)]
    if s == r:
        print(ten(s) + " == " + ten(r))
        print("It's a tie!")
    elif win(s, r):
        print(ten(s) + " >> " + ten(r))
        y += 1
        print("Player wins. Points: " + str(y))
    elif win(r, s):
        print(ten(s) + " << " + ten(r))
        y -= 1
        print("Player loses. Points: " + str(y))
    if y == 6:
        print("Player wins!")
    elif y == -6:
        print("Player loses!")
