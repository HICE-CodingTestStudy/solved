
#include <iostream>
#include <algorithm>
using namespace std;

int n, m, s, e, arr[100001];

int main() {
    cin.tie(NULL);
    ios_base::sync_with_stdio(false);

    cin >> n >> m;
    for (int i = 0; i < n; i++)
        cin >> arr[i];

    sort(arr, arr + n);

    int tmp, ans = 2000000000;
    while (s < n && e < n) {
        int tmp = arr[e] - arr[s];
        if (tmp < m) 
            e++;
        else {
            ans = min(ans, tmp);
            s++;
        }
    }
    cout << ans;
}
