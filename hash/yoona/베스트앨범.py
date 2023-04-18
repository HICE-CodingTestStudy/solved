from collections import defaultdict

def solution(genres, plays):
    answer = []
    hash_table = defaultdict(list)
    total = defaultdict(int)
    
    for index, genre in enumerate(genres):
        hash_table[genre].append((index, plays[index]))
        total[genre] += plays[index]
    
    sorted_total = sorted(total.items(), key = lambda x : x[1], reverse = True)
    
    for genre_total in sorted_total:
        values = hash_table[genre_total[0]]
        
        if len(values) < 2:
            answer.append(values[0][0])
        else:
            sorted_values = sorted(values, key = lambda x : x[1], reverse = True)
            answer.append(sorted_values[0][0])
            answer.append(sorted_values[1][0])
    return answer