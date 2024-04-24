# 프로그래머스- 가장 긴 팰린드롬
s = 'abcdcba'
answer = 7
    
def solution(s):
    ans = 1                       
    for i in range(len(s)):          
        for j in range(len(s), i, -1):
            if j-i < ans:
                break
            
            if s[i:j] == s[i:j][::-1]: 
                ans = j-i
                break
    return ans

def sol(s):
    l, r, ans = 0, 0, 0
    while l<len(s) and r<len(s):
        ll, rr = l, r
        while 0<=ll and rr<len(s):
            if s[ll] == s[rr]:
                ans = max(ans, rr-ll+1)
                ll -= 1
                rr += 1
            else:
                break
        if l == r:
            r += 1
        else:
            l += 1
    return ans
    
if __name__ == '__main__':
    
    solution(s)