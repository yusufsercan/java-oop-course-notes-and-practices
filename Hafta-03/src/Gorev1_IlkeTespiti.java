/*
 * Nesne Yonelimli Programlama - Hafta 03 / Laboratuvar
 * Gorev 1: Verilen kodda hangi ilke kullanilmis? (ilke tespiti)
 *
 * Asagidaki dort bolgenin her biri dort temel ilkeden BIRINI ornekler.
 * Ogrenci once foydeki tabloyu doldurur, sonra bu programi calistirip
 * kendi yanitiyla karsilastirir.
 */
public class Gorev1_IlkeTespiti {

    public static void main(String[] args) {
        System.out.println("=== ILKE TESPITI ===");

        // --- BOLGE A ---
        Karakter k = new Karakter("Gezgin", 100);
        k.hasarAl(30);
        k.hasarAl(-10);                       // reddedilir
        System.out.println("A) can = " + k.getCan() + "  (dogrudan yazilamaz)");

        // --- BOLGE B ---
        Savasabilir s = new Savasci("Kaan", 120, 25);
        System.out.println("B) sozlesme uzerinden: " + s.saldiriGucu()
                           + " (sinif adi kullanilmadi)");

        // --- BOLGE C ---
        Savasci sv = new Savasci("Kaan", 120, 25);
        sv.hasarAl(20);                       // metot Karakter'den GELDI
        System.out.println("C) Savasci hasarAl() yazmadi, can = " + sv.getCan());

        // --- BOLGE D ---
        Karakter[] takim = {
            new Karakter("Gezgin", 100),
            new Savasci("Kaan", 120, 25),
            new Buyucu("Elif", 80, 35)
        };
        System.out.println("D) tek dongu, uc farkli davranis:");
        for (Karakter x : takim) {
            System.out.println("   " + x.tanit());
        }
    }
}

/** Temel karakter: kapsulleme ornegi. */
class Karakter {
    private final String ad;
    private int can;

    Karakter(String ad, int can) {
        this.ad = ad;
        this.can = (can > 0) ? can : 1;
    }

    String getAd() {
        return ad;
    }

    int getCan() {
        return can;
    }

    void hasarAl(int miktar) {
        if (miktar <= 0) {
            System.out.println("   Uyari: hasar pozitif olmalidir; islem yapilmadi.");
            return;
        }
        can = Math.max(0, can - miktar);
    }

    String tanit() {
        return String.format("%-8s can=%3d  tur=Karakter", ad, can);
    }
}

/** Soyutlama: "savasabilen her sey" sozlesmesi. */
interface Savasabilir {
    int saldiriGucu();
}

class Savasci extends Karakter implements Savasabilir {
    private final int kilic;

    Savasci(String ad, int can, int kilic) {
        super(ad, can);
        this.kilic = kilic;
    }

    @Override
    public int saldiriGucu() {
        return kilic * 2;
    }

    @Override
    String tanit() {
        return String.format("%-8s can=%3d  tur=Savasci   guc=%d",
                             getAd(), getCan(), saldiriGucu());
    }
}

class Buyucu extends Karakter implements Savasabilir {
    private final int mana;

    Buyucu(String ad, int can, int mana) {
        super(ad, can);
        this.mana = mana;
    }

    @Override
    public int saldiriGucu() {
        return mana + 10;
    }

    @Override
    String tanit() {
        return String.format("%-8s can=%3d  tur=Buyucu    guc=%d",
                             getAd(), getCan(), saldiriGucu());
    }
}

/*
================================================================================
GÖREV 1: İLKE TESPİT TABLOSU (TABLO 3.2)
================================================================================
Bölge | Hangi İlke?                   | Gerekçe (Anahtar Sözcük / Satır No)
------+-------------------------------+-----------------------------------------
  A   | Kapsülleme (Encapsulation)    | 'private' int can alanı doğrudan erişime
      |                               | kapatılmış; değişim 'hasarAl()' metodu 
      |                               | ile sınırlandırılmıştır.
------+-------------------------------+-----------------------------------------
  B   | Soyutlama (Abstraction)       | Nesne somut sınıf yerine 'interface' 
      |                               | (Savasabilir) sözleşmesi üzerinden 
      |                               | çağrılmış, sınıf detayı gizlenmiştir.
------+-------------------------------+-----------------------------------------
  C   | Kalıtım / Miras (Inheritance) | Savasci sınıfı 'extends' Karakter diyerek
      |                               | 'hasarAl()' metodunu üst sınıftan 
      |                               | yeniden yazmadan devralmıştır.
------+-------------------------------+-----------------------------------------
  D   | Çok Biçimlilik (Polymorphism) | Alt sınıflar 'tanit()' metodunu '@Override'
      |                               | ederek tek bir döngüde farklı türlerin 
      |                               | kendilerine has davranmasını sağlamıştır.
================================================================================

KORUMA SATIRI ANALİZİ:
Karakter sınıfı hasarAl() metodunda canın sıfırın altına düşmesini engelleyen
koruma ifadesi 23. satırdadır:
--> "can = Math.max(0, can - miktar);"

KONTROL SORULARI YANITLARI:
1. Savasabilir referansıyla hasarAl() ÇAĞRILAMAZ. Çünkü Savasabilir bir 'interface'tir
   ve içinde yalnızca saldiriGucu() sözleşmesi yer alır.
2. hasarAl() metodu üst sınıf olan Karakter'den 'extends' yoluyla devralınmıştır.
   can alanı Karakter sınıfında private olsa da, hasarAl() bizzat Karakter'in 
   kendi metodu olduğu için doğrudan erişebilir.
3. Buyucu sınıfı Karakter'i 'extends' ettiği için (IS-A ilişkisi) bir Karakter 
   olarak kabul edilir ve Karakter[] dizisine eklenebilir.
================================================================================

*/

/*
================================================================================
NESNE YÖNELİMLİ PROGRAMLAMA - HAFTA 03 / LABORATUVAR
GÖREV 1: DÖRT TEMEL İLKENİN KOD ÜZERİNDEN TESPİTİ VE MİMARİ ANALİZİ
Dosya: Gorev1_IlkeTespiti.java
================================================================================

--------------------------------------------------------------------------------
I. LABORATUVAR FÖYÜ TABLO 3.2 KESİN YANIT ANAHTARI
--------------------------------------------------------------------------------
Bölge | İlke Adı                      | Zorunlu Anahtar Sözcük & Gerekçe
------+-------------------------------+-----------------------------------------
  A   | Kapsülleme (Encapsulation)    | 'private': 'can' alanı doğrudan erişime
      |                               | kapatılmış; kontrolsüz veri girişine karşı
      |                               | 'hasarAl()' metoduyla korunmuştur.
------+-------------------------------+-----------------------------------------
  B   | Soyutlama (Abstraction)       | 'interface': Nesneye somut sınıfı yerine
      |                               | 'Savasabilir' sözleşmesi üzerinden
      |                               | erişilmiş, sınıf detayları gizlenmiştir.
------+-------------------------------+-----------------------------------------
  C   | Kalıtım / Miras (Inheritance) | 'extends': Savasci sınıfı 'Karakter'i
      |                               | genişleterek 'hasarAl()' ve 'getCan()'
      |                               | metotlarını sıfırdan yazmadan devralmıştır.
------+-------------------------------+-----------------------------------------
  D   | Çok Biçimlilik (Polymorphism) | '@Override': Üst sınıftaki 'tanit()'
      |                               | metodu alt sınıflarda ezilmiş; tek döngüde
      |                               | her nesne kendi kimliğine göre davranmıştır.
================================================================================


--------------------------------------------------------------------------------
II. BÖLGE BÖLGE İLKELERİN MÜHENDİSLİK DETAYLARI
--------------------------------------------------------------------------------

1. BÖLGE A: KAPSÜLLEME (ENCAPSULATION) - "VERİYİ VE KURALI ZIRHLA"
   - Problem: Eğer 'public int can;' yazılsaydı, dışarıdan herhangi bir kod
     'k.can = -9999;' veya 'k.can = 50000;' yazarak nesnenin tutarlılığını bozabilirdi.
   - Çözüm: Alan 'private' yapılarak zırh içine alındı.
   - Kritik Koruma Satırı (Satır 23):
     "can = Math.max(0, can - miktar);"
     Bu satır sayesinde karakter 1000 hasar da alsa canı asla negatif olamaz,
     en az 0'a çekilir.
   - Altın Kural: Kapsülleme sadece 'getter/setter' yazmak değil; veriyi koruyan
     iş kurallarını (business rules) nesnenin bizzat içine gömmektir.

2. BÖLGE B: SOYUTLAMA (ABSTRACTION) - "NE YAPTIĞINA ODAKLAN, KİM OLDUĞUNA DEĞİL"
   - Kod: Savasabilir s = new Savasci("Kaan", 120, 25);
   - Burada 's' referansının tipi bir sınıf değil, 'interface'dir (Sözleşme).
   - Dış dünya 's' nesnesinin bir savaşçı mı, ejderha mı yoksa uzay gemisi mi
     olduğunu bilmez ve umursamaz; sadece saldırma yeteneği ('saldiriGucu()')
     olduğunu bilir.
   - Ayrım:
     * Arayüz (interface): "Ne yapılacak?" -> int saldiriGucu();
     * Somut Sınıf (class): "Nasıl yapılacak?" -> return kilic * 2;

3. BÖLGE C: KALITIM / MİRAS (INHERITANCE) - "KODU TEKRAR ETME, DEVRAL"
   - Kod: class Savasci extends Karakter implements Savasabilir { ... }
   - 'Savasci' sınıfı 'hasarAl()' veya 'getCan()' metotlarını yazmamıştır.
   - Ancak 'sv.hasarAl(20);' çağrısı kusursuz çalışır. Çünkü 'extends Karakter'
     diyerek Karakter sınıfının bellekteki ve koddaki tüm mirasını devralmıştır.
   - 'super(ad, can);': Yapıcılar miras alınmaz! Savasci doğarken üst sınıfın
     yapıcısını 'super' ile ilk satırda tetiklemek zorundadır.

4. BÖLGE D: ÇOK BİÇİMLİLİK (POLYMORPHISM) - "TEK ÇAĞRI, ÇOKLU DAVRANIŞ"
   - Kod:
     Karakter[] takim = { new Karakter(...), new Savasci(...), new Buyucu(...) };
     for (Karakter x : takim) {
         System.out.println(x.tanit());
     }
   - Yukarı Çevrim (Upcasting): Savasci ve Buyucu birer Karakter olduğu için
     tek bir Karakter[] dizisine konulabilirler.
   - Dinamik Bağlama (Dynamic Dispatch):
     Döngü yalnızca 'x.tanit()' der. Kodda tek bir 'if / switch' yoktur!
     JVM çalışma anında (runtime) nesnenin gerçek türüne bakar:
     * x bir Gezgin ise -> Karakter.tanit() çalışır.
    */