# BOJ 1377 버블 소트
import sys
input = sys.stdin.readline

# 몇번만에 정렬되는가? 
# 한 스텝마다 (i++ 마다) 원소 하나가 정렬됨
# 항상 오른쪽으로만 이동하므로, 왼쪽으로 밀린 횟수의 최대값이
# 버블정렬이 일어난 횟수.


if __name__ == "__main__":
    n = int(input())
    a = []
    for i in range(n):
        a.append((int(input()), i))

    sorted_a = sorted(a)
    res = []
    for i in range(n):
        res.append(sorted_a[i][1] - a[i][1])
    print(max(res) + 1)

    

    