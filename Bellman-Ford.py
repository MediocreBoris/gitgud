class Node:
    def __init__(self):
        self.graphs = []
        self.weight = 4294967296
        self.path = []
    def addGraph(self, graph):
        self.graphs.append(graph)
    def setPath(self, path):
        self.path = []
        for i in path:
            self.path.append(i)
    def getPath(self):
        return self.path
    def addPath(self, node):
        self.path.append(node)
    def getGraphs(self):
        return self.graphs
    def setWeight(self, num):
        self.weight = num
    def getWeight(self):
        return self.weight

class Graph:
    def __init__(self, weight, end):
        self.weight = weight
        self.end = end
    def getWeight(self):
        return self.weight
    def getEnd(self):
        return self.end

def create():
    x = {"S": Node(), "A": Node(), "B": Node(), "C": Node(),
         "D": Node(), "E": Node(), "F": Node(), "G": Node(),
         "H": Node(), "I": Node()}

    x["S"].addGraph(Graph(3, "A"))
    x["S"].addGraph(Graph(4, "B"))
    x["S"].addGraph(Graph(6, "C"))
    x["S"].addGraph(Graph(1, "D"))
    x["S"].addGraph(Graph(6, "E"))
    x["A"].addGraph(Graph(2, "C"))
    x["A"].addGraph(Graph(1, "E"))
    x["A"].addGraph(Graph(5, "F"))
    x["B"].addGraph(Graph(2, "D"))
    x["B"].addGraph(Graph(-1, "G"))
    x["B"].addGraph(Graph(2, "H"))
    x["C"].addGraph(Graph(2, "F"))
    x["E"].addGraph(Graph(1, "H"))
    x["G"].addGraph(Graph(-2, "E"))
    x["H"].addGraph(Graph(2, "I"))
    x["I"].addGraph(Graph(-1, "C"))

    return x

x = create()
y = True
while y:
    y = False
    x["S"].setWeight(0)
    for nodes in x:
        #if x[nodes].getWeight():
        #    continue
        for graphs in x[nodes].graphs:
            if x[graphs.getEnd()].weight > graphs.getWeight() + x[nodes].getWeight():
                y = True
                x[graphs.getEnd()].setPath(x[nodes].getPath())
                x[graphs.getEnd()].addPath(nodes)
                x[graphs.getEnd()].setWeight(graphs.getWeight() + x[nodes].getWeight())
for nodes in x:
    x[nodes].addPath(nodes)
    print("Weight: " + str(x[nodes].getWeight()) + " | " + " => ".join(x[nodes].getPath()))