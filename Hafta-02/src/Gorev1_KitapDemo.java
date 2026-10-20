public class Gorev1_KitapDemo {
    public static void main(String[] args) {
        System.out.println("--- Kutuphane ---");
        Kitap k1 = new Kitap("Nesne Yonelimli Programlama", "D.thoure", 520, 2024, 480.0);
        Kitap k2 = new Kitap("Veri Yapilari", "A. utyaru", 310, 2019, 0.0);
        
        // Yeni yapıcı testi (Fiyatı sonradan belirlenen 3. kitap):
        Kitap k3 = new Kitap("Algoritmalar", "C. Leiserson", 450, 2022);
        k3.setFiyat(199.90);

        System.out.println(k1);
        System.out.println(k2);
        System.out.println(k3);

        // --- Kopya Yapıcı Testi ---
        System.out.println("\n--- Kopya yapici testi ---");
        Kitap kopya = new Kitap(k1);
        System.out.println("k1 == kopya -> " + (k1 == kopya)); // Adresler farklı olduğu için 'false' basar[cite: 9]

        // --- Dizi ile Raf Toplamı ve En Pahalı Kitabı Bulma ---
        Kitap[] raf = { k1, k2, k3 };

        double toplamDeger = 0.0;
        Kitap enPahali = raf[0];

        for (Kitap k : raf) {
            toplamDeger += k.getFiyat();
            if (k.getFiyat() > enPahali.getFiyat()) {
                enPahali = k;
            }
        }

        System.out.printf("%nRaf toplam degeri : %.2f TL%n", toplamDeger);
        System.out.printf("En pahali kitap   : %s%n", enPahali.getBaslik());
    }
}

/*
gorev1
==================================================================================
1. Alanlar private olmasaydı:
   -> Nesne doğduktan sonra dışarıdan doğrudan müdahale ile geçersiz değer 
      atanabilmesi (veri bütünlüğünün bozulması) sorunu geri gelirdi.

2. Doğrulama main içine yazılsaydı:
   -> Kod tekrarı (DRY ihlali) meydana gelir ve kontrollerin unutulduğu çağrılarda 
      nesnenin geçersiz verilerle oluşması engellenemezdi.

3. toString() ezilmeseydi (override edilmeseydi):
   -> Object sınıfının varsayılan çıktısı olan 'Kitap@hexadecimalKod' yazılırdı.
================================================================================

gorev2
================================================================================
1. Değişiklik Sayısı:
   -> Ayrı ayrı yazılsaydı 3 yer gerekirdi.
   -> this(...) zinciri sayesinde sadece 1 merkezi yerde değiştirmek yeterlidir[cite: 8, 10].

2. İki String Parametreli Yapıcı Çakışması:
   -> Bulunamaz. Derleyici isimlere değil, parametre türlerinin sırasına 
      bakar [(String, String)]. İki imza aynı olduğu için belirsizlik hatası verir[cite: 10].

3. Nesne Kopyalama ve '==' Operatörü:
   -> 'kopya = k1'           : Adres kopyalar, aynı nesneyi gösterir -> (k1 == kopya) TRUE[cite: 9, 10].
   -> 'kopya = new Kitap(k1)': Yeni nesne üretir, bağımsız adrestir   -> (k1 == kopya) FALSE[cite: 8, 9, 10].
================================================================================

*/