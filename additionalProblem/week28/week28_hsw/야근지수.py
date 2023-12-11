import heapq

def solution(n, works):
    ans = 0

    if (sum(works) <= n):
        ans = 0
    else:
        works = [-i for i in works]
        heapq.heapify(works)
        
        while (n > 0):
            heapq.heappush(works, heapq.heappop(works) + 1)
            n -= 1
        
        for i in works:
            ans += i ** 2
        
    return ans
