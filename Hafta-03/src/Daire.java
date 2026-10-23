/*
 * Nesne Yonelimli Programlama - Hafta 03
 * Ornek 3a: Sekil'in birinci alt sinifi
 *
 * Daire, Sekil'in SOZLESMESINI yerine getirir: alan() ve cevre()
 * metotlarini kendi formuluyle doldurur. Baska hicbir sey yazmasi gerekmez.
 */
public class Daire extends Sekil //extends Sekil ifadesi miras ilişkisini kurar. super("Daire", renk); satırı, Hafta 2'de öğrendiğiniz yapıcı zincirinin bir üst sınıfa uzanmış hâlidir: önce üst sınıfın yapıcısı çalışır, sonra alt sınıfın gövdesi.
{

    private final double yaricap;

    public Daire(double yaricap, String renk) 
    {
        super("Daire", renk);              // once ust sinifin yapicisi
        if (yaricap <= 0) {
            System.out.println("Uyari: yaricap pozitif olmalidir; 1.0 kullanildi.");
            this.yaricap = 1.0;
        } else {
            this.yaricap = yaricap;
        }
    }

    public double getYaricap() {
        return yaricap;
    }
//@Override işaretleyicisi ise derleyiciye “bu metot üst sınıftaki bir metodu eziyor” der; üst sınıfta böyle bir metot yoksa derleme hatası alırsınız.
    @Override
    public double alan() {
        return Math.PI * yaricap * yaricap;
    }

    @Override
    public double cevre() {
        return 2 * Math.PI * yaricap;
    }
}
