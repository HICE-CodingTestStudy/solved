#BOJ 14613

import sys
input = sys.stdin.readline
import math

W, L, D = map(float, input().split())

# P = 2000 + 50 W - 50 L + 0 Z
# <= P <=
# <= W-L <=
# 다이아는 전부 이겨야함...

BP, SP, GP, PP, DP = 0.0, 0.0, 0.0, 0.0, 0.0
top = math.factorial(20)

for dcount in range(21):
    for wcount in range(21-dcount):
        lcount = 20 - dcount - wcount
        temp = top//math.factorial(wcount)//math.factorial(lcount)//math.factorial(dcount)
        # 브
        if (lcount-wcount) >= 11:
            BP += temp * (W**wcount) * (L**lcount) * (D**dcount)
        # 실
        if 1 <= (lcount-wcount) < 11:
            SP += temp * (W**wcount) * (L**lcount) * (D**dcount)
        # 골
        if 0 <= (wcount-lcount) < 10:
            GP += temp * (W**wcount) * (L**lcount) * (D**dcount)
        # 플
        if 10 <= (wcount - lcount) < 20:
            PP += temp * (W**wcount) * (L**lcount) * (D**dcount)


# 다
DP += (W**20)
    
print(f"{BP:.8f}")
print(f"{SP:.8f}")
print(f"{GP:.8f}")
print(f"{PP:.8f}")
print(f"{DP:.8f}")

                
    
