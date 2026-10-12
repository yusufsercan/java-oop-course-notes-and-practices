// bu dosya Dikdortgen sinifini tanimlar
// tek başına çalıştırılabilir bir program değildir
// DikdörtgenDemo.java dosyası ile birlikte çalıştırılabilir
// bu kodun amacı Dikdörtgen sınıfını tanımlamak ve onunla ilgili metotları sağlamaktır bu sayede DikdörtgenDemo.java dosyasında bu sınıfın nesneleri oluşturulabilir ve kullanılabilir
// * Ornek 5a: Ozellik ve metot ayrimini gosteren kucuk bir sinif

public class Dikdortgen 
{
   public double genislik;
   public double yukseklik;

   public double alan()
   {
     return genislik*yukseklik;
   }
   public double cevre()
   {
     return 2*(genislik+yukseklik);
   }
   public boolean karedirMi()
   {
     return genislik==yukseklik;
   }
   public void olcekle(double katsayi) 
   {
    genislik*=katsayi;
    yukseklik*=katsayi;
   }

}
