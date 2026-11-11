public class Kisi 
{
    private final String ad;
    private final String tcNo;
    private final int dogumYili;

    public static final int SIMDIKI_YIL=2026;

    public Kisi(String ad,String tcNo, int dogumYili)
    {
        System.out.println("Kisi constructor calisti");

        if(ad==null ||ad.isBlank())
        {
            throw new IllegalArgumentException("Ad bos olamaz.");
        }
        if(tcNo==null || tcNo.length()!=11)
        {
            throw new IllegalArgumentException("TC No 11 haneli olmali");
        }
        this.ad=ad;
        this.tcNo=tcNo;
        this.dogumYili=dogumYili;
    }
    public String getAd()
    {
        return ad;
    }
    public int yas()
     {
        return SIMDIKI_YIL-dogumYili;
     }
     public boolean resitMi()
     {
        return  yas()>=18;
     }
     public String maskeliTcNo()
     {
        return "*********" + tcNo.substring(9);//substring şifrelemeye yarıyor
     }
     public String kimlikKarti()
     {
        return ad + " | " + maskeliTcNo()+ " | " + " | " + yas()+ "yasinda";
     }
    
}
