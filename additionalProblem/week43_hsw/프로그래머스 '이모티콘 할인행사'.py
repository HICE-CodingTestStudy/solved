# 프로그래머스 '이모티콘 할인행사'
# 2023 KAKAO BLIND RECRUITMENT
from itertools import product

def solution(users, emoticons):
    discount = [40, 30, 20, 10]
    num_membership, max_cost = 0, 0
    ans = [0, 0]

    #할인율의 모든 조합의 경우를 생성
    dis_case = list(product(discount, repeat = len(emoticons)))
    
    for dis in dis_case:
        num_membership, max_cost = 0, 0
        
        for userinfo in users:
            limit_discount, limit_price = map(int, userinfo)
            
            # 누적금액 계산
            price, flag = 0, False           
            for i in range(len(dis)):
                if limit_discount <= dis[i]:
                    price += emoticons[i] * (100 - dis[i]) / 100
                # 기준 초과시 탈출
                if price >= limit_price:
                    flag = True
                    break
            
            # 기준 초과로 탈출시 멤버십 인원 추가 및 금액 0으로 갱신
            if flag:
                price = 0
                num_membership += 1
            # 누적금액 합산
            max_cost += price
            
        # 1번 목표 갱신시
        if num_membership > ans[0]:
            ans = [num_membership, max_cost]
        elif num_membership == ans[0]:
            ans[1] = max(ans[1], max_cost)

    return ans