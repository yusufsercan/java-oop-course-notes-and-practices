
public class KisiDemo {

    public static void main(String[] args) {

        System.out.println("=== OGRENCI ===");

        Ogrenci o = new Ogrenci(
                "Elif Demir", "12345678902", 2005,
                "YZM376014", "Yazilim Muhendisligi"
        );

        o.gnoAyarla(3.24);

        System.out.println(o.getAd());
        System.out.println(o.yas());
        System.out.println(o.resitMi());
        System.out.println(o.maskeliTcNo());
        System.out.println(o.kimlikKarti());

        System.out.println("\n=== YONETICI ===");

        Yonetici y = new Yonetici(
                "Murat Kaya", "32345678903", 1978,
                "P-1042", "Bilgi Islem", 7
        );

        System.out.println(y.kimlikKarti());

        System.out.println("\n=== IS-A VE UPCASTING ===");

        Kisi ref = o;

        System.out.println(o instanceof Kisi);
        System.out.println(y instanceof Personel);
        System.out.println(y instanceof Kisi);

        System.out.println(ref.kimlikKarti());
        System.out.println(ref.getClass().getSimpleName());

        // System.out.println(ref.getGno()); // DERLEME HATASI

        System.out.println("\n=== OBJECT ===");

        System.out.println(o.toString());
        System.out.println(Ogrenci.uretilenSayisi());
    }
}


/*
 * ============================================================
 * HAFTA 07 - ORNEK 1
 * MIRAS, EXTENDS, SUPER, OVERRIDE VE IS-A
 * ============================================================
 *
 * 1. BU ORNEGI NEDEN YAZDIK?
 *
 * Önceki haftalarda her sınıfı bağımsız tasarlıyorduk. Ogrenci ve Personel gibi sınıfların ad, kimlik numarası ve doğum yılı alanları birbirine benziyordu. Yaş hesaplama ve kimlik maskeleme metotlarını da iki sınıfa ayrı ayrı yazmak zorunda kalıyorduk.
 *
 * Bu durum kod tekrarına yol açıyordu. Aynı iş kuralını iki ayrı sınıfta tutmak, gelecekte bu kurallardan birinin güncellenip diğerinin unutulması riskini oluşturuyordu.
 *
 * Miras kullanarak ortak bilgileri Kisi sınıfında topladık. Ogrenci ve Personel sınıfları ise yalnızca kendilerine özel alanları tanımladı. Böylece ortak metotları yeniden yazmadan kullanabilir hâle geldiler.
 *
 * Ancak mirasın asıl amacı yalnızca kod tekrarını azaltmak değildir. Asıl amaç sınıflar arasında gerçek bir tür ilişkisi kurmaktır. Her öğrenci bir kişidir ve her personel bir kişidir. Bu yüzden Ogrenci extends Kisi ve Personel extends Kisi mantıklıdır.
 *
 *
 * 2. EXTENDS ANAHTAR SOZCUGU
 *
 * public class Ogrenci extends Kisi
 *
 * Bu satır Ogrenci sınıfının Kisi sınıfından türediğini bildirir. Kisi üst sınıf (superclass), Ogrenci ise alt sınıf (subclass) olur.
 *
 * Alt sınıf, üst sınıfın erişilebilir davranışlarını kullanabilir ve üzerine yeni özellikler ekleyebilir. Örneğin Ogrenci içinde yas() metodunu yazmamış olmamıza rağmen o.yas() çağrısı çalışır.
 *
 * Benzer şekilde Yonetici extends Personel yazdığımızda yönetici, Personel üzerinden Kisi sınıfına kadar uzanan bir miras zincirine sahip olur.
 *
 *
 * 3. ALT SINIF NELERI DEVRALIR?
 *
 * Üst sınıftaki public metotlar alt sınıf tarafından kullanılabilir. protected üyeler de uygun erişim koşullarında alt sınıflara açıktır. Paket düzeyindeki üyeler aynı pakette erişilebilir ancak farklı pakette miras yoluyla erişilemez.
 *
 * private alanlar ise alt sınıfın kodundan doğrudan görülemez. Fakat bu, alanların alt sınıf nesnesinde bulunmadığı anlamına gelmez.
 *
 * Örneğin Kisi sınıfındaki tcNo alanı private olmasına rağmen Ogrenci nesnesinin kişi bölümünde bulunur. Ogrenci doğrudan tcNo alanına erişemez ama miras aldığı maskeliTcNo() metodu bu alanı okuyabilir.
 *
 * Constructor'lar miras alınmaz. Alt sınıfın constructor'ı, üst sınıfın constructor'ını super(...) ile çağırır.
 *
 * static üyeler alt sınıf üzerinden görülebilir ancak her alt sınıfa ayrı ayrı kopyalanmaz. Sınıf düzeyindeki ortak üyeler aynı üyelerdir.
 *
 *
 * 4. SUPER(...) NEDEN KULLANILIR?
 *
 * Ogrenci constructor'ındaki:
 *
 * super(ad, tcNo, dogumYili);
 *
 * satırı Kisi sınıfının üç parametre alan constructor'ını çağırır.
 *
 * Bu çağrı gereklidir çünkü bir öğrenci nesnesinin kişiyle ilgili alanları da doğru şekilde hazırlanmalıdır. Önce Kisi constructor'ı gelen adı, kimlik numarasını ve doğum yılını doğrular. Daha sonra bu bilgileri kendi private final alanlarına atar.
 *
 * Üst sınıfın kurulum işlemi bittikten sonra Ogrenci constructor'ının geri kalan satırları çalışır. Öğrenci numarası ve bölüm bilgileri atanır, GNO başlangıç değeri belirlenir ve ortak öğrenci sayacı artırılır.
 *
 * Böylece aynı doğrulama mantığını Ogrenci ve Personel sınıflarına tekrar yazmak zorunda kalmayız.
 *
 * Dersin kullandığı JDK 21 sürümünde super(...) çağrısı constructor'ın ilk deyimi olmalıdır. Eğer üst sınıfta parametresiz constructor yoksa ve uygun super(...) çağrısı yapılmazsa derleme hatası oluşur.
 *
 *
 * 5. UC SEVIYELI MIRAS NASIL CALISIR?
 *
 * Yonetici sınıfı Personel sınıfından, Personel sınıfı ise Kisi sınıfından türetilmiştir.
 *
 * new Yonetici(...) çağrıldığında Yonetici constructor'ı kendi üst sınıfındaki constructor'a super(...) ile gider. Personel constructor'ı da Kisi constructor'ını çağırır.
 *
 * Böylece önce Kisi'nin ortak alanları, sonra Personel'in özel alanları, en son Yonetici'nin özel alanları hazırlanır.
 *
 * Ekranda constructor mesajlarının Kisi, Personel ve Yonetici sırasıyla görünmesinin nedeni budur.
 *
 * ÖNEMLİ: Üç ayrı nesne oluşturulmaz. Bellekte tek bir Yonetici nesnesi vardır ve bu nesne miras zincirindeki sınıfların örnek alanlarını taşır.
 *
 *
 * 6. METOT EZME (OVERRIDING) NEDIR?
 *
 * Kisi sınıfında kimlikKarti() metodu zaten vardır. Bu metot ad, maskelenmiş kimlik numarası ve yaş bilgisini döndürür.
 *
 * Ancak bir öğrencinin kimlik kartında öğrenci numarasını ve bölümünü de göstermek istiyoruz. Bunun için Ogrenci sınıfında aynı metodu yeniden tanımladık.
 *
 * @Override
 * public String kimlikKarti() {
 *     return super.kimlikKarti()
 *             + " | OGRENCI " + ogrenciNo
 *             + " (" + bolum + ")";
 * }
 *
 * Buradaki @Override, üst sınıftaki mevcut metodu yeniden uyguladığımızı belirtir. Bu, yeni bir metot oluşturmakla aynı şey değildir.
 *
 * Metot adının ve parametre listesinin üst sınıftaki metotla uyumlu olması gerekir. Dönüş türü de ezme kurallarına uygun olmalıdır. Ayrıca erişim düzeyi daraltılamaz.
 *
 *
 * 7. SUPER.METOT() NEDEN KULLANILIR?
 *
 * super.kimlikKarti() üst sınıftaki kimlikKarti() metodunun gövdesini çağırır.
 *
 * Bu satırı kullanmamızın nedeni, üst sınıfın hazırladığı ortak kimlik kartı bilgisini tekrar yazmadan kullanmaktır.
 *
 * Ardından öğrenciye özel bilgileri ekleriz. Böylece üst sınıfın kimlik kartı biçimi değişirse bu değişiklik alt sınıfa da yansıyabilir.
 *
 * Aynı mantık Yonetici sınıfında da kullanılır. Önce Personel sürümü çağrılır. Personel sürümü de Kisi sürümünü kullandığı için sonuçta üç sınıfın bilgileri birleştirilir.
 *
 * Bir metodu ezerken şu soruyu sormalıyız: Üst sınıfın yaptığı işi tamamen mi değiştirmek istiyorum, yoksa üzerine yeni davranış mı ekliyorum?
 *
 * İkinci durumda super.metot() kullanmak çoğu zaman doğru yaklaşımdır.
 *
 *
 * 8. OVERRIDING ILE OVERLOADING FARKI
 *
 * Overloading, aynı isimde fakat farklı parametre listelerine sahip birden fazla metot tanımlamaktır.
 *
 * Overriding ise üst sınıftan gelen uygun bir örnek metodunu aynı imzayla alt sınıfta yeniden uygulamaktır.
 *
 * Örneğin Kisi.kimlikKarti() ile Ogrenci.kimlikKarti() arasında overriding vardır.
 *
 * Eğer üst sınıftaki metot kimlikKarti() iken alt sınıfta kimlikKarti(String baslik) yazsaydık bu, metot ezme değil, farklı parametre listesine sahip yeni bir metot olurdu.
 *
 * @Override etiketi, böyle bir imza hatasını derleme sırasında yakalayabilmemizi sağlar.
 *
 *
 * 9. INSTANCEOF NEYI KONTROL EDER?
 *
 * o instanceof Kisi ifadesi, o nesnesinin Kisi türüyle uyumlu olup olmadığını kontrol eder.
 *
 * Ogrenci, Kisi sınıfından türediği için sonuç true olur. Yonetici hem Personel hem Kisi olduğu için y instanceof Personel ve y instanceof Kisi ifadeleri de true olur.
 *
 * Ancak ters yön otomatik olarak doğru değildir. Her öğrenci bir kişidir fakat her kişi öğrenci değildir.
 *
 * Bu nedenle miras ilişkisini tek yönlü düşünmeliyiz.
 *
 *
 * 10. UPCASTING (YUKARI DONUSUM)
 *
 * Kisi ref = o;
 *
 * Bu satırda gerçek türü Ogrenci olan bir nesneyi Kisi türündeki referansla tutuyoruz. Yeni bir nesne oluşturulmuyor; mevcut nesnenin referansı üst türdeki değişkene atanıyor.
 *
 * Java bu dönüşüme izin verir çünkü her Ogrenci aynı zamanda bir Kisidir.
 *
 * Ancak ref değişkeninin derleme zamanındaki türü Kisi olduğu için doğrudan getGno() çağıramayız. Kisi sınıfında böyle bir metot yoktur.
 *
 * ref.kimlikKarti() ise geçerlidir. Çünkü kimlikKarti() Kisi sınıfında tanımlıdır. Gerçek nesne Ogrenci olduğu için onun ezilmiş metodu çalışır.
 *
 * Referansın türüyle gerçek nesnenin türü arasındaki bu ayrım, Hafta 9'daki çok biçimlilik konusunun temelidir.
 *
 *
 * 11. OBJECT SINIFIYLA BAGLANTI
 *
 * Java'da her sınıf doğrudan veya dolaylı olarak Object sınıfından türetilir. Bu nedenle Ogrenci nesneleri de Object sınıfındaki uygun metotlara sahiptir.
 *
 * toString() bunlardan biridir. Biz Ogrenci sınıfında @Override ile toString() metodunu yeniden tanımladık. Böylece System.out.println(o) yazdığımızda anlaşılır bir öğrenci bilgisi gösterilebilir.
 *
 * Bunun yerine yeni bir bilgi() metodu yazsaydık @Override kullanmamız gerekmezdi. Çünkü yeni bir metot tanımlamış olurduk.
 *
 *
 * 12. SIK YAPILAN HATALAR
 *
 * HATA 1: Mirası yalnızca kod tekrarını azaltmak için kullanmak. Gerçek IS-A ilişkisi bulunmuyorsa bileşim daha uygun olabilir.
 *
 * HATA 2: private alanların alt sınıf nesnesinde hiç bulunmadığını düşünmek. Alanlar nesnede vardır fakat alt sınıf kodu bunlara doğrudan erişemez.
 *
 * HATA 3: Constructor'ların miras alındığını düşünmek. Constructor'lar miras alınmaz, super(...) ile çağrılır.
 *
 * HATA 4: super(...) ile super.metot() ifadelerini karıştırmak. Birincisi üst sınıfın constructor'ını, ikincisi üst sınıfın metodunu çağırır.
 *
 * HATA 5: Üst sınıftaki metodu genişletmek istediğimiz hâlde super.metot() çağrısını unutmak. Bu durumda üst sınıfın önemli kuralları kullanılmayabilir.
 *
 * HATA 6: Bir üst tür referansının alt türe özgü bütün metotları doğrudan çağırabileceğini düşünmek. Erişilebilir metotlar referansın derleme zamanındaki türüyle sınırlıdır.
 *
 *
 * 13. BU ORNEKTEN KAZANIMLARIMIZ
 *
 * Bir sınıfı extends ile başka bir sınıftan türetmeyi öğrendik. Ortak alanları ve davranışları üst sınıfa taşırken alt sınıflarda yalnızca özel bilgileri tutmayı gördük.
 *
 * Constructor'ların nasıl zincir oluşturduğunu, super(...) ve super.metot() ifadelerinin farklı görevlerini ve @Override ile metot ezmenin neden önemli olduğunu öğrendik.
 *
 * Ayrıca instanceof ile tür ilişkisini sınamayı ve upcasting ile alt tür nesnesini üst tür referansında tutmayı gördük.
 *
 * EN ÖNEMLİ SONUÇ: Miras, iki sınıf arasında gerçek bir "X bir Y'dir" ilişkisi kurmak için kullanılmalıdır. Sadece aynı kodu paylaşmak istemek, tek başına yeterli bir gerekçe değildir.
 *
 * ============================================================
 */
