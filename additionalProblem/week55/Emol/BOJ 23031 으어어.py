# BOJ 23031 으어어… 에이쁠 주세요..

import sys

input = sys.stdin.readline

# 방향: 하, 좌, 상, 우 (아래 방향을 보고 시작)
dir = [(1, 0), (0, -1), (-1, 0), (0, 1)]


def move_forward(x, y, direction, n):
    nx = x + dir[direction][0]
    ny = y + dir[direction][1]
    if 1 <= nx <= n and 1 <= ny <= n:
        return nx, ny
    else:
        return x, y


def light_up(n, x, y, lit):
    lit[x - 1][y - 1] = True
    for dx in range(-1, 2):
        for dy in range(-1, 2):
            nx, ny = x + dx, y + dy
            if 1 <= nx <= n and 1 <= ny <= n:
                lit[nx - 1][ny - 1] = True


def move_zombies(zombies, n):
    new_zombies = []
    for x, y, direction in zombies:
        nx = x + dir[direction][0]
        ny = y + dir[direction][1]
        if 1 <= nx <= n and 1 <= ny <= n:
            new_zombies.append((nx, ny, direction))
        else:
            # 벽에 부딪힌 경우 반대 방향으로 변경
            direction = (direction + 2) % 4
            new_zombies.append((x, y, direction))
    return new_zombies


if __name__ == "__main__":
    n = int(input())
    moves = list(input().strip())
    board = [input().strip() for _ in range(n)]

    x, y, direction = 1, 1, 0
    lit = [[False] * n for _ in range(n)]
    zombies = []

    for i in range(n):
        for j in range(n):
            if board[i][j] == "Z":
                zombies.append((i + 1, j + 1, 0))

    resultCheck = True
    for move in moves:
        if move == "L":
            direction = (direction - 1) % 4
        elif move == "R":
            direction = (direction + 1) % 4
        elif move == "F":
            x, y = move_forward(x, y, direction, n)

            if board[x - 1][y - 1] == "S":
                light_up(n, x, y, lit)

            for zx, zy, _ in zombies:
                if zx == x and zy == y and not lit[zx - 1][zy - 1]:
                    resultCheck = False
                    break

            if not resultCheck:
                break

        zombies = move_zombies(zombies, n)

        for zx, zy, _ in zombies:
            if zx == x and zy == y and not lit[zx - 1][zy - 1]:
                resultCheck = False
                break

        if not resultCheck:
            break

    if resultCheck:
        print("Phew...")
    else:
        print("Aaaaaah!")
