package fr.umontpellier.iut.exercice3;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RomanAdditionnerTest {
    private RomanAdditionner romanAdditionner = new RomanAdditionner();

    @Test
    void I_plus_I_should_give_II() {assertEquals(romanAdditionner.compute("I"), "I");}
}
