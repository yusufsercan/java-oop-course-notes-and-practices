
public class BilesimDemo {

    public static void main(String[] args) {

        Motor m1 = new Motor(130);

        Araba a = new Araba("61 CD 002", m1);

        a.kontagiAc();
        a.surus(120);
        a.kontagiKapat();

        System.out.println(a.bilgi());

        System.out.println("=== ELEKTRIKLI ARABA ===");

        Motor m2 = new Motor(210);

        ElektrikliAraba e =
                new ElektrikliAraba("61 EF 003", m2, 30);

        e.kontagiAc();
        e.surus(100);
        e.surus(100);

        System.out.println(e.bilgi());

        System.out.println(e instanceof Araba);
    }
}

/*
 * ============================================================
 * HAFTA 07 - ORNEK 4
 * IS-A, HAS-A, KALITIM, BILESIM VE DELEGASYON
 * ============================================================
 *
 * 1. BU ORNEGI NEDEN YAZDIK?
 *
 * Miras konusunu öğrendikten sonra her sınıfın başka bir sınıftan extends ile türetilmesi gerektiğini düşünebiliriz. Ancak iki sınıf arasında benzer davranışların bulunması, aralarında mutlaka miras ilişkisi olması gerektiği anlamına gelmez.
 *
 * Örneğin araba ve motor sınıflarını düşünelim. Araba, motorun çalıştırma işlevine ihtiyaç duyar. Ancak bu ihtiyacı karşılamak için Araba extends Motor yazarsak yanlış bir tür ilişkisi kurmuş oluruz.
 *
 * Çünkü bu ifade "Araba bir motordur" anlamına gelir. Gerçekte araba bir motor değildir; arabanın bir motoru vardır.
 *
 * Bu ayrım, IS-A ve HAS-A ilişkilerini öğrenmemizin temel nedenidir.
 *
 *
 * 2. IS-A ILISKISI NEDIR?
 *
 * IS-A, "X bir Y'dir" cümlesini ifade eder. Bu ilişki, gerçekten bir tür akrabalığı varsa extends ile kurulur.
 *
 * Örneğin ElektrikliAraba bir Araba türüdür. ElektrikliAraba, Araba sınıfının temel davranışlarını kullanabilmeli ve o davranışların geçerli kurallarını koruyabilmelidir.
 *
 * Bu nedenle ElektrikliAraba extends Araba kullanmak anlamlıdır.
 *
 * Ancak yalnızca cümlenin günlük dilde doğru olması her zaman yeterli değildir. Alt sınıf üst sınıfın kurallarını bozmadan kullanabilmelidir.
 *
 *
 * 3. HAS-A ILISKISI NEDIR?
 *
 * HAS-A, "X'in bir Y'si vardır" cümlesini ifade eder.
 *
 * Arabanın bir motoru vardır. Bu nedenle Araba sınıfında Motor türünde bir alan tanımladık.
 *
 * private final Motor motor;
 *
 * Bu satırda Araba, Motor sınıfından türemiyor. Bunun yerine bir Motor nesnesinin referansını kendi alanında saklıyor.
 *
 * Böylece araba, motorun sunduğu gerekli işlemleri kullanabilir ancak motorun bütün üyelerini kendi üyeleriymiş gibi miras almak zorunda kalmaz.
 *
 *
 * 4. YANLIS KALITIMIN SONUCLARI
 *
 * Eğer class KotuAraba extends Motor yazsaydık Java bu kodu kabul edebilirdi. Ancak programın tasarımı yanlış olurdu.
 *
 * KotuAraba artık Motor türünden sayılırdı. Motor beklenen bir metoda araba nesnesi göndermek mümkün olurdu. Ayrıca motorun yakitPompasiTest() gibi servis içi işlemleri arabanın da doğrudan erişilebilir üyeleri hâline gelebilirdi.
 *
 * Bu durum, kullanılmaması gereken davranışların yanlış sınıflara taşınmasına neden olur.
 *
 * Üstelik arabanın iki ayrı motorla çalışmasını modellemek zorlaşır. Çünkü motor, arabanın sahip olduğu ayrı bir nesne olarak değil, arabanın kendi türü olarak tanımlanmıştır.
 *
 *
 * 5. BILESIM ILE NASIL DUZELTTIK?
 *
 * Araba sınıfına private final Motor motor; alanını ekledik.
 *
 * Constructor'da Motor nesnesini parametre olarak alıyoruz ve this.motor = motor; ifadesiyle saklıyoruz.
 *
 * Böylece araba, bir Motor nesnesiyle birlikte çalışıyor. Ancak Araba sınıfı Motor sınıfından türemiş olmuyor.
 *
 * final kullanılması motor referansının constructor'dan sonra yeniden atanmasını engeller. Fakat motorun kendi çalışma durumunun değişmesini engellemez. Örneğin motor.calistir() yine çağrılabilir.
 *
 *
 * 6. DELEGASYON NEDIR?
 *
 * Delegasyon, bir sınıfın belirli bir işi başka bir nesneye yaptırmasıdır.
 *
 * Araba sınıfında:
 *
 * public void kontagiAc() {
 *     System.out.println(plaka + ": Kontak acildi");
 *     motor.calistir();
 * }
 *
 * yazdık.
 *
 * Burada Araba kendi açılış mesajını yazdırıyor ancak motorun çalıştırılmasını Motor nesnesine bırakıyor.
 *
 * Araba, motorun çalışma mantığını yeniden uygulamıyor. İşi ilgili nesneye devrediyor.
 *
 * Aynı yaklaşım bilgi() metodunda da bulunuyor. Araba, motorun gücünü motor.gucHp() çağrısıyla öğreniyor.
 *
 *
 * 7. NEDEN MOTOR ALANINI PRIVATE TUTTUK?
 *
 * Araba sınıfının dışarıya motorun bütün işlemlerini açmasını istemiyoruz.
 *
 * Örneğin yakitPompasiTest() metodu bir servis işlemi olabilir. Bu metodu motorun kendisi sunuyor fakat Araba sınıfında bu işlem için bir metot tanımlamadık.
 *
 * Böylece Araba sınıfı hangi motor işlevlerini kendi kullanıcılarına sunacağına karar verebiliyor.
 *
 * Bileşimin önemli avantajlarından biri budur: Kullanılan nesnenin bütün ayrıntılarını dışarıya açmadan yalnızca gereken işlemler sunulabilir.
 *
 *
 * 8. ELEKTRIKLIARABA NEDEN EXTENDS KULLANIYOR?
 *
 * ElektrikliAraba gerçek bir Araba türüdür. Plaka, motor, sürüş ve kilometre gibi ortak özellikleri vardır. Ayrıca şarj yüzdesi gibi kendine özgü bir duruma sahiptir.
 *
 * Bu yüzden ElektrikliAraba extends Araba kullandık.
 *
 * Constructor'da super(plaka, motor) çağrısıyla üst sınıfın bilgilerini hazırlıyoruz. Ardından şarj yüzdesini atıyoruz.
 *
 * Math.max(0, sarjYuzdesi), şarjın negatif olmamasını; Math.min(100, ...), 100 değerini geçmemesini sağlıyor.
 *
 * Böylece örnek açısından şarj yüzdesini 0 ile 100 arasında tutuyoruz.
 *
 *
 * 9. SURUS() METODUNU NEDEN EZDIK?
 *
 * Normal Araba sınıfı sürüş sırasında motorun çalışıp çalışmadığını ve kilometrenin pozitif olup olmadığını kontrol ediyor.
 *
 * ElektrikliAraba sınıfında ise ek bir şart var: Şarjın sürüş için yeterli olması gerekiyor.
 *
 * Bu nedenle surus(int km) metodunu @Override ile yeniden tanımladık.
 *
 * Önce istenen mesafeye göre yaklaşık şarj gereksinimini hesaplıyoruz. Math.ceil(km / 5.0) ifadesi sayıyı yukarı yuvarlar. Bizim örneğimizde 100 km için 20 birim şarj gerekiyor.
 *
 * Şarj yeterliyse super.surus(km) çağrılır. Böylece üst sınıfın motor ve kilometre kontrolleri korunur.
 *
 * Üst sınıf işlemi başarılı olursa kullanılan şarj miktarını düşürüyoruz.
 *
 * Bu yaklaşım sayesinde üst sınıfın temel davranışını tekrar yazmadan yeni bir kural ekliyoruz.
 *
 *
 * 10. PROGRAMIN CALISMA SIRASI
 *
 * Önce 130 HP gücünde bir Motor nesnesi oluşturuyoruz. Bu motoru Araba constructor'ına gönderiyoruz. Araba motoru kendi alanında saklıyor.
 *
 * a.kontagiAc() çağrısı motor.calistir() işlemini tetikliyor. Ardından 120 km sürüş yapılıyor ve kilometre sayacı artırılıyor. Kontak kapatıldığında motor durduruluyor.
 *
 * Daha sonra yeni bir Motor nesnesiyle ElektrikliAraba oluşturuyoruz. Başlangıç şarjı %30.
 *
 * İlk 100 km sürüşte 20 birim şarj gerekiyor. Şarj yeterli olduğu için super.surus(100) çalışıyor, kilometre sayacı artıyor ve kalan şarj %10 oluyor.
 *
 * İkinci 100 km denemesinde şarj yetersiz olduğu için sürüş reddediliyor. Böylece kilometre sayacı da yeniden artırılmıyor.
 *
 * Son satırdaki e instanceof Araba true döner. Çünkü ElektrikliAraba, Araba sınıfından türetilmiştir.
 *
 *
 * 11. KALITIM VE BILESIM KARSILASTIRMASI
 *
 * Kalıtımda sınıflar arasında tür akrabalığı kurulur. Alt sınıf, üst sınıfın davranışlarına ve kurallarına bağlı hâle gelir.
 *
 * Bileşimde ise bir sınıf başka bir sınıfın nesnesini alan olarak saklar. Böylece sınıflar birbirinden daha bağımsız kalabilir ve bir nesnenin sunduğu işlevlerden yalnızca gerekenler kullanılabilir.
 *
 * Kalıtım, çok biçimlilik açısından güçlüdür ancak üst sınıftaki değişiklikler alt sınıfları etkileyebilir.
 *
 * Bileşim, ortak davranışları nesneler üzerinden birleştirmeyi kolaylaştırır. Birden fazla motor gibi durumları modellemek de daha doğal olabilir.
 *
 *
 * 12. SIK YAPILAN HATALAR
 *
 * HATA 1: Sadece metotları hazır kullanmak için extends yazmak.
 *
 * HATA 2: IS-A ile HAS-A ilişkilerini karıştırmak. Araba bir motor değildir, motoru vardır.
 *
 * HATA 3: Alt sınıfın üst sınıfın kurallarını gerçekten sürdürebilip sürdüremediğini kontrol etmemek.
 *
 * HATA 4: Bileşim kullanıldığında başka nesnenin bütün metotlarını da dışarıya açmak gerektiğini düşünmek.
 *
 * HATA 5: Metot ezilirken üst sınıfın gerekli kontrollerini devre dışı bırakmak.
 *
 *
 * 13. BU ORNEKTEN KAZANIMLARIMIZ
 *
 * IS-A ilişkisi için kalıtım, HAS-A ilişkisi için bileşim kullanmamız gerektiğini öğrendik.
 *
 * extends anahtar sözcüğünün yalnızca kod paylaşımı değil, gerçek bir tür ilişkisi kurduğunu gördük.
 *
 * Bileşimde bir sınıfın başka bir nesnenin işlevlerini delegasyon yoluyla kullanabildiğini öğrendik.
 *
 * Ayrıca alt sınıfın üst sınıfın mevcut davranışını super.metot() ile koruyarak üzerine yeni kısıtlar ekleyebileceğini tekrar gördük.
 *
 * EN ÖNEMLİ KURAL: Şüphe varsa önce bileşimi düşün. Kalıtımı ancak gerçek bir tür ilişkisi bulunuyorsa ve üst sınıfın kuralları korunabiliyorsa kullan.
 *
 * ============================================================
 */
