import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RoomEscape {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        char[] goal = new char[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            goal[i] = st.nextToken().charAt(0);
        }

        int count = 0;
        char[] button = new char[N];
        Arrays.fill(button, '0');
        for (int i = 0; i < N; i++) {
            if (goal[i] == button[i]) continue;

            count++;
            for (int j = 0; j < 3 && i + j < N; j++) {
                button[i + j] = button[i + j] == '0' ? '1' : '0';
            }
        }

        System.out.println(count);
    }
}
