# 2. To implement Best First Search.
# Using the graph below and heuristic values, write a Python program to apply Best First Search to reach T.

from heapq import heappush, heappop

# Graph representation
graph = {
    'Start': ['X', 'Y'],
    'X': [],
    'Y': ['Z'],
    'Z': ['T'],
    'T': []
}

# Heuristic values
heuristic = {
    'X': 6,
    'Y': 4,
    'Z': 2,
    'T': 0,
    'Start': 5
}

# Best First Search function
def best_first_search(start, goal):
    pq = []
    visited = []

    heappush(pq, (heuristic[start], start))

    while pq:
        h, node = heappop(pq)

        if node not in visited:
            visited.append(node)

            if node == goal:
                return visited

            for neighbor in graph[node]:
                heappush(pq, (heuristic[neighbor], neighbor))

# Call the function
result = best_first_search('Start', 'T')

# Display result
print("Best First Search Traversal:", result)