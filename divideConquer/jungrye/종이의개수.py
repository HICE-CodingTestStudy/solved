import sys
N = int(input())
graph = []
for _ in range(N):
    graph.append(list(map(int, sys.stdin.readline().split())))
count = [0, 0, 0]


def divide(x, y, N):
    start_num = graph[x][y]
    for i in range(x, x+N):
        for j in range(y, y+N):
            if graph[i][j] != start_num:
                divide(x, y, N//3)
                divide(x, y+N//3, N//3)
                divide(x, y+2*(N//3), N//3)

                divide(x+N//3, y, N//3)
                divide(x+N//3, y+N//3, N//3)
                divide(x+N//3, y+2*(N//3), N//3)

                divide(x+2*(N//3), y, N//3)
                divide(x+2*(N//3), y+N//3, N//3)
                divide(x+2*(N//3), y+2*(N//3), N//3)
                return
    if start_num == -1:
        start_num = 2
    count[start_num] += 1
    return


divide(0, 0, N)
print(count[2])
print(count[0])
print(count[1])
