

public class Hafta06TekilDemo {

    public static void main(String[] args) {

        UygulamaAyarlari a = UygulamaAyarlari.getInstance();
        UygulamaAyarlari b = UygulamaAyarlari.getInstance();

        a.setTema("koyu");

        System.out.println(a == b);

        System.out.println(b.getTema());
    }
}