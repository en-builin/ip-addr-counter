package en.builin;

import java.util.HashSet;

public class CounterHashSetImpl implements Counter {

    private final HashSet<String> storage = new HashSet<>();

    @Override
    public void count(String address) {
        storage.add(address);
    }

    @Override
    public long getResult() {
        return storage.size();
    }
}
