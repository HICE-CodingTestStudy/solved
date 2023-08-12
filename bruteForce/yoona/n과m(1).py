import sys

input = sys.stdin.readline
N, M = map(int, input().split())


def permutation(choosed, used):
    if len(choosed) == M:
        print(" ".join(map(str, choosed)))
        return

    for i in range(N):
        if not used[i]:
            choosed.append(i + 1)
            used[i] = 1
            permutation(choosed, used)
            used[i] = 0
            choosed.pop()


choosed = []
used = [0] * N

permutation(choosed, used)