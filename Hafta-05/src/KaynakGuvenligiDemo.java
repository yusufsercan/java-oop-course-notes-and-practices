
class Kaynak implements AutoCloseable {

    private final String ad;
    private boolean acik;

    // CONSTRUCTOR: Kaynagi aciyoruz.
    public Kaynak(String ad) {
        this.ad = ad;
        this.acik = true;

        System.out.println("ACILDI: " + ad);
    }

    // Kaynak uzerinde bir islem
    public void islemYap() {
        if (!acik) {
            throw new IllegalStateException("Kaynak kapali!");
        }

        System.out.println("ISLEM YAPILDI: " + ad);
    }

    // Kapatma metodu
    @Override
    public void close() {
        if (acik) {
            acik = false;
            System.out.println("KAPANDI: " + ad);
        }
    }
}

public class KaynakGuvenligiDemo {

    public static void main(String[] args) {

        System.out.println("1 - NORMAL DURUM");

        try (Kaynak k = new Kaynak("rapor.txt")) {
            k.islemYap();
        }

        System.out.println("2 - HATA DURUMU");

        try (Kaynak k = new Kaynak("veri.txt")) {

            k.islemYap();

            throw new RuntimeException("Beklenmedik hata!");

        } catch (RuntimeException e) {

            System.out.println("HATA YAKALANDI: " + e.getMessage());
        }
    }
}

/*
 * ============================================================
 * HAFTA 05 - GARBAGE COLLECTOR, AUTOCLOSEABLE VE
 *           TRY-WITH-RESOURCES
 * ============================================================
 *
 * 1. BU ORNEGI NEDEN YAZDIK?
 * ------------------------------------------------------------
 *
 * Onceki derslerde Java'da nesne olusturmayi
 * ve constructor kullanmayi ogrendik.
 *
 * Ancak su sorularin cevabini bilmiyorduk:
 *
 * - Bir nesne artik kullanilmiyorsa ne olur?
 * - Nesnenin kullandigi bellek nasil temizlenir?
 * - Program bir dosya actiginda onu kim kapatir?
 * - Dosya acikken hata olusursa ne yapilir?
 *
 * Bu ornekte iki farkli kavrami ogrendik:
 *
 * A) Bellek yonetimi -> Garbage Collector
 * B) Kaynak yonetimi -> AutoCloseable ve
 *                      try-with-resources
 *
 * ONEMLI:
 *
 * Bunlar ayni isi yapmaz.
 *
 * Garbage Collector nesnelerin bellegini
 * yonetir.
 *
 * try-with-resources ise kullandigimiz dosya,
 * baglanti vb. kaynaklarin zamaninda
 * kapatilmasini saglar.
 *
 *
 * 2. JAVA'DA GARBAGE COLLECTOR NEDIR?
 * ------------------------------------------------------------
 *
 * C++ dilinde bellek yonetiminde programcinin
 * sorumluluklari daha fazladir.
 *
 * Java'da ise Garbage Collector (GC) vardir.
 *
 * GC, artik erisilemeyen nesnelerin kullandigi
 * bellegi geri kazanabilir.
 *
 * Ornek:
 *
 * Ogrenci a = new Ogrenci("Ali");
 *
 * a = null;
 *
 * Ilk satirda yeni bir nesne olusturulur.
 *
 * Ikinci satirda a referansi artik o nesneyi
 * gostermez.
 *
 * Nesneye baska bir yoldan da erisilemiyorsa
 * nesne cop toplanmaya uygun hale gelir.
 *
 * FAKAT:
 *
 * a = null yazmak nesneyi hemen bellekten
 * silmez.
 *
 * GC'nin ne zaman calisacagina JVM karar verir.
 *
 * System.gc() kullanmak bile temizligin
 * kesin olarak gerceklesecegini garanti etmez.
 *
 * Java'da C++'taki gibi destructor yoktur.
 *
 * finalize() da guvenilir bir destructor
 * degildir; kullanimi terk edilmistir.
 *
 *
 * 3. PEKI NEDEN GC TEK BASINA YETERLI DEGIL?
 * ------------------------------------------------------------
 *
 * Bir programin rapor.txt dosyasini actigini
 * dusunelim.
 *
 * Program dosyaya yazma islemi yapiyor.
 *
 * Daha sonra islem tamamlandiginda dosyanin
 * kapatilmasi gerekiyor.
 *
 * Ancak program dosyayi kapatmadan once
 * hata verirse ne olacak?
 *
 * GC bellegi yonetse bile dosyanin ne zaman
 * kapatilacagini garanti etmez.
 *
 * Bu yuzden dosya, soket ve veritabani
 * baglantisi gibi dis kaynaklarda
 * acik bir kapatma islemi gerekir.
 *
 * Bunun icin AutoCloseable arayuzunu ve
 * try-with-resources yapisini kullaniyoruz.
 *
 * NOT:
 *
 * Bu ornekte gercek dosya acilmiyor.
 *
 * Dosya acma ve kapatma islemleri ekrana
 * mesaj yazdirilarak SIMULE ediliyor.
 *
 *
 * ============================================================
 * 4. KAYNAK SINIFININ INCELENMESI
 * ============================================================
 *
 * class Kaynak implements AutoCloseable
 *
 * Burada Kaynak adinda bir sinif tanimladik.
 *
 * implements:
 *
 * Bir sinifin bir arayuzu (interface)
 * uyguladigini belirtir.
 *
 * AutoCloseable:
 *
 * Java'nin sundugu bir arayuzdur.
 *
 * Bir nesnenin kapatilabilir bir kaynak
 * olarak kullanilabilmesini saglar.
 *
 * Bu arayuz, close() metodunu tanimlar.
 *
 * Kaynak sinifimiz da close() metodunu
 * uygulamak zorundadir.
 *
 * Bu sayede Kaynak nesnelerini
 * try-with-resources icinde kullanabiliriz.
 *
 *
 * 5. SINIFIN ALANLARI
 * ------------------------------------------------------------
 *
 * private final String ad;
 * private boolean acik;
 *
 * private final String ad:
 *
 * Kaynagin adini tutar.
 *
 * private kullanildigi icin sinif disindan
 * dogrudan degistirilemez.
 *
 * final kullanildigi icin constructor'da
 * atandiktan sonra yeniden atanamaz.
 *
 * Ornek deger:
 *
 * "rapor.txt"
 *
 * private boolean acik:
 *
 * Kaynagin acik mi kapali mi oldugunu tutar.
 *
 * true  -> Kaynak acik.
 * false -> Kaynak kapali.
 *
 * Burada final kullanmadik.
 *
 * Cunku kaynak acildiginda true,
 * kapatildiginda false olmasini istiyoruz.
 *
 * Yani acik alaninin degisebilmesi gerekiyor.
 *
 *
 * 6. CONSTRUCTOR'IN CALISMASI
 * ------------------------------------------------------------
 *
 * public Kaynak(String ad) {
 *     this.ad = ad;
 *     this.acik = true;
 *
 *     System.out.println("ACILDI: " + ad);
 * }
 *
 * Constructor nesne olusturulurken calisir.
 *
 * Kaynak k = new Kaynak("rapor.txt");
 *
 * yazildiginda:
 *
 * 1) new ile yeni Kaynak nesnesi olusturulur.
 *
 * 2) Constructor'a "rapor.txt" gonderilir.
 *
 * 3) this.ad = ad;
 *
 *    Parametreden gelen ad bilgisi nesnenin
 *    kendi ad alanina atanir.
 *
 * 4) this.acik = true;
 *
 *    Kaynagin acik oldugu belirtilir.
 *
 * 5) Ekrana su mesaj yazdirilir:
 *
 *    ACILDI: rapor.txt
 *
 * ONEMLI:
 *
 * Constructor burada bir kaynak acma
 * islemini taklit ediyor.
 *
 * Gercek bir uygulamada dosya acma gibi
 * islemler icin uygun Java kutuphaneleri
 * kullanilir.
 *
 *
 * 7. ISLEMYAP() METODU
 * ------------------------------------------------------------
 *
 * public void islemYap() {
 *
 *     if (!acik) {
 *         throw new IllegalStateException(
 *             "Kaynak kapali!"
 *         );
 *     }
 *
 *     System.out.println("ISLEM YAPILDI: " + ad);
 * }
 *
 * Bu metot kaynak uzerinde bir islem
 * yapildigini temsil ediyor.
 *
 * Ilk olarak:
 *
 * if (!acik)
 *
 * kontrolu yapiliyor.
 *
 * ! operatoru "degil" anlamina gelir.
 *
 * acik = true ise:
 *
 * !acik -> false
 *
 * Kosul saglanmaz ve islem devam eder.
 *
 * acik = false ise:
 *
 * !acik -> true
 *
 * Kosul saglanir ve hata firlatilir.
 *
 * throw new IllegalStateException(...)
 *
 * Buradaki amac:
 *
 * Kapatilmis bir kaynak uzerinde islem
 * yapilmasini engellemek.
 *
 * Kaynak aciksa:
 *
 * System.out.println(...)
 *
 * calisir.
 *
 * Ornek cikti:
 *
 * ISLEM YAPILDI: rapor.txt
 *
 *
 * ============================================================
 * 8. CLOSE() METODUNUN MANTIGI
 * ============================================================
 *
 * @Override
 * public void close() {
 *
 *     if (acik) {
 *         acik = false;
 *         System.out.println("KAPANDI: " + ad);
 *     }
 * }
 *
 * @Override:
 *
 * AutoCloseable arayuzundeki close()
 * metodunu uyguladigimizi belirtir.
 *
 * close():
 *
 * Kaynagi kapatma islemini temsil eder.
 *
 * try-with-resources blogu tamamlandiginda
 * Java tarafindan otomatik cagrilir.
 *
 * if (acik):
 *
 * Kaynagin hala acik olup olmadigini kontrol
 * eder.
 *
 * Eger kaynak aciksa:
 *
 * acik = false;
 *
 * yapilarak kapali duruma getirilir.
 *
 * Ardindan:
 *
 * KAPANDI: rapor.txt
 *
 * mesaji yazdirilir.
 *
 * PEKI NEDEN if (acik) KULLANDIK?
 *
 * Bir kaynagin yanlislikla iki kez
 * kapatilmak istendigini dusunelim.
 *
 * k.close();
 * k.close();
 *
 * Ilk cagrida acik true oldugundan
 * kaynak kapatilir.
 *
 * Ikinci cagrida acik false oldugu icin
 * if bloguna girilmez.
 *
 * Boylece ikinci kapatma denemesi
 * herhangi bir ek islem yapmaz.
 *
 * Buna IDEMPOTENT DAVRANIS denir.
 *
 * Yani ayni kapatma isleminin tekrar
 * edilmesi yeni bir etki yaratmaz.
 *
 * Bu davranisi biz if (acik) ile sagladik.
 *
 *
 * ============================================================
 * 9. MAIN METODU - NORMAL DURUM
 * ============================================================
 *
 * try (Kaynak k = new Kaynak("rapor.txt")) {
 *     k.islemYap();
 * }
 *
 * try-with-resources yapisinda kaynak
 * try kelimesinden sonraki parantezde
 * tanimlanir.
 *
 * ADIM ADIM:
 *
 * 1) new Kaynak("rapor.txt")
 *
 *    Yeni nesne olusturulur.
 *    Constructor calisir.
 *
 *    acik = true olur.
 *
 *    Cikti:
 *    ACILDI: rapor.txt
 *
 * 2) k.islemYap();
 *
 *    Kaynagin acik oldugu kontrol edilir.
 *
 *    Kaynak acik oldugu icin islem yapilir.
 *
 *    Cikti:
 *    ISLEM YAPILDI: rapor.txt
 *
 * 3) try blogunun sonuna gelinir.
 *
 *    Burada close() metodunu kendimiz
 *    cagirmadik.
 *
 *    Java otomatik olarak:
 *
 *    k.close();
 *
 *    islemini gerceklestirir.
 *
 * 4) close() icindeki if (acik) calisir.
 *
 *    acik = false olur.
 *
 *    Cikti:
 *    KAPANDI: rapor.txt
 *
 * SONUC:
 *
 * Program normal tamamlansa da kaynak
 * otomatik kapatildi.
 *
 *
 * ============================================================
 * 10. MAIN METODU - HATA DURUMU
 * ============================================================
 *
 * try (Kaynak k = new Kaynak("veri.txt")) {
 *
 *     k.islemYap();
 *
 *     throw new RuntimeException(
 *         "Beklenmedik hata!"
 *     );
 *
 * } catch (RuntimeException e) {
 *
 *     System.out.println(
 *         "HATA YAKALANDI: " + e.getMessage()
 *     );
 * }
 *
 * Bu bolumun amaci:
 *
 * ISLEM SIRASINDA HATA OLUSSA BILE
 * KAYNAGIN KAPATILDIGINI GOSTERMEK.
 *
 * ADIM ADIM:
 *
 * 1) Kaynak nesnesi olusturulur.
 *
 *    Constructor calisir.
 *
 *    Cikti:
 *    ACILDI: veri.txt
 *
 * 2) k.islemYap() cagrilir.
 *
 *    Kaynak acik oldugu icin islem yapilir.
 *
 *    Cikti:
 *    ISLEM YAPILDI: veri.txt
 *
 * 3) throw new RuntimeException(...)
 *
 *    satiri calisir.
 *
 *    Burada bilerek bir hata olusturduk.
 *
 *    RuntimeException programin calisma
 *    zamaninda olusabilen bir istisna turudur.
 *
 *    throw ifadesi istisnayi firlatir.
 *
 *    Bu noktadan sonra try govdesindeki
 *    normal komut akisina devam edilmez.
 *
 * 4) try-with-resources devreye girer.
 *
 *    catch bloguna gecmeden ONCE
 *    close() otomatik olarak cagrilir.
 *
 *    Cikti:
 *    KAPANDI: veri.txt
 *
 * 5) catch (RuntimeException e)
 *
 *    firlatilan istisnayi yakalar.
 *
 *    e degiskeni hata nesnesini tutar.
 *
 *    e.getMessage():
 *
 *    Hatanin mesajini dondurur.
 *
 *    Cikti:
 *    HATA YAKALANDI: Beklenmedik hata!
 *
 * SONUC:
 *
 * Hata olusmasina ragmen kaynak kapatildi.
 *
 * Bu, try-with-resources kullanmamizin
 * en onemli nedenidir.
 *
 *
 * 11. NEDEN NORMAL TRY YERINE
 *     TRY-WITH-RESOURCES KULLANIYORUZ?
 * ------------------------------------------------------------
 *
 * Normal kullanim:
 *
 * Kaynak k = new Kaynak("rapor.txt");
 *
 * try {
 *     k.islemYap();
 * } catch (RuntimeException e) {
 *     System.out.println(e.getMessage());
 * }
 *
 * Burada kaynagin otomatik kapatilmasi
 * saglanmaz.
 *
 * close() islemini kendimiz yonetmeliyiz.
 *
 * Ozellikle hata olustugunda kapatma
 * islemini unutmak kaynak sizintisina
 * neden olabilir.
 *
 * try-with-resources ise kapatma
 * sorumlulugunu otomatiklestirir.
 *
 * Derleyici, kaynaklarin uygun sekilde
 * kapatilmasini saglayan kodu ekler.
 *
 *
 * 12. BIRDEN FAZLA KAYNAK
 * ------------------------------------------------------------
 *
 * try (
 *     Kaynak a = new Kaynak("a.txt");
 *     Kaynak b = new Kaynak("b.txt")
 * ) {
 *     a.islemYap();
 *     b.islemYap();
 * }
 *
 * Kaynaklar sirayla acilir:
 *
 * 1) a.txt
 * 2) b.txt
 *
 * Ancak TERS SIRAYLA kapatilir:
 *
 * 1) b.txt
 * 2) a.txt
 *
 * Buna LIFO mantigi gibi bakabiliriz:
 *
 * Son acilan kaynak ilk kapatilir.
 *
 * ONEMLI:
 *
 * close() islemi sirasinda da bir istisna
 * olusabilir.
 *
 * Eger hem try govdesi hem de close()
 * hata firlatirsa, try govdesindeki hata
 * asil istisna olarak korunur.
 *
 * close() kaynakli istisna ise suppressed
 * exception olarak saklanabilir.
 *
 *
 * 13. PROGRAMIN BEKLENEN CIKTISI
 * ------------------------------------------------------------
 *
 * 1 - NORMAL DURUM
 * ACILDI: rapor.txt
 * ISLEM YAPILDI: rapor.txt
 * KAPANDI: rapor.txt
 *
 * 2 - HATA DURUMU
 * ACILDI: veri.txt
 * ISLEM YAPILDI: veri.txt
 * KAPANDI: veri.txt
 * HATA YAKALANDI: Beklenmedik hata!
 *
 *
 * ============================================================
 * 14. EN SIK YAPILAN HATALAR
 * ============================================================
 *
 * HATA 1:
 *
 * Garbage Collector ile close() metodunun
 * ayni isi yaptigini dusunmek.
 *
 * GC bellek yonetir.
 * close() kaynak kapatma islemini yapar.
 *
 * HATA 2:
 *
 * try-with-resources kullanmadan kaynagin
 * kendiliginden kapanacagini dusunmek.
 *
 * HATA 3:
 *
 * AutoCloseable kullanan bir sinifta
 * close() metodunu tanimlamayi unutmak.
 *
 * HATA 4:
 *
 * Kaynak kapandiktan sonra islem yapmaya
 * calismak.
 *
 * Bizim ornekte bu durumda
 * IllegalStateException firlatilir.
 *
 * HATA 5:
 *
 * try-with-resources icinde otomatik kapanan
 * kaynagi gereksiz yere elle kapatmak.
 *
 * HATA 6:
 *
 * System.gc() cagrildiginda bellekteki
 * tum cop nesnelerin hemen silinecegini
 * dusunmek.
 *
 *
 * ============================================================
 * 15. VIZE ICIN TEMEL KAZANIMLAR
 * ============================================================
 *
 * 1) Java'da C++'taki gibi destructor yoktur.
 *
 * 2) Garbage Collector erisilemeyen nesnelerin
 *    bellegini geri kazanabilir.
 *
 * 3) GC'nin calisma zamani kesin degildir.
 *
 * 4) finalize() destructor degildir.
 *
 * 5) Bellek yonetimi ve dis kaynak yonetimi
 *    birbirinden farklidir.
 *
 * 6) AutoCloseable kapatilabilir kaynaklar
 *    icin bir arayuzdur.
 *
 * 7) close() kaynak kapatma islemini yapar.
 *
 * 8) try-with-resources blok sonunda
 *    close() metodunu otomatik cagirir.
 *
 * 9) Hata durumunda da kapatma islemi yapilir.
 *
 * 10) close(), catch blogundan once calisir.
 *
 * 11) Birden fazla kaynak ters sirayla kapanir.
 *
 * 12) close() metodunun tekrar cagrildiginda
 *     guvenli davranacak sekilde yazilmasi
 *     iyi bir tasarim tercihidir.
 *
 *
 * ============================================================
 * GENEL SONUC
 * ============================================================
 *
 * CONSTRUCTOR:
 * Nesnenin baslangic durumunu hazirlar.
 *
 * GARBAGE COLLECTOR:
 * Erisilemeyen nesnelerin bellegini yonetir.
 *
 * AUTOCLOSEABLE:
 * Kapatilabilir kaynak davranisini tanimlar.
 *
 * CLOSE():
 * Kaynak kapatma islemini gerceklestirir.
 *
 * TRY-WITH-RESOURCES:
 * Kaynagin normal ve hata durumlarinda
 * otomatik kapatilmasini saglar.
 *
 * EN ONEMLI KAZANIM:
 *
 * NESNENIN BELLEKTEN TEMIZLENMESI ILE
 * KULLANDIGI DIS KAYNAGIN KAPATILMASI
 * AYNI SEY DEGILDIR.
 *
 * ============================================================
 */
