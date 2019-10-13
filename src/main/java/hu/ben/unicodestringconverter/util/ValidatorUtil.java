package hu.ben.unicodestringconverter.util;

import java.io.File;
import java.text.MessageFormat;

import hu.ben.unicodestringconverter.constant.Constants;
import hu.ben.unicodestringconverter.exception.ArgumentsNotValidException;

public final class ValidatorUtil {

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
        if (!new File(Constants.WORKING_DIRECTORY + arguments[0]).exists()) {
            throw new ArgumentsNotValidException(MessageFormat.format(
                "Given path [{0}] does not exists!",
                arguments[0]
            ));
        }
    }

    private static void validateIfArgumentIsDirectory(String[] arguments) {
        if (!new File(Constants.WORKING_DIRECTORY + arguments[0]).isFile()) {
            throw new ArgumentsNotValidException(
                MessageFormat.format(
                    "Given path [{0}] is not a file!",
                    arguments[0]
                ));
        }
    }

    private ValidatorUtil() {
    }

}
