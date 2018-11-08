import org.junit.Test;

import static org.junit.Assert.*;

public class PopuTest {

    @Test
    public void test0() {
        String[] friends = new String[]{"NNN",
                "NNN",
                "NNN"};
        assertEquals(0, new Popu().countDoubleFriends(friends));
    }

    @Test
    public void test1() {
        String[] friends = new String[]{"NYY",
                "YNY",
                "YYN"};
        assertEquals(2, new Popu().countDoubleFriends(friends));
    }

    @Test
    public void test2() {
        String[] friends = new String[]{"NYNNN",
                "YNYNN",
                "NYNYN",
                "NNYNY",
                "NNNYN"};
        assertEquals(4, new Popu().countDoubleFriends(friends));
    }

    @Test
    public void test3() {
        String[] friends = new String[]{"NNNNYNNNNN",
                "NNNNYNYYNN",
                "NNNYYYNNNN",
                "NNYNNNNNNN",
                "YYYNNNNNNY",
                "NNYNNNNNYN",
                "NYNNNNNYNN",
                "NYNNNNYNNN",
                "NNNNNYNNNN",
                "NNNNYNNNNN"};
        assertEquals(8, new Popu().countDoubleFriends(friends));
    }
}