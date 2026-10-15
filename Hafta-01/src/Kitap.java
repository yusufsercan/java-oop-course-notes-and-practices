
public class Kitap 
{ 
    public String baslik;
    public String yazar;
    public  int sayfaSayisi;
    public int basimYili;
    public double fiyat;


public int yasi(int buguninYili)
{
   return buguninYili-basimYili;
   
}

public boolean kalinMi()
{
    if(sayfaSayisi>400)
    {
       return true;
    }
    else
        return false;
}

public double sayfaBasinaFiyat()
{
    return fiyat/sayfaSayisi;
}

public void indirimUygula(double yuzde)
{
    if(yuzde<0 || yuzde >100)
    {
        System.out.println("Hata:Geçersiz indirim oranı! (0-100 arasında olmalıdır)");
        return;
    }
     fiyat -= (fiyat * yuzde / 100.0);    
    
}

public void bilgiYaz()
{
    System.out.printf("Kitap: %s | Yazar: %s | Sayfa: %d | Yıl: %d | Fiyat: %.2f TL%n", 
                  baslik, yazar, sayfaSayisi, basimYili, fiyat);
}

    
}
