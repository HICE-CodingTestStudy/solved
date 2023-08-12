import sys

input = sys.stdin.readline

N, K = map(int, input().split())

country = [list(map(int, input().split())) for _ in range(N)]
country = sorted(country, key=lambda x: (x[1], x[2], x[3]), reverse=True)

idx = [c[0] for c in country].index(K)

for i in range(idx):
    if country[i][1:] == country[idx][1:]:
        print(i + 1)
        break
