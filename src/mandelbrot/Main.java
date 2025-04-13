package mandelbrot;

public class Main {
    public static void main(String[] args) {
        ComplexNumbers z1 = new ComplexNumbers(3, 2);
        ComplexNumbers z2 = new ComplexNumbers(1, -4);

        ComplexNumbers summe12 = z1.add(z2); //z1 + z2
        ComplexNumbers summe21 = z2.add(z1); //z2 + z1
        
        ComplexNumbers produkt = z1.multiply(z2);

        System.out.println("z1: " + z1);
        System.out.println("z2: " + z2);
        System.out.println("Summe: " + summe21);
        System.out.println("Summe: " + summe12);
        System.out.println("Produkt: " + produkt);
    }
}

