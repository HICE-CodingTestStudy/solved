# BOJ 30408 춘배가 선물하는 특별한 하트
import sys

input = sys.stdin.readline


def dfs(N, M, memo):
    if N == M:  # 현재 하트가 M과 같다면 YES
        return True

    if N < M or N == 1:  # 현재 하트가 M보다 작거나, 1이면 더 이상 진행 불가
        return False

    if N in memo:  # 이미 계산된 경우
        return memo[N]

    if N % 2 == 0:
        result = dfs(N // 2, M, memo)

    else:
        smaller_half = (N - 1) // 2
        larger_half = smaller_half + 1
        result = dfs(smaller_half, M, memo) or dfs(larger_half, M, memo)

    memo[N] = result
    return result


N, M = map(int, input().split())

memo = {}

# 결과 출력
if dfs(N, M, memo):
    print("YES")
else:
    print("NO")
