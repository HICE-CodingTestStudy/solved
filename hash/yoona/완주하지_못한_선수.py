from collections import Counter


def solution(participant, completion):
    answer = ''
    
    answer = Counter(participant)-Counter(completion) # 일반적인 dictioanry는 뺄셈이 안되지만, Counter는 뺄셈이 가능함!!!⭐⭐⭐⭐⭐
    
    return list(answer.keys())[0] # dictionary의 key 값은 not subscriptable -> list로 변경 후 주소 값 가져오기