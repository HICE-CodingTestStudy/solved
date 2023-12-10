#BOJ 1365
import sys
input = sys.stdin.readline
# 이분탐색해주는 함수 import
from bisect import bisect_left as bil
# bisect_left(literable, value) : value는 literable의 몇번째 인덱스의 왼쪽에 들어갈 수 있나?

N = int(input())
L = list(map(int, list(input().split(' '))))
LIS = []
for i in range(N):
    if not i:    #첫번쨰 원소는 그대로 추가
        LIS.append(L[0])
    if LIS[-1] < L[i]:    
        LIS.append(L[i])
    else:   # LIS의 마지막 원소보다 작으면, LIS 배열속
            #  어디에 들어갈 수 있는지 이분탐색으로 찾는다. 얻은 인덱스의 값과 변경
        LIS[bil(LIS,L[i])] = L[i]
print(N-len(LIS))

# 2 1 3 5 4 
# 2
# 1
# 1 3
# 1 3 5
# 1 3 4
