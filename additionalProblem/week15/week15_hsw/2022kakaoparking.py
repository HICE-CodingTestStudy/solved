from datetime import datetime
import math

# fees
# [기본시간(분), 기본요금(원), 단위시간(분), 단위요금(원)]

# records
# ["시각 차량번호 입출내역", ...]

def solution(fees, records):
    # {차량번호 : 누적시간} 형태의 딕셔너리
    time_dic = {record.split()[1] : 0 for record in records}
    # {차량번호 : 입차시간} 형태의 딕셔너리
    car = {}
    
    # 입출 기록에 따른 주차요금 계산
    for record in records:
        time, car_num, inout = record.split()
        
        if inout == 'IN':   #입차시간 입력
            car[car_num] = datetime.strptime(time, "%H:%M")
        else: #inout == 'OUT'
            total_time = str((datetime.strptime(time, "%H:%M")) - car[car_num])
            total_time = int(total_time.split(':')[0]) * 60 + int(total_time.split(':')[1])
            time_dic[car_num] += total_time
            #출차 후 기록 제거
            del car[car_num]
            
    # 23:59 계산            
    if len(car)>0:
        for car_num in car:
            total_time = str(datetime.strptime('23:59', "%H:%M") - car[car_num])
            total_time = int(total_time.split(':')[0]) * 60 + int(total_time.split(':')[1])
            time_dic[car_num] += total_time
    #------------ 누적 주차시간 계산 완료---------------#
    
    # 주차요금 계산
    answer = sorted(time_dic.items()) # 차량번호가 작은 자동차부터 정렬
    
    # fees
    # [기본시간(분), 기본요금(원), 단위시간(분), 단위요금(원)]
    
    for i in range(len(answer)):
        if answer[i][1] <= fees[0]: # 기본 시간 이하
            answer[i] = fees[1]
        else:   # 기본 시간 초과
            answer[i] = fees[1] + math.ceil((answer[i][1] - fees[0])/fees[2])*fees[3]
    return answer
