def solution(participant, completion):
    myDict = {}

    # 침가자를 {"이름": 인원수} 꼴로 dict에 저장.
    for part in participant:
        if part in myDict:
            myDict[part] += 1
        else:
            myDict[part] = 1

    # 완주자 받아서 인원수 하나씩 줄임
    for comp in completion:
        if comp in myDict:
            myDict[comp] -= 1

    # 인원이 남아있는 이름(key)이 답임.
    temp = [key for key, v in myDict.items() if v == 1]

    answer = temp[0]
    return answer


participant = ["mislav", "stanko", "mislav", "ana"]
completion = ["stanko", "ana", "mislav"]
print(solution(participant, completion))
