package pl.srslycpp.myWeb.App;

import java.util.Scanner;

public class ClassTest {

    private int amount;
    private String currency;

    @Override
    public String toString() {
        return "ClassTest{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }

    public ClassTest(String currency, int amount){
        this.amount=amount;
        this.currency=currency;
    }

    public enum EnumTest1{
        PLN, HRT;
    }

    public static class AddingAmonut{
        public static ClassTest add(ClassTest amount1, ClassTest amount2){
            if(!amount1.currency.equals(amount2.currency)){
                throw  new RuntimeException("Currency does not match ");
            }
            return new ClassTest(amount1.currency, amount1.amount+amount2.amount);
        }
    }

    public enum EnumTest {

        FALL(1), SUMMER(2), SPRING(3), WINTER(4);

        private int code;

        EnumTest(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public int getMaxTemperature(EnumTest e) {

            switch (e) {
                case SUMMER:
                    return 5;
                case FALL:
                    return 4;
                case WINTER:
                    return 3;
                case SPRING:
                    return 2;
            }
            return 0;
        }

        public static EnumTest valueOf(int in) {
            for (EnumTest e : EnumTest.values()) {
                if (e.getCode() == in)
                    return e;
            }
            throw new RuntimeException(" Ni ma takiego enuma :) ");
        }
    }
        public static class Average{

            private double arr[] = new double[10] ;
            private double ava;


            public double countAva(){
                System.out.println("Enter 10 numbers: ");
                Scanner sc = new Scanner(System.in);
                for (int i=0; i<arr.length; i++){
                   arr[i] = sc.nextDouble();
                    ava = ava+arr[i];
                }
                sc.close();
                return ava/arr.length;
            }
        }
    public static class JavaExample {
        public void binary()
        {
            //Two variables to hold two input binary numbers
            long b1, b2;
            int i = 0, carry = 0;

            //This is to hold the output binary number
            int[] sum = new int[10];

            //To read the input binary numbers entered by user
            Scanner scanner = new Scanner(System.in);

            //getting first binary number from user
            System.out.print("Enter first binary number: ");
            b1 = scanner.nextLong();
            //getting second binary number from user
            System.out.print("Enter second binary number: ");
            b2 = scanner.nextLong();

            //closing scanner after use to avoid memory leak
            scanner.close();
            while (b1 != 0 || b2 != 0)
            {
                System.out.println("pierwsze i: " + i);
                sum[i++] = (int)((b1 % 10 + b2 % 10 + carry) % 2);
                System.out.println("sum[i] "+sum[i]);
                carry = (int)((b1 % 10 + b2 % 10 + carry) / 2);
                System.out.println("carry "+carry);
                b1 = b1 / 10;
                System.out.println("b1: "+b1);
                b2 = b2 / 10;
                System.out.println("b2: "+b2);
            }
            if (carry != 0) {
                System.out.println("drugie i w ifie : " + i);
                sum[i++] = carry;
            }
            --i;
            System.out.println("trzecie i: " + i);
            System.out.print("Output: ");
            while (i >= 0) {
                System.out.print(sum[i--]);
                System.out.println("czwarte i: " + i+">>> "+carry);
            }
            System.out.print("\n");
        }
    }
        public static void main(String[] args)  throws Exception{

            AddingAmonut add = new AddingAmonut();
            System.out.println(add.add(new ClassTest("PLN",6),
                    new ClassTest("PLN",6)).toString());
            Average average = new Average();
            //System.out.println(average.countAva());
            JavaExample binary = new JavaExample();
            binary.binary();

            double i=1;
            double j=2;
            double k=0;
            System.out.println("main "+ i/2);
            System.out.println("main "+ j/2);
            System.out.println("main "+ k/2);

    }
}