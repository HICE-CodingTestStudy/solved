from collections import defaultdict
def solution(phone_book):
    answer = True
    
    phone_book.sort(key=lambda x : len(x)) # 길이순으로 정렬
    
    hash_table = defaultdict(int)
    for phone_number in phone_book:
        if not hash_table:
            hash_table[phone_number] += 1 # 첫 원소(제일 짧은)는 무조건 추가
        
        else:
            heads = hash_table.keys()
            print(heads)
            for head in heads:
                print(head)
                if phone_number.startswith(head):
                    return False
            
    return answer

solution(["119", "97674223", "1195524421"])