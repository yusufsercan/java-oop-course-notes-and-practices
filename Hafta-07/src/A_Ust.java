
public class A_Ust {

    static {
        System.out.println("0a. A_Ust static");
    }

    int alanA = yaz("1. A_Ust alani", 10);

    {
        System.out.println("2. A_Ust ornek blogu");
    }

    public A_Ust() {
        System.out.println("3. A_Ust constructor");
    }

    protected static int yaz(String mesaj, int deger) {
        System.out.println(mesaj);
        return deger;
    }
}

/*
 * ============================================================
 * HAFTA 07 - EK OGRENME NOTU
 * PROTECTED STATIC, ALAN BASLATMA VE KOD YAZIM SIRASI
 * ============================================================
 *
 * 1. BU KODDA NEDEN PROTECTED STATIC KULLANDIK?
 *
 * A_Ust sınıfında aşağıdaki metodu tanımladık:
 *
 * protected static int yaz(String mesaj, int deger) {
 *     System.out.println(mesaj);
 *     return deger;
 * }
 *
 * Burada protected ve static farklı görevler üstlenir. protected, metodun nerelerden erişilebilir olduğunu belirleyen bir erişim belirleyicisidir. static ise metodun bir nesneye değil, sınıfın kendisine ait olduğunu ifade eder.
 *
 * protected kullanmamızın nedeni, bu metodu A_Ust sınıfından türeyen B_Orta ve C_Alt sınıflarında da kullanabilmektir. protected üyeler aynı paketteki sınıflar tarafından erişilebilir. Ayrıca farklı paketlerdeki alt sınıflar da belirli kurallar çerçevesinde bu üyelere erişebilir.
 *
 * Ancak önemli bir ayrıntı vardır: Bizim A_Ust, B_Orta ve C_Alt sınıflarımız aynı pakette bulunduğu için protected kullanmak zorunlu değildir. Metodu herhangi bir erişim belirleyicisi yazmadan tanımlasaydık da aynı paketteki diğer sınıflar ona erişebilirdi.
 *
 * protected kullanmamızın amacı, özellikle miras ilişkilerinde bu erişim düzeyinin nasıl kullanılabileceğini görmektir.
 *
 *
 * 2. YAZ() METODUNU NEDEN STATIC TANIMLADIK?
 *
 * yaz() metodunun görevine baktığımızda yalnızca kendisine gönderilen mesajı ekrana yazdırdığını ve kendisine gönderilen sayıyı geri döndürdüğünü görüyoruz.
 *
 * Bu metot A_Ust nesnesinin alanA değerini okumaz veya değiştirmez. Aynı şekilde B_Orta ve C_Alt nesnelerinin alanlarına da ihtiyaç duymaz.
 *
 * Dolayısıyla belirli bir nesnenin durumuna bağlı değildir. Bu nedenle metodu static olarak tanımlamak uygun bir tercihtir.
 *
 * Peki static kelimesini kaldırsaydık ne olurdu?
 *
 * protected int yaz(String mesaj, int deger)
 *
 * şeklinde yazsaydık metot bir örnek metodu olurdu. Bizim örneğimizde bu kullanım da çalışabilirdi çünkü Java, nesnenin alan başlatıcısından örnek metodu çağırmaya izin verir.
 *
 * Ancak bu metodu static olarak tanımlamak daha anlamlıdır. Çünkü metot nesneye özel hiçbir bilgiyi kullanmıyor.
 *
 * ÖNEMLİ: Bir metodun static olması onun mutlaka daha hızlı veya daha iyi olduğu anlamına gelmez. Kararı verirken metodun belirli bir nesnenin durumuna ihtiyaç duyup duymadığını düşünmeliyiz.
 *
 *
 * 3. ALANA NEDEN DOGRDUDAN 10 ATAMADIK?
 *
 * Normalde bir alanı şu şekilde tanımlayabilirdik:
 *
 * int alanA = 10;
 *
 * Ancak bizim kodumuzda şu ifade bulunuyor:
 *
 * int alanA = yaz("1. A_Ust alani", 10);
 *
 * Bunun nedeni alanın hangi aşamada başlatıldığını terminal üzerinden gözlemlemek istememizdir.
 *
 * Java bu satıra geldiğinde önce yaz() metodunu çağırır. Metoda "1. A_Ust alani" metni ve 10 sayısı gönderilir.
 *
 * yaz() metodu önce System.out.println(mesaj) satırını çalıştırarak mesajı terminale yazdırır. Ardından return deger; satırıyla 10 değerini geri döndürür.
 *
 * Metottan dönen 10 değeri alanA alanına atanır.
 *
 * Yani kodumuz aslında iki işlemi birlikte yapmaktadır:
 *
 * Birincisi, alan başlatıcısının çalıştığını ekrana yazdırmak. İkincisi, alanA değişkenine 10 değerini atamak.
 *
 * Burada yaz() metodu yalnızca başlatma sırasını öğrenmek amacıyla kullandığımız yardımcı bir metottur. Normal bir uygulamada böyle bir gözlem yapmamız gerekmiyorsa doğrudan int alanA = 10; yazmak yeterlidir.
 *
 *
 * 4. METOT SINIFIN ALTINDA OLDUGU HALDE NASIL CAGIRILIYOR?
 *
 * A_Ust sınıfında yaz() metodunu alan tanımından sonra yazdık. Ancak alanA başlatıcısında bu metodu kullanabiliyoruz.
 *
 * Çünkü Java'da metotların kullanılabilmesi için mutlaka kendilerini çağıran satırdan önce yazılmış olmaları gerekmez.
 *
 * Derleyici sınıfın metot tanımlarını bildiği için yaz() metodunu sınıfın altında tanımlamamız bir sorun oluşturmaz.
 *
 * Bu durum C dilindeki fonksiyon bildirimleriyle karıştırılmamalıdır. Java'da bir sınıfın metotlarını constructor'dan sonra yazmak oldukça yaygın bir düzenleme biçimidir.
 *
 *
 * 5. NEDEN SINIFIN BASINDA STATIC BLOK BULUNUYOR?
 *
 * Önceki örneklerimizde genellikle önce alanları, ardından constructor'ı ve son olarak metotları yazıyorduk.
 *
 * Ancak Java'da bir sınıfın bütün üyelerinin mutlaka bu sırada yazılması gerektiğine dair genel bir zorunluluk yoktur.
 *
 * Bu örneği başlatma sırasını öğrenmek için hazırladığımızdan static bloğu en üstte gösterdik.
 *
 * static {
 *     System.out.println("0a. A_Ust static");
 * }
 *
 * Bu blok A_Ust sınıfı ilk kez aktif olarak kullanılıp başlatıldığında çalışır. Her yeni nesnede tekrar çalışmaz.
 *
 * Buna karşılık:
 *
 * int alanA = yaz("1. A_Ust alani", 10);
 *
 * bir örnek alan başlatıcısıdır. Bu nedenle her yeni nesne oluşturulduğunda çalışır.
 *
 * Aradaki en önemli fark, static bloğun sınıfın başlangıcıyla, alanA başlatıcısının ise nesnenin başlangıcıyla ilgili olmasıdır.
 *
 *
 * 6. STATIC BLOGU ALANIN ALTINA TASISAYDIK NE OLURDU?
 *
 * Örneğin sınıfı şu sırada yazsaydık:
 *
 * int alanA = yaz("1. A_Ust alani", 10);
 *
 * static {
 *     System.out.println("0a. A_Ust static");
 * }
 *
 * yine static blok önce çalışırdı.
 *
 * Çünkü alanA nesneye ait bir alandır. Static blok ise sınıf başlatılırken çalışır. Yeni nesnenin örnek alanları hazırlanmadan önce sınıfın başlatılması tamamlanır.
 *
 * Dolayısıyla yalnızca örnek alan başlatıcısının static bloktan önce yazılması, onun önce çalışacağı anlamına gelmez.
 *
 * ANCAK BURADA ONEMLI BIR KURAL VAR:
 *
 * Bir sınıfta birden fazla static alan başlatıcısı veya static blok bulunuyorsa bunlar kendi aralarında kaynak kodda yazıldıkları sıraya göre çalışır.
 *
 * Aynı şekilde örnek alan başlatıcıları ve örnek başlatma blokları da kendi aralarında kaynak kod sırasını takip eder.
 *
 * Yani her şeyi istediğimiz yere taşıdığımızda sonuç mutlaka aynı kalacak diye düşünmemeliyiz.
 *
 *
 * 7. A_UST SINIFININ BASLATMA SIRASI
 *
 * A_Ust sınıfının ilk kez aktif olarak kullanıldığını ve yeni bir nesne oluşturulduğunu düşünelim.
 *
 * Öncelikle sınıfın static başlatma işlemi gerçekleşir. Static blok çalışır ve ekrana "0a. A_Ust static" mesajını yazdırır.
 *
 * Ardından nesnenin örnek alanları hazırlanır. alanA başlatıcısı yaz() metodunu çağırır. Metot "1. A_Ust alani" mesajını yazdırır ve 10 değerini döndürür.
 *
 * Daha sonra örnek başlatma bloğu çalışır ve "2. A_Ust ornek blogu" mesajını yazdırır.
 *
 * Son olarak constructor gövdesi çalışır ve "3. A_Ust constructor" mesajını yazdırır.
 *
 * Bu nedenle ilk kullanımda gözlemlediğimiz temel sıra:
 *
 * 0a. A_Ust static
 * 1. A_Ust alani
 * 2. A_Ust ornek blogu
 * 3. A_Ust constructor
 *
 * şeklindedir.
 *
 * Eğer aynı sınıftan ikinci bir nesne oluşturursak static blok yeniden çalışmaz. Ancak örnek alan başlatıcısı, örnek başlatma bloğu ve constructor yeni nesne için tekrar çalışır.
 *
 *
 * 8. HAFTA 7 MIRAS KONUSUYLA BAGLANTISI
 *
 * Bu örneği tek başına A_Ust sınıfını öğrenmek için yazmadık. Asıl amacımız A_Ust, B_Orta ve C_Alt sınıflarındaki başlatma sırasını incelemektir.
 *
 * B_Orta sınıfı A_Ust sınıfından, C_Alt sınıfı ise B_Orta sınıfından türemiştir.
 *
 * İlk kez new C_Alt() çağırdığımızda sınıfların static başlatmaları en üst sınıftan en alt sınıfa doğru gerçekleştirilir.
 *
 * Nesne kurulumu sırasında da önce A_Ust seviyesindeki örnek alan başlatıcıları, örnek blok ve constructor gövdesi tamamlanır. Ardından B_Orta, son olarak C_Alt seviyesindeki işlemler gerçekleşir.
 *
 * Bunun nedeni super() constructor zinciridir. Alt sınıfın constructor gövdesi çalışmadan önce üst sınıfın ilgili kurulum işlemleri tamamlanır.
 *
 * Bu sayede alt sınıf, üst sınıftan gelen nesne durumunun hazırlanmış olmasına dayanabilir.
 *
 *
 * 9. SIK YAPILAN HATALAR
 *
 * HATA 1: protected ve static anahtar sözcüklerinin aynı görevi yaptığını düşünmek. protected erişim düzeyini, static ise sınıfa ait olmayı ifade eder.
 *
 * HATA 2: Bir metodun static olması için mutlaka static alanları kullanması gerektiğini düşünmek. Nesne durumuna ihtiyaç duymayan yardımcı metotlar da static olabilir.
 *
 * HATA 3: Örnek alan başlatıcısından örnek metot çağrılamayacağını sanmak. Bu mümkündür, ancak nesne henüz tam hazırlanmadığı için ezilebilir metotları bu aşamada çağırmak tehlikeli olabilir.
 *
 * HATA 4: Metot tanımının mutlaka onu çağıran satırdan önce yazılması gerektiğini düşünmek. Java'da bu zorunluluk yoktur.
 *
 * HATA 5: Sınıftaki bütün başlangıç işlemlerinin yalnızca kaynak kodda yukarıdan aşağıya çalıştığını düşünmek. Static başlatma ve nesne başlatma farklı aşamalardır.
 *
 * HATA 6: static bloğun her new işleminde çalışacağını sanmak. Static blok sınıfın başlatılması sırasında bir kez çalışır.
 *
 *
 * 10. BU ORNEKTEN KAZANIMLARIMIZ
 *
 * protected ile static anahtar sözcüklerinin farklı görevlerini öğrendik. Metotların hangi durumlarda static tanımlanabileceğini ve bir metodu sınıfın altında tanımlamanın sorun olmadığını gördük.
 *
 * Bir alanın başlangıç değerinin doğrudan sayı verilerek veya metot çağrısının döndürdüğü değer kullanılarak atanabileceğini öğrendik.
 *
 * Static blokların sınıf başlatılırken, örnek alan başlatıcıları ile örnek blokların ise her yeni nesnede çalıştığını anladık.
 *
 * Ayrıca sınıf içindeki üyelerin yazım düzeniyle Java'nın gerçek çalışma sırasının her zaman aynı şey olmadığını gördük.
 *
 * EN ONEMLI SONUC:
 *
 * Java kodunu incelerken yalnızca satırların nerede yazıldığına bakmamalıyız. Öncelikle o satırın sınıfa mı yoksa nesneye mi ait olduğunu belirlemeliyiz.
 *
 * Ardından sınıf başlatma, örnek alan başlatma, örnek blok ve constructor zincirinin hangi sırada çalıştığını değerlendirmeliyiz.
 *
 * ============================================================
 */
