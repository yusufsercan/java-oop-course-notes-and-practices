
public class Gorev2_KanBasinciDemo
{
    public static void main(String[] args)
    {
        KanBasinci k = new KanBasinci(118, 76);

        System.out.println("Olcum 1 : " + k);
        System.out.println("Nabiz basinci: " + k.getNabizBasinci());

        System.out.println("\n--- Tutarsiz istekler ---");

        k.ayarla(90, 130);
        k.ayarla(30, 260);
        k.ayarla(120, 120);

        System.out.println("Degismedi: " + k);

        System.out.println("\n--- Gecerli olcumler ---");

        k.ayarla(135, 85);
        System.out.println("Olcum 2 : " + k);

        k.ayarla(152, 96);
        System.out.println("Olcum 3 : " + k);

        System.out.println("Nabiz basinci: " + k.getNabizBasinci());
    }
}
