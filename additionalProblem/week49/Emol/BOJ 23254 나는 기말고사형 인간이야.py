# BOJ 23254 나는 기말고사형 인간이야
import sys, heapq as hq

input = sys.stdin.readline

if __name__ == "__main__":

    n, m = map(int, input().split())
    totalTime = 24 * n

    a_list = list(map(int, input().split()))
    b_list = list(map(int, input().split()))

    time, answer = 0, 0

    heap = []
    for i in range(m):
        hq.heappush(heap, [-b_list[i], a_list[i]])

    while totalTime - time and heap:
        b, a = hq.heappop(heap)
        b *= -1

        # 남은시간 내로
        if (100 - a) // b < totalTime - time:
            temp = a + (b * ((100 - a) // b))

            # 100점 가능
            if temp >= 100:
                answer += 100

            # 100점 불가
            else:
                hq.heappush(heap, [-(100 - temp), temp])

            time += (100 - a) // b

        else:
            answer += a + (totalTime - time) * b
            time += totalTime - time

    for b, a in heap:
        answer += a
    print(answer)
