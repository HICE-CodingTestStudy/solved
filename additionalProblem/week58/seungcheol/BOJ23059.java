import java.io.*;
import java.util.*;

//리그 오브 레게노
public class BOJ23059 {
    static Map<String, Integer> idxMap;
    static List<Integer> counts;
    static List<String> items;
    static List<List<Integer>> adjList;
    static int lastIdx;
    static PriorityQueue<Item> resultPQ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        items = new ArrayList<>();
        idxMap = new HashMap<>();
        counts = new ArrayList<>();
        lastIdx = 0;
        adjList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String str1 = st.nextToken();
            int idx1 = init(str1);
            String str2 = st.nextToken();
            int idx2 = init(str2);
            //idx2에 선행 아이템 개수 1개 추가
            counts.set(idx2, counts.get(idx2) + 1);
            //idx1의 인접 노드로 idx2추가
            adjList.get(idx1).add(idx2);
        }
        Queue<Integer> queue = new ArrayDeque<>();
        resultPQ = new PriorityQueue<>();
        for (int i = 0; i < counts.size(); i++) {
            //첫 아이템인 경우 추가
            if (counts.get(i) == 0) {
                queue.offer(i);
                resultPQ.offer(new Item(0, items.get(i)));
            }
        }

        topologySort(queue);
        StringBuilder sb = new StringBuilder();
        //전체 아이템 개수와 만들어진 아이템의 수가 같다면 가능한 것
        if (items.size() == resultPQ.size()) {
            while (!resultPQ.isEmpty()) {
                sb.append(resultPQ.poll().name);
                sb.append("\n");
            }
        } else {
            sb.append(-1);
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void topologySort(Queue<Integer> queue) {
        int breadth = 1;
        while (!queue.isEmpty()) {
            //너비만큼 수행 -> 이를 처리 순서 기준으로 사용
            int size = queue.size();
            while (size-- > 0){
                int idx = queue.poll();

                for (int i = 0; i < adjList.get(idx).size(); i++) {
                    List<Integer> list = adjList.get(idx);
                    //현재 i의 아이템을 만들었으니 다음 아이템들 -1 카운팅
                    int next = list.get(i);
                    int count = counts.get(next) - 1;
                    if (count == 0) {
                        queue.offer(next);
                        resultPQ.offer(new Item(breadth, items.get(next)));
                    }
                    counts.set(next, count);
                }
            }
            breadth++;
        }
    }

    /**
     * 문자열에 대해 인덱스를 반환
     * 처음 나온 문자열이면 나온 마지막 인덱스를 세팅하고 반환
     */
    private static int init(String str) {
        int idx = idxMap.getOrDefault(str, -1);
        //처음 나온 문자열이면
        if (idx == -1) {
            //아이템 이름 리스트에 추가
            items.add(str);
            //인접 리스트 초기화
            adjList.add(new ArrayList<>());
            //개수 리스트 초기화
            counts.add(0);
            //마지막 인덱스 + 1
            idx = lastIdx;
            idxMap.put(str, idx);
            lastIdx++;
        }
        return idx;
    }
}

class Item implements Comparable<Item> {
    //처리된 순서 (너비)
    int num;
    String name;

    public Item(int num, String name) {
        this.num = num;
        this.name = name;
    }

    @Override
    public int compareTo(Item o) {
        //처리 순서가 같다면 사전 순 정렬
        if(this.num == o.num) return this.name.compareTo(o.name);
        return Integer.compare(this.num, o.num);
    }
}