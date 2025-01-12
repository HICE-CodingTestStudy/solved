# BOJ 1421 나무꾼 이다솜
import sys
input = sys.stdin.readline

if __name__ == "__main__":
    n, c, w = map(int,input().split())
    tree_lengths = [int(input()) for _ in range(n)]
    
    def sol(l):
        total = 0
        for tree in tree_lengths:
            if tree < l:
                continue
            
            cuts = tree // l - (1 if tree % l == 0 else 0)
            piece = tree // l
            profit = piece * l * w - cuts * c
            total += max(0, profit)
        return total
    
    max_length = max(tree_lengths)
    ans = 0
    
    for length in range(1, max_length+1):
        ans = max(ans, sol(length))
    
    print(ans)