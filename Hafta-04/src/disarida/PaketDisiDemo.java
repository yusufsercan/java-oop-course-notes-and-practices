/*
 * Nesne Yonelimli Programlama - Hafta 04
 * Ornek 10c: FARKLI PAKETTEN erisim
 *
 * Bu sinif "disarida" paketindedir ve okul.Kayit sinifini genisletir.
 *  - public    : erisilir
 *  - protected : yalnizca MIRAS YOLUYLA (this uzerinden) erisilir
 *  - varsayilan: erisilemez
 *  - private   : erisilemez
 */
package disarida;

import okul.Kayit;// import okul.Kayit; ifadesi, disarida paketindeki PaketDisiDemo sınıfının okul paketindeki Kayit sınıfını kullanabilmesini sağlar. Bu sayede, Kayit sınıfının public üyelerine erişebiliriz. Ancak, protected ve default (paket) üyelerine erişim sağlanamaz çünkü bu üyeler farklı paketlerdeki sınıflar tarafından doğrudan erişilemez.
//yani import, sadece sınıfın adını kullanmayı kolaylaştırır ve erişim izinlerini değiştirmez. protected ve default üyeler, farklı paketlerdeki sınıflar tarafından doğrudan erişilemez.
// eğer eklemezsek, disarida.PaketDisiDemo sınıfı okul.Kayit sınıfını kullanamaz ve derleme hatası alırız. Bu nedenle, import ifadesi gereklidir.

public class PaketDisiDemo extends Kayit // extends Kayit, yani PaketDisiDemo sınıfı Kayit sınıfının alt sınıfıdır ve farklı bir pakette yer alır.
//buradaki extends anahtar kelimesi, PaketDisiDemo sınıfının Kayit sınıfından türetildiğini ve onun özelliklerini miras aldığını belirtir. Bu sayede, PaketDisiDemo sınıfı Kayit sınıfının public ve protected üyelerine erişebilir, ancak default (paket) ve private üyelerine erişemez.
// extends Kayit yerine okul.Kayit yazmak da mümkündür, ancak bu durumda PaketDisiDemo sınıfı Kayit sınıfını miras almaz, sadece onun bir örneğini oluşturur ve public üyelerine erişebilir.
{

    public void kendiUzerindenOku() {
        System.out.println("=== Farkli paket, ALT SINIF: disarida.PaketDisiDemo ===");
        System.out.println("  public    kurum = " + this.kurum); // this.kurum, PaketDisiDemo sınıfının kendi örneği üzerinden Kayit sınıfının public üyesine erişir. Bu, alt sınıfın üst sınıfın public üyesine erişebileceğini gösterir.
        System.out.println("  protected bolum = " + this.bolum + "   <- miras yoluyla");
        // System.out.println(this.donem);     // DERLEME HATASI: varsayilan erisim
        // System.out.println(this.gizliId);   // DERLEME HATASI: private
        System.out.println("  varsayilan donem -> ERISILEMEZ (baska paket)");
        System.out.println("  private   gizliId -> ERISILEMEZ");
    }

    public static void main(String[] args) 
    {
        new PaketDisiDemo().kendiUzerindenOku();

        System.out.println();
        System.out.println("=== Farkli paket, ILGISIZ NESNE uzerinden ===");
        Kayit baskasi = new Kayit();
        System.out.println("  public    kurum = " + baskasi.kurum);//baskasi.kurum, farklı paket içindeki bir nesne üzerinden Kayit sınıfının public üyesine erişir. Bu, farklı paketlerdeki nesnelerin public üyelere erişebileceğini gösterir.
        // System.out.println(baskasi.donem);   // DERLEME HATASI: varsayilan, farkli paket 
        // System.out.println(baskasi.bolum);  // DERLEME HATASI: protected, farkli paket
        System.out.println("  protected bolum -> ERISILEMEZ (baska nesne, baska paket)");
        System.out.println("  Yalnizca public uyeler disari acik kalir.");
    }
}


/*
 * ORNEK 10C - PAKETDISIDEMO.JAVA
 * KONU: FARKLI PAKETTEN ERISIM VE MIRAS
 *
 * 1. KODUN AMACI:
 * Farkli paketlerde bulunan siniflar arasindaki erisim
 * kurallarini gostermek. Ozellikle protected ve default
 * arasindaki farki anlamak.
 *
 * 2. PAKET VE IMPORT:
 *
 * package disarida;
 * -> PaketDisiDemo sinifi disarida paketindedir.
 *
 * import okul.Kayit;
 * -> Baska paketteki Kayit sinifinin basit adi ile
 *    kullanilabilmesini saglar.
 *
 * ONEMLI:
 * import miras almak demek DEGILDIR!
 * Sadece sinif adini kullanmayi kolaylastirir.
 * Erisim izinlerini degistirmez.
 *
 * 3. EXTENDS ANAHTAR KELIMESI:
 *
 * public class PaketDisiDemo extends Kayit
 *
 * PaketDisiDemo, Kayit sinifindan miras alir.
 * Yani Kayit ust sinif, PaketDisiDemo alt siniftir.
 *
 * extends okul.Kayit yazmak da ayni mirasi kurar.
 * Burada sinifin tam paket adi kullanilmis olur.
 *
 * 4. kendiUzerindenOku() METODU:
 *
 * this.kurum:
 * - public oldugu icin erisilebilir.
 *
 * this.bolum:
 * - protected oldugu icin miras yoluyla erisilebilir.
 * - Siniflar farkli pakette olsa da bu kullanim gecerlidir.
 *
 * this.donem:
 * - default oldugu icin ERISILEMEZ.
 * - Alt sinif olmak, default erisim sinirini kaldirmaz.
 *
 * this.gizliId:
 * - private oldugu icin ERISILEMEZ.
 * - Miras iliskisi private alana dogrudan erisim saglamaz.
 *
 * 5. MAIN METODUNUN CALISMA SIRASI:
 *
 * ADIM 1:
 * new PaketDisiDemo().kendiUzerindenOku();
 *
 * PaketDisiDemo nesnesi olusturulur ve metodu cagrilir.
 * Kendi uzerinden public ve protected uyeler okunur.
 *
 * ADIM 2:
 * Kayit baskasi = new Kayit();
 *
 * Bu kez Kayit sinifindan ayri bir nesne olusturulur.
 * "baskasi" referansinin turu Kayit'tir.
 *
 * ADIM 3:
 * baskasi.kurum -> public, erisim BASARILI.
 *
 * ADIM 4:
 * baskasi.bolum -> protected, DERLEME HATASI!
 *
 * NEDEN?
 * PaketDisiDemo farkli pakettedir.
 * protected erisim miras iliskisiyle sinirlidir.
 *
 * Alt sinif kendi uzerinden this.bolum okuyabilir,
 * ancak Kayit turundeki baska bir nesnenin bolum
 * alanina bu sekilde erisemez.
 *
 * 6. THIS VE BASKASI FARKI:
 *
 * this -> O anda metodu calistiran mevcut nesne.
 * baskasi -> new Kayit() ile olusturulmus ayri nesne.
 *
 * Bu ornekte protected erisimin sonucunu
 * bu fark belirlemektedir.
 *
 * 7. SINAVDA DIKKAT:
 *
 * - import, private veya default erisimi acmaz.
 * - extends, siniflar arasinda miras kurar.
 * - protected, farkli pakette alt sinifa belirli
 *   kurallarla erisim saglar.
 * - default, farkli pakette miras olsa bile erisilemez.
 * - private, alt siniftan dogrudan erisilemez.
 * - Yasak erisim satirlari yorumdan cikarilirsa
 *   calisma zamani hatasi degil, DERLEME HATASI olur.
 *
 * SONUC:
 * Farkli paketteki alt sinif, kendi uzerinden
 * public ve uygun protected uyeleri kullanabilir.
 * Default ve private uyelere dogrudan erisemez.
 */

