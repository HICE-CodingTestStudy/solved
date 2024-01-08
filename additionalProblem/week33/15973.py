# BOJ 15973
import sys
input = sys.stdin.readline

px1, py1, px2, py2 = map(int, input().split())
qx1, qy1, qx2, qy2 = map(int, input().split())

if qx1 < px1:
    px1, qx1 = qx1, px1
    py1, qy1 = qy1, py1
    px2, qx2 = qx2, px2
    py2, qy2 = qy2, py2

if qx1 == px2:
    if py2 == qy1 or py1 == qy2:
        print("POINT") 
    elif py1 <= qy1 <= py2 or py1 <= qy2 <= py2 or qy1 <= py1 <= qy2:
        print("LINE")

elif qx1 < px2:
    if qy2 < py1 or py2 < qy1:
        print("NULL")
    elif qy2 == py1 or py2 == qy1:
        print("LINE")
    else:
        print("FACE")
else:
    print("NULL")