package Day27.Java_IO;
import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class NIOFileAPITest {
    private static final String HOME = System.getProperty("user.home");
    private static final String PLAY_WITH_NIO = "TempPlayGround";

    @Test
    public void givenPathWhenCheckedThenConfirm() throws IOException {
        // Check File Exists
        Path homePath = Paths.get(HOME);
        Assert.assertTrue(Files.exists(homePath));

        // Delete File and Check File Not Exist
        Path playPath = Paths.get(HOME + "/" + PLAY_WITH_NIO);
        if (Files.exists(playPath)) {
            // Simple recursive deletion utility strategy for test directory
            File tempPlayground = playPath.toFile();
            File[] allContents = tempPlayground.listFiles();
            if (allContents != null) {
                for (File file : allContents) {
                    file.delete();
                }
            }
            tempPlayground.delete();
        }
        Assert.assertTrue(Files.notExists(playPath));

        // Create Directory
        Files.createDirectory(playPath);
        Assert.assertTrue(Files.exists(playPath));

        // Create Files loop
        IntStream.range(1, 10).forEach(cntr -> {
            Path tempFile = Paths.get(playPath + "/temp" + cntr);
            Assert.assertTrue(Files.notExists(tempFile));
            try {
                Files.createFile(tempFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Assert.assertTrue(Files.exists(tempFile));
        });

        // List Files, Directories as well as Files with Extension
        System.out.println("--- Listing regular files via Files.list ---");
        Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);
        
        System.out.println("--- Listing all items via DirectoryStream ---");
        Files.newDirectoryStream(playPath).forEach(System.out::println);
        
        System.out.println("--- Filtered stream matches prefix 'temp' ---");
        Files.newDirectoryStream(playPath, path -> path.toFile().isFile() && 
                path.toString().contains("temp")).forEach(System.out::println);
    }
}
