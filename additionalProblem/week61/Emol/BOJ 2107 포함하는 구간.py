# BOJ 2107 포함하는 구간
import sys
input = sys.stdin.readline

def sol(parts):
    parts = sorted(parts, key=lambda x: x[0])
    max_cnt = 0

    for i in range(len(parts)-1):
        e = parts[i][1]
        cnt = 0
        for j in range(i+1, len(parts)):

            cs = parts[j][0]
            ce = parts[j][1]

            if ce < e:
                cnt += 1

            elif cs > e:
                break

        max_cnt = max(max_cnt, cnt)
        
    print(max_cnt)

if __name__ == "__main__":
    n = int(input())
    intervals = [tuple(map(int, input().split())) for _ in range(n)]
    
    sol(intervals)