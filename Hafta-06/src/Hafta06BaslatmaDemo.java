

public class Hafta06BaslatmaDemo {

    public static void main(String[] args) {

        System.out.println("MAIN BASLADI");

        Oturum a = new Oturum("Ali");
        Oturum b = new Oturum("Ayse");

        System.out.println("TOPLAM: " + Oturum.toplam());
    }
}