# BOJ 29757 트리 긋기(인척하는 문제)
import sys
input = sys.stdin.readline

# 입력 받기
N = int(input())
points = [list(map(int, input().split())) + [i+1] for i in range(N)]

# x, y 좌표를 오름차순 기준으로 정렬
points.sort(key=lambda x: (x[0], x[1]))

# 간선의 갯수만큼 출력
for i in range(N-1):
    print(points[i][2], points[i+1][2])
