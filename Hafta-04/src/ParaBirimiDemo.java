/*
 * Nesne Yonelimli Programlama - Hafta 04
 * Ornek 7: Degismez nesnenin davranisi
 */
public class ParaBirimiDemo 
{

    public static void main(String[] args) 
    {
        ParaBirimi ucret = ParaBirimi.lira(1250.75);
        System.out.println("ucret          : " + ucret);

        ParaBirimi zam = ParaBirimi.lira(200.00);
        ParaBirimi yeni = ucret.topla(zam);// burada, ucret nesnesi degismez, topla metodu yeni bir ParaBirimi nesnesi dondurur. Bu sayede, ucret nesnesi hala eski degerini korur ve yan etkilerden kaciniyoruz. Bu, degismez (immutable) sinif kurallarina uygun bir davranistir.

        System.out.println("zam            : " + zam);
        System.out.println("yeni           : " + yeni);
        System.out.println("ucret (hala)   : " + ucret + "   <- DEGISMEDI");

        System.out.println();
        System.out.println("--- Paylasim guvenli ---");
        ParaBirimi paylasilan = ucret;
        System.out.println("paylasilan     : " + paylasilan);
        yardimciIslem(paylasilan);
        System.out.println("cagri sonrasi  : " + paylasilan + "   <- DEGISMEDI");

        System.out.println();
        System.out.println("--- Kural ihlali yapiciyla engellenir ---");
        try // try bloku, ParaBirimi.lira(10).topla(new ParaBirimi(500, "USD")) ifadesinde oluşabilecek IllegalArgumentException istisnasını yakalamak için kullanılır. Eğer bu istisna fırlatılırsa, catch bloğu çalışır ve hata mesajı ekrana yazdırılır. Bu sayede, programın çökmesi engellenir ve kullanıcıya anlamlı bir geri bildirim sağlanır.
        {
            ParaBirimi.lira(10).topla(new ParaBirimi(500, "USD"));// bu ifade, 10 TL ile 500 USD'yi toplamaya çalışır. Ancak, ParaBirimi sınıfında birimKontrol(diger) metodu, iki ParaBirimi nesnesinin aynı para birimi koduna sahip olup olmadığını kontrol eder. Eğer kodlar farklıysa, IllegalArgumentException fırlatır. Bu sayede, farklı para birimlerini toplama işlemi engellenir ve hata oluşur.
        } 
        catch (IllegalArgumentException e) 
        {
            System.out.println("Yakalandi: " + e.getMessage());
        }
        try
        {
            new ParaBirimi(100, "TL");// burada "TL" kodu yerine "TRY" kullanılması gerekir. ParaBirimi sınıfı, para birimi kodlarını standart bir formatta saklamak için toUpperCase() metodunu kullanır ve "TL" kodunu kabul etmez. Bu nedenle, bu ifade IllegalArgumentException fırlatır ve catch bloğu çalışır.
        } 
        catch (IllegalArgumentException e) 
        {
            System.out.println("Yakalandi: " + e.getMessage());
        }
    }

    /* Bu metot nesneyi degistirmeye calissa bile degistiremez: setter yoktur. */
    static void yardimciIslem(ParaBirimi p) 
    {
        ParaBirimi ikiKati = p.carp(2);
        System.out.println("  yardimci icinde uretilen: " + ikiKati);
    }
}
