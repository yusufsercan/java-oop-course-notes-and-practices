
public class SayacDemo 
{

    public static void main(String[] args) {

        System.out.println(EtkinlikBileti.uretilenSayisi());

        EtkinlikBileti a = new EtkinlikBileti("Ali");
        EtkinlikBileti b = new EtkinlikBileti("Ayse");

        System.out.println(a.bilgi());
        System.out.println(b.bilgi());

        System.out.println(EtkinlikBileti.uretilenSayisi());
    }
}