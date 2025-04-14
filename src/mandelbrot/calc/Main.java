package mandelbrot.calc;

public class Main {
    public static void main(String[] args) {
        ComplexNumbers c1 = new ComplexNumbers(0.3, 0.2);
        ComplexNumbers c2 = new ComplexNumbers(1, -4);

        ComplexNumbers summe12 = c1.add(c2); //c1 + c2
        ComplexNumbers summe21 = c2.add(c1); //c2 + c1
        
        ComplexNumbers produkt = c1.multiply(c2);
        ComplexNumbers z = new ComplexNumbers(0, 0);
        
        for(int n = 0 ; n<100 ; n++)
        {
        	z=recursive_step(z,c1);
            System.err.println("z_: " +n+z);

        }
        
        
        System.out.println("c1: " + c1);
        System.out.println("c2: " + c2);
        System.out.println("Summe: " + summe21);
        System.out.println("Summe: " + summe12);
        System.out.println("Produkt: " + produkt);
    }
    
    static ComplexNumbers  recursive_step(ComplexNumbers zn, ComplexNumbers c)
    {
    	return zn.multiply(zn).add(c); 
    }
}

