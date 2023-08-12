def solution(citations):
    answer = 0
    tempList = []

    n = len(citations)
    citations = sorted(citations)

    for i in range(n):
        if citations[i] >= n-i:
            return n-i


print(solution([3, 0, 6, 1, 5]))
print(solution([1, 4, 5]))
