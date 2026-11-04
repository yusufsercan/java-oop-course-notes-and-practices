public class KanBasinci
{
    public static final int EN_DUSUK = 40;
    public static final int EN_YUKSEK = 250;

    private int buyuk;
    private int kucuk;

    // Constructor
    public KanBasinci(int buyuk, int kucuk)
    {
        // Gecerli baslangic durumu
        this.buyuk = 120;
        this.kucuk = 80;

        // Gelen olcum dogrulanir
        ayarla(buyuk, kucuk);
    }

    // Iki tansiyon degerini birlikte kontrol eden metot
    public boolean ayarla(int yeniBuyuk, int yeniKucuk)
    {
        // 1. KONTROL: Her iki deger de 40-250 araliginda mi?
        if (yeniBuyuk < EN_DUSUK || yeniBuyuk > EN_YUKSEK ||
            yeniKucuk < EN_DUSUK || yeniKucuk > EN_YUKSEK)
        {
            System.out.println(
                "  RED: degerler 40..250 araliginda olmalidir."
            );
            return false;
        }

        // 2. KONTROL: Buyuk tansiyon kucukten kesinlikle buyuk mu?
        if (yeniBuyuk <= yeniKucuk)
        {
            System.out.printf(
                "  RED: buyuk tansiyon kucukten buyuk olmalidir (%d <= %d).%n",
                yeniBuyuk, yeniKucuk
            );
            return false;
        }

        // Butun kontroller gecildiyse iki alan birlikte guncellenir.
        this.buyuk = yeniBuyuk;
        this.kucuk = yeniKucuk;

        return true;
    }

    // Hocanin verdigi siniflandirma tablosu
    public String degerlendir()
    {
        if (this.buyuk < 120 && this.kucuk < 80)
        {
            return "NORMAL";
        }
        else if (this.buyuk < 130 && this.kucuk < 80)
        {
            return "YUKSEK-NORMAL";
        }
        else if (this.buyuk < 140 || this.kucuk < 90)
        {
            return "EVRE-1";
        }
        else
        {
            return "EVRE-2";
        }
    }

    // Turetilmis getter
    public int getNabizBasinci()
    {
        return this.buyuk - this.kucuk;
    }

    public int getBuyuk()
    {
        return this.buyuk;
    }

    public int getKucuk()
    {
        return this.kucuk;
    }

    @Override
    public String toString()
    {
        return String.format(
            "%d/%d mmHg (%s)",
            this.buyuk, this.kucuk, degerlendir()
        );
    }
}

/*
 * ============================================================
 * HAFTA 04 - LABORATUVAR GOREV 2
 * KONU: IKI ALAN ARASINDA TUTARLILIK KURALI
 * ============================================================
 *
 * 1. GOREVIN AMACI
 * ------------------------------------------------------------
 *
 * KanBasinci sinifi, buyuk ve kucuk tansiyon
 * degerlerini temsil eder.
 *
 * Temel amac:
 *
 * Iki alanin birbirleriyle olan iliskisini kontrol
 * ederek nesnenin her zaman gecerli durumda kalmasini
 * saglamaktir.
 *
 * KURAL:
 * Buyuk tansiyon, kucuk tansiyondan kesinlikle
 * buyuk olmalidir.
 *
 * Bu nedenle iki alan ayri setter metotlariyla
 * degil, tek bir ayarla() metodu ile guncellenir.
 *
 *
 * 2. PRIVATE ALANLAR VE SABITLER
 * ------------------------------------------------------------
 *
 * public static final int EN_DUSUK = 40;
 * public static final int EN_YUKSEK = 250;
 *
 * public -> Sinif disindan erisilebilir.
 * static -> Sabitler sinifin kendisine aittir.
 * final  -> Degerleri sonradan degistirilemez.
 *
 * private int buyuk;
 * private int kucuk;
 *
 * Bu iki alan private oldugu icin disaridan
 * dogrudan degistirilemez.
 *
 *
 * 3. CONSTRUCTOR VE THIS
 * ------------------------------------------------------------
 *
 * public KanBasinci(int buyuk, int kucuk)
 *
 * this.buyuk = 120;
 * this.kucuk = 80;
 * ayarla(buyuk, kucuk);
 *
 * this.buyuk:
 * Nesnenin buyuk alanini ifade eder.
 *
 * buyuk:
 * Constructor'a gelen parametredir.
 *
 * DIKKAT:
 *
 * buyuk = 120;
 *
 * yazarsak nesnenin alani yerine parametreyi
 * degistirmis oluruz.
 *
 * this.buyuk = 120;
 *
 * yazarsak nesnenin alanini degistiririz.
 *
 * NEDEN ONCE 120/80?
 *
 * Constructor'a gecersiz bir olcum gelse bile
 * nesnenin 0/0 gibi gecersiz durumda kalmasini
 * engellemek icin.
 *
 * Daha sonra ayarla() gelen olcumu kontrol eder.
 *
 *
 * 4. AYARLA() METODU
 * ------------------------------------------------------------
 *
 * public boolean ayarla(int yeniBuyuk, int yeniKucuk)
 *
 * Iki degeri birlikte kontrol eder.
 *
 * KONTROL 1:
 *
 * Her iki deger de 40 ile 250 arasinda olmali.
 * 40 ve 250 sinirlari dahildir.
 *
 * <  ve > kontrolleri aralik disindaki degerleri
 * reddetmek icin kullanilir.
 *
 * || -> VEYA operatorudur.
 *
 * Kosullardan biri bile gecersizse islem reddedilir.
 *
 * KONTROL 2:
 *
 * yeniBuyuk <= yeniKucuk
 *
 * Buyuk deger kucukten buyuk degilse
 * islem reddedilir.
 *
 * Ornek:
 *
 * 90/130  -> RED
 * 120/120 -> RED
 * 118/76  -> KABUL
 *
 * GECERSIZ ISLEM:
 *
 * - RED uyarisi yazilir.
 * - return false calisir.
 * - Nesnenin alanlari degismez.
 *
 * GECERLI ISLEM:
 *
 * this.buyuk = yeniBuyuk;
 * this.kucuk = yeniKucuk;
 *
 * return true;
 *
 * Iki alan ancak butun kurallar saglandiginda
 * birlikte guncellenir.
 *
 *
 * 5. NEDEN IKI AYRI SETTER YOK?
 * ------------------------------------------------------------
 *
 * setBuyuk() ve setKucuk() yazilmis olsaydi
 * iki alan birbirinden bagimsiz degisebilirdi.
 *
 * Ornek:
 *
 * Mevcut olcum: 120/80
 *
 * setKucuk(130) cagrilsaydi:
 *
 * 120/130 gibi gecersiz bir ara durum
 * ortaya cikabilirdi.
 *
 * ayarla(140, 90) ise iki degeri birlikte
 * kontrol edip birlikte gunceller.
 *
 * BU GOREVIN ANA KAZANIMI:
 *
 * Birbirine bagli alanlarda tutarlilik kurallari
 * tek bir kontrollu metot uzerinden korunmalidir.
 *
 *
 * 6. DEGERLENDIR() METODU
 * ------------------------------------------------------------
 *
 * degerlendir() bir String dondurur.
 *
 * Hocanin laboratuvar tablosuna gore:
 *
 * buyuk < 120 && kucuk < 80
 * -> NORMAL
 *
 * buyuk < 130 && kucuk < 80
 * -> YUKSEK-NORMAL
 *
 * buyuk < 140 || kucuk < 90
 * -> EVRE-1
 *
 * Diger durumlar
 * -> EVRE-2
 *
 * && -> Iki kosul da dogru olmali.
 * || -> En az bir kosul dogru olmali.
 *
 * if / else if / else kullanilir.
 *
 * Boylece uygun olan ilk kategori secilir
 * ve diger kategorilere gecilmez.
 *
 * return kullanilan ayri if bloklari da
 * ayni erken cikis davranisini saglayabilir.
 *
 * Ornek:
 *
 * 118/76 -> NORMAL
 * 135/85 -> EVRE-1
 * 152/96 -> EVRE-2
 *
 * NOT:
 * Bu kategoriler laboratuvarin egitim amacli
 * tablosuna gore belirlenmistir.
 *
 *
 * 7. TURETILMIS GETTER
 * ------------------------------------------------------------
 *
 * public int getNabizBasinci()
 *
 * return buyuk - kucuk;
 *
 * Nabiz basinci ayri bir alanda tutulmaz.
 *
 * Ornek:
 *
 * 118 - 76 = 42
 * 152 - 96 = 56
 *
 * Bu hesaplama, Sicaklik sinifindaki
 * getFahrenheit() ve Urun sinifindaki
 * getStokDegeri() ile ayni mantiktadir.
 *
 * Bilgi ihtiyac aninda hesaplanir.
 *
 *
 * 8. TOSTRING() VE DEMO
 * ------------------------------------------------------------
 *
 * @Override
 *
 * toString() metodunun Object sinifindan gelen
 * surumunu ezdigimizi belirtir.
 *
 * String.format() ile:
 *
 * 118/76 mmHg (NORMAL)
 *
 * biciminde metin dondurulur.
 *
 * "Olcum 1 :" gibi ifadeler toString() icinde
 * bulunmaz. Bunlar Demo sinifinda yazdirilir.
 *
 * Gorev2_KanBasinciDemo:
 *
 * - Nesne olusturur.
 * - Gecersiz istekleri dener.
 * - Eski degerlerin korundugunu gosterir.
 * - Gecerli olcumleri uygular.
 * - Nabiz basincini hesaplatir.
 *
 *
 * 9. SINAV ICIN ONEMLI NOKTALAR
 * ------------------------------------------------------------
 *
 * - this.alan ile parametreyi karistirma.
 * - Constructor nesneye gecerli ilk durum vermeli.
 * - Iki alan arasindaki kural birlikte korunmali.
 * - Gecersiz islemde alanlar degismemeli.
 * - boolean metot true veya false dondurur.
 * - return metottan hemen cikilmasini saglar.
 * - && ile || arasindaki farki unutma.
 * - Kosullarin degerlendirme sirasi onemlidir.
 * - >= ile > arasindaki farki kontrol et.
 * - Turetilmis bilgi icin gereksiz alan olusturma.
 * - toString() yalnizca nesneyi temsil etmeli.
 *
 *
 * GENEL SONUC:
 *
 * KAPSULLEME SADECE ALANLARI PRIVATE YAPMAK DEGILDIR.
 *
 * BIRBIRINE BAGLI ALANLARIN TUTARLILIGINI KORUMAK DA
 * SINIFIN SORUMLULUGUDUR.
 *
 * ============================================================
 */
