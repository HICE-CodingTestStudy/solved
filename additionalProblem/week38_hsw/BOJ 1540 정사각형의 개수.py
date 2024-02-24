#BOJ 1540 정사각형의 개수

import math, sys
input = sys.stdin.readline

n = int(input())

x = int(math.sqrt(n))
between = n - (x * x)

# n이하의 제곱수에 해당되는 점으로 만들 수 있는 정사각형의 개수를 모두 더한다.
ans = 0
for i in range(1, x):
    ans += i * i

# 제곱수 사이의 갯수만큼 찍어보면서 정사각형의 갯수를 구한다(더한다)
stack = 0
for _ in range(between):
    ans += stack
    stack = (stack + 1) % x

print(ans)
