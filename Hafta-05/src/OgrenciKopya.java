public class OgrenciKopya
{
    private final String ad;
    private int yas;
    private final int[] notlar;

    // ANA CONSTRUCTOR
    public OgrenciKopya(String ad, int yas, int[] notlar)
    {
        if (ad == null || ad.isBlank())
        {
            throw new IllegalArgumentException("Ad bos olamaz.");
        }

  

        if (notlar == null)
        {
            throw new IllegalArgumentException("Not dizisi null olamaz.");
        }

        this.ad = ad.trim();// boşluklarla iligili trim
        this.yas=setYas(yas);

        // Disaridaki diziden bagimsiz bir kopya olusturur.
        this.notlar = notlar.clone();// burada clone() kullanilmazsa disaridaki dizi degistirildiginde ogrencinin notlari da degisebilir. ne işe yarar ? dışarıdan gelen diziyi kopyalar ve kendi içinde saklar. böylece dışarıdaki dizi değişse bile öğrencinin notları değişmez.
    }

    // KOPYA CONSTRUCTOR
    public OgrenciKopya(OgrenciKopya kaynak)
    {
        this(kaynak.ad, kaynak.yas, kaynak.notlar);
    }

    // Gecersiz istekte eski yas korunur.
    public int setYas(int yas)
    {
        if (yas < 0)
        {
            System.out.println("RED: Yas negatif olamaz.");
            return 0;
        }

        return yas;
    }

    public void notDegistir(int index, int yeniNot)
    {
        if (index < 0 || index >= notlar.length ||
            yeniNot < 0 || yeniNot > 100)
        {
            System.out.println("RED: Gecersiz not veya indeks.");
            return;
        }

        notlar[index] = yeniNot;
    }

    public int getNot(int index)
    {
        return notlar[index];
    }

    @Override
    public String toString()
    {
        return String.format(
            "Ogrenci[%s, yas=%d, ilkNot=%d]",
            ad, yas, notlar[0]
        );
    }

    public static void main(String[] args)
    {
        int[] disNotlar = {70, 80};

        OgrenciKopya a =
            new OgrenciKopya("Ayse", 20, disNotlar);

        OgrenciKopya b = new OgrenciKopya(a);

        // Disaridaki dizi degistiriliyor.
        disNotlar[0] = 0;

        // Sadece kopyanin notu degistiriliyor.
        b.notDegistir(0, 95);

        System.out.println("A: " + a);
        System.out.println("B: " + b);
        System.out.println("A == B: " + (a == b));

        a.setYas(-5);
        System.out.println("Son durum A: " + a);
    }
}
/*
 * DIZILERDE CLONE() KULLANIMI
 *
 * this.notlar = notlar;
 * -> Yeni dizi olusturmaz.
 * -> Iki referans ayni diziyi gosterir.
 * -> Disaridaki degisiklik nesneyi de etkileyebilir.
 *
 * this.notlar = notlar.clone();
 * -> Yeni ve bagimsiz bir int[] dizisi olusturur.
 * -> Dizinin elemanlarini kopyalar.
 * -> Disaridaki degisiklik nesnenin notlarini etkilemez.
 *
 * final:
 * Dizi referansinin yeniden atanmasini engeller,
 * ancak dizi elemanlarinin degismesini engellemez.
 *
 * KAZANIM:
 * Degistirilebilir verileri constructor'a alirken
 * guvenli kopyalama yaparak kapsullemeyi korumak.
 *
 * NOT:
 * int[] icin clone() yeterlidir.
 * Nesne dizilerinde elemanlar da degistirilebilir
 * nesnelerse ek kopyalama gerekebilir.
 */

/*
 * CONSTRUCTOR VE SETTER DOGRULAMASI
 *
 * Constructor'da gecersiz veri gelirse istisna
 * firlatilarak nesnenin olusturulmasi engellenir.
 *
 * Setter'da gecersiz veri gelirse islem reddedilir
 * ve mevcut nesnenin eski degeri korunabilir.
 *
 * Constructor icinde setYas() kullanmak her zaman
 * uygun degildir. Setter sadece uyari yazip
 * return ediyorsa constructor calismaya devam eder.
 *
 * Bu nedenle constructor'in gecersiz veriyi
 * gercekten reddettiginden emin olunmalidir.
 */

/*
 * ============================================================
 * HAFTA 05 - CONSTRUCTOR DOGRULAMASI VE DERIN KOPYA
 * ============================================================
 *
 * 1. AMAC
 * ------------------------------------------------------------
 * Constructor ile gecerli nesne olusturmak,
 * kopya constructor kullanmak ve dizileri guvenli
 * sekilde kopyalamak.
 *
 * 2. CONSTRUCTOR VE SETTER FARKI
 * ------------------------------------------------------------
 * Constructor:
 * Henuz gecerli bir nesne bulunmadigindan,
 * gecersiz degerde IllegalArgumentException
 * firlatilabilir.
 *
 * Setter:
 * Nesne zaten vardir.
 * Gecersiz degerde atama reddedilir ve onceki
 * gecerli durum korunabilir.
 *
 * 3. KOPYA CONSTRUCTOR
 * ------------------------------------------------------------
 * public OgrenciKopya(OgrenciKopya kaynak)
 *
 * Ayni siniftan bir nesneyi parametre alir.
 *
 * new OgrenciKopya(a)
 *
 * Yeni bir nesne olusturur.
 *
 * OgrenciKopya b = a;
 *
 * Yeni nesne olusturmaz. Referansi paylasir.
 *
 * Bu ornekte kopya constructor, kaynak nesnenin
 * null olmadigini varsayar.
 *
 * 4. SIG KOPYA VE DERIN KOPYA
 * ------------------------------------------------------------
 * SIG KOPYA:
 *
 * this.notlar = kaynak.notlar;
 *
 * Iki nesne ayni diziyi paylasir.
 * Birinin dizi elemanini degistirmek digerini
 * de etkileyebilir.
 *
 * DERIN KOPYA:
 *
 * this.notlar = kaynak.notlar.clone();
 *
 * Yeni bir int dizisi olusturulur.
 * Kaynak ve kopya birbirinden bagimsiz olur.
 *
 * Bizim ana constructor'da clone() kullanildigi
 * icin kopya constructor da guvenli kopya yapar.
 *
 * 5. FINAL VE DIZILER
 * ------------------------------------------------------------
 * private final int[] notlar;
 *
 * final, notlar referansinin baska bir diziye
 * yeniden atanmasini engeller.
 *
 * Ancak notlar[0] gibi elemanlar degisebilir.
 *
 * Bu nedenle final tek basina diziyi
 * degismez yapmaz.
 *
 * 6. KAPSÜLLEME
 * ------------------------------------------------------------
 * Dizi constructor'da kopyalanmazsa disaridaki
 * kod, ogrencinin notlarini dolayli olarak
 * degistirebilir.
 *
 * Ayrica dizi getter'i yazilacaksa,
 * dogrudan ic diziyi dondurmek yerine
 * kopyasini dondurmek tercih edilir.
 *
 * 7. BEKLENEN CIKTI
 * ------------------------------------------------------------
 *
 * A: Ogrenci[Ayse, yas=20, ilkNot=70]
 * B: Ogrenci[Ayse, yas=20, ilkNot=95]
 * A == B: false
 * RED: Yas negatif olamaz.
 * Son durum A: Ogrenci[Ayse, yas=20, ilkNot=70]
 *
 * 8. SINAV ICIN KRITIK NOTLAR
 * ------------------------------------------------------------
 *
 * - Constructor gecersiz girisi reddedebilir.
 * - this(...) ile ana constructor'a devredilebilir.
 * - new ile yeni nesne olusturulur.
 * - Referans atamasi nesne kopyalamaz.
 * - Kopya constructor yeni nesne olusturur.
 * - Sig kopyada degisebilir alanlar paylasilir.
 * - Derin kopyada degisebilir alanlar cogaltilir.
 * - int[].clone() yeni dizi uretir.
 * - final bir dizi referansini korur,
 *   ancak dizi elemanlarini degismez yapmaz.
 *
 * NOT:
 * Nesne dizileri ve cok boyutlu diziler icin
 * clone() her zaman tam derin kopya saglamaz.
 * Gerekirse elemanlar tek tek kopyalanmalidir.
 *
 * GENEL KAZANIM:
 *
 * NESNE OLUSTURULURKEN VE KOPYALANIRKEN
 * KAPSÜLLEME KURALLARI KORUNMALIDIR.
 *
 * ============================================================
 */
