

public class Ogrenci extends  Kisi
{
    private  final String ogrenciNo;
    private  final String bolum;
    private double gno;

    private  static int uretilen = 0;

    public Ogrenci(String ad, String tcNo, int dogumYili, String ogrenciNo, String bolum)
    {
        super(ad, tcNo, dogumYili);
        System.out.println("Ogrenci constructor calisti");
        if(ogrenciNo == null || ogrenciNo.isBlank())
        {
            throw new IllegalArgumentException("Ogrenci no bos olamaz");
        }
        this.ogrenciNo=ogrenciNo;
        this.bolum=bolum;
        this.gno=0.0;   
        uretilen++;
    }
    public boolean gnoAyarla(double yeniGno)
    {
        if(!Double.isFinite(yeniGno)|| yeniGno<0.0|| yeniGno >4.0)//>Double.isFinite() Java'da bir double değerinin sonlu (finite) bir sayı olup olmadığını kontrol eden statik bir metottur.
        //Ogrenci.java kodunda bunu kullanmamızın nedeni, öğrencinin GNO değerine yalnızca geçerli sayılar atanmasını sağlamak.
        {
            return  false;
        }
        this.gno=yeniGno;
        return true;
    }
    public double getGno()
    {
        return gno;
    }
    public static int uretilenSayisi() 
    {
        return uretilen;
    }
    @Override
    public String kimlikKarti() {
        return super.kimlikKarti()
                + " | OGRENCI " + ogrenciNo
                + " (" + bolum + ")";
    }

    @Override
    public String toString() {
        return "Ogrenci{" + getAd()
                + ", " + ogrenciNo
                + ", GNO=" + gno + "}";
    }
}
