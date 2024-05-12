#BOJ 1756
import sys
input = sys.stdin.readline

D, N = map(int, input().split())
oven= list(map(int, input().split()))
pizza = list(map(int,input().split()))

for i in range(1, D):
    if oven[i] > oven[i-1]: oven[i] = oven[i-1]

flag = 0
for i in reversed(range(D-1)):
    if pizza[flag] <= oven[i]:
        flag += 1

    if flag is N:
        print(i+1)
        break

if flag is not N:
    print(0)
