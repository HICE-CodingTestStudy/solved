package week21.soobin;

public class MusicPlayedJustNow {
    private class MusicInfo {
        String playedCode, name;
        int duration;

        MusicInfo(String[] musicInfo) {
            int start = parseTime(musicInfo[0]);
            int end = parseTime(musicInfo[1]);
            this.duration = end - start;
            this.name = musicInfo[2];
            this.playedCode = getWholeCode(convertMinorCode(musicInfo[3]));
        }

        private int parseTime(String start) {
            String[] split = start.split(":");
            return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
        }

        private String getWholeCode(String code) {
            if (duration <= code.length()) return code.substring(0, duration);

            String playedCode = "";
            int repeat = duration / code.length();
            for (int i = 1; i <= repeat; i++) playedCode += code;
            if (duration - repeat * code.length() > 0)
                playedCode += code.substring(0, duration - repeat * code.length());

            return playedCode;
        }
    }

    private String convertMinorCode(String code) {
        return code.replace("C#", "c")
                .replace("D#", "d")
                .replace("F#", "f")
                .replace("G#", "g")
                .replace("A#", "a");
    }

    public String solution(String m, String[] musicinfos) {
        m = convertMinorCode(m);
        String answer = "(None)";
        int maxDuration = 0;

        for (String musicInfo : musicinfos) {
            MusicInfo info = new MusicInfo(musicInfo.split(","));

            if (info.playedCode.contains(m) && maxDuration < info.duration) {
                answer = info.name;
                maxDuration = info.duration;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        MusicPlayedJustNow m = new MusicPlayedJustNow();
        String answer = m.solution(
                "CC#BCC#BCC#BCC#B",
                new String[] {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"}
        );
        System.out.println(answer);
    }
}
