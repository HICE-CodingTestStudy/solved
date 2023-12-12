
#include <string>
#include <vector>

using namespace std;

int dx1[] = {1, -1, 0, 0, -2, 0, 2, 0}, dy1[] = {0, 0, 1, -1, 0, 2, 0, -2};
int dx2[] = {-1, 1, 1, -1}, dy2[] = {1, 1, -1, -1};
vector<int> ans;

void sub(vector<string> v) {
    for (int x = 0; x < 5; x++)
        for (int y = 0; y < 5; y++)
            if (v[x][y] == 'P') {
                for (int d = 0; d < 8; d++) {
                    int nx = x + dx1[d], ny = y + dy1[d];
                    if (nx < 0 || ny < 0 || nx > 4 || ny > 4) continue;
                    if (v[nx][ny] == 'P' && v[(nx + x) / 2][(ny + y) / 2] != 'X') {
                        ans.push_back(0);
                        return;
                    }
                }
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx2[d], ny = y + dy2[d];
                    if (nx < 0 || ny < 0 || nx > 4 || ny > 4) continue;
                    if (v[nx][ny] == 'P' && (v[x][ny] != 'X' || v[nx][y] != 'X')) {
                        ans.push_back(0);
                        return;
                    }
                }
            }
    ans.push_back(1);
}

vector<int> solution(vector<vector<string>> places) {
    for (int i = 0; i < places.size(); i++) {
        vector<string> v = places[i];
        sub(places[i]);
    }
    return ans;
}