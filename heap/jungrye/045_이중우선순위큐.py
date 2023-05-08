import heapq


def solution(operations):
    answer = []
    pQueue = []
    for op in operations:
        o, n = op.split()

        if o == "I":
            heapq.heappush(pQueue, int(n))
        else:
            if len(pQueue) == 0:
                continue
            if n == '1':
                pQueue = sorted(pQueue)
                del pQueue[-1]
            else:
                heapq.heappop(pQueue)

    if len(pQueue) == 0:
        answer = [0, 0]
    elif len(pQueue) == 1:
        answer.append(pQueue[0])
        answer.append(pQueue[0])
    else:
        answer.append(max(pQueue))
        answer.append(min(pQueue))

    return answer


print(solution(["I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"]))
print(solution(["I -45", "I 653", "D 1", "I -642",
      "I 45", "I 97", "D 1", "D -1", "I 333"]))
