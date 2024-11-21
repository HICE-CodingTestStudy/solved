# BOJ 1484 다이어트
import sys, math

input = sys.stdin.readline


if __name__ == "__main__":

    g = int(input())

    weights = []

    for x in range(1, int(math.isqrt(g)) + 1):
        if g % x == 0:
            y = g // x
            if (x + y) % 2 == 0 and (y - x) % 2 == 0:
                current_weight = (y + x) // 2
                previous_weight = (y - x) // 2
                if previous_weight > 0:
                    weights.append(current_weight)

    if weights:
        weights.sort()
        for weight in weights:
            print(weight)

    else:
        print(-1)
