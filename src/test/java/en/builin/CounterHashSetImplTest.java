package en.builin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CounterHashSetImplTest {

    Counter counter;

    @BeforeEach
    void setUp() {
        counter = new CounterHashSetImpl();
    }

    @Test
    void properCounts() {
        counter.count("122.163.242.115");
        counter.count("53.28.120.4");
        counter.count("239.24.131.18");
        counter.count("93.173.52.136");
        counter.count("51.79.86.92");
        counter.count("51.79.86.92");

        assertEquals(counter.getResult(), 5);
    }

    @Test
    void noCountsZeroResult() {
        assertEquals(counter.getResult(), 0);
    }

    @Test
    void emptyLineCountsOne() {
        counter.count("");
        assertEquals(counter.getResult(), 1);
    }
}