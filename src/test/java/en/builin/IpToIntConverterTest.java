package en.builin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IpToIntConverterTest {

    @Test
    void convertRight() {

        IpToIntConverter converter = new IpToIntConverter();

        assertEquals(-1, converter.applyAsInt("255.255.255.255"));
        assertEquals(0, converter.applyAsInt("0.0.0.0"));
        assertEquals(-1062731518, converter.applyAsInt("192.168.1.2"));
    }
}