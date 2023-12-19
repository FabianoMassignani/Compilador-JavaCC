from graphviz import Digraph
import sys


arg1 = sys.argv[1]

print(arg1)


def criar_nos(graph, arvore, pai=None):
    if not arvore:
        return

    no_tipo = arvore[0]

    no_nome = f"{no_tipo}_{id(arvore)}"
    graph.node(no_nome, label=no_tipo)

    if pai:
        graph.edge(pai, no_nome)

    for filho in arvore[1:]:
        criar_nos(graph, filho, pai=no_nome)

 
def transform_input(input_string):
    lines = input_string.strip().split("\n")
    stack = []
    root = None

    for line in lines:
        indent = len(line) - len(line.lstrip())
        node_name = line.strip()

        while stack and stack[-1][1] >= indent:
            stack.pop()

        new_node = [node_name]

        if stack:
            stack[-1][0].append(new_node)
        else:
            root = new_node

        stack.append((new_node, indent))

    return root
 
transformed_tree = transform_input(arg1)

#mudar o nome da imagem
graph = Digraph('Tree',format="png")   

criar_nos(graph, transformed_tree)
graph.view()
 
 
  