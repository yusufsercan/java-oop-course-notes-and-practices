public class Gorev1_Kimlik
{
    public static void main (String[] args)
    {
        String adSoyad ="Ali Yılmaz";
        int numara=331001;
        String bolum ="Yazilim Muhendisligi";
        double ortalama = 3.13;

        String cizgi = "+---------------------------------------+";

        System.out.println(cizgi);
        System.out.printf("| %-38s|%n", "OGRENCI KIMLIK KARTI");
        System.out.println(cizgi);

        System.out.printf("| Ad Soyad : %-27s|%n",adSoyad);
        System.out.printf("| Numara   : %-27d|%n",numara);
        System.out.printf("| Bolum    : %-27s|%n", bolum);
        System.out.printf("| Ortalama : %-27.2f|%n", ortalama);
        System.out.println(cizgi);

    }
    


}

// bu kod hafta-01 dersinin lab ödevlerinden biridir. pratik tekrar amaçlı yazılmıştır.