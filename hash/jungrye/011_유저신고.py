def solution(id_list, report, k):
    deduplicated = {}
    cntDict = {string: 0 for string in id_list}
    repDict = {string: [] for string in id_list}
    answer = []

    # 중복투표 제외
    for rep in report:
        deduplicated[rep] = 1
    repList = deduplicated.keys()

    # Dict에 추가.
    # cntDict 나:신고당한횟수
    # repDict 나:내가신고한사람들
    for rep in repList:
        repFrom, repTo = rep.split()
        cntDict[repTo] += 1
        repDict[repFrom].append(repTo)

    # 신고횟수 k 이상인지 검사
    for id in id_list:
        sum = 0
        for rep in repDict[id]:
            if cntDict[rep] >= k:
                sum += 1
        answer.append(sum)

    return answer


print(solution(["muzi", "frodo", "apeach", "neo"], [
      "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"], 2))
print(solution(['con', 'ryan'], ["ryan con",
      "ryan con", "ryan con", "ryan con"], 3))
