/*
 * Nesne Yonelimli Programlama - Hafta 05
 *
 * Konu: Constructor Overloading ve this(...) Zinciri
 *
 * Amac:
 * Bir nesneyi farkli sayida parametreyle olusturmak,
 * ancak tum dogrulamalari tek bir ana constructor
 * uzerinden gerceklestirmek.
 */

// bu kodun amacı sadece constructor overloading ve this(...) zincirini göstermek olduğundan,
// KitapZinciri sınıfının alanları private final olarak tanımlanmıştır.

public class KitapZinciri
{
    private final String isbn;
    private final String baslik;
    private final String yazar;
    private final int sayfaSayisi;
    private final int basimYili;

    // 1. CONSTRUCTOR - Iki parametreli
    public KitapZinciri(String isbn, String baslik)
    {
        this(isbn, baslik, "Bilinmiyor");

        System.out.println("[2] Iki parametreli constructor tamamlandi.");
    }

    // 2. CONSTRUCTOR - Uc parametreli
    public KitapZinciri(String isbn, String baslik, String yazar)
    {
        this(isbn, baslik, yazar, 100);

        System.out.println("[3] Uc parametreli constructor tamamlandi.");
    }

    // 3. CONSTRUCTOR - Dort parametreli
    public KitapZinciri(String isbn, String baslik,
                       String yazar, int sayfaSayisi)
    {
        this(isbn, baslik, yazar, sayfaSayisi, 2026);

        System.out.println("[4] Dort parametreli constructor tamamlandi.");
        
    }

    // 4. CONSTRUCTOR - Bes parametreli ANA YAPICI
    public KitapZinciri(String isbn, String baslik,
                       String yazar, int sayfaSayisi, int basimYili)
    {
        // Tum dogrulamalar tek bir yerde yapilir.

        if (isbn == null || isbn.isBlank())
        {
            throw new IllegalArgumentException("ISBN bos olamaz.");
        }

        if (baslik == null || baslik.isBlank())
        {
            throw new IllegalArgumentException("Baslik bos olamaz.");
        }
    
        if (sayfaSayisi <= 0)
        {
            throw new IllegalArgumentException(
                "Sayfa sayisi pozitif olmalidir."
            );
        }

        if (basimYili < 1450 || basimYili > 2026)
        {
            throw new IllegalArgumentException(
                "Basim yili gecersiz."
            );
        }

        this.isbn = isbn.trim();
        this.baslik = baslik.trim();
        this.yazar = (yazar == null || yazar.isBlank())
                     ? "Bilinmiyor" : yazar.trim();

        this.sayfaSayisi = sayfaSayisi;
        this.basimYili = basimYili;

        System.out.println("[5] ANA constructor calisti.");
    }

    @Override
    public String toString()
    {
        return String.format(
            "Kitap[%s, %s, %s, %d sayfa, %d]",
            isbn, baslik, yazar, sayfaSayisi, basimYili
        );
    }
}


/*
 * ============================================================
 * HAFTA 05 - CONSTRUCTOR OVERLOADING VE THIS(...) ZINCIRI
 * ============================================================
 *
 * 1. KODUN AMACI
 * ------------------------------------------------------------
 *
 * Bir KitapZinciri nesnesini farkli parametre sayilariyla
 * olusturmak ve ayni dogrulama kodunu tekrar yazmamaktir.
 *
 * Sinifta dort farkli constructor bulunur.
 *
 * - 2 parametreli constructor
 * - 3 parametreli constructor
 * - 4 parametreli constructor
 * - 5 parametreli ANA constructor
 *
 * Butun constructor'lar ayni sinifa aittir.
 *
 *
 * 2. CONSTRUCTOR OVERLOADING NEDIR?
 * ------------------------------------------------------------
 *
 * Ayni sinifta farkli parametre listelerine sahip
 * birden fazla constructor yazmaktir.
 *
 * Ornek:
 *
 * KitapZinciri(String isbn, String baslik)
 *
 * KitapZinciri(String isbn, String baslik, String yazar)
 *
 * KitapZinciri(String isbn, String baslik,
 *             String yazar, int sayfaSayisi)
 *
 * Java hangi constructor'in calisacagina gonderilen
 * parametrelerin sayisina, turlerine ve sirasina
 * bakarak karar verir.
 *
 * DIKKAT:
 * Parametre isimlerini degistirmek overloading icin
 * yeterli degildir.
 *
 *
 * 3. THIS ILE THIS(...) ARASINDAKI FARK
 * ------------------------------------------------------------
 *
 * this.isbn = isbn;
 *
 * Buradaki this, mevcut nesneyi ifade eder.
 *
 * this.isbn -> Nesnenin alani
 * isbn      -> Parametre
 *
 * Ancak:
 *
 * this(isbn, baslik, "Bilinmiyor");
 *
 * Buradaki this(...), ayni siniftaki baska
 * bir constructor'i cagirir.
 *
 * KISACASI:
 *
 * this.alan -> Nesnenin alanina erisim
 * this(...) -> Baska constructor'i cagirma
 *
 *
 * 4. CONSTRUCTOR ZINCIRI NASIL CALISIR?
 * ------------------------------------------------------------
 *
 * Ornek:
 *
 * new KitapZinciri("123", "Java", "Ali");
 *
 * Uc parametre gonderildigi icin Java once
 * 3 parametreli constructor'i secer.
 *
 * Buradaki this(...) ile:
 *
 * 3 parametreli -> 4 parametreli
 * 4 parametreli -> 5 parametreli ANA
 *
 * Ana constructor tum kontrolleri yapar.
 * Alanlara degerlerini atar.
 *
 * Daha sonra constructor'lar geri donerek
 * kendi kalan satirlarini calistirir.
 *
 * EKRAN CIKTISI:
 *
 * [5] ANA constructor calisti.
 * [4] Dort parametreli constructor tamamlandi.
 * [3] Uc parametreli constructor tamamlandi.
 *
 * ONEMLI:
 *
 * CAGRI SIRASI:      3 -> 4 -> 5
 * TAMAMLANMA SIRASI: 5 -> 4 -> 3
 *
 * Tek bir new ifadesiyle tek bir KitapZinciri
 * nesnesi olusturulur.
 *
 * Constructor sayisi kadar nesne olusturulmaz.
 *
 *
 * 5. NEDEN ANA CONSTRUCTOR KULLANIYORUZ?
 * ------------------------------------------------------------
 *
 * Eski tasarimda her constructor icinde:
 *
 * - ISBN kontrolu
 * - Baslik kontrolu
 * - Sayfa sayisi kontrolu
 *
 * tekrar tekrar yazilabilirdi.
 *
 * Bu durum gereksiz kod tekrarina neden olurdu.
 *
 * Yeni tasarimda kisa constructor'lar yalnizca
 * eksik parametreleri varsayilanlarla tamamlar.
 *
 * Tum kontroller 5 parametreli ana constructor'da
 * bulunur.
 *
 * Boylece bir kural degistiginde tek yerde
 * duzenleme yapmak yeterli olur.
 *
 * Bu, DRY (Don't Repeat Yourself) ilkesine uygundur.
 *
 *
 * 6. VARSAYILAN PARAMETRELER
 * ------------------------------------------------------------
 *
 * 2 parametreli constructor:
 *
 * Yazar bilinmiyorsa "Bilinmiyor" kullanilir.
 *
 * 3 parametreli constructor:
 *
 * Sayfa sayisi verilmemisse 100 kullanilir.
 *
 * 4 parametreli constructor:
 *
 * Basim yili verilmemisse 2026 kullanilir.
 *
 * Bu ornekte 2026, ders notundaki sabit varsayimdir.
 * Sistem tarihinden otomatik alinmaz.
 *
 *
 * 7. THIS(...) KURALLARI
 * ------------------------------------------------------------
 *
 * - Ayni siniftaki baska bir constructor'i cagirir.
 *
 * - Java 21'de constructor'in ilk satiri olmalidir.
 *
 * - Bir constructor'da en fazla bir this(...)
 *   cagrisi bulunabilir.
 *
 * - Constructor zinciri kendi icinde sonsuz
 *   donguye giremez.
 *
 * - Zincirin sonunda this(...) kullanmayan
 *   bir ana constructor bulunmalidir.
 *
 *
 * 8. FINAL ALANLAR
 * ------------------------------------------------------------
 *
 * private final String isbn;
 *
 * final alanlar ilk kez constructor icinde atanabilir.
 * Atandiktan sonra yeniden atanamaz.
 *
 * Kisa constructor'lar bu alanlara atama yapmaz.
 * Islem ana constructor'da gerceklesir.
 *
 * Boylece final alanlar yalnizca bir kez atanir.
 *
 *
 * 9. GECERSIZ PARAMETRELER
 * ------------------------------------------------------------
 *
 * Ana constructor tum dogrulamalari yapar.
 *
 * Gecersiz veri gelirse:
 *
 * throw new IllegalArgumentException(...)
 *
 * calisir.
 *
 * Boylece hatali nesnenin olusturulmasi
 * basariyla tamamlanmaz.
 *
 * Ornek:
 *
 * new KitapZinciri("", "Java");
 *
 * Bu cagri da zincir uzerinden ana constructor'a
 * ulasir ve ISBN kontrolunde reddedilir.
 *
 *
 * 10. SINAV ICIN KRITIK NOKTALAR
 * ------------------------------------------------------------
 *
 * - Constructor'in adi sinif adi ile aynidir.
 * - Constructor'in donus turu yoktur.
 * - void bile yazilmaz.
 * - Overloading icin parametre listesi farkli olmali.
 * - this(...) ayni siniftaki constructor'i cagirir.
 * - this(...) Java 21'de ilk satir olmalidir.
 * - Dogrulama tek ana constructor'da toplanabilir.
 * - Cagri sirasi ile tamamlanma sirasi farklidir.
 * - Zincirdeki her constructor ayri nesne uretmez.
 * - final alanlar yalnizca bir kez atanir.
 * - Gecersiz parametrede constructor istisna firlatabilir.
 *
 *
 * GENEL SONUC:
 *
 * CONSTRUCTOR OVERLOADING:
 * Bir sinifin birden fazla kurulus yoluna sahip olmasi.
 *
 * THIS(...) ZINCIRI:
 * Bu kurulus yollarinin ortak bir ana constructor'a
 * baglanmasi.
 *
 * ANA CONSTRUCTOR:
 * Dogrulama kurallarinin tek yerde toplandigi yapici.
 *
 * EN ONEMLI KAZANIM:
 *
 * FARKLI CONSTRUCTOR'LAR KULLANILSA DA
 * DOGRULAMA KURALLARI TEK BIR YERDE TUTULABILIR.
 *
 * ============================================================
 * 
 * 
 * 
 */
