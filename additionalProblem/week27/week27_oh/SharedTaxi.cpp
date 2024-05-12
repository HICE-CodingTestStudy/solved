
#include <string>
#include <vector>

using namespace std;

int graph[201][201];

int solution(int n, int s, int a, int b, vector<vector<int>> fares) {
    int ans = 1e9;

    fill(graph[0], graph[201], 1e8);

    for (int i = 0; i <= n; i++)
        graph[i][i] = 0;

    for (int i = 0; i < fares.size(); i++) {
        graph[fares[i][0]][fares[i][1]] = fares[i][2];
        graph[fares[i][1]][fares[i][0]] = fares[i][2];
    }

    for (int k = 1; k <= n; k++)
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j]);

    ans = graph[s][a] + graph[s][b];
    for (int i = 1; i <= n; i++)
        ans = min(ans, graph[s][i] + graph[i][a] + graph[i][b]);

    return ans;
}
