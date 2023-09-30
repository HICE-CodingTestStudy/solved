import sys
input = sys.stdin.readline

x, y = [], []
N = int(input())

for _ in range(N):
    a,b = map(int, input().split())
    x.append(a)
    y.append(b)
    
#신발끈 정리 사용 위하여 마지막에 첫 좌표 삽입
x.append(x[0])
y.append(y[0])

x0, y0 = 0, 0
for i in range(N):
    x0 += x[i] * y[i+1]
    y0 += y[i] * x[i+1]
print(round(abs((x0-y0)/2),1))
