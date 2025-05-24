import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RemoveGptChars {
    private final String inputPath;
    private final String outputPath;
    private final String encoding;

    public RemoveGptChars(String inputPath, String outputPath, String encoding) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.encoding = encoding;
    }

    public static void main(String[] args) {
        String inputPath = null;
        String outputPath = null;
        String encoding = "UTF-8";

        // Basic CLI arg parsing
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-i":
                    inputPath = args[++i];
                    break;
                case "-o":
                    outputPath = args[++i];
                    break;
                case "-e":
                    encoding = args[++i];
                    break;
                default:
                    System.err.println("Unknown option: " + args[i]);
                    printUsageAndExit();
            }
        }

        if (inputPath == null) {
            System.err.println("Error: Input file is required.");
            printUsageAndExit();
        }

        if (outputPath == null) {
            outputPath = inputPath;
        }

        var cleaner = new RemoveGptChars(inputPath, outputPath, encoding);

        try {
            cleaner.process();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            printUsageAndExit();
        } catch (IOException e) {
            System.err.println("I/O Error: " + e.getMessage());
            printUsageAndExit();
        }
    }

    public String cleanText(String input) {
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            // Keep printable ASCII or common whitespace
            if ((c >= 32 && c <= 126) || c == '\n' || c == '\r' || c == '\t') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public void process() throws IOException {
        String content = Files.readString(Paths.get(inputPath), Charset.forName(encoding));
        String cleaned = cleanText(content);
        Files.writeString(Paths.get(outputPath), cleaned, Charset.forName(encoding));
    }

    private static void printUsageAndExit() {
        System.out.println("Usage: java RemoveGptChars -i input.txt [-o output.txt] [-e encoding]");
        System.exit(1);
    }
}