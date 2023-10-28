
#include <string>
#include <vector>
#include <iostream>
using namespace std;

vector<string> splitStr(string s) {  // ',' 기준으로 split
    vector<string> res;
    string tmp = "";
    for (auto c : s) {
        if (c == ',') {
            res.push_back(tmp);
            tmp = "";
        }
        else tmp += c;
    }
    res.push_back(tmp);
    return res;
}

string shorten(string s) {  // X# -> x
    string res = "";
    for (auto c : s) {
        if (c == '#')
            res[res.size() - 1] += 32;
        else res += c;
    }
    return res;
}

int getMinute(string s) {  // 분 단위로 변환
    int res = ((s[0] - '0') * 10 + s[1] - '0') * 60 + (s[3] - '0') * 10 + s[4] - '0';
    return res;
}

string solution(string m, vector<string> musicinfos) {
    string answer = "";
    vector<pair<int, string>> v;
    vector<string> melody;
    vector<vector<string>> strToken;
    
    for (int i = 0; i < musicinfos.size(); i++) {
        strToken.push_back(splitStr(musicinfos[i]));    // ',' 기준으로 split
        int t = getMinute(strToken[i][1]) - getMinute(strToken[i][0]);  // 시간(분)
        string title = strToken[i][2], mel = shorten(strToken[i][3]);  // 노래 제목, 악보 정보
        v.push_back({ t, title });

        string totalMel = "";
        for (int i = 0; i < t / mel.size(); i++) totalMel += mel;
        for (int i = 0; i < t % mel.size(); i++) totalMel += mel[i];
        melody.push_back(totalMel);    // 재생시간 동안의 전체 멜로디 추가
    }

    int runT = 0;
    m = shorten(m);
    for (int i = 0; i < melody.size(); i++)
        if (melody[i].find(m) != string::npos && runT < v[i].first) { // 재생시간 긴 노래제목 저장
            answer = v[i].second;
            runT = v[i].first;
        }

    return answer == "" ? "(None)" : answer;
}
