
public class B_Orta extends A_Ust {

    static {
        System.out.println("0b. B_Orta static");
    }

    int alanB = yaz("4. B_Orta alani", 20);

    {
        System.out.println("5. B_Orta ornek blogu");
    }

    public B_Orta() {
        super();
        System.out.println("6. B_Orta constructor");
    }
}