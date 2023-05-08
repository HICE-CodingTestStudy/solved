import sys
from collections import deque

input = sys.stdin.readline
N = int(input())

cards = deque([i + 1 for i in range(N)])

while True:
    if len(cards) == 1:
        break

    cards.popleft()
    cards.append(cards.popleft())

print(cards[0])
