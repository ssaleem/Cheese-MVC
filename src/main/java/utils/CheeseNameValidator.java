package utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheeseNameValidator {
    private static String alphabetSpaceRegex = "[^a-zA-Z\\s]";
    private static Pattern alphabetSpacePattern = Pattern.compile(alphabetSpaceRegex);

    public static boolean isInValid(String str) {
        Matcher match = alphabetSpacePattern.matcher(str);
        return match.find();
    }
}
