package pl.srslycpp.myWeb.App;

public enum EnumTest {

    FALL(1), SUMMER(2), SPRIMG(3), WINTER(4);

    private int code;

    EnumTest(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public int getMaxTemperature(EnumTest e){

        switch (e){
            case SUMMER:
                return 5;
            case FALL:
                return 4;
            case WINTER:
                return 3;
            case SPRIMG:
                return 2;
        }
        return 0;
    }

    public  EnumTest valueOf (int in){
        for(EnumTest e : EnumTest.values()){
            if(e.getCode() == in)
                return e;
        }
        throw new RuntimeException(" Ni ma takiego enuma :) ");
    }

    public static void main(String[] args) {
        EnumTest enumTest = EnumTest.FALL;


        enumTest.getCode(); //1
        enumTest.getMaxTemperature(FALL); //4
        enumTest.valueOf(3); //SPRING
    }
}
