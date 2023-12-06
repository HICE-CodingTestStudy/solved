#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N, M, nHackable[10001];
vector<int> graph[10001];

void parseInput() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N >> M;

    for (int i = 0 ; i < M; i++) {
        int A, B;
        cin >> A >> B;
        graph[A - 1].push_back(B - 1);
    }
}

void printAnswer(int max) {
    for (int i = 0; i < N; i++) {
        if (nHackable[i] == max) {
            cout << i + 1 << " ";
        }
    }
}

void bfs(int start) {
    queue<int> queue;
    int visited[10001] = {0,};
    queue.push(start);
    visited[start] = 1;

    while (!queue.empty()) {
        int n = queue.front(); queue.pop();

        for (int i = 0; i < graph[n].size(); i++) {
            int next = graph[n].at(i);
            if (visited[next]) continue;

            nHackable[next]++;
            queue.push(next);
            visited[next] = 1;
        }
    }
}

int solution() {
    for (int i = 0; i < N; i++) {
        bfs(i);
    }

    int max = -1;
    for (int i = 0; i < N; i++) {
        if (nHackable[i] > max) {
            max = nHackable[i];
        }
    }

    return max;
}

int main() {
    parseInput();
    int max = solution();
    printAnswer(max);
}