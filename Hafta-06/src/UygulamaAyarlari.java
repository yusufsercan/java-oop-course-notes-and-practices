public class UygulamaAyarlari {

    // Sinifin ortak nesnesi
    private static final UygulamaAyarlari ORNEK =
            new UygulamaAyarlari();

    // Ortak nesnenin kendi alani
    private String tema;

    // Disaridan nesne olusturulmasini engelle
    private UygulamaAyarlari() {

        this.tema = "acik";

        System.out.println("AYARLAR OLUSTURULDU");
    }

    // Var olan ortak nesneyi dondur
    public static UygulamaAyarlari getInstance() {

        return ORNEK;
    }

    public void setTema(String yeniTema) {

        this.tema = yeniTema;
    }

    public String getTema() {

        return tema;
    }
}

/*
 * ============================================================
 * HAFTA 06 - ORNEK 4
 * SINGLETON MANTIGI, PRIVATE CONSTRUCTOR
 * VE ORTAK NESNE KULLANIMI
 * ============================================================
 *
 * 1. BU ORNEGI NEDEN YAZDIK?
 *
 * Önceki haftalarda constructor kullanarak farklı nesneler oluşturmayı öğrendik. Örneğin Ogrenci sınıfından iki öğrenci oluşturduğumuzda her öğrencinin kendine ait bir adı ve yaşı bulunur.
 *
 * Ancak her problemde sürekli yeni nesneler oluşturmak zorunda değiliz. Bazı durumlarda programın farklı bölümlerinin aynı ortak nesneye erişmesini isteyebiliriz.
 *
 * Bu örnekte uygulamanın tema ayarlarını düşünelim. Kullanıcı temayı koyu olarak değiştirdiğinde uygulamanın başka bir bölümü eski açık tema ayarını kullanmamalıdır. Eğer her bölüm kendi ayarlar nesnesini oluşturursa birbirinden bağımsız ve tutarsız ayarlar ortaya çıkabilir.
 *
 * Bu problemi çözmenin bir yolu tek bir ayarlar nesnesi oluşturup onu ihtiyaç duyan bölümlere aktarmaktır. Bu geçerli bir yöntemdir. Ancak burada öğrendiğimiz yöntem, ortak nesnenin oluşturulmasını ve kullanılmasını sınıfın kendisinin kontrol etmesidir.
 *
 * Bu tasarımın temel fikri Singleton desenidir. Amaç, sınıfın dışarıya tek bir ortak nesne sunması ve bu nesneye farklı yerlerden erişilmesini sağlamaktır.
 *
 *
 * 2. SINGLETON TAM OLARAK NEDIR?
 *
 * Singleton, bir sınıfın ortak kullanılan tek bir örneğini sunmaya yönelik bir tasarım desenidir. Sınıf dışından normal yollarla sürekli yeni nesne oluşturulmasını engellemek ve var olan ortak nesneye belirli bir metot üzerinden erişim sağlamak için kullanılır.
 *
 * Bizim sınıfımız UygulamaAyarlari'dır. Bu sınıftan oluşturulan ortak nesne ise ORNEK alanında saklanır.
 *
 * Sınıf ile nesneyi birbirine karıştırmamalıyız. UygulamaAyarlari bir sınıftır. new UygulamaAyarlari() ifadesiyle oluşturulan şey ise o sınıfın bir nesnesidir.
 *
 *
 * 3. ORTAK NESNEYI TUTAN ALANI INCELEYELIM
 *
 * private static final UygulamaAyarlari ORNEK =
 *         new UygulamaAyarlari();
 *
 * private, ORNEK alanına başka sınıfların doğrudan erişmesini engeller. static, bu alanın belirli bir ayarlar nesnesine değil, UygulamaAyarlari sınıfının kendisine ait olduğunu belirtir. final ise ORNEK referansının sonradan başka bir nesneye atanmasını engeller.
 *
 * UygulamaAyarlari alanın türüdür. ORNEK ise ortak nesneyi gösteren referansın adıdır.
 *
 * new UygulamaAyarlari() ifadesi ortak nesneyi oluşturur. Nesnenin referansı ORNEK alanında saklanır. Sınıf başlatılırken bu alanın başlatıcısı bir kez çalışır.
 *
 * Burada dikkat edilmesi gereken nokta, static olanın ORNEK alanı olmasıdır. ORNEK'in gösterdiği nesnenin içindeki tema alanı static değildir.
 *
 *
 * 4. ORTAK NESNE NE ZAMAN OLUSTURULUYOR?
 *
 * UygulamaAyarlari sınıfı ilk kez aktif olarak kullanılıp başlatıldığında ORNEK alanının başlangıç işlemi gerçekleştirilir. Bu sırada new UygulamaAyarlari() çağrılır ve ortak ayarlar nesnesi oluşturulur.
 *
 * Sonraki getInstance() çağrılarında yeni nesne üretilmez. ORNEK alanının sakladığı mevcut nesne referansı geri döndürülür.
 *
 * Bu yönteme erken başlatma (eager initialization) denir. Çünkü ortak nesne, sınıfın başlatılması sırasında oluşturulur.
 *
 * Daha sonra sınıfın başka yerlerinden getInstance() çağrılması aynı nesnenin tekrar oluşturulması anlamına gelmez.
 *
 *
 * 5. TEMA ALANI NEDEN STATIC DEGIL?
 *
 * private String tema;
 *
 * Bu normal bir örnek alanıdır. Yani tema, oluşturulmuş UygulamaAyarlari nesnesinin durumudur.
 *
 * Peki bütün kullanıcılar aynı temayı nasıl görüyor? Çünkü bütün kullanıcılar aynı ayarlar nesnesine erişiyor. Ortak görünen şeyin sebebi tema alanının static olması değil, kullanılan nesnenin aynı olmasıdır.
 *
 * Bu ayrım çok önemlidir. Bir alanın static olarak sınıfa ait olması ile farklı referansların aynı nesneyi göstermesi aynı şey değildir.
 *
 * Örneğimizde yalnızca bir ortak nesne oluşturduğumuz için onun tek bir tema alanı vardır. Farklı referanslardan bu nesneye erişildiğinde aynı tema alanı okunur veya değiştirilir.
 *
 *
 * 6. PRIVATE CONSTRUCTOR'I INCELEYELIM
 *
 * private UygulamaAyarlari() {
 *     this.tema = "acik";
 *     System.out.println("AYARLAR OLUSTURULDU");
 * }
 *
 * Normalde constructor public olsaydı başka bir sınıf new UygulamaAyarlari() yazarak yeni bir nesne oluşturabilirdi.
 *
 * Ancak biz constructor'ı private yaptık. Böylece başka sınıfların doğrudan yeni ayarlar nesnesi üretmesini engelledik.
 *
 * Peki private constructor varken sınıfın içindeki new UygulamaAyarlari() nasıl çalışıyor? Çünkü private üyeler aynı sınıfın kendi kodu içinden kullanılabilir. ORNEK alanını başlatan ifade de UygulamaAyarlari sınıfının içinde bulunduğu için constructor'a erişebilir.
 *
 * Constructor'ın ilk satırında this.tema = "acik"; ile yeni nesnenin başlangıç temasını belirliyoruz. Buradaki this, oluşturulmakta olan ayarlar nesnesini gösterir.
 *
 * Daha sonra ekrana "AYARLAR OLUSTURULDU" yazdırıyoruz. Bu mesajın amacı constructor'ın kaç kez çalıştığını gözlemlemektir.
 *
 * Bizim örneğimizde mesaj yalnızca bir kez yazdırılır çünkü ortak nesne yalnızca bir kez oluşturulur.
 *
 *
 * 7. GETINSTANCE() METODUNU SATIR SATIR INCELEYELIM
 *
 * public static UygulamaAyarlari getInstance() {
 *     return ORNEK;
 * }
 *
 * public, metodun başka sınıflardan çağrılabilmesini sağlar. static, metodun çağrılması için önceden bir UygulamaAyarlari nesnesinin bulunmasının gerekmediğini gösterir.
 *
 * UygulamaAyarlari, metodun dönüş türüdür. Yani metot bir UygulamaAyarlari nesnesinin referansını döndürür.
 *
 * getInstance(), ortak nesneyi elde etmek için kullanılan metot ismidir. Bu isim Singleton deseninde yaygın olarak kullanılır.
 *
 * return ORNEK; satırı sınıfın sakladığı mevcut nesnenin referansını döndürür. Burada yeni bir nesne üretilmez.
 *
 * ÖNEMLİ FARK:
 *
 * return ORNEK;
 *
 * Mevcut nesnenin referansını döndürür.
 *
 * return new UygulamaAyarlari();
 *
 * ise her çağrıda yeni nesne oluştururdu. İkinci ifade bizim Singleton tasarımımızda kullanılmıyor.
 *
 * Bu fark, Hafta 5'te öğrendiğimiz static factory metotlarıyla da bağlantılıdır. Static fabrika metotları gerektiğinde yeni nesne üretebilir veya mevcut nesneleri döndürebilir. Bizim getInstance() metodumuz her seferinde mevcut ortak nesneyi döndürmek amacıyla yazılmıştır.
 *
 *
 * 8. SETTER VE GETTER METOTLARI
 *
 * public void setTema(String yeniTema) {
 *     this.tema = yeniTema;
 * }
 *
 * Bu metot, üzerinde çağrıldığı nesnenin tema alanını değiştirir. yeniTema parametresi dışarıdan gelen yeni değerdir. this.tema ise nesnenin mevcut alanıdır.
 *
 * Metot static değildir çünkü belirli bir nesnenin durumunu değiştirir. Örneğin a.setTema("koyu") çağrısında a referansının gösterdiği ayarlar nesnesinin teması değiştirilir.
 *
 * public String getTema() {
 *     return tema;
 * }
 *
 * Bu metot da örnek metodudur. Üzerinde çağrıldığı nesnenin tema alanını döndürür.
 *
 * Kodumuz öğrenme amaçlı kısa tutulduğu için setTema() metodunda doğrulama yapılmadı. Gerçek bir uygulamada yalnızca "acik" ve "koyu" gibi izin verilen değerlerin kabul edilmesi daha güvenli bir tasarım olabilir.
 *
 *
 * 9. MAIN METODUNUN CALISMA SIRASI
 *
 * İlk satırda UygulamaAyarlari.getInstance() çağrılır. Sınıf ilk kez aktif olarak kullanıldığı için başlatılır. ORNEK alanının başlangıç işlemi gerçekleşir ve yeni bir UygulamaAyarlari nesnesi oluşturulur.
 *
 * Constructor bu nesnenin temasını "acik" yapar ve "AYARLAR OLUSTURULDU" mesajını yazdırır. Oluşturulan ortak nesnenin referansı a değişkenine verilir.
 *
 * İkinci satırda UygulamaAyarlari.getInstance() tekrar çağrılır. Ancak ORNEK alanı zaten vardır. Bu yüzden constructor yeniden çalışmaz. Mevcut nesnenin referansı b değişkenine verilir.
 *
 * Ardından a.setTema("koyu") çalışır. a referansının gösterdiği ortak nesnenin tema alanı "koyu" olarak değiştirilir.
 *
 * Sonraki satırda System.out.println(a == b) çalışır. == operatörü burada iki referansın aynı nesneyi gösterip göstermediğini karşılaştırır. a ve b aynı nesneyi gösterdiği için sonuç true olur.
 *
 * En son b.getTema() çağrılır. b de a ile aynı nesneyi gösterdiğinden, a üzerinden yapılan değişiklik b üzerinden de görülür. Bu yüzden ekrana "koyu" yazdırılır.
 *
 * BEKLENEN ÇIKTI:
 *
 * AYARLAR OLUSTURULDU
 * true
 * koyu
 *
 *
 * 10. REFERANS KARSILASTIRMASINI ANLAYALIM
 *
 * Ogrenci a = new Ogrenci("Ali", 20);
 * Ogrenci b = new Ogrenci("Ali", 20);
 *
 * Bu iki satır iki farklı nesne oluşturur. Nesnelerin alan değerleri aynı olsa bile referanslar farklı nesneleri gösterir. Dolayısıyla a == b sonucu false olur.
 *
 * Buna karşılık:
 *
 * UygulamaAyarlari a = UygulamaAyarlari.getInstance();
 * UygulamaAyarlari b = UygulamaAyarlari.getInstance();
 *
 * ifadelerinde iki farklı nesne oluşturulmaz. getInstance() aynı ORNEK referansını döndürdüğü için a == b sonucu true olur.
 *
 * Burada unutulmaması gereken şey, iki nesnenin aynı verilere sahip olmasının aynı nesne oldukları anlamına gelmemesidir. == ile nesne referanslarını karşılaştırdığımızda nesnelerin kimliğini karşılaştırmış oluruz.
 *
 *
 * 11. STATIC FINAL NESNENIN ICERIGINI KORUR MU?
 *
 * Hayır. private static final UygulamaAyarlari ORNEK bildirimindeki final, ORNEK alanına başka bir nesne referansı atanmasını engeller.
 *
 * Ancak ORNEK'in gösterdiği ayarlar nesnesinin iç durumu değiştirilebilir. Örneğin a.setTema("koyu") çağrısı geçerlidir çünkü nesne referansını değiştirmiyoruz; aynı nesnenin tema alanını değiştiriyoruz.
 *
 * Bu davranış üçüncü örnekteki final dizi konusuyla aynı mantığa dayanır. final, bir referansı yeniden atanmaya karşı korur ama gösterilen nesnenin her zaman değişmez olacağını garanti etmez.
 *
 *
 * 12. SINGLETON'IN AVANTAJLARI VE RISKLERI
 *
 * Singleton'ın önemli avantajı, ortak kullanılması gereken bir nesneye kontrollü erişim sağlamasıdır. Farklı bölümler aynı nesne üzerinden çalışabilir ve sınıf dışından rastgele yeni nesneler üretilmesi engellenebilir.
 *
 * Ancak bu yaklaşımın riskleri de vardır. Ortak nesnenin değiştirilebilir alanları bulunuyorsa bir yerde yapılan değişiklik programın başka bölümlerini etkileyebilir. Bu durum hataları takip etmeyi ve birim testleri yazmayı zorlaştırabilir.
 *
 * Bu nedenle Singleton her sınıfa uygulanması gereken bir yöntem değildir. Gerçekten tek bir ortak nesneye ihtiyaç varsa tercih edilmelidir.
 *
 * Hocanın ders notunda Singleton'ın ayrıntılarının Hafta 14'te yeniden ele alınacağı belirtiliyor. Bu hafta yalnızca private constructor, static nesne ve static erişim metodunun nasıl birlikte çalıştığını öğreniyoruz.
 *
 *
 * 13. SIK YAPILAN HATALAR
 *
 * HATA 1: getInstance() metodunun her çağrıda yeni nesne ürettiğini düşünmek. Bizim örneğimizde getInstance() mevcut ORNEK referansını döndürür.
 *
 * HATA 2: Private constructor'ın aynı sınıf içinden de çağrılamayacağını sanmak. Private constructor aynı sınıfın kendi kodundan çağrılabilir.
 *
 * HATA 3: Singleton kullanıldığında sınıftaki bütün alanların static olması gerektiğini düşünmek. Tema alanımız normal bir örnek alanıdır.
 *
 * HATA 4: static final bir nesnenin içindeki bütün alanların değişmez olduğunu sanmak. final referansın yeniden atanmasını engeller, nesnenin iç durumunu değil.
 *
 * HATA 5: İki referansın aynı bilgileri taşıyan farklı nesneleri göstermesiyle aynı nesneyi göstermesini karıştırmak.
 *
 * HATA 6: Her yerde Singleton kullanmanın iyi tasarım olduğunu düşünmek. Gereksiz ortak durum, sınıfların birbirine bağımlılığını artırabilir.
 *
 *
 * 14. HOCANIN BU ORNEKLE KAZANDIRMAK ISTEDIKLERI
 *
 * Private constructor kullanarak nesne oluşturma yollarını kontrol etmeyi öğrendik. Static bir alanda ortak nesne referansı saklanabileceğini gördük.
 *
 * Static bir metot olan getInstance() ile nesne oluşturmadan ortak nesne referansına erişebildiğimizi öğrendik. Metodun mevcut referansı döndürmesi ile new kullanarak yeni nesne oluşturmasının farklı işlemler olduğunu anladık.
 *
 * a == b karşılaştırmasının iki referansın aynı nesneyi gösterip göstermediğini kontrol ettiğini gördük. Ayrıca static alan ile örnek alanın aynı sınıfta birlikte bulunabileceğini ve Singleton'ın avantajları kadar riskleri de olduğunu öğrendik.
 *
 *
 * 15. GENEL SONUC
 *
 * Bu örneğin asıl amacı yalnızca getInstance() metodu yazmayı öğrenmek değildir. Asıl amaç, bir sınıfın nesne oluşturma sorumluluğunu kontrol edebilmesini ve gerektiğinde aynı ortak nesneyi farklı kullanıcılara sunabilmesini anlamaktır.
 *
 * private constructor, dışarıdan doğrudan nesne oluşturulmasını engeller. static final ORNEK alanı ortak nesne referansını saklar. getInstance() metodu ise bu referansı dışarıya döndürür.
 *
 * EN ÖNEMLİ KURAL: Yeni nesne oluşturmak ile mevcut nesnenin referansını paylaşmak aynı şey değildir. Hangi yöntemin doğru olduğuna programın ihtiyaçları karar verir.
 *
 * ============================================================
 */


