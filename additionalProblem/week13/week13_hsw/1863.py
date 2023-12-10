#boj 1863
import sys

input = sys.stdin.readline

n = int(input())
pos = [0]
result = 0

for _ in range(n):
    x, y  =  map(int, input().split())
    if y > pos[-1]:        #현재 높이가 이전 빌딩의 높이보다 큰경우 +1
        result += 1
        pos.append(y)
    else:                    # 현재 높이가 이전 빌딩의 높이보다 작은경우
        while y < pos[-1]:    #이전 빌딩 높이가 같거나 커질때까지 pop 반복
            pos.pop()

        if y > pos[-1]:        #반복문 이후 이전 빌딩보다 크면 +1
            result += 1
            pos.append(y)
print(result)
   