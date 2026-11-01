/*
 * Nesne Yonelimli Programlama - Hafta 04
 * Ornek 10a: Dort erisim duzeyinin ayni sinifta gosterimi
 *
 * Bu sinif "okul" paketindedir. Dort uyesi dort farkli erisim duzeyindedir.
 * Hangi uyeye nereden erisilebildigini Ornek 10b ve 10c gosterir.
 */

package okul;

public class Kayit {

    public    String kurum   = "KTU Of Teknoloji Fakultesi";  // her yerden
    protected String bolum   = "Yazilim Muhendisligi";        // paket + alt sinif
              String donem   = "2026-2027 Guz";               // yalnizca paket  herhangi bir belirteci yoksa default (paket) erisim duzeyi varsayilir
    private   String gizliId = "K-000731";                    // yalnizca bu sinif

    public String getGizliId() {
        return gizliId;    // private alana ancak sinifin kendi metodu erisir
    }

    public void hepsiniYaz() {
        System.out.println("  [Kayit icinden] kurum   = " + kurum);
        System.out.println("  [Kayit icinden] bolum   = " + bolum);
        System.out.println("  [Kayit icinden] donem   = " + donem);
        System.out.println("  [Kayit icinden] gizliId = " + gizliId);
    }
}
/*
 bu koddaki mantık, erişim belirleyicilerin (access modifiers) nasıl çalıştığını göstermek için tasarlanmıştır.
önemli noktalar:
1. Dört erişim düzeyi vardır:
   - public: Her yerden erişilebilir.
   - protected: Aynı paket içinden ve alt sınıflardan erişilebilir.
   - default (paket-private): Sadece aynı paket içinden erişilebilir.
   - private: Sadece tanımlandığı sınıf içinden erişilebilir.
2. 'getGizliId()' metodu, private olan 'gizliId' alanına erişim sağlar. Bu, kapsülleme (encapsulation) ilkesine uygun bir yaklaşımdır.
3. 'hepsiniYaz()' metodu, sınıfın kendi alanlarını yazdırır ve private alanına erişebilir çünkü aynı sınıf içindedir.

*/



/*
 * ORNEK 10A - KAYIT.JAVA
 * KONU: ERISIM BELIRLEYICILERI (ACCESS MODIFIERS)
 *
 * 1. KODUN AMACI:
 * Java'daki dort erisim duzeyini ayni sinif uzerinde
 * gostermek. Bu sinif, diger iki ornek tarafindan
 * kullanilarak erisim kurallarinin test edilmesini saglar.
 *
 * 2. PACKAGE KAVRAMI:
 * package okul; -> Bu sinifin "okul" paketine ait
 * oldugunu belirtir.
 *
 * Paketler, siniflari mantiksal olarak gruplandirir.
 * Paket farki, bazi uyelere erisilip erisilemeyecegini
 * dogrudan etkiler.
 *
 * 3. DORT ERISIM BELIRLEYICISI:
 *
 * public String kurum:
 * - Her yerden erisilebilir.
 * - Ayni sinif, ayni paket ve farkli paket fark etmez.
 *
 * protected String bolum:
 * - Ayni paket icindeki siniflardan erisilebilir.
 * - Farkli paketteki alt siniflardan da miras yoluyla
 *   belirli kurallar dahilinde erisilebilir.
 *
 * String donem:
 * - Basinda erisim belirleyicisi bulunmaz.
 * - Bu nedenle DEFAULT (package-private) erisimlidir.
 * - Yalnizca ayni paket icindeki siniflardan erisilir.
 * - Farkli paketteki alt siniflardan bile erisilemez.
 *
 * private String gizliId:
 * - Yalnizca Kayit sinifinin kendi icinden erisilebilir.
 * - Ayni paketteki baska siniflar da dogrudan erisemez.
 *
 * 4. METOTLAR:
 *
 * getGizliId():
 * private olan gizliId bilgisini public metot
 * araciligiyla disariya okutur.
 *
 * DIKKAT: private, degerin asla gorulemeyecegi anlamina
 * gelmez. Dogrudan erisim yasaktir ama sinif, public
 * getter araciligiyla bilgiyi disariya sunabilir.
 *
 * hepsiniYaz():
 * Sinifin kendi icindeki tum alanlari yazdirir.
 * private dahil butun alanlara erisebilir.
 *
 * 5. SINIFLAR ARASINDAKI ILISKI:
 *
 * Kayit.java          -> Alanlari tanimlar.
 * PaketIciDemo.java   -> Ayni paketten erisimi test eder.
 * PaketDisiDemo.java  -> Farkli paketten erisimi test eder.
 *
 * 6. SINAVDA DIKKAT:
 *
 * - public: Her yerden erisim.
 * - protected: Ayni paket + miras kurallari.
 * - default: Sadece ayni paket.
 * - private: Sadece tanimlandigi sinif.
 *
 * - default icin herhangi bir anahtar kelime YAZILMAZ.
 * - private bir uyeye ayni sinifin metotlari erisebilir.
 * - Bu ornekte public alanlar ogretim amaciyla kullanildi.
 *   Gercek tasarimlarda alanlar genellikle private olur.
 */

