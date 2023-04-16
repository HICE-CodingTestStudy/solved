# 프로
def solution(phone_book):
    # 전화번호부 오름차순 정렬
    myList = sorted(phone_book)
    answer = True

    # 첫번째인덱스를 temp에 저장
    temp = myList[0]

    # 전화번호가 temp로 시작하면(접두어면) False.
    # temp로 시작하는 번호가 없으면 temp를 업데이트해줌.
    for i in range(1, len(myList)):
        if myList[i].startswith(temp):
            answer = False
            break
        else:
            temp = myList[i]

    return answer


print(solution(["12", "123", "1235", "567", "88"]))
