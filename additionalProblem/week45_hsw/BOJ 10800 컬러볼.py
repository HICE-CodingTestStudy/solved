# BOJ 10800 컬러볼
import sys
input = sys.stdin.readline
from collections import defaultdict

if __name__ == '__main__':
    n = int(input())
    
    balls = []
    for i in range(n):
        c, s = map(int, input().split())
        balls.append([c, s, i])
    balls.sort(key=lambda x: x[1])
    print(balls)
    
    ans = defaultdict(int)
    size_sum = defaultdict(int)
    
    total, j = 0,0
    for i in range(n):
        while balls[j][1] < balls[i][1]:
            total += balls[j][1]
            size_sum[balls[j][0]] += balls[j][1]
            j += 1
        ans[balls[i][2]] = total - size_sum[balls[i][0]]
    
    for i in range(n):
        print(ans[i])
            
    
    