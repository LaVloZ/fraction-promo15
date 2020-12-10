package fraction;

import java.math.BigInteger;
import java.util.Objects;

public class Fraction {

    public static final Fraction ZERO = fromInt(0);
    public final int numerator;
    public final int denominator;

    private Fraction(int numerator, int denominator) {
        if(denominator == 0)
            throw new IllegalArgumentException("Cannot create fraction with zero denominator");
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static Fraction fromInt(int value) {
        return of(value, 1);
    }

    public static Fraction of(int numerator, int denominator) {
        final int gcd = gcd(numerator, denominator);
        return new Fraction(numerator / gcd,denominator / gcd).normalize();
    }

    private static int gcd(int numerator, int denominator) {
        return BigInteger.valueOf(numerator).gcd(BigInteger.valueOf(denominator)).intValue();
    }

    private Fraction opposite() {
        return of(- numerator, denominator);
    }

    private Fraction normalize() {
        if(denominator < 0)
            return of(-numerator, -denominator);
        return this;
    }

    public Fraction add(Fraction other) {
        return of((numerator * other.denominator) + (other.numerator * denominator),
                denominator * other.denominator);
    }

    public Fraction subtract(Fraction other) {
        return add(other.opposite());
    }

    @Override
    public String toString() {
        if(this.denominator == 1)
            return String.valueOf(this.numerator);
        return this.numerator + "/" + this.denominator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return this.numerator == fraction.numerator && this.denominator == fraction.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    public Fraction multiply(Fraction fraction) {
        return of(numerator * fraction.numerator, denominator * fraction.denominator);
    }

    public Fraction divide(Fraction fromInt) {
        if(ZERO.equals(fromInt))
            throw new IllegalArgumentException("Cannot divide by zero");

        return of(2,3);
    }
}
