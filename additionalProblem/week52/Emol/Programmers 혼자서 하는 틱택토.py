# Programmers 혼자서 하는 틱택토
def solution(board):
    def check_win(b, ch):
        for i in range(3):
            if b[i][0] == b[i][1] == b[i][2] == ch:
                return True
        for j in range(3):
            if b[0][j] == b[1][j] == b[2][j] == ch:
                return True
        if b[0][0] == b[1][1] == b[2][2] == ch:
            return True
        if b[0][2] == b[1][1] == b[2][0] == ch:
            return True
        return False

    o_count = sum(row.count("O") for row in board)
    x_count = sum(row.count("X") for row in board)

    if o_count != x_count and o_count != x_count + 1:
        return 0

    o_wins = check_win(board, "O")
    x_wins = check_win(board, "X")

    if o_wins and x_wins:
        return 0
    if o_wins and o_count != x_count + 1:
        return 0
    if x_wins and o_count != x_count:
        return 0

    return 1
