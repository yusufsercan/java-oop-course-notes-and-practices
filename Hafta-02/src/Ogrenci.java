/*
 * Nesne Yonelimli Programlama - Hafta 02
 * Ornek 2: Yapici metotlarla yeniden yazilmis Ogrenci sinifi
 *
 * Yapici metot (constructor), nesnenin GECERLI BIR DURUMDA dogmasini
 * garanti eder. Alanlar private oldugu icin disaridan bozulamaz.
 */
public class Ogrenci 

{
    private String ad;
    private String soyad;
    private int numara;
    private double vizeNotu;
    private double finalNotu;

    
    private static double notDuzelt(double not)
    {
        if(not<0)
        {
            return 0.0;

        }
        else if(not>100)
        {
            return 100.0;
        }
        else 
        {
            return not;
        }

    }
    public Ogrenci(String ad, String soyad, int numara, double vizeNotu, double finalNotu) // yapıcı  metot
    {   
        this.ad=ad;
        this.soyad=soyad;
        this.numara=numara;
        this.vizeNotu=notDuzelt(vizeNotu);// notDuzel ile kontorl ediyoruz yukarıdaki if ler çalışıyor.
        this.finalNotu=notDuzelt(finalNotu);
    }

    public Ogrenci(String ad, String soyad, int numara)
    {
        this(ad,soyad,numara,0.0,0.0);  
    }

    public Ogrenci(Ogrenci kaynak)
    {
        this(kaynak.ad, kaynak.soyad,kaynak.numara,kaynak.vizeNotu,kaynak.finalNotu);
    }
    public double ortalamaHesapla()
    {
        return vizeNotu*0.4+finalNotu*0.5;
    }
    public boolean gectiMi()
    {
        return ortalamaHesapla()>=50.0;
    }
    public String tamAd()
    {
        return ad + " " + soyad;
    }
    public int getNumara()
    {
        return numara;
    }
    public void notlariGuncelle(double vize, double bitirme)
    {
        this.vizeNotu= notDuzelt(vize);
        this.finalNotu=notDuzelt(bitirme);
    }

    @Override // : Derleyiciye bir nottur: "Haberin olsun, Java'nın varsayılan  toString() metodunun yerine kendi yazdığımı koyuyorum." Yazım hatası yaparsan derleyici seni hemen uyarır.
    public String toString()
    {
        return String.format("%d | %-14s | vize:%5.1f final:%5.1f ort:%6.2f | %s",
                numara, tamAd(), vizeNotu, finalNotu,
                ortalamaHesapla(), gectiMi() ? "GECTI" : "KALDI");
    }
    
}


/*
Bir Yapıcıyı Sıradan Metottan Ayıran 3 Kesin Kural
Dışarıdan bakınca süslü parantezleri var, içine kod yazılıyor, parametre alıyor; metoda çok benziyor. Ama 3 temel kuralda metotlardan tamamen ayrılır:

Adı Özgür Değildir: Sıradan bir metoda notuHesapla, selamVer gibi istediğin ismi verirsin. Yapıcının adı ise sınıfın adıyla harfi harfine aynı olmak zorundadır (Ogrenci).

Dönüş Türü Asla Yazılmaz: Normal metotlar ya bir şey döner (int, double, String) ya da bir şey dönmüyorsa başına void yazarsın. Yapıcıda void dahi yazılamaz! Çünkü onun varlık sebebi geriye bir değer üretmek değil, yeni doğan nesneyi hayata hazırlamaktır.

Çağrılma Şekli: Normal bir metodu o1.ortalamaHesapla() diye nesne üzerinden istediğin zaman çağırırsın. Yapıcı ise nesne üzerinden çağrılamaz; yalnızca ve yalnızca new operatörüyle, nesne doğarken nesne başına sadece bir kez otomatik çalışır.

Not:Yapıcı; sınıfla aynı adı taşıyan, asla dönüş türü (void dâhil) olmayan, yalnızca new ile nesne doğarken çalışan ve nesneye güvenli/geçerli bir başlangıç durumu veren özel bir inşa mekanizmasıdır. this ise onun sadece bir yardımcısıdır, şartı değildir.

NOT:Yapıcının önüne yanlışlıkla void yazmak, bu haftanın en sık yapılan hatasıdır. void Ogrenci(...) yazdığınızda
derleyici hata vermez; çünkü bu artık Ogrenci adında sıradan bir metottur. Sonuç: new Ogrenci("Ali", ...)
satırı “cannot be applied to given types” hatası verir ve neden olduğunu anlamak zaman alır.


Altın Tasarım İlkeleri (Özet Kural Listesi)
İhtiyaç yoksa getter açma: Dışarının sadece bilmesi gerekeni aç.

Değişmeyecek alana asla setter yazma: Veriyi sabit (read-only) tut.

Değişecek alana mutlaka bekçi (doğrulama) koy: Kural sınıfın içinde yaşasın.

Hesaplanabilir değerleri metot yap: Bellekte senkronizasyon hatasını sıfırla.



 Okunabilir Gösterim: @Override public String toString()

@Override
public String toString() {
    return String.format("%d | %-14s | vize:%5.1f final:%5.1f ort:%6.2f | %s",
        numara, tamAd(), vizeNotu, finalNotu,
        ortalamaHesapla(), gectiMi() ? "GECTI" : "KALDI");
}
Neden Var?

System.out.println(o1); dendiğinde Java nesneyi doğrudan yazdıramaz, arka planda nesnenin toString() metodunu arar.

Yazılmazsa Ne Olur?

Java'nın Object sınıfındaki varsayılan metot çalışır ve konsola anlamsız bir adres basar: Ogrenci@3f2a1b.

Kritik Parçalar:

@Override: Üst sınıftan miras gelen varsayılan toString() metodunu ezdiğimizi derleyiciye bildirir.

String.format(...): printf gibidir; ekrana basmaz, biçimlendirilmiş tek bir String metin üretip döndürür.

gectiMi() ? "GECTI" : "KALDI": Tek satırlık pratik şart ifadesidir (Ternary operatörü).



*/