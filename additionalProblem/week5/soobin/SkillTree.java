package week5.soobin;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class SkillTree {
    private boolean isAvailable(String skill, String skillTree) {
        Set<Character> precedingSet = new HashSet<>();
        Queue<Character> skillLevel = new LinkedList<>();
        for (char level : skill.toCharArray()) {
            skillLevel.add(level);
            precedingSet.add(level);
        }

        boolean[] needPreceding = new boolean[skillTree.length()];
        for (int i = 0; i < skillTree.length(); i++)
            needPreceding[i] = precedingSet.contains(skillTree.charAt(i));

        for (int i = 0; i < skillTree.length(); i++) {
            char currentSkill = skillTree.charAt(i);

            if (!needPreceding[i]) continue;

            char level = skillLevel.peek();
            if (currentSkill == level) skillLevel.poll();
            else return false;
        }

        return true;
    }

    public int solution(String skill, String[] skillTrees) {
        int answer = 0;
        for (String tree: skillTrees)
            if (isAvailable(skill, tree))
                answer++;

        return answer;
    }

    public static void main(String[] args) {
        SkillTree s = new SkillTree();

        System.out.println(s.solution("CBD", new String[] {"BACDE", "CBADF", "AECB", "BDA"}));
    }
}
