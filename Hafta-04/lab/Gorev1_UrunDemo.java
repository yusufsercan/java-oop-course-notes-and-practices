

public class Gorev1_UrunDemo
{
    public static void main(String[] args)
    {
        Urun u = new Urun("8690001", "Defter A5", 42.50, 30);

        System.out.println("Baslangic : " + u);

        System.out.println("\n--- Gecersiz istekler ---");

        u.setFiyat(-10);
        u.setAd("   ");
        u.stokCik(500);

        System.out.println("Degismedi : " + u);

        System.out.println("\n--- Gecerli islemler ---");

        u.setFiyat(45.00);
        u.stokGir(5);
        u.stokCik(12);

        System.out.println("Son durum : " + u);

        System.out.printf(
           
            "Stok degeri: %.2f TL%n",
            u.getStokDegeri()
        );
    }
}
