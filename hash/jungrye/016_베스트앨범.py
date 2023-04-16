def solution(genres, plays):
    genDict = {}  # 장르
    playDict = {}  # 길이
    totalDict = {}  # 장르별 노래 수
    myDict = {}  # 디테일한 정보들 들어있음
    answer = []

    # 속한 노래 많은 장르 찾기
    for i in range(len(genres)):
        if (genres[i] in totalDict):
            totalDict[genres[i]] += plays[i]
        else:
            totalDict[genres[i]] = plays[i]
        genDict[i] = genres[i]
        playDict[i] = plays[i]
    totalDict = dict(
        sorted(totalDict.items(), key=lambda x: x[1], reverse=True))

    # myDict 만들기, 정렬
    for i in totalDict:
        keys = [k for k, v in genDict.items() if v == i]
        myDict[i] = []
        for j in keys:
            myDict[i].append({"numOfPlay": playDict[j], "index": j})

    # 노래수 기준 내림차순 정렬 후 장르별 최대 2곡을 answer에 추가.
    for i in myDict:
        tempArr = myDict[i]
        tempArr = sorted(tempArr, key=lambda x: x['numOfPlay'], reverse=True)
        if len(tempArr) == 1:
            answer.append(tempArr[0]['index'])
        else:
            answer.append(tempArr[0]['index'])
            answer.append(tempArr[1]['index'])

    return answer


print(solution(["classic", "pop", "classic", "kpop", "kpop",
      "classic", "pop"], [500, 600, 150, 800, 2500, 1565, 5341]))

# myDict 모양
# {
#   '장르1':
#       [
#         {'노래수': n, '고유번호': idx},
#         {'노래수': n, '고유번호': idx},
#         {'노래수': n, '고유번호': idx},
#         {'노래수': n, '고유번호': idx},
#       ],
#   '장르2':
#       [
#         {'노래수': n, '고유번호': idx},
#         {'노래수': n, '고유번호': idx},
#         {'노래수': n, '고유번호': idx},
#         {'노래수': n, '고유번호': idx},
#       ],
# }
