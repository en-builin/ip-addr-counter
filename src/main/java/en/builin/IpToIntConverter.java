package en.builin;

import java.util.function.ToIntFunction;

public class IpToIntConverter implements ToIntFunction<String> {

    @Override
    public int applyAsInt(String ipString) {

        byte[] numericAddress = textToNumericFormat(ipString);
        int result = 0;
        for (byte b : numericAddress) {
            result = result << 8 | (b & 0xFF);
        }
        return result;
    }

    /**
     * Ported from sun.net.utils.IPAddressUtil
     */
    private byte[] textToNumericFormat(String src)
    {
        byte[] res = new byte[4];

        long tmpValue = 0;
        int currByte = 0;
        boolean newOctet = true;

        int len = src.length();
        if (len == 0 || len > 15) {
            return null;
        }
        for (int i = 0; i < len; i++) {
            char c = src.charAt(i);
            if (c == '.') {
                if (newOctet || tmpValue < 0 || tmpValue > 0xff || currByte == 3) {
                    return null;
                }
                res[currByte++] = (byte) (tmpValue & 0xff);
                tmpValue = 0;
                newOctet = true;
            } else {
                int digit = digit(c);
                if (digit < 0) {
                    return null;
                }
                tmpValue *= 10;
                tmpValue += digit;
                newOctet = false;
            }
        }
        if (newOctet || tmpValue < 0 || tmpValue >= (1L << ((4 - currByte) * 8))) {
            return null;
        }
        res[currByte] = (byte) ((tmpValue >> ((3-currByte) * 8)) & 0xff);
        return res;
    }

    private int digit(char c) {
        int val = c - '0';
        return (val < 0 || val >= 10) ? -1 : val;
    }
}
