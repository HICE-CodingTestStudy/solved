# BOJ 14698 전생했더니 슬라임 연구자였던 건에 대하여 (Hard)
import sys, heapq

input = sys.stdin.readline

MOD = 1_000_000_007

if __name__ == "__main__":
    t = int(input())
    for _ in range(t):
        n = int(input())
        cList = list(map(int, input().split()))

        if n == 1:
            print(1)
            continue

        # 최소힙으로 초기화
        heapq.heapify(cList)

        totalCost = 1
        while len(cList) > 1:
            a = heapq.heappop(cList)
            b = heapq.heappop(cList)

            cost = (a * b) % MOD
            totalCost = (totalCost * cost) % MOD

            newSlime = a * b
            heapq.heappush(cList, newSlime)

        print(totalCost)
