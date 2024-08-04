# BOJ 23815 똥게임
import sys

input = sys.stdin.readline

INF = -int(1e9)


def calc(value, operation):
    op, x = operation[0], int(operation[1:])
    if op == "+":
        return value + x
    elif op == "-":
        return value - x
    elif op == "*":
        return value * x
    elif op == "/":
        return value // x
    return value


if __name__ == "__main__":
    n = int(input())
    options = [input().split() for _ in range(n)]

    # dp[i][j]: i번째 선택지, j는 광고 시청여부
    dp = [[INF] * 2 for _ in range(n + 1)]

    dp[0][0] = 1

    for i in range(1, n + 1):
        # k: 옵션 선택에 따름
        for k in range(2):
            # 이전 상태에서 광고를 보지 않은 경우
            if dp[i - 1][0] != INF:
                new_val = calc(dp[i - 1][0], options[i - 1][k])
                if new_val > 0:
                    dp[i][0] = max(dp[i][0], new_val)
                    # dp[i][1] = max(dp[i][1], new_val)

            # 이전 상태에서 광고를 본 경우
            if dp[i - 1][1] != INF:
                new_val = calc(dp[i - 1][1], options[i - 1][k])
                if new_val > 0:
                    dp[i][1] = max(dp[i][1], new_val)

        # 두 선택지가 모두 - 또는 / 인 경우
        if options[i - 1][0][0] in "-/" and options[i - 1][1][0] in "-/":
            dp[i][1] = max(dp[i][1], dp[i - 1][0])  # 광고를 보고 스킵

    result = max(dp[n])

    print(result) if result > 0 else print("ddong game")
