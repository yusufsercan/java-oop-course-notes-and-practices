/*
 * Nesne Yonelimli Programlama - Hafta 02
 * Ornek 9: Erisim belirleyicilerine giris (public / private)
 *
 * Alanlar private oldugu icin gecersiz duruma dusmek imkansizdir.
 * Kural sinifin ICINDE, tek yerde tanimlidir.
 */
public class Dikdortgen {

    private double genislik;
    private double yukseklik;

    public Dikdortgen(double genislik, double yukseklik) {
        this.genislik = pozitifYap(genislik);
        this.yukseklik = pozitifYap(yukseklik);
    }

    /** Kare olusturmak icin kisa yapici. */
    public Dikdortgen(double kenar) {
        this(kenar, kenar); //bir atama değil, bir yapıcı çağrısıdır
    }

    private static double pozitifYap(double deger) {
        return (deger > 0) ? deger : 1.0; // ? deger ->Evet, sıfırdan büyükse gelen değeri olduğu gibi koru ve döndür.
                                          // : 1.0 -> Hayır, sayı sıfır veya negatifse (örneğin 0, -3.5 veya -10 girilmişse) geçersizdir; nesne bozulmasın diye onu varsayılan güvenli değer olan 1.0 yap ve döndür
    }

    // --- Okuma erisimi (getter) ---
    public double getGenislik() {
        return genislik;
    }

    public double getYukseklik() {
        return yukseklik;
    }

    // --- Denetimli yazma erisimi (setter) ---
    public void setGenislik(double genislik) {
        if (genislik <= 0) {
            System.out.println("Uyari: genislik pozitif olmalidir; degisiklik yapilmadi.");
            return;
        }
        this.genislik = genislik;
    }

    public void setYukseklik(double yukseklik) {
        if (yukseklik <= 0) {
            System.out.println("Uyari: yukseklik pozitif olmalidir; degisiklik yapilmadi.");
            return;
        }
        this.yukseklik = yukseklik;
    }

    public double alan() {
        return genislik * yukseklik;
    }

    public double cevre() {
        return 2 * (genislik + yukseklik);
    }

    public boolean karedirMi() {
        return Math.abs(genislik - yukseklik) < 1e-9;//1e-9 ifadesi, matematikte ve programlamada bilimsel gösterimdir 0.000000001 (milyarda bir) demektir.
        //Math.abs(...) (Absolute Value - Mutlak Değer): Sonucun eksi çıkma ihtimalini ortadan kaldırır (genişlik mi büyük yükseklik mi büyük fark etmesin diye)
        //< 1e-9: Der ki: "Bu iki kenarın arasındaki fark milyarda birden (0.000000001) daha küçükse, aradaki o mikroskobik fark önemsiz bir donanım yuvarlama hatasıdır; sen bunları eşit (kare) kabul et!"
    }

    @Override
    public String toString() {
        return String.format("%.1f x %.1f | alan=%.2f | cevre=%.2f | %s",
                genislik, yukseklik, alan(), cevre(),
                karedirMi() ? "kare" : "dikdortgen");
    }

    public static void main(String[] args) {
        Dikdortgen d1 = new Dikdortgen(4, 3);
        Dikdortgen kare = new Dikdortgen(5);
        System.out.println("d1   = " + d1);
        System.out.println("kare = " + kare);

        System.out.println("\n--- Gecersiz deger denemeleri ---");
        Dikdortgen d2 = new Dikdortgen(-10, 0);   // yapici duzeltir
        System.out.println("new Dikdortgen(-10, 0) -> " + d2);

        d1.setGenislik(-5);                        // setter reddeder
        System.out.println("d1 (setGenislik(-5) sonrasi) = " + d1);

        // d1.genislik = -5;   <-- DERLENMEZ: genislik private'tir
    }
}

/*
1. KAPSÜLLEME (ENCAPSULATION) NEDİR?
--------------------------------------------------------------------------------
Nesnenin durumunu (alanlarını) dış dünyadan 'private' ile gizleyip, bu verilere 
yalnızca sınıfın kendi sunduğu denetimli metotlar (getter/setter) üzerinden 
erişim izni vermektir.
* Amacı: Veri bütünlüğünü korumak; nesnenin geçersiz/tutarsız değerlerle 
  bozulmasını (örn: genislik = -5) engellemek.


2. ERİŞİM BELİRTEÇLERİ (ACCESS MODIFIERS - TABLO 2.13)
--------------------------------------------------------------------------------
Belirteç         | Sınıf İçi | Paket İçi | Alt Sınıf | Her Yer
-----------------|-----------|-----------|-----------|---------
private          | EVET      | HAYIR     | HAYIR     | HAYIR
(paket-default)  | EVET      | EVET      | HAYIR     | HAYIR
protected        | EVET      | EVET      | EVET      | HAYIR
public           | EVET      | EVET      | EVET      | EVET


3. JAVABEANS İSİMLENDİRME STANDARTLARI (TABLO 2.14)
--------------------------------------------------------------------------------
Kural            | Doğru Kullanım          | Hatalı Kullanım
-----------------|-------------------------|------------------------------------
Getter Adı       | getGenislik()           | genislik(), genislikAl()
Boolean Getter   | isKare(), kareMi()      | getKare()
Setter Adı       | setGenislik(double g)   | genislikAyarla(double g)
Setter Dönüşü    | void (veya akıcı 'this')| Alanın türü
Doğrulama        | Setter'ın içinde        | Çağrı yerinde tek tek


4. "BAK ŞÖYLE YAPARSAN HATA ALIRSIN" (MÜHENDİSLİK VE SINAV TUZAKLARI)
--------------------------------------------------------------------------------
[TUZAK 1] Hesaplanabilir Değerleri Alan (Field) Olarak Tutmak:
          'private double alan;' şeklinde değişken açmak veri tutarsızlığına 
          (inconsistency) yol açar. Genişlik değiştiğinde alan eski kalır.
          DOĞRUSU: Alan tutma, 'getAlan() { return genislik * yukseklik; }' yaz!

[TUZAK 2] Düşünmeden Otomatik Getter/Setter Basmak:
          Değiştirilmemesi gereken bir alana (örn: ogrenciNo, tcKimlik) 'set' 
          yazarsan kapsüllemeyi delmiş olursun. Sadece okunacaksa yalnızca 
          getter açılır.

[TUZAK 3] Doğrulamayı (Validation) Setter Dışına Taşımak:
          Eksi kontrolünü 'main' metodunda yapmak kod tekrarı yaratır. 
          Bütün kurallar sınıfın içindeki setter'a yazılmalıdır.
================================================================================




1. YAPICI (CONSTRUCTOR) VE C++ FONKSİYON EŞLEŞMESİ
--------------------------------------------------------------------------------
Yapıcı bir sınıf değildir; bir nesne 'new' ile belleğe çıkarken çalışan 
özel bir ilk kurulum fonksiyonudur[cite: 1, 6]. 

C++ Kavramı           | Java Karşılığı     | Sınıftaki Yeri / Örneği
----------------------|--------------------|---------------------------------------
Class                 | Class              | public class Dikdortgen[cite: 6]
Member Variable       | Field (Alan)       | private double genislik;[cite: 6]
Constructor           | Constructor        | public Dikdortgen(double g, double y)[cite: 6]
Member Function       | Method             | public void setGenislik(double g)[cite: 7]

Bir Kodun Yapıcı Olduğunu Anlamanın 2 Kuralı:
* Kural 1: Adı sınıfla birebir aynıdır (Büyük/küçük harf duyarlı)[cite: 6].
* Kural 2: DÖNÜŞ TİPİ KESİNLİKLE YOKTUR ('void', 'double', 'int' yazmaz)[cite: 6].


2. YAPICI ZİNCİRLEME (CONSTRUCTOR CHAINING) VE 'this(...)'
--------------------------------------------------------------------------------
    public Dikdortgen(double kenar) {
        this(kenar, kenar); // Patron yapıcıyı çağırır[cite: 7]
    }

* 'this.kenar = kenar;' Neden Yazılmaz?
  Sınıfta 'kenar' adında bir alan (field) yoktur; sadece 'genislik' ve 'yukseklik' 
  vardır[cite: 6]. Kare, iki kenarı eşit bir dikdörtgendir[cite: 6, 7].
* 'this.' ile 'this(...)' Farkı:
  - this.alan : Nesnenin içindeki değişkene işaret eder[cite: 6, 7].
  - this(...) : Sınıf içindeki parametresi uyan BAŞKA BİR YAPICIYI çalıştırır[cite: 7].
* ALTIN KURAL: 'this(...)' çağrısı, yapıcının süslü parantezinden sonraki 
  İLK ÇALIŞTIRILABİLİR SATIR olmak zorundadır! Öncesine kod yazılırsa derlenmez.


3. TERNARY OPERATÖRÜ (? :) VE GÜVENLİ FİLTRELEME
--------------------------------------------------------------------------------
    private static double pozitifYap(double deger) {
        return (deger > 0) ? deger : 1.0;[cite: 7]
    }

* Çalışma Mantığı: (Koşul) ? Doğruysa_Dönecek : Yanlışsa_Dönecek[cite: 7]
* Sıfırdan Büyükse: Değeri olduğu gibi korur (örn: 0.5 girilirse 0.5 kalır)[cite: 7].
* Sıfır veya Eksi ise: Nesne geçersiz duruma düşmesin diye 1.0 değerine eşitler[cite: 7].
* Neden 'private static'? Sadece sınıfın iç matematiğidir; dış dünyaya kapalıdır[cite: 7]. 
  Nesne alanlarına ('this') ihtiyaç duymadığı için 'static' tanımlanmıştır[cite: 7].


4. KAYAN NOKTALI SAYILARDA EŞİTLİK VE tolerans (1e-9)
--------------------------------------------------------------------------------
    public boolean karedirMi() {
        return Math.abs(genislik - yukseklik) < 1e-9;[cite: 9]
    }

* '1e-9' Nedir? Bilimsel gösterimle $1 \times 10^{-9}$ yani 0.000000001 (milyarda bir)[cite: 9].
* Neden 'genislik == yukseklik' Kullanılmaz?
  İşlemciler kesirli sayıları (double/float) ikilik tabanda tutarken mikroskobik 
  yuvarlama farkları oluşur (Örn: 0.1 + 0.2 == 0.3 ifadesi 'false' döner).
* Epsilon (Tolerans) Yaklaşımı: İki kenarın farkının mutlak değeri milyarda birden 
  küçükse, aradaki fark donanımsal yuvarlama kabul edilir ve şekil kare sayılır[cite: 9].


5. KAPSÜLLEME (ENCAPSULATION) VE ERİŞİM DENETİMİ
--------------------------------------------------------------------------------
* Alanlar 'private' yapılarak dış dünyadan doğrudan müdahaleye kapatılır[cite: 6].
* Değişiklikler yalnızca kontrollü 'setter' metotları üzerinden yapılır[cite: 7].
* Erken Dönüş (Guard Clause):
      if (genislik <= 0) {
          System.out.printf("Uyari: genislik pozitif olmalidir...%n");[cite: 7]
          return; // Hatalıysa işlemi yapmadan metodu anında keser![cite: 7]
      }
      this.genislik = genislik;[cite: 7]


6. "BAK ŞÖYLE YAPARSAN HATA ALIRSIN" (SINAV VE MÜLAKAT TUZAKLARI)
--------------------------------------------------------------------------------
[TUZAK 1] Yapıcıya 'void' Yazmak:
          'public void Dikdortgen(...)' yazarsan Java bunu yapıcı değil sıradan 
          bir metot sayar. 'new Dikdortgen(...)' çağrıldığında derleme hatası alırsın.

[TUZAK 2] 'return;' Koymayı Unutmak:
          Setter içinde eksi kontrolü yapıp 'return;' yazmazsan, ekrana uyarı 
          yazsa bile kod bir alt satıra geçer ve eksi değeri atayarak nesneyi bozar.

[TUZAK 3] Hesaplanabilir Özellikleri Değişken (Field) Yapmak:
          Sınıfta 'private double alan;' diye değişken tutmak veri tutarsızlığı 
          yaratır[cite: 8]. Genişlik güncellendiğinde alan eski kalır[cite: 8]. 
          Doğrusu: 'public double getAlan() { return genislik * yukseklik; }' 
          şeklinde dinamik hesaplatmaktır[cite: 8].

[TUZAK 4] double Karşılaştırmasında '==' Kullanmak:
          Vize sınavında 'double' türündeki iki sayıyı '==' ile karşılaştırmak 
          hassasiyet kaybı nedeniyle mantıksal hatalara yol açar.
================================================================================

*/