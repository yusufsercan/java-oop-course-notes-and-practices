
public class C_Alt extends B_Orta {

    static {
        System.out.println("0c. C_Alt static");
    }

    int alanC = yaz("7. C_Alt alani", 30);

    {
        System.out.println("8. C_Alt ornek blogu");
    }

    public C_Alt() {
        super();
        System.out.println("9. C_Alt constructor");
    }
}