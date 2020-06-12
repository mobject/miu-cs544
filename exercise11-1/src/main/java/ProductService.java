public class ProductService {
    private String greeting;
    public ProductService() {}
    public ProductService(String greeting){
        this.greeting=greeting;
    }
    public void sayHello(){
        System.out.println(greeting);
    }
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}