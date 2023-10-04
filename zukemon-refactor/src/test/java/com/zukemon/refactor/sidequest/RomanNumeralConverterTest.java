package com.zukemon.refactor.sidequest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RomanNumeralConverterTest {

    @Test
    public void test1() {
        RomanNumeralConverter converter = new RomanNumeralConverter();

        Integer result = converter.convert("I");

        assertThat(result).isEqualTo(1);
    }

    @Test
    public void test2() {
        RomanNumeralConverter converter = new RomanNumeralConverter();

        Integer result = converter.convert("II");

        assertThat(result).isEqualTo(2);
    }

    @Test
    public void test3() {
        RomanNumeralConverter converter = new RomanNumeralConverter();

        Integer result = converter.convert("III");

        assertThat(result).isEqualTo(3);
    }

    @Test
    public void test5() {
        RomanNumeralConverter converter = new RomanNumeralConverter();

        Integer result = converter.convert("V");

        assertThat(result).isEqualTo(5);
    }

    @Test
    public void test6() {
        RomanNumeralConverter converter = new RomanNumeralConverter();

        Integer result = converter.convert("VI");

        assertThat(result).isEqualTo(6);
    }

    @Test
    public void test7() {
        RomanNumeralConverter converter = new RomanNumeralConverter();

        Integer result = converter.convert("VII");

        assertThat(result).isEqualTo(7);
    }

    @Test
    public void test10() {
        RomanNumeralConverter converter = new RomanNumeralConverter();

        Integer result = converter.convert("X");

        assertThat(result).isEqualTo(10);
    }

    @Test
    public void test4() {
        RomanNumeralConverter converter = new RomanNumeralConverter();

        Integer result = converter.convert("IV");

        assertThat(result).isEqualTo(4);
    }

    @Test
    public void test9() {
        RomanNumeralConverter converter = new RomanNumeralConverter();

        Integer result = converter.convert("IX");

        assertThat(result).isEqualTo(9);
    }

    @Test
    public void test1000() {
        RomanNumeralConverter converter = new RomanNumeralConverter();

        Integer result = converter.convert("M");

        assertThat(result).isEqualTo(1000);
    }

    @Test
    public void test100() {
        RomanNumeralConverter converter = new RomanNumeralConverter();

        Integer result = converter.convert("C");

        assertThat(result).isEqualTo(100);
    }

    @Test
    public void test1999() {
        RomanNumeralConverter converter = new RomanNumeralConverter();

        Integer result = converter.convert("MCMXCIX");

        assertThat(result).isEqualTo(1999);
    }

    private class RomanNumeralConverter {
        public Integer convert(String romanNumeral) {
            return 1;
        }
    }
}
