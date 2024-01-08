#BOJ 3019

import sys
input = sys.stdin.readline

C, P = map(int, input().split())
Lines = list(map(int, input().split()))

ans = 0

match P:
    case 1:
        # |
        ans += C
        # ㅡ
        for i in range(C-3):
            if (Lines[i] == Lines[i+1] and Lines[i+1] == Lines[i+2] and 
                    Lines[i+2] == Lines[i+3]):
                ans += 1
    case 2:
        # ㅁ
        for i in range(C-1):
            if Lines[i] == Lines[i+1]:
                ans += 1
    case 3:
        # 』『
        for i in range(C-2):
            if Lines[i] == Lines[i+1] and Lines[i+1] == Lines[i+2]-1:
                ans += 1
        for i in range(C-1):
            # ㄱ
            if Lines[i] == Lines[i+1] + 1:
                ans += 1
    case 4:
        #ㄱㄴ
        for i in range(C-2):
            if Lines[i] == Lines[i+1] + 1 and Lines[i+1]==Lines[i+2]:
                ans += 1
        # 『
        for i in range(C-1):
            if Lines[i] == Lines[i+1] -1:
                ans += 1
    case 5:
        for i in range(C-2):
            # ㅗ
            if Lines[i] == Lines[i+1] and Lines[i+1] == Lines[i+2]:
                ans += 1
            # ㅜ
            if Lines[i] == Lines[i+1] + 1 and Lines[i+1] == Lines[i+2] - 1:
                ans += 1
        for i in range(C-1):
            # ㅓ
            if Lines[i] == Lines[i+1] + 1:
                ans += 1
            # ㅏ
            if Lines[i] == Lines[i+1] - 1:
                ans += 1

    case 6:
        # _』
        for i in range(C-2):
            if Lines[i]==Lines[i+1] and Lines[i+1]==Lines[i+2]:
                ans+=1
            #『ㅡ
            if Lines[i]==Lines[i+1]-1 and Lines[i+1]==Lines[i+2]:
                ans+=1
        for i in range(C-1):
            #L
            if Lines[i]==Lines[i+1]:
                ans+=1
            #ㄱ
            if Lines[i]==Lines[i+1]+2:
                ans+=1
    case 7:
        for i in range(C-2):
            #L_
            if Lines[i]==Lines[i+1] and Lines[i+1]==Lines[i+2]:
                ans+=1
            #-ㄱ
            if Lines[i]==Lines[i+1] and Lines[i+1]==Lines[i+2] + 1:
                ans+=1
        for i in range(C-1):
            #P
            if Lines[i]==Lines[i+1]-2:
                ans+=1
            #J
            if Lines[i]==Lines[i+1]:
                ans+=1

print(ans)