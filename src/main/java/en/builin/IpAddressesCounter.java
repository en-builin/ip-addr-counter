package en.builin;

import java.util.function.ToIntFunction;

public class IpAddressesCounter {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Enter file name parameter: ip-addr-counter.jar [fileName]");
            return;
        }

        ToIntFunction<String> converter = new IpToIntConverter();

        Counter counter = new CounterBitSetImpl(converter);

        FileProcessor fileProcessor = new FileProcessor(counter);

        fileProcessor.processFile(args[0]);

        System.out.println(counter.getResult());
    }
}