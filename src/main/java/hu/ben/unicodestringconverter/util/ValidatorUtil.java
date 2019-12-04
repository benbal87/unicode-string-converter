package hu.ben.unicodestringconverter.util;

import java.io.File;
import java.text.MessageFormat;

import hu.ben.unicodestringconverter.constant.Constants;
import hu.ben.unicodestringconverter.exception.ArgumentsNotValidException;

public final class ValidatorUtil {

    private ValidatorUtil() {
    }

    public static void validateArgument(String[] arguments) {
        validateNumberOfArguments(arguments);
        validateIfArgumentIsValidPath(arguments);
        validateIfArgumentIsDirectory(arguments);
    }

    private static void validateNumberOfArguments(String[] arguments) {
        if (arguments.length != 1) {
            throw new ArgumentsNotValidException(
                MessageFormat.format(
                    "Number of arguments must be one. There were {0} number of of them.",
                    arguments.length
                ));
        }
    }

    private static void validateIfArgumentIsValidPath(String[] arguments) {
        if (
            !isFileExistsInWorkingDirectory(arguments[0])
            && !isFileExists(arguments[0])
        ) {
            throw new ArgumentsNotValidException(MessageFormat.format(
                "Given path [{0}] does not exists!",
                arguments[0]
            ));
        }
    }

    private static void validateIfArgumentIsDirectory(String[] arguments) {
        if (
            !isArgumentInWorkingDirectoryIsFile(arguments[0])
            && !isFile(arguments[0])
        ) {
            throw new ArgumentsNotValidException(
                MessageFormat.format(
                    "Given path [{0}] is not a file!",
                    arguments[0]
                ));
        }
    }

    public static boolean isFileExistsInWorkingDirectory(String argument) {
        return new File(Constants.WORKING_DIRECTORY + argument).exists();
    }

    public static boolean isFileExists(String argument) {
        return new File(argument).exists();
    }

    private static boolean isArgumentInWorkingDirectoryIsFile(String argument) {
        return new File(Constants.WORKING_DIRECTORY + argument).isFile();
    }

    private static boolean isFile(String argument) {
        return new File(argument).isFile();
    }

}
