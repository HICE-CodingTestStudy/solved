#include <bits/stdc++.h>
using namespace std;
int freq[1000002], sum[1000002];
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    int N, Q;
    cin >> N;
    for(int i = 0; i < N; i++) {
        int S, E;
        cin >> S >> E;
        freq[S]++;
        freq[E + 1]--;
    }
    
    for(int i = 1; i <= 1000000; i++) {
        sum[i] = sum[i - 1] + freq[i];
    }

    cin >> Q;
    for(int i = 0; i < Q; i++) {
        int T;
        cin >> T;
        cout << sum[T] << "\n";
    }
}