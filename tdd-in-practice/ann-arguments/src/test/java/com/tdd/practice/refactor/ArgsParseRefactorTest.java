package com.tdd.practice.refactor;

import com.tdd.practice.TooManyArgumentsException;
import com.tdd.practice.refactor.option.BooleanOption;
import com.tdd.practice.refactor.option.IntegerOption;
import com.tdd.practice.refactor.option.Options;
import com.tdd.practice.refactor.option.StringOption;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArgsParseRefactorTest {


    /*
    * A test of parsing arguments of command line.
    * For example: -l true/false -p 8080 -d /usr/logs
    * Explanation:
    * -l : logging, if "-l" is presented, the value is true, and if "-l" is omitted the value is "false"
    * -p : port
    * -d : directory
    * */
    @Test
    public void testMultipleParing() throws Exception {

        Options options = ArgsRefactor.parseRefactoring(Options.class, "-l", "-p", "8080", "-d", "/usr/logs");

        assertTrue(options.logging());
        assertEquals(8080, options.port());
        assertEquals("/usr/logs", options.dir());

    }

    @Test
    public void shouldSetTrueIfFlagPresents() throws Exception {
        BooleanOption booleanOption = ArgsRefactor.parseRefactoring(BooleanOption.class, "-l");
        assertTrue(booleanOption.logging());
    }

    @Test
    public void shouldSetFalseIfFlagNotPresents() throws Exception {
        BooleanOption booleanOption = ArgsRefactor.parseRefactoring(BooleanOption.class);
        assertFalse(booleanOption.logging());
    }

    @Disabled
    @Test
    public void shouldThrowTooManyArgumentsException() throws Exception{
        BooleanOption booleanOption;
        try {
            booleanOption = ArgsRefactor.parseRefactoring(BooleanOption.class, "-l", "abc");
            fail("Should throw an exception: Too many arguments.");
        } catch (TooManyArgumentsException e) {
            assertEquals("Too many arguments.", e.getMessage());
        }

    }



    /**
     * 2, Integer: -p 8080
     * Note: We must make sure that the previous tests are still passed when we are refactoring.
     * */
    @Test
    public void shouldParseIntIfPortPresents() throws Exception {
        IntegerOption integerOption = ArgsRefactor.parseRefactoring(IntegerOption.class, "-p", "8080");
        assertEquals(8080, integerOption.port());
    }

    /**
     * 3, String: -d /usr/logs
     * */
    @Test
    public void shouldSetDir() throws Exception {
        StringOption stringOption = ArgsRefactor.parseRefactoring(StringOption.class, "-d", "/usr/logs");
        assertEquals("/usr/logs", stringOption.dir());
    }


}
