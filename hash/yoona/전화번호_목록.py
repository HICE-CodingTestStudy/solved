def solution(phone_book):
    answer = True
    
    phone_book = sorted(phone_book)
    
    temp = phone_book[0]
    for index in range(1, len(phone_book)):
        if phone_book[index].startswith(temp):
            return False
        else:
            temp = phone_book[index]
        
    return answer