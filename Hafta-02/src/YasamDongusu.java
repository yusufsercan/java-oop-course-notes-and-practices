/*
 * Nesne Yonelimli Programlama - Hafta 02
 * Ornek 8: Nesne yasam dongusu ve cop toplayici
 *
 * C++'ta yikici (destructor) nesne yok edilirken CALISMASI GARANTI
 * edilerek cagrilirdi. Java'da yikici YOKTUR. Bellek, cop toplayici
 * (garbage collector) tarafindan, ZAMANI BELIRSIZ bicimde geri alinir.
 * Bu nedenle kaynak kapatma isi try-with-resources'a birakilir.
 */
public class YasamDongusu {

    /** Yonetilmesi gereken bir kaynagi temsil eder. */
    static class Kaynak implements AutoCloseable //Bu sınıf gerçek bir dosya değil;  deney yapabilmek için uydurulan sahte bir dosya/araçtır.
    {
        private final String ad;

        Kaynak(String ad) {
            this.ad = ad;
            System.out.println("  [ACILDI]  " + ad);
        }

        void kullan() {
            System.out.println("  [KULLANIM] " + ad);
        }

        @Override
        public void close() {
            System.out.println("  [KAPANDI] " + ad);
        }
    }

    public static void main(String[] args) {

        System.out.println("1) Nesne dogar (new) ve referansla erisilir:");
        Ogrenci o = new Ogrenci("Ali", "Yilmaz", 331001, 65, 78);
        System.out.println("   " + o);

        System.out.println("\n2) Referans baska nesneye yonlendirilir:");
        o = new Ogrenci("Ayse", "Demir", 331002, 70, 80);
        System.out.println("   " + o);
        System.out.println("   Ilk nesneye artik ERISILEMEZ -> cop toplayici adayi.");

        System.out.println("\n3) Referans null yapilir:");
        o = null;
        System.out.println("   Ikinci nesne de erisilemez duruma geldi.");

        System.out.println("\n4) Cop toplayici ONERISI (garanti degil):");
        System.gc();
        System.out.println("   System.gc() yalnizca bir ONERIDIR; JVM uymayabilir.");

        System.out.println("\n5) Kaynak yonetimi: try-with-resources");
        try (Kaynak k = new Kaynak("veri.txt")) {
            k.kullan();
        }   // close() burada OTOMATIK cagrilir
        System.out.println("   try blogu bitti; close() calisti.");

        System.out.println("\n6) Hata olsa bile close() calisir:");
        try (Kaynak k = new Kaynak("ayarlar.txt")) {
            k.kullan();
            if (k != null) {
                throw new RuntimeException("beklenmedik hata");
            }
        } catch (RuntimeException e) {
            System.out.println("   [YAKALANDI] " + e.getMessage());
        }
    }
}

/*

BU KODUN AMACI:
Sıfırdan ezberlenecek bir program değildir; JVM'in bellek yönetimini (Heap/Stack), 
Çöp Toplayıcı (GC) mantığını ve 'try-with-resources' güvencesini kanıtlayan bir 
deney düzeneğidir


1. 'Kaynak' SINIFI NEDEN static VE AutoCloseable?
--------------------------------------------------------------------------------
* static class: 'main' metodu static olduğu için, ayrı bir dosyaya gerek kalmadan 
  aynı dosya içinde bağımsız bir yardımcı sınıf olarak tanımlanmıştır
* implements AutoCloseable: Java'ya "Ben işletim sisteminden kaynak (dosya/soket) 
  tutan bir nesneyim; işim bitince veya hata çıkınca 'close()' metodumu mutlaka 
  çalıştır" taahhüdü verir


2. ADIM ADIM BELLEKTE NELER OLUYOR? (main İÇİNDEKİ 6 ADIM)
--------------------------------------------------------------------------------
ADIM 1: Nesne Doğar (new) ve Referansla Erişilir
* Heap bellekte "Ali" nesnesi oluşturulur
* Stack'teki 'o' referansı (tasma) Ali nesnesinin bellek adresini tutar
* Çıktı: [331001 | Ali Yilmaz ...]

ADIM 2: Referans Başka Nesneye Yönlendirilir
* 'o = new Ogrenci("Ayse", ...);' satırıyla 'o' referansı yeni nesneye çevrilir
* Sonuç: "Ali" nesnesine uzanan hiçbir referans kalmaz. 
* Bellek Terimi: İlk nesne artık 'ERİŞİLEMEZ' (Unreachable) duruma düşer ve 
  Çöp Toplayıcı (GC) ADAYI olur.

ADIM 3: Referans 'null' Yapılır
* 'o = null;' ile referans tamamen boşa çıkarılır
* Sonuç: İkinci nesne olan "Ayşe" de sahipsiz kalır ve o da çöp adayı olur

ADIM 4: Çöp Toplayıcı Önerisi (System.gc())
* 'System.gc();' komutu çöp toplayıcıyı ZORLA ÇALIŞTIRMAZ
* Yalnızca JVM'e bellek temizliği için bir ÖNERİDE (ricada) bulunur.
* JVM bellek ihtiyacı görmezse bu çağrıyı tamamen yok sayabilir.

ADIM 5: try-with-resources Kaynak Yönetimi
* 'try (Kaynak k = new Kaynak("veri.txt"))' parantez içinde açılır.
* Blok sonundaki süslü parantez '}' bittiği mikrosaniyede Java OTOMATİK olarak 
  'k.close()' metodunu çağırır.
* Çıktı Sırası:
  1. [ACILDI]   veri.txt
  2. [KULLANIM] veri.txt
  3. [KAPANDI]  veri.txt  --> (Otomatik tetiklendi!)
  4. try blogu bitti; close() calisti.

ADIM 6: Hata Çıksa Bile Kapanma Garantisi (EN ÇOK ÇIKAN SINAV SORUSU!)
* 'k.kullan()' sonrasında yapay bir hata fırlatılır ('throw new RuntimeException')
* ALTIN KURAL: Sistem programı patlatıp 'catch' bloğuna geçmeden ÖNCE, 
  kaynağın 'close()' metodunu çalıştırmayı GARANTİ EDER
* Çıktı Sırası
  1. [ACILDI]   ayarlar.txt
  2. [KULLANIM] ayarlar.txt
  3. [KAPANDI]  ayarlar.txt   --> DİKKAT: Hata olmasına rağmen ÖNCE kapandı!
  4. [YAKALANDI] beklenmedik hata --> Sonra catch bloğu devreye girdi.







1. DİLLER ARASI BELLEK YÖNETİMİ FARKI (TABLO 2.12)
--------------------------------------------------------------------------------
Soru                  | C Dili             | C++ Dili          | Java
----------------------|--------------------|-------------------|----------------------
Bellek nasıl ayrılır? | malloc(sizeof(T))  | new T()           | new T()[cite: 6]
Bellek nasıl verilir? | free(p) — elle     | delete p — elle   | Çöp toplayıcı (otomatik)[cite: 6]
Yıkıcı (destructor)?  | Yok                | Var (~T())        | KESİNLİKLE YOKTUR![cite: 6]
Ne zaman geri alınır? | free çağrıldığı an | delete anında     | Belirsiz (JVM karar verir)[cite: 6]
Bellek sızıntısı?     | Evet (unutulursa)  | Evet (unutulursa) | Nadiren (referans tutulursa)[cite: 6]
Kaynak kapatma        | fclose — elle      | Yıkıcıda (~T())   | try-with-resources[cite: 6]


2. BİR JAVA NESNESİNİN 5 AŞAMALI YAŞAM DÖNGÜSÜ
--------------------------------------------------------------------------------
1. Bellek Ayrılır     : 'new' ile Heap'te yer açılır, alanlar varsayılan değerle başlar[cite: 6].
2. Yapıcı Çalışır    : 'this(...)' zinciri biter; nesne geçerli ve hazır duruma gelir[cite: 6].
3. Kullanılır         : Referans üzerinden metotlar çağrılır, alanlar değişir[cite: 6].
4. Erişilemez Olur    : Nesneyi gösteren son referans başka nesneye kayar veya 'null' olur[cite: 6].
5. Çöp Toplanır       : JVM bellek ihtiyacı duyduğunda alanı temizler; zamanı belirsizdir[cite: 6].

* Kritik Ayrım: İlk 3 aşama yazılımcının elindedir; son 2 aşama tamamen JVM'e aittir[cite: 6].


3. 'System.gc()' TUZAĞI (EMİR DEĞİL, BİR RİCADIR!)
--------------------------------------------------------------------------------
* 'System.gc()' metodu Çöp Toplayıcı'yı ZORLA ÇALIŞTIRMAZ[cite: 6]. 
* Yalnızca JVM'e bellek temizliği için bir "öneride" bulunur[cite: 6].
* JVM isterse bu öneriyi tamamen yok sayabilir[cite: 6]. 
* Canlı üretim kodunda 'System.gc()' çağırmak neredeyse her zaman tasarım hatasıdır[cite: 6].


4. DIŞ KAYNAK YÖNETİMİ: 'try-with-resources' VE 'AutoCloseable'
--------------------------------------------------------------------------------
Çöp Toplayıcı sadece RAM'i temizler; dosya, veritabanı veya ağ soketlerini zamanında 
kapatamaz[cite: 7]. Açık kalan kaynaklar işletim sistemini kilitler[cite: 7].

A) Ne Zaman Kullanılır?
* Nesne bir dosyayı, soketi, veritabanını tutuyorsa ve sınıf 'AutoCloseable' 
  arayüzünü uyguluyorsa (implements AutoCloseable) kullanılır[cite: 7]. 
* Normal sınıflarda ('Ogrenci', 'Nokta') ASLA kullanılmaz.

B) Sözdizimi (Syntax):
   try (Kaynak k = new Kaynak("ayarlar.txt")) {
       k.kullan();
       // İşlem yapılır...
   } catch (Exception e) {
       // Hata yakalanır...
   }

C) Altın Kural (Kapanış Garantisi):
* Blok normal tamamlansa da veya içeride beklenmedik bir HATA (Exception) fırlatılsa 
  bile sistem 'k.close()' metodunu 'catch' bloğundan BİLE ÖNCE otomatik çağırır[cite: 8].


5. "BAK ŞÖYLE YAPARSAN HATA ALIRSIN" (SINAV VE MÜLAKAT TUZAKLARI)
--------------------------------------------------------------------------------
[TUZAK 1] "Java'da yıkıcı metot (destructor) vardır" Demek:
          Java'da YIKICI YOKTUR! C++'taki '~Sinif()' mantığı Java'da bulunmaz[cite: 6]. 
          Kaynak kapatma işini 'AutoCloseable' ve 'try-with-resources' üstlenir[cite: 6, 7].

[TUZAK 2] 'finalize()' Metodunu Çözüm Sanmak:
          Eski Java sürümlerinde yıkıcı gibi anlatılan 'finalize()', Java 9'da terk 
          edildi (deprecated), Java 18'de silinmeye aday gösterildi[cite: 8]. 
          Güvenilir değildir, asla kullanılmaz[cite: 8].

[TUZAK 3] 'AutoCloseable' Olmayan Sınıfı 'try' Parantezine Yazmak:
          try (Ogrenci o = new Ogrenci("Ali", 101)) { ... }
          DERLEME HATASI! Ogrenci sınıfı 'AutoCloseable' uygulamadığı için 
          Java derleyicisi bu kodu derlemez.

*/