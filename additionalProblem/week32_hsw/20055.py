# BOJ 20055
import sys
input = sys.stdin.readline
from collections import deque

N, K = map(int, input().split())
durability = deque(list(map(int, input().split())))
robot = deque([False] * N)
ans = 0

while True:
    ans += 1

    # 1.
    durability.rotate(1)
    robot.rotate(1)
    # 단계만 카운트하므로, 매 단계에서 내리도록 세팅
    robot[N-1] = False

    # 2.
    for i in range(N-2, -1, -1):
        # 2-1.
        if robot[i] and not robot[i+1] and durability[i+1]:
            robot[i], robot[i+1] = False, True
            durability[i+1] -= 1
    robot[N-1] = False
    
    # 3.
    if durability[0]:
        robot[0] = True
        durability[0] -= 1

    # 4. 
    if durability.count(0) >= K:
        break
print(ans)