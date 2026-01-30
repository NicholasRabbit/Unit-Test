package com.others;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class PalindromicPrimeNumberTest {

    @BeforeEach
    public void setUp() {

    }


    /*
    * Write a programme to get a prime number which is just larger than the
    * given number n; this prime number must be a palindromic number.
    *
    * Decompose this requirement to minor tasks.
    * 1. Get a number which is larger than 'n'.
    * 2. Verify whether this number is a prime. If not, get the next number.
    * 3. Check if this prime number is a palindrome. If not, get then next prime number.
    *
    * */
    // 1. Get a number which is larger than 'n'.
    @Test
    public void getANumberLargerThan100() {
        int n = 100;
        PalindromicPrimeNumber p = new PalindromicPrimeNumber();
        int largerNumber = p.getALargerNumber(n);

        assertTrue(largerNumber > n);

    }

    @Test
    public void getANumberLargerThanAny() {
        int n = 300;
        PalindromicPrimeNumber p = new PalindromicPrimeNumber();
        int largerNumber = p.getALargerNumber(n);
        assertTrue(largerNumber > n);
    }

    // Test if the method throw any exceptions if the argument is the maximum integer.
    @Test
    public void shouldThrowAnExceptionIfGivenMaxInt() {
        int n = 0x7f_ff_ff_ff;
        PalindromicPrimeNumber p = new PalindromicPrimeNumber();
        int largerNumber = p.getALargerNumber(n);
        assertTrue(largerNumber > n);
        String s = "abt";
        s.substring(1, 2);
    }

}
