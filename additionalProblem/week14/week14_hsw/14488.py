#boj 14488

import sys
input = sys.stdin.readline

def left(x,v,t):
    return x-v*t
def right(x,v,t):
    return x+v*t

s = input()
n = int(s.split()[0])
t = s.split()[1]
if '.' in t:
    t = int(t.split('.')[0]+t.split('.')[1].ljust(4,'0'))
else:
    t = int(t)
x = list(map(int, input().split()))
x = list(map(lambda a : a*10000, x))
v = list(map(int, input().split()))

bound = [0,0]
bound[0] = left(x[0],v[0],t)
bound[1] = right(x[0],v[0],t)

for i in range(1,n):
    L = left(x[i], v[i], t)
    R = right(x[i], v[i], t)
    if bound[1] < L or bound[0]> R:
        print("0")
        sys.exit(0)
    if bound[0] <= L:
        bound[0] = L
    if bound[1] >= R:
        bound[1] = R
print("1")
