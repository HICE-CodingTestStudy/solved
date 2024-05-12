# BOJ 4803 트리
import sys
input = sys.stdin.readline

def cycle(start):
    for node in graph[start]:
        if parent[start] == node:
            continue

        if visited[node]:
            return True
        parent[node] = start
        visited[node] = True

        if cycle(node):
            return True
    return False

if __name__ == "__main__":
    
    case = 1
    while True:
        n, m = map(int, input().split())
        if not n and not m:
            break

        graph = [[] for _ in range(n+1)]
        parent = [-1] * (n+1)
        visited = [False] * (n+1)
        cnt = 0
        
        for i in range(m):
            a, b = map(int, input().split())
            graph[a].append(b)
            graph[b].append(a)

        for node in range(1, n+1):
            if not visited[node]:
                parent[node] = node
                visited[node] = True
                if not cycle(node):
                    cnt += 1

        if not cnt:
            print(f"Case {case}: No trees.")
        elif cnt == 1:
            print(f"Case {case}: There is one tree.")
        else:
            print(f"Case {case}: A forest of {cnt} trees.")

        case += 1

        



