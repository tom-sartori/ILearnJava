package fr.umontpellier.iut.exercice2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class PrimeFactorsTest {
    private PrimeFactors primeFactors = new PrimeFactors();


    private List<Integer> list(int... ints) {
        List<Integer> list = new ArrayList<>();
        for (int i : ints)
            list.add(i);
        return list;
    }


    @Test
    public void one_should_give_empty() {
        assertEquals(primeFactors.computeFactors(1), list());
    }


    @Test
    public void two_should_give_2() {
        assertEquals(primeFactors.computeFactors(2), list(2));
    }


    @Test
    public void three_should_give_3() {
        assertEquals(primeFactors.computeFactors(3), list(3));
    }


    @Test
    public void four_should_give_2_and_2() {
        assertEquals(primeFactors.computeFactors(4), list(2, 2));
    }


    @Test
    public void five_should_give_5() {
        assertEquals(primeFactors.computeFactors(5), list(5));
    }


    @Test
    public void six_should_give_2_and_3() {
        assertEquals(primeFactors.computeFactors(6), list(2, 3));
    }

    @Test
    public void seven_should_give_7() {
        assertEquals(primeFactors.computeFactors(7), list(7));
    }


    @Test
    public void eight_should_give_2() {
        assertEquals(primeFactors.computeFactors(8), list(2, 2, 2));
    }


    @Test
    public void nine_should_give_3() {
        assertEquals(primeFactors.computeFactors(9), list(3, 3));
    }

    @Test
    public void ten_should_give_10() {
        assertEquals(primeFactors.computeFactors(10), list(2, 5));
    }

    @Test
    public void four_thousand_one_hundred_fifty_eight_should_give_2_3_3_3_7_11() {
        assertEquals(primeFactors.computeFactors(4158), list(2, 3, 3, 3, 7, 11)); }


    @Test
    public void three_hundred_should_give_2_2_3_5_5() {
        assertEquals(primeFactors.computeFactors(300), list(2,2,3,5,5)); }
}