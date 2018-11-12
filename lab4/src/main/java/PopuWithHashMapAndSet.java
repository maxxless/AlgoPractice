import java.util.HashMap;
import java.util.HashSet;

public class PopuWithHashMapAndSet {
    public int countDoubleFriends(String[] friends) {
        HashMap<Integer, HashSet<Integer>> connections = new HashMap<Integer, HashSet<Integer>>();
        for (int rawIndex = 0; rawIndex < friends.length; rawIndex++) {
            connections.put(rawIndex, new HashSet<Integer>());
            for (int columnIndex = 0; columnIndex < friends[rawIndex].length(); columnIndex++) {
                if (friends[rawIndex].charAt(columnIndex) == 'Y')
                    connections.get(rawIndex).add(columnIndex);
                for (int k = 0; k < friends.length; k++) {
                    if (rawIndex != columnIndex && friends[rawIndex].charAt(k) == 'Y' && friends[columnIndex].charAt(k) == 'Y')
                        connections.get(rawIndex).add(columnIndex);
                }
            }
        }
        int highestScore = 0;
        for (Integer friend : connections.keySet()) {
            if (connections.get(friend).size() > highestScore)
                highestScore = connections.get(friend).size();
        }
        return highestScore;
    }
}