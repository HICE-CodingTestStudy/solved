# 프로
def solution(participant, completion):
    myDict = {}

    for part in participant:
        if part in myDict:
            myDict[part] += 1
        else:
            myDict[part] = 1

    for comp in completion:
        if comp in myDict:
            myDict[comp] -= 1

    temp = [key for key, v in myDict.items() if v == 1]

    answer = temp[0]
    return answer


participant = ["mislav", "stanko", "mislav", "ana"]
completion = ["stanko", "ana", "mislav"]
print(solution(participant, completion))
