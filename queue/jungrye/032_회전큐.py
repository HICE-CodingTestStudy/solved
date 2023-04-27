import sys
from collections import deque

# n: 큐에 처음 들어있는 원소 수
# m: 뽑아내려는 원소 개수
# 둘째줄: 뽑아내려는 수의 위치가 순서대로
# 1: popleft
# 2: 1234 -> 2341
# 3: 1234 -> 4123
# 2,3번 연산 최솟값은?

n, m = map(int, sys.stdin.readline().split())
place = list(map(int, sys.stdin.readline().split()))

deq = deque()
for i in range(1, n+1):
    deq.append(i)

count = 0
for i in place:
    if i == deq[0]:
        deq.popleft()
        continue
    else:
        if deq.index(i) > len(deq)//2:
            while i != deq[0]:
                deq.appendleft(deq.pop())
                count += 1
        else:
            while i != deq[0]:
                deq.append(deq.popleft())
                count += 1
        deq.popleft()
print(count)

# 초기: 1(2)345678910
# l (2)3456789101
# pop 3456789101  2
# r 1345678(9)10
# r 101345678(9)
# r (9)101345678
# pop 10134(5)678   9
# r 810134567
# r 781013456
# r 678101345
# r 5678101345
# pop 678101345   5
