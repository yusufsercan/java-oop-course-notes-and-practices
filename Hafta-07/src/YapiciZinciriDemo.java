
public class YapiciZinciriDemo {

    public static void main(String[] args) {

        System.out.println("=== ILK NESNE ===");

        C_Alt a = new C_Alt();

        System.out.println(
                "Alanlar: " + a.alanA + ", "
                + a.alanB + ", " + a.alanC
        );

        System.out.println("=== IKINCI NESNE ===");

        C_Alt b = new C_Alt();

        System.out.println(
                "Alanlar: " + b.alanA + ", "
                + b.alanB + ", " + b.alanC
        );
    }
}

/*
 * ============================================================
 * HAFTA 07 - ORNEK 2
 * YAPICI ZINCIRI, SUPER(...) VE BASLATMA SIRASI
 * ============================================================
 *
 * 1. BU ORNEGI NEDEN YAZDIK?
 *
 * Hafta 5'te constructor'ların ve örnek başlatma bloklarının çalışma sırasını öğrendik. Hafta 6'da static blokların sınıf başına bir kez çalıştığını gördük. Bu örnekte bu iki bilgiyi miras ilişkisiyle birleştirdik.
 *
 * Bir sınıf başka bir sınıftan türediğinde yalnızca kendi alanlarını hazırlamaz; üst sınıflarından gelen nesne durumunun da geçerli biçimde kurulması gerekir.
 *
 * Üç seviyeli bir hiyerarşide önce en üst sınıfın nesneye ait bölümü, ardından orta sınıfın, en sonunda alt sınıfın bölümü hazırlanır. Bu düzeni anlamadan super(...) çağrısının neden gerekli olduğunu doğru kavrayamayız.
 *
 *
 * 2. UC SEVIYELI HIYERARSI
 *
 * A_Ust en üst sınıfımızdır. B_Orta, A_Ust sınıfından türemiştir. C_Alt ise B_Orta sınıfından türemiştir.
 *
 * Dolayısıyla C_Alt nesnesi oluşturduğumuzda hem B_Orta hem A_Ust sınıflarından gelen örnek alanlarına sahip olur.
 *
 * Ancak üç ayrı nesne oluşturulmaz. Tek bir C_Alt nesnesi oluşturulur ve bu nesnenin içinde miras zincirindeki örnek alanları bulunur.
 *
 *
 * 3. STATIC BLOKLARIN SIRASI
 *
 * İlk C_Alt nesnesi oluşturulduğunda C_Alt sınıfının başlatılması gerekir. Bunun için önce üst sınıflarının da başlatılması tamamlanır.
 *
 * Bu yüzden A_Ust sınıfının static bloğu ilk çalışır. Ardından B_Orta, son olarak C_Alt sınıfının static bloğu çalışır.
 *
 * Ekrandaki 0a, 0b ve 0c mesajları bu sırayı gösterir.
 *
 * İkinci C_Alt nesnesi oluşturulduğunda aynı sınıfların static blokları tekrar çalışmaz. Çünkü sınıf başlatma işlemleri daha önce tamamlanmıştır.
 *
 *
 * 4. ORNEK ALAN BASLATICILARI
 *
 * int alanA = yaz("1. A_Ust alani", 10);
 *
 * Bu satır A_Ust sınıfının örnek alan başlatıcısıdır. Yeni nesne oluşturulurken yaz() metodu çağrılır, belirtilen mesaj yazdırılır ve metot 10 döndürür. Dönen değer alanA'ya atanır.
 *
 * B_Orta sınıfında alanB için benzer şekilde 20, C_Alt sınıfında alanC için 30 değeri atanır.
 *
 * Bu alanlar static değildir. Dolayısıyla her yeni nesne için tekrar hazırlanırlar.
 *
 *
 * 5. ORNEK BASLATMA BLOKLARI
 *
 * { System.out.println("2. A_Ust ornek blogu"); }
 *
 * Bu yapı örnek başlatma bloğudur. Her yeni nesnede çalışır ve kendi sınıf seviyesindeki constructor gövdesinden önce yürütülür.
 *
 * A_Ust alan başlatıcısı bittikten sonra A_Ust örnek bloğu çalışır; ardından A_Ust constructor gövdesi yürütülür.
 *
 * Aynı sıralama B_Orta ve C_Alt seviyelerinde de tekrarlanır.
 *
 *
 * 6. SUPER(...) CAGRISININ GOREVI
 *
 * B_Orta constructor'ındaki super() ifadesi A_Ust constructor'ını çağırır. C_Alt constructor'ındaki super() ise B_Orta constructor'ını çağırır.
 *
 * Bu çağrılar sayesinde önce üst sınıfın kurulumu tamamlanır. Ardından alt sınıfın kendi başlangıç işlemleri yürütülür.
 *
 * JDK 21'de super(...) çağrısı constructor'ın ilk deyimi olmalıdır. Üst sınıfın kurulumu tamamlanmadan alt sınıfın constructor gövdesinin çalıştırılmasına izin verilmez.
 *
 * Eğer super() açıkça yazılmazsa derleyici uygun koşullarda otomatik olarak super() ekler. Ancak üst sınıfta erişilebilir parametresiz constructor yoksa bu otomatik çağrı başarısız olur ve derleme hatası ortaya çıkar.
 *
 *
 * 7. THIS(...) ILE SUPER(...) ARASINDAKI FARK
 *
 * this(...) aynı sınıfın başka bir constructor'ını çağırır. super(...) ise doğrudan üst sınıfın constructor'ını çağırır.
 *
 * Aynı constructor'da iki çağrı birden ilk deyim olamayacağı için ikisini doğrudan birlikte kullanamayız.
 *
 * Ancak bir constructor this(...) ile aynı sınıftaki başka bir constructor'a gidebilir; o constructor da super(...) ile üst sınıf constructor'ını çağırabilir. Böylece Hafta 5'teki constructor zinciri miras zincirine bağlanır.
 *
 *
 * 8. PROGRAMIN ADIM ADIM CALISMASI
 *
 * İlk new C_Alt() çağrısında önce A_Ust, B_Orta ve C_Alt sınıflarının static başlangıç işlemleri yapılır. Ardından A_Ust alanı, örnek bloğu ve constructor gövdesi çalışır.
 *
 * A_Ust kurulumu bittikten sonra B_Orta alan başlatıcısı, örnek bloğu ve constructor gövdesi çalışır. Son olarak aynı işlemler C_Alt için gerçekleştirilir.
 *
 * Böylece alanA = 10, alanB = 20 ve alanC = 30 olur.
 *
 * İkinci new C_Alt() çağrısında static bloklar çalışmaz. Ancak yeni bir nesne oluşturulduğu için 1'den 9'a kadar bütün örnek başlatma ve constructor işlemleri tekrar yapılır.
 *
 *
 * 9. SIK YAPILAN HATALAR
 *
 * HATA 1: super(...) çağrısının constructor gövdesinden sonra çalışabileceğini düşünmek. JDK 21'de üst sınıf constructor çağrısı ilk deyim olmak zorundadır.
 *
 * HATA 2: Üst sınıftaki constructor'ların miras alındığını düşünmek. Constructor'lar miras alınmaz; super(...) ile çağrılır.
 *
 * HATA 3: Her new işleminde static blokların yeniden çalışacağını düşünmek. Static bloklar sınıfın başlatılması sırasında bir kez çalışır.
 *
 * HATA 4: Üç seviyeli miras varsa üç ayrı nesne oluştuğunu sanmak. Gerçekte tek bir alt sınıf nesnesi oluşturulur.
 *
 * HATA 5: Örnek başlatma bloğunun constructor gövdesinden sonra çalışacağını düşünmek. Kendi sınıf seviyesindeki alan başlatıcıları ve örnek bloklar constructor gövdesinden önce çalışır.
 *
 *
 * 10. BU ORNEKTEN KAZANIMLARIMIZ
 *
 * Miras zincirinde sınıf başlatma sırasını ve nesne oluşturma sırasını öğrendik. Static blokların üst sınıftan alt sınıfa doğru ve yalnızca bir kez çalıştığını gördük.
 *
 * Her yeni nesnede örnek alan başlatıcılarının, örnek blokların ve constructor gövdelerinin her sınıf seviyesinde yeniden yürütüldüğünü anladık.
 *
 * super(...) ile üst sınıf constructor'ının, this(...) ile aynı sınıf constructor'ının çağrıldığını öğrendik.
 *
 * EN ÖNEMLİ KURAL: Üst sınıfın nesne bölümü kurulmadan alt sınıfın constructor gövdesi çalışmaz.
 *
 * ============================================================
 */
