package programmers.level2;

import java.util.ArrayList;
import java.util.HashSet;

/*
- element가 배열인 경우 contains가 기대한 대로 동작하지 않음

- String의 A.contains(B)는 B의 모든 구성 요소가 A에 있는지를 검사하는 것이 아니라
B라는 문자열을 하나의 단위로 보고, 해당 문자열이 A 안에 있는지를 검사

- column의 조합을 위해 HashSet<Integer>(이때 integer는 어떤 컬럼을 고를지를 나타내는 인덱스)를 처음에 만들었으나
이는 하나의 키를 나타내기 때문에 List<HashSet<Integer>> 이런 식으로 다른 컬렉션에 또 들어가야 했는데,
좀 복잡해보여서 HashSet<Integer>에서 저장하던 정보를 String 타입으로 바꿔서 저장함 {1, 2, 3} -> "123"
 */
public class 후보키 {

    public static void main(String[] args) {
        String[][] inputs = {{"100","ryan","music","2"},
                {"200","apeach","math","2"},
                {"300","tube","computer","3"},
                {"400","con","computer","4"},
                {"500","muzi","music","3"},
                {"600","apeach","music","2"}};
        System.out.println(new 후보키().solution(inputs));
    }

    public int solution(String[][] relation) {
        // 모든 키 조합을 만든다 -> 재귀함수로, 경우의수는 2^8
        // 해당 키 조합으로 모든 row를 식별할 수 있는지 검사한다 -> row는 max가 20
        // 최악의 경우 2^8 * 20 * ...
        // 문자열의 길이도 짧으므로 충분히 시간 안에 가능할 듯
        HashSet<String> keys = new HashSet<>();
        generateKeys(keys, relation, "", 0);
        keys = remainOnlyMinCombination(keys);

        return keys.size();
    }

    public void generateKeys(HashSet<String> keys, String[][] relation, String nowKey, int depth) {
        if(depth >= relation[0].length) {
            if(!nowKey.isEmpty() && isCandidateKey(nowKey, relation)) // key가 비어있지 않고, row들을 식별할 수 있으면 keys에 추가
                keys.add(nowKey);
            return;
        }

        // 지금 column을 key에 추가하지 않기
        generateKeys(keys, relation, nowKey, depth+1);
        // 지금 column을 key에 추가하기
        nowKey += depth;
        generateKeys(keys, relation, nowKey, depth+1);
    }

    private boolean isCandidateKey(String key, String[][] relation) {
        // key를 구성하는 col으로만 row의 정보를 추출한 뒤 저장할 List
        ArrayList<String[]> rows = new ArrayList<>();
        for(int i=0; i<relation.length; i++) {
            String[] row = new String[key.length()];
            for(int j=0; j<row.length; j++)
                row[j] = relation[i][key.charAt(j) - '0'];
            if(stringArrayContain(rows, row)) return false;
            rows.add(row);
        }
        return true;
    }

    private boolean stringArrayContain(ArrayList<String[]> list, String[] target) {
        for(int i=0; i<list.size(); i++) {
            String[] now = list.get(i);
            if(now.length != target.length) continue;
            boolean isSame = true;
            for(int j=0; j<now.length; j++) {
                if(!now[j].equals(target[j])) {
                    isSame = false;
                    break;
                }
            }
            if(isSame) return true;
        }
        return false;
    }

    // 식별할 수 있는 최소한의 조합만이 candidate key가 될 수 있으므로
    // keys에서 A가 B의 부분집합이라면 B를 삭제해야 한다
    private HashSet<String> remainOnlyMinCombination(HashSet<String> keys) {
        HashSet<String> ret = new HashSet<>();
        for(String i : keys) {
            boolean isMin = true;
            for(String j : keys) {
                if(i.equals(j)) continue;
                // if(i.contains(j)) { // String의 contains: "123".conatins("13") -> false
                if(stringContain(i, j)) {
                    isMin = false;
                    break;
                }
            }
            if(isMin) ret.add(i);
        }
        return ret;
    }

    private boolean stringContain(String i, String j) {
        for(int k=0; k<j.length(); k++) {
            if(i.indexOf(j.charAt(k)) == -1) return false;
        }
        return true;
    }

}
