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
  Ucgen sınıfı toplamda yalnızca 32 satırdır. İçinde getAd(), getRenk(),
  setRenk(), ozet() veya toString() metotlarının HİÇBİRİ yazılmamıştır.
  Buna rağmen terminal çıktısında:
  "Dik ucgen   renk=yesil   alan= 12.000   cevre= 17.211"
  bilgisi eksiksiz basılır; çünkü ozet() metodunu üst sınıftan devralmıştır.

3. MİRAS YOLUYLA NELER DEVRALINIR? (TABLO 3.8 - VİZE GARANTİ TABLO)
--------------------------------------------------------------------------------
Üst Sınıftaki Üye   | Alt Sınıfta Erişilebilir mi? | Neden / Nasıl İşler?
--------------------+------------------------------+----------------------------
public metot        | Evet, doğrudan erişilir      | Herkese açıktır.
                    |                              |
protected üye       | Evet, doğrudan erişilir      | Yalnızca alt sınıflara ve 
                    |                              | aynı pakete açıktır.
                    |                              |
varsayılan (paket)  | Aynı paketteyse EVET,        | Erişim yetkisi paket 
                    | farklı paketteyse HAYIR      | sınırıyla belirlenir.
                    |                              |
private alan        | Doğrudan: HAYIR              | Alan bellekte devralınır 
                    | Dolaylı : EVET               | ama alt sınıfa görünmez.
                    |                              | public getter/setter ile 
                    |                              | güvenle okunur/yazılır.
                    |                              |
Yapıcı (Constructor)| HAYIR, devralınmaz           | Yapıcılar miras alınmaz!
                    |                              | Alt sınıf 'super(...)' ile 
                    |                              | üst yapıcıyı çağırmalıdır.
                    |                              |
static metot        | Evet, miras alınır           | Sınıfa aittir, nesneye 
                    | AMA @Override EDİLEMEZ       | değil. Alt sınıfta 
                    |                              | yazılırsa gizlenir (hiding).

4. VİZE SINAVI TUZAKLARI VE KRİTİK AYRINTILAR
--------------------------------------------------------------------------------
[TUZAK 1] "private alanlar alt sınıfa geçer mi?"
CEVAP: EVET. Bir nesne RAM'de (Heap) üretildiğinde, üst sınıfın private alanları
da o nesnenin belleğinde fiziksel olarak yer tutar. Ancak alt sınıfın kodundan
doğrudan 'this.ad' yazamazsın (derleme hatası verir). Üst sınıfın 'getAd()' 
metodu üzerinden dolaylı olarak erişirsin.

[TUZAK 2] "Yapıcılar (Constructor) miras alınır mı?"
CEVAP: KESİNLİKLE HAYIR. Bir sınıf üst sınıfın yapıcısını miras alamaz.
Alt sınıf kendi yapıcısını yazmak ve ilk satırda 'super(...)' diyerek üst sınıfın
kurucusunu tetiklemek zorundadır.

[TUZAK 3] "static metotlar override edilir mi?"
CEVAP: HAYIR. Çok biçimlilik (polymorphism) ve metot ezme (@Override) 
yalnızca çalışma zamanında (Heap bellekteki somut nesneler üzerinde) çalışır. 
static metotlar ise derleme anında sınıfa bağlanır.

5. MÜHENDİSLİK İLKESİ: IS-A vs. HAS-A (KALITIM MI, BİLEŞİM Mİ?)
--------------------------------------------------------------------------------
Miras, kod tekrarını önlemenin TEK ve HER ZAMAN DOĞRU yolu değildir.
Bir sınıfı diğerine bağlamadan önce şu soruyu sormak zorundasın:

  "Alt sınıf gerçekten üst sınıfın bir türü müdür?"

* IS-A İlişkisi (Kalıtım / Inheritance - extends):
  - Daire bir Şekil midir? -> EVET (Daire IS-A Sekil) -> Miras doğru.
  - Ucgen bir Şekil midir? -> EVET (Ucgen IS-A Sekil) -> Miras doğru.

* HAS-A İlişkisi (Bileşim / Composition):
  - Araba bir Motor mudur? -> HAYIR!
  - Araba extends Motor YANLIŞ bir tasarımdır!
  - Arabanın bir motoru VARDIR (Araba HAS-A Motor).
  - Doğru Tasarım: Miras yerine motoru arabanın içine nesne olarak koymaktır:
    class Araba {
        private Motor motor; // Composition (Bileşim)
    }

ÖZET FORMÜL:
- "Bir türü müdür?" -> IS-A  -> extends (Miras)
- "Sahip midir?"     -> HAS-A -> private Alan (Bileşim)
- "Yapabilir mi?"    -> CAN-DO-> implements (Arayüz)
================================================================================
*/