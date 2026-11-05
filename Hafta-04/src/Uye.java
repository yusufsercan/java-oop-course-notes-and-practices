
public class Uye
{
    private final String uyeNo;
    private final String katilimYili;

    private String ad = "ISIMSIZ";
    private int puan = 0;

    // Tum Uye nesnelerinin paylastigi sayac
    private static int sonrakiSira = 1;

    public Uye(String katilimYili, String ad)
    {
        this.katilimYili = katilimYili;

        // Uye numarasi otomatik uretilir.
        this.uyeNo = String.format(
            "U-%s-%04d", katilimYili, sonrakiSira
        );

        // Yeni uye icin sayac bir artirilir.
        sonrakiSira++;

        // Ad dogrulamasi
        setAd(ad);
    }

    public void setAd(String ad)
    {
        if (ad == null || ad.isBlank())// isBlank() Java 11 ile geldi, Java 8'de yok. Java 8'de isEmpty() kullanilabilir. ikisi de bos stringleri kontrol eder, ama isBlank() bosluklari da kontrol eder.
        {
            System.out.println("  RED: uye adi bos olamaz.");
            return;
        }

        this.ad = ad;
    }

    public String getAd()
    {
        return this.ad;
    }

    public String getUyeNo()
    {
        return this.uyeNo;
    }

    public String getKatilimYili()
    {
        return this.katilimYili;
    }

    public int getPuan()
    {
        return this.puan;
    }

    public void puanEkle(int miktar)// burada miktar negatif olursa puan azalmasi gibi bir durum olmasin diye kontrol ekledik. eger negatif olursa puan eklenmez.
    {
        if (miktar <= 0)
        {
            System.out.println("  RED: eklenecek puan pozitif olmalidir.");
            return;
        }

        this.puan += miktar;
    }

    public void puanHarca(int miktar)// burada miktar negatif olursa puan artmasi gibi bir durum olmasin diye kontrol ekledik. eger negatif olursa puan harcanmaz.
    // harcanacak puan miktari, mevcut puandan fazla olamaz. eger fazla olursa puan harcanmaz.
    {
        if (miktar <= 0)
        {
            System.out.println("  RED: harcanacak puan pozitif olmalidir.");
            return;
        }

        if (miktar > this.puan)// eger harcanacak puan miktari mevcut puandan fazla ise, puan harcanmaz ve hata mesaji verilir.
        {
            System.out.printf(
                "  RED: yetersiz puan (%d > %d).%n",
                miktar, this.puan
            );
            return;
        }

        this.puan -= miktar;
    }

    // Turetilmis getter
    public String getSeviye()// puan degerine gore seviye belirlenir. 1000 ve uzeri PLATIN, 500-999 ALTIN, 100-499 GUMUS, 0-99 STANDART
    {
        if (this.puan >= 1000)
        {
            return "PLATIN";
        }
        else if (this.puan >= 500)
        {
            return "ALTIN";
        }
        else if (this.puan >= 100)
        {
            return "GUMUS";
        }
        else
        {
            return "STANDART";
        }
    }

    @Override
    public String toString()
    {
        return String.format(
            "Uye[%s, %s, puan=%d, %s]",
            uyeNo, ad, puan, getSeviye()
        );
    }
}


/*
 * ============================================================
 * HAFTA 04 - LABORATUVAR GOREV 3
 * KONU: SALT-OKUNUR KIMLIK VE OTOMATIK NUMARA
 * ============================================================
 *
 * 1. GOREVIN AMACI
 * ------------------------------------------------------------
 *
 * Her uyeye otomatik ve degistirilemez bir uye numarasi
 * vermek, puan islemlerini kontrollu sekilde yonetmek
 * ve uyelik seviyesini mevcut puandan hesaplamak.
 *
 *
 * 2. PRIVATE FINAL ALANLAR
 * ------------------------------------------------------------
 *
 * private final String uyeNo;
 * private final String katilimYili;
 *
 * private:
 * Alanlara sinif disindan dogrudan erisimi engeller.
 *
 * final:
 * Ilk atamadan sonra yeniden atama yapilmasini engeller.
 *
 * uyeNo constructor icinde otomatik olusturulur.
 * Disaridan uye numarasi parametresi alinmaz.
 *
 * Uye numarasi icin setter yazilmaz.
 *
 *
 * 3. STATIC SAYAC
 * ------------------------------------------------------------
 *
 * private static int sonrakiSira = 1;
 *
 * static, alani nesneye degil sinifa ait yapar.
 *
 * Butun Uye nesneleri ayni sayaci paylasir.
 *
 * Yeni uye olusturuldukca:
 *
 * sonrakiSira++;
 *
 * calisir.
 *
 * DIKKAT:
 * Sayaç puanEkle() icinde artirilmaz.
 * Sayacin artmasi yeni uye olusturulmasina baglidir.
 *
 *
 * 4. STRING.FORMAT() ILE NUMARA URETME
 * ------------------------------------------------------------
 *
 * String.format("U-%s-%04d", katilimYili, sonrakiSira)
 *
 * U-   -> Sabit metin.
 * %s   -> String degerini yerlestirir.
 * %04d -> Tam sayiyi en az 4 haneli gosterir,
 *         gerekirse soluna sifir ekler.
 *
 * Ornek:
 *
 * yil = "2026"
 * sira = 1
 *
 * SONUC: U-2026-0001
 *
 * sira = 12
 *
 * SONUC: U-2026-0012
 *
 * Numara olusturulduktan sonra sayac artirilir.
 * Ancak onceki uyelerin uyeNo alanlari degismez.
 *
 *
 * 5. CONSTRUCTOR
 * ------------------------------------------------------------
 *
 * public Uye(String katilimYili, String ad)
 *
 * Yapici iki parametre alir.
 *
 * uyeNo  -> Sinif kendisi uretir.
 * puan   -> 0'dan baslar.
 * ad     -> setAd() ile dogrulanir.
 *
 * private String ad = "ISIMSIZ";
 *
 * Bu baslangic degeri, gecersiz ad verilmesi
 * durumunda nesnenin bos isimle kalmasini engeller.
 *
 * setAd(ad):
 *
 * ad == null || ad.isBlank()
 *
 * Bos veya yalnizca bosluk iceren adlari reddeder.
 *
 *
 * 6. PUAN ISLEMLERI
 * ------------------------------------------------------------
 *
 * puanEkle(int miktar):
 *
 * - Miktar pozitif olmali.
 * - Gecerliyse puan artar.
 * - Gecersizse RED uyarisi yazilir.
 *
 * puanHarca(int miktar):
 *
 * - Miktar pozitif olmali.
 * - Mevcut puandan fazla harcanamaz.
 * - Gecersiz istekte puan degismez.
 *
 * Ornek:
 *
 * puan = 520
 *
 * puanHarca(700);
 *
 * Islem reddedilir, puan 520 kalir.
 *
 * puanHarca(20);
 *
 * puan 500 olur.
 *
 * NEDEN setPuan() YOK?
 *
 * Puan dogrudan atanmak yerine is metotlariyla
 * kontrollu olarak degistirilir.
 *
 *
 * 7. TURETILMIS GETTER - GETSEVIYE()
 * ------------------------------------------------------------
 *
 * getSeviye() mevcut puana gore uyelik seviyesini
 * hesaplar.
 *
 * 1000 ve uzeri -> PLATIN
 * 500-999        -> ALTIN
 * 100-499        -> GUMUS
 * 0-99           -> STANDART
 *
 * Seviye ayri bir alanda tutulmaz.
 *
 * Puan degistiginde getSeviye() yeniden hesaplama
 * yaparak guncel seviyeyi dondurur.
 *
 * DIKKAT:
 * Kosullar buyuk esikten kucuk esige dogru yazilir.
 *
 * 1200 puan hem 1000 hem 500 hem 100 esigini
 * gectigi icin ilk kontrol PLATIN olmali.
 *
 *
 * 8. TOSTRING() VE DEMO
 * ------------------------------------------------------------
 *
 * @Override ile toString() metodu ezilir.
 *
 * Ornek:
 *
 * Uye[U-2026-0001, Mert Aydin, puan=520, ALTIN]
 *
 * Gorev3_UyeDemo:
 *
 * - Uc farkli uye olusturur.
 * - Otomatik numaralandirmayi gosterir.
 * - Gecersiz ad girisini test eder.
 * - Puan ekleme ve harcamayi test eder.
 * - Seviyenin puana gore degismesini gosterir.
 * - Uye numarasinin salt-okunur oldugunu gosterir.
 *
 *
 * 9. SINAV ICIN KRITIK NOKTALAR
 * ------------------------------------------------------------
 *
 * - static alan tum nesneler tarafindan paylasilir.
 * - final alan yeniden atanamaz.
 * - private final alan salt-okunur tasarima yardim eder.
 * - this.uyeNo nesnenin kendi uye numarasidir.
 * - sonrakiSira sinifin ortak sayacidir.
 * - String.format("%04d", 1) -> "0001"
 * - Constructor nesne olusturulurken calisir.
 * - setAd() gecersiz adlari reddeder.
 * - puanEkle() ve puanHarca() is metotlaridir.
 * - getSeviye() turetilmis getter'dir.
 * - Gecersiz islemde eski puan korunur.
 *
 *
 * GENEL SONUC:
 *
 * STATIC SAYAC -> OTOMATIK NUMARA URETIMI
 * PRIVATE FINAL -> KIMLIGIN KORUNMASI
 * IS METOTLARI -> PUAN KURALLARININ KORUNMASI
 * TURETILMIS GETTER -> GUNCEL SEVIYENIN HESAPLANMASI
 *
 * ============================================================
 */
