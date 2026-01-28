# 1. Solve the 8-Queens problem by placing 8 queens on an 8×8 chessboard such that no two queens attack each other.

# Step 2: Define safe function
def safe(board, row, col):
    """
    Check if it's safe to place a queen at (row, col)
    by checking column and both diagonals.
    """
    for r in range(row):
        c = board[r]
        # Column check
        if c == col:
            return False
        # Left diagonal check
        if abs(r - row) == abs(c - col):
            return False
    return True

# Step 3: Define recursive solve_queens function
def solve_queens(row, board, n):
    """
    Recursively place queens on the board using backtracking.
    """
    # Step 3.1: If all rows are filled, print the solution
    if row == n:
        print_solution(board)
        return

    # Step 3.2: Try placing queen in each column
    for col in range(n):
        if safe(board, row, col):
            board[row] = col  # Place queen
            solve_queens(row + 1, board, n)  # Recurse for next row
            board[row] = -1   # Backtrack

# Step 4: Define print_solution function
def print_solution(board):
    """
    Print the board with 'Q' for queens and '.' for empty cells.
    """
    n = len(board)
    for row in range(n):
        for col in range(n):
            if board[row] == col:
                print("Q", end=" ")
            else:
                print(".", end=" ")
        print()
    print()  # Blank line between solutions

# Step 5: Initialize board and call solver
n = 8
board = [-1] * n  # No queens placed initially
solve_queens(0, board, n)  # Start placing queens from row 0

# Step 6: Program stops after printing all solutions
