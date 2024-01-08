#BOJ 1374

import sys
input = sys.stdin.readline

def sol(n, lectures):
    # 시작시간 기준 정렬
    lectures.sort(key = lambda x: x[1])
    classrooms = []
    for lecture in lectures:
        start_time = lecture[1]

        flag = False
        # 강의 시간 중 종료시간이 가장 빠른 것 찾기
        for i, classroom in enumerate(classrooms):
            if classroom <= start_time:
                # 해당 강의실 사용
                classrooms[i] = lecture[2]
                flag = True
                break
        if not flag:
            # 새 강의실 생성
            classrooms.append(lecture[2])

    return len(classrooms)

N = int(input())
lectures = []
for _ in range(N):
    lecture_info = list(map(int, input().split()))
    lectures.append(lecture_info)

result = sol(N, lectures)
print(result)
    