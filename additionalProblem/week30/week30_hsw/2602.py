#BOJ 2602
import sys
input = sys.stdin.readline

scroll = list(input().rstrip())
bridge = [input().rstrip() for _ in range(2)]

Lscroll = len(scroll)
Lbridge = len(bridge[0])

#dp[i][j][k] : k 번째 돌다리의 i번째 == j번째 글자
dp = [[[0] * 2 for _ in range(Lscroll)] for _ in range(Lbridge)]

#돌다리 위치
for i in range(Lbridge):
    # 두루마리 위치
    for j in range(Lscroll):
        # 천사/악마
        for k in range(2):
            if bridge[k][i] == scroll[j]:
                # 첫 글자의 기본 경우
                if j == 0:
                    dp[i][j][k] = 1
                # 이전 돌다리 위치 반복 & j-1번째 글자와 반대행(1-k)에 대한 
                # 이전 위치에서의 값 합산
                else:
                    for x in range(i):
                        dp[i][j][k] += dp[x][j-1][1-k]

ans = 0
for i in range(Lbridge):
    for j in range(2):
        # j번째 행의 처음 i개의 글자를 사용하여 두루마리의 마지막 글자에 도달하는 방법의 수
        ans += dp[i][-1][j]
print(ans)