package fr.umontpellier.iut.exercice1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


public class FizzBuzzTest {
    private FizzBuzz fizzBuzz = new FizzBuzz();

    @Test
    public void test_one_should_print_one() {
        String result = fizzBuzz.getValue(1);
        assertEquals(result,"1");
    }

    @Test
    public void test_two_should_print_two() {
        String result = fizzBuzz.getValue(2);
        assertEquals(result,"2");
    }

    @Test
    public void test_three_should_print_fizz() {
        String result = fizzBuzz.getValue(3);
        assertEquals(result,"Fizz");
    }

    @Test
    public void test_five_should_print_buzz() {
        String result = fizzBuzz.getValue(5);
        assertEquals(result,"Buzz");
    }

    @Test
    public void test_six_should_print_fizz() {
        String result = fizzBuzz.getValue(6);
        assertEquals(result,"Fizz");
    }

    @Test
    public void test_ten_should_print_buzz() {
        String result = fizzBuzz.getValue(10);
        assertEquals(result,"Buzz");
    }

    @Test
    public void test_fifteen_should_print_fizzbuzz() {
        String result = fizzBuzz.getValue(15);
        assertEquals(result,"FizzBuzz");
    }

    @Test
    public void test_getList_Should_Return_a_sequence_of_5_FizzBuzz_elements_when_5() {
        String[] result = fizzBuzz.computeList(5);
        assertEquals(result[0],"1");
        assertEquals(result[1],"2");
        assertEquals(result[2],"Fizz");
        assertEquals(result[3],"4");
        assertEquals(result[4],"Buzz");
    }
}
