board = [" "] * 9
player = "X"

winning_pattern = [
    [0, 1, 2],  # Row 1
    [3, 4, 5],  # Row 2
    [6, 7, 8],  # Row 3
    [0, 3, 6],  # Column 1
    [1, 4, 7],  # Column 2
    [2, 5, 8],  # Column 3
    [0, 4, 8],  # Diagonal 1
    [2, 4, 6],  # Diagonal 2
]


def showBoard():
    print(f"{board[0]} | {board[1]} | {board[2]}")
    print("--+---+--")
    print(f"{board[3]} | {board[4]} | {board[5]}")
    print("--+---+--")
    print(f"{board[6]} | {board[7]} | {board[8]}")


def checkWin(player):
    for pattern in winning_pattern:
        if board[pattern[0]] == board[pattern[1]] == board[pattern[2]] == player:
            return True
    return False


def checkDraw():
    return " " not in board


print("Rules:\n-Enter position from 1 to 9 to play your move\n- Get 3 of your moves in a line or diagonal to win\n- If all positions are filled and no one wins, it's a draw\n- Enter -1 to exit the game\n")

while True:
    showBoard()
    value = int(input(f"Player {player}, enter your move (1-9): "))
    if value == -1:
        print("Game exited.")
        break
    if value < 1 or value > 9: # Invalid input
        print("Invalid value. Try again.")
        continue

    if board[value - 1] != " ": # Invalid overriding move
        if player == board[value - 1]:
            print("Pay attention to your move pls! You are playing the same move again!\n")
            continue
        print("- Why are you trying to override your opponent's move!?\n- Are you afraid you'll lose?\n")
        continue

    board[value - 1] = player

    if checkWin(player):
        showBoard()
        print(f"Player {player} wins!")
        break
    if checkDraw():
        showBoard()
        print("It's a draw!")
        break

    if player == "X":
        player = "O"
    else:
        player = "X"