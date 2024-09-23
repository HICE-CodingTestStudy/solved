# BOJ 15729 방탈출
# 그리디
import sys

input = sys.stdin.readline


if __name__ == "__main__":
    n = int(input())
    result = list(map(int, input().split()))
    res = 0

    for i in range(n - 2):
        if result[i] == 1:
            res += 1
            result[i] ^= 1
            result[i + 1] ^= 1
            result[i + 2] ^= 1
    
    if result[n - 2] == 1:
        res += 1
        result[n - 2] ^= 1
        result[n - 1] ^= 1

    if result[n - 1] == 1:
        res += 1

    print(res)
    
    
    
    