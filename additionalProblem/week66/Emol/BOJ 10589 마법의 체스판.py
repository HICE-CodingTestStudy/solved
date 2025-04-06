# BOJ 10589 마법의 체스판
import sys

input = sys.stdin.readline


if __name__ == "__main__":
    n, m = map(int, input().split())
    ans = []

    for j in range(2, m + 1, 2):
        ans.append((1, j, n, j))
    for i in range(2, n + 1, 2):
        ans.append((i, 1, i, m))

    print(len(ans))
    for t in ans:
        print(*t)
