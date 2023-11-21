# 최고의집합

def solution(n, s):
    answer = []
    if n>s:
        return [-1]
    for _ in range(n):
        answer.append(s//n)
    i = len(answer)-1
    for _ in range(s%n):
        answer[i] += 1
        i -= 1   
    
    return answer
