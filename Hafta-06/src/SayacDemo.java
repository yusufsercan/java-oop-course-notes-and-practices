
class EtkinlikBileti {

    // Sinifin ortak alanlari
    private static int sonrakiNo = 1;
    private static int uretilen = 0;

    // Her nesneye ozel alanlar
    private final String biletNo;
    private final String ad;

    public EtkinlikBileti(String ad) {

        if (ad == null || ad.isBlank()) {
            throw new IllegalArgumentException("Ad bos olamaz.");
        }

        this.ad = ad;

        this.biletNo = "BLT-" +
                String.format("%03d", sonrakiNo++);

        uretilen++;
    }

    public String bilgi() {
        return biletNo + " / " + ad;
    }

    public static int uretilenSayisi() {
        return uretilen;
    }
}

public class SayacDemo {

    public static void main(String[] args) {

        System.out.println(EtkinlikBileti.uretilenSayisi());

        EtkinlikBileti a = new EtkinlikBileti("Ali");
        EtkinlikBileti b = new EtkinlikBileti("Ayse");

        System.out.println(a.bilgi());
        System.out.println(b.bilgi());

        System.out.println(EtkinlikBileti.uretilenSayisi());
    }
}

/*
 * ============================================================
 * HAFTA 06 - ORNEK 1
 *
 * STATIC ALAN, ORNEK ALAN, STATIC METOT
 * VE OTOMATIK NUMARA URETME
 * ============================================================
 *
 *
 * 1. BU ORNEGI NEDEN YAZDIK?
 * ------------------------------------------------------------
 *
 * Önceki haftalarda Java'da sınıf ve nesne
 * oluşturmayı öğrendik.
 *
 * Örneğin:
 *
 * Ogrenci a = new Ogrenci("Ali", 20);
 * Ogrenci b = new Ogrenci("Ayse", 21);
 *
 * Bu iki nesnenin ad ve yaş bilgileri ayrıdır.
 *
 * a nesnesinin yaşı değiştirilirse b nesnesinin
 * yaşı değişmemelidir.
 *
 * Çünkü yaş bilgisi Ogrenci sınıfının tamamına
 * değil, belirli bir öğrenciye aittir.
 *
 * İşte böyle alanlara:
 *
 * ÖRNEK ALANI (INSTANCE FIELD)
 *
 * diyoruz.
 *
 *
 * PEKİ SORUN NEREDE BAŞLIYOR?
 *
 * Bir etkinliğe katılan herkese farklı bir
 * bilet numarası vermemiz gerektiğini düşünelim.
 *
 * İlk bilete BLT-001,
 * ikinci bilete BLT-002,
 * üçüncü bilete BLT-003 verilsin.
 *
 * Önceden bildiğimiz yöntemle bilet numarasını
 * constructor'a dışarıdan verebilirdik:
 *
 * new EtkinlikBileti("BLT-001", "Ali");
 * new EtkinlikBileti("BLT-002", "Ayse");
 *
 * Ancak burada önemli bir problem var.
 *
 * Programı kullanan kişi yanlışlıkla iki
 * bilete aynı numarayı verebilir.
 *
 * Ayrıca sıradaki numaranın kaç olduğunu
 * her defasında kendisi takip etmelidir.
 *
 * Yani numara üretme sorumluluğu, bilet
 * sınıfında değil, onu kullanan kişidedir.
 *
 *
 * BİZ NEYİ DÜZELTMEK İSTİYORUZ?
 *
 * Bilet numarasını artık dışarıdan istemeyelim.
 *
 * Bilet sınıfı numarayı kendi üretsin.
 *
 * Her yeni nesnede ortak sayaç bir artsın.
 *
 * Böylece:
 *
 * new EtkinlikBileti("Ali");
 * new EtkinlikBileti("Ayse");
 *
 * yazmak yeterli olsun.
 *
 *
 * 2. NEDEN NORMAL BİR int ALANI YETMİYOR?
 * ------------------------------------------------------------
 *
 * Şunu yazdığımızı düşünelim:
 *
 * private int sonrakiNo = 1;
 *
 * Bu alan static değildir.
 *
 * Dolayısıyla her EtkinlikBileti nesnesinin
 * kendisine ait bir sonrakiNo alanı oluşur.
 *
 * İlk bileti oluşturduğumuzda:
 *
 * sonrakiNo = 1
 *
 * İkinci bileti oluşturduğumuzda da yeni
 * nesnenin kendi sayacı:
 *
 * sonrakiNo = 1
 *
 * değerinden başlar.
 *
 * Böylece her nesne kendi sayacını tutarsa
 * otomatik numara üretimi yanlış çalışır.
 *
 * Bizim ihtiyacımız her nesnede yeni bir
 * sayaç değil, bütün biletlerin paylaştığı
 * TEK bir sayaçtır.
 *
 *
 * 3. STATIC TAM OLARAK NEYİ DEĞİŞTİRİYOR?
 * ------------------------------------------------------------
 *
 * Java'da bir alanın başına static yazarsak
 * o alan artık tek bir nesneye değil,
 * SINIFIN KENDİSİNE ait olur.
 *
 * Örneğin:
 *
 * private static int sonrakiNo = 1;
 *
 * Artık:
 *
 * - İlk bilet aynı sayacı kullanır.
 * - İkinci bilet aynı sayacı kullanır.
 * - Üçüncü bilet aynı sayacı kullanır.
 *
 * Kaç nesne oluşturursak oluşturalım,
 * sınıfa ait ortak bir sonrakiNo alanı vardır.
 *
 * KISACA:
 *
 * int x;        -> Her nesnenin kendi x'i.
 *
 * static int x; -> Sınıfın ortak x'i.
 *
 *
 * ============================================================
 * 4. KODDAKİ ALANLARI SATIR SATIR İNCELEYELİM
 * ============================================================
 *
 * A) private static int sonrakiNo = 1;
 *
 * private:
 * Sınıf dışından doğrudan erişimi engeller.
 *
 * static:
 * Alanın tek tek bilet nesnelerine değil,
 * EtkinlikBileti sınıfına ait olduğunu söyler.
 *
 * int:
 * Tam sayı tutulacağını belirtir.
 *
 * sonrakiNo:
 * Sıradaki bilet için kullanılacak sayıdır.
 *
 * = 1:
 * İlk numaranın 1'den başlayacağını belirtir.
 *
 * Burada final kullanmadık.
 *
 * Çünkü her bilet oluşturulduğunda
 * sonrakiNo değerinin değişmesi gerekiyor.
 *
 *
 * B) private static int uretilen = 0;
 *
 * Bu da sınıfa ait ortak bir alandır.
 *
 * Kaç bilet nesnesi üretildiğini tutar.
 *
 * Başlangıçta hiç nesne oluşturulmadığı
 * için değeri 0'dır.
 *
 * İlk bilet oluşturulunca 1,
 * ikinci bilet oluşturulunca 2 olur.
 *
 * DİKKAT:
 *
 * sonrakiNo ile uretilen aynı amaçla
 * kullanılmıyor.
 *
 * sonrakiNo -> Bir sonraki verilecek numara.
 *
 * uretilen -> Şimdiye kadar üretilen
 *             toplam bilet sayısı.
 *
 * Bizim örneğimizde ilişkili olsalar da
 * kavramsal olarak farklı bilgilerdir.
 *
 *
 * C) private final String biletNo;
 *
 * Bu alan static DEĞİLDİR.
 *
 * Çünkü her biletin numarası farklıdır.
 *
 * Ali'nin bileti BLT-001,
 * Ayşe'nin bileti BLT-002 olabilir.
 *
 * final kullandık.
 *
 * Böylece nesne oluşturulurken atanan
 * biletNo daha sonra yeniden atanamaz.
 *
 * Her nesne kendi biletNo değerini tutar.
 *
 *
 * D) private final String ad;
 *
 * Bu da nesneye özel alandır.
 *
 * Ali ve Ayşe'nin adlarını ortak bir
 * static alanda tutmamız yanlış olurdu.
 *
 * Çünkü o durumda bir kişinin adı
 * değiştiğinde diğer biletlerde de
 * aynı ad görünebilirdi.
 *
 *
 * ============================================================
 * 5. CONSTRUCTOR NASIL ÇALIŞIYOR?
 * ============================================================
 *
 * public EtkinlikBileti(String ad) {
 *
 *     if (ad == null || ad.isBlank()) {
 *         throw new IllegalArgumentException(
 *             "Ad bos olamaz."
 *         );
 *     }
 *
 *     this.ad = ad;
 *
 *     this.biletNo = "BLT-" +
 *             String.format("%03d", sonrakiNo++);
 *
 *     uretilen++;
 * }
 *
 *
 * ADIM 1:
 *
 * new EtkinlikBileti("Ali")
 *
 * yazdığımızda yeni bir bilet nesnesinin
 * oluşturulma işlemi başlar.
 *
 * Constructor'a "Ali" bilgisi gönderilir.
 *
 *
 * ADIM 2:
 *
 * if (ad == null || ad.isBlank())
 *
 * Gelen adın geçerli olup olmadığı kontrol
 * edilir.
 *
 * null:
 * Hiçbir String nesnesinin verilmemesidir.
 *
 * isBlank():
 * String'in boş veya yalnızca boşluklardan
 * oluşup oluşmadığını kontrol eder.
 *
 * ||:
 * İki koşuldan en az biri doğruysa
 * if bloğu çalışır.
 *
 * Geçersiz ad gelirse exception fırlatılır.
 *
 * Böylece geçersiz bir bilet oluşturulmaz.
 *
 * ÖNEMLİ:
 *
 * Doğrulamayı sayaç artırma işleminden
 * ÖNCE yaptık.
 *
 * Çünkü başarısız nesne oluşturma
 * denemelerini üretilmiş bilet olarak
 * saymak istemiyoruz.
 *
 *
 * ADIM 3:
 *
 * this.ad = ad;
 *
 * Constructor parametresi olan ad bilgisi,
 * oluşturulan nesnenin kendi ad alanına
 * atanır.
 *
 * this.ad -> Nesnenin alanı.
 *
 * ad      -> Constructor parametresi.
 *
 *
 * ADIM 4:
 *
 * String.format("%03d", sonrakiNo++)
 *
 * Bu satırdaki parçaları ayıralım.
 *
 * String.format():
 * Verilen bilgiyi belirli bir yazım
 * biçimine göre String'e dönüştürür.
 *
 * %03d:
 * Sayıyı en az üç basamaklı biçimde yazar.
 * Eksik basamakların başına 0 ekler.
 *
 * Örnekler:
 *
 * 1  -> 001
 * 2  -> 002
 * 15 -> 015
 * 123 -> 123
 *
 * sonrakiNo++:
 * Önce sayacın mevcut değeri kullanılır,
 * sonra sayaç bir artırılır.
 *
 * Yani ilk bilette:
 *
 * Kullanılan sayı = 1
 * Sonraki sayaç değeri = 2
 *
 * İkinci bilette:
 *
 * Kullanılan sayı = 2
 * Sonraki sayaç değeri = 3
 *
 *
 * ADIM 5:
 *
 * this.biletNo = "BLT-" +
 *         String.format("%03d", sonrakiNo++);
 *
 * Bir önceki adımda üretilen sayının
 * başına "BLT-" eklenir.
 *
 * İlk bilet:
 *
 * "BLT-" + "001" = "BLT-001"
 *
 * İkinci bilet:
 *
 * "BLT-" + "002" = "BLT-002"
 *
 * Sonuç her nesnenin kendi biletNo alanına
 * atanır.
 *
 *
 * ADIM 6:
 *
 * uretilen++;
 *
 * Sınıfın ortak üretilen sayacı bir artar.
 *
 * Bu kod her başarılı constructor
 * çalışmasında yürütülür.
 *
 *
 * ============================================================
 * 6. METOTLARI NEDEN FARKLI YAZDIK?
 * ============================================================
 *
 * A) public String bilgi()
 *
 * Bu metot:
 *
 * return biletNo + " / " + ad;
 *
 * sonucunu döndürür.
 *
 * Hangi biletin bilgilerini döndüreceğini
 * bilmesi gerekir.
 *
 * a.bilgi() -> Ali'nin biletini döndürür.
 *
 * b.bilgi() -> Ayşe'nin biletini döndürür.
 *
 * Bu nedenle bilgi() STATIC DEĞİLDİR.
 *
 * Bir örnek metodudur.
 *
 *
 * B) public static int uretilenSayisi()
 *
 * Bu metot:
 *
 * return uretilen;
 *
 * sonucunu döndürür.
 *
 * Herhangi bir biletin adı veya
 * numarasıyla ilgilenmez.
 *
 * Yalnızca sınıfın ortak sayacını okur.
 *
 * Bu yüzden static olarak yazılmıştır.
 *
 * Kullanımı:
 *
 * EtkinlikBileti.uretilenSayisi()
 *
 * Burada nesne oluşturmaya gerek yoktur.
 *
 *
 * 7. STATIC METOT NEDEN NESNE ALANINA
 *    DOĞRUDAN ERİŞEMEZ?
 * ------------------------------------------------------------
 *
 * Diyelim ki şöyle bir metot yazdık:
 *
 * public static void yazdir() {
 *     System.out.println(ad);
 * }
 *
 * Bu kod derleme hatası verir.
 *
 * NEDEN?
 *
 * Çünkü ad alanı her nesnede ayrıdır.
 *
 * Ali'nin adını mı yazdıracağız?
 * Ayşe'nin adını mı yazdıracağız?
 *
 * Static metot belirli bir nesneye bağlı
 * çalışmadığı için bunu bilemez.
 *
 * Static metot içinde this de bulunmaz.
 *
 * Ancak bir nesne parametresi alırsa
 * o nesne üzerinden örnek üyeye erişebilir.
 *
 * TERSİ İSE MÜMKÜNDÜR:
 *
 * Bir örnek metodu static üyeye erişebilir.
 *
 * Çünkü örnek metodu hem kendi nesnesini
 * hem de ait olduğu sınıfı bilir.
 *
 *
 * ============================================================
 * 8. MAIN METODUNUN ÇALIŞMA SIRASI
 * ============================================================
 *
 * 1) İlk satır:
 *
 * EtkinlikBileti.uretilenSayisi()
 *
 * Henüz bilet oluşturulmadığı için 0 döner.
 *
 * Ekrana:
 * 0
 *
 * yazılır.
 *
 * 2) Ali için yeni bilet oluşturulur.
 *
 * biletNo = BLT-001
 * sonrakiNo = 2
 * uretilen = 1
 *
 * 3) Ayşe için yeni bilet oluşturulur.
 *
 * biletNo = BLT-002
 * sonrakiNo = 3
 * uretilen = 2
 *
 * 4) a.bilgi() çağrılır.
 *
 * BLT-001 / Ali
 *
 * yazılır.
 *
 * 5) b.bilgi() çağrılır.
 *
 * BLT-002 / Ayse
 *
 * yazılır.
 *
 * 6) EtkinlikBileti.uretilenSayisi()
 *    tekrar çağrılır.
 *
 * Ortak sayaç artık 2 olduğu için:
 *
 * 2
 *
 * yazılır.
 *
 *
 * ============================================================
 * 9. HOCANIN VERMEK İSTEDİĞİ KAZANIMLAR
 * ============================================================
 *
 * KAZANIM 1:
 *
 * Bir alanın static olup olmayacağına
 * ezbere karar vermemeliyiz.
 *
 * Önce şu soruyu sormalıyız:
 *
 * "Bu bilgi tek bir nesneye mi ait,
 * yoksa sınıfın tamamına mı?"
 *
 *
 * KAZANIM 2:
 *
 * Nesneye özel bilgiler örnek alanıdır.
 *
 * Örnek:
 * ad, biletNo, bakiye, fiyat.
 *
 *
 * KAZANIM 3:
 *
 * Ortak bilgiler static alan olabilir.
 *
 * Örnek:
 * toplam üretilen bilet,
 * sıradaki bilet numarası.
 *
 *
 * KAZANIM 4:
 *
 * static üyeler için bir nesne oluşturmak
 * zorunda değiliz.
 *
 * Doğru ve okunabilir kullanım:
 *
 * EtkinlikBileti.uretilenSayisi()
 *
 *
 * KAZANIM 5:
 *
 * Static metot doğrudan nesne alanına
 * erişemez çünkü belirli bir this'i yoktur.
 *
 * main() de static bir metottur.
 *
 *
 * KAZANIM 6:
 *
 * Otomatik sayaç, benzersiz numara üretme
 * sorumluluğunu sınıfa taşır.
 *
 * Ancak bu basit sayaç yalnızca tek program
 * çalışması içindeki sıralı kullanım
 * için düşünülmüştür.
 *
 * Program yeniden başlarsa sayaç sıfırlanır.
 *
 * Birden fazla iş parçacığı aynı anda
 * numara üretirse ayrıca güvenlik önlemleri
 * gerekir.
 *
 *
 * ============================================================
 * 10. SIK YAPILAN HATALAR
 * ============================================================
 *
 * HATA 1:
 * Her şeyi static yapmak.
 *
 * Özellikle nesneye özel bir alanın static
 * yapılması bütün nesnelerin aynı bilgiyi
 * paylaşmasına neden olur.
 *
 * HATA 2:
 * Derleme hatasını gidermek için düşünmeden
 * bir alanın başına static eklemek.
 *
 * HATA 3:
 * Static metodu nesne üzerinden çağırmak.
 *
 * a.uretilenSayisi() derlenebilir.
 *
 * Ama bu bilgi a nesnesine özel değildir.
 *
 * Tercih edilen:
 * EtkinlikBileti.uretilenSayisi()
 *
 * HATA 4:
 * static ile private kavramlarını karıştırmak.
 *
 * static  -> Üyenin kime ait olduğunu söyler.
 * private -> Üyeye kimlerin erişebileceğini
 *            belirler.
 *
 * HATA 5:
 * Sayaç değerini şu anda var olan nesnelerin
 * sayısı sanmak.
 *
 * Bizim uretilen sayacımız şimdiye kadar
 * üretilmiş biletlerin toplamını tutar.
 *
 *
 * ============================================================
 * GENEL SONUÇ
 * ============================================================
 *
 * Bu örnekte öğrendiğimiz asıl konu
 * numara biçimlendirmek değildir.
 *
 * Asıl konu şudur:
 *
 * BİLGİ TEK BİR NESNEYE AİTSE
 * -> ÖRNEK ALANI
 *
 * BİLGİ SINIFIN TAMAMINA AİTSE
 * -> STATIC ALAN
 *
 * Sınıf tasarlarken bu kararı doğru
 * vermek, programın doğru çalışması
 * açısından çok önemlidir.
 *
 * ============================================================
 */
