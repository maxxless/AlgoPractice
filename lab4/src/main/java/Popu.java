public class Popu {
    public int countDoubleFriends(String[] friends) {
        int doubleFriendsNumber = 0;
        for (int i = 0; i < friends.length; i++) {
            int connectionsNumber = 0;
            for (int j = 0; j < friends.length; j++) {
                if (i == j)
                    continue;
                if (friends[i].charAt(j) == 'Y') {
                    connectionsNumber++;
                } else {
                    for (int tempIndex = 0; tempIndex < friends.length; tempIndex++) {
                        if (i == tempIndex || j == tempIndex)
                            continue;
                        if (friends[i].charAt(tempIndex) == 'Y' && friends[j].charAt(tempIndex) == 'Y') {
                            connectionsNumber++;
                            break;
                        }
                    }
                }
            }
            doubleFriendsNumber = connectionsNumber > doubleFriendsNumber ? connectionsNumber : doubleFriendsNumber;
        }
        return doubleFriendsNumber;
    }
}