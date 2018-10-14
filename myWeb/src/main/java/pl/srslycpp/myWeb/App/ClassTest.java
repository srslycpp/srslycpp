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
                return ava/arr.length; }
        }


        public static void main(String[] args)  throws Exception{

            AddingAmonut add = new AddingAmonut();
            System.out.println(add.add(new ClassTest("PLN",6),
                    new ClassTest("PLN",6)).toString());
            Average average = new Average();
            System.out.println(average.countAva());

    }
}