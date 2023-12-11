
#include <iostream>
#include <queue>
using namespace std;

struct point
{
    int x; int y;
};
int t, n;
point home, fest, store[100];
bool v[100];

bool bfs(int n) {
    queue<pair<int, int>> q;
    q.push({home.x, home.y});

    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();

        if (abs(fest.x - x) + abs(fest.y - y) <= 1000)
            return true;
        
        for (int i = 0; i < n; i++) {
            if (v[i]) continue;
            if (abs(store[i].x - x) + abs(store[i].y - y) <= 1000) {
                v[i] = 1;
                q.push({store[i].x, store[i].y});
            }
        }
    }
    return false;
}

int main() {
    cin >> t;

    while(t--) {
        cin >> n;
        cin >> home.x >> home.y; 
        for (int i = 0; i < n; i++)
            cin >> store[i].x >> store[i].y;
        cin >> fest.x >> fest.y;

        bfs(n) ? cout << "happy\n" : cout << "sad\n";

        fill(v, v + 100, false);
    }
}
