#boj 1943
#0-1 knapsack 문제
import sys
input = sys.stdin.readline
for _ in range(3):
    total = 0 
    coins = [] 
    N = int(input())
    for _ in range(N):
        C, num = map(int, input().split())
        total += C * num
        coins.append([C, num])

    if total&1:
        print(0)
        continue

    total //= 2
    dp = [True] + [False] * total     #0원은 언제나 참, 나머지는 False로 초기화

    answer = 0
    for i in range(len(coins)):
        C, num = coins[i]
        
        # 거꾸로 탐색하면서 지불 가능한 액수 갱신
        for n in range(total, C-1, -1):    #거꾸로 세면서 지불가능한 액수 갱신, 오름차순이면 이미 사용한 걸 또 사용할 가능성있음.
            if dp[n-C]:     #(n - coin)원부터 동전을 하나씩 사용 
                for j in range(num):
                    if n + C * j <= total:
                        dp[n + C * j] = True
                    else:
                        break
                        
        if dp[-1]:
            answer = 1
            break

    print(answer)
