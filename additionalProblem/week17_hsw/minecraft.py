def solution(picks, minerals):
    answer = 0
    
    num = int(sum(picks)) * 5
    
    if len(minerals) > num:
        minerals = minerals[:num]
    
    arr = [[0,0,0] for _ in range(10)]
    for i in range(len(minerals)):
        if minerals[i] == "diamond":
            arr[i//5][0] += 1
        elif minerals[i] == "iron":
            arr[i//5][1] += 1
        else: #minerals[i] == "stone"
            arr[i//5][2] += 1
        
    arr.sort(key = lambda x : (-x[0], -x[1], -x[2]))
    
    for i in arr:
        diamond, iron, stone = i
        for j in range(len(picks)):
            if picks[j]>0 and j==0:
                picks[j] -= 1
                answer += diamond + iron + stone
                break
            elif picks[j]>0 and j == 1:
                picks[j] -= 1
                answer += diamond * 5 + iron + stone
                break
            elif picks[j]>0 and j==2:
                picks[j] -= 1
                answer += diamond * 25 + iron * 5 + stone
                break
    return answer
