# 프로그래머스 2022 KAKAO BLIND RECRUITMENT - 양과 늑대
import sys
input = sys.stdin.readline
from collections import deque
info = [0,0,1,1,1,0,1,0,1,0,1,1]
edges = [[0,1],[1,2],[1,4],[0,8],[8,7],[9,10],[9,11],[4,3],[6,5],[4,6],[8,9]]

#트리 구조 생성
tree = [[] for _ in range(len(info))]
for edge in edges:
    tree[edge[0]].append(edge[1])


q = deque([[tree[0], 1, 1]]) # 연결된 노드, 총합, 총 양의 수
sum, answer = 0, 1 # 루트는 항상 양
# bfs
while q:
    node = q.popleft()
    print('node:', node)
    print('node[0]:', node[0])
    print()
    # i : 현재 노드에서 다음 노드로 가는 인덱스, postnode : 현재노드에서 갈 다음 노드
    for i, postnode in enumerate(node[0]):
        sheep_total = node[2]
        # 양일 경우
        if info[postnode] == 0:
            sum = node[1] + 1
            sheep_total += 1
        # 늑대일 경우
        else:
            sum = node[1] - 1

        if sum > 0:
            q.append([node[0][:i] + node[0][i+1:] + tree[postnode], sum, sheep_total])
            answer = max(answer, sheep_total)

print(answer)
