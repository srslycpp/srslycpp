package pl.srslycpp.myWeb.App;

public abstract class AbstractClassTest{

    public abstract void run();

    public void run2(){};

    @Override
    public String toString() {
        return super.toString();
    }

    AbstractClassTest(){
        System.out.println("abstract Class Test");
    };
}
