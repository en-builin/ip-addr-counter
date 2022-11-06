package en.builin;

import lombok.extern.slf4j.Slf4j;

import java.util.function.ToIntFunction;

@Slf4j
public class IpAddressesCounter {

    public static void main(String[] args) {

        if (args.length == 0) {
            log.info("Enter file name parameter: ip-addr-counter.jar [fileName]");
            return;
        }

        ToIntFunction<String> converter = new IpToIntConverter();

        Counter counter = new CounterBitSetImpl(converter);

        FileProcessor fileProcessor = new FileProcessor(counter);

        fileProcessor.processFile(args[0]);

        log.info("Unique ips count: {}", counter.getResult());
    }
}