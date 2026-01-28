# 3. Solve the water jug problem using a 4-liter jug and a 3-liter jug to measure exactly 2 liters of water.

from collections import deque
# deque: double-ended queue for efficient FIFO operations
# deque(): creates a queue to store BFS states
# popleft(): removes and returns the front element (FIFO order)

def water_jug():
    # Step 4: Initialize BFS data structures
    q = deque([(0, 0)])      # Queue starting with initial state (0,0)
    visited = set([(0, 0)])  # Keep track of visited states
    parent = {}               # To reconstruct the path

    # Step 5: BFS traversal
    while q:
        a, b = q.popleft()  # Step 5.1: Dequeue front state

        # Step 5.2: Check if target reached
        if (a, b) == (2, 0):
            path = []
            while (a, b) != (0, 0):
                path.append((a, b))
                a, b = parent[(a, b)]
            path.append((0, 0))
            path.reverse()  # Step 7: Reconstruct path

            # Step 7: Print solution path
            for step in path:
                print(step)
            return

        # Step 5.3: Generate all possible moves from (a, b)
        moves = [
            (4, b),                      # Fill Jug A
            (a, 3),                      # Fill Jug B
            (0, b),                      # Empty Jug A
            (a, 0),                      # Empty Jug B
            (a - min(a, 3 - b), b + min(a, 3 - b)),  # Pour A → B
            (a + min(b, 4 - a), b - min(b, 4 - a))   # Pour B → A
        ]

        # Step 5.4: Process each new state
        for m in moves:
            if m not in visited:
                visited.add(m)
                parent[m] = (a, b)  # Track parent for path reconstruction
                q.append(m)         # Add to queue for BFS

# Step 6: Start BFS traversal
water_jug()  # Step 12 & 13: Execute and print solution
