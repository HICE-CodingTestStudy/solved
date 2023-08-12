from collections import deque

N = int(input())
M = int(input())

graph = [[] for _ in range(N+1)]
for _ in range(M):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

visited = [0 for _ in range(N+1)]
visited[1] = 1
queue = deque([1])
count = 0
while queue:
    index = queue.popleft()
    for i in graph[index]:
        if visited[i] == 0:
            visited[i] = 1
            queue.append(i)
            count += 1
print(count)
