# 2021 Kakao blind recruitment 합승택시요금
def solution(n, s, a, b, fares):
    INF = int(1e9)
    maps = [[INF] * (n+1)for _ in range(n+1)] 
    for c, d, f in fares:
        maps[c][d] = f
        maps[d][c] = f
    for k in range(1, n+1):
        for i in range(1, n+1):
            for j in range(1, n+1):
                if i == j:
                    maps[i][j] = 0
                else:
                    maps[i][j] = min(maps[i][k] + maps[k][j], maps[i][j])
    answer = INF
    for i in range(1, n+1):
        answer = min(maps[s][i]+ maps[i][a] + maps[i][b], answer)
    
    return answer
