# https://school.programmers.co.kr/learn/courses/30/lessons/181188
# 프로그래머스 - 요격시스템

def solution(targets):
    answer = 0
    targets.sort(key = lambda x: x[1])
    end = 0
    for s, e in targets:
        if s >= end:
            answer += 1
            end = e
    return answer
