# 2. To implement Hill Climbing Algorithm.
# Write a Python program using the Hill Climbing Algorithm to guide the hiker from BaseCamp to the highest plateau.
# Note: The hiker starts at BaseCamp and wants to reach the highest plateau by moving only to a nearby plateau that is higher.

# | Plateau        | Height |
# | BaseCamp (A)   | 3      |
# | PineHill (B)   | 5      |
# | EaglePeak (C)  | 8      |
# | RockyRidge (D) | 4      |

# Step 2: Define the plateaus and their heights
plateaus = {
    'BaseCamp': 3,
    'PineHill': 5,
    'EaglePeak': 8,
    'RockyRidge': 4
}

# Step 3: Define the trail (sequence of plateaus along the path)
trail = ['BaseCamp', 'PineHill', 'EaglePeak', 'RockyRidge']

# Step 4: Initialize starting position
current = 'BaseCamp'

# Step 5: Traverse the trail using Hill Climbing
for next_plateau in trail:
    # Move to next plateau only if it is higher
    if plateaus[next_plateau] > plateaus[current]:
        current = next_plateau  # Update current position

# Step 6: Print results
print("Highest plateau reached:", current)
print("Height:", plateaus[current])

# Step 7: Stop program (ends automatically after printing)
