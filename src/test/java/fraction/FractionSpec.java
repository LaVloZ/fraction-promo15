package fraction;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FractionSpec {

    @Test
    public void cantDealWithFractionsWithZeroDenominator(){
        assertThrows(IllegalArgumentException.class, () -> Fraction.of(3,0));
    }

    @Test
    public void isEqualToItsSimplifiedForm(){
        assertEquals(Fraction.fromInt(1),Fraction.of(2,2));
    }

    @Test
    public void isEqualToItsUnsimplifiedForm(){
        assertEquals(Fraction.of(2,2),Fraction.fromInt(1));
    }

    @Nested
    class Subtraction {
        @Test
        public void subtractTwoFractionWithTheSameDenominator() {
            assertEquals(Fraction.of(2, 5), Fraction.of(3, 5).subtract(Fraction.of(1, 5)));
        }

        @Test
        public void subtractTwoFractionWithTheDifferentDenominator() {
            assertEquals(Fraction.of(13, 30), Fraction.of(3, 5).subtract(Fraction.of(1, 6)));
        }
    }

    @Nested
    class FormatRules {
        @Test
        public void formatIntegerAsInteger() {
            assertEquals("2", Fraction.fromInt(2).toString());
        }

        @Test
        public void formatFractionAsFraction() {
            assertEquals("2/3", Fraction.of(2, 3).toString());
        }

        @Test
        public void formatResultSimplifiedForAdditionTwoFractions() {
            assertEquals("7/6", Fraction.of(2, 4).add(Fraction.of(4, 6)).toString());
        }

        @Test
        public void formatFractionToInteger() {
            assertEquals("1", Fraction.of(2, 2).toString());
        }

    }

    @Nested
    class Simplification {
        @Test
        public void doesNothingForSimplifiedFraction() {
            assertEquals(Fraction.of(2, 3), Fraction.of(2, 3));
        }

        @Test
        public void doesNothingForIntegers() {
            assertEquals(Fraction.fromInt(2), Fraction.fromInt(2));
        }

        @Test
        public void simplifyFractionByDividingNumeratorAndDenominatorByGcd() {
            assertEquals(Fraction.of(2, 3),
                    Fraction.of(2 * 3 * 5 * 11 * 13 * 17 * 19, 3 * 3 * 5 * 11 * 13 * 17 * 19));
        }
    }

    @Nested
    class AdditionRules {
        @Test
        public void zeroPlusZeroEqualsZero() {
            assertEquals(Fraction.ZERO, Fraction.ZERO.add(Fraction.ZERO));
        }

        @Test
        public void zeroIsNeutralInSecondOperandForIntegers() {
            assertEquals(Fraction.fromInt(1), Fraction.fromInt(1).add(Fraction.ZERO));
        }

        @Test
        public void zeroIsNeutralInFirstOperandForIntegers() {
            assertEquals(Fraction.fromInt(1), Fraction.ZERO.add(Fraction.fromInt(1)));
        }

        @Test
        public void canAddTwoIntegers() {
            assertEquals(Fraction.fromInt(3), Fraction.fromInt(1).add(Fraction.fromInt(2)));
        }

        @Test
        public void canAddTwoFractionsWithTheSameDenominatorByAddingTheirNumerators() {
            assertEquals(Fraction.of(4, 5),
                    Fraction.of(1, 5).add(Fraction.of(3, 5)));
        }

        @Test
        public void canAddPositiveIntegerWithNegativeInteger() {
            assertEquals(Fraction.fromInt(3), Fraction.fromInt(4).add(Fraction.fromInt(-1)));
        }

        @Test
        @DisplayName("The sum of two fractions is the addition of the multiplication of their numerators by the other denominators over the multiplication of their denominators")
        public void canAddTwoFractionsWithDifferentDominators() {
            assertEquals(Fraction.of((1 * 3) + (1 * 2), 2 * 3),
                    Fraction.of(1, 2).add(Fraction.of(1, 3)));
        }

        @Test
        public void canAddIntegerWithAFraction() {
            assertEquals(Fraction.of((2 * 3) + (1 * 1), 1 * 3),
                    Fraction.fromInt(2).add(Fraction.of(1, 3)));
        }

    }


}
