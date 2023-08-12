import sys

input = sys.stdin.readline

N = int(input())
A = list(map(int, input().split()))
B = list(map(int, input().split()))

A = sorted(A)
B = sorted(B, reverse=True)

result = 0
for a, b in zip(A, B):
    result += a * b

print(result)
