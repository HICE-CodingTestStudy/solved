# BOJ 14658 하늘에서 별똥별이 빗발친다
import sys

input = sys.stdin.readline


if __name__ == "__main__":
    n, m, l, k = map(int, input().split())
    stars = [tuple(map(int, input().split())) for _ in range(k)]

    x_pos = set()
    y_pos = set()

    for x, y in stars:
        x_pos.add(x)
        x_pos.add(x - l)
        y_pos.add(y)
        y_pos.add(y - l)

    max_covered = 0
    for x in x_pos:
        for y in y_pos:
            count = 0
            for x_star, y_star in stars:
                if x <= x_star <= x + l and y <= y_star <= y + l:
                    count += 1
            if count > max_covered:
                max_covered = count

    print(k - max_covered)
