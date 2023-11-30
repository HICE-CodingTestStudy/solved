
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int n, m, x, y, a, b, ans, light[101][101], v[101][101], can[101][101];
int dx[] = { 0,0,1,-1 }, dy[] = { 1,-1,0,0 };
struct Point { int x, y; };
vector <Point> room[101][101];

int bfs(){
    v[1][1] = light[1][1] = ans = 1;
    queue <pair<int,int>> q;
    q.push({1,1});
    
    while (!q.empty()) {
        int x = q.front().first, y = q.front().second;
        q.pop();
        
        for (auto r : room[x][y])
            ans += !light[r.x][r.y]++;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (nx && ny && nx <= n && ny <= n)
                can[nx][ny] = 1;
        }
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                if (light[i][j] && !v[i][j] && can[i][j]) {
                    v[i][j] = 1;
                    q.push({i,j});
                }
    }

    return ans;
}

int main(){
    cin >> n >> m;
    
    while(m--){
        cin >> x >> y >> a >> b;
        room[x][y].push_back({a,b});
    }
    
    cout << bfs();
}