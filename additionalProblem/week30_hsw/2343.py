#BOJ 2343
import sys
input = sys.stdin.readline

def count_bluray_size(videos, mid):
    count = 1  # 초기 블루레이 개수
    current_size = 0

    for video in videos:
        # 현재 블루레이에 강의 추가 가능한 경우
        if current_size + video <= mid:
            current_size += video
        else:
            # 새로운 블루레이에 강의 추가
            count += 1
            current_size = video

    return count

def get_min_size(videos, M):
    # 이분 탐색의 초기 범위 설정
    left, right = max(videos), sum(videos)

    while left < right:
        # 중간값 계산
        mid = (left + right) // 2
        # 현재 블루레이 크기로 가능한 블루레이 개수 구하기
        cnt = count_bluray_size(videos, mid)

        if cnt <= M:
            # 해당 cnt가 M 이하인 경우, 오른쪽 범위를 줄임
            right = mid
        else:
            # 해당 cnt가 M을 초과하는 경우, 왼쪽 범위를 늘림
            left = mid + 1

    # 최소 블루레이 크기 반환
    return left

N, M = map(int, input().split())
videos = list(map(int, input().split(' ')))

# 최소 블루레이 크기 구하기
ans = get_min_size(videos, M)

print(ans)