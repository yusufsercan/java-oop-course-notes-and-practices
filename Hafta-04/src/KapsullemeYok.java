/*
 * Nesne Yonelimli Programlama - Hafta 04
 * Ornek 1: KAPSULLEME YOK - motivasyon problemi
 *
 * Hafta 1-3'te alanlari sadelik adina "public" biraktik. Bu ornek, o kararin
 * bedelini somut olarak gosterir: sinif kendi verisini koruyamaz, bir nesne
 * ANLAMSIZ bir duruma dusebilir ve hatanin nerede olustugu belli olmaz.
 */

/* Aynı dosyadaki paket-özel yardımcı sınıf (public değil). */
class AcikOgrenci {
    public String ad;
    public int yas;
    public double notOrtalamasi;

    public void yaz() {
        System.out.printf("ad='%s' | yas=%d | ortalama=%.1f%n", ad, yas, notOrtalamasi);
    }
}

public class KapsullemeYok {
    public static void main(String[] args) {
        AcikOgrenci o = new AcikOgrenci();
        o.ad = "Elif Kaya";
        o.yas = 20;
        o.notOrtalamasi = 78.5;
        o.yaz();

        System.out.println();
        System.out.println("--- Farkli modullerden gelen atamalar ---");

        // (a) Kayit modulu: yasi yanlislikla eksi yaziyor
        o.yas = -3;
        // (b) Sinav modulu: 100 uzeri not yaziyor
        o.notOrtalamasi = 350.0;
        // (c) Arayuz modulu: adi bosaltiyor
        o.ad = "";

        o.yaz();

        System.out.println();
        System.out.println("Nesne gecersiz durumda; derleyici hic sikayet etmedi.");
        System.out.println("Hatanin kaynagi bu satirlar degil, alanlarin acik olmasidir.");
    }
}

/*
================================================================================
NESNE YÖNELİMLİ PROGRAMLAMA - HAFTA 04 / TEORİK DERS NOTLARI
BÖLÜM 2: AÇIK BIRAKILAN ALANIN BEDELİ VE GECİKMİŞ HATA ANALİZİ
Dosya: KapsullemeYok.java
================================================================================

--------------------------------------------------------------------------------
I. TEMEL MİMARİ PROBLEMİ (ANTIPATTERN)
--------------------------------------------------------------------------------
1. 'public' ALANLARIN TEHLİKESİ:
   - Sınıf alanları ('ad', 'yas', 'notOrtalamasi') 'public' tanımlandığında,
     sınıfın dışındaki herhangi bir kod parçası bu alanlara doğrudan erişip
     dilediği değeri yazabilir (o.yas = -3).
   - Bu durum DÜZEY 1 KAPSÜLLEME (yani aslında SIFIR KAPSÜLLEME) durumudur.
   - Nesne, kendi verisinin doğruluğunu denetleme yetkisini tamamen kaybetmiştir.

2. DERLEYİCİNİN (COMPILER) ÇARESİZLİĞİ:
   - Java derleyicisi yalnızca TİP GÜVENLİĞİNİ (Type Safety) denetler.
   - Derleyici şuna bakar:
     * -3 bir integer mıdır? -> EVET.
     * 350.0 bir double mıdır? -> EVET.
     * "" bir String midir? -> EVET.
   - Derleyici iş mantığını (Business Logic) bilemez. Yaşın eksi olamayacağını,
     notun 0-100 arasında olması gerektiğini bilmek YAZILIMCININ ve NESNENİN
     kendi sorumluluğundadır.

--------------------------------------------------------------------------------
II. VİZE VE MÜLAKAT KAVRAMI: "GECİKMİŞ HATA" (DELAYED BUG)
--------------------------------------------------------------------------------
* Tanım:
  Hatanın sisteme girdiği / oluştuğu yer ile fark edildiği / patladığı yerin
  birbirinden zaman ve mekân olarak çok uzak olması durumudur.

* Neden En Tehlikeli ve Pahalı Hata Türüdür?
  1. Sessiz Başarısızlık (Silent Failure): 'o.yas = -3;' satırı çalıştığında
     hiçbir Exception fırlatılmaz, terminal kırmızıya boyanmaz. Kod çalışmaya
     devam eder.
  2. İz Kaybı: Bu nesne veri tabanına yazılır. Aylar sonra burs dağıtan veya
     mezuniyet hesaplayan başka bir modül bu eksi yaş yüzünden çöker.
  3. Hata Ayıklama (Debugging) Maliyeti: Sistem çöktüğünde yazılımcılar hatayı
     mezuniyet modülünde arar; ancak hatanın asıl kaynağı aylar önce çalışmış
     olan o.yas = -3 atamasıdır.

--------------------------------------------------------------------------------
III. ÇÖZÜM REÇETESİ (HAFTA 4'ÜN VİZYONU)
--------------------------------------------------------------------------------
1. Alanları derhal 'private' yap!
2. Dışarıdan her önüne gelenin veri değiştirmesini engelle.
3. Değişim yapılacaksa, sınıfın içine kuralları kontrol eden (guard clauses)
   doğrulama metotları koy (Örn: setYas(int yas) -> if (yas < 0) hataVer()).
4. Unutma: "Hatanın kaynağı o.yas = -3 satırı değil; alanların public bırakılmasıdır."
================================================================================
*/