import sys

country = []
n, k = map(int, sys.stdin.readline().split())
for i in range(n):
    country.append(list(map(int, input().split())))
sortedList = sorted(country, key=lambda x: (
    x[1], x[2], x[3]), reverse=True)

answer = 1
tied = 0
if sortedList[0][0] == k:
    print(answer)
else:
    for i in range(1, len(sortedList)):
        if sortedList[i][0] == k:
            if sortedList[i][1:] == sortedList[i-1][1:]:
                break
            else:
                answer += (1+tied)
                break
        else:
            if sortedList[i][1:] == sortedList[i-1][1:]:
                tied += 1
            else:
                answer += (1+tied)
                tied = 0
    print(answer)
