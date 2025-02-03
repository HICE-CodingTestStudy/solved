# BOJ 1195 킥다운

import sys
input = sys.stdin.readline

if __name__ == "__main__":
    gear1 = input().strip()
    gear2 = input().strip()
    
    L1 = len(gear1)
    L2 = len(gear2)
    A, B = gear1, gear2
    
    min_width = L1 + L2
    for x in range(-L2, L1 + 1):
        valid = True

        start = max(0, x)
        end = min(L1, x + L2)
        
        for i in range(start, end):
            if A[i] == '2' and B[i - x] == '2':
                valid = False
                break 
        
        if valid:
            current_width = max(L1, x + L2) - min(0, x)
            if current_width < min_width:
                min_width = current_width

    print(min_width)         
            
