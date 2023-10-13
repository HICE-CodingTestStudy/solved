package week16.soobin;

public class RadioWaveStation {
    private int countStationsOfSection(int start, int end, int w) {
        int waveCoverage = 2 * w + 1;
        int sectionLength = end - start + 1;
        int stationOfSection = sectionLength / waveCoverage;
        if (sectionLength % waveCoverage != 0) stationOfSection++;

        return stationOfSection;
    }

    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        // 첫 번째 구간
        if (stations[0] - w > 1) {
            int start = 1, end = stations[0] - w - 1;
            answer += countStationsOfSection(start, end, w);
        }

        for (int i = 1; i < stations.length; i++) {
            int start = stations[i - 1] + w + 1;
            int end = stations[i] - w - 1;
            if (end - start + 1 > 0) answer += countStationsOfSection(start, end, w);
        }

        // 마지막 구간
        if (stations[stations.length - 1] + w < n) {
            int start = stations[stations.length - 1] + w + 1;
            int end = n;
            answer += countStationsOfSection(start, end, w);
        }

        return answer;
    }
}
