public class Gorev1_KitapDemo {
    public static void main(String[] args) {
        System.out.println("--- Kutuphane ---");

        // Föydeki terminal çıktısını birebir üreten kitaplar
        Kitap k1 = new Kitap("Nesne Yonelimli Programlama", "R. Benveniste", 520, 2024, 480.0);
        Kitap k2 = new Kitap("Veri Yapilari", "A. Demir", 310, 2019, 0.0);

        System.out.println(k1);
        System.out.println(k2);

        // Hatalı değer doğrulama denemesi (Savunmacı test)
        System.out.println("\n--- Hatali Deger Denemesi ---");
        Kitap k3 = new Kitap("", null, -10, 1300, -50.0);
        System.out.println(k3);
    }
}