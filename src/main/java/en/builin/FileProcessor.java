package en.builin;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class FileProcessor {

    private final Counter counter;

    void processFile(String fileName) {

        try (Stream<String> linesStream = Files.lines(Path.of(fileName))) {
            linesStream.forEach(counter::count);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file!");
        }
    }
}
