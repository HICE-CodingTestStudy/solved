N = int(input())
graph = [0]
for i in range(1, N+1):
    graph.append(int(input()))

countList = [0]  # 각 index에 대해 count 저장할 list
for i in range(1, N+1):
    visited = [0 for _ in range(N+1)]
    visited[i] = 1
    count = 1
    temp = i
    while True:
        if visited[graph[temp]] == 1:  # 더 안이어지면 현재까지 count 저장.
            countList.append(count)
            break
        else:  # 방문
            visited[graph[temp]] = 1
            temp = graph[temp]
            count += 1

print(countList.index(max(countList)))
