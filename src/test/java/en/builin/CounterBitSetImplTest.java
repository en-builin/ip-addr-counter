package en.builin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CounterBitSetImplTest {

    @Test
    void properCounts() {

        Counter counter = new CounterBitSetImpl(new IpToIntConverter());

        assertEquals(0, counter.getResult());

        counter.count("");
        assertEquals(0, counter.getResult());

        counter.count("122.163.242.115");
        counter.count("53.28.120.4");
        counter.count("239.24.131.18");
        counter.count("93.173.52.136");
        counter.count("51.79.86.92");
        counter.count("51.79.86.92");

        assertEquals(5, counter.getResult());
    }

    @Test
    void failsOnFormatErrors() {

        Counter counter = new CounterBitSetImpl(new IpToIntConverter());

        assertThrows(NullPointerException.class, () -> {
            counter.count(" ");
        });
        assertThrows(NullPointerException.class, () -> {
            counter.count("555.555.555.555");
        });
    }
}