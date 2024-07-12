#include <bits/stdc++.h>
using namespace std;
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int N, X;
    cin >> N >> X;
    int sum[N + 1] = {};
    for(int i = 0; i < N; i++) {
        int cnt;
        cin >> cnt;
        sum[i + 1] = sum[i] + cnt;
    }

    int max_cnt = 0, periods = 0;
    for(int i = 0; i <= N - X; i++) {
        if(sum[i + X] - sum[i] == max_cnt) periods++;
        else if(sum[i + X] - sum[i] > max_cnt) {
            max_cnt = sum[i + X] - sum[i];
            periods = 1;
        }
    }

    if(max_cnt == 0) cout << "SAD";
    else cout << max_cnt << "\n" << periods;
}