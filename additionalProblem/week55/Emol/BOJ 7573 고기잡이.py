# BOJ 7573 고기잡이
import sys

input = sys.stdin.readline


def catch_fish(fish1, fish2, fish, length, m):
    global res

    x = fish[fish1][0]
    y = fish[fish2][1]

    for width in range(1, length // 2 + 1):
        height = length // 2 - width
        if height <= 0:
            break

        cnt = 0

        for i in range(m):
            if x <= fish[i][0] <= x + width and y <= fish[i][1] <= y + height:
                cnt += 1

        res = max(res, cnt)


if __name__ == "__main__":
    n, l, m = map(int, input().split())

    fish = []
    for i in range(m):
        x, y = map(int, input().split())
        fish.append((x, y))

    res = 0
    for i in range(m):
        for j in range(m):
            catch_fish(i, j, fish, l, m)

    print(res)
