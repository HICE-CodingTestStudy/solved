def solution(answers):
    answer = []
    person1 = [1, 2, 3, 4, 5]
    person2 = [2, 1, 2, 3, 2, 4, 2, 5]
    person3 = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]

    score = [0, 0, 0]

    for index in range(len(answers)):
        if person1[index % len(person1)] == answers[index]:
            score[0] += 1

        if person2[index % len(person2)] == answers[index]:
            score[1] += 1

        if person3[index % len(person3)] == answers[index]:
            score[2] += 1

    for i, s in enumerate(score):
        if s == max(score):
            answer.append(i + 1)

    return answer
