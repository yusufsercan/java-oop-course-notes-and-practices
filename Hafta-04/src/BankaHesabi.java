/*
 * Nesne Yonelimli Programlama - Hafta 04
 * Ornek 8: Is kurali setter'da degil, ANLAMLI METOTTA
 *
 * setBakiye() yazmak kapsullemeyi bozar: cagiran taraf istedigi degeri
 * verebilirdi. Bunun yerine bakiyeyi degistir	 	en iki ANLAMLI islem sunulur:
 * yatir() ve cek(). Kural (pozitif tutar, yeterli bakiye, gunluk limit)
 * bu metotlarin icindedir.
 */
public class BankaHesabi 
{

    public static final double GUNLUK_CEKIM_LIMITI = 2000.0;

    private final String iban;
    private final String sahip;
    private double bakiye;
    private double bugunCekilen;

    public BankaHesabi(String iban, String sahip, double acilis) // buradaki acilis parametresi, hesabin ilk bakiyesini belirler. Ancak, negatif bir acilis bakiyesi olamaz; bu nedenle, constructor icinde acilis degeri sifir veya pozitif olarak ayarlanir. Bu sayede, BankaHesabi nesnesi olusturuldugunda her zaman gecerli bir bakiye ile baslar.
    {
        this.iban = iban;
        this.sahip = sahip;
        this.bakiye = (acilis > 0) ? acilis : 0.0; // acilis bakiyesi negatif olamaz, sifirla baslatilir onun kontrolu burada yapilir.
        this.bugunCekilen = 0.0;
    }

    public String getIban() 
    {
        return iban;
    }

    public String getSahip() 
    {
        return sahip;
    }

    /* Yalnizca OKUMA. setBakiye() BILEREK yazilmamistir. */
    public double getBakiye() 
    {
        return bakiye;
    }

    public double getKalanGunlukHak() 
    {
        return GUNLUK_CEKIM_LIMITI - bugunCekilen;// burada GUNLUK_CEKIM_LIMITI sabiti ile bugunCekilen degiskeni arasindaki fark hesaplanir ve kalan gunluk cekim hakki olarak dondurulur. Bu sayede, kullanici gunluk limitini asip asmadigini kontrol edebilir ve gerekli islemleri yapabilir.
    }

    public boolean yatir(double tutar) // bu metot, kullanicinin hesaba para yatirma islemini gerceklestirir. Metot, yatirilacak tutarin gecerli olup olmadigini kontrol eder ve eger gecerliyse bakiye degiskenini gunceller. Metot, islemin basarili olup olmad
    {
        if (tutar <= 0) 
        {
            System.out.println("  RED: yatirilan tutar pozitif olmalidir.");
            return false;
        }
        bakiye += tutar;// burada bakiye degiskeni, yatirilan tutar kadar artirilir. Bu sayede, kullanici hesaba para yatirdiginda bakiye guncellenir ve dogru bir sekilde tutulur.
        System.out.printf("  OK : %.2f yatirildi. Bakiye = %.2f%n", tutar, bakiye);
        return true;
    }

    public boolean cek(double tutar) // bu metot, kullanicinin hesaptan para cekme islemini gerceklestirir. Metot, cekilecek tutarin gecerli olup olmadigini kontrol eder ve eger gecerliyse bakiye ve bugunCekilen degiskenlerini gunceller. Metot, islemin basarili olup olmadigini belirten bir boolean deger dondurur.
    {
        if (tutar <= 0) 
        {
            System.out.println("  RED: cekilen tutar pozitif olmalidir.");
            return false;
        }
        if (tutar > bakiye) 
        {
            System.out.printf("  RED: yetersiz bakiye (%.2f > %.2f).%n", tutar, bakiye);
            return false;
        }
        if (bugunCekilen + tutar > GUNLUK_CEKIM_LIMITI) 
        {
            System.out.printf("  RED: gunluk limit asilir (%.2f + %.2f > %.2f).%n",
                    tutar, bugunCekilen, GUNLUK_CEKIM_LIMITI);
            return false;
        }
        bakiye -= tutar;// burada bakiye degiskeni, cekilen tutar kadar azaltılır. Bu sayede, kullanici hesaptan para cektiginde bakiye guncellenir ve dogru bir sekilde tutulur.
        bugunCekilen += tutar;// burada bugunCekilen degiskeni, cekilen tutar kadar artirilir. Bu sayede, kullanici gunluk limitini asip asmadigini kontrol edebilir ve gerekli islemleri yapabilir.
        System.out.printf("  OK : %.2f cekildi. Bakiye = %.2f%n", tutar, bakiye);
        return true;
    }

    @Override
    public String toString() 
    {
        return String.format("Hesap[%s, %s, bakiye=%.2f]", iban, sahip, bakiye);
    }
}


/*
 * ============================================================
 * HAFTA 04 - BANKAHESABI.JAVA
 * KONU: KAPSULLEME, IS METOTLARI VE VERI DOGRULAMA
 * ============================================================
 *
 * 1. KODUN AMACI
 * ------------------------------------------------------------
 *
 * BankaHesabi sinifi, bir banka hesabinin bakiyesini
 * belirli is kurallarina gore yonetmek icin tasarlanmistir.
 *
 * Temel amac:
 * Bakiye gibi onemli bir alanin disaridan dogrudan
 * degistirilmesini engellemek ve degisiklikleri
 * kontrollu metotlar araciligiyla gerceklestirmek.
 *
 * Bu sinifta setBakiye() BILEREK yazilmamistir.
 *
 * Bunun yerine:
 * - yatir() -> Para yatirma islemini gerceklestirir.
 * - cek()   -> Para cekme islemini gerceklestirir.
 *
 * Bu yaklasim kapsulleme (encapsulation) ilkesine
 * uygun bir tasarimdir.
 *
 *
 * 2. ALANLAR VE ERISIM BELIRLEYICILERI
 * ------------------------------------------------------------
 *
 * public static final double GUNLUK_CEKIM_LIMITI = 2000.0;
 *
 * public -> Disaridan erisilebilir.
 * static -> Sabit, nesnelere degil sinifa aittir.
 * final  -> Ilk atamadan sonra degistirilemez.
 *
 * private final String iban;
 * private final String sahip;
 *
 * Bu alanlar yapicida atanir ve daha sonra
 * yeniden atanamaz.
 *
 * private double bakiye;
 * private double bugunCekilen;
 *
 * Bu iki alan private oldugu icin disaridan
 * dogrudan degistirilemez.
 *
 * Ancak final olmadiklari icin sinifin kendi
 * metotlari tarafindan guncellenebilirler.
 *
 *
 * 3. CONSTRUCTOR (YAPICI METOT)
 * ------------------------------------------------------------
 *
 * public BankaHesabi(String iban, String sahip, double acilis)
 *
 * Nesne olusturulurken:
 * 1. IBAN atanir.
 * 2. Hesap sahibi atanir.
 * 3. Acilis bakiyesi kontrol edilir.
 * 4. bugunCekilen sifirlanir.
 *
 * this.bakiye = (acilis > 0) ? acilis : 0.0;
 *
 * Buradaki ?: operatoru TERNARY OPERATOR olarak bilinir.
 *
 * Kosul dogruysa acilis degeri kullanilir.
 * Yanlissa bakiye 0.0 olur.
 *
 * Ornek:
 * acilis = 1000 -> bakiye = 1000
 * acilis = -100 -> bakiye = 0
 *
 * DIKKAT:
 * Gecersiz acilis tutari istisna firlatilmadan
 * sifira donusturulmektedir. Bu, ornegin tasarimidir.
 *
 *
 * 4. GETTER METOTLARI
 * ------------------------------------------------------------
 *
 * getIban()   -> IBAN bilgisini okur.
 * getSahip()  -> Hesap sahibini okur.
 * getBakiye() -> Guncel bakiyeyi okur.
 *
 * Bu metotlar hesap bilgilerini degistirmez.
 *
 * getKalanGunlukHak():
 *
 * return GUNLUK_CEKIM_LIMITI - bugunCekilen;
 *
 * Kalan gunluk para cekme hakkini hesaplar.
 *
 * Ornek:
 * Gunluk limit  = 2000
 * Bugun cekilen = 1500
 * Kalan hak     = 500
 *
 * ONEMLI:
 * Kalan hak ayri bir alanda tutulmaz.
 * Her cagrida mevcut bilgilerden hesaplanir.
 *
 * Buna TURETILMIS GETTER denir.
 *
 *
 * 5. YATIR() METODU
 * ------------------------------------------------------------
 *
 * public boolean yatir(double tutar)
 *
 * Metot para yatirma islemini gerceklestirir.
 *
 * ADIM 1:
 * if (tutar <= 0)
 *
 * Yatirilacak miktarin pozitif olup olmadigi
 * kontrol edilir.
 *
 * ADIM 2:
 * Gecersizse return false ile metot sonlandirilir.
 *
 * ADIM 3:
 * Gecerliyse bakiye += tutar; calisir.
 *
 * += operatoru:
 *
 * bakiye += tutar;
 * bakiye = bakiye + tutar;
 *
 * Iki ifade de ayni islemi gerceklestirir.
 *
 * ADIM 4:
 * return true ile basarili sonuc dondurulur.
 *
 * DIKKAT:
 * yatir() metodu bugunCekilen alanini DEGISTIRMEZ.
 * Para yatirmak gunluk cekim hakkini artirmaz.
 *
 *
 * 6. CEK() METODU
 * ------------------------------------------------------------
 *
 * public boolean cek(double tutar)
 *
 * Para cekme isleminden once UC KURAL denetlenir.
 *
 * KONTROL 1 - POZITIF TUTAR:
 *
 * if (tutar <= 0)
 *
 * Sifir veya negatif miktarlar reddedilir.
 *
 * KONTROL 2 - YETERLI BAKIYE:
 *
 * if (tutar > bakiye)
 *
 * Hesapta yeterli para yoksa islem reddedilir.
 *
 * KONTROL 3 - GUNLUK LIMIT:
 *
 * if (bugunCekilen + tutar > GUNLUK_CEKIM_LIMITI)
 *
 * Gunluk limitin asilip asilmadigi kontrol edilir.
 *
 * Butun kontroller basariliysa:
 *
 * bakiye -= tutar;
 * bugunCekilen += tutar;
 *
 * Bakiye azalir, gunluk cekilen miktar artar.
 *
 * SONUC:
 * Basarili islem -> true
 * Basarisiz islem -> false
 *
 * COK ONEMLI:
 * Kontroller, degisiklik yapilmadan ONCE uygulanir.
 *
 * Herhangi bir kontrol basarisiz olursa return false
 * calisir ve alanlarin eski degerleri korunur.
 *
 * Bu, nesnenin gecerli durumunu korumak icin onemlidir.
 *
 *
 * 7. PEKISTIRME - ISLEM SIRASI
 * ------------------------------------------------------------
 *
 * Baslangic:
 * bakiye = 1500
 * gunluk limit = 2000
 * bugunCekilen = 0
 *
 * h.cek(1200);
 *
 * SONUC:
 * bakiye = 300
 * bugunCekilen = 1200
 * kalan hak = 800
 *
 * h.yatir(800);
 *
 * SONUC:
 * bakiye = 1100
 * bugunCekilen = 1200
 * kalan hak = 800
 *
 * h.cek(900);
 *
 * SONUC: RED
 *
 * NEDEN?
 * 1200 + 900 = 2100
 * 2100 > 2000 oldugu icin limit asilir.
 *
 * Bakiye ve bugunCekilen DEGISEMEZ.
 *
 * h.cek(500);
 *
 * SONUC:
 * bakiye = 600
 * bugunCekilen = 1700
 * kalan hak = 300
 *
 *
 * 8. SINIR DEGER - > VE >= FARKI
 * ------------------------------------------------------------
 *
 * if (bugunCekilen + tutar > 2000)
 *
 * Ornek:
 * 1700 + 300 > 2000
 * 2000 > 2000 -> FALSE
 *
 * Bu nedenle 300 TL cekilebilir.
 * Gunluk limite TAM OLARAK ulasmak serbesttir.
 *
 * Ancak >= kullansaydik:
 *
 * 2000 >= 2000 -> TRUE
 *
 * Bu kez limite tam ulasan islem de reddedilirdi.
 *
 * DIKKAT:
 * >  -> Yalnizca buyukse true
 * >= -> Buyuk veya esitse true
 *
 * SINAV TUZAGI:
 * Limitin asilmasi ile limite esit olunmasi
 * AYNI SEY DEGILDIR.
 *
 *
 * 9. BAKIYE VE GUNLUK HAK FARKI
 * ------------------------------------------------------------
 *
 * Varsayalim:
 *
 * bakiye = 300
 * kalan gunluk hak = 0
 *
 * h.cek(1);     -> false
 * h.yatir(500); -> true
 * h.cek(100);   -> false
 *
 * SON DURUM:
 * bakiye = 800
 * kalan gunluk hak = 0
 *
 * NEDEN?
 *
 * Para yatirmak sadece bakiyeyi artirir.
 * Gunluk limit yeniden acilmaz.
 *
 * Hesapta para bulunmasi, o paranin tamamini
 * gunluk limit kapsaminda cekebilecegimiz
 * anlamina gelmez.
 *
 * Basarili cekim icin hem bakiye hem de
 * gunluk limit kosullari saglanmalidir.
 *
 *
 * 10. SETTER MI, IS METODU MU?
 * ------------------------------------------------------------
 *
 * KOTU TASARIM:
 *
 * hesap.setBakiye(100000);
 *
 * Bu islem, para yatirma veya cekme kurallarini
 * atlayarak bakiyeyi dogrudan degistirebilir.
 *
 * DAHA IYI TASARIM:
 *
 * hesap.yatir(500);
 * hesap.cek(200);
 *
 * Bu metotlar:
 * - Gerceklesen islemi aciklar.
 * - Gecersiz degerleri denetler.
 * - Is kurallarini sinifin icinde toplar.
 * - Bakiyenin kontrolsuz degismesini engeller.
 *
 * Gercek bir uygulamada yetkilendirme kontrolleri
 * de gerekir. Kapsulleme tek basina tum guvenlik
 * problemlerini cozmez.
 *
 *
 * 11. TOSTRING() METODU
 * ------------------------------------------------------------
 *
 * @Override
 * public String toString()
 *
 * Nesnenin ekrana nasil yazdirilacagini belirler.
 *
 * String.format(...) kullanilarak IBAN, hesap sahibi
 * ve bakiye bir metin halinde dondurulur.
 *
 * %.2f -> Iki ondalik basamakli sayi.
 * %s   -> String degeri.
 *
 *
 * 12. SINAVDA DIKKAT EDILECEKLER
 * ------------------------------------------------------------
 *
 * - private, disaridan dogrudan erisimi kisitlar.
 * - final, alana ikinci kez atama yapilmasini engeller.
 * - static, uyeyi sinif duzeyine tasir.
 * - Getter her zaman setter gerektirmez.
 * - Her degisiklik icin setter yazilmaz.
 * - yatir() ve cek() birer IS METODUDUR.
 * - return false metottan hemen cikar.
 * - Basarisiz islemde alanlar degismemelidir.
 * - boolean metotlar true veya false dondurur.
 * - Kalan gunluk hak, hesaplanan bir bilgidir.
 * - > ve >= arasindaki farki unutma.
 * - Bakiye ile kalan gunluk limit ayni bilgi degildir.
 *
 * EK MUHENDISLIK NOTLARI:
 *
 * - Bu ornekte gun degisince bugunCekilen alanini
 *   sifirlayan bir mekanizma bulunmuyor.
 *
 * - Gercek finans uygulamalarinda double yerine
 *   uygun para temsili tercih edilir.
 *
 * - Double.NaN ve Infinity gibi ozel degerler icin
 *   ek dogrulama gerekir.
 *
 *
 * GENEL SONUC:
 *
 * KAPSULLEME = VERIYI GIZLEMEK + IS KURALINI KORUMAK
 *
 * Bir sinif kendi verisinin dogrulugundan sorumlu
 * olmali ve disariya yalnizca gerekli islemleri acmalidir.
 *
 * ============================================================
 */
