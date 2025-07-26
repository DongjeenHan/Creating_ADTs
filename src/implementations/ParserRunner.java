package implementations;

/**
 * Entry point for running the XML parser.
 * Accepts a filename as a command-line argument and parses it.
 */
public class ParserRunner {

    /**
     * Main method to start the XML parser.
     *
     * @param args command-line arguments; expects a single XML filename
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -jar Parser.jar <filename.xml>");
            return;
        }

        Parser parser = new Parser();
        parser.parse(args[0]);
    }
}
