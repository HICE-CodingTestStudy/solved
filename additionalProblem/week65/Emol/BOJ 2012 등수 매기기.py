# BOJ 2012 등수 매기기
import sys

input = sys.stdin.readline


if __name__ == "__main__":
    n = int(input())
    prediction = [int(input().strip()) for _ in range(n)]

    prediction.sort()

    ans = 0
    for i, want in enumerate(prediction):
        actual = i + 1
        ans += abs(want - actual)

    print(ans)
