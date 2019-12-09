x = 1
def index(v, w, z):
    if v.index("Q") != w - z and v.index("Q") != w and v.index("Q") != w + z:
        return 1
for i in range(0, 8):
    q = "--------"
    la = l(q)
    la[i] = "Q"
    for j in range(0, 8):
        lb = l(q)
        if index(la, j, 1):
            lb[j] = "Q"
            for k in range (0, 8):
                lc = l(q)
                if index(lb, k, 1) and index(la, k, 2):
                    lc[k] = "Q"
                    for l in range (0, 8):
                        ld = l(q)
                        if index(lc, l, 1) and index(lb, l, 2) and index(la, l, 3):
                            ld[l] = "Q"
                            for m in range (0, 8):
                                le = l(q)
                                if index(ld, m, 1) and index(lc, m, 2) and index(lb, m, 3) and index(la, m, 4):
                                    le[m] = "Q"
                                    for n in range(0, 8):
                                        lf = l(q)
                                        if index(le, n, 1) and index(ld, n, 2) and index(lc, n, 3) and index(lb, n, 4) and index(la, n, 5):
                                            lf[n] = "Q"
                                            for o in range(0, 8):
                                                lg = l(q)
                                                if index(lf, o, 1) and index(le, o, 2) and index(ld, o, 3) and index(lc, o, 4) and index(lb, o, 5) and index(la, o, 6):
                                                    lg[o] = "Q"
                                                    for p in range(0, 8):
                                                        lh = l(q)
                                                        if index(lg, p, 1) and index(lf, p, 2) and index(le, p, 3) and index(ld, p, 4) and index(lc, p, 5) and index(lb, p, 6) and index(la, p, 7):
                                                            lh[p] = "Q"
                                                            print("Solution " + str(x) + ":")
                                                            print(la)
                                                            print(lb)
                                                            print(lc)
                                                            print(ld)
                                                            print(le)
                                                            print(lf)
                                                            print(lg)
                                                            print(lh)
                                                            print("  ")
                                                            x += 1