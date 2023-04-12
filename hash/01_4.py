# 프로
def solution(clothes):
    answer = len(clothes)
    myDict = {}
    for item in clothes:
        if item[1] in myDict:
            myDict[item[1]] += 1
        else:
            myDict[item[1]] = 1
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
