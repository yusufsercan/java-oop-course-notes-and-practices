
public final class KursKaydi
{
    // Ortak bos kayit nesnesi
    public static final KursKaydi BOS = new KursKaydi("-", "BOS", 0);

    private final String ogrenciNo;
    private final String dersKodu;
    private final int kredi;

    // PRIVATE CONSTRUCTOR
    private KursKaydi(String ogrenciNo, String dersKodu, int kredi)
    {
        this.ogrenciNo = ogrenciNo;
        this.dersKodu = dersKodu;
        this.kredi = kredi;
    }

    // Ortak kimlik kontrolu
    private static boolean kimlikGecerliMi(String no, String kod)
    {
        return no != null && !no.isBlank()
            && kod != null && !kod.isBlank();
    }

    // FABRIKA 1: Normal kayit (varsayilan 3 kredi)
    public static KursKaydi normal(String no, String kod)
    {
        return olustur(no, kod, 3);
    }

    // FABRIKA 2: Istenen krediyle kayit
    public static KursKaydi olustur(String no, String kod, int kredi)
    {
        if (!kimlikGecerliMi(no, kod) || kredi < 1 || kredi > 10)
        {
            System.out.println("RED: Gecersiz kayit, BOS dondu.");
            return BOS;
        }

        return new KursKaydi(
            no.trim(), kod.trim().toUpperCase(), kredi
        );
    }

    // FABRIKA 3: Dinleyici kaydi (0 kredi)
    public static KursKaydi dinleyici(String no, String kod)
    {
        if (!kimlikGecerliMi(no, kod))
        {
            System.out.println("RED: Gecersiz dinleyici, BOS dondu.");
            return BOS;
        }

        return new KursKaydi(
            no.trim(), kod.trim().toUpperCase(), 0
        );
    }

    // FABRIKA 4: Kopyalama
    public static KursKaydi kopyasi(KursKaydi kaynak)
    {
        if (kaynak == null)
        {
            throw new IllegalArgumentException("Kaynak null olamaz.");
        }

        if (kaynak == BOS)
        {
            return BOS;
        }

        return new KursKaydi(
            kaynak.ogrenciNo, kaynak.dersKodu, kaynak.kredi
        );
    }

    @Override
    public String toString()
    {
        return String.format(
            "Kayit[%s, %s, %d kredi]",
            ogrenciNo, dersKodu, kredi
        );
    }

    public static void main(String[] args)
    {
        KursKaydi a = KursKaydi.normal("123", "yzm205");
        KursKaydi b = KursKaydi.dinleyici("456", "yzm205");

        KursKaydi c = KursKaydi.olustur("", "yzm205", 3);
        KursKaydi d = KursKaydi.kopyasi(c);
        KursKaydi e = KursKaydi.kopyasi(a);

        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("c: " + c);

        System.out.println("c == d: " + (c == d));
        System.out.println("a == e: " + (a == e));
    }
}

/*
 * ============================================================
 * HAFTA 05 - STATIK FABRIKA METODU
 * ============================================================
 * 
 * Asıl kazanımlarımız şunlar:
1. private constructor: Nesnenin dışarıdan doğrudan oluşturulmasını engeller.
2. public static fabrika: Henüz nesne yokken sınıf adıyla çağrılır.
3. return new KursKaydi(...): Yeni nesne oluşturup döndürür.
4. return BOS: Yeni nesne oluşturmaz, mevcut nesneyi döndürür.
5. static final BOS: Sınıfa ait, referansı yeniden atanamayan ortak nesnedir.
6. == operatörü: Nesnelerin bilgilerini değil, aynı nesneyi gösterip göstermediklerini karşılaştırır.
Bir ayrıntı daha: Geçersiz kayıtta BOS döndürmek bu örnek için seçilmiş bir tasarımdır. Gerçek uygulamada ihtiyaca göre istisna fırlatmak da tercih edilebilir.

 *
 * 1. AMAC
 * ------------------------------------------------------------
 *
 * Nesne olusturma islemini dogrudan constructor
 * yerine isimlendirilmis static metotlarla yonetmek.
 *
 * 2. PRIVATE CONSTRUCTOR
 * ------------------------------------------------------------
 *
 * private KursKaydi(...)
 *
 * Sinif disindan dogrudan new KursKaydi(...)
 * kullanilmasini engeller.
 *
 * Nesneler fabrika metotlari uzerinden olusturulur.
 *
 * 3. STATIC FABRIKA
 * ------------------------------------------------------------
 *
 * public static KursKaydi normal(...)
 *
 * static oldugu icin nesne gerektirmeden
 * KursKaydi.normal(...) seklinde cagrilir.
 *
 * Fabrika metodu:
 * - Yeni nesne olusturabilir.
 * - Onceden olusturulmus nesne dondurebilir.
 * - Girdiyi kontrol edebilir.
 * - Farkli kurulus yollarina anlamli isim verebilir.
 *
 * 4. NORMAL VE DINLEYICI
 * ------------------------------------------------------------
 *
 * normal(String, String)
 * -> Varsayilan 3 krediyle kayit olusturur.
 *
 * dinleyici(String, String)
 * -> 0 krediyle dinleyici kaydi olusturur.
 *
 * Iki metot ayni parametre turlerini alabilir.
 * Cunku isimleri farklidir.
 *
 * Ayni imzaya sahip iki constructor yazilamaz.
 *
 * 5. BOS SABITI
 * ------------------------------------------------------------
 *
 * public static final KursKaydi BOS
 *
 * Sinifin hazir tuttugu ortak bos kayittir.
 *
 * Gecersiz isteklerde:
 *
 * return BOS;
 *
 * kullanilir.
 *
 * Boylece her hatali istekte yeni nesne
 * olusturmak zorunda kalmayiz.
 *
 * BOS alaninin final olmasi, referansin
 * yeniden atanmasini engeller.
 *
 * Bu ornekte sinif final ve alanlar da final
 * oldugu icin BOS nesnesi degistirilemez.
 *
 * 6. KOPYASI() FABRIKASI
 * ------------------------------------------------------------
 *
 * kopyasi(kaynak)
 *
 * kaynak == BOS ise:
 * -> Ayni BOS nesnesini dondurur.
 *
 * Diger gecerli kayitlarda:
 * -> Yeni bir KursKaydi nesnesi uretir.
 *
 * Bu nedenle:
 *
 * c == d -> true
 * a == e -> false
 *
 * == operatoru referans esitligini kontrol eder.
 *
 * 7. CONSTRUCTOR VE FABRIKA FARKI
 * ------------------------------------------------------------
 *
 * Constructor:
 * - new ile cagrilir.
 * - Her basarili new yeni bir nesne olusturur.
 * - Ayni imzayla ikinci constructor yazilamaz.
 *
 * Statik fabrika:
 * - Isimlendirilmis normal metottur.
 * - Yeni nesne olusturmak zorunda degildir.
 * - Farkli isimlerle ayni parametre turlerini
 *   kullanabilir.
 * - Girdiyi ayrisip kontrol ederek nesne uretebilir.
 * - Uygun tasarimlarda alt tur dondurebilir.
 *
 * 8. FABRIKANIN DEZAVANTAJLARI
 * ------------------------------------------------------------
 *
 * - Constructor private ise alt sinifin bu
 *   constructor'i cagirmasi mumkun olmaz.
 *
 * - Fabrika metotlari anlamli isimlendirilmelidir.
 *
 * - API belgelerinde constructor'lar kadar
 *   kolay fark edilmeyebilir.
 *
 * 9. ONEMLI AYRINTI
 * ------------------------------------------------------------
 *
 * Statik fabrika mutlaka constructor'in
 * yerini almak zorunda degildir.
 *
 * Her sinif icin kullanilmasi zorunlu degildir.
 * Ihtiyac olan durumlarda tercih edilir.
 *
 * Yaygin isimler:
 *
 * of(), from(), valueOf(), getInstance(),
 * copyOf(), olustur(), kopyasi()
 *
 * 10. VIZE KAZANIMI
 * ------------------------------------------------------------
 *
 * private constructor -> Dogrudan uretimi kisitlar.
 *
 * public static fabrika -> Nesne olmadan cagrilir.
 *
 * BOS -> Onceden uretilmis nesne paylasilabilir.
 *
 * kopyasi() -> Gerektiginde yeni veya mevcut
 * nesne dondurebilir.
 *
 * SONUC:
 *
 * STATIK FABRIKA METODU, NESNE OLUSTURMAYI
 * KONTROL EDEN VE ANLAMLI ISIMLERLE SUNAN
 * BIR TASARIM YONTEMIDIR.
 *
 * ============================================================
 */
