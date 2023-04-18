from collections import defaultdict

def solution(id_list, report, k):
    answer = []
    hash_table = defaultdict(list)
    hash_table_2 = defaultdict(int)
    
    for i in report:
        user_id, report_id = i.split()
        hash_table[report_id].append(user_id)
    
    for key, value in hash_table.items():
        value = set(value)
        
        if len(value)>=k:
            for name in value:
                hash_table_2[name] += 1
    
    for i in id_list:
        answer.append(hash_table_2[i])
    
    return answer