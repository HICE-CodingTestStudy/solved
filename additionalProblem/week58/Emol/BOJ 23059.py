# BOJ 23059 리그오브레게노

import sys, heapq

input = sys.stdin.readline


if __name__ == "__main__":
    n = int(input())
    next_adj = {}  # 현아이템 - 다음 아이템 (그래프)
    pre_adj = {}  # 각 아이템의 선행 아이템 수
    items = set()  # 모든 아이템 집합

    for _ in range(n):
        a, b = input().split()
        next_adj.setdefault(a, []).append(b)
        items.add(a)
        items.add(b)
        pre_adj.setdefault(a, 0)
        pre_adj.setdefault(b, 0)
        pre_adj[b] += 1

    result = []
    heap = []  # 현재 구매 가능한 아이템들

    for item in items:
        if pre_adj[item] == 0:  # 선행하는 아이템이 없는 템들 추가
            heapq.heappush(heap, item)

    while heap:

        current_state = []
        while heap:
            current_state.append(
                heapq.heappop(heap)
            )  # 현재 구매 가능한 아이템 모두 추출

        current_state.sort()

        next_heap = []
        for current in current_state:
            result.append(current)  # 아이템 구매
            for next_item in next_adj.get(current, []):
                pre_adj[next_item] -= 1

                if pre_adj[next_item] == 0:
                    heapq.heappush(
                        next_heap, next_item
                    )  # 다음 루프에서 구매할 아이템 추가
        heap = next_heap

    # 사이클 있으면 종료
    if len(result) != len(items):
        print(-1)
    else:
        for item in result:
            print(item)
