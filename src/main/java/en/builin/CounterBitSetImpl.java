package en.builin;

import lombok.RequiredArgsConstructor;

import java.util.BitSet;
import java.util.function.ToIntFunction;

@RequiredArgsConstructor
public class CounterBitSetImpl implements Counter {

    private final ToIntFunction<String> converter;

    private final BitSet storagePositive = new BitSet(Integer.MAX_VALUE);
    private final BitSet storageNegative = new BitSet(Integer.MAX_VALUE);

    @Override
    public void count(String address) {
        if ("".equals(address)) {
            return;
        }
        int value = converter.applyAsInt(address);
        if (value >= 0) {
            storagePositive.set(value);
        } else {
            storageNegative.set(~value);
        }
    }

    @Override
    public long getResult() {
        return storagePositive.cardinality() + storageNegative.cardinality();
    }
}
