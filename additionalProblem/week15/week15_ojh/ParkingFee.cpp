#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int cnt, cost[10001], t[10001];
bool flag[10001];

int* solution(int fees[], size_t fees_len, const char* records[], size_t records_len) {
    
    for (int i = 0; i < records_len; i++) {
        int time = ((records[i][0] - '0') * 10 + records[i][1] - '0') * 60 +
            (records[i][3] - '0') * 10 + records[i][4] - '0';
        
        int carNum = 0;
        for (int j = 6, digit = 1000; j < 10; j++, digit /= 10)
            carNum += (records[i][j] - '0') * digit;
        
        if (records[i][11] == 'I') {
            t[carNum] -= time;
            flag[carNum] = 1;
        }
        else if (records[i][11] == 'O') {
            t[carNum] += time;
            flag[carNum] = 0;
        }
    }
    
    for (int i = 0; i < 10000; i++) {
        if (flag[i]) {
            // 23:59, 23 * 60 + 59 = 1439
            t[i] += 1439;
            flag[i] = 0;
        }
        if (t[i]) cnt++;
    }
    
    for (int i = 0; i < 10000; i++) {
        if (t[i]) {
            int a = 0, b = 0;
            a = t[i] >= fees[0] ? 1 : 0;
            b = (t[i] - fees[0] * a) / fees[2] + ((t[i] - fees[0] * a) % fees[2] > 0);
            cost[i] = a ? a * fees[1] + b * fees[3] : fees[1];
        }
    }
    
    int* answer = (int*)malloc(sizeof(int) * cnt);
    
    for (int i = 0, j = 0; i < 10000; i++)
        if (t[i]) {
            answer[j] = cost[i];
            j++;
        }

    return answer;
}
