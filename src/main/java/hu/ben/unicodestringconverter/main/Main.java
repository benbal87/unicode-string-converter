package hu.ben.unicodestringconverter.main;

import hu.ben.unicodestringconverter.convert.Converter;
import hu.ben.unicodestringconverter.util.ValidatorUtil;

public class Main {

    public static void main(String[] arguments) {
        ValidatorUtil.validateArgument(arguments);
        Converter converter = new Converter();
        converter.convertFile(arguments[0]);

        System.out.println("File converting ended.");
    }

}
