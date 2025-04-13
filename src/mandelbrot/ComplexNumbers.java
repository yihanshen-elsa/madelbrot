package mandelbrot;

public class ComplexNumbers {
    private double real;
    private double imag;

    // Konstruktor
    public ComplexNumbers(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    // Getter
    public double getReal() {
        return real;
    }

    public double getImag() {
        return imag;
    }

    // Addition
    public ComplexNumbers add(ComplexNumbers andere) {
        return new ComplexNumbers(this.real + andere.real, this.imag + andere.imag);
    }

    // Subtraktion
    public ComplexNumbers subtract(ComplexNumbers other) {
        return new ComplexNumbers(this.real - other.real, this.imag - other.imag);
    }

    // Multiplikation (komplex!)
    public ComplexNumbers multiply(ComplexNumbers other) {
        double neuerReal = this.real * other.real - this.imag * other.imag;
        double neuerImag = this.real * other.imag + this.imag * other.real;
        return new ComplexNumbers(neuerReal, neuerImag);
    }

    // toString
    @Override
    public String toString() {
        if (imag >= 0) {
            return real + " + " + imag + "i";
        } else {
            return real + " - " + (-imag) + "i";
        }
    }
}

