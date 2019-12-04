package hu.ben.unicodestringconverter.main;

import java.io.File;

import hu.ben.unicodestringconverter.constant.Constants;
import hu.ben.unicodestringconverter.convert.Converter;
import hu.ben.unicodestringconverter.util.ValidatorUtil;

public class Main {

    public static void main(String[] arguments) {
        ValidatorUtil.validateArgument(arguments);

        File file = getTextFile(arguments[0]);

        Converter converter = new Converter();
        converter.convertFile(file);

        System.out.println("File converting ended.");
    }

    private static File getTextFile(String path) {
        File file = null;

        if (ValidatorUtil.isFileExistsInWorkingDirectory(path)) {
            file = new File(Constants.WORKING_DIRECTORY + path);
        } else if (ValidatorUtil.isFileExists(path)) {
            file = new File(path);
        }

        return file;
    }

}
