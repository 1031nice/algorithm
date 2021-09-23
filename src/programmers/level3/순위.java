package programmers.level3;

import java.util.HashSet;

/*
TODO
그래프를 이용해서도 풀어보자
 */

public class 순위 {
    static class Person {
        int id;
        HashSet<Integer> bigs = new HashSet<>();
        HashSet<Integer> smalls = new HashSet<>();
        public Person(int id) {
            this.id = id;
        }
    }

    public int solution(int n, int[][] results) {
        Person[] persons = new Person[n];
        for(int i=0; i<n; i++) {
            persons[i] = new Person(i);
        }

        boolean hasChange = true;
        while(hasChange) {
            hasChange = false;
            for(int i=0; i<results.length; i++) {
                int winner = results[i][0]-1;
                int learner = results[i][1]-1;
                int before1 = persons[winner].smalls.size();
                int before2 = persons[learner].bigs.size();
                persons[winner].smalls.addAll(persons[learner].smalls);
                persons[winner].smalls.add(persons[learner].id);
                persons[learner].bigs.addAll(persons[winner].bigs);
                persons[learner].bigs.add(persons[winner].id);
                int after1 = persons[winner].smalls.size();
                int after2 = persons[learner].bigs.size();
                if((before1 != after1) || (before2 != after2))
                    hasChange = true;
            }
        }
        int answer = 0;
        for(int i=0; i<n; i++) {
            Person now = persons[i];
            if(now.smalls.size() + now.bigs.size() == n-1)
                answer++;
        }
        return answer;
    }
}
