# BOJ 1091 카드 섞기
import sys
input = sys.stdin.readline

def shuffle(state,s):
    return [state[i] for i in s]

if __name__ == "__main__":
    n = int(input())
    p = list(map(int, input().split()))
    s = tuple(map(int, input().split()))
    
    current = list(range(n))
    positions = set()
    cnt = 0
    finished = False
    
    while tuple(current) not in positions:
        positions.add(tuple(current))
        
        if all(p[i] == current[i] % 3 for i in range(n)):
            print(cnt)
            finished = True
            break
        
        current = shuffle(current, s)
        cnt += 1
    
    if not finished:
        print(-1)
        
        
    
    
