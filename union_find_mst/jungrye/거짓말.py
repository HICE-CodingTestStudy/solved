# 진실을 아는 사람이 파티에 왔을떈 진실만 말해야됨
# a파티에선 진실을 듣고 b파티에선 구라를 들어도 안됨
# N: 사람수
# 진실 아는 사람 주어짐
# 각 파티에 오는 사람들의 번호가 주어짐
# 지민이는 모든 파티 참가
# 지민이가 거짓말쟁이로 알려지지 않으면서 과장된 이야기 할 수 있는 파티 개수

import sys

# 1. 사람수, 파티수
N, M = map(int, input().split())
parent = [-1 for _ in range(N + 1)]
height = [0 for _ in range(N + 1)]  # (루트 노드만?) 높이
graph = []
num_party = 0


def find(u):
    if parent[u] == -1:
        return u
    parent[u] = find(parent[u])
    return parent[u]


def union(u, v):
    u = find(u)
    v = find(v)
    if u == v:
        return False
    if height[v] > height[u]:
        temp = u
        u = v
        v = temp
    if height[v] == height[u]:
        height[v] += 1
    parent[v] = u
    height[v] = 0
    return True


def get_party_num():
    global graph
    global num_party

    for i in graph:
        is_zero = False
        for j in i:
            if find(j) == 0:
                is_zero = True
        global num_party
        if is_zero == False:
            num_party += 1


# 3. 파티오는사람수, 사람번호
def get_party_info():
    global graph

    for _ in range(M):
        party_people = list(map(int, input().split()))
        graph.append(party_people[1:])
        for i in range(1, len(party_people)):
            union(party_people[1], party_people[i])
    get_party_num()


# 2. 진실을 아는 사람의 수와 번호
know = list(map(int, input().split()))
n_know = 0
if know[0] == 0:
    get_party_info()
    print(M)
    exit(0)
else:
    n_know = know[0]
    for i in range(1, len(know)):
        parent[know[i]] = 0
    height[0] = 1
    get_party_info()


print(num_party)
