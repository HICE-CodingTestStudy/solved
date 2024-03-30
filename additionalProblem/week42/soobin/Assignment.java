import java.util.*;

public class Assignment {
    private class Job {
        String name;
        int start, duration;

        Job(String name, String start, int duration) {
            this.name = name;
            this.start = convertTime(start);
            this.duration = duration;
        }

        private int convertTime(String str) {
            String[] splited = str.split(":");
            int hour = Integer.parseInt(splited[0]);
            int minute = Integer.parseInt(splited[1]);
            return hour * 60 + minute;
        }
    }

    private final Queue<Job> waitingJobs = new PriorityQueue<>(Comparator.comparingInt(o -> o.start));
    private final Stack<Job> stoppedJobs = new Stack<>();
    private final List<String> answer = new ArrayList<>();
    private Job current;
    private int currentTime;

    public List<String> solution(String[][] plans) {
        addJobs(plans);
        processWaitingJobs();
        processRemainedJobs();
        return answer;
    }

    private void addJobs(String[][] plans) {
        for (String[] plan : plans) {
            String name = plan[0];
            String start = plan[1];
            int duration = Integer.parseInt(plan[2]);
            waitingJobs.add(new Job(name, start, duration));
        }
    }

    private void processWaitingJobs() {
        while (!waitingJobs.isEmpty()) {
            if (current == null) {
                current = waitingJobs.poll();
                currentTime = current.start;
            }

            if (waitingJobs.isEmpty()) {
                answer.add(current.name);
                break;
            }

            int timeDiff = calcTimeDiff();
            if (timeDiff < 0) {
                answer.add(current.name);
                currentTime += current.duration;
                if (!stoppedJobs.isEmpty()) {
                    current = stoppedJobs.pop();
                    continue;
                }
            } else if (timeDiff == 0) {
                answer.add(current.name);
            } else {
                int done = waitingJobs.peek().start - currentTime;
                current.duration -= done;
                stoppedJobs.add(current);
            }
            current = null;
        }
    }

    private int calcTimeDiff() {
        return currentTime + current.duration - waitingJobs.peek().start;
    }

    private void processRemainedJobs() {
        while (!stoppedJobs.isEmpty())
            answer.add(stoppedJobs.pop().name);
    }
}
