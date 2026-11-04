
public class Urun
{
    private final String barkod;
    private String ad = "Adsiz Urun";
    private double fiyat;
    private int stok;

    public Urun(String barkod, String ad, double fiyat, int stok)
    {
        this.barkod = barkod;

        // Dogrulamalari tekrar yazmak yerine metotlari cagiriyoruz.
        setAd(ad);
        setFiyat(fiyat);

        // Baslangic stogu 0 olabilir.
        // Sifir disindaki miktarlar stokGir ile denetlenir.
        if (stok != 0)
        {
            stokGir(stok);
        }
    }

    public void stokGir(int adet)// burada stokgir metodu ile stok girisi yapılıyor. adet pozitif olmalı ve geçerli ise stok += adet yapılıyor.
    {
        if (adet <= 0)
        {
            System.out.println("  RED: stok girisi pozitif olmalidir.");
            return;
        }

        this.stok += adet;
    }

    public void stokCik(int adet)// burada stok cikisi metodu ile stok cikisi yapılıyor. adet pozitif olmalı ve mevcut stoktan fazla olmamalı. geçerli ise stok -= adet yapılıyor.
    {
        if (adet <= 0)
        {
            System.out.println("  RED: stok cikisi pozitif olmalidir.");
            return;
        }

        if (adet > this.stok)
        {
            System.out.printf(
                "  RED: stok yetersiz (%d > %d).%n",
                adet, this.stok
            );
            return;
        }

        this.stok -= adet;// amacı stok miktarını azaltmak. stok cikisi yapıldığında stok miktarı azalır.
    }

    public String getBarkod()
    {
        return this.barkod;
    }

    public String getAd()
    {
        return this.ad;
    }

    public void setAd(String ad)
    {
        if (ad == null || ad.isBlank())
        {
            System.out.println("  RED: urun adi bos olamaz.");
            return;
        }

        this.ad = ad;
    }

    public double getFiyat()
    {
        return this.fiyat;
    }

    public void setFiyat(double fiyat)
    {
        if (fiyat < 0)
        {
            System.out.printf(
             
                "  RED: fiyat negatif olamaz (%.2f).%n",
                fiyat
            );
            return;
        }

        this.fiyat = fiyat;
    }

    public int getStok()
    {
        return this.stok;
    }

    // Turetilmis getter: Ayrica stokDegeri alani tutulmaz.
    public double getStokDegeri()
    {
        return this.fiyat * this.stok;
    }

    @Override
    public String toString()
    {
        return String.format(
           
            "Urun[%s, %s, %.2f TL, stok=%d]",
            barkod, ad, fiyat, stok
        );
    }
}

/*
 * ============================================================
 * HAFTA 04 - LABORATUVAR GOREV 1
 * KONU: KAPSULLEME IHLALINI ONARMA
 * ============================================================
 *
 * 1. GOREVIN AMACI
 * ------------------------------------------------------------
 *
 * Urun sinifindaki alanlari kapsulleme ilkesine uygun
 * sekilde korumak ve gecersiz veri girisini engellemek.
 *
 * Urunun barkodu, adi, fiyati ve stogu bulunur.
 *
 * Bu bilgiler disaridan dogrudan degistirilemez.
 * Degisiklikler kontrollu metotlarla yapilir.
 *
 *
 * 2. PRIVATE VE FINAL
 * ------------------------------------------------------------
 *
 * private final String barkod;
 *
 * private:
 * Alana sinif disindan dogrudan erisimi engeller.
 *
 * final:
 * Ilk atamadan sonra yeniden atamayi engeller.
 *
 * Barkod constructor'da atanir ve daha sonra
 * degistirilemez.
 *
 * getBarkod() yalnizca okuma imkani saglar.
 *
 * private String ad;
 * private double fiyat;
 * private int stok;
 *
 * Bu alanlar private oldugu icin sadece sinifin
 * kontrollu metotlari tarafindan degistirilebilir.
 *
 *
 * 3. CONSTRUCTOR VE METOT TEKRARI
 * ------------------------------------------------------------
 *
 * Constructor nesne olusturulurken calisir.
 *
 * setAd(ad);
 * setFiyat(fiyat);
 * stokGir(stok);
 *
 * Bu metotlar dogrulama kurallarini icerir.
 *
 * Boylece ayni if kontrollerini hem constructor'da
 * hem de setter metotlarinda tekrar yazmayiz.
 *
 * Buna DRY (Don't Repeat Yourself) ilkesi denir.
 *
 * Bu cozumde baslangic stogu 0 olabilir.
 * Bu nedenle stok 0 ise stokGir(0) cagrilmaz.
 *
 * Negatif miktar ise stokGir() tarafindan reddedilir.
 *
 *
 * 4. DOGRULAMALI SETTER
 * ------------------------------------------------------------
 *
 * setAd(String ad):
 *
 * ad == null || ad.isBlank()
 *
 * Gecersiz adlari reddeder.
 *
 * isBlank():
 * Bos String veya yalnizca bosluklardan olusan
 * String degerleri icin true dondurur.
 *
 * setFiyat(double fiyat):
 *
 * fiyat < 0
 *
 * Negatif fiyatlari reddeder.
 * Sifir fiyat bu gorevde gecerlidir.
 *
 * Gecersiz istekte:
 *
 * System.out.println("RED: ...");
 * return;
 *
 * Uyari verilir ve atama yapilmadan metot sonlanir.
 *
 * DIKKAT:
 * throw new ... sonrasinda return yazilmaz.
 * throw zaten normal akisla devam etmez.
 *
 *
 * 5. STOK GIRIS VE CIKIS ISLEMLERI
 * ------------------------------------------------------------
 *
 * stokGir(int adet):
 *
 * - adet pozitif olmali.
 * - Gecerliyse stok += adet yapilir.
 *
 * stokCik(int adet):
 *
 * - adet pozitif olmali.
 * - adet mevcut stoktan fazla olmamali.
 * - Gecerliyse stok -= adet yapilir.
 *
 * Ornek:
 *
 * stok = 30
 *
 * stokCik(500);
 *
 * 500 > 30 oldugu icin islem reddedilir.
 * Stok 30 olarak kalir.
 *
 * stokGir(5);  -> stok = 35
 * stokCik(12); -> stok = 23
 *
 * NEDEN setStok() YOK?
 *
 * Stok, dogrudan atanan bir deger yerine
 * giris ve cikis islemleriyle yonetilir.
 *
 * Bu, BankaHesabi ornegindeki yatir() ve cek()
 * metotlariyla ayni mantiktir.
 *
 *
 * 6. TURETILMIS GETTER
 * ------------------------------------------------------------
 *
 * getStokDegeri():
 *
 * return fiyat * stok;
 *
 * Stok degeri ayri bir alanda tutulmaz.
 *
 * Ornek:
 *
 * fiyat = 45
 * stok = 23
 *
 * getStokDegeri() -> 1035
 *
 * Fiyat veya stok degistiginde hesaplanan
 * deger de otomatik olarak guncel olur.
 *
 * Bu, Sicaklik sinifindaki getFahrenheit()
 * metoduyla benzer bir tasarimdir.
 *
 *
 * 7. TOSTRING() VE DEMO
 * ------------------------------------------------------------
 *
 * @Override ile Object sinifindan gelen
 * toString() metodu ezilir.
 *
 * String.format() urunun bilgilerini okunabilir
 * bir metin halinde bicimlendirir.
 *
 * Gorev1_UrunDemo sinifindaki main() metodu:
 *
 * - Urun nesnesi olusturur.
 * - Gecersiz istekleri dener.
 * - Nesnenin degismedigini kontrol eder.
 * - Gecerli istekleri uygular.
 * - Son stok degerini yazdirir.
 *
 * Demo sinifi is kurallarini uygulamaz;
 * Urun sinifindaki metotlari test eder.
 *
 *
 * 8. SINAV ICIN ONEMLI NOKTALAR
 * ------------------------------------------------------------
 *
 * - Constructor yalnizca nesne olusturulurken calisir.
 * - Setter sonradan yapilan degisiklikleri de denetler.
 * - Ayni dogrulama kodunu iki yerde yazma.
 * - private ile final ayni anlama gelmez.
 * - Getter yazmak setter yazmayi zorunlu kilmaz.
 * - Stok islemleri icin anlamli metotlar kullan.
 * - adet <= 0 kontrolu sifiri da reddeder.
 * - return; void metodu hemen sonlandirir.
 * - Gecersiz istek nesnenin durumunu degistirmemelidir.
 * - Turetilmis bilgi gereksiz yere alanda tutulmaz.
 * - Java'da sinif adi ve dosya adi uyumlu olmalidir.
 *
 *
 * GENEL SONUC:
 *
 * KAPSULLEME SADECE ALANLARI PRIVATE YAPMAK DEGILDIR.
 *
 * Nesnenin verisini korumak, gecersiz degisiklikleri
 * engellemek ve is kurallarini sinifin icinde
 * toplamak da kapsullemenin temel amacidir.
 *
 * ============================================================
 */
    