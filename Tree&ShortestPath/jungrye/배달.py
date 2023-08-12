import heapq


def solution(N, road, K):
    graph = [[] for _ in range(N+1)]

    for i in road:
        graph[i[0]].append((i[1], i[2]))
        graph[i[1]].append((i[0], i[2]))
    # print(graph)

    dist = [1e9 for _ in range(N+1)]
    dist[1] = 0

    queue = []
    heapq.heappush(queue, [dist[1], 1])

    while queue:
        curr_dist, curr_dest = heapq.heappop(queue)
        if dist[curr_dest] < curr_dist:
            continue
        for new_dest, new_dist in graph[curr_dest]:
            distance = curr_dist+new_dist
            if distance < dist[new_dest]:
                dist[new_dest] = distance
                heapq.heappush(queue, [distance, new_dest])

    answer = 0
    for i in dist:
        if i <= K:
            answer += 1
    return answer


print(solution(5, [[1, 2, 1], [2, 3, 3], [5, 2, 2],
      [1, 4, 2], [5, 3, 1], [5, 4, 2]], 3))  # 4
print(solution(6, [[1, 2, 1], [1, 3, 2], [2, 3, 2], [
      3, 4, 3], [3, 5, 2], [3, 5, 3], [5, 6, 1]], 4))  # 4
