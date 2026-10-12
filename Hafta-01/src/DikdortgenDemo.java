//  * Ornek 5b: Dikdortgen sinifinin kullanimi
// bu kodun amacı Dikdörtgen sınıfını kullanarak nesneler oluşturmak ve onların metotlarını çağırmaktır. DikdörtgenDemo.java dosyası, Dikdörtgen.java dosyasındaki sınıfı kullanır; yani Dikdörtgen sınıfından nesne üretir ve metotlarını çağırır.

public class DikdortgenDemo 
{
    public static void main(String[] args)
    {
        Dikdortgen d1 = new Dikdortgen();
        d1.genislik = 4.0;
        d1.yukseklik = 3.0;

        Dikdortgen d2 = new Dikdortgen();
        d2.genislik = 5.0;
        d2.yukseklik = 5.0;

        yaz("d1", d1);// d1 nesnesinin alan, cevre ve kare mi? bilgilerini yazdirir
        yaz("d2", d2);// d2 nesnesinin alan, cevre ve kare mi? bilgilerini yazdirir

        d1.olcekle(2.0);// d1 nesnesinin genislik ve yukseklik degerlerini iki katina cikarir buradaki metot, Dikdortgen sinifinda tanimlanmis olan olcekle() metodunu cagirir ve d1 nesnesinin genislik ve yukseklik degerlerini iki katina cikarir
        System.out.println("d1 iki katina olceklendi:");
        yaz("d1", d1);
    }
    static void yaz(String etiket, Dikdortgen d) // 
    {
        System.out.printf("%s -> %.1f x %.1f | alan=%.2f | cevre=%.2f | kare mi?=%s%n",etiket, d.genislik, d.yukseklik, d.alan(), 
        d.cevre(), d.karedirMi()? "evet" : "hayir"); 
    }
}
// NOT: javada %lf diyemezsin, sadece %f kullanabilirsin yani float ve double için aynı formatı kullanabilirsin.