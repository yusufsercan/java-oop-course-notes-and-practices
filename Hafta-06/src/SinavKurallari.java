
public final class SinavKurallari {

    // Sinifin ortak sabitleri
    public static final double VIZE_ORANI = 0.40;
    public static final double FINAL_ORANI = 0.60;

    // Disaridan degistirilmesini istemedigimiz dizi
    private static final int[] ESIKLER = {50, 60, 70, 80, 90};

    // Sinif baslatilirken kurallari dogrula
    static {

        double toplam = VIZE_ORANI + FINAL_ORANI;

        if (Math.abs(toplam - 1.0) > 0.000001) {
            throw new ExceptionInInitializerError(
                "Agirliklarin toplami 1 olmali."
            );
        }

        System.out.println("SINAV KURALLARI DOGRULANDI");
    }

    // Yardimci siniftan disarida nesne olusturulmasin
    private SinavKurallari() {
    }

    // Basari notunu hesapla
    public static double puan(int vize, int fin) {

        return vize * VIZE_ORANI + fin * FINAL_ORANI;
    }

    // Asil diziyi degil, kopyasini dondur
    public static int[] esikler() {

        return ESIKLER.clone();
    }
}

/*
 * ============================================================
 * HAFTA 06 - ORNEK 3
 * STATIC FINAL, UTILITY CLASS, STATIC DOGRULAMA
 * VE SAVUNMALI KOPYA
 * ============================================================
 *
 * 1. BU ORNEGI NEDEN YAZDIK?
 *
 * Bu örnekte bir üniversitenin sınav kurallarını yöneten sınıf tasarladık. Öğrencilerin başarı notu hesaplanırken vizenin %40, finalin %60 etkili olmasını istiyoruz. Ayrıca geçme eşiklerini bir dizide saklıyor ve bu eşiklerin başka sınıflar tarafından değiştirilmesini istemiyoruz.
 *
 * Önceki bilgilerimizle başarı notunu doğrudan vize * 0.40 + fin * 0.60 şeklinde hesaplayabilirdik. Ancak bu yöntem, aynı sayıların programın farklı yerlerinde tekrar edilmesine neden olabilir. Oranlar değiştiğinde bütün kullanım yerlerini tek tek düzeltmek gerekir. Bir noktayı unutmak programın yanlış sonuç üretmesine yol açabilir.
 *
 * Bu nedenle sınav ağırlıklarını anlamlı isimlere sahip static final sabitler olarak tanımladık. Ardından bu sabitlerin birbiriyle tutarlı olup olmadığını static blokta kontrol ettik.
 *
 * Ek olarak, bu sınıfın nesneye özel bir durumu olmadığı için hesaplama işlemlerini static metotlarla sunduk. Geçme eşiklerinin tutulduğu diziyi de private yapıp dışarıya clone() ile kopyasını verdik.
 *
 *
 * 2. MAGIC NUMBER (SIHIRLI SAYI) PROBLEMI
 *
 * Kodun içinde doğrudan kullanılan 0.40 ve 0.60 gibi değerler, anlamları açıkça belirtilmediğinde sihirli sayı olarak adlandırılır. Örneğin bir geliştirici 0.40 değerinin vize ağırlığı mı, indirim oranı mı, başka bir katsayı mı olduğunu hemen anlayamayabilir.
 *
 * Ayrıca aynı kural birden fazla yerde tekrar edilmişse değişiklik yapmak zorlaşır. Bu yüzden:
 *
 * public static final double VIZE_ORANI = 0.40;
 * public static final double FINAL_ORANI = 0.60;
 *
 * biçiminde anlamlı isimler kullanıyoruz.
 *
 * Artık hesaplama kodunu okuyan kişi hangi katsayının ne işe yaradığını anlayabilir. Kural değiştirilecekse ilgili sabitin tanımı güncellenir.
 *
 *
 * 3. PUBLIC STATIC FINAL IFADELERININ ANLAMI
 *
 * public static final double VIZE_ORANI = 0.40;
 *
 * public, sabitin başka sınıflardan erişilebilir olmasını sağlar. static, sabitin belirli bir öğrenci nesnesine değil, SinavKurallari sınıfına ait olduğunu belirtir. final, alanın başlangıç değeri atandıktan sonra yeniden atanmasını engeller. double, ondalıklı bir sayı tutulduğunu gösterir.
 *
 * VIZE_ORANI ismini büyük harfle ve alt çizgiyle yazmamız Java'nın sabit adlandırma geleneğidir. Bu yazım biçimine SCREAMING_SNAKE_CASE denir.
 *
 * static ve final aynı şey değildir. static, bir üyenin sınıfa ait olduğunu; final ise yeniden atanamayacağını belirtir. Bir alan static olup değişebilir veya final olup nesneye özel olabilir. Bu iki anahtar sözcük farklı özellikler kazandırır.
 *
 *
 * 4. STATIC BLOĞU NEDEN KULLANDIK?
 *
 * static {
 *     double toplam = VIZE_ORANI + FINAL_ORANI;
 *     ...
 * }
 *
 * Bir sınav sisteminde vize ve final ağırlıklarının toplamının 1.0 olması bekleniyor. Bu kurala uyulmaması hâlinde hesaplama sonuçları yanlış olabilir.
 *
 * Bu kontrolü her öğrenci için tekrar yapmak yerine sınıfın başlatılması sırasında bir kez yapmak istedik. Çünkü sınav oranları bütün öğrenciler için ortak ve bu örnekte değişmeyen kurallardır.
 *
 * Static blok, sınıf başlatılırken bu kuralları kontrol ediyor. Kurallar geçerliyse program devam ediyor, geçersizse sınıfın başlatılması sırasında hata oluşuyor.
 *
 *
 * 5. STATIC BLOĞUN SATIR SATIR AÇIKLAMASI
 *
 * double toplam = VIZE_ORANI + FINAL_ORANI;
 *
 * Bu satır sınav ağırlıklarını toplar. Bizim örneğimizde yaklaşık olarak 0.40 + 0.60 = 1.0 sonucunu elde ederiz.
 *
 * if (Math.abs(toplam - 1.0) > 0.000001)
 *
 * Burada toplamın 1.0 değerinden ne kadar uzak olduğunu kontrol ediyoruz. Math.abs() metodu verilen sayının mutlak değerini hesaplar. Örneğin Math.abs(-5) sonucu 5'tir. Biz de toplam - 1.0 ifadesinin pozitif veya negatif olmasından bağımsız şekilde farkın büyüklüğünü ölçüyoruz.
 *
 * Neden doğrudan toplam != 1.0 yazmadık? Çünkü double türündeki ondalıklı sayılar bellekte her zaman tam olarak temsil edilemez. Bazı işlemlerde çok küçük yuvarlama farkları oluşabilir. Bu nedenle belirli bir tolerans kullanmak daha uygun olabilir. Bizim örneğimizde tolerans 0.000001 olarak seçilmiştir.
 *
 * throw new ExceptionInInitializerError("Agirliklarin toplami 1 olmali.");
 *
 * Eğer fark belirlediğimiz toleranstan büyükse sınıfın başlatılması sırasında hata oluşturuyoruz. Bu sayede yanlış sınav kurallarıyla hesaplama yapılmasını engelliyoruz.
 *
 * ExceptionInInitializerError bir derleme hatası değil, sınıf başlatılırken karşılaşılabilen çalışma zamanı hatasıdır. Sınıfın başlatılması başarısız olursa aynı sınıfın sonraki kullanımında NoClassDefFoundError da görülebilir.
 *
 * System.out.println("SINAV KURALLARI DOGRULANDI");
 *
 * Bu satır yalnızca doğrulama başarılı olduğunda çalışır ve kuralların uygun olduğunu bildirir.
 *
 *
 * 6. YARDIMCI SINIF (UTILITY CLASS) NEDIR?
 *
 * Bazı sınıflar nesneye özel bilgiler tutmaz. Yalnızca genel hesaplamalar, dönüşümler veya yardımcı işlemler sunar. Örneğin Math.sqrt(144.0) çağırmak için Math nesnesi oluşturmamız gerekmez.
 *
 * Bizim SinavKurallari sınıfımız da bu mantıkla tasarlanmıştır. Her öğrenci için ayrı bir SinavKurallari nesnesi oluşturmanın anlamı yoktur. Çünkü hesaplama kuralları herkes için aynıdır ve metotlar yalnızca parametreleri ve ortak sabitleri kullanır.
 *
 * Hocanın utility sınıflar için verdiği üç temel kural şunlardır: İşlevler ve sabitler static olarak sunulur, constructor private yapılır ve sınıf final tanımlanır.
 *
 * Ancak bu yaklaşım nesneye özel durum tutan sınıfların yerine kullanılmamalıdır. İki nesne oluşturduğumuzda aralarında anlamlı bir fark olacaksa o sınıf büyük ihtimalle normal nesne yönelimli tasarıma ihtiyaç duyuyordur.
 *
 *
 * 7. FINAL CLASS NE ANLAMA GELIR?
 *
 * final class SinavKurallari
 *
 * Bir sınıfın başındaki final anahtar sözcüğü, o sınıftan kalıtım alınmasını engeller. Bu, final bir değişkenin yeniden atanamamasıyla aynı kavram değildir.
 *
 * final değişken, alanın yeniden atanmasını engeller. final sınıf ise başka bir sınıfın extends kullanarak onu genişletmesini engeller.
 *
 * Kalıtım konusu Hafta 7'de daha ayrıntılı ele alınacaktır. Bu örnekte final sınıf kullanmamızın nedeni, SinavKurallari sınıfının genişletilmek için değil, yalnızca ortak yardımcı işlemleri sunmak için tasarlanmış olmasıdır.
 *
 *
 * 8. PRIVATE CONSTRUCTOR NEDEN GEREKLI?
 *
 * private SinavKurallari() {
 * }
 *
 * Constructor'ı private yaparak başka sınıfların new SinavKurallari() yazmasını engelliyoruz. Çünkü bu sınıfın ayrı ayrı nesnelerini oluşturmak istemiyoruz.
 *
 * Eğer constructor tanımlamasaydık, Java sınıfa varsayılan parametresiz constructor ekleyebilirdi. Bu da istenmeyen nesne oluşturmaya izin verebilirdi. Private constructor ile sınıfın bir yardımcı sınıf olarak kullanılmasını açıkça ifade ediyoruz.
 *
 * Private constructor kullanımı her sınıf için zorunlu değildir. Yalnızca nesne oluşturulmasını dışarıya açmak istemediğimiz tasarımlarda kullanılır.
 *
 *
 * 9. PUAN() METODUNU SATIR SATIR INCELEYELIM
 *
 * public static double puan(int vize, int fin)
 *
 * public, metodun dışarıdan çağrılabileceğini belirtir. static, metodu çağırmak için SinavKurallari nesnesi oluşturmak gerekmediğini gösterir. double, hesaplanan sonucun türüdür. int vize ve int fin ise dışarıdan alınan iki sınav notudur.
 *
 * return vize * VIZE_ORANI + fin * FINAL_ORANI;
 *
 * Bu satır vizeyi kendi ağırlığıyla, finali kendi ağırlığıyla çarpar ve sonuçları toplar.
 *
 * Örneğin SinavKurallari.puan(70, 80) çağrısında vize = 70, fin = 80 olur. Vizenin katkısı 70 * 0.40 = 28, finalin katkısı 80 * 0.60 = 48 olarak hesaplanır. Toplam 28 + 48 = 76 olduğu için metot 76.0 döndürür.
 *
 * Metodun static olmasının nedeni, belirli bir öğrenci nesnesinin alanlarını kullanmamasıdır. Hesaplama için gereken bilgiler parametrelerden ve sınıfın ortak sabitlerinden sağlanır.
 *
 *
 * 10. FINAL DIZILERDE NEDEN YETERLI DEGIL?
 *
 * private static final int[] ESIKLER = {50, 60, 70, 80, 90};
 *
 * Burada static dizinin sınıfa ait olduğunu belirtir. final ise ESIKLER alanının başka bir diziye yeniden atanmasını engeller.
 *
 * Örneğin ESIKLER = new int[5]; yazamayız. Çünkü final olan referansı yeniden atamaya çalışmış oluruz.
 *
 * Fakat ESIKLER[0] = 10; yazmak teknik olarak mümkündür. Çünkü bu kez referansı değiştirmiyoruz; referansın gösterdiği dizinin içindeki bir elemanı değiştiriyoruz.
 *
 * İşte buradaki en önemli ayrım şudur: final, bir referansın hangi nesneyi göstereceğini sabitler; gösterilen nesnenin içeriğini otomatik olarak değişmez yapmaz.
 *
 *
 * 11. NEDEN DIZIYI PRIVATE TUTUYORUZ?
 *
 * Diziyi public static final olarak tanımlasaydık dışarıdaki herhangi bir kod SinavKurallari.ESIKLER[0] = 0; yazabilirdi. Bu işlem derlenirdi ve sınıfımızın sınav eşiklerini dışarıdan değiştirebilirdi.
 *
 * Bu, özellikle ortak kuralların tutulduğu sınıflarda tehlikelidir. Çünkü bir yerde yapılan değişiklik programın başka bir bölümündeki hesaplamaları da etkileyebilir.
 *
 * Bu nedenle değiştirilebilir diziyi private tutuyoruz. Böylece başka sınıflar asıl diziye doğrudan erişemiyor.
 *
 *
 * 12. SAVUNMALI KOPYA (DEFENSIVE COPY) NEDIR?
 *
 * public static int[] esikler() {
 *     return ESIKLER.clone();
 * }
 *
 * Bu metot asıl ESIKLER dizisinin referansını döndürmek yerine onun yeni bir kopyasını oluşturur.
 *
 * Eğer doğrudan return ESIKLER; yazsaydık metodu çağıran kişi aynı dizinin referansını elde ederdi. Sonra diziyi değiştirdiğinde sınıfın gerçek kuralları da değişirdi.
 *
 * return ESIKLER.clone(); kullanıldığında ise int dizisinin değerleri yeni bir diziye kopyalanır. Çağıran kod yeni diziyi değiştirebilir ama orijinal ESIKLER dizisi bu değişiklikten etkilenmez.
 *
 * Örneğin önce int[] kopya = SinavKurallari.esikler(); yazıyoruz. Bu anda kopya dizisinin elemanları 50, 60, 70, 80, 90'dır. Ardından kopya[0] = 0; yazıyoruz. Artık yalnızca kopyanın ilk elemanı 0'dır.
 *
 * Daha sonra SinavKurallari.esikler()[0] çağrısı yeniden yapıldığında asıl diziden yeni bir kopya alınır. Asıl dizinin ilk elemanı değişmediği için ekrana 50 yazdırılır.
 *
 *
 * 13. CLONE() HER ZAMAN DERIN KOPYA OLUSTURUR MU?
 *
 * Hayır. Bizim örneğimizde int[] kullandığımız için clone() yeni bir dizi oluşturur ve içindeki sayısal değerleri kopyalar. Bu nedenle iki dizi birbirinden bağımsız olur.
 *
 * Ancak Ogrenci[] gibi nesne dizilerinde clone() yeni bir dış dizi oluştururken elemanlardaki nesne referanslarını kopyalar. Diziler farklı olsa bile içlerindeki nesneler aynı kalabilir.
 *
 * İki boyutlu dizilerde de benzer bir durum vardır. int[][] dizisini clone() ile kopyalarsak yalnızca dış dizi kopyalanır; iç diziler paylaşılmaya devam edebilir. Tamamen bağımsız bir yapı isteniyorsa iç dizilerin de ayrıca kopyalanması gerekir.
 *
 * Bu ayrıntı Hafta 6'nın laboratuvarındaki savunmalı kopyalama soruları için önemlidir.
 *
 *
 * 14. MAIN METODUNUN CALISMA SIRASI
 *
 * İlk olarak SinavKurallari.VIZE_ORANI yazdırılır. Burada 0.4 çıktısı elde edilir. Dikkat edilmesi gereken nokta, bu aşamada static blok mesajının henüz yazdırılmamış olmasıdır.
 *
 * Bunun nedeni VIZE_ORANI'nın bir derleme zamanı sabiti olmasıdır. Java'da uygun ilkel tür veya String sabitlerinin değerleri derleme sırasında kullanım yerine yerleştirilebilir. Böyle bir sabiti okumak sınıfın başlatılmasını tetiklemez.
 *
 * Daha sonra SinavKurallari.puan(70, 80) çağrılır. Bu çağrı sınıfın başlatılmasını gerektirdiği için önce static blok çalışır. Oranların toplamı kontrol edilir ve "SINAV KURALLARI DOGRULANDI" mesajı yazdırılır. Ardından puan() metodu çalışır ve 76.0 sonucunu döndürür.
 *
 * Sonraki satırda esikler() metodu çağrılarak asıl dizinin kopyası alınır. kopya[0] = 0; ile bu kopyanın ilk elemanı değiştirilir.
 *
 * Programın sonunda SinavKurallari.esikler()[0] yazdırılır. Asıl dizinin içeriği korunmuş olduğundan çıktı 50 olur.
 *
 * BEKLENEN ÇIKTI:
 *
 * 0.4
 * SINAV KURALLARI DOGRULANDI
 * 76.0
 * 50
 *
 *
 * 15. HOCANIN BU ORNEKLE KAZANDIRMAK ISTEDIKLERI
 *
 * Sihirli sayılar yerine anlamlı static final sabitler kullanarak kuralları tek noktadan yönetmeyi öğrendik. static ile final anahtar sözcüklerinin farklı görevleri olduğunu gördük.
 *
 * Static blokların sınıf başlatılırken kurulum doğrulaması yapabileceğini ve tutarsız bir kurulumun erken hata vermesinin sessizce yanlış sonuç üretmekten daha iyi olabileceğini öğrendik.
 *
 * Nesneye özel bilgi tutmayan yardımcı sınıflarda neden static metotlar, private constructor ve final sınıf kullanılabileceğini anladık.
 *
 * final referansların içeriği otomatik olarak korumadığını gördük. Özellikle diziler ve koleksiyonlar gibi değiştirilebilir nesnelerde private alan ve savunmalı kopyanın neden önemli olduğunu öğrendik.
 *
 * Ayrıca her static final sabitinin okunmasının sınıf başlatılmasını gerektirmediğini ve derleme zamanı sabitlerinin özel davranışını gördük.
 *
 *
 * 16. SIK YAPILAN HATALAR
 *
 * HATA 1: final bir dizinin bütün elemanlarını da değişmez yaptığını düşünmek. final yalnızca referansın yeniden atanmasını engeller.
 *
 * HATA 2: Değiştirilebilir ortak dizileri public olarak dışarıya açmak. Başka sınıflar bu dizilerin içeriğini değiştirebilir.
 *
 * HATA 3: Her clone() çağrısının iç içe bütün nesneleri de bağımsız kopyaladığını düşünmek. Nesne dizilerinde ve çok boyutlu dizilerde bu genellikle doğru değildir.
 *
 * HATA 4: Utility sınıf için gereksiz nesneler oluşturmak veya bütün sınıfları utility sınıf gibi tasarlamak. Utility sınıf yalnızca nesneye özel durum bulunmadığında uygun olabilir.
 *
 * HATA 5: Static bloğun her static metot çağrısında yeniden çalıştığını düşünmek. Static başlatma sınıf başına bir kez gerçekleşir.
 *
 * HATA 6: Derleme zamanı sabitine erişimin mutlaka static bloğu çalıştıracağını sanmak. Uygun sabitler kullanım yerine gömülebilir.
 *
 * HATA 7: ExceptionInInitializerError ile derleme hatasını karıştırmak. Bu hata sınıf başlatılması sırasında ortaya çıkar.
 *
 *
 * 17. GENEL SONUC
 *
 * Bu örnekle öğrendiğimiz asıl konu, bir sınıfın ortak kurallarını güvenli ve düzenli şekilde yönetmektir.
 *
 * static final ortak sabitleri ifade eder. Static blok kurulumun doğrulanmasını sağlar. Yardımcı sınıf gereksiz nesne oluşturmayı önler. Private constructor dışarıdan nesne oluşturulmasını kontrol eder. Savunmalı kopya ise değiştirilebilir ortak verilerin dışarıdan bozulmasını engeller.
 *
 * EN ÖNEMLİ KURAL: Ortak bir veriyi yalnızca final yazarak güvenli hâle getirdiğimizi düşünmemeliyiz. Verinin türünü, değiştirilebilir olup olmadığını ve dışarıya nasıl sunulduğunu da değerlendirmeliyiz.
 *
 * ============================================================
 */
