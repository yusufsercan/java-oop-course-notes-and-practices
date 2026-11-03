/*
 * Nesne Yonelimli Programlama - Hafta 04
 * Ornek 4: Dogrulamali setter ve turetilmis getter
 *
 * Fiziksel bir kural vardir: -273.15 C'nin altina inilemez. Bu kural sinifin
 * ICINDE tutulur; sinifi kullanan hicbir kod bu kurali tekrar yazmak zorunda
 * kalmaz ve unutamaz.
 */
public class Sicaklik 
{

    public static final double MUTLAK_SIFIR = -273.15; // sabit alan, sinifin her yerinden erisilebilir ve degistirilemez. Bu sayede, mutlak sifir degeri tek bir yerde tanimlanir ve diger kodlar bu degeri dogrudan kullanabilir.

    private double celsius;
    private double enDusuk;
    private double enYuksek;

    public Sicaklik(double celsius) 
    {
        this.celsius = MUTLAK_SIFIR;// celsius alani, constructor baslangicinda mutlak sifir degeri ile baslatilir. Bu sayede, nesne olusturuldugunda gecersiz bir deger atanamaz.
        this.enDusuk = Double.MAX_VALUE;// enDusuk alani, constructor baslangicinda maksimum deger ile baslatilir. Bu sayede, ilk setCelsius() cagrisi ile karsilastirildiginda, enDusuk degeri dogru bir sekilde guncellenir.
        this.enYuksek = -Double.MAX_VALUE;// enYuksek alani, constructor baslangicinda minimum deger ile baslatilir. Bu sayede, ilk setCelsius() cagrisi ile karsilastirildiginda, enYuksek degeri dogru bir sekilde guncellenir.
        // başında - olmasının nedeni, Double.MIN_VALUE'nin pozitif en küçük değer olmasıdır. Bu nedenle, enYuksek alanını başlatmak için negatif maksimum değer kullanılır.
        setCelsius(celsius);// constructor, setCelsius() metodunu cagirir. Bu sayede, nesne olusturulurken deger dogrulama kurallari uygulanir ve mutlak sifirin altinda bir deger atanamaz.
    }

    public double getCelsius() 
    {
        return celsius;
    }

    public void setCelsius(double yeni) // setCelsius() metodu, sicaklik degerini degistirmek icin kullanilir. Bu metod, mutlak sifirin altinda bir deger atanmasini engellemek icin dogrulama yapar. Eger yeni deger mutlak sifirin altindaysa, deger degistirilmez ve bir mesaj yazdirilir. 
                                        //Aksi takdirde, celsius alani yeni degerle guncellenir ve enDusuk ve enYuksek alanlari da gerekli durumlarda guncellenir.
                                        // buradaki yeni aslında girdiğimiz celsius değeridir. Bu değer mutlak sıfırın altında ise, celsius alanı değiştirilmez ve bir uyarı mesajı yazdırılır. Eğer değer geçerliyse, celsius alanı güncellenir ve enDusuk ve enYuksek alanları da gerekli durumlarda güncellenir.
    {
        if (yeni < MUTLAK_SIFIR) {
            System.out.printf("  RED: %.2f C mutlak sifirin altinda; deger degismedi.%n",
                    yeni);
            return;
        }
        this.celsius = yeni;
        if (yeni < enDusuk) 
        {
            enDusuk = yeni;
        }
        if (yeni > enYuksek) 
        {
            enYuksek = yeni;
        }
    }

    /* Fahrenheit ve Kelvin icin ALAN YOKTUR; ihtiyac aninda hesaplanir.
       Boylece iki alanin birbiriyle celismesi imkansiz hale gelir. */
    public double getFahrenheit() 
    {
        return celsius * 9.0 / 5.0 + 32.0;
    }

    public double getKelvin() 
    {
        return celsius - MUTLAK_SIFIR;
    }

    public void setFahrenheit(double f) 
    {
        setCelsius((f - 32.0) * 5.0 / 9.0);
    }

    public double getEnDusuk() 
    {
        return enDusuk;
    }

    public double getEnYuksek() 
    {
        return enYuksek;
    }

    @Override
    public String toString() 
    {
        return String.format("%.2f C = %.2f F = %.2f K", celsius, getFahrenheit(),
                getKelvin());
    }
}

/*
 * ============================================================
 * HAFTA 04 - SICAKLIK.JAVA
 * KONU: KAPSULLEME, DOGRULAMALI SETTER, TURETILMIS GETTER
 * ============================================================
 *
 * 1. KODUN AMACI
 * ------------------------------------------------------------
 *
 * Sicaklik sinifi, sicaklik degerlerini belirli kurallara
 * uygun sekilde saklamak ve yonetmek icin tasarlanmistir.
 *
 * Sinifin temel amaclari:
 *
 * - Mutlak sifirin altinda sicaklik atanmasini engellemek.
 * - Guncel sicakligi Celsius cinsinden saklamak.
 * - Fahrenheit ve Kelvin degerlerini ihtiyac aninda hesaplamak.
 * - Simdiye kadar kabul edilen en dusuk ve en yuksek
 *   sicaklik degerlerini takip etmek.
 *
 * Bu ornekte kapsulleme ve veri tutarliligi on plandadir.
 *
 *
 * 2. ALANLARIN GOREVLERI
 * ------------------------------------------------------------
 *
 * public static final double MUTLAK_SIFIR = -273.15;
 *
 * public -> Disaridan erisilebilir.
 * static -> Sabit, nesneye degil sinifa aittir.
 * final  -> Deger yeniden atanamaz.
 *
 * private double celsius;
 * private double enDusuk;
 * private double enYuksek;
 *
 * celsius  -> GUNCEL sicaklik.
 * enDusuk  -> Kabul edilen EN DUSUK sicaklik.
 * enYuksek -> Kabul edilen EN YUKSEK sicaklik.
 *
 * Bu uc bilgi birbirinden farklidir.
 *
 * Ornek:
 *
 * Sicaklik s = new Sicaklik(20);
 * s.setCelsius(35);
 * s.setCelsius(10);
 *
 * SONUC:
 *
 * celsius  = 10
 * enDusuk  = 10
 * enYuksek = 35
 *
 * Guncel sicaklik degistiginde eski en yuksek
 * degerin silinmesi gerekmez.
 *
 *
 * 3. CONSTRUCTOR (YAPICI METOT)
 * ------------------------------------------------------------
 *
 * public Sicaklik(double celsius)
 *
 * Nesne olusturulurken alanlara baslangic degerleri atanir.
 *
 * this.celsius = MUTLAK_SIFIR;
 * this.enDusuk = Double.MAX_VALUE;
 * this.enYuksek = -Double.MAX_VALUE;
 *
 * Double.MAX_VALUE:
 * double turunun en buyuk pozitif sonlu degeridir.
 *
 * -Double.MAX_VALUE:
 * Bunun negatifidir.
 *
 * NEDEN BU DEGERLER KULLANILIYOR?
 *
 * Ilk gecerli sicakligin hem en dusuk hem en yuksek
 * olarak kaydedilebilmesini saglamak icin.
 *
 * Ornek:
 *
 * Sicaklik s = new Sicaklik(20);
 *
 * setCelsius(20) constructor icinde cagrilir.
 *
 * 20 < Double.MAX_VALUE -> true
 * 20 > -Double.MAX_VALUE -> true
 *
 * Boylece:
 *
 * celsius  = 20
 * enDusuk  = 20
 * enYuksek = 20
 *
 * DIKKAT:
 * Constructor dogrudan celsius alanini guncellemek
 * yerine setCelsius() metodunu cagirir.
 *
 * Bu sayede dogrulama kurali tek yerde kullanilir.
 *
 *
 * 4. SETCELSIUS() - DOGRULAMALI SETTER
 * ------------------------------------------------------------
 *
 * public void setCelsius(double yeni)
 *
 * Metodun amaci yeni sicakligi kontrol ederek atamaktir.
 *
 * if (yeni < MUTLAK_SIFIR)
 * {
 *     // Uyari yazdirilir.
 *     return;
 * }
 *
 * Mutlak sifir: -273.15 Celsius
 *
 * Ornek:
 *
 * setCelsius(-300);
 *
 * -300 < -273.15 -> true
 *
 * Bu nedenle metot uyaridan sonra sonlanir.
 * Sicaklik degeri DEGISTIRILMEZ.
 *
 * return;
 *
 * void metotlarda deger dondurmeden metodu sonlandirir.
 *
 * Deger gecerliyse:
 *
 * this.celsius = yeni;
 *
 * Ardindan:
 *
 * if (yeni < enDusuk)
 *     enDusuk = yeni;
 *
 * if (yeni > enYuksek)
 *     enYuksek = yeni;
 *
 * Boylece gecmis minimum ve maksimum degerler
 * gerektiğinde guncellenir.
 *
 *
 * 5. TURETILMIS GETTER MANTIGI
 * ------------------------------------------------------------
 *
 * public double getFahrenheit()
 * {
 *     return celsius * 9.0 / 5.0 + 32.0;
 * }
 *
 * public double getKelvin()
 * {
 *     return celsius - MUTLAK_SIFIR;
 * }
 *
 * Fahrenheit ve Kelvin icin AYRI ALAN tutulmaz.
 *
 * Her iki deger de guncel Celsius sicakligindan hesaplanir.
 *
 * Ornek:
 *
 * celsius = 0
 *
 * Fahrenheit = 0 * 9 / 5 + 32 = 32
 * Kelvin     = 0 - (-273.15)  = 273.15
 *
 * NEDEN AYRI ALAN TUTMUYORUZ?
 *
 * Eger celsius, fahrenheit ve kelvin degerlerini
 * ayri alanlarda tutsaydik, birini degistirdigimizde
 * digerlerini guncellemeyi unutabilirdik.
 *
 * Bu durumda veriler birbiriyle celisebilirdi.
 *
 * TEK DOGRULUK KAYNAGI:
 *
 * Celsius saklanir.
 * Fahrenheit ve Kelvin ihtiyac aninda hesaplanir.
 *
 * NOT:
 * enDusuk ve enYuksek gereksiz tekrar degildir.
 * Bunlar gecmis olcumleri temsil eden ayri bilgilerdir.
 *
 *
 * 6. SETFAHRENHEIT() METODU
 * ------------------------------------------------------------
 *
 * public void setFahrenheit(double f)
 * {
 *     setCelsius((f - 32.0) * 5.0 / 9.0);
 * }
 *
 * Bu metot Fahrenheit degerini Celsius'a donusturur.
 *
 * Daha sonra setCelsius() metodunu cagirir.
 *
 * Ornek:
 *
 * setFahrenheit(32);
 *
 * (32 - 32) * 5 / 9 = 0 Celsius
 *
 * Aslinda setCelsius(0) cagrilmis olur.
 *
 * ONEMLI:
 *
 * Mutlak sifir kontrolunu burada yeniden yazmiyoruz.
 * Kontrol setCelsius() icinde yapiliyor.
 *
 * Boylece ayni is kurali birden fazla yere
 * kopyalanmamis oluyor.
 *
 *
 * 7. PEKISTIRME - BIRINCI ORNEK
 * ------------------------------------------------------------
 *
 * Sicaklik s = new Sicaklik(20);
 *
 * s.setCelsius(35);
 * s.setFahrenheit(32);
 * s.setCelsius(-300);
 *
 * ADIM 1:
 * Baslangic sicakligi 20.
 *
 * celsius = 20, enDusuk = 20, enYuksek = 20
 *
 * ADIM 2:
 * setCelsius(35)
 *
 * celsius = 35, enDusuk = 20, enYuksek = 35
 *
 * ADIM 3:
 * setFahrenheit(32)
 *
 * 32 Fahrenheit = 0 Celsius
 *
 * celsius = 0, enDusuk = 0, enYuksek = 35
 *
 * ADIM 4:
 * setCelsius(-300)
 *
 * Mutlak sifirin altinda oldugu icin reddedilir.
 * Degerler degismez.
 *
 * SONUC:
 *
 * getCelsius()  -> 0
 * getEnDusuk()  -> 0
 * getEnYuksek() -> 35
 * getKelvin()   -> 273.15
 *
 *
 * 8. PEKISTIRME - IKINCI ORNEK
 * ------------------------------------------------------------
 *
 * Sicaklik s = new Sicaklik(-10);
 *
 * s.setCelsius(15);
 * s.setFahrenheit(14);
 * s.setCelsius(-280);
 *
 * ADIM 1:
 * Baslangic -10 Celsius.
 *
 * celsius = -10, enDusuk = -10, enYuksek = -10
 *
 * ADIM 2:
 * setCelsius(15)
 *
 * celsius = 15, enDusuk = -10, enYuksek = 15
 *
 * ADIM 3:
 * setFahrenheit(14)
 *
 * (14 - 32) * 5 / 9 = -10 Celsius
 *
 * celsius = -10, enDusuk = -10, enYuksek = 15
 *
 * ADIM 4:
 * setCelsius(-280)
 *
 * Gecersiz deger oldugu icin reddedilir.
 *
 * SONUC:
 *
 * getCelsius()  -> -10
 * getEnDusuk()  -> -10
 * getEnYuksek() -> 15
 *
 * DIKKAT:
 * Guncel sicaklik ile en yuksek sicaklik
 * her zaman ayni olmak zorunda degildir.
 *
 *
 * 9. TOSTRING() METODU
 * ------------------------------------------------------------
 *
 * @Override
 * public String toString()
 *
 * Nesneyi okunabilir metin halinde temsil eder.
 *
 * String.format("%.2f C = %.2f F = %.2f K", ...)
 *
 * %.2f -> Iki ondalik basamakla bicimlendirir.
 *
 * Ornek:
 *
 * 0.00 C = 32.00 F = 273.15 K
 *
 * Ondalik ayirici sistemin dil ayarina gore degisebilir.
 *
 *
 * 10. SINAVDA DIKKAT EDILECEKLER
 * ------------------------------------------------------------
 *
 * - private alanlara dogrudan disaridan erisilemez.
 *
 * - final sabitlerin yeniden atanmasini engeller.
 *
 * - Constructor, dogrulama yapan setter'i cagirabilir.
 *
 * - void metotta return; kullanilabilir.
 *
 * - return; metodu hemen sonlandirir.
 *
 * - Gecersiz sicaklik nesnenin durumunu degistirmemelidir.
 *
 * - getFahrenheit() ve getKelvin() turetilmis getter'dir.
 *
 * - Celsius tek kaynak olarak saklanir.
 *
 * - setFahrenheit() dogrudan alan degistirmek yerine
 *   setCelsius() metodunu cagirir.
 *
 * - enDusuk ve enYuksek gecmis degerleri takip eder.
 *
 * - Double.MAX_VALUE ilk minimumu belirlemeye,
 *   -Double.MAX_VALUE ilk maksimumu belirlemeye yardim eder.
 *
 * - if kosullarinda <, >, <= ve >= farklarini unutma.
 *
 *
 * 11. EK MUHENDISLIK NOTU
 * ------------------------------------------------------------
 *
 * Hocanin bu orneginde, constructor'a mutlak sifirin
 * altinda bir deger verilirse setCelsius() istegi reddeder.
 *
 * Fakat bu durumda enDusuk ve enYuksek ilk olcumle
 * guncellenmeden kalir.
 *
 * Ayrica Double.NaN gibi ozel degerler mevcut
 * karsilastirma kosullarindan gecerek sorun olusturabilir.
 *
 * Gercek uygulamada bu tur girdiler icin ek dogrulama
 * ve gerekirse istisna firlatma tercih edilmelidir.
 *
 *
 * GENEL SONUC:
 *
 * KAPSULLEME:
 * Nesnenin durumunu kontrollu metotlarla yonetmek.
 *
 * DOGRULAMALI SETTER:
 * Gecersiz degerlerin nesneye atanmasini engellemek.
 *
 * TURETILMIS GETTER:
 * Saklanan bilgilerden yeni bilgi hesaplamak.
 *
 * TEK DOGRULUK KAYNAGI:
 * Ayni bilgiyi birden fazla alanda gereksiz yere
 * saklamayarak veri tutarsizligini onlemek.
 *
 * ============================================================
 */
