import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class LOL {
    private static class Unit {
        String skill;
        int round;

        Unit(String skill, int round) {
            this.skill = skill;
            this.round = round;
        }
    }
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static Map<String, List<String>> nextSkills = new HashMap<>();
    private static Map<String, Integer> numPrevious = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Queue<Unit> queue = init();
        Queue<Unit> pq = solution(queue);
        print(pq);
    }

    private static Queue<Unit> init() throws Exception {
        int N = Integer.parseInt(br.readLine());
        nextSkills = new HashMap<>();
        numPrevious = new HashMap<>();
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String prev = st.nextToken();
            String next = st.nextToken();
            nextSkills.computeIfAbsent(prev, key -> new LinkedList<>()).add(next);
            numPrevious.put(next, numPrevious.getOrDefault(next, 0) + 1);
            numPrevious.computeIfAbsent(prev, key -> numPrevious.put(prev, 0));
        }

        Queue<Unit> queue = new ArrayDeque<>();
        for (Map.Entry<String, Integer> skill : numPrevious.entrySet()) {
            if (skill.getValue() > 0) continue;
            queue.add(new Unit(skill.getKey(), 0));
        }

        return queue;
    }

    private static Queue<Unit> solution(Queue<Unit> queue) {
        Queue<Unit> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.round == o2.round) return o1.skill.compareTo(o2.skill);
            return o1.round - o2.round;
        });
        while (!queue.isEmpty()) {
            Unit curr = queue.poll();
            pq.add(curr);

            if (!nextSkills.containsKey(curr.skill)) continue;

            for (String next : nextSkills.get(curr.skill)) {
                numPrevious.put(next, numPrevious.get(next) - 1);
                if (numPrevious.get(next) == 0) {
                    numPrevious.remove(next);
                    queue.add(new Unit(next, curr.round + 1));
                }
            }
        }

        return pq;
    }

    private static void print(Queue<Unit> pq) throws Exception {
        int nTotal = numPrevious.size();
        if (nTotal > pq.size()) {
            bw.write(String.valueOf(-1));
        } else {
            while (!pq.isEmpty()) {
                bw.write(pq.poll().skill + "\n");
            }
        }
        bw.flush();
    }
}
