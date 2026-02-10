# BEGINNER 8 PUZZLE

board = [1, 2, 3, 4, 0, 5, 6, 7, 8]
goal = [1, 2, 3, 4, 5, 6, 7, 8, 0]
moves = 0

print("3x3 Puzzle Game")
print("Type tile number to move or type exit\n")

while board != goal:

    # print board
    i = 0
    while i < 9:
        if board[i] == 0:
            print("_", end=" ")
        else:
            print(board[i], end=" ")

        if i == 2 or i == 5:
            print()
        i = i + 1
    print("\n")

    # find empty position
    empty = board.index(0)

    # check possible moves
    possible = []

    # Find which tiles can move into the empty space

    # If empty index is greater than 2,
    # it means the blank is NOT in the top row (0,1,2)
    # So a tile from ABOVE can move down into the blank.
    if empty > 2:
        possible.append(board[empty - 3])

    # If empty index is less than 6,
    # it means the blank is NOT in the bottom row (6,7,8)
    # So a tile from BELOW can move up into the blank.
    if empty < 6:
        possible.append(board[empty + 3])

    # If empty index is NOT divisible by 3,
    # it means blank is NOT in the left column (0,3,6)
    # So a tile from LEFT can move right into the blank.
    if empty % 3 != 0:
        possible.append(board[empty - 1])

    # If empty index mod 3 is NOT 2,
    # it means blank is NOT in the right column (2,5,8)
    # So a tile from RIGHT can move left into the blank.
    if empty % 3 != 2:
        possible.append(board[empty + 1])

    print("You can move:", possible)

    choice = input("Enter tile: ")

    if choice == "exit":
        break

    if choice.isdigit() == False:
        print("Enter number only\n")
        continue

    tile = int(choice)

    if tile not in possible:
        print("Wrong move\n")
        continue

    tile_index = board.index(tile)

    # swap
    temp = board[empty]
    board[empty] = board[tile_index]
    board[tile_index] = temp

    moves = moves + 1

print("Puzzle solved in", moves, "moves!")
