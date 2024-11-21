# BOJ 12886 돌 그룹
import sys
from collections import deque

input = sys.stdin.readline


if __name__ == "__main__":
    a, b, c = map(int, input().split())
    flag = False
    printed = False
    total = a + b + c

    if total % 3:
        print(0)
        printed = True

    else:
        q = deque([(a, b, c)])
        visited = set([(a, b, c)])
        while q:
            x, y, z = q.popleft()
            if x == y == z:
                print(1)
                flag = True
                break

            for a, b in [(x, y), (x, z), (y, z)]:
                if a != b:
                    if a < b:
                        na = a + a
                        nb = b - a
                    else:
                        na = b + b
                        nb = a - b

                    c = x + y + z - a - b

                    state = tuple(sorted([na, nb, c]))

                    if state not in visited:
                        visited.add(state)
                        q.append(state)
    if not flag and not printed:
        print(0)
