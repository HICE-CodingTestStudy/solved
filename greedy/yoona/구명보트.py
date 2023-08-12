def solution(people, limit):
    answer = 0
    people = sorted(people)

    start, end = 0, len(people) - 1

    while start < end:
        if people[start] + people[end] <= limit:
            start += 1

        end -= 1
        answer += 1

    # 사람이 남아 있으면
    if start == end:
        answer += 1

    return answer
