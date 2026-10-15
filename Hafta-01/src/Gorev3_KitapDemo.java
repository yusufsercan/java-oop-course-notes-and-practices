public class Gorev3_KitapDemo 
{
    public static void main(String[] args)
    {
      Kitap k1 =new Kitap();
      Kitap k2 =new Kitap();

      k1.baslik="Kurk Mantolu Madonna";
      k1.yazar="Sabahattin Ali";
      k1.sayfaSayisi=450;
      k1.basimYili=1943;
      k1.fiyat=350;
      

      k2.baslik="Overhead";
      k2.yazar="Oliver James";
      k2.sayfaSayisi=350;
      k2.basimYili=2012;
      k2.fiyat= 500;

      k1.bilgiYaz();
      k2.bilgiYaz();

      int yas = k1.yasi(2026);
      System.out.println("1. Kitabın Yaşı: " + yas);

      double sayfaFiyat = k1.sayfaBasinaFiyat();
      System.out.printf("1. Kitabın Sayfa Başına Fiyatı: %.2f TL%n", sayfaFiyat);

      k1.indirimUygula(20);

      System.out.println(" --- %20 İndirim Sonrası ---");
      k1.bilgiYaz();

      Kitap[] raf = { k1, k2 };
      double toplamDeger = 0.0;
      for (Kitap k : raf) 
    {
      toplamDeger += k.fiyat;
    }
    System.out.printf("Raftaki Kitapların Toplam Değeri: %.2f TL%n", toplamDeger);


    }
    
}
