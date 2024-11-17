# BOJ 23327 리그전 오브 레전드
import sys

input = sys.stdin.readline

# (a+b)^2  =a^2 + 2ab + b^2
# (a+b+c)^2  =a^2 + b^2 + c^2 + 2(ab+ac+bc)
# 곱의 합 = 1/2 * (합의 제곱 - 제곱의 합)

if __name__ == "__main__":
    n, q = map(int, input().split())
    a = list(map(int, input().split()))

    s = [0] * (n + 1)
    s2 = [0] * (n + 1)

    for i in range(1, n + 1):
        s[i] = s[i - 1] + a[i - 1]
        s2[i] = s2[i - 1] + a[i - 1] * a[i - 1]

    for _ in range(q):
        l, r = map(int, input().split())
        total = s[r] - s[l - 1]
        total_sq = s2[r] - s2[l - 1]
        ans = (total * total - total_sq) // 2
        print(ans)
