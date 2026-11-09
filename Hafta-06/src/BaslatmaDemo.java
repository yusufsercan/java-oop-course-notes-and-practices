
class Oturum {

    private static int olusturulan = 0;

    static {
        System.out.println("STATIC BLOK");
    }

    private String kullanici = "atanmadi";

    {
        System.out.println("ORNEK BLOK: " + kullanici);
    }

    public Oturum(String kullanici) {
        this.kullanici = kullanici;
        olusturulan++;

        System.out.println("CONSTRUCTOR: " + this.kullanici);
    }

    public static int toplam() {
        return olusturulan;
    }
}

public class BaslatmaDemo {

    public static void main(String[] args) {

        System.out.println("MAIN BASLADI");

        Oturum a = new Oturum("Ali");
        Oturum b = new Oturum("Ayse");

        System.out.println("TOPLAM: " + Oturum.toplam());
    }
}

/*
 * ============================================================
 * HAFTA 06 - ORNEK 2
 * SINIF BASLATMA SIRASI, STATIC BLOK VE CONSTRUCTOR
 * ============================================================
 *
 * 1. BU ORNEGI NEDEN YAZDIK?
 *
 * Önceki haftalarda new ile nesne oluşturulduğunda constructor'ın çalıştığını öğrendik. Ancak bir Java sınıfında yalnızca constructor bulunmaz. Static alanlar, static bloklar, örnek alan başlatıcıları ve örnek başlatma blokları da bulunabilir.
 *
 * Bu yapıların hepsinin aynı anda veya her nesnede çalıştığını düşünmek yanlış olur. Bu örnekte asıl amacımız, hangi başlangıç işleminin ne zaman yapıldığını ve kaç kez tekrarlandığını anlamaktır.
 *
 * Static işlemler sınıf düzeyindedir; örnek işlemleri ise oluşturulan nesnelere aittir. Bir sınıftan iki nesne üretildiğinde static başlatma işlemi bir kez, nesneye özel başlangıç işlemleri ise iki kez yapılır.
 *
 *
 * 2. STATIC BLOK NEDIR, NEDEN KULLANILIR?
 *
 * static { ... } biçimindeki yapı static başlatma bloğudur. Sınıf ilk kez aktif olarak kullanılıp başlatıldığında çalışır. Her new işleminde yeniden çalışmaz.
 *
 * Bizim örneğimizdeki static blok yalnızca "STATIC BLOK" mesajını yazdırıyor. Bunun amacı çalışma sırasını görmektir. Gerçek bir uygulamada static blok kullanmak için daha anlamlı bir neden olmalıdır. Örneğin bir sınıfın ortak kurallarını doğrulamak, karmaşık bir sabit dizisini hazırlamak veya bir defalık başlangıç hesaplaması yapmak için kullanılabilir.
 *
 * Basit bir değer ataması için static blok kullanmak zorunda değiliz. Örneğin private static int olusturulan = 0; yazmak zaten yeterlidir.
 *
 *
 * 3. SINIF NE ZAMAN BASLATILIR?
 *
 * Java'da sınıflar, her zaman programın en başında topluca başlatılmaz. Bir sınıfın ilk aktif kullanımı, o sınıfın başlatılmasını tetikleyebilir. Örneğin o sınıftan nesne oluşturulması, static bir metodun çağrılması veya derleme zamanı sabiti olmayan static bir alanın kullanılması böyle bir olaydır.
 *
 * Bu davranış tembel başlatma olarak düşünülebilir. Sınıfın ihtiyaç duyulmayan başlangıç işlemleri gereksiz yere yapılmaz.
 *
 * Bizim programımızda Oturum sınıfının ilk aktif kullanımı new Oturum("Ali") satırıdır. Bu nedenle Oturum'un static bloğu, main metodundaki "MAIN BASLADI" mesajından sonra çalışır.
 *
 * ÖNEMLİ AYRINTI: Her static alan okuması sınıfı başlatmak zorunda değildir. Derleme zamanı sabiti olan bazı static final alanlar, sınıf başlatılmadan da okunabilir. Bu özel durumu üçüncü örnekte göreceğiz.
 *
 *
 * 4. PRIVATE STATIC INT OLUSTURULAN = 0;
 *
 * Bu alan sınıfa aittir ve toplam kaç Oturum nesnesinin oluşturulduğunu tutar. Başlangıç değeri 0'dır. Her yeni Oturum nesnesinin constructor'ında olusturulan++ çalıştığı için ortak sayaç artar.
 *
 * Alan static olduğu için Ali ve Ayşe nesneleri kendi ayrı olusturulan sayaçlarına sahip değildir. İki nesne de aynı ortak değere katkıda bulunur.
 *
 *
 * 5. STATIC BLOĞUN ÇALIŞMASI
 *
 * static {
 *     System.out.println("STATIC BLOK");
 * }
 *
 * Bu blok Oturum sınıfının başlatılması sırasında çalışır. İlk new işleminden önce çalışması gerekir çünkü sınıfın static başlatması nesnenin oluşturulmasından önce tamamlanır.
 *
 * Ancak ikinci new işleminde tekrar çalışmaz. Çünkü sınıf aynı çalışma sürecinde zaten başarılı biçimde başlatılmıştır.
 *
 *
 * 6. ORNEK ALAN BASLATICISI
 *
 * private String kullanici = "atanmadi";
 *
 * Bu alan static değildir. Her Oturum nesnesinin kendine ait kullanici bilgisi vardır. Yeni bir nesne oluşturulurken önce Java'nın varsayılan değerleri atanır; referans türleri için bu değer null'dır. Daha sonra örnek alan başlatıcısı çalışarak kullanici alanına "atanmadi" değerini verir.
 *
 * İlk Oturum nesnesinde bu işlem yapılır. İkinci Oturum nesnesi için de yeniden yapılır. Çünkü her nesne kendi alanlarını saklar.
 *
 *
 * 7. ORNEK BASLATMA BLOGU
 *
 * {
 *     System.out.println("ORNEK BLOK: " + kullanici);
 * }
 *
 * Bu bloğun başında static bulunmaz. Dolayısıyla örnek başlatma bloğudur ve her yeni nesnede çalışır. Constructor gövdesinden önce çalışır.
 *
 * Burada ekrana "ORNEK BLOK: atanmadi" yazılmasının özel bir nedeni vardır. Nesnenin kullanici alanına henüz constructor içindeki gerçek ad atanmamıştır. Örnek alan başlatıcısı daha önce "atanmadi" değerini atadığı için blok bu değeri görür.
 *
 * İlk nesnenin constructor'ına "Ali" gönderilmiş olsa bile örnek bloğu "Ali" yazdırmaz. Çünkü constructor parametresinin nesnenin alanına atanması henüz gerçekleşmemiştir.
 *
 *
 * 8. CONSTRUCTOR'I SATIR SATIR INCELEYELIM
 *
 * public Oturum(String kullanici)
 *
 * Constructor, yeni oturum nesnesi oluşturulurken kullanıcı adını parametre olarak alır.
 *
 * this.kullanici = kullanici;
 *
 * Burada parametreden gelen değer, oluşturulan nesnenin kendi kullanici alanına atanır. İlk nesnede "Ali", ikinci nesnede "Ayse" atanır. this.kullanici nesnenin alanını, sağ taraftaki kullanici parametreyi ifade eder.
 *
 * olusturulan++;
 *
 * Sınıfa ait ortak sayaç bir artırılır. Constructor bir nesneye ait bilgileri kullanabilir ve aynı zamanda sınıfın static alanlarına da erişebilir. Bu yüzden constructor içinde olusturulan++ yazmamız geçerlidir.
 *
 * System.out.println("CONSTRUCTOR: " + this.kullanici);
 *
 * Bu satır constructor içindeki atamalardan sonra çalışır. Dolayısıyla artık "atanmadi" değil, gerçek kullanıcı adı yazdırılır.
 *
 *
 * 9. MAIN METODUNUN CALISMA SIRASI
 *
 * Önce "MAIN BASLADI" yazdırılır. Bu sırada Oturum sınıfının static bloğu henüz çalışmamıştır.
 *
 * Birinci new Oturum("Ali") çağrısında Oturum sınıfı başlatılır ve static blok bir kez çalışarak "STATIC BLOK" mesajını yazdırır. Ardından yeni nesnenin alanları hazırlanır, kullanici alanına "atanmadi" atanır ve örnek bloğu bu değeri yazdırır. Son olarak constructor çalışır, kullanici alanını "Ali" yapar, sayacı 1'e çıkarır ve "CONSTRUCTOR: Ali" mesajını yazdırır.
 *
 * İkinci new Oturum("Ayse") çağrısında static blok artık çalışmaz. Yeni nesnenin kullanici alanı yine başlangıçta "atanmadi" olur, örnek bloğu çalışır ve ardından constructor bu değeri "Ayse" olarak değiştirir. Ortak sayaç 2 olur.
 *
 * Son satırda Oturum.toplam() çağrılır. Bu static metot ortak olusturulan alanını döndürür. Böylece "TOPLAM: 2" yazdırılır.
 *
 * BEKLENEN ÇIKTI:
 *
 * MAIN BASLADI
 * STATIC BLOK
 * ORNEK BLOK: atanmadi
 * CONSTRUCTOR: Ali
 * ORNEK BLOK: atanmadi
 * CONSTRUCTOR: Ayse
 * TOPLAM: 2
 *
 *
 * 10. BASLATMA SIRASININ GENEL KURALI
 *
 * Sınıf ilk kez aktif olarak kullanıldığında static alan başlatıcıları ve static bloklar, kaynak kodda yazıldıkları sıraya göre çalışır. Bunlar sınıf başına bir kez gerçekleştirilir.
 *
 * Yeni bir nesne oluşturulduğunda nesnenin alanlarına önce varsayılan değerler atanır. Daha sonra örnek alan başlatıcıları ile örnek bloklar kaynak kod sırasına göre çalışır. En son constructor gövdesi çalışır.
 *
 * Burada önemli bir ayrıntı, static alanlar ile static blokların birbirinden tamamen bağımsız iki aşama gibi düşünülmemesidir. İkisi de kaynak kodda bulundukları sıraya göre çalışır. Aynı kural örnek alan başlatıcıları ve örnek bloklar için de geçerlidir.
 *
 * Bu sırayı yanlış anlamak özellikle başlangıç değeri başka bir alana bağlı olan sınıflarda sessiz hatalara yol açabilir. Örneğin daha sonra atanacak bir static değerin erken kullanılması beklenmedik 0 veya null değerleriyle karşılaşmamıza neden olabilir. Bazı ileri başvuru biçimleri ise doğrudan derleme hatası verir.
 *
 *
 * 11. STATIC BLOĞUN GERCEK AMACI: KURULUM DOGRULAMASI
 *
 * Hocanın SinavKurallari örneğinde static blok yalnızca mesaj yazdırmak için kullanılmıyor. Vize, ödev, laboratuvar ve final ağırlıklarının toplamı kontrol ediliyor. Kurallar yanlışsa sınıfın başlatılması sırasında ExceptionInInitializerError oluşuyor.
 *
 * Bunun amacı yanlış kurallarla hesaplama yapan bir programın sessizce devam etmesini engellemektir. Yani static blok, sınıfın ortak başlangıç durumunun doğru hazırlanmasını güvenceye almak için kullanılabilir.
 *
 *
 * 12. SIK YAPILAN HATALAR
 *
 * HATA 1: Static bloğun her yeni nesnede çalıştığını düşünmek. Static blok sınıf başına bir kez çalışır; örnek bloğu ise her yeni nesne için çalışır.
 *
 * HATA 2: Constructor'ın örnek başlatma bloğundan önce çalıştığını sanmak. Örnek bloğu constructor gövdesinden önce çalışır.
 *
 * HATA 3: Örnek bloğunun constructor parametresinin alana atanmış hâlini göreceğini düşünmek. Alan ataması constructor gövdesindeyse henüz yapılmamıştır.
 *
 * HATA 4: Static alanların ve blokların kaynak koddaki sırasını önemsememek. Bu sıra programın davranışını etkileyebilir.
 *
 * HATA 5: Basit atamalar için gereksiz yere static blok kullanmak. Normal bir alan başlatıcısı yeterliyse onu tercih etmek daha anlaşılırdır.
 *
 *
 * 13. BU ORNEKTEN KAZANIMLARIMIZ
 *
 * Sınıfın başlatılması ile nesnenin başlatılmasının farklı işlemler olduğunu öğrendik. Static bloğun yalnızca bir kez, örnek başlatma bloğunun ise her yeni nesnede çalıştığını gördük.
 *
 * Alan başlatıcıları, başlatma blokları ve constructor gövdesinin hangi sırada çalıştığını öğrendik. Ayrıca bir sınıfın ilk aktif kullanımının ne zaman gerçekleştiğini ve static blokların hangi gerçek problemlerde kullanılabileceğini gördük.
 *
 * EN ÖNEMLİ KURAL: Önce sınıfın ortak başlangıç işlemleri yapılır; ardından her yeni nesnenin kendi başlangıç işlemleri gerçekleştirilir. Static ve örnek başlatma işlemleri farklı sorumluluklara sahiptir.
 *
 * ============================================================
 */
