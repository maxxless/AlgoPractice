import java.util.HashMap;
import java.util.HashSet;

public class PopuWithHashMapAndSet {
    public int countDoubleFriends(String[] friends) {
        HashMap<Integer, HashSet<Integer>> connections = new HashMap<Integer, HashSet<Integer>>();
        for (int i = 0; i < friends.length; i++) {
            connections.put(i, new HashSet<Integer>());
            for (int j = 0; j < friends[i].length(); j++) {
                if (friends[i].charAt(j) == 'Y')
                    connections.get(i).add(j);
                for (int k = 0; k < friends.length; k++) {
                    if (i != j && friends[i].charAt(k) == 'Y' && friends[j].charAt(k) == 'Y')
                        connections.get(i).add(j);
                }
            }
        }
        int highestScore = 0;
        for (Integer person : connections.keySet()) {
            if (connections.get(person).size() > highestScore)
                highestScore = connections.get(person).size();
        }
        return highestScore;
    }
}