##⭐⭐⭐순열로 모든 경우의 수를 만든다면 시간 초과 발생 !!
##⭐⭐⭐모든 문자열을 *3 하여 비교하기 !!
##⭐⭐⭐["6", "10", "2"]를 사전 순 배열하면 ["10", "2", "6"]


def solution(numbers):
    input_list = list(map(str, numbers))
    input_list.sort(key=lambda x: x * 3, reverse=True)

    answer = str(int("".join(input_list)))
    return answer
