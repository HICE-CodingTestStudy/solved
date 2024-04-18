# BOJ 2467 용액
import sys
input = sys.stdin.readline

# 변수
#n = 5
#solution = [-99,-2,-1,4,98]

temp, tempa, tempb = int(1e9), 0, 0
    
# main
if __name__ == "__main__":
    n = int(input())
    solution = list(map(int, input().split()))
    
    l, r = 0, n-1
    tempa, tempb = l, r
    temp = abs(solution[l]+solution[r])   
    while l<r:
        c = solution[l] + solution[r]
        
        if abs(c) <= temp:
            temp = abs(c)
            tempa, tempb = l, r
            if temp == 0:
                break
        if c < 0:
            l += 1
        else:
            r -= 1
    
    print(solution[tempa], solution[tempb])