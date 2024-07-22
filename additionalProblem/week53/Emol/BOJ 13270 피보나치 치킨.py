# BOJ 13270 피보나치 치킨
import sys

input = sys.stdin.readline
# 태영이 너 이자식!


if __name__ == "__main__":

    n = int(input().strip())

    dp_min = [int(1e9)] * (n + 1)
    dp_max = [0] * (n + 1)

    # 치킨 마리=a / 인원=chicken
    chicken, people = 1, 2

    dp_min[0], dp_max[0] = 0, 0

    while people <= n:
        for i in range(people, n + 1):
            dp_min[i] = min(dp_min[i], dp_min[i - people] + chicken)
            dp_max[i] = max(dp_max[i], dp_max[i - people] + chicken)
        people, chicken = people + chicken, people

    print(dp_min[n], dp_max[n])
