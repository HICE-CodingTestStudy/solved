#BOJ 5107

import sys
input = sys.stdin.readline

def count_chains(graph):
    def dfs(node, start):
        visited.add(node)
        for person in graph[node]:
            if person == start:
                continue  # 시작 노드로 되돌아가는 간선 무시
            if person not in visited:
                dfs(person, start)

    chain_count = 0
    visited = set()

    for person in graph:
        if person not in visited:
            dfs(person, person)  # 시작 노드를 인수로 전달
            chain_count += 1

    return chain_count


test_case = 1

while True:
    N = int(input())
    if N == 0:
           break

    names = [input() for _ in range(N)]
    graph = {}

    for i in range(N):
        sender, receiver = names[i].split()
        if sender not in graph:
            graph[sender] = []
        if receiver not in graph:
            graph[receiver] = []
        graph[sender].append(receiver)
        graph[receiver].append(sender)

    chain_count = count_chains(graph)
    print(f"{test_case} {chain_count}")
    test_case += 1


