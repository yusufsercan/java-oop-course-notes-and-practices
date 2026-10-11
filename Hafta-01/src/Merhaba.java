/**
 *
 * KRITIK KURAL (Dosya - Sinif Uyumu):
 * Java'da bir dosya icinde en fazla bir tane 'public' sinif olabilir ve dosya adi,
 * bu public sinifin adiyla BUYUK-kucuk harf dahil birebir ayni olmak zorundadir.
 * (Merhaba.java -> public class Merhaba)
 */
public class Merhaba {

    /**
     * JVM'in (Java Virtual Machine) programi baslatmak icin aradigi giris kapisidir.
     * C/C++ dilindeki 'int main()' fonksiyonunun karsiligidir.
     *
     * PARAMETRE VE ANAHTAR KELIME ANATOMISI (Vize Icin Kritik):
     * 1. public : JVM'in bu metoda sinif disindan serbestce erisebilmesi gerekir.
     * 2. static : JVM'in bu metodu calistirmak icin once 'new Merhaba()' diyerek
     *             nesne uretmesine gerek kalmaz. Sinif yuklendigi anda dogrudan cagrilabilir.
     *             (Tavuk mu yumurta mi problemini cozer: program baslamadan nesne uretilemez).
     * 3. void   : C'deki gibi 'return 0;' dondurmez; program basariyla biterse JVM
     *             kendisi temiz cikis yapar (hata durumunda System.exit() kullanilir).
     * 4. main   : JVM tarafindan ozel olarak taninan degismez metot adidir.
     * 5. String[] args : Komut satirindan girilen argumanlari tutan metin dizisidir.
     *                    Orn: 'java -cp bin Merhaba ali veli' calistiginda args[0]="ali" olur.
     */
    public static void main(String[] args) {

        // System  : java.lang paketinde bulunan hazir bir sistem sinifi.
        // out     : System sinifinin standart cikis akisini (konsol/ekran) temsil eden static nesnesi.
        // println : Ekrana yazi basip imleci bir alt satira geciren (print line) metot.
        System.out.println("Merhaba, Java!");

        // C'deki printf gibi bicimli yazdirma da Java'da System.out.printf(...) ile yapilabilir.
        System.out.println("KTU Of Teknoloji Fakultesi - Yazilim Muhendisligi");
    }
}