import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackTest {

    @Test
    void addRank() {
        HashMap<String,Integer> testHashMap = new HashMap<>();
        boolean test = BlackJack.addRank(testHashMap,"Jon",true);
        assertEquals(true,test);
    }

    @Test
    void getAllRanks() {
        assertEquals(true, BlackJack.getAllRanks());
    }
}