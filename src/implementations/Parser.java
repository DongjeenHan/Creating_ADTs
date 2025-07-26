/**
 * XML Parser that uses a custom stack implementation to check for balanced tags.
 */
package implementations;

import java.io.*;
import java.util.regex.*;

import implementations.MyStack;
import implementations.MyQueue;
import exceptions.EmptyQueueException;

/**
 * The Parser class reads an XML file and checks if tags are properly opened and closed.
 */
public class Parser {
    /** Stack to track opening tags */
    private final MyStack<TagInfo> stack = new MyStack<>();

    /** Pattern for identifying tags in the XML */
    private static final Pattern TAG_PATTERN = Pattern.compile("<\\s*/?\\s*[^<>]+?/?>");

    /**
     * Parses an XML file and reports any mismatched or unclosed tags.
     *
     * @param filename the name of the file to parse
     */
    public void parse(String filename) {
        boolean hasErrors = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                Matcher matcher = TAG_PATTERN.matcher(line);

                while (matcher.find()) {
                    String tag = matcher.group().trim();

                    if (tag.startsWith("<?") && tag.endsWith("?>")) continue;

                    if (tag.matches("<\\s*[^/<>]+\\s*/\\s*>")) continue;

                    if (tag.startsWith("</")) {
                        String closingTagName = getTagName(tag);

                        if (!stack.isEmpty()) {
                            String expectedTag = getTagName(stack.peek().tag);

                            if (closingTagName.equals(expectedTag)) {
                                stack.pop(); // matched properly
                            } else {
                                TagInfo expected = stack.pop();
                                System.out.printf("Line %d: Mismatched tags: expected </%s> but found </%s>%n",
                                        lineNumber, getTagName(expected.tag), closingTagName);
                                hasErrors = true;
                            }
                        } else {
                            System.out.printf("Line %d: Mismatched tags: no opening tag for </%s>%n",
                                    lineNumber, closingTagName);
                            hasErrors = true;
                        }
                    } else {
                        stack.push(new TagInfo(tag, lineNumber));
                    }
                }
            }

            while (!stack.isEmpty()) {
                TagInfo unclosed = stack.pop();
                System.out.printf("Line %d: Unclosed tag: %s%n", unclosed.lineNumber, unclosed.tag);
                hasErrors = true;
            }

            if (!hasErrors) {
                System.out.println("XML is valid.");
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Extracts the tag name from a full tag string.
     *
     * @param fullTag the full tag string including brackets
     * @return the tag name without brackets or attributes
     */
    private String getTagName(String fullTag) {
        return fullTag.replaceAll("[</>]", "").split("\\s+")[0];
    }

    /**
     * Helper class to store tag name and line number.
     */
    private static class TagInfo {
        String tag;
        int lineNumber;

        TagInfo(String tag, int lineNumber) {
            this.tag = tag;
            this.lineNumber = lineNumber;
        }
    }
}
