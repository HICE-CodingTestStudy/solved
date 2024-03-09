# BOJ 19583 싸이버개강총회
import sys
input = sys.stdin.readline

s, e, q = map(str, input().rstrip().split())
s = int(s[:2]) * 60 + int(s[3:])
e = int(e[:2]) * 60 + int(e[3:])
q = int(q[:2]) * 60 + int(q[3:])
check = set()
cnt = 0
while True:
    try:
        time, name = map(str, input().rstrip().split())
        time = int(time[:2]) * 60 + int(time[3:])
        if time <= s:
            check.add(name)
        elif e <= time <= q:
            if name in check:
                check.remove(name)
                cnt +=1
    except:
        break

print(cnt)
