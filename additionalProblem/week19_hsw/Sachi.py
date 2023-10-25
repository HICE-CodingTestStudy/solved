def solution(gems):
    N = len(gems); T = len(set(gems))
    lp, rp = 0, 0
    dic = {gems[0] : 1}
    answer = [0, N]
    
    while lp < N and rp < N:
        # 엑조디아 완성하면
        if len(dic) == T:
            # 크기 비교 후 작다면 갱신
            if (rp - lp) < (answer[1] - answer[0]):
                answer = [lp , rp]
            # 그 외엔 lp 증가 및 dic 개수 감소
            else:
                dic[gems[lp]] -= 1
                if dic[gems[lp]] == 0:
                   del dic[gems[lp]]
                lp += 1
        # 종류가 부족하면
        else:
            # rp증가
            rp += 1
            if rp == N:
                break
                
            # dic 개수 증가
            dic[gems[rp]] = dic.get(gems[rp], 0) + 1
    
    return [answer[0] + 1, answer[1] + 1]
