# BOJ 2056 작업
import sys
input = sys.stdin.readline

n = int(input())
linked = {}
worktimelist = [0]
for i in range(1, n+1):
    temp = list(map(int, input().split()))
    worktimelist.append(temp[0])
    if temp[1]:
        worktime, worklist = temp[0], temp[2:]
        for work in worklist:
            if i in linked:
                linked[i].append(work)
            else:
                linked[i] = [work]
            
    else:
        continue

for i in range(1, n+1):
    if i in linked:
        time = 0
        for work in linked[i]:
            time = max(time, worktimelist[work])
        worktimelist[i] += time
ans = max(worktimelist)
print(ans)
