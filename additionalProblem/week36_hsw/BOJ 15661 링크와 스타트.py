#BOJ 15661 링크와 스타트
import sys
input = sys.stdin.readline

n = int(input())
s = []
for _ in range(n):
    s.append(list(map(int, input().split())))

visited = [False] * n
ans = 99999

# 팀원 정하는 것 하나하나 계산
def backtrack(depth):
    if depth == n:
        calculate()
        return

    visited[depth] = True
    backtrack(depth+1)
    visited[depth] = False
    backtrack(depth+1)
    
# Sij 최소값 계산
def calculate():
    global ans
    start, link = 0, 0
    for i in range(n):
        for j in range(n):
            # i, j가 스타트팀
            if visited[i] and visited[j]:
                start += s[i][j]
            # i, j가 링크팀
            elif not visited[i] and not visited[j]:
                link += s[i][j]
    ans = min(ans, abs(start - link))
    return

backtrack(0)
print(ans)
