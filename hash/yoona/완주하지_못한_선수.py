from collections import defaultdict


def solution(participant, completion):
    answer = ''
    
    hash_table = defaultdict(int)
    
    for p in participant:
        hash_table[p] += 1
    
    for c in completion:
        hash_table[c] -= 1
    
    for key, value in hash_table.items():
        if value > 0:
            answer = key
            break
    
    return answer