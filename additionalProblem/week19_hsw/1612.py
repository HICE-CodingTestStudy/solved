#BOJ 1612

import sys
input = sys.stdin.readline

N = int(input())

if not N % 2 or not N % 5:
    print(-1)
else:
    j=0; cnt = 1
    while True:
        j = (10 * j +1) % N
        if not j % N:
            print(cnt)
            break
        cnt += 1
