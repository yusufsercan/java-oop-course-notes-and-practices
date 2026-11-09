

public class Hafta06KurallarDemo {

    public static void main(String[] args) {

        System.out.println(SinavKurallari.VIZE_ORANI);

        System.out.println(SinavKurallari.puan(70, 80));

        int[] kopya = SinavKurallari.esikler();

        kopya[0] = 0;

        System.out.println(SinavKurallari.esikler()[0]);
    }
}