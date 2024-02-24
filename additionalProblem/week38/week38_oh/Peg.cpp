
#include <iostream>
using namespace std;

int t, ans, pin;
int dx[] = { -1,1,0,0 }, dy[] = { 0,0,-1,1 };
char arr[7][11];

void dfs(int cnt) {
    for (int i = 1; i <= 5; i++)
        for (int j = 1; j <= 9; j++)
            if (arr[i][j] == 'o')
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d], ny = j + dy[d];
                    int nnx = nx + dx[d], nny = ny + dy[d];
                    if (arr[nx][ny] == 'o' && arr[nnx][nny] == '.') {
                        arr[nnx][nny] = 'o';
                        arr[nx][ny] = '.';
                        arr[i][j] = '.';
                        dfs(cnt + 1);
                        arr[nnx][nny] = '.';
                        arr[nx][ny] = 'o';
                        arr[i][j] = 'o';
                    }
                }
    ans = min(ans, pin - cnt);
}

int main() {
    cin >> t;

    for (int T = 0; T < t; T++) {
        ans = 9, pin = 0;
        for (int i = 1; i <= 5; i++)
            for (int j = 1; j <= 9; j++) {
                cin >> arr[i][j];
                if (arr[i][j] == 'o') pin++;
            }

        dfs(0);

        cout << ans << " " << pin - ans << '\n';
    }
}