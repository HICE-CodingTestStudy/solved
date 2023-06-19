graph = [[] for _ in range(13)]
for _ in range(12):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)


for i in range(len(graph)):
    if len(graph[i]) == 3:  # 연결된 노드의 수 3개
        part = []
        for j in graph[i]:
            part.append(len(graph[j]))
        part.sort()
        if part == [1, 2, 3]: # 각 인접 노드에 연결된 노드의 수가 1,2,3 
            print(i)
