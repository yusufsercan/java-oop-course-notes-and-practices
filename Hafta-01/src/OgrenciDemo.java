
// burada Ogrenci.java dosyasini import etmeye gerek yoktur; cunku ayni paket icindedirler.
// bu kodda OgrenciDemo.java dosyasi, Ogrenci.java dosyasini kullanir; yani Ogrenci sinifindan nesne uretir ve metotlarini cagirir.

public class OgrenciDemo {

    public static void main(String[] args) {

        // 1) Nesne olusturma: "new" ile obekte yer ayrilir, referans degiskene atanir.
        Ogrenci o1 = new Ogrenci();
        o1.ad = "Ali";
        o1.soyad = "Yilmaz";
        o1.numara = 331001;
        o1.vizeNotu = 65;
        o1.finalNotu = 78;

        Ogrenci o2 = new Ogrenci();
        o2.ad = "Ayse";
        o2.soyad = "Demir";
        o2.numara = 331002;
        o2.vizeNotu = 40;
        o2.finalNotu = 52;

        // 2) Her nesnenin kendi alan degerleri vardir; davranis tanimi ortaktir.
        System.out.println("--- Ogrenci Listesi ---");
        o1.bilgiYaz();
        o2.bilgiYaz();

        // 3) Nesneler bir dizide toplanabilir.
        Ogrenci[] sinif = { o1, o2 };
        double toplam = 0;
        for (Ogrenci o : sinif) {
            toplam += o.ortalamaHesapla();
        }
        System.out.printf("Sinif ortalamasi: %.2f%n", toplam / sinif.length);

        // 4) Referans semantigi: iki degisken ayni nesneyi gosterebilir.
        Ogrenci takma = o1;
        takma.vizeNotu = 90;
        System.out.println("--- takma.vizeNotu = 90 atamasindan sonra ---");
        o1.bilgiYaz();          // o1 de degisti, cunku ayni nesnedir.
        System.out.println("o1 == takma  -> " + (o1 == takma));

        // 5) Baslatilmamis referans: null
        Ogrenci o3 = null;
        System.out.println("o3 == null   -> " + (o3 == null));
        // o3.bilgiYaz();       <-- CALISMA ZAMANI HATASI: NullPointerException
    }
}

/*
1. SORU: C'DEKİ '#include' OLMADAN BU İKİ DOSYA BİRBİRİNİ NASIL BULUYOR?
--------------------------------------------------------------------------------
- C Yaklaşımı (Metin Kopyalama):
  C'de derleme öncesinde önişlemci (Preprocessor) çalışır. '#include "Ogrenci.h"'
  yazdığında, o başlık dosyasının içindeki kodları alır ve fiziksel olarak kodun
  tepesine yapıştırır. Yazmazsan derleyici diğer dosyadan haberdar olamaz.

- Java Yaklaşımı (Otomatik Sınıf Arama - Class Lookup):
  Java'da önişlemci yoktur. Java derleyicisi (javac), kod içinde 'Ogrenci' tipini
  gördüğünde sırasıyla şu adımları izler:
  1. Bu bir ilkel tip mi (int, double)? -> Hayır.
  2. Java'nın yerleşik kütüphanesi mi (java.lang vs.)? -> Hayır.
  3. "O halde bulunduğum dizine (aynı pakete) bakayım" der.
  4. Aynı klasörde 'Ogrenci.java' veya 'Ogrenci.class' dosyasını gördüğü an
     bağıntıyı kendisi kurar ve iki sınıfı el sıkıştırır.


================================================================================
2. SORU: 'Ogrenci.java' SİLİNİRSE 'OgrenciDemo' NEDEN ÇALIŞMAZ?
--------------------------------------------------------------------------------
- Sınıf (Ogrenci.java)    : Bir bina projesidir (mavi kopya / blueprint).
- Sürücü (OgrenciDemo.java): O projeyi alıp binayı inşa eden şantiyedir.

Eğer projeyi (Ogrenci.java) çöpe atarsan, şantiye ustası (javac) 'new Ogrenci()'
satırına geldiğinde duvara toslar:
"cannot find symbol: class Ogrenci" hatası verir.
Çünkü Java, nesnenin bellekte kaç bayt yer tutacağını, hangi değişkenlere ve
hangi metotlara sahip olduğunu Ogrenci sınıfına bakmadan bilemez.


================================================================================
3. DERLEME VE ÇALIŞMA ZİNCİRİNDE ARKA PLANDA NE DÖNÜYOR?
--------------------------------------------------------------------------------
1. Derleme Aşaması (javac -d bin src/OgrenciDemo.java):
   Derleyici akıllıdır. Sen sadece OgrenciDemo'yu derle desen bile, onun
   içinde Ogrenci sınıfının kullanıldığını fark eder. Eğer 'bin/Ogrenci.class'
   henüz yoksa, arka planda sessizce 'Ogrenci.java'yı da derler ve bin klasörüne
   iki adet bytecode üretir:
   - bin/Ogrenci.class
   - bin/OgrenciDemo.class

2. Çalıştırma Aşaması (java -cp bin OgrenciDemo):
   - JVM ayağa kalkar ve ClassLoader (Sınıf Yükleyici) mekanizması devreye girer.
   - Önce 'OgrenciDemo.class' dosyasını belleğe çeker ve main metodunu başlatır.
   - Kod akışı 'new Ogrenci()' satırına geldiğinde, JVM 'bin' klasöründen
     'Ogrenci.class' dosyasını dinamik olarak yükler.


================================================================================
4. BELLEK MİMARİSİ: STACK, HEAP VE REFERANS SEMANTİĞİ (OTEL BENZETMESİ)
--------------------------------------------------------------------------------
Kod Satırı:
Ogrenci o1 = new Ogrenci();
Ogrenci takma = o1;

- HEAP BÖLGESİ (Fiziksel Nesneler / Otel Odası):
  'new Ogrenci()' ifadesi çalıştığı an, dinamik bellekte (Heap) bir alan açılır.
  Bu alanın içinde ad, soyad, numara, vizeNotu, finalNotu verileri barınır.
  Burası gerçek "nesnedir".

- STACK BÖLGESİ (Yerel Değişkenler / Manyetik Anahtar Kartı):
  'o1' ve 'takma' değişkenleri nesnenin kendisi DEĞİLDİR!
  Onlar Stack üzerinde yaşayan ve Heap'teki o odanın adresini tutan
  birer kumandadır (referanstır).

- REFERANS KOPYALAMA MANTIĞI:
  'Ogrenci takma = o1;' satırı yeni bir nesne (oda) oluşturmaz.
  Yalnızca mevcut odanın anahtar kartının bir kopyasını 'takma' değişkenine verir.
  İki kumanda da aynı nesneyi işaret ettiği için:
  takma.vizeNotu = 95.0; dendiğinde, o1.bilgiYaz() da 95.0 basar.


================================================================================
5. GARBAGE COLLECTOR (ÇÖP TOPLAYICI) NEDİR? (C'DEKİ free() FARKI)
--------------------------------------------------------------------------------
C dilinde dinamik bellek (malloc) açtığında, işin bitince 'free()' ile elle
temizlemek zorundaydın; unutursan bellek sızıntısı (memory leak) oluşurdu.

Java'da eğer:
o1 = null;
takma = null;
yaparsan, Heap'teki o öğrenci nesnesine bağlı hiçbir kumanda kalmaz.
JVM'in arka planında çalışan Garbage Collector (Çöp Toplayıcı), adresi kaybolmuş
bu sahipsiz nesneyi otomatik olarak bellekten siler. Mühendisi manuel bellek
yönetimi yükünden kurtarır.
================================================================================
*/



