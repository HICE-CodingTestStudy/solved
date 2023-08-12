n = int(input())
a = list(input().split())
a = [int(i) for i in a]
b = list(input().split())
b = [int(i) for i in b]
sortedA = sorted(a)
sortedB = sorted(b, reverse=True)

answer = 0
for i in range(n):
    answer += sortedA[i]*sortedB[i]
print(answer)
