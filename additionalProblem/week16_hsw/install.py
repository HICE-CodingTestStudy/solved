# 기지국 설치
import math

def solution(n, stations, w):
    answer = 0
    first = 1
    for s in stations:
        answer += max(math.ceil((s - w - first) / (2 * w + 1)),0)
        first = s + w + 1
    
    if n >= first:
        answer += math.ceil((n - first + 1) / (2 * w + 1))
        
    return answer
