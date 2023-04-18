def solution(clothes):
    answer = len(clothes)
    myDict = {}

    # {"의상 종류": 의상수} 꼴로 dict에 저장.
    for item in clothes:
        if item[1] in myDict:
            myDict[item[1]] += 1
        else:
            myDict[item[1]] = 1

    # 종류가 하나면 의상 수가 답임. (불필요해보임)
    # 여러 종류면 전체 경우의 수에서 아무것도 입지않은 경우의수 1을 뺀 게 답임.
    if len(myDict) == 1:
        return answer
    else:
        answer = 1
        for v in myDict.values():
            answer *= (v+1)
        return answer-1


clothes = [
    ["a", "headgear"], ["a", "headgear"], ["a", "headgear"], ["a", "headgear"],
    ["b", "eyewear"], ["b", "eyewear"], ["b", "eyewear"],
    ["c", "ss"], ["c", "ss"],
    ["d", "socks"]
]
print(solution(clothes))
