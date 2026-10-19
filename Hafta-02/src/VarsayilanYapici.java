/*
 * Nesne Yonelimli Programlama - Hafta 02
 * Ornek 7: Varsayilan yapicinin kaybolmasi
 *
 * Hicbir yapici yazmazsaniz, derleyici sizin icin parametresiz bir
 * "varsayilan yapici" (default constructor) uretir. Ancak KENDINIZ bir
 * yapici yazdiginiz anda derleyici artik varsayilan yapiciyi uretmez.
 */
public class VarsayilanYapici {

    // ---- Durum 1: hic yapici yok -> derleyici varsayilani uretir ----
    static class A {
        int deger;
        // Derleyici gizlice sunu ekler:  A() { }
    }

    // ---- Durum 2: parametreli yapici var -> varsayilan KAYBOLUR ----
    static class B {
        int deger;

        B(int deger) {
            this.deger = deger;
        }
        // new B();  <-- DERLENMEZ: parametresiz yapici artik yok
    }

    // ---- Durum 3: ikisini de acikca yaziyoruz -> ikisi de kullanilabilir ----
    static class C {
        int deger;

        C() {
            this(0);
        }

        C(int deger) {
            this.deger = deger;
        }
    }

    public static void main(String[] args) {
        A a = new A();                 // calisir
        System.out.println("A varsayilan yapici ile olustu, deger = " + a.deger);

        B b = new B(42);               // calisir
        System.out.println("B parametreli yapici ile olustu, deger = " + b.deger);
        // B b2 = new B();             // DERLENMEZ

        C c1 = new C();                // calisir
        C c2 = new C(7);               // calisir
        System.out.println("C: c1.deger = " + c1.deger + ", c2.deger = " + c2.deger);

        System.out.println();
        System.out.println("Alanlarin varsayilan degerleri:");
        Varsayilanlar v = new Varsayilanlar();
        v.yaz();
    }

    /** Ilkel ve referans alanlarin varsayilan degerleri. */
    static class Varsayilanlar {
        int tamSayi;
        double ondalik;
        boolean mantiksal;
        char karakter;
        String metin;
        int[] dizi;

        void yaz() {
            System.out.println("  int     -> " + tamSayi);
            System.out.println("  double  -> " + ondalik);
            System.out.println("  boolean -> " + mantiksal);
            String karakterGosterimi = (karakter == '\u0000')
                    ? "\\u0000 (bos karakter)" : String.valueOf(karakter);
            System.out.println("  char    -> " + karakterGosterimi);
            System.out.println("  String  -> " + metin);
            System.out.println("  int[]   -> " + dizi);
        }
    }
}



/*

1. VARSAYILAN YAPICI (DEFAULT CONSTRUCTOR) MEKANİZMASI
--------------------------------------------------------------------------------
* Kural: Sınıfta HİÇBİR yapıcı yazılmazsa, derleyici arka planda otomatik olarak 
  görünmez, parametresiz ve boş gövdeli bir yapıcı üretir:
      public Sinif() { }

* KAYBOLMA KURALI (EN BÜYÜK SINAV TUZAĞI):
  Sınıfa parametreli EN AZ BİR yapıcı yazdığın anda derleyici otomatik üretimden 
  vazgeçer! Varsayılan yapıcı ANINDA KAYBOLUR.
  - Sınıfta sadece 'Sinif(int x)' varsa -> 'new Sinif()' ARTIK DERLENMEZ!
  - Çözüm: Parametresiz kullanıma ihtiyaç varsa elle açıkça 'Sinif() { ... }' yazılmalıdır.

* Tablo Özeti:
  Sınıfta Yazılı Olan Yapıcı    | 'new Sinif()'     | 'new Sinif(42)'
  ------------------------------|-------------------|------------------
  Hiç yok                       | ÇALIŞIR (Derleyici)| DERLENMEZ
  Yalnızca Sinif(int)           | DERLENMEZ         | ÇALIŞIR
  Sinif() ve Sinif(int)         | ÇALIŞIR           | ÇALIŞIR
  private Sinif()               | DERLENMEZ (Kilit) | DERLENMEZ


2. KOPYA YAPICI (COPY CONSTRUCTOR) VE BELLEK BAĞIMSIZLIĞI
--------------------------------------------------------------------------------
Kendi sınıfından bir nesneyi parametre olarak alan ve onun verilerini kopyalayarak 
Heap'te yepyeni, bağımsız bir nesne üreten yapıcıdır.

    public Ogrenci(Ogrenci digeri) {
        this(digeri.ad, digeri.soyad, digeri.numara, digeri.vizeNotu, digeri.finalNotu);
    }

Atama (=) ile Kopya Yapıcı Arasındaki Fark:
-------------------------------------------
İfade                       | Nesne Sayısı | o1 == o2  | Birini Değiştirmek Diğerini Etkiler mi?
----------------------------|--------------|-----------|----------------------------------------
Ogrenci o2 = o1;            | 1 (Tek nesne)| true      | EVET (Aynı nesneyi işaret eden 2 kumanda)
Ogrenci o2 = new Ogrenci(o1)| 2 (Bağımsız) | false     | HAYIR (Heap'te ayrı bir ikiz nesne)


3. '==' İLE 'equals()' ARASINDAKİ FARK
--------------------------------------------------------------------------------
* '==' Operatörü: Referans tiplerde nesnenin İÇERİĞİNE BAKMAZ! 
  Stack'teki RAM bellek adreslerini karşılaştırır. İki nesne tıpatıp aynı 
  bilgilere sahip olsa bile farklı 'new' ile üretilmişlerse '==' sonucu FALSE döner.
* 'equals()' Metodu: Nesnelerin İÇERİK (alan) eşitliğini karşılaştırmak için kullanılır.


4. "BAK ŞÖYLE YAPARSAN HATA ALIRSIN" (MÜHENDİSLİK UYARILARI)
--------------------------------------------------------------------------------
[TUZAK 1] Mevcut Kodu Kırmak:
          Çalışan bir projede sınıfa parametreli yapıcı ekleyip parametresiz 
          yapıcıyı yazmayı unutursan, projede 'new Sinif()' ile nesne üreten 
          bütün eski kodlar derleme hatası verir.

[TUZAK 2] o2 = o1 İşlemini Kopya Sanmak:
          'o2 = o1;' yazıldığında nesne kopyalanmaz, sadece referans (adres) 
          kopyalanır. 'o2.setNot(100)' yaptığında 'o1'in de notu değişir. 
          Gerçek bağımsızlık için 'new' anahtarı şarttır!

[TUZAK 3] Sığ Kopya (Shallow Copy) Sınırı:
          Kopya yapıcı temel tipleri (int, double) ve değişmezleri (String) 
          kusursuz kopyalar. Ancak nesnenin içinde başka bir nesne referansı 
          (dizi, liste vb.) varsa, onlar yine ortak adresi paylaşır.
================================================================================


1. PARAMETRE NEDİR?
* Bir metodun veya yapıcının işini yapabilmesi için dışarıdan beklediği 
  girdi yuvalarıdır (değişkenlerdir).

2. PARAMETRELİ YAPICI NEDİR?
* Nesne Heap'te ilk kez yaratılırken (new anında) zorunlu bilgileri alıp 
  nesnenin durumunu (state) doğrudan başlatan özel metottur.

3. NEDEN KULLANILIR?
* Güvenlik ve Bütünlük: Nesnenin eksik, tanımsız veya boş (null) bilgilerle 
  sistemde başıboş dolaşmasını engeller. Doğduğu anda hazır olmasını sağlar.

4. PARAMETRE VS ALAN (FIELD) FARKI:
* Alan (Field)      : Nesneye aittir, Heap'te yaşar, nesne silinene kadar kalır.
* Parametre (Param) : Metoda aittir, Stack'te geçici yaşar, yapıcı bittiğinde silinir.


*/