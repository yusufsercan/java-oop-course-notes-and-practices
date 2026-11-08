/*
 * Nesne Yonelimli Programlama - KTU Of Teknoloji Fakultesi
 * Hafta 05 - Yapici ve Yikici Yontemler
 * Ders ornegi 1
 *
 * Amac: Asiri yuklenmis yapicilar, this(...) zinciri ve kopya yapici.
 *       Tum yapicilar tek bir "ana yapici" uzerinde birlesir; dogrulama
 *       kurali yalnizca orada yazilir.
 */
public class Nokta {

    private final double x;
    private final double y;
    private final String etiket;

    /* --- 1. Parametresiz yapici: her seyi varsayilana birakir --- */
    public Nokta() 
    {
        this(0.0, 0.0, "Baslangic"); // buradaki this ana yapiciya gider ve dogrulama yapar
        System.out.println("  [1] Parametresiz yapici govdesi calisti");
    }

    /* --- 2. Tek parametreli: kosegen uzerinde bir nokta --- */
    public Nokta(double kosegen) 
    {
        this(kosegen, kosegen, "Kosegen");
        System.out.println("  [2] Tek parametreli yapici govdesi calisti");
    }

    /* --- 3. Iki parametreli: en sik kullanilan bicim --- */
    public Nokta(double x, double y) 
    {
        this(x, y, "Adsiz");
        System.out.println("  [3] Iki parametreli yapici govdesi calisti");
    }

    /* --- 4. ANA YAPICI: dogrulama yalnizca burada --- */
    public Nokta(double x, double y, String etiket) 
    {
        if (Double.isNaN(x) || Double.isNaN(y)) // isNaN() metodu ile NaN degerleri kontrol ediyoruz yani eger x veya y NaN ise IllegalArgumentException fırlatıyoruz Nan ın anlamı Not a Number yani sayi degil demektir
        {
            throw new IllegalArgumentException("Koordinat NaN olamaz");
        }
        this.x = x;
        this.y = y;
        this.etiket = (etiket == null || etiket.isBlank()) ? "Adsiz" : etiket.trim();// .trim() metodu ile etiketin başındaki ve sonundaki boşlukları kaldırıyoruz ve eğer etiket null veya boş ise "Adsiz" olarak ayarlıyoruz
        System.out.println("  [4] ANA yapici calisti -> " + this.etiket);
    }

    /* --- 5. KOPYA YAPICI: ayni turden bir nesneden yeni nesne uretir --- */
    public Nokta(Nokta kaynak) 
    {
        this(kaynak.x, kaynak.y, kaynak.etiket + " (kopya)");
    }

    public double getX() 
    {
         return x;
 
    }
    public double getY() 
    { 
        return y; 
    }
    public String getEtiket() 
    { 
        return etiket; 
    }

    public double baslangictanUzaklik() 
    {
        return Math.sqrt(x * x + y * y);
    }

    public Nokta otele(double dx, double dy) 
    {
        return new Nokta(x + dx, y + dy, etiket);
    }

    @Override
    public String toString() 
    {
        return String.format("%s(%.2f, %.2f)", etiket, x, y);
    }
}

/*
 * ============================================================
 * HAFTA 05 - DERS ORNEGI 1: NOKTA.JAVA
 * KONU: CONSTRUCTOR OVERLOADING, THIS(...) VE KOPYA YAPICI
 * ============================================================
 *
 * 1. KODUN AMACI
 * ------------------------------------------------------------
 *
 * Bu sinif, iki boyutlu duzlemde bir noktayi temsil eder.
 *
 * Her Nokta nesnesinin:
 *
 * - x koordinati
 * - y koordinati
 * - etiket bilgisi
 *
 * bulunur.
 *
 * Bu ornekte temel amac:
 *
 * 1) Farkli constructor'larla nesne olusturmak.
 * 2) this(...) ile constructor'lari zincirlemek.
 * 3) Dogrulamayi tek bir ana constructor'da toplamak.
 * 4) Kopya constructor ile yeni nesne uretmek.
 * 5) final alanlarin nasil calistigini anlamak.
 * 6) otele() ile mevcut nesneyi degistirmeden yeni
 *    nesne olusturmak.
 *
 *
 * 2. PRIVATE FINAL ALANLAR
 * ------------------------------------------------------------
 *
 * private final double x;
 * private final double y;
 * private final String etiket;
 *
 * private:
 * Alanlara sinif disindan dogrudan erisimi engeller.
 *
 * final:
 * Alanlara ilk atama yapildiktan sonra yeniden
 * atama yapilmasini engeller.
 *
 * Bu sinifta x, y ve etiket ana constructor'da
 * atanir.
 *
 * Diger constructor'lar bu alanlara tekrar
 * atama yapmaz.
 *
 * ONEMLI:
 *
 * final, her zaman nesnenin degismez oldugu
 * anlamina gelmez.
 *
 * Ancak bu ornekte alanlar final'dir,
 * setter yoktur ve String degismez bir turdur.
 *
 * Bu nedenle Nokta nesnesinin mevcut alan
 * degerleri olusturulduktan sonra degismez.
 *
 *
 * 3. CONSTRUCTOR OVERLOADING
 * ------------------------------------------------------------
 *
 * Ayni sinifta farkli parametre listelerine sahip
 * birden fazla constructor tanimlamaktir.
 *
 * Nokta sinifinda 5 constructor vardir:
 *
 * 1) Nokta()
 *
 * 2) Nokta(double kosegen)
 *
 * 3) Nokta(double x, double y)
 *
 * 4) Nokta(double x, double y, String etiket)
 *
 * 5) Nokta(Nokta kaynak)
 *
 * Java, gonderilen parametrelerin sayisina,
 * turune ve sirasina bakarak uygun constructor'i
 * secer.
 *
 * Parametre isimlerinin farkli olmasi tek basina
 * overloading icin yeterli degildir.
 *
 *
 * 4. THIS VE THIS(...) FARKI
 * ------------------------------------------------------------
 *
 * this.x = x;
 *
 * Buradaki this, mevcut nesneyi ifade eder.
 *
 * this.x -> Nesnenin x alani
 * x      -> Constructor parametresi
 *
 * Ancak:
 *
 * this(x, y, "Adsiz");
 *
 * ifadesi ayni siniftaki baska bir constructor'i
 * cagirir.
 *
 * KISACASI:
 *
 * this.alan -> Nesnenin alanina erisim
 *
 * this(...) -> Ayni siniftaki constructor'i cagirma
 *
 *
 * 5. CONSTRUCTOR ZINCIRI
 * ------------------------------------------------------------
 *
 * Ornek:
 *
 * Nokta n = new Nokta(3.0, 4.0);
 *
 * Iki parametre gonderildigi icin Java once
 * iki parametreli constructor'i secer.
 *
 * Buradaki:
 *
 * this(x, y, "Adsiz");
 *
 * ifadesi ana constructor'i cagirir.
 *
 * Cagri sirasi:
 *
 * [3] Iki parametreli
 *            |
 *            v
 * [4] ANA constructor
 *
 * Ancak ekrana yazdirilma sirasi:
 *
 * [4] ANA yapici calisti -> Adsiz
 * [3] Iki parametreli yapici govdesi calisti
 *
 * NEDEN?
 *
 * this(...) ile cagrilan constructor tamamlanmadan
 * cagiran constructor'in sonraki satirina gecilmez.
 *
 * SINAV KURALI:
 *
 * Constructor zincirinde cagrilar ana yapiciya
 * dogru ilerler.
 *
 * Constructor govdelerinin kalan satirlari
 * ters yonde tamamlanir.
 *
 *
 * 6. THIS(...) KULLANIM KURALLARI
 * ------------------------------------------------------------
 *
 * - Ayni siniftaki baska bir constructor'i cagirir.
 *
 * - Dersin kullandigi Java 21'de constructor'in
 *   ilk satirinda bulunmalidir.
 *
 * - Bir constructor'da en fazla bir this(...)
 *   cagrisi bulunabilir.
 *
 * - Sonsuz veya dongusel constructor zinciri
 *   olusturulamaz.
 *
 * - Zincirin sonunda this(...) kullanmayan
 *   bir constructor bulunmalidir.
 *
 * - Constructor zinciri ayri ayri nesneler
 *   olusturmaz.
 *
 * Tek new ifadesiyle tek bir Nokta nesnesi
 * olusturulur.
 *
 *
 * 7. ANA CONSTRUCTOR VE DOGRULAMA
 * ------------------------------------------------------------
 *
 * public Nokta(double x, double y, String etiket)
 *
 * Bu constructor, sinifin ana constructor'idir.
 *
 * Diger kurulus yollari buraya baglanir.
 *
 * Burada:
 *
 * if (Double.isNaN(x) || Double.isNaN(y))
 *
 * kontrolu bulunur.
 *
 * Double.NaN:
 * Gecerli bir sayisal sonucu temsil etmeyen
 * ozel double degeridir.
 *
 * Double.isNaN():
 * Bir degerin NaN olup olmadigini kontrol eder.
 *
 * Gecersiz koordinat gelirse:
 *
 * throw new IllegalArgumentException(...);
 *
 * calisir.
 *
 * Bu durumda nesnenin olusturulmasi
 * basariyla tamamlanmaz.
 *
 * NOT:
 * Bu kontrol NaN degerlerini reddeder.
 * Pozitif/negatif Infinity degerlerini reddetmez.
 *
 *
 * 8. ETIKET DOGRULAMASI
 * ------------------------------------------------------------
 *
 * this.etiket =
 *     (etiket == null || etiket.isBlank())
 *     ? "Adsiz"
 *     : etiket.trim();
 *
 * Burada ternary operator kullanilmistir.
 *
 * Kosul ? Dogruysa : Yanlissa
 *
 * etiket null veya bos ise:
 *
 * "Adsiz" atanir.
 *
 * Diger durumlarda:
 *
 * trim() ile bastaki ve sondaki bosluklar
 * temizlenir.
 *
 *
 * 9. KOPYA CONSTRUCTOR
 * ------------------------------------------------------------
 *
 * public Nokta(Nokta kaynak)
 * {
 *     this(kaynak.x, kaynak.y,
 *          kaynak.etiket + " (kopya)");
 * }
 *
 * Kopya constructor, parametre olarak
 * ayni siniftan bir nesne alir.
 *
 * Ornek:
 *
 * Nokta a = new Nokta(2.0, 3.0, "Ilk");
 *
 * Nokta b = new Nokta(a);
 *
 * Burada b, a'nin bilgileri kullanilarak
 * olusturulmus YENI bir nesnedir.
 *
 * Koordinatlar aynidir.
 * Etiketin sonuna " (kopya)" eklenir.
 *
 * ONEMLI FARK:
 *
 * Nokta b = a;
 *
 * Yeni nesne olusturmaz.
 * Sadece referans kopyalanir.
 *
 * Nokta b = new Nokta(a);
 *
 * Yeni nesne olusturur.
 * Kopya constructor calisir.
 *
 * Bu ornekte kopya constructor, kaynagin
 * null olmadigini varsayar.
 *
 * null gonderilirse NullPointerException
 * olusabilir.
 *
 *
 * 10. REFERANS KARSILASTIRMASI
 * ------------------------------------------------------------
 *
 * Nokta a = new Nokta(5.0, 8.0, "A");
 *
 * Nokta b = a;
 *
 * Nokta c = new Nokta(a);
 *
 * System.out.println(a == b); // true
 *
 * System.out.println(a == c); // false
 *
 * System.out.println(b == c); // false
 *
 * == operatoru nesne referanslarinda,
 * iki referansin ayni nesneyi gosterip
 * gostermedigini kontrol eder.
 *
 * Iki nesnenin alanlari ayni olabilir.
 *
 * Ancak bu durum onlarin ayni nesne
 * oldugu anlamina gelmez.
 *
 *
 * 11. OTELE() METODU
 * ------------------------------------------------------------
 *
 * public Nokta otele(double dx, double dy)
 * {
 *     return new Nokta(x + dx, y + dy, etiket);
 * }
 *
 * Bu metot mevcut nesnenin koordinatlarini
 * degistirmez.
 *
 * Yeni koordinatlarla yeni bir Nokta uretir.
 *
 * Ornek:
 *
 * Nokta p = new Nokta(3.0, 4.0, "P");
 *
 * Nokta q = p.otele(2.0, -1.0);
 *
 * p = (3.0, 4.0)
 *
 * q = (5.0, 3.0)
 *
 * p nesnesi degismedi.
 *
 * q yeni bir nesne oldu.
 *
 * Bu tasarim, mevcut nesnenin durumunu
 * koruyarak yeni sonuclar uretmeyi saglar.
 *
 * Hafta 4'teki immutable ParaBirimi
 * ornegiyle benzer bir mantiga sahiptir.
 *
 *
 * 12. BASLANGICTAN UZAKLIK
 * ------------------------------------------------------------
 *
 * public double baslangictanUzaklik()
 * {
 *     return Math.sqrt(x * x + y * y);
 * }
 *
 * Bu metot, noktanin (0, 0) noktasina
 * uzakligini hesaplar.
 *
 * Formul:
 *
 * uzaklik = karekok(x*x + y*y)
 *
 * Ornek:
 *
 * x = 3
 * y = 4
 *
 * uzaklik = karekok(9 + 16)
 *         = karekok(25)
 *         = 5
 *
 * Math.sqrt() karekok hesaplar.
 *
 * Bu metot turetilmis sonuc uretir.
 *
 * Uzaklik ayri bir alanda tutulmaz.
 *
 *
 * 13. TOSTRING() METODU
 * ------------------------------------------------------------
 *
 * @Override
 * public String toString()
 *
 * Nesnenin okunabilir metin biciminde
 * gosterilmesini saglar.
 *
 * String.format("%s(%.2f, %.2f)", ...)
 *
 * %s   -> String deger
 * %.2f -> Ondalik sayiyi iki basamakla gosterir
 *
 * Ornek:
 *
 * Adsiz(3.00, 4.00)
 *
 * Ondalik ayirici, varsayilan Locale ayarina
 * gore nokta veya virgul olabilir.
 *
 *
 * 14. UC PRATIKTEN OGRENDIKLERIMIZ
 * ------------------------------------------------------------
 *
 * PRATIK 1:
 *
 * new Nokta(3.0, 4.0)
 *
 * [4] Ana constructor once yazdirir.
 * [3] Iki parametreli constructor sonra yazdirir.
 *
 * Etiket: Adsiz
 * Uzaklik: 5.0
 *
 *
 * PRATIK 2:
 *
 * Nokta a = new Nokta(2.0, 3.0, "Ilk");
 * Nokta b = new Nokta(a);
 *
 * Iki ayri nesne olusur.
 *
 * a == b sonucu false olur.
 *
 * Ana constructor iki kez calisir.
 *
 *
 * PRATIK 3:
 *
 * Nokta p = new Nokta(3.0, 4.0, "P");
 * Nokta q = p.otele(2.0, -1.0);
 * Nokta r = new Nokta(q);
 *
 * p -> (3, 4), "P"
 * q -> (5, 3), "P"
 * r -> (5, 3), "P (kopya)"
 *
 * p == q -> false
 * q == r -> false
 *
 * r.baslangictanUzaklik():
 *
 * karekok(34) = yaklasik 5.83
 *
 *
 * 15. SIK YAPILAN HATALAR
 * ------------------------------------------------------------
 *
 * - this.x ile this(...) ifadelerini karistirmak.
 *
 * - this(...) cagrisi sonrasinda hangi
 *   constructor'in calistigini karistirmak.
 *
 * - Constructor zincirinde birden fazla
 *   nesne olusturuldugunu sanmak.
 *
 * - Kopya constructor ile referans atamasini
 *   ayni islem zannetmek.
 *
 * - final alanin yeniden atanabilecegini dusunmek.
 *
 * - otele() metodunun mevcut nesneyi
 *   degistirdigini sanmak.
 *
 * - == operatorunun nesnelerin alanlarini
 *   karsilastirdigini dusunmek.
 *
 *
 * 16. VIZE ICIN KRITIK KAZANIMLAR
 * ------------------------------------------------------------
 *
 * 1) Constructor'in donus turu yoktur.
 *
 * 2) Constructor overloading icin parametre
 *    listeleri farkli olmalidir.
 *
 * 3) this(...) ayni siniftaki baska bir
 *    constructor'i cagirir.
 *
 * 4) Cagri sirasi ile tamamlanma sirasi farklidir.
 *
 * 5) Kopya constructor yeni bir nesne olusturur.
 *
 * 6) Referans atamasi yeni nesne olusturmaz.
 *
 * 7) == operatoru referans esitligini kontrol eder.
 *
 * 8) final alanlar yeniden atanamaz.
 *
 * 9) otele() yeni nesne dondurur.
 *
 * 10) Math.sqrt() karekok hesaplar.
 *
 * 11) IllegalArgumentException gecersiz
 *     constructor girdilerini reddetmek icin
 *     kullanilabilir.
 *
 *
 * GENEL SONUC:
 *
 * CONSTRUCTOR OVERLOADING
 * -> Farkli parametrelerle nesne olusturma.
 *
 * THIS(...) ZINCIRI
 * -> Constructor'lari ortak ana yapiciya baglama.
 *
 * KOPYA CONSTRUCTOR
 * -> Var olan nesnenin bilgileriyle yeni nesne uretme.
 *
 * FINAL ALANLAR
 * -> Ilk atamadan sonra alanlarin yeniden atanmasini
 *    engelleme.
 *
 * OTELE()
 * -> Mevcut nesneyi degistirmeden yeni nesne uretme.
 *
 * ============================================================
 */
