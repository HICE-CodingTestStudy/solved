import sys
from collections import deque

input = sys.stdin.readline

N, M = map(int, input().split())
arr = deque([i + 1 for i in range(N)])
location = list(map(int, input().split()))
cnt = 0

for loc in location:
    while True:
        index = arr.index(loc)
        mid_index = len(arr) // 2

        if index <= mid_index:
            if arr[0] == loc:
                arr.popleft()  # 1번 행동
                break
            else:
                arr.append(arr.popleft())  # 2번 행동
                cnt += 1

        else:
            if arr[-1] == loc:
                arr.pop()  # 1번 + 3번 행동
                cnt += 1
                break

            else:
                arr.appendleft(arr.pop())  # 3번 행동
                cnt += 1

print(cnt)
