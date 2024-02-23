#Programmers 2024 KAKAO WINTER INTERNSHIP 도넛과 막대 그래프

# 풀이 https://velog.io/@mino0121/ProgrammersPython-도넛과-막대그래프
# 각 그래프에 대하여 해당 그래프임을 판별할 수 있는 특정한 조건을 지닌 노드가 있는지를 확인해야함.

def solution(edges):

# edges 입력을 바탕으로, 정점에 출입하는 횟수 초기화 및 카운팅
edge = {}
for a, b in edges:
    if not edge.get(a):
        edge[a] = [0, 0]
    if not edge.get(b):
        edge[b] = [0, 0]
    edge[a][0] += 1
    edge[b][1] += 1

answer = [0,0,0,0]
for key, counts in edge.items():
    # 생성된 정점 번호 체크, 
    # 최초 점은 나가는 간선만 2 이상, 들어오는 간선은 없음
    if counts[0] >= 2 and counts[1] == 0:
        answer[0] = key
    # 막대 그래프 수 체크
    # 들어오는 간선이 있지만 나가는 간선은 없음
    elif counts[0] == 0 and counts[1] > 0:
        answer[2] += 1
    # 8자 모양
    # 들어오는 간선, 나가는 간선 모두 각각 2개 이상
    elif counts[0] >= 2 and counts[1] >= 2:
        answer[3] += 1

# 위에 해당이 안되는 나머지 그래프는 도넛
answer[1] = (edge[answer[0]][0] - answer[2] - answer[3])

return answer
