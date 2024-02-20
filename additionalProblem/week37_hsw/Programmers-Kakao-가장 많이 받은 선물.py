friends = ["muzi", "ryan", "frodo", "neo"]
gifts = ["muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"]

#Programmers 2024 KAKAO WINTER INTERNSHIP 가장 많이 받은 선물
def solution(friends, gifts):
    numFriends = len(friends)

    maps = {}
    for f1 in friends:
        maps[f1] = [0,{}]
        for f2 in friends:
            if f1 != f2:
                maps[f1][1][f2] = 0

    for gift in gifts:
        f1, f2 = gift.split()
        maps[f1][0] += 1
        maps[f1][1][f2] += 1
        maps[f2][0] -= 1
        maps[f2][1][f1] -= 1
        
    answer = [0] * numFriends

    for f1, values in maps.items():
        idx = friends.index(f1)
        giftIndex = values[0]

        for f2, cnt in values[1].items():
            if cnt > 0:
                answer[idx] += 1
            elif cnt == 0:
                if giftIndex > maps[f2][0]:
                    answer[idx] += 1

    return max(answer)

solution(friends, gifts)
