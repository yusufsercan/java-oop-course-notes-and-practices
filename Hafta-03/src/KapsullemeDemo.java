/*
 * Nesne Yonelimli Programlama - Hafta 03
 * Ornek 6: Kapsulleme bir nesneyi neye karsi korur?
 */
public class KapsullemeDemo {

    public static void main(String[] args) {
        BankaHesabi h = new BankaHesabi("TR12 0006 1005", "Ayse Yilmaz", 3000.0);
        System.out.println("Baslangic : " + h);

        System.out.println();
        System.out.println("--- Gecerli islemle r ---");
        System.out.println("  yatir(500)  -> " + h.yatir(500.0));
        System.out.println("  cek(300)    -> " + h.cek(300.0));
        System.out.println("  Durum: " + h);

        System.out.println();
        System.out.println("--- Kurallari zorlayan islemler ---");
        System.out.println("  yatir(-100) -> " + h.yatir(-100.0));
        System.out.println("  cek(99999)  -> " + h.cek(99999.0));
        System.out.println("  cek(1800)   -> " + h.cek(1800.0));
        System.out.println("  Durum: " + h);

        System.out.println();
        System.out.println("--- Gun degisince ayni istek onaylanir ---");
        h.gunuKapat();
        System.out.println("  cek(1800)   -> " + h.cek(1800.0));
        System.out.println("  Durum: " + h);

        System.out.println();
        System.out.println("--- Kapsulleme neyi ENGELLEDI? ---");
        System.out.println("  h.bakiye = 1000000;  satiri DERLENMEZ (private alan).");
        System.out.println("  setBakiye() metodu YOKTUR; bakiye yalnizca is kurallari");
        System.out.println("  uygulanarak degisir. Kural TEK yerde: BankaHesabi sinifi.");
        System.out.printf("  Son bakiye (yalnizca getter ile okunur): %.2f TL%n",
                          h.getBakiye());
    }
}   

/*
1. KARŞILAŞILAN İLK HATA: println NEDEN HATA VERDİ?

--------------------------------------------------------------------------------

Yazdığın hatalı satır şuydu:



    System.out.println(" RED: yetersiz bakiye (istenilen %.2f...).%n", tutar, bakiye);

               ^^^^^^^ [DERLEME HATASI!]



Neden Derlenmedi?

Java'da metotlar aşırı yüklenirken (overloading) parametre türü ve sayısına göre 

imza (method signature) denetimi yapılır:



- 'println()' metodu yalnızca TEK BİR DEĞER basmak üzere tasarlanmıştır:

    println(String x)

    println(int x)

    println(double x)

    println(Object x)



Sen virgülden sonra 'tutar' ve 'bakiye' değişkenlerini ekleyerek bu metoda 

3 AYRI PARAMETRE göndermeye çalıştın. Java derleyicisi bakar:

"Benim kütüphanemde (String, double, double) alan bir println metodu YOK!" der ve 

kod daha derlenirken altını kırmızıyla çizer.





--------------------------------------------------------------------------------

2. İKİNCİ TUZAK: printf'İN ÇALIŞMA ZAMANI BOMBASI (Runtime Exceptions)

--------------------------------------------------------------------------------

"Madem öyle, her yere printf yazayım rahat edeyim" dediğinde düşeceğin en büyük

mühendislik tuzağı şudur:



Derleyiciler format metninin İÇİNDEKİ yazıyı sıradan bir metin olarak görür;

içindeki format belirteçlerinin sayısını her zaman denetlemez.



Senin kodundaki o gizli hatayı hatırla:



    System.out.printf(" RED: (%.2f + %.2f > %.2f).%n", tutar, bakiye);

                             ^^^^   ^^^^   ^^^^        ─────  ──────

                               3 TANE BELİRTEÇ         2 TANE DEĞİŞKEN



Sonuç Ne Olur?

- Kod tıkır tıkır DERLENİR (hiçbir kırmızı çizgi çıkmaz).

- Program çalışır; o satıra geldiği an JVM ekrana hata fırlatır ve program ÇÖKER:

  java.util.MissingFormatArgumentException: Format specifier '%.2f'



Yazılımda kural şudur:

"Derleme anında (compile-time) yakalanan hata dosttur; 

çalışma anında (runtime) müşterinin veya hocanın önünde patlayan hata felakettir."



'println' seni yazarken uyarır ve korur; 'printf' ise dikkatsiz yazılırsa 

çalışma anında patlayan saatli bir bombaya dönüşür.





--------------------------------------------------------------------------------

3. ARKA PLANDAKİ GİZLİ MALİYET: PERFORMANS VE BELLEK FARKI

--------------------------------------------------------------------------------

Neden büyük sistemlerde her şeye printf yazılmaz?



A) 'println' Nasıl Çalışır?

   - System.out.println("İşlem tamamlandı");

   - Java bu metni doğrudan konsol akışına (output stream) bayt bayt basar ve 

     sonuna satır sonu karakterini ekler.

   - Ekstra bellek açmaz, fazladan işlemci yormaz. Dümdüz, en hızlı yoldur.



B) 'printf' Nasıl Çalışır?

   - System.out.printf("Maaş: %.2f TL%n", maas);

   Arka planda JVM sırasıyla şu adımları işletir:

   1. java.util.Formatter motorunu ayağa kaldırır.

   2. Format metnini baştan sona karakter karakter tarar, '%' işaretlerini arar, 

      regex ayrıştırması yapar.

   3. İlkel veri türlerini ('double', 'int') nesneye dönüştürür (Autoboxing -> Double, Integer).

   4. Tüm parametreleri Heap bellekte 'Object[] args' dizisine sarar.

   5. Biçimlendirmeyi yapar ve ekrana basar.



Eğer 100.000 satırlık bir döngüde basit metinler için 'printf' kullanırsan, 

sistemin belleğini gereksiz yere çöple doldurur ve işlemciyi boşuna format 

taramasıyla yorarsın.





--------------------------------------------------------------------------------

4. 'println' METODUNUN ASIL GÜCÜ: toString() VE NESNE YÖNELİMİ

--------------------------------------------------------------------------------

Hafta 3'te yazdığımız 'Sekil', 'Daire', 'Kare' sınıflarını hatırla.

Her sınıfın içine ne yazdık?



    @Override

    public String toString() {

        return ozet();

    }



Şimdi ekrana bir nesneyi basmak istediğinde:



    Daire d = new Daire(3.0, "kirmizi");



Hangisi daha temiz, daha nesne yönelimli ve zariftir?



- KÖTÜ YOL (printf zorlaması):

  System.out.printf("%s%n", d.toString()); // Gereksiz formatlama kalabalığı!



- TEMİZ VE DOĞRU YOL (println):

  System.out.println(d); // Java otomatik olarak d.toString() çağrısını yapar!



'println', doğrudan bir nesne referansı aldığında arka planda 'String.valueOf(nesne)'

üzerinden nesnenin 'toString()' metodunu tetikler. Nesne yönelimli mimaride 

rapor basmanın en temiz yolu budur.





--------------------------------------------------------------------------------

5. printf İLE String.format() ARASINDAKİ İLİŞKİ

--------------------------------------------------------------------------------

Slaytta 'Sekil.java' içindeki 'ozet()' metoduna dikkat ettin mi?

Hoca orada 'printf' yazmamış, şunu yazmıştır:



    public String ozet() {

        return String.format("%-11s renk=%-9s alan=%9.3f cevre=%9.3f", 

                             ad, renk, alan(), cevre());

    }



Neden?

- 'printf()'  : Biçimlendirir ve EKRANA (konsola) basar. Geriye bir şey döndürmez (void).

- 'String.format()' : Biçimlendirir ama ekrana basmaz; sonucu bir 'String' METİN 

                      olarak teslim eder. Böylece o metni ister veritabanına 

                      kaydedersin, ister dosyaya yazarsın, ister ekrana basarsın.



İkisinin format kalıpları ('%-11s', '%.2f', '%n') BİREBİR AYNIDIR; tek fark 

birinin ekrana dökmesi, diğerinin metin üretmesidir.





--------------------------------------------------------------------------------

6. EN SIK KULLANILAN FORMAT BELİRTEÇLERİ (HIZLI KOPYA KAĞIDI)

--------------------------------------------------------------------------------

Belirteç    Açıklama                     Örnek Kullanım          Çıktı

---------   ---------------------------  ----------------------  -----------------

%d          Tam sayılar (byte, int, long)printf("%d", 42)        42

%f          Ondalıklı sayılar            printf("%.2f", 3.14159) 3.14 (2 basamak)

%s          Metin (String / Object)      printf("%s", "Ali")     Ali

%n          Satır sonu (Platform bağımsız)printf("Bitti%n")      Bitti + yeni satır

%-15s       Sola dayalı, 15 karakter alanprintf("%-10s:", "Ad")  Ad        :

%8.3f       Toplam 8 hane, 3 basamak vir.printf("%8.3f", 12.5)     12.500



NOT: printf içinde ASLA işletim sistemine bağımlı '\n' kullanma! 

Windows ve Linux arasında satır sonu farkı yaşamamak için daima '%n' kullan.





--------------------------------------------------------------------------------

7. USTA MÜHENDİSİN KARAR MATRİSİ: HANGİSİNİ NE ZAMAN KULLANMALIYIM?

--------------------------------------------------------------------------------

Kullanım Senaryosu                        Doğru Araç        Gerekçe

----------------------------------------  ----------------  -----------------------------------------

Basit uyarı ve durum mesajları            println           Sade, hızlı, satır sonunu kendi yönetir.

Bir nesnenin durumunu yazdırma            println(nesne)    Nesnenin toString() metodunu otomatik çağırır.

Para, kuruş, ondalık hassasiyeti (%.2f)   printf            Virgülden sonraki basamakları sabitler.

Hizalı tablo ve rapor çıktıları           printf            Sütun genişliklerini (%-15s) net ayarlar.

Metodu 'String' döndüren bir raporlama    String.format     Ekrana basmak yerine metni üretip teslim eder.

Döngü içinde milyonlarca kez log yazma    println           printf'in tarama/parsing maliyetinden kaçınır.

================================================================================
*/