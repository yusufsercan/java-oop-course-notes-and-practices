
/*
 * Nesne Yonelimli Programlama - Hafta 01
 * Ornek 3b: Ayni problemin NESNE YONELIMLI cozumu
 *
 * Veri (bakiye) ve onu degistiren davranislar tek bir sinifta toplanmistir.
 * Bakiye disaridan dogrudan degistirilemez; kural sinifin icindedir.
 */


public class Hesap 
{
    private  String sahip;
    private  double bakiye;

    public Hesap(String sahip, double baslangicBakiyesi)
    {
         this.sahip =sahip;
         this.bakiye =(baslangicBakiyesi > 0) ? baslangicBakiyesi : 0.0;
    }
   

public void paraYatır(double miktar)
{
    if(miktar <= 0)
    {
        System.out.println("Hata: yatirilicak tutar pozitif olmak zorundadir.");
        return;
    }
    bakiye += miktar;
}

public void paraCek(double miktar)
{
  if(miktar <0 || miktar > bakiye)
  {
    System.out.println("Hata: cekilcek tutar yetersiz.");
    return;
  }
  bakiye -= miktar;

}

public double getBakiye()
{
    return bakiye;
}
public void durumYaz()
{
    System.out.printf("%-12s : %10.2f TL%n",sahip, bakiye);
}
}

