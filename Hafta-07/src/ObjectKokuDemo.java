
public class ObjectKokuDemo {


    public static void main(String[] args) {

        Bos a = new Bos();
        Bos b = new Bos();

        System.out.println(a.getClass().getSimpleName());
        System.out.println(a.getClass().getSuperclass().getName());

        System.out.println(a instanceof Object);

        System.out.println(a.equals(a));
        System.out.println(a.equals(b));

        System.out.println(a.toString());
    }
}

/*
 * ============================================================
 * HAFTA 07 - ORNEK 5
 * OBJECT SINIFI, TOSTRING(), EQUALS() VE GETCLASS()
 * ============================================================
 *
 * 1. BU ORNEGI NEDEN YAZDIK?
 *
 * Java'da bütün sınıfların doğrudan veya dolaylı olarak Object sınıfından türediğini öğrendik. Bunu göstermek için hiçbir alanı ve metodu bulunmayan Bos sınıfını oluşturduk.
 *
 * Bos sınıfında extends yazmamış olmamıza rağmen onun doğrudan üst sınıfı Object'tir. Bu nedenle Bos nesneleri Object sınıfından gelen metotları kullanabilir.
 *
 *
 * 2. GETCLASS() VE GETSUPERCLASS()
 *
 * a.getClass() çağrısı a referansının gösterdiği gerçek nesnenin sınıfını verir.
 *
 * getSimpleName() bu sınıfın kısa adını döndürür. Bizim örneğimizde sonuç Bos olur.
 *
 * getSuperclass() ise sınıfın doğrudan üst sınıfını verir. Bos sınıfının doğrudan üst sınıfı java.lang.Object olduğu için ekranda bu ad görünür.
 *
 * Daha uzun bir miras zincirinde getSuperclass() çağrılarını tekrarlayarak üst sınıfları sırayla inceleyebiliriz.
 *
 *
 * 3. INSTANCEOF OBJECT
 *
 * a instanceof Object ifadesi true döner. Çünkü Bos sınıfı Object'ten türetilmiştir.
 *
 * Bu durum Java sınıf hiyerarşisinin kökünde Object bulunduğunu gösterir.
 *
 *
 * 4. EQUALS() METODUNUN VARSAYILAN DAVRANISI
 *
 * a.equals(a) sonucu true olur. Çünkü aynı nesne kendiyle karşılaştırılır.
 *
 * a.equals(b) sonucu false olur. Çünkü a ve b iki farklı new işlemiyle oluşturulan ayrı nesnelerdir.
 *
 * Nesnelerin içindeki değerlerin aynı olması tek başına varsayılan equals() sonucunu true yapmaz. Object sınıfındaki varsayılan equals() uygulaması nesne kimliğini karşılaştırır.
 *
 * İleride bir sınıfın nesnelerini içeriklerine göre eşit saymak istersek equals() metodunu uygun şekilde ezmemiz gerekir.
 *
 * equals() ezildiğinde hashCode() sözleşmesinin de korunması gerekir. Bu konu daha sonraki haftalarda ayrıntılandırılacaktır.
 *
 *
 * 5. TOSTRING() METODUNUN AMACI
 *
 * Object sınıfından gelen varsayılan toString() uygulaması sınıf adı ve hash koduna dayalı bir metin üretir.
 *
 * Bu gösterim, hata ayıklama sırasında çoğu zaman yeterince açıklayıcı değildir.
 *
 * Bu yüzden nesneye anlamlı bir metinsel gösterim vermek istediğimizde toString() metodunu @Override ile yeniden uygulayabiliriz.
 *
 * Örneğin önceki KisiDemo programındaki Ogrenci.toString() metodu, öğrencinin adı, numarası ve GNO değerini anlaşılır bir biçimde döndürüyordu.
 *
 * System.out.println(nesne) çağrısı da nesnenin toString() gösteriminden yararlanır.
 *
 *
 * 6. SIK YAPILAN HATALAR
 *
 * HATA 1: İki farklı nesnenin alanları aynı olduğu için varsayılan equals() sonucunun mutlaka true olacağını düşünmek.
 *
 * HATA 2: toString() metodunun yalnızca özel olarak bizim oluşturduğumuz bir metot olduğunu sanmak. Bu metot Object sınıfından gelir ve gerektiğinde override edilir.
 *
 * HATA 3: getClass() sonucunun her zaman referansın yazıldığı türü gösterdiğini düşünmek. getClass() gerçek nesnenin sınıfını verir.
 *
 *
 * 7. BU ORNEKTEN KAZANIMLARIMIZ
 *
 * Bütün Java sınıflarının Object köküne bağlı olduğunu öğrendik. Object sınıfındaki toString(), equals() ve getClass() metotlarının temel görevlerini gördük.
 *
 * Nesne kimliği eşitliği ile içerik eşitliğinin farklı kavramlar olduğunu anladık.
 *
 * EN ÖNEMLİ KURAL: Bir nesneyi okunabilir biçimde göstermek için toString(), nesneleri değerlerine göre karşılaştırmak için ise doğru bir equals() ve hashCode() tasarımı gerekir.
 *
 * ============================================================
 */
