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



/*
 * ============================================================
 * EK DERS NOTU - MIRAS, REFERANS TURU VE PROTECTED ERISIM
 * ============================================================
 *
 * KONU:
 * Farkli paketlerde miras iliskisi, this anahtar kelimesi,
 * nesne olusturma, referans turu ve protected erisim kurallari.
 *
 *
 * 1. PACKAGE, IMPORT, EXTENDS VE THIS ARASINDAKI FARK
 * ------------------------------------------------------------
 *
 * package disarida;
 * -> Bu sinifin hangi pakete ait oldugunu belirtir.
 *
 * import okul.Kayit;
 * -> Baska paketteki Kayit sinifini kisa adiyla kullanmamizi
 *    saglar. Miras alma islemi GERCEKLESTIRMEZ.
 *
 * extends Kayit
 * -> PaketDisiDemo sinifi, Kayit sinifindan miras alir.
 * -> Kayit ust sinif, PaketDisiDemo alt siniftir.
 *
 * this
 * -> O anda metodu calistiran nesnenin kendisini ifade eder.
 * -> Miras alma islemini this DEGIL, extends gerceklestirir.
 *
 * NOT:
 * extends Kayit ve extends okul.Kayit ayni mirasi kurar.
 * Ikincisinde sinifin tam paket adi yazilmistir.
 *
 *
 * 2. THIS.BOLUM NEDEN CALISIR?
 * ------------------------------------------------------------
 *
 * public class PaketDisiDemo extends Kayit {
 *
 *     public void test() {
 *         System.out.println(this.bolum);
 *     }
 * }
 *
 * Kayit sinifindaki bolum alani protected olarak tanimlidir.
 *
 * PaketDisiDemo, Kayit sinifindan miras aldigi icin
 * kendi nesnesi uzerinden bu alana erisebilir.
 *
 * this.bolum -> CALISIR.
 *
 * Burada this, mevcut PaketDisiDemo nesnesini gosterir.
 * Kayit dosyasini yeniden calistirmaz veya yeniden
 * nesne olusturmaz.
 *
 *
 * 3. KAYIT TURUNDE NESNE OLUSTURURSAK NE OLUR?
 * ------------------------------------------------------------
 *
 * Kayit k = new Kayit();
 * System.out.println(k.bolum);
 *
 * SONUC: DERLEME HATASI!
 *
 * NEDEN?
 *
 * - Kod hala disarida paketindeki PaketDisiDemo icindedir.
 * - Kayit sinifindan nesne olusturmak bizi Kayit sinifinin
 *   icine tasimaz.
 * - k referansinin turu Kayit'tir.
 * - protected alanlara farkli paketteki alt siniftan,
 *   ust sinif turundeki bir referansla erisilemez.
 *
 * ONEMLI:
 * Nesne olusturmak, erisim izinlerini degistirmez.
 *
 *
 * 4. ALT SINIF TURUNDE NESNE OLUSTURURSAK?
 * ------------------------------------------------------------
 *
 * PaketDisiDemo k = new PaketDisiDemo();
 * System.out.println(k.bolum);
 *
 * SONUC: CALISIR!
 *
 * NEDEN?
 *
 * - k referansinin turu PaketDisiDemo'dur.
 * - PaketDisiDemo, Kayit sinifinin alt sinifidir.
 * - protected bolum alanina alt sinif turundeki
 *   referans uzerinden erisilebilir.
 *
 * DIKKAT:
 * protected erisim sadece this ile sinirli degildir.
 * Ayni alt sinif turundeki baska nesnelerle de kullanilir.
 *
 *
 * 5. REFERANS TURU VE GERCEK NESNE TURU
 * ------------------------------------------------------------
 *
 * PaketDisiDemo a = new PaketDisiDemo();
 * Kayit b = a;
 *
 * BURADA NE OLDU?
 *
 * 1. new PaketDisiDemo() ile TEK bir nesne olusturuldu.
 *
 * 2. a degiskeninin turu PaketDisiDemo'dur.
 *
 * 3. Kayit b = a; satirinda yeni bir nesne olusturulmadi.
 *
 * 4. b adinda Kayit turunde yeni bir REFERANS olusturuldu.
 *
 * 5. a ve b AYNI nesneyi gosterir.
 *
 * 6. a'nin turu degismez, hala PaketDisiDemo'dur.
 *
 * 7. b'nin turu Kayit'tir.
 *
 * Nesnenin gercek turu ise PaketDisiDemo olarak kalir.
 *
 * Bu isleme UPCASTING denir.
 * Alt sinif referansi, ust sinif turundeki bir referansa
 * atanabilir.
 *
 *
 * 6. AYNI NESNEDE PROTECTED ERISIM FARKI
 * ------------------------------------------------------------
 *
 * PaketDisiDemo a = new PaketDisiDemo();
 * Kayit b = a;
 *
 * System.out.println(a.bolum); // CALISIR
 * System.out.println(b.bolum); // DERLEME HATASI
 *
 * NEDEN?
 *
 * Iki referans ayni nesneyi gostermesine ragmen
 * derleme zamanindaki turleri farklidir.
 *
 * a -> PaketDisiDemo turunde.
 * b -> Kayit turunde.
 *
 * Farkli paketteki protected erisim kontrolunde,
 * referansin derleme zamanindaki turu onemlidir.
 *
 * Bu ornekte a.bolum erisimi gecerlidir.
 * Ancak b.bolum erisimi gecersizdir.
 *
 * KURAL:
 * Farkli paketteki bir alt sinif, protected bir uyeye
 * kendi turu veya kendi turunun alt sinifi uzerinden
 * erisebilir. Ust sinif turundeki referansla erisemez.
 *
 *
 * 7. PUBLIC OLDUGUNDA NE DEGISIR?
 * ------------------------------------------------------------
 *
 * PaketDisiDemo a = new PaketDisiDemo();
 * Kayit b = a;
 *
 * System.out.println(a.kurum); // CALISIR
 * System.out.println(b.kurum); // CALISIR
 *
 * NEDEN?
 *
 * Kayit sinifindaki kurum alani public'tir.
 *
 * public uyeler farkli paketlerden de erisilebilir.
 * Bu nedenle a ve b referanslariyla erisim mumkundur.
 *
 * Burada miras iliskisi ve referans turleri,
 * public erisimi engellemez.
 *
 *
 * 8. ERISIM TABLOSU
 * ------------------------------------------------------------
 *
 * Kodlar PaketDisiDemo sinifinin icindedir.
 *
 * REFERANS              public  protected  default  private
 * ------------------------------------------------------------
 * this                   EVET    EVET       HAYIR    HAYIR
 * PaketDisiDemo a        EVET    EVET       HAYIR    HAYIR
 * Kayit b                EVET    HAYIR      HAYIR    HAYIR
 *
 * Bu tablo Kayit sinifinda tanimlanan alanlar icindir.
 * private alana dogrudan erisilemez ancak public getter
 * varsa onun uzerinden bilgi okunabilir.
 *
 *
 * 9. VIZEDE KARISTIRILMAMASI GEREKENLER
 * ------------------------------------------------------------
 *
 * - import, bir siniftan miras almaz.
 * - extends, miras iliskisi kurar.
 * - this, mevcut nesneyi gosterir.
 * - new, nesne olusturur.
 * - Kayit b = a; yeni bir nesne olusturmaz.
 * - Bir referansi baska ture atamak, ilk referansin
 *   turunu degistirmez.
 * - Nesnenin gercek turu ile referans turu farkli olabilir.
 * - protected, public gibi her yerden erisilebilir degildir.
 * - protected erisimde paket ve miras kurallari onemlidir.
 * - default erisim farkli pakette mirasla bile acilmaz.
 * - private, alt siniftan dogrudan erisilemez.
 * - Gecersiz alan erisimleri DERLEME HATASI verir.
 *
 *
 * 10. SINAV ICIN COZUM YONTEMI
 * ------------------------------------------------------------
 *
 * Bir erisim sorusuyla karsilasirsan:
 *
 * ADIM 1: Kod hangi pakette?
 *
 * ADIM 2: Erismek istenen alan hangi sinifta?
 *
 * ADIM 3: Alan public, protected, default veya private mi?
 *
 * ADIM 4: Siniflar arasinda extends iliskisi var mi?
 *
 * ADIM 5: Erisim this uzerinden mi, baska referanstan mi?
 *
 * ADIM 6: Referansin SOL TARAFTAKI TURU ne?
 *
 * ADIM 7: Erisim derleme zamaninda gecerli mi?
 *
 * OZET:
 * Nesne olusturmak ile miras almak farkli islemlerdir.
 * Referans turu ile nesnenin gercek turu ayni olmak
 * zorunda degildir.
 *
 * protected erisim sorularinda sadece new ifadesine
 * degil, referansin tanimlandigi ture de bakilmalidir.
 *
 * ============================================================
 */
