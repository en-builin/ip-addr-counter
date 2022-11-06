package en.builin;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FileProcessorTest {

    @Mock
    Counter counter;
    @InjectMocks
    FileProcessor fileProcessor;

    @Test
    void testFileFullyRead() {

        String testFile = Path.of("src/test/resources/test-file.txt").toString();

        fileProcessor.processFile(testFile);

        verify(counter, times(10)).count(anyString());
    }

    @Test
    void passedEmptyFile() {

        String testFile = Path.of("src/test/resources/empty-file.txt").toString();

        fileProcessor.processFile(testFile);

        verify(counter, never()).count(anyString());
    }

    @Test
    void throwsExceptionFileNotExist() {

        String testFile = Path.of("src/test/resources/no-such-file.txt").toString();

        assertThrows(RuntimeException.class,
                () -> fileProcessor.processFile(testFile));
    }

}