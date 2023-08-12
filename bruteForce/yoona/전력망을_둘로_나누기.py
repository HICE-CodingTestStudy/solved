# 풀이 참조
import copy


def solution(n, wires):
    parent = [0] * (n + 1)

    # parent 노드를 자기 자신으로 초기화
    for i in range(1, n + 1):
        parent[i] = i

    # 부모가 누구인지 찾기
    def find_parent(x, parent):
        if parent[x] != x:
            return find_parent(parent[x], parent)
        else:
            return parent[x]

    # 노드 합치기
    def union(a, b, parent):
        a = find_parent(a, parent)
        b = find_parent(b, parent)
        if a < b:
            parent[b] = a
        else:
            parent[a] = b

    res = n
    for i in range(len(wires)):
        parent_copy = copy.deepcopy(parent)
        for j in range(len(wires)):  # 와이어 하나씩 빼기
            if i == j:
                continue

            a, b = wires[j]
            union(a, b, parent_copy)

        # 이 for문은 왜 돌리는지 모르겠으나 안돌리면 정답이 안나옴...테케 3개에서는 안해도 되지만 실제 부분에서는 음........
        for a, b in wires:
            parent_copy[a] = find_parent(a, parent_copy)
            parent_copy[b] = find_parent(b, parent_copy)

        res = min(
            abs(
                parent_copy.count(parent_copy[wires[i][0]])
                - parent_copy.count(parent_copy[wires[i][1]])
            ),
            res,
        )

    return res
