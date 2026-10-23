/*
 * Nesne Yonelimli Programlama - Hafta 03
 * Ornek 4: DORT ILKE tek ekranda
 *
 * Bu program SekilKotu.java'daki uc sorunun da cozuldugunu gosterir:
 *   1) switch yok            -> COK BICIMLILIK
 *   2) olu alan yok          -> her alt sinif yalnizca kendi verisini tutar
 *   3) yeni tur eklemek icin var olan kod degismiyor -> SOYUTLAMA + MIRAS
 */
public class SekilDemo {

    // Bu metot Daire, Kare, Ucgen adlarini HIC BILMEZ. Yalnizca Sekil'i bilir.
    private static void rapor(Sekil[] sekiller) {
        double toplamAlan = 0.0;
        for (Sekil s : sekiller) {
            System.out.println("  " + s.ozet());   // dinamik baglama
            toplamAlan += s.alan();
        }
        System.out.printf("  %-11s%21s%9.3f%n", "TOPLAM", "", toplamAlan);
    }

    public static void main(String[] args) {
        // Ust tur referansi, alt tur nesnesi: cok bicimliligin can damari.
        
        Sekil[] sekiller = {
            new Daire(3.0, "kirmizi"),
            new Kare(4.0, "mavi"),
            new Ucgen(6.0, 4.0, "yesil")
        };

        System.out.println("--- 1) COK BICIMLILIK: tek dongu, uc farkli davranis ---");
        rapor(sekiller);

        System.out.println();
        System.out.println("--- 2) KAPSULLEME: renk yalnizca setter'dan degisir ---");
        sekiller[0].setRenk("mor");
        sekiller[1].setRenk("   ");            // reddedilir
        System.out.println("  daire.renk = " + sekiller[0].getRenk());
        System.out.println("  kare.renk  = " + sekiller[1].getRenk());

        System.out.println();
        System.out.println("--- 3) SOYUTLAMA: cagiran taraf formulu bilmiyor ---");
        System.out.printf("  En buyuk alanli sekil: %s%n", enBuyuk(sekiller).getAd());

        System.out.println();
        System.out.println("--- 4) MIRAS: ozet() hicbir alt sinifta yazilmadi ---");
        System.out.println("  Ucgen bir Sekil mi? " + (sekiller[2] instanceof Sekil));
        System.out.println("  Sekil bir Ucgen mi? " + (sekiller[0] instanceof Ucgen));

        System.out.println();
        System.out.println("--- 5) Gecersiz olcu: nesne yine de gecerli dogar ---");
        Sekil bozuk = new Kare(-5.0, "sari");
        System.out.println("  " + bozuk.ozet());
    }

    private static Sekil enBuyuk(Sekil[] sekiller) {
        Sekil enIyi = sekiller[0];
        for (Sekil s : sekiller) {
            if (s.alan() > enIyi.alan()) {
                enIyi = s;
            }
        }
        return enIyi;
    }
}

/*
--------------------------------------------------------------------------------

0. GİRİŞ: BU HAFTA KAMERA NEDEN GERİYE ÇEKİLDİ?

--------------------------------------------------------------------------------

İlk iki haftada tek bir sınıfın anatomisini öğrendik: Alanlar, metotlar, yapıcı 

(constructor), 'this', 'private' ve 'getter/setter'. Elimizde tek başına düzgün 

çalışan bir sınıf vardı.



Fakat gerçek dünyada hiçbir yazılım tek bir sınıftan ibaret değildir:

- Bir okul sisteminde Öğrenci de vardır, Personel de vardır.

- Bir e-ticaret kasasında Kredi Kartı da vardır, Havale de vardır, Kapora da vardır.

- Bir çizim programında Daire de vardır, Kare de vardır, Üçgen de vardır.



"Birbirine benzeyen ama aynı olmayan varlıklar aynı sisteme girdiğinde kodu 

nasıl organize edeceğiz?" Bu hafta işte bu devasa sorunun cevabıdır.





--------------------------------------------------------------------------------

1. PROBLEM TANIMI: KÖTÜ TASARIM TUZAĞI (SekilKotu.java)

--------------------------------------------------------------------------------

Diyelim ki bir çizim programı yapıyoruz. Şekillerimiz var (Daire, Kare, Dikdörtgen).

Acemi bir yazılımcı gibi düşünüp tek bir sınıf açtık:



    public class SekilKotu {

        private String tur; // Tip Kodu (Type Code): "daire", "kare", "dikdortgen"

        private double a;   // Daire için yarıçap, kare için kenar, dikdörtgen için genişlik

        private double b;   // Yalnızca dikdörtgende yükseklik; diğerlerinde BOŞ!

        public double alan() {

            switch (tur) {

                case "daire": return Math.PI * a * a;

                case "kare": return a * a;

                case "dikdortgen": return a * b;

                default: return 0.0; // SESSİZ YANLIŞ SONUÇ!

            }

        }

        public double cevre() {

            switch (tur) { ... } // Aynı switch bir daha!

        }

        public String tanit() {

            switch (tur) { ... } // Aynı switch üçüncü kez!

        }

    }

BU KODUN ÜÇ BÜYÜK FELAKETİ (VİZE KLASİĞİ):

------------------------------------------------------------------------------

1) SESSİZ YANLIŞ SONUÇ (Silent Failure):

   Programa yeni bir şekil ("ucgen") verdiğinde hiçbir switch onu tanımaz. 

   Program çökmez, kırmızı hata vermez. Sessizce alan=0.0 ve cevre=0.0 üretir!

   Müşteriye veya ekrana yanlış matematik basılır. Yazılımda en tehlikeli hata,

   çökmeyen ama yanlış çalışan sessiz hatalardır.



2) ÖLÜ ALANLAR (Dead Fields):

   Bir kare nesnesi üretirken: new SekilKotu("kare", 4.0, 999.0); yazabilirsin.

   Kare tek kenarlıdır, 'b' kenarı olamaz! Ama nesne bellekte gereksiz yere 8 bayt 

   yer kaplar ve derleyici bu saçmalığı denetleyemez.



3) KIRILGAN TASARIM (Fragile Design / Switch Statements Kod Kokusu):

   Yarın sisteme "Ucgen" veya "Besgen" eklemek istediğinde:

   alan(), cevre(), tanit() metotlarındaki 3 AYRI SWITCH bloğuna tek tek gidip

   kod eklemek zorundasın. Bir tanesini bile unutursan derleyici seni UYARMAZ!

   Sistem sessizce patlar.



ALTIN KURAL (Code Smell):

Kodunuzda aynı switch veya if-else zinciri birden fazla metotta tekrar ediyorsa,

orada gizlenmiş bir sınıf hiyerarşisi vardır. Çözüm ÇOK BİÇİMLİLİKTİR.


================================================================================

2. DÖRT TEMEL İLKENİN MİMARİSİ VE ÇÖZÜMLERİ

================================================================================


                     ┌──────────────────────────┐

                     │   KAPSÜLLEME (Data)      │

                     │ Veri + İş Kuralı Tek Yerde│

                     └─────────────┬────────────┘

                                   │

                                   ▼

┌──────────────────────────┐  ortak veriyi  ┌──────────────────────────┐

│   SOYUTLAMA (Contract)   │ ─────────────> │     MİRAS (Structure)    │

│ Ne yapılacak? (abstract) │     taşır      │ Ortak olanı 1 kez yaz    │

└─────────────┬────────────┘                └─────────────┬────────────┘

              │                                           │

              │             sözleşmeyi                    │

              └───────────> uygular ve <──────────────────┘

                            besler

                                   │

                                   ▼

                     ┌──────────────────────────┐

                     │ ÇOK BİÇİMLİLİK (Behavior)│

                     │  Tek Çağrı, Farklı Gövde │

                     └──────────────────────────┘

--------------------------------------------------------------------------------

İLKE 1: KAPSÜLLEME (Encapsulation) - "Veriye Giden Tek Kapı"

--------------------------------------------------------------------------------

VİZE TUZAĞI: "Kapsülleme = Değişkenleri private yapıp getter/setter açmaktır."

CEVAP     : KESİNLİKLE YANLIŞ VE EKSİKTİR!



Eğer 'bakiye' alanını private yapıp altına doğrudan 'setBakiye(double b)' açarsan,

alanı 'public double bakiye' yapmaktan hiçbir farkı kalmaz. Dışarıdan biri yine

bakiye = -99999 yapabilir.



GERÇEK KAPSÜLLEME:

Veriyi ve o veriyi değiştiren İŞ KURALINI (Business Logic) aynı sınıfa kilitlemektir.



Ders Örneği (BankaHesabi.java):

1. 'bakiye' alanı private'tır.

2. 'setBakiye()' metodu BİLEREK YAZILMAMIŞTIR!

3. Bakiye sadece 'yatir(tutar)' ve 'cek(tutar)' iş metotlarıyla değişebilir.

4. cek() metodu kapıda bekler:

   - Tutar pozitif mi?

   - Bakiye yeterli mi?

   - Günlük çekim limiti (2000 TL) aşıldı mı?

   Kuralları geçemeyen işlem reddedilir, bakiye asla bozulmaz.



Kapsüllemenin 4 Düzeyi:

- Düzey 0: public double bakiye; (Koruma sıfır)

- Düzey 1: private + körü körüne get/set (Sözde koruma)

- Düzey 2: private + doğrulamalı setter (if (b > 0) this.bakiye = b;)

- Düzey 3: private + setter YOK + gerçek dünya iş metotları (yatir, cek) -> EN İYİSİ!


--------------------------------------------------------------------------------

İLKE 2: SOYUTLAMA (Abstraction) - "Ne" ile "Nasıl"ın Ayrılması

--------------------------------------------------------------------------------

Soyutlama, dış dünyaya "NE" yapılabileceğini gösterip, "NASIL" yapıldığını gizlemektir.

Bir arabanın direksiyonu ve fren pedalı arayüzdür (ne yapılacağını söyler). Motorun

nasıl yaktığı içeride gizlidir.



Java'da Soyutlamanın 2 Aracı Vardır:



A) SOYUT SINIF (abstract class Sekil):

   - 'abstract class Sekil': Bu sınıftan new ile doğrudan NESNE ÜRETİLEMEZ!

     (Çünkü dünyada tek başına 'şekil' diye bir cisim yoktur; daire, kare vardır.)

   - 'public abstract double alan();': Süslü parantezi { } YOKTUR! Noktalı virgülle biter.

     Bu bir ŞARTNAMEDİR / SÖZLEŞMEDİR:

     "Benden türeyecek her sınıf (Daire, Kare), kendi alan formülünü yazmak ZORUNDADIR!"

     Eğer alt sınıf bu metodu yazmazsa KOD DERLENMEZ.



B) ARAYÜZ (interface Odeme):

   - İçinde sadece yapılacak işlerin listesi (imzalar) vardır: boolean ode(double tutar);

   - Kasa sınıfı tahsilat yaparken şunu der:

     public static void tahsilEt(Odeme yontem, double tutar) {

         yontem.ode(tutar); // Kasa arkada KrediKarti mı var, Havale mi var BİLMEZ!

     }

   - Mühendislik Kazancı: Yarın "KriptoPara" ile ödeme gelse, Kasa sınıfına TEK BİR 

     SATIR BİLE DOKUNMAZSIN! Yeni bir sınıf açıp 'implements Odeme' dersin, biter.


--------------------------------------------------------------------------------

İLKE 3: MİRAS (Inheritance) - "Ortak Olanı Bir Kez Yazmak"

--------------------------------------------------------------------------------

Ortak alanları ve davranışları üst sınıfta toplarız; alt sınıflar 'extends' ile devralır.



Ders Örneği:

- Sekil.java (Üst Sınıf):

  'ad' ve 'renk' alanları burada durur.

  'getAd()', 'getRenk()', 'setRenk()', 'ozet()' ve 'toString()' burada yazılır.

- Daire, Kare, Ucgen (Alt Sınıflar):

  Bu metotları TEKRAR YAZMAZ! Doğrudan miras alır. Ucgen.java sadece 30 satırdır 

  ama ozet() metodunu çatır çatır kullanır.



super(...) ÇAĞRISI (ÇOK ÖNEMLİ):

Bir çocuk doğmadan önce babası doğmalıdır!

Alt sınıfın yapıcısının İLK SATIRINDA mutlaka üst sınıfın yapıcısı çağrılmalıdır:



    public Daire(double yaricap, String renk) {

        super("Daire", renk); // 1. SATIRDA OLMAK ZORUNDA!

        this.yaricap = yaricap;

    }



Miras Alınanlar vs Alınmayanlar Tablosu:

- public / protected metotlar : Aynen devralınır, doğrudan kullanılır.

- private alanlar             : Devralınır ama görünmez; üstün get/set'i ile erişilir.

- Yapıcılar (Constructors)    : MİRAS ALINMAZ! Yalnızca super(...) ile çağrılır.

- static metotlar             : Sınıfa aittir, nesneye değil; ezilemez (override edilemez).

--------------------------------------------------------------------------------

İLKE 4: ÇOK BİÇİMLİLİK (Polymorphism) - "Aynı Çağrı, Farklı Davranış"

--------------------------------------------------------------------------------

Dört ilkenin en büyük meyvesidir. Switch bloklarını tarihe gömen şey budur!



Sahne:

    Sekil[] sekiller = {

        new Daire(3.0, "kirmizi"),

        new Kare(4.0, "mavi"),

        new Ucgen(6.0, 4.0, "yesil")

    };



    for (Sekil s : sekiller) {

        System.out.println(s.alan()); // SİHİRLİ SATIR!

    }


Ne Oldu?

Kodda tek bir 's.alan()' çağrısı var; switch yok, if-else yok.

- Döngü 1. turda Daire'nin pi*r^2 formülünü çalıştırır.

- Döngü 2. turda Kare'nin a*a formülünü çalıştırır.

- Döngü 3. turda Üçgen'in taban*h/2 formülünü çalıştırır.



Bunu sağlayan arkadaki mekanizma: DİNAMİK BAĞLAMA (Dynamic Binding / Dispatch).

JVM çalışma anında o referansın gösterdiği gerçek nesnenin tablosuna bakar ve 

doğru metot gövdesini çalıştırır.


================================================================================

3. VİZENİN EN KRİTİK AYRIMLARI (HOCANIN KESİN SORACAĞI YERLER)

================================================================================

AYRIM 1: BİR REFERANSIN İKİ TÜRÜ (STATİK TÜR vs DİNAMİK TÜR)

------------------------------------------------------------------------------

Şu satırı aklına kazı:



           Sekil s = new Daire(3.0, "kirmizi");

           ───────   ─────────────────────────

              │                  │

         BİLDİRİM TÜRÜ       GERÇEK TÜR

         (Statik Tür)       (Dinamik Tür)



1) BİLDİRİM TÜRÜ (Sol Taraf - Sekil):

   - Derleyiciye bakar.

   - "Hangi metotlar ÇAĞRILABİLİR?" sorusuna sol taraf karar verir!

   - s.alan()      -> DERLENİR (Çünkü Sekil sınıfında alan() bildirilmiş).

   - s.getYaricap()-> DERLENMEZ! (Çünkü Sekil sınıfı yarıçap diye bir şey bilmez!)



2) GERÇEK TÜR (Sağ Taraf - Daire):

   - JVM'e (Çalışma zamanına) bakar.

   - "Hangi GÖVDE ÇALIŞACAK?" sorusuna sağ taraf karar verir!

   - s.alan() çağrıldığında Daire'nin içindeki formül çalışır.



Peki 's' üzerinden Daire'ye özgü getYaricap()'a nasıl ulaşırız?

Aşağı Dönüşüm (Downcasting) ile:

    if (s instanceof Daire) {       // Önce güvenlik kontrolü!

        Daire d = (Daire) s;        // Dönüşüm yapıldı

        System.out.println(d.getYaricap()); // Artık çağrılabilir!

    }

AYRIM 2: MİRAS MI, ARAYÜZ MÜ? (IS-A vs CAN-DO)
------------------------------------------------------------------------------

Kriter               Miras (extends / abstract class)  Arayüz (implements / interface)

-------------------  --------------------------------  -------------------------------

Temel Soru           "Bu NEDİR?" (IS-A)                "Bu NE YAPABİLİR?" (CAN-DO)

Cümle Testi          "Daire bir Şekildir."             "Kredi kartı ödeme yapabilir."

Ortak Veri Durumu    Ortak alanlar (ad, renk) taşır.   Alan taşımaz (sadece sabitler).

Java Kısıtı          Tekli Miras (1 sınıftan türeyebilir) Çoklu Arayüz (N tane uygulanabilir)

Kullanım Nedeni      Ortak kod + ortak veri paylaşımı. Ortak yetenek sözleşmesi kurma.


ELMAS PROBLEMİ (Diamond Problem):

Neden Java'da 'class C extends A, B' yasaktır?

Çünkü A sınıfında da 'hesapla()' olsa, B sınıfında da 'hesapla()' olsa; C nesnesi 

'c.hesapla()' dediğinde JVM hangisini çalıştıracağını BİLEMEZ (belirsizlik).



AYRIM 3: @Override YAZMAZSAK NE OLUR?
------------------------------------------------------------------------------

Eğer alt sınıfta üst sınıfın metodunu ezerken metot adını yanlışlıkla 'Alan()' veya

'alan(int x)' yazarsan ve tepesine @Override koymadıysan:

Derleyici hata vermez! Bunu yepyeni, bağımsız bir metot zanneder.

Sonuç: Çok biçimlilik sessizce devre dışı kalır, üst sınıfın boşluğu çalışır.

@Override koyarsan derleyici kontrol eder: "Üst sınıfta bu isimde bir metot yok, 

yazım hatası yaptın!" diyerek seni derleme anında kurtarır.

================================================================================

4. C DİLİNDEN JAVA'YA GEÇENLER İÇİN KÖPRÜ

================================================================================

C Programlama Dünyası                 Java Nesne Yönelim Dünyası

------------------------------------  ------------------------------------------

static değişken + .h başlık disiplini private alan + public metot (Kapsülleme)

struct içindeki fonksiyon işaretçisi  interface / abstract metot (Soyutlama)

İç içe struct tanımlamak              extends ile sınıf türetmek (Miras)

void* işaretçi dönüşümleri (tehlikeli)Üst tür referansı (Tip güvenli Çok Biçimlilik)

switch(nesne->tip) blokları           Dinamik Bağlama (Dynamic Dispatch)

================================================================================

5. VİZE SINAVI İÇİN "HIZLI HATIRLATMA" KONTROL LİSTESİ

================================================================================

[ ] abstract sınıftan asla 'new' ile nesne üretilemez.

[ ] abstract metodun gövdesi olmaz, alt sınıfa zorunluluk dayatır.

[ ] super(...) çağrısı alt sınıf yapıcısının MUTLAKA ilk satırında olmalıdır.

[ ] Bir referansın sol tarafı (bildirim türü) neyi çağırabileceğini sınırlar.

[ ] Kapsülleme sadece gizlemek değil; kuralları metodun içine (yatir/cek) gömmektir.

[ ] Sırf kod tekrarından kaçmak için alakasız sınıflar arasında miras kurulmaz (IS-A testi).

[ ] Bir sınıf birden fazla sınıftan miras alamaz (Elmas problemi engeli).

================================================================================
*/