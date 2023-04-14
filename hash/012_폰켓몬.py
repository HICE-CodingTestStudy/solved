def solution(nums):
    myDict = {}

    # {"종류": 마리수} 꼴로 dict에 저장.
    for i in nums:
        if i in myDict:
            myDict[i] += 1
        else:
            myDict[i] = 1

    # 종류 수가 (마리수)/2보다 적거나 같으면 종류 수가 답.
    if len(nums)/2 >= len(myDict):
        answer = len(myDict)
    else:
        answer = len(nums)/2
    return answer
