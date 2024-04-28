# BOJ 2109 순회강연
import sys
input = sys.stdin.readline

if __name__ == "__main__":
    n = int(input())
    plan = [[0] * 2 for _ in range(n)]
    for i in range(n):
        p, d = map(int, input().split())
        plan[i]=[d, p]
    plan.sort(key=lambda x: -x[1])
    
    ans = 0
    payday = [False] * 10001
    for d, p in plan:
        today = d
        while today > 0 and payday[today]:
            today -= 1
            
        if today == 0:
            continue
        else:
            payday[today] = True
            ans += p
    print(ans)