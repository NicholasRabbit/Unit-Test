package com.tdd.practice.refactor;

import com.tdd.practice.annotation.Option;
import com.tdd.practice.exception.IllegalOptionException;
import com.tdd.practice.refactor.parser_refactored.BooleanParserRefactored;
import com.tdd.practice.refactor.parser_refactored.SingleValueOptionParser;
import com.tdd.practice.refactor.parser_refactored.OptionParserRefactored;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ArgsRefactor {

    /**
     * The tutorial video of refactoring Args is saved in my cloud disk and is named "TDD in Practice".
     * */
    public static <T> T parseRefactoring(Class<T> optionsClass, String... args) throws Exception {
        List<String> arguments = Arrays.asList(args);
        Constructor<?> constructor = optionsClass.getDeclaredConstructors()[0];  // Obtain the constructor of Option
        // the return value of ".map" is a collection of the return value "parseOption(...)".
        Object[] values = Arrays.stream(constructor.getParameters()).map(it -> parseOption(arguments, it)).toArray();
        return (T) constructor.newInstance(values);

    }

    /**
     * How to refactor the code which is the bad smell?
     * 1, Extract method for the statement in "if...".
     * 2, It is proper to use a design patter named abstract factory. The reason is that classes of "if(...)" condition
     *    are embedded in Java. But we can use "Map.of(...)" instead.
     * 3, After eliminating the ugly "if" statement, we find that duplicated code in IntegerOptionParser and
     *    StringOptionParser. Then we should do the refactor brutally.
     *    In order to compare with the bad smell, I refactor in new class. See "parser_refactored" and the course video
     *    in "TDD in Practice" saved in my cloud disk.
     * */
    private static Object parseOption(List<String> arguments, Parameter parameter) {
        /*OptionParser parser = null;
        if (type == boolean.class) {
            parser = new BooleanParser();
        }
        if (type == int.class) {
            parser = new IntegerOptionParser();
        }
        if (type == String.class) {
            parser = new StringOptionParser();
        }
        return parser.parse(arguments, option);*/

        if (!parameter.isAnnotationPresent(Option.class))
            throw new IllegalOptionException(parameter.getName());

        return PARSERS.get(parameter.getType())
                .parse(arguments, parameter.getAnnotation(Option.class));

    }

    // Map.of(...)
    /*private static Map<Class<?>, OptionParser> PARSERS = Map.of(
            boolean.class, new BooleanParser(),
            int.class, new IntegerOptionParser(),
            String.class, new StringOptionParser());*/

    private static Map<Class<?>, OptionParserRefactored> PARSERS = Map.of(
            boolean.class, new BooleanParserRefactored(),
            int.class, new SingleValueOptionParser<>(Integer::parseInt, 0),
            String.class, new SingleValueOptionParser<>( String::valueOf,  ""));

}