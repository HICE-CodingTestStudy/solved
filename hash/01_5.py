# 프로
def solution(phone_book):
    myList = sorted(phone_book)
    answer = True
    temp = myList[0]
    for i in range(1, len(myList)):
        if myList[i].startswith(temp):
            answer = False
            break
        else:
            temp = myList[i]

    return answer


print(solution(["12", "123", "1235", "567", "88"]))
