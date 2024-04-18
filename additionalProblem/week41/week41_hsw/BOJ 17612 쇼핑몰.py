# BOJ 17612 쇼핑몰
import sys
input = sys.stdin.readline
import heapq

n, k = map(int, input().split())

shopping = []
for _ in range(n):
    id, w = map(int, input().split())
    shopping.append((id, w))

counter = []
for i in range(k):
    heapq.heappush(counter, (0, i))

counter_time = [0] * (k)
r_list = []
for i in range(n):
    time, counter_num = heapq.heappop(counter)
    counter_time[counter_num] += shopping[i][1]
    heapq.heappush(counter, (counter_time[counter_num], counter_num))
    r_list.append((counter_time[counter_num], -counter_num, i))

r_list.sort()
ans = 0
for index, (_, _, i) in enumerate(r_list):
    ans += (index+1) * shopping[i][0]
print(ans)
