N = int(input())
graph = []
for _ in range(N):
    graph.append(list(map(int, input().split())))

count = [0, 0]


def divide(x, y, N):
    start_color = graph[x][y]
    for i in range(x, x+N):
        for j in range(y, y+N):
            if graph[i][j] != start_color:
                half = N//2
                divide(x, y, half)
                divide(x, y+half, half)
                divide(x+half, y, half)
                divide(x+half, y+half, half)
                return
    count[start_color] += 1
    return


divide(0, 0, N)
print(count[0])
print(count[1])
