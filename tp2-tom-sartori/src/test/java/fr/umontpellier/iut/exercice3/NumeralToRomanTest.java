package fr.umontpellier.iut.exercice3;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumeralToRomanTest {

    private NumeralToRoman numeralToRoman = new NumeralToRoman();


    @Test
    void one_should_give_I() {
        assertEquals(numeralToRoman.getRoman(1), "I");
    }


    @Test
    void five_should_give_V() {
        assertEquals(numeralToRoman.getRoman(5), "V");
    }


    @Test
    void ten_should_give_X() {
        assertEquals(numeralToRoman.getRoman(10), "X");
    }


    @Test
    void fifty_should_give_L() {
        assertEquals(numeralToRoman.getRoman(50), "L");
    }


    @Test
    void hundred_should_give_C() {
        assertEquals(numeralToRoman.getRoman(100), "C");
    }


    @Test
    void fivehundred_should_give_D() {
        assertEquals(numeralToRoman.getRoman(500), "D");
    }


    @Test
    void thousand_should_give_M() {
        assertEquals(numeralToRoman.getRoman(1000), "M");
    }


    @Test
    void two_should_give_II() {
        assertEquals(numeralToRoman.getRoman(2), "II");
    }


    @Test
    void three_should_give_III() {
        assertEquals(numeralToRoman.getRoman(3), "III");
    }


    @Test
    void four_should_give_IV() {
        assertEquals(numeralToRoman.getRoman(4), "IV");
    }


    @Test
    void six_should_give_VI() {
        assertEquals(numeralToRoman.getRoman(6), "VI");
    }


    @Test
    void seven_should_give_VII() {
        assertEquals(numeralToRoman.getRoman(7), "VII");
    }


    @Test
    void eight_should_give_VIII() {
        assertEquals(numeralToRoman.getRoman(8), "VIII");
    }


    @Test
    void nine_should_give_IX() {
        assertEquals(numeralToRoman.getRoman(9), "IX");
    }

    @Test
    void XI_should_give_11() {
        assertEquals(numeralToRoman.getRoman(11), "XI");
    }


    @Test
    void thousandsixtysix_should_give_1066() {
        assertEquals(numeralToRoman.getRoman(1066), "MLXVI");
    }

}