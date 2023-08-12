def solution(number, k):
    answer = ''
    stack = []

    for i in number:
        if k == 0 or len(stack) == 0:
            stack.append(i)
        else:
            # top = stack.pop()
            top = stack[-1]
            while (True):
                # i가 더 큼 -> stack pop, i push
                if k > 0 and top < i:
                    k -= 1
                    stack.pop()
                    if len(stack) != 0:
                        top = stack[-1]
                    else:
                        stack.append(i)
                        break
                # top이 더 큼 -> i push만
                else:
                    stack.append(i)
                    break
    while (k > 0):
        k -= 1
        stack.pop()
    answer = ''.join(stack)
    return answer


# print(solution("1924", 2))
# print(solution("1231234", 3))
print(solution("4177252841", 4))  # 775841
print(solution("4321", 1))
