

public class EtkinlikBileti {

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

        this.biletNo = "BLT-" +  String.format("%03d", sonrakiNo++); 
        uretilen++;
    }

    public String bilgi() {
        return biletNo + " / " + ad;
    }

    public static int uretilenSayisi() {
        return uretilen;
    }
}


/*
 * ============================================================
 * HAFTA 06 - ORNEK 1 - SayacDemo.java - EtkinlikBileti.java
 * STATIC ALAN, ORNEK ALAN VE SAYAC DESENI
 * ============================================================
 *
 * 1. ORNEGIN AMACI VE COZMEYE CALISTIGIMIZ PROBLEM
 *
 * Bu örnekte bir etkinlik bilet sistemi tasarladık. Sistemde her biletin kendine ait bir adı ve bilet numarası olması gerekiyor. Ayrıca şu ana kadar toplam kaç bilet üretildiğini takip etmek istiyoruz.
 *
 * Önceki bilgilerimizle bilet numarasını constructor'a dışarıdan gönderebilirdik. Ancak bu yöntemde numaraları takip etme sorumluluğu sınıfı kullanan kişiye ait olurdu. Kullanıcı yanlışlıkla iki bilete aynı numarayı verebilirdi. Ayrıca sıradaki numaranın kaç olduğunu kendisi takip etmek zorunda kalırdı.
 *
 * Bu sorunu çözmek için numara üretme sorumluluğunu EtkinlikBileti sınıfına taşıdık. Her nesnede ayrı bir sayaç oluşturmak yerine bütün biletlerin ortak kullandığı bir sayaç tanımladık. İşte burada static anahtar sözcüğüne ihtiyaç duyduk.
 *
 *
 * 2. STATIC VE ORNEK ALANI ARASINDAKI FARK
 *
 * Normal bir alan, sınıftan oluşturulan her nesneye ayrı ayrı aittir. Örneğin private String ad; yazarsak Ali ve Ayşe için oluşturulan biletlerin ad alanları birbirinden bağımsız olur. Bir nesnenin adını değiştirmek diğer nesnenin adını değiştirmez.
 *
 * Buna karşılık private static int sonrakiNo = 1; yazdığımızda sonrakiNo artık tek tek bilet nesnelerine değil, EtkinlikBileti sınıfının kendisine ait olur. Bütün biletler aynı sayacı kullanır. Her yeni nesne oluşturulduğunda sayaç sıfırdan başlamaz.
 *
 * ÖNEMLİ: static bir erişim belirleyicisi değildir. static, üyenin sınıfa mı yoksa nesneye mi ait olduğunu söyler. private ise o üyeye nereden erişilebileceğini belirler. Bu iki anahtar sözcük birbirinden farklı görevler yapar.
 *
 *
 * 3. SINIFIN ALANLARINI TEK TEK INCELEYELIM
 *
 * private static int sonrakiNo = 1;
 *
 * private, alanın dışarıdan doğrudan değiştirilmesini engeller. static, bu sayacın sınıfa ait olduğunu belirtir. int, tam sayı tutulacağını söyler. Başlangıç değeri 1'dir çünkü ilk bilet numaramız BLT-001 olacaktır.
 *
 * Burada final kullanmadık. Çünkü her yeni bilet oluşturulduğunda sonrakiNo değerinin değişmesi gerekiyor.
 *
 * private static int uretilen = 0;
 *
 * Bu alan da sınıfın ortak alanıdır. Şimdiye kadar başarıyla üretilen bilet sayısını tutar. Başlangıçta hiç bilet olmadığı için değeri 0'dır. Her başarılı constructor çalışmasında uretilen++ ile bir artırılır.
 *
 * sonrakiNo ile uretilen birbirine benzese de farklı bilgileri temsil eder. sonrakiNo, sıradaki bilet için kullanılacak numarayı; uretilen ise bugüne kadar oluşturulan toplam bilet sayısını tutar.
 *
 * private final String biletNo;
 *
 * Bu alan static değildir çünkü her biletin numarası kendine özel olmalıdır. Ali'nin bilet numarası BLT-001, Ayşe'nin numarası BLT-002 olabilir. final sayesinde constructor içinde atanan biletNo referansı daha sonra yeniden atanamaz.
 *
 * private final String ad;
 *
 * Bu da örnek alanıdır. Her biletin sahibi farklı olabilir. Eğer ad alanını static yapsaydık bütün biletler aynı adı paylaşırdı. Bu, nesneye özel veriyi yanlışlıkla ortaklaştırmak anlamına gelirdi.
 *
 *
 * 4. CONSTRUCTOR'IN CALISMA MANTIGI
 *
 * public EtkinlikBileti(String ad)
 *
 * Bu constructor, yeni bilet oluşturulurken ad bilgisini parametre olarak alır. Bilet numarasını ise dışarıdan istemez; kendisi üretir.
 *
 * if (ad == null || ad.isBlank())
 *
 * Öncelikle verilen adın geçerli olup olmadığını kontrol ediyoruz. ad == null, hiçbir String nesnesi verilmediği anlamına gelir. ad.isBlank() ise metnin boş ya da yalnızca boşluk karakterlerinden oluşup oluşmadığını denetler. || operatörü koşullardan en az biri doğruysa if bloğuna girilmesini sağlar.
 *
 * null kontrolünü önce yazmamız da önemlidir. || operatörü kısa devre değerlendirme yaptığı için ad null ise isBlank() çağrılmaz. Böylece NullPointerException oluşmaz.
 *
 * throw new IllegalArgumentException("Ad bos olamaz.");
 *
 * Geçersiz ad verilmişse exception fırlatıyoruz. Böylece geçersiz bilgilerle bir bilet oluşturulmasını engelliyoruz. Kontrolü numara üretiminden önce yapmamız önemlidir; başarısız bir oluşturma denemesinin ortak sayaçları artırmasını istemeyiz.
 *
 * this.ad = ad;
 *
 * Constructor parametresiyle gelen ad değerini oluşturulan nesnenin kendi ad alanına atıyoruz. this.ad nesnenin alanıdır; sağ taraftaki ad ise parametredir.
 *
 * this.biletNo = "BLT-" + String.format("%03d", sonrakiNo++);
 *
 * Bu satır bilet numarasını oluşturur. "BLT-" sabit metin kısmıdır. String.format("%03d", ...) ise sayıyı en az üç basamaklı gösterir ve gerekirse başına sıfır ekler. Örneğin 1 sayısı 001, 12 sayısı 012 olarak yazılır.
 *
 * sonrakiNo++ ifadesi önce sayacın mevcut değerini kullanır, ardından sayacı bir artırır. İlk bilet oluşturulurken kullanılan değer 1'dir ve işlem sonunda sonrakiNo 2 olur. İkinci bilet oluşturulurken 2 kullanılır ve sayaç 3 olur.
 *
 * Böylece ilk biletin biletNo alanına BLT-001, ikinci biletin biletNo alanına BLT-002 atanır. Her bilet kendi numarasını saklar; fakat numaraları üretirken aynı ortak sayaçtan yararlanır.
 *
 * uretilen++;
 *
 * Biletin alanları hazırlandıktan sonra üretilen toplam nesne sayısını bir artırıyoruz. Bu örnekte constructor'ın son işlemi olduğu için başarılı şekilde oluşturulan her bilet sayaca eklenmiş olur.
 *
 *
 * 5. METOTLARIN NEDEN FARKLI TANIMLANDIGI
 *
 * public String bilgi()
 *
 * Bu metot biletNo ve ad alanlarını birleştirerek döndürür. Her iki alan da belirli bir nesneye aittir. Dolayısıyla metot hangi nesne üzerinden çağrıldığını bilmelidir. a.bilgi() Ali'nin, b.bilgi() Ayşe'nin bilgilerini döndürür. Bu nedenle bilgi() bir örnek metodudur, static değildir.
 *
 * public static int uretilenSayisi()
 *
 * Bu metot yalnızca sınıfın ortak uretilen alanını okur. Belirli bir biletin adına veya numarasına ihtiyacı olmadığı için static tanımlanmıştır. EtkinlikBileti.uretilenSayisi() şeklinde, nesne oluşturmadan çağrılabilir.
 *
 * Bir static metodun doğrudan örnek alanına erişememesinin sebebi this referansının bulunmamasıdır. Örneğin static bir metot içinde doğrudan ad yazarsak Java hangi biletin adını kastettiğimizi bilemez. Ancak static metot, kendisine parametre olarak verilen bir nesne üzerinden o nesnenin erişilebilir alanlarını veya metotlarını kullanabilir.
 *
 * Tersine, bir örnek metodu static alanlara erişebilir. Çünkü örnek metodu hem kendi nesnesinin bilgilerine hem de sınıfın ortak üyelerine ulaşabilir.
 *
 * main() metodu da static olduğundan, nesneye özel metotları kullanmak istediğimizde önce bir nesne oluşturup o nesne üzerinden çağrı yaparız.
 *
 *
 * 6. PROGRAMIN CALISMA SIRASI
 *
 * İlk satırda EtkinlikBileti.uretilenSayisi() çağrılır. Henüz hiçbir bilet üretilmediği için ortak uretilen alanının değeri 0'dır ve ekrana 0 yazdırılır.
 *
 * Ardından new EtkinlikBileti("Ali") çalışır. Constructor ad bilgisini doğrular, Ali'yi nesnenin alanına atar, sonrakiNo sayacını kullanarak BLT-001 numarasını üretir ve ortak uretilen alanını 1 yapar.
 *
 * Sonraki new EtkinlikBileti("Ayse") çağrısında ortak sayaç kaldığı yerden devam eder. Ayşe'nin biletine BLT-002 numarası verilir, sonrakiNo 3 ve uretilen 2 olur.
 *
 * a.bilgi() çağrısı BLT-001 / Ali sonucunu verir. b.bilgi() çağrısı BLT-002 / Ayse sonucunu verir. Son satırdaki EtkinlikBileti.uretilenSayisi() çağrısı ise artık 2 döndürür.
 *
 * BEKLENEN ÇIKTI:
 *
 * 0
 * BLT-001 / Ali
 * BLT-002 / Ayse
 * 2
 *
 *
 * 7. HOCANIN BU ORNEKLE KAZANDIRMAK ISTEDIKLERI
 *
 * Bir alanı static yapmadan önce "Bu bilgi bütün nesneler için ortak mı, yoksa her nesnenin kendine ait bilgisi mi?" sorusunu sormalıyız.
 *
 * Nesneye özel veriler örnek alanlarında tutulmalıdır. Sınıf genelindeki ortak sayaçlar veya kurallar ise uygun olduğunda static alanlarda tutulabilir.
 *
 * Static üyelere sınıf adı üzerinden erişmek tercih edilir. Örneğin a.uretilenSayisi() yazımı derlenebilse de EtkinlikBileti.uretilenSayisi() daha doğru ve anlaşılır bir kullanımdır.
 *
 * Static metotların doğrudan this kullanamamasının nedeni belirli bir nesneye bağlı olmamalarıdır. Bu, özellikle main() içinde alınabilecek derleme hatalarını anlamak için önemlidir.
 *
 * Sayaç deseni, ortak bilgi yönetimini ve numara üretme sorumluluğunu sınıfın içine taşır. Böylece sınıfı kullanan kodun her defasında benzersiz numara hesaplamasına gerek kalmaz.
 *
 *
 * 8. SIK YAPILAN HATALAR VE DIKKAT EDILECEKLER
 *
 * HATA 1: Derleme hatasını gidermek amacıyla nesneye özel bir alanı düşünmeden static yapmak. Örneğin ad, fiyat veya bakiye gibi alanları static yapmak farklı nesnelerin bilgilerini birbirine karıştırabilir.
 *
 * HATA 2: static ile private kavramlarını aynı sanmak. static aidiyeti, private ise erişim iznini belirler.
 *
 * HATA 3: Static sayacın her yeni nesnede sıfırlandığını düşünmek. Ortak sayaç, aynı sınıfın nesneleri arasında paylaşılır.
 *
 * HATA 4: uretilen sayacının şu anda bellekte yaşayan nesne sayısını gösterdiğini düşünmek. Bizim sayacımız geçmişte üretilmiş toplam nesne sayısını tutar; bir nesnenin artık kullanılmaması sayacı azaltmaz.
 *
 * HATA 5: Bu basit sayacın bütün gerçek sistemlerde benzersizliği garanti ettiğini sanmak. Program yeniden başladığında sayaç başlangıç değerine döner. Aynı anda birden fazla iş parçacığı numara üretirse de ek önlem gerekir. Bu örnek tek program çalışması ve sıralı kullanım için tasarlanmıştır.
 *
 *
 * 9. GENEL SONUC
 *
 * Bu örneğin asıl amacı bilet numarası biçimlendirmek değil, sınıfa ait bilgi ile nesneye ait bilgiyi ayırabilmektir.
 *
 * Örnek alanı her nesnenin bağımsız durumunu saklar. Static alan sınıfın ortak durumunu saklar. Static metot, belirli bir nesne gerektirmeyen sınıf düzeyindeki işlemleri gerçekleştirebilir.
 *
 * EN ÖNEMLİ KURAL: "Bir bilgi hangi nesneye ait?" sorusunun cevabı belirli bir nesne ise örnek alanı; cevap sınıfın tamamı ise static alan düşünülmelidir.
 *
 * ============================================================
 */
