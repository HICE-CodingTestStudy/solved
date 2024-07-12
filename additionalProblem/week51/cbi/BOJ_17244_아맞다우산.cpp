#include<bits/stdc++.h>
#define pii pair<int, int>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, M, E[2], index = 0, dir[4][2] = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    cin >> N >> M;
    
    char house[M][N];
    deque<pii> items;
    for(int i = 0; i < M; i++) {
        for(int j = 0; j < N; j++) {
            cin >> house[i][j];
            if(house[i][j] == 'S') {
                house[i][j] = '0';
                items.push_front({i, j});
            }
            else if(house[i][j] == 'E') {
                E[0] = i;
                E[1] = j;
            }
            else if(house[i][j] == 'X') {
                house[i][j] = '0' + ++index;
                items.push_back({i, j});
            }
        }
    }
    house[E[0]][E[1]] = '0' + index + 1;

    int adj[index + 2][index + 2], perms[index + 1];
    for(int i = 0; i <= index; i++)perms[i] = i;
    for(int type = 0; type < items.size(); type++) {
        pii p = items[type];
        int cnt[M][N];
        for(int i = 0; i < M; i++) {
            fill(cnt[i], cnt[i] + N, -1);
        }
        queue<pii> q;
        cnt[p.first][p.second] = 0;
        q.push({p.first, p.second});
        while(!q.empty()) {
            pii now = q.front();
            q.pop();
            if(now.first == E[0] && now.second == E[1]) continue;
            for(int i = 0; i < 4; i++) {
                int nr = now.first + dir[i][0], nc = now.second + dir[i][1];
                if(cnt[nr][nc] != -1 || house[nr][nc] == '#') continue;
                cnt[nr][nc] = cnt[now.first][now.second] + 1;
                q.push({nr, nc});
                if('1' <= house[nr][nc] && house[nr][nc] <= '6') {
                    adj[type][house[nr][nc] - '0'] = cnt[nr][nc];
                }
            }
        }
    }

    int ans = 1e9;
    do {
        int sum = adj[perms[index]][index + 1];
        for(int i = 0; i < index; i++) {
            sum += adj[perms[i]][perms[i + 1]];
        }
        ans = min(ans, sum);
    } while(next_permutation(perms + 1, perms + index + 1));

    cout << ans;
}