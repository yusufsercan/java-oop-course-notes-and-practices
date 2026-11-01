/*
 * Nesne Yonelimli Programlama - Hafta 04
 * Ornek 10b: AYNI PAKETTEN erisim
 *
 * Bu sinif da "okul" paketindedir. Bu nedenle public, protected ve
 * varsayilan (paket) uyelerin ucune de erisebilir; private uyeye erisemez.
 */
package okul;

public class PaketIciDemo {

    public static void main(String[] args) {
        Kayit k = new Kayit(); // burada Kayit sınıfının bir örneğini oluşturuyoruz. Bu sınıf aynı paket içinde olduğundan, erişim düzeylerini test edebiliriz.
        System.out.println("=== Ayni paket: okul.PaketIciDemo ===");
        System.out.println("  public    kurum = " + k.kurum);
        System.out.println("  protected bolum = " + k.bolum);
        System.out.println("  varsayilan donem = " + k.donem);
        // System.out.println(k.gizliId);   // DERLEME HATASI: private
        System.out.println("  private   gizliId -> yalnizca getGizliId(): "
                + k.getGizliId());// burada getGizliId() metodu aracılığıyla private alanına erişiyoruz. Bu, kapsülleme ilkesine uygun bir yaklaşımdır.
        System.out.println();
        k.hepsiniYaz();// burada hepsiniYaz() metodu çağrılıyor ve sınıfın kendi alanlarını yazdırıyor. Bu metod, private alanına erişebilir çünkü aynı sınıf içindedir.
    }
}

/*
bu koddaki önemli noktalar:
1. PaketIciDemo sınıfı, Kayit sınıfı ile aynı pakette olduğundan, Kayit sınıfının public, protected ve default (paket) üyelerine erişebilir.
2. private üye olan gizliId'ye doğrudan erişim sağlanamaz, ancak getGizliId() metodu aracılığıyla erişilebilir.
3. hepsiniYaz() metodu, Kayit sınıfının kendi alanlarını yazdırır ve private alanına erişebilir çünkü aynı sınıf içindedir.
*/


/*
 * ORNEK 10B - PAKETICIDEMO.JAVA
 * KONU: AYNI PAKETTEN ERISIM
 *
 * 1. KODUN AMACI:
 * Ayni paket icindeki farkli iki sinifin birbirlerinin
 * uyelerine hangi kurallarla erisebildigini gostermek.
 *
 * 2. PAKET ILISKISI:
 *
 * Kayit.java        -> package okul;
 * PaketIciDemo.java -> package okul;
 *
 * Iki sinif da ayni pakettedir.
 * Bu nedenle PaketIciDemo, Kayit sinifinin
 * public, protected ve default uyelerine erisebilir.
 *
 * 3. KODUN CALISMA SIRASI:
 *
 * ADIM 1:
 * Kayit k = new Kayit();
 *
 * Kayit sinifindan bir nesne olusturulur.
 * "k" referansi bu nesneyi gosterir.
 *
 * ADIM 2:
 * k.kurum -> public oldugu icin ERISILEBILIR.
 *
 * ADIM 3:
 * k.bolum -> protected oldugu ve iki sinif ayni
 * pakette bulundugu icin ERISILEBILIR.
 *
 * ADIM 4:
 * k.donem -> default erisimlidir.
 * Iki sinif ayni pakette oldugu icin ERISILEBILIR.
 *
 * ADIM 5:
 * k.gizliId -> private oldugu icin ERISILEMEZ!
 *
 * Bu satir yorumdan cikarilirsa DERLEME HATASI olusur.
 * Cunku private uye yalnizca Kayit sinifina aittir.
 *
 * ADIM 6:
 * k.getGizliId() -> public getter cagrilir.
 * Getter, Kayit sinifinin icinde oldugu icin
 * private alani okuyup sonucunu dondurebilir.
 *
 * ADIM 7:
 * k.hepsiniYaz() -> Kayit sinifinin public metodu
 * cagrilir. Metot kendi sinifinin butun alanlarini
 * ekrana yazdirir.
 *
 * 4. ONEMLI AYRIM:
 *
 * AYNI PAKETTE OLMAK != AYNI SINIFTA OLMAK
 *
 * PaketIciDemo ve Kayit farkli siniflardir.
 * Dolayisiyla private uyeler paylasilmaz.
 *
 * Ancak ayni pakette olduklari icin default ve
 * protected uyelere erisim mumkundur.
 *
 * 5. SINAVDA DIKKAT:
 *
 * - Ayni paketteki farkli siniflar private alana
 *   dogrudan erisemez.
 * - protected erisim icin ayni pakette miras
 *   iliskisi kurulmasi zorunlu degildir.
 * - import yazilmamasi normaldir; iki sinif da
 *   ayni paket icindedir.
 *
 * SONUC:
 * Ayni paketteki farkli bir sinif;
 * public + protected + default uyelere erisebilir.
 * private uyelere dogrudan erisemez.
 */

