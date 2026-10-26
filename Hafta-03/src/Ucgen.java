/*
 * Nesne Yonelimli Programlama - Hafta 03
 * Ornek 3c: Sekil'in ucuncu alt sinifi - SONRADAN eklenen tur
 *
 * Bu dosya, SekilKotu.java'daki sorunun cozumunu kanitlar: yeni bir sekil
 * turu eklemek icin VAR OLAN hicbir dosyaya dokunulmaz. Yalnizca bu dosya
 * yazilir ve sistem yeni turu tanir.
 */
public class Ucgen extends Sekil {

    private final double taban;
    private final double yukseklik;

    public Ucgen(double taban, double yukseklik, String renk) {
        super("Dik ucgen", renk);
        this.taban = (taban > 0) ? taban : 1.0;
        this.yukseklik = (yukseklik > 0) ? yukseklik : 1.0;
        if (taban <= 0 || yukseklik <= 0) {
            System.out.println("Uyari: olculer pozitif olmalidir; 1.0 kullanildi.");
        }
    }

    @Override
    public double alan() {
        return taban * yukseklik / 2.0;
    }

    @Override
    public double cevre() {
        return taban + yukseklik + Math.hypot(taban, yukseklik);
    }
}

/*
================================================================================
NESNE YÖNELİMLİ PROGRAMLAMA - DERS ÇALIŞMA NOTLARI
BÖLÜM 4.3: MİRAS (INHERITANCE) VE KODUN YENİDEN KULLANIMI
Dosya Adı: hafta03_miras_ve_kalitim_vize_notu.txt
================================================================================

1. MİRASIN TEMEL AMACI: "ORTAK OLANI YUKARIDA BİR KEZ YAZMAK"
--------------------------------------------------------------------------------
- Tanım: Miras, bir sınıfın başka bir sınıfın üyelerini devralmasıdır.
- İlke: Devralınan üyeler alt sınıfta kesinlikle yeniden yazılmaz; sanki o 
  sınıfın içinde bizzat yazılmış gibi doğrudan kullanılır.
- Çözülen Problem: Kod tekrarını (boilerplate / copy-paste) ortadan kaldırmak 
  ve ortak davranışları tek bir merkezden yönetmektir. Bir değişiklik
  gerektiğinde 10 ayrı sınıfa gitmek yerine sadece üst sınıftaki metodu 
  güncellemek yeterli olur.

2. JAVA MİRAS AĞACI (HIERARCHY TREE)
--------------------------------------------------------------------------------
                        +--------------------+
                        |  java.lang.Object  |  <-- Her sınıfın gizli kökü
                        +---------+----------+
                                  |
                                  v
                        +--------------------+
                        |abstract class Sekil|  <-- Ortak gövde
                        +---------+----------+
                                  |
       +--------------------------+--------------------------+
       |                          |                          |
       v                          v                          v
+---------------+          +---------------+          +---------------+
|  class Daire  |          |  class Kare   |          |  class Ucgen  |
+---------------+          +---------------+          +---------------+

* java.lang.Object:
  Java'da hiçbir sınıf başıboş değildir. Kodunda 'extends' yazmasan dahi 
  derleyici her sınıfı otomatik olarak Object sınıfından türetir.
  (toString(), equals(), hashCode() metotları buradan miras gelir).

* abstract class Sekil:
  - Ortak Alanlar : ad, renk (private)
  - Ortak Metotlar: getAd(), getRenk(), setRenk(), ozet(), toString()
  - Soyut Sözleşme: alan(), cevre() (Gövdesiz; alt sınıflar doldurmak zorunda)

* Somut Kanıt (Ucgen.java):
  Ucgen sınıfı toplamda yalnızca 32 satırdır.
*/