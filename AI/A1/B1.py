# 1. To implement A* Algorithms.
# Using the graph below with given path costs and heuristic values, write a Python program to find the
# optimal path from the start node S to the goal node G.

from heapq import heappush, heappop

# Graph with path costs
graph = {
    'S': [('A', 1), ('B', 4)],
    'A': [('D', 5)],
    'B': [('G', 1)],
    'D': [('G', 3)],
    'G': []
}

# Heuristic values h(n)
h = {
    'S': 7,
    'A': 6,
    'B': 5,
    'D': 2,
    'G': 0
}

# A* Algorithm
def astar(start, goal):

    pq = []
    # Step 1 MATCHES:
    # Priority queue represents the Open List (nodes to be evaluated)

    # Step 2 MATCHES:
    # Add the start node to the Open List
    # g(start) = 0, f(start) = g + h(start)
    heappush(pq, (0 + h[start], start, [start], 0))

    while pq:
        # Step 3 MATCHES:
        # Select the node from the Open List with the lowest f(n)
        f, node, path, cost = heappop(pq)

        # Step 3 MATCHES:
        # If the current node is the goal, return the path and total cost
        if node == goal:
            return path, cost

        # Step 4 MATCHES:
        # Evaluate all neighboring nodes
        for neighbor, edge_cost in graph[node]:

            # g(n): actual cost from start node to neighbor
            new_cost = cost + edge_cost

            # f(n) = g(n) + h(n)
            # This matches the A* evaluation function definition
            new_f = new_cost + h[neighbor]

            # Step 4 MATCHES:
            # Add the neighbor to the Open List
            heappush(pq, (new_f, neighbor, path + [neighbor], new_cost))

    # Step 5 & 6 MATCHES:
    # If Open List is empty and goal is not reached
    return None, float('inf')


# Calling the function
path, cost = astar('S', 'G')

# Step 6 MATCHES:
# Print the final optimal path and cost
print("Optimal Path:", path)
print("Total Cost:", cost)
