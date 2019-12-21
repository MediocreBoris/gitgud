"""
Not to be taken seriously, as there are way easier solutions. This is just for training.
This code will find a given word in a string.
"""

def find(tex, wor, i, num):
    if num == len(wor):
        return True
    elif i > len(tex) - 1:
        return False
    elif tex[i] == wor[num]:
        return find(tex, wor, i + 1, num + 1)

z = input()
y = input()
j = 1
for i in range(0, len(z)):
    if z[i] == y[0]:
        if find(z, y, i + 1, 1):
            print("Occurence " + str(j) +" at letter " + str(i + 1))
            j += 1