public class Popu {
    public int countDoubleFriends(String[] friends) {
        int doubleFriendsNumber = 0;
        for (int rawIndex = 0; rawIndex < friends.length; rawIndex++) {
            int connectionsNumber = 0;
            for (int columnIndex = 0; columnIndex < friends.length; columnIndex++) {
                if (rawIndex == columnIndex)
                    continue;
                if (friends[rawIndex].charAt(columnIndex) == 'Y') {
                    connectionsNumber++;
                } else {
                    for (int tempIndex = 0; tempIndex < friends.length; tempIndex++) {
                        if (rawIndex == tempIndex || columnIndex == tempIndex)
                            continue;
                        if (friends[rawIndex].charAt(tempIndex) == 'Y' && friends[columnIndex].charAt(tempIndex) == 'Y') {
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