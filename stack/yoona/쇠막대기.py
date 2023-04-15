import sys

strings = sys.stdin.readline().strip()  # readline()은 개행문자 포함 -> strip()으로 공백 제거
lasers = []  # 레이저 위치 저장
pipes = []  # 파이프 위치 저장
stack = []

for index, string in enumerate(strings):
    if string == "(":
        stack.append(index)
        continue

    start = stack[-1]
    stack.pop()

    # 위치 차이가 1이면 레이저
    if start + 1 == index:
        lasers.append((start, index))

    # 위치 차이가 1이상 이면 파이프
    else:
        pipes.append((start, index))

answer = 0
for pipe in pipes:
    cnt = 0
    for laser in lasers:
        # 레이저가 파이프 사이에 있으면 잘림 횟수 추가
        if pipe[0] < laser[0] < laser[1] < pipe[1]:
            cnt += 1
    # 토막의 총 횟수는 잘린 횟수 +1
    answer += cnt + 1

print(answer)
