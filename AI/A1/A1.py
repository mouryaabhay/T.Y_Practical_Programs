# 1. To implement Breadth First Search (BFS)
# Given the graph below, write a Python program to perform Breadth First Search starting from node A.

# A -> B -> D -> G
# |
# C -> F

from collections import deque

# Given graph (adjacency list based on the image description)
graph = {
    'A': ['B', 'C'],
    'B': ['D'],
    'C': ['F'],
    'D': ['G'],
    'F': [],
    'G': []
}

def bfs(graph, start):
    visited = set()
    queue = deque()
    queue.append(start)
    visited.add(start)
    result = []

    while queue:
        node = queue.popleft()
        print(node, end = " ")

        for neighbor in graph[node]:
            if neighbor not in visited:
                visited.add(neighbor)
                queue.append(neighbor)

print("BFS starting from A:", bfs('A'))