public class HesapDemo {

    public static void main(String[] args) {

        Hesap normal = new Hesap("Ali", 1000);
        VadeliHesap vadeli =
                new VadeliHesap("Ayse", 20000, 30);

        System.out.println("=== KESINTI ===");

        System.out.println(normal.aylikKesinti());
        System.out.println(vadeli.aylikKesinti());

        System.out.println("=== VADE DOLMADAN ===");

        System.out.println(vadeli.cek(500));

        System.out.println("=== VADE DOLDUKTAN SONRA ===");

        vadeli.gunIlerlet(31);

        System.out.println(vadeli.cek(500));
        System.out.println(vadeli.cek(999999));

        System.out.printf("Bakiye: %.2f%n", vadeli.getBakiye());

        System.out.println("=== FAIZ ===");

        System.out.printf("Faiz: %.2f%n", vadeli.faizIsle());
        System.out.printf("Yeni bakiye: %.2f%n", vadeli.getBakiye());

        System.out.println("=== FINAL METOT ===");

        System.out.println(normal.hesapNo());
        System.out.println(vadeli.hesapNo());
    }
}

/*
 * ============================================================
 * HAFTA 07 - ORNEK 3
 * PROTECTED, FINAL, OVERRIDE VE SUPER.METOT()
 * ============================================================
 *
 * 1. BU ORNEGI NEDEN YAZDIK?
 *
 * Normal bir banka hesabında para yatırma ve çekme gibi ortak işlemler vardır. Vadeli hesap da bir hesap türüdür ancak normal hesaptan farklı olarak vadesi dolmadan para çekilmesine izin vermemelidir.
 *
 * Eğer VadeliHesap sınıfına Hesap sınıfındaki bütün para çekme kodunu tekrar yazsaydık aynı doğrulama mantığını iki farklı yerde tutmuş olurduk. İleride normal hesapta bir güvenlik kontrolü değiştirildiğinde vadeli hesapta bu kontrolün unutulması riski doğardı.
 *
 * Bu nedenle VadeliHesap sınıfını Hesap sınıfından türettik. Normal hesabın ortak işlemlerini miras aldık ve yalnızca vadeli hesaba özel kuralları ekledik.
 *
 *
 * 2. HESAP SINIFINDAKI ERISIM DUZEYLERI
 *
 * private final String hesapNo;
 *
 * Hesap numarasının yalnızca Hesap sınıfı tarafından yönetilmesini istiyoruz. Alt sınıfın bu alanı doğrudan değiştirmesi gerekmiyor. Bu yüzden private kullandık.
 *
 * protected final String sahip;
 *
 * Hesap sahibinin bilgisi alt sınıflar tarafından kullanılabilir. final kullanıldığı için başlangıçta atandıktan sonra yeniden atanamaz.
 *
 * protected double bakiye;
 *
 * Bu alanı protected yaptık çünkü VadeliHesap sınıfının faiz hesaplama metodunda bakiyeye doğrudan faiz ekliyoruz.
 *
 * ÖNEMLİ: protected, private ve public arasında yeni bir erişim seçeneğidir. Aynı paketteki sınıflar protected üyeye erişebilir. Farklı paketlerde ise alt sınıflara miras yoluyla erişim sağlar; bu erişimin ayrıca Java'nın niteleyici kurallarına uyması gerekir.
 *
 * Her alanı protected yapmak doğru değildir. Alt sınıfların doğrudan erişimine gerçekten ihtiyaç yoksa private tercih edilmelidir.
 *
 *
 * 3. NEDEN SUPER(...) KULLANDIK?
 *
 * VadeliHesap constructor'ındaki:
 *
 * super(sahip, acilisBakiyesi);
 *
 * çağrısı Hesap constructor'ını çalıştırır.
 *
 * Böylece hesap sahibinin adı ve açılış bakiyesi üst sınıf tarafından doğrulanır. Hesap numarası da orada üretilir.
 *
 * Üst sınıfın kurulumu bittikten sonra VadeliHesap kendi vadeGunu ve gecenGun alanlarını hazırlar.
 *
 * Bu sayede ortak kurulum mantığını tekrar yazmak zorunda kalmayız.
 *
 *
 * 4. CEK() METODUNU NEDEN EZDIK?
 *
 * Normal bir hesapta para çekebilmek için miktarın geçerli ve bakiyenin yeterli olması gerekir.
 *
 * Vadeli hesapta ise ek bir şart vardır: vade dolmuş olmalıdır.
 *
 * Bu nedenle:
 *
 * @Override
 * public boolean cek(double miktar)
 *
 * metodunu alt sınıfta yeniden uyguladık.
 *
 * Önce vadeDoldumu() çağrılır. Eğer sonuç false ise işlem reddedilir. Vade dolmuşsa super.cek(miktar) çağrılır.
 *
 * super.cek(miktar), üst sınıfın kendi para çekme metodunu çalıştırır. Böylece miktarın pozitif olması, sonlu bir sayı olması ve bakiyenin yeterli olması gibi temel kontroller korunur.
 *
 *
 * 5. SUPER.CEK() NEDEN ONEMLI?
 *
 * Eğer vade dolduktan sonra doğrudan bakiye -= miktar; yazsaydık üst sınıftaki kontrollerin bir kısmı devre dışı kalabilirdi.
 *
 * Örneğin bakiye 19500 iken 999999 çekmeye çalışıldığında işlem reddedilmelidir. Biz super.cek() çağırdığımız için üst sınıfın bakiye kontrolü çalışır ve işlem false döner.
 *
 * Bir metodu ezerken üst sınıfın davranışını tamamen mi değiştirdiğimizi, yoksa üzerine yeni bir kural mı eklediğimizi düşünmeliyiz.
 *
 * Yalnızca genişletme yapıyorsak super.metot() ile temel kuralları korumak çok önemli olabilir.
 *
 *
 * 6. PROTECTED BAKIYE NEDEN VAR?
 *
 * VadeliHesap sınıfındaki faizIsle() metodu:
 *
 * double faiz = bakiye * 0.05 * gecenGun / 365.0;
 * bakiye += faiz;
 *
 * satırlarını çalıştırır.
 *
 * İlk satır mevcut bakiyeye, faiz oranına ve geçen gün sayısına göre faiz tutarını hesaplar. İkinci satır bu faizi bakiyeye ekler.
 *
 * Bu örnekte bakiye protected olduğu için alt sınıf doğrudan erişebilmektedir.
 *
 * Ancak bunun bir tasarım maliyeti vardır. Alt sınıf bakiyeyi doğrudan değiştirebildiğinde üst sınıfın bütün kontrollerinden geçmesi zorunlu olmaz.
 *
 * Daha kapsüllenmiş bir tasarımda bakiye private tutulup alt sınıflara kontrollü bir protected bakiyeArtir(double) metodu sunulabilir.
 *
 * Bu yüzden önce private kullanmayı, gerçekten gerekiyorsa protected erişim açmayı düşünmeliyiz.
 *
 *
 * 7. KESINTIORANI() NEDEN PROTECTED VE EZILEBILIR?
 *
 * Hesap sınıfında:
 *
 * protected double kesintiOrani() {
 *     return 0.0;
 * }
 *
 * metodu bulunuyor.
 *
 * Normal hesap için kesinti oranı sıfırdır. Ancak VadeliHesap farklı bir oran kullanmalıdır.
 *
 * Bu nedenle VadeliHesap sınıfında yalnızca kesintiOrani() metodunu ezdik ve 0.005 döndürdük.
 *
 * Üst sınıftaki aylikKesinti() metodu ise değiştirilmedi:
 *
 * return bakiye * kesintiOrani();
 *
 * Bu metot normal hesap üzerinde çağrıldığında normal kesinti oranını, vadeli hesap üzerinde çağrıldığında ezilmiş kesintiOrani() sürümünü kullanır.
 *
 * Bu yöntem, alt sınıflara bütün hesaplamayı yeniden yazdırmak yerine küçük bir değişiklik noktası sunar.
 *
 * Hocanın PDF'sinde buna uzatma noktası (extension point) deniyor.
 *
 *
 * 8. FINAL METOT NE DEMEKTIR?
 *
 * public final String hesapNo() {
 *     return hesapNo;
 * }
 *
 * Buradaki final, hesapNo() metodunun alt sınıflar tarafından override edilmesini engeller.
 *
 * Böylece farklı hesap türleri hesap numarası metodunun davranışını keyfî biçimde değiştiremez.
 *
 * Hesap sınıfı final değildir, çünkü bu sınıftan VadeliHesap gibi alt sınıflar oluşturulmasına izin veriyoruz.
 *
 * final sınıf ile final metot arasındaki fark budur:
 *
 * final class: Sınıftan kalıtım alınamaz.
 * final metot: Metot alt sınıfta ezilemez.
 * final alan: Alan yeniden atanamaz.
 *
 *
 * 9. PROGRAMIN CALISMA SIRASI
 *
 * Önce bir normal hesap ve bir vadeli hesap oluşturulur. Hesap numarası üretimi static sayaçla yapıldığı için birinci hesap TR1001, ikinci hesap TR1002 numarasını alır.
 *
 * normal.aylikKesinti() çağrısı normal hesabın sıfır oranını kullanır ve 0.0 döndürür.
 *
 * vadeli.aylikKesinti() çağrısı VadeliHesap içinde ezilmiş kesintiOrani() metodunu kullanır. 20000 * 0.005 = 100.0 sonucu elde edilir.
 *
 * Vade dolmadan vadeli.cek(500) çağrıldığında işlem reddedilir. Ardından gün sayısı 31'e çıkarılır. Artık vade dolduğu için 500 çekilebilir.
 *
 * Ancak 999999 çekme denemesinde vade dolmuş olsa bile bakiye yetersizdir. super.cek() çağrısı üst sınıfın kontrolünü işlettiği için sonuç false olur.
 *
 * Son olarak faizIsle() bakiyeye yaklaşık 82.81 faiz ekler ve yeni bakiye 19582.81 olur.
 *
 *
 * 10. SIK YAPILAN HATALAR
 *
 * HATA 1: protected alanları bütün sınıfların her koşulda kullanabileceğini düşünmek. Farklı paketlerde protected erişimin özel kuralları vardır.
 *
 * HATA 2: Üst sınıfın kurallarını korumak gerekirken super.metot() çağrısını unutmak. Bu hata program derlense bile yanlış iş davranışlarına yol açabilir.
 *
 * HATA 3: final metodu ezmeye çalışmak. Java buna derleme hatası verir.
 *
 * HATA 4: Her alanı protected yapmanın mirası kolaylaştırdığı için iyi olduğunu düşünmek. Bu yaklaşım kapsüllemeyi zayıflatabilir.
 *
 * HATA 5: Üst sınıftaki metotla aynı adı fakat farklı parametre listesi olan bir metodu override sanmak. Bu durumda overriding yerine overloading gerçekleşir.
 *
 *
 * 11. BU ORNEKTEN KAZANIMLARIMIZ
 *
 * Alt sınıfın üst sınıfın mevcut davranışını genişletebileceğini öğrendik. super.metot() ile mevcut kontrolleri koruyarak ek kural koyabildiğimizi gördük.
 *
 * protected erişimin neden var olduğunu ama neden dikkatli kullanılması gerektiğini öğrendik. final sınıf, final metot ve final alan arasındaki farkı anladık.
 *
 * Ayrıca küçük bir protected metodu ezerek üst sınıftaki daha büyük bir hesaplamanın davranışını değiştirebileceğimizi gördük.
 *
 * EN ÖNEMLİ KURAL: Kalıtımda üst sınıfın kurallarını korumak ve alt sınıfları yalnızca gerçekten gerekli yerlerden genişletmek sürdürülebilir tasarım açısından önemlidir.
 *
 * ============================================================
 */
