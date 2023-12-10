#boj 2116
import sys
input = sys.stdin.readline

N = int(input())
dice = [list(map(int, input().split())) for _ in range(N)]

upper=0    #윗면의 수를 담을 변수
maximum_result=0    #합의 최댓값을 담을 변수
#전개도를 보면 0-5, 1-3, 2-4면이 마주봄.

for j in range(6):
    sum = 0    #옆면의 합을 담을 변수
    i = 0    #현재 탐색중인 주사위
    while i != N:
        if j == 0:
            sum += max(dice[i][1],dice[i][2],dice[i][3],dice[i][4])
            upper = dice[i][5]

        elif j == 1:
            sum += max(dice[i][0],dice[i][2],dice[i][4],dice[i][5])
            upper = dice[i][3]

        elif j == 2:
            sum += max(dice[i][0],dice[i][1],dice[i][3],dice[i][5])
            upper = dice[i][4]

        elif j == 3:
            sum += max(dice[i][0],dice[i][2],dice[i][4],dice[i][5])
            upper = dice[i][1]

        elif j == 4:
            sum += max(dice[i][0],dice[i][1],dice[i][3],dice[i][5])
            upper = dice[i][2]

        elif j == 5:
            sum += max(dice[i][1],dice[i][2],dice[i][3],dice[i][4])
            upper = dice[i][0]

        i += 1    #다음 주사위
        if i != N:
            j = dice[i].index(upper)    #다음 주사위의 밑면

    if sum > maximum_result:    #기존 합이 최댓값보다 크면 갱신
        maximum_result = sum

print(maximum_result)