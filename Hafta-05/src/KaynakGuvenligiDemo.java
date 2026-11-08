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













/*
 * ============================================================
 * HAFTA 05 - GENEL TEORI OZETI
 *
 * KONULAR:
 * 4.7 - Hangi Nesne Olusturma Yontemini Secmeliyiz?
 * 5   - C ve Java Arasindaki Farklar
 * 6   - Sik Yapilan Hatalar
 * 7   - Iyi Tasarim Ilkeleri
 * 8-9 - Gelecek Haftaya Kopru ve Genel Kazanimlar
 * ============================================================
 *
 *
 * 1. NEDEN FARKLI NESNE OLUSTURMA YONTEMLERI VAR?
 * ------------------------------------------------------------
 *
 * Java'da her yeni ogrendigimiz yapiyi
 * butun siniflarda kullanmak zorunda degiliz.
 *
 * Constructor, constructor overloading,
 * kopya constructor ve static factory
 * birbirinden farkli ihtiyaclara cozum getirir.
 *
 * Amacimiz hepsini her sinifta kullanmak degil,
 * uygun problemi uygun yontemle cozmektir.
 *
 *
 * 2. NORMAL CONSTRUCTOR
 * ------------------------------------------------------------
 *
 * Ornek:
 *
 * Ogrenci a = new Ogrenci("Ali", 20);
 *
 * Ne zaman kullanilir?
 *
 * Nesneyi olusturmak icin gerekli bilgiler
 * belliyse normal constructor yeterlidir.
 *
 * new ifadesi yeni nesne olusturur.
 *
 * Constructor ise nesnenin baslangic
 * durumunu hazirlar.
 *
 * Constructor'in donus turu bulunmaz.
 *
 * void bile yazilmaz.
 *
 * Constructor'da nesnenin gecerli bir
 * baslangic durumuna sahip olmasi saglanir.
 *
 *
 * 3. CONSTRUCTOR OVERLOADING
 * ------------------------------------------------------------
 *
 * Ayni sinifta farkli parametre listelerine
 * sahip constructor'lar tanimlamaktir.
 *
 * Ornek:
 *
 * Ogrenci("Ali");
 * Ogrenci("Ali", 20);
 *
 * Ne zaman kullanilir?
 *
 * Ayni nesneyi farkli miktarda bilgiyle
 * olusturmak istedigimizde kullanilir.
 *
 * Java, parametrelerin sayisina, turune
 * ve sirasina gore constructor'i secer.
 *
 * Yalnizca parametre isimlerinin farkli
 * olmasi overloading icin yeterli degildir.
 *
 * this(...) kullanarak constructor'lari
 * ortak bir ana constructor'a baglayabiliriz.
 *
 * Boylece ayni dogrulama kurallarini
 * tekrar tekrar yazmak zorunda kalmayiz.
 *
 *
 * 4. THIS(...) ZINCIRI
 * ------------------------------------------------------------
 *
 * Ornek:
 *
 * public Ogrenci(String ad) {
 *     this(ad, 18);
 * }
 *
 * public Ogrenci(String ad, int yas) {
 *     // Ana constructor
 * }
 *
 * this(...) ayni siniftaki baska bir
 * constructor'i cagirir.
 *
 * Dersimizdeki Java 21'e gore this(...)
 * constructor'in ilk satirinda bulunmalidir.
 *
 * Cagrilan constructor tamamlandiktan
 * sonra cagiran constructor'in kalan
 * satirlari calisir.
 *
 * Constructor zinciri sonsuz donguye
 * girmemelidir.
 *
 * Ana constructor'da:
 *
 * - Parametreler kontrol edilir.
 * - Gecersiz bilgiler reddedilir.
 * - Nesnenin alanlari atanir.
 *
 * ONEMLI:
 *
 * Bir this(...) zinciri, tek new ifadesi
 * icin birden fazla nesne olusturmaz.
 *
 *
 * 5. KOPYA CONSTRUCTOR
 * ------------------------------------------------------------
 *
 * Ornek:
 *
 * Ogrenci a = new Ogrenci("Ali", 20);
 *
 * Ogrenci b = new Ogrenci(a);
 *
 * Kopya constructor, ayni siniftan bir
 * nesneyi parametre olarak alir.
 *
 * Amaci var olan nesnenin bilgileriyle
 * YENI bir nesne olusturmaktir.
 *
 * Peki su satirla farki nedir?
 *
 * Ogrenci b = a;
 *
 * Bu satir yeni nesne olusturmaz.
 *
 * b ve a ayni nesneyi gosterir.
 *
 * Ogrenci b = new Ogrenci(a);
 *
 * Bu satir ise yeni nesne olusturur.
 *
 * Dolayisiyla:
 *
 * a == b
 *
 * ifadesinin sonucu, iki referansin
 * ayni nesneyi gosterip gostermedigine
 * baglidir.
 *
 *
 * 6. SIG VE DERIN KOPYA
 * ------------------------------------------------------------
 *
 * Bir nesnenin icinde int[] gibi
 * degistirilebilir alanlar bulunabilir.
 *
 * Ornek:
 *
 * this.notlar = kaynak.notlar;
 *
 * Bu satirda yeni dizi olusturulmaz.
 *
 * Iki nesne ayni diziyi paylasir.
 *
 * Buna sig kopyalama davranisi denir.
 *
 * Buna karsilik:
 *
 * this.notlar = kaynak.notlar.clone();
 *
 * yeni bir int dizisi olusturur.
 *
 * Boylece kaynak nesnenin dizisi ile
 * kopyanin dizisi birbirinden bagimsiz olur.
 *
 * Bu, int[] gibi ilkel tur dizileri
 * icin bagimsiz kopyalama saglar.
 *
 * DIKKAT:
 *
 * Nesne dizilerinde clone(), icindeki
 * nesneleri otomatik derin kopyalamaz.
 *
 * Gerekirse elemanlar da kopyalanmalidir.
 *
 * KAZANIM:
 *
 * Kopya constructor yazmak tek basina
 * yeterli degildir.
 *
 * Degistirilebilir alanlarin paylasilip
 * paylasilmadigi da kontrol edilmelidir.
 *
 *
 * 7. STATIC FACTORY METHOD
 * ------------------------------------------------------------
 *
 * Ornek:
 *
 * KursKaydi a =
 *     KursKaydi.normal("123", "JAVA101");
 *
 * KursKaydi b =
 *     KursKaydi.dinleyici("456", "JAVA101");
 *
 * Normal constructor yerine nesne olusturma
 * islemini isimlendirilmis static metotlarla
 * gerceklestirebiliriz.
 *
 * Ne zaman tercih edilir?
 *
 * - Farkli olusturma yollari varsa.
 * - Bu yollara anlamli isim vermek istiyorsak.
 * - Nesne uretimini kontrol etmek istiyorsak.
 * - Bazen mevcut nesneyi dondurmek istiyorsak.
 * - Ayni parametre turleriyle farkli
 *   anlamda kuruluslar yapmak istiyorsak.
 *
 * Ornek:
 *
 * return new KursKaydi(...);
 *
 * Yeni nesne olusturur.
 *
 * return BOS;
 *
 * Onceden olusturulmus ortak nesneyi dondurur.
 *
 * Static fabrika her cagrida yeni nesne
 * olusturmak zorunda degildir.
 *
 * Constructor private yapilirsa nesne
 * olusturma yollari sinif tarafindan
 * kontrol edilebilir.
 *
 * ONEMLI:
 *
 * Static fabrika constructor'in her zaman
 * yerine gecmesi gereken bir yapi degildir.
 *
 * Gerektiginde tercih edilen alternatif
 * bir nesne olusturma yontemidir.
 *
 *
 * 8. HANGI YONTEMI SECELIM?
 * ------------------------------------------------------------
 *
 * Basit nesne olusturma:
 * -> Normal constructor
 *
 * Farkli parametrelerle nesne olusturma:
 * -> Constructor overloading
 *
 * Bagimsiz nesne kopyalama:
 * -> Kopya constructor
 *
 * Isimlendirilmis veya kontrollu uretim:
 * -> Static factory method
 *
 * Bir sinif bu yontemlerden birkacini
 * ayni anda kullanabilir.
 *
 * Genel hedef:
 *
 * Dogrulama ve nesne kurma kurallarinin
 * tutarli olmasini saglamak.
 *
 *
 * ============================================================
 * 9. C VE JAVA ARASINDAKI FARKLAR
 * ============================================================
 *
 * C dilinde struct tanimlanabilir.
 *
 * Bir struct degiskeni, alanlari
 * acikca baslatilmadan da tanimlanabilir.
 *
 * C'de baslatma icin ayrica init()
 * benzeri fonksiyonlar yazilabilir.
 *
 * Ancak bu fonksiyonun cagrilmasi
 * dil tarafindan zorunlu kilinmaz.
 *
 * Java'da new ile nesne olusturulurken
 * constructor cagrilir.
 *
 * Boylece Java nesnesinin olusturulma
 * sureci dilin kurallarina baglidir.
 *
 *
 * 10. C VE JAVA'DA KOPYALAMA FARKI
 * ------------------------------------------------------------
 *
 * C dilinde:
 *
 * Ogrenci b = a;
 *
 * a ve b birer struct degiskeniyse
 * yapinin alan degerleri kopyalanir.
 *
 * Java'da:
 *
 * Ogrenci b = a;
 *
 * yalnizca nesne referansi kopyalanir.
 *
 * Yani:
 *
 * C struct atamasi -> Alanlar kopyalanir.
 *
 * Java nesne atamasi -> Referans kopyalanir.
 *
 * Bu fark, Java'da en cok karistirilan
 * noktalardan biridir.
 *
 *
 * 11. BELLEK VE KAYNAK YONETIMI
 * ------------------------------------------------------------
 *
 * C/C++:
 *
 * Bellek yonetiminde programcinin
 * dogrudan sorumluluklari olabilir.
 *
 * Java:
 *
 * Artik erisilemeyen nesnelerin bellegini
 * Garbage Collector geri kazanabilir.
 *
 * Java'da C++'taki gibi destructor yoktur.
 *
 * Ancak dis kaynaklarin kapatilmasi
 * hala gereklidir.
 *
 * Ornek:
 *
 * Dosya, ag baglantisi, veritabani baglantisi.
 *
 * Bunlar icin AutoCloseable ve
 * try-with-resources kullanabiliriz.
 *
 * Garbage Collector ile close() ayni
 * isi yapmaz.
 *
 *
 * ============================================================
 * 12. SIK YAPILAN HATALAR
 * ============================================================
 *
 * HATA 1:
 *
 * Constructor'a void yazmak.
 *
 * public void Ogrenci(...)
 *
 * Bu bir constructor degil, normal metottur.
 *
 * DOGRU:
 *
 * public Ogrenci(...)
 *
 *
 * HATA 2:
 *
 * Parametreli constructor yazdikten sonra
 * parametresiz constructor'in otomatik
 * olarak devam ettigini dusunmek.
 *
 * Sinifta herhangi bir constructor
 * tanimlanirsa Java'nin otomatik ekledigi
 * parametresiz constructor artik verilmez.
 *
 * Gerekirse kendimiz yazmaliyiz.
 *
 *
 * HATA 3:
 *
 * this(...) cagrisi oncesinde baska
 * islemler yazmak.
 *
 * Dersimizdeki Java 21'de this(...)
 * constructor'in ilk satirinda olmalidir.
 *
 *
 * HATA 4:
 *
 * Kopya constructor'da diziyi dogrudan
 * paylasmak.
 *
 * this.notlar = kaynak.notlar;
 *
 * Bu satir bagimsiz dizi olusturmaz.
 *
 * Gerektiginde clone() veya uygun
 * kopyalama yontemleri kullanilmalidir.
 *
 *
 * HATA 5:
 *
 * Constructor'in icinde cok fazla
 * sorumluluk toplamak.
 *
 * Constructor'in temel gorevi nesneyi
 * gecerli bir baslangic durumuna getirmektir.
 *
 * Uzun hesaplamalar veya agir islemler
 * gerektiginde ayri metotlara tasinabilir.
 *
 *
 * HATA 6:
 *
 * Dosya veya baglanti gibi kaynaklari
 * zamaninda kapatmayi unutmak.
 *
 * Normal try blogu kaynagi otomatik
 * kapatmaz.
 *
 * try-with-resources ise bloktan cikilirken
 * close() metodunun cagrilmasini saglar.
 *
 *
 * 13. DERLEYICI HATALARINI TANIMA
 * ------------------------------------------------------------
 *
 * The constructor X() is undefined
 *
 * -> Uygun constructor bulunmuyor.
 *
 * Recursive constructor invocation
 *
 * -> Constructor zinciri donguye girmis.
 *
 * Duplicate method
 *
 * -> Ayni imzaya sahip tanim tekrar edilmis.
 *
 * The constructor X(...) is not visible
 *
 * -> Constructor'a erisim izni yok.
 *    Ornegin constructor private olabilir.
 *
 * The blank final field ... may not
 * have been initialized
 *
 * -> final alan her olusturma yolunda
 *    atanmamis.
 *
 * The final field ... cannot be assigned
 *
 * -> final alana yeniden atama yapiliyor.
 *
 * The resource type X does not
 * implement AutoCloseable
 *
 * -> try-with-resources icindeki tur
 *    AutoCloseable uygulamiyor.
 *
 *
 * ============================================================
 * 14. IYI TASARIM ILKELERI
 * ============================================================
 *
 * 1) Birden fazla constructor varsa
 *    ortak bir ana constructor kullanmayi dusun.
 *
 * 2) Ayni dogrulama kodunu farkli
 *    constructor'larda tekrar yazma.
 *
 * 3) Constructor'lari kisa ve anlasilir tut.
 *
 * 4) Gecersiz degerlerle nesne olusturulmasini
 *    constructor asamasinda engelle.
 *
 * 5) Disaridan gelen degistirilebilir
 *    nesneleri gerektiğinde kopyala.
 *
 * 6) Kopya constructor'da degistirilebilir
 *    alanlarin gercekten kopyalandigina
 *    dikkat et.
 *
 * 7) Farkli nesne olusturma yollari
 *    gerekiyorsa static fabrikayi degerlendir.
 *
 * 8) Metotlara yaptiklari isi anlatan
 *    anlamli isimler ver.
 *
 * 9) Kaynak kapatma islemlerini unutma.
 *
 * 10) finalize() metodunu kullanma.
 *
 * 11) Birbirine bagimli alanlarin baslangic
 *     hesaplamalarini constructor'da yap.
 *
 *
 * 15. ADLANDIRMA KURALLARI
 * ------------------------------------------------------------
 *
 * Sinif adi:
 * OgrenciKaydi
 *
 * Metot adi:
 * kayitOlustur()
 *
 * Sabit adi:
 * MAX_PUAN
 *
 * Constructor:
 * Sinif adiyla birebir ayni olmali.
 *
 * Kopya constructor:
 * Ayni siniftan nesne parametresi almali.
 *
 * Static fabrika icin yaygin isimler:
 *
 * of()
 * from()
 * valueOf()
 * getInstance()
 * newInstance()
 * copyOf()
 *
 * Bu isimlendirme kurallari kodun
 * okunabilirligini artirir.
 *
 *
 * ============================================================
 * 16. HAFTA 05 GENEL KAZANIMLARI
 * ============================================================
 *
 * Bu haftada bir Java nesnesinin
 * dogusunu ve yasam dongusunu ogrendik.
 *
 * CONSTRUCTOR:
 * Nesneyi gecerli baslangic durumuna getirir.
 *
 * OVERLOADING:
 * Farkli parametrelerle nesne kurmayi saglar.
 *
 * THIS(...):
 * Constructor'lar arasinda zincir kurar.
 *
 * KOPYA CONSTRUCTOR:
 * Mevcut nesnenin bilgileriyle yeni
 * bir nesne olusturur.
 *
 * CLONE():
 * int[] gibi dizilerin bagimsiz
 * kopyasini olusturabilir.
 *
 * STATIC FACTORY:
 * Nesne olusturma yollarini isimlendirir
 * ve uretim islemini kontrol eder.
 *
 * BASLATMA SIRASI:
 * Sinif ve nesne olusturulurken
 * hangi islemin once yapildigini aciklar.
 *
 * GARBAGE COLLECTOR:
 * Erisilemeyen nesnelerin bellegini
 * geri kazanabilir.
 *
 * AUTOCLOSEABLE:
 * Kapatilabilir kaynak davranisi tanimlar.
 *
 * TRY-WITH-RESOURCES:
 * Kaynaklarin zamaninda ve hata halinde
 * otomatik kapatilmasini saglar.
 *
 *
 * ============================================================
 * 17. VIZE ICIN SONUC
 * ============================================================
 *
 * Bir Java sinifi yazarken onemli olan
 * butun ozellikleri kullanmak degildir.
 *
 * Dogru problemi, dogru yontemle cozmektir.
 *
 * Basit bir nesne icin constructor yeterliyse
 * static fabrika yazmak zorunda degiliz.
 *
 * Kopyalama gerekmiyorsa kopya constructor
 * yazmak zorunda degiliz.
 *
 * Ozel baslatma gerekmiyorsa static veya
 * ornek baslatma blogu yazmak zorunda degiliz.
 *
 * Ihtiyaca uygun, anlasilir, dogrulama
 * kurallari tutarli siniflar yazmaliyiz.
 *
 * HAFTA 05'IN ANA FIKRI:
 *
 * BIR NESNENIN NASIL OLUSTURULDUGUNU,
 * NASIL BASLATILDIGINI, NASIL KOPYALANDIGINI
 * VE KAYNAKLARININ NASIL YONETILDIGINI
 * ANLAMAK.
 *
 * ============================================================
 */
