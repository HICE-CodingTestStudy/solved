# 프로그래머스 '과제 진행하기'
def solution(plans):
    # 선 정렬 후 변환
    plans.sort(key = lambda x: x[1])
    plans = [(name, 60 * int(start.split(':')[0]) + int(start.split(':')[1]), int(playtime)) for name, start, playtime in plans]
    
    
    ans = []
    #해결 못한 문제는 스택에 쌓음
    stack = []
    for i in range(len(plans)):
        #종료 조건
        if i == len(plans)-1:
            ans.append(plans[i][0])
            #스택 역으로 순회하면서 정답에 넣기
            for j in range(-1, -len(stack)-1, -1):
                ans.append(stack[j][0])
            break
        
        time_left = plans[i+1][1] - plans[i][1] - plans[i][2]
        # 여유시간 비교
        # 해결 가능시
        if time_left >= 0:
            ans.append(plans[i][0])
            while stack:
                if stack[-1][1] <= time_left:
                    temp = stack.pop()
                    ans.append(temp[0])
                    time_left -= temp[1]
                else:
                    stack[-1][1] -= time_left
                    break
        # 해결 X -> 다시 스택에 쌓음
        else:
            stack.append([plans[i][0], -time_left])            
    return ans
