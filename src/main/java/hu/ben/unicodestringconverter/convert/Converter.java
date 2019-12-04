package hu.ben.unicodestringconverter.convert;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.CharUtils;

import hu.ben.unicodestringconverter.exception.UnicodeReplacingException;

public class Converter {

    private static final String pattern = "[^\\p{ASCII}]";

    public void convertFile(File file) {
        List<String> convertedLines = convertAccentedCharactersToUnicodeString(file);

        try {
            FileUtils.writeLines(file, convertedLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> convertAccentedCharactersToUnicodeString(File file) {
        List<String> fileLines = null;
        try {
            fileLines = FileUtils.readLines(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (fileLines != null) {
            for (String line : fileLines) {
                char[] chars = line.toCharArray();

                boolean wereThereAnyReplaces = false;
                StringBuilder stringBuilder = new StringBuilder();
                for (char actualChar : chars) {
                    if (Pattern.matches(pattern, Character.toString(actualChar))) {
                        wereThereAnyReplaces = true;
                        String unicodeEscaped = CharUtils.unicodeEscaped(actualChar);
                        stringBuilder.append(unicodeEscaped);
                    } else {
                        stringBuilder.append(actualChar);
                    }
                }

                if (wereThereAnyReplaces) {
                    boolean replaceResult = Collections.replaceAll(fileLines, line, stringBuilder.toString());

                    if (!replaceResult) {
                        int index = fileLines.indexOf(line);
                        throw new UnicodeReplacingException(MessageFormat.format(
                            "Replace was not successful with {0}. line = '{1}'",
                            index,
                            line
                        ));
                    }
                }
            }
        }

        return fileLines;
    }

}
