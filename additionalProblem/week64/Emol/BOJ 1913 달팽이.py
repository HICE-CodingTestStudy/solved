# BOJ 1913 달팽이
import sys

input = sys.stdin.readline


def sol(top, left, bound, count):
    # 중심
    if top == n // 2 and left == n // 2:
        arr[top][left] = count
        return

    # 왼쪽 열
    for r in range(top, bound):
        arr[r][left] = count
        count -= 1

    # 아래쪽 행
    for c in range(left + 1, bound):
        arr[bound - 1][c] = count
        count -= 1

    # 오른쪽 열
    for r in range(bound - 2, top - 1, -1):
        arr[r][bound - 1] = count
        count -= 1

    # 위쪽 행
    for c in range(bound - 2, left, -1):
        arr[top][c] = count
        count -= 1

    sol(top + 1, left + 1, bound - 1, count)


if __name__ == "__main__":
    n = int(input().strip())
    target = int(input().strip())

    arr = [[0] * n for _ in range(n)]
    sol(0, 0, n, n**2)

    pos = (0, 0)
    for i, row in enumerate(arr):
        print(" ".join(map(str, row)))
        if target in row:
            pos = (i + 1, row.index(target) + 1)
    print(f"{pos[0]} {pos[1]}")
