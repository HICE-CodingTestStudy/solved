import sys

N = int(input())
U = []
for i in range(N):
    U.append(int(sys.stdin.readline().strip()))
U.sort()

ab = set()
for i in range(N):
    for j in range(N):
        ab.add(U[i]+U[j])

for i in range(N-1, 1, -1):
    for j in range(i+1):
        if U[i]-U[j] in ab:
            print(U[i])
            quit(0)
