# BOJ 23611  진심 좌우 반복뛰기
import sys, math

input = sys.stdin.readline


def solution(N, K):
    n = int(math.sqrt(2 * (N - 1) // K))
    total_distance = K * n * (n + 1) // 2

    while total_distance < N - 1:
        n += 1
        total_distance += K * n

    remaining_distance = total_distance - (N - 1)

    if n % 2 == 0:
        pos = -K * (n // 2) + remaining_distance
        direction = "L" if remaining_distance > 0 else "R"
    else:
        pos = K * ((n + 1) // 2) - remaining_distance
        direction = "R" if remaining_distance > 0 else "L"

    return f"{pos} {direction}"


if __name__ == "__main__":
    T = int(input())

    for _ in range(T):
        N, K = map(int, input().split())
        print(solution(N, K))
