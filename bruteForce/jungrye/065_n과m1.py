def permutation(visited, nList, level, m):
    if level == m:
        print(' '.join(map(str, answers)))
        return
    for i in range(len(visited)):
        if visited[i] == 1:
            continue
        else:
            visited[i] = 1
            answers[level] = nList[i]
            permutation(visited, nList, level+1, m)
            visited[i] = 0


n, m = map(int, input().split())
nList = []
for i in range(n):
    nList.append(i+1)
visited = [0]*n
answers = [0]*m
level = 0

permutation(visited, nList, level, m)
