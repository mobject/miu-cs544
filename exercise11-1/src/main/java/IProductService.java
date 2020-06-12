public class IProduct {
    private String greeting;
    public IProduct() {}
    public IProduct(String greeting){
        this.greeting=greeting;
    }
    public void sayHello(){
        System.out.println(greeting);
    }
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}