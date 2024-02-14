import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class JavaCasting {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final Map<String, String> parents = new HashMap<>();

    public static void main(String[] args) throws IOException {
        String[] target = parseInput();
        boolean isFamily = isParentAndChild(target[0], target[1]);
        if (!isFamily) isFamily = isParentAndChild(target[1], target[0]);
        printAnswer(isFamily);
    }

    private static String[] parseInput() {
        String[] target = new String[2];
        try {
            int N = Integer.parseInt(br.readLine());
            for (int i = 1; i < N; i++) {
                String[] relation = br.readLine().split(" ");
                parents.put(relation[0], relation[1]);
            }
            target = br.readLine().split(" ");
        } catch (IOException ignored) {}
        return target;
    }

    private static boolean isParentAndChild(String current, String target) {
        while (parents.containsKey(current)) {
            String parent = parents.get(current);
            if (parent.equals(target)) return true;
            current = parent;
        }

        return false;
    }

    private static void printAnswer(boolean isFamily) {
        try {
            bw.write(String.valueOf(isFamily ? 1 : 0));
            bw.flush();
        } catch (IOException ignored) {}
    }
}
