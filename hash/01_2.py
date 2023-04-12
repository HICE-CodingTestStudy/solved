# 프로
def solution(nums):
    myDict = {}

    for i in nums:
        if i in myDict:
            myDict[i] += 1
        else:
            myDict[i] = 1

    if len(nums)/2 >= len(myDict):
        answer = len(myDict)
    else:
        answer = len(nums)/2
    return answer
