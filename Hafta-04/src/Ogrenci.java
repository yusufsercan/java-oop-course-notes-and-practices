/*
 * Nesne Yonelimli Programlama - Hafta 04
 * Ornek 2: TAM KAPSULLENMIS sinif
 *
 * Tum alanlar private. Disariya yalnizca gerekli kapilar acilir:
 *  - ogrenciNo : salt-okunur (final + yalnizca getter)
 *  - ad        : okunur/yazilir ama BOS OLAMAZ
 *  - yas       : okunur/yazilir ama 17..70 araliginda olmalidir
 *  - notOrt    : disaridan HIC yazilamaz; yalnizca notEkle() ile degisir
 */
public class Ogrenci {
	
    public static final int EN_KUCUK_YAS = 17;
    public static final int EN_BUYUK_YAS = 70;

    private final String ogrenciNo;   // dogumda belirlenir, bir daha degismez
    private String ad;
    private int yas;
    private double notToplami;
    private int notAdedi;

    public Ogrenci(String ogrenciNo, String ad, int yas) {
        this.ogrenciNo = ogrenciNo;
        setAd(ad);          // dogrulama tek yerde: setter'da
        setYas(yas);
        this.notToplami = 0.0;
        this.notAdedi = 0;
    }

    // ---- salt-okunur alan: setter YOK ----
    public String getOgrenciNo() {
        return ogrenciNo;
    }

    // ---- dogrulamali setter ----
    public String getAd() {
        return ad;
    }

    public void setAd(String yeniAd) {
        if (yeniAd == null || yeniAd.trim().isEmpty()) {
            System.out.println("  RED: ad bos birakilamaz; eski deger korundu.");
            if (this.ad == null) {
                this.ad = "ISIMSIZ";
            }
            return;
        }
        this.ad = yeniAd.trim();
    }

    public int getYas() {
        return yas;
    }

    public void setYas(int yeniYas) {
        if (yeniYas < EN_KUCUK_YAS || yeniYas > EN_BUYUK_YAS) {
            System.out.println("  RED: yas " + EN_KUCUK_YAS + ".." + EN_BUYUK_YAS
                    + " araliginda olmalidir (verilen: " + yeniYas + ").");
            return;
        }
        this.yas = yeniYas;
    }

    // ---- turetilmis (hesaplanan) getter: karsiligi olan alan YOK ----
    public double getNotOrtalamasi() {
        if (notAdedi == 0) {
            return 0.0;
        }
        return notToplami / notAdedi;
    }

    public int getNotAdedi() {
        return notAdedi;
    }

    /* Ortalama disaridan atanamaz; yalnizca gecerli not eklenerek degisir. */
    public boolean notEkle(double puan) {
        if (puan < 0.0 || puan > 100.0) {
            System.out.println("  RED: not 0..100 araliginda olmalidir (verilen: "
                    + puan + ").");
            return false;
        }
        notToplami += puan;
        notAdedi++;
        return true;
    }

    public boolean gecerMi() {
        return notAdedi > 0 && getNotOrtalamasi() >= 50.0;
    }

    @Override
    public String toString() {
        return String.format("Ogrenci[no=%s, ad=%s, yas=%d, ort=%.2f, notAdedi=%d]",
                ogrenciNo, ad, yas, getNotOrtalamasi(), notAdedi);
    }
}

/*
================================================================================
NESNE YÖNELİMLİ PROGRAMLAMA - HAFTA 04 / TEORİK DERS NOTLARI
ÖRNEK 2: DÜZEY 3 TAM KAPSÜLLEME, TÜRETİLMİŞ GETTER VE MERKEZİ DOĞRULAMA
Dosya: Ogrenci.java
================================================================================

--------------------------------------------------------------------------------
1. YAPICI İÇİNDE 'this.ad = ad' YERİNE NEDEN 'setAd(ad)' ÇAĞRILDI?
--------------------------------------------------------------------------------
* DRY (Don't Repeat Yourself) & Tek Doğruluk Kaynağı:
  - Eğer yapıcıda doğrudan 'this.ad = ad;' yazılsaydı, nesne doğarken gönderilen 
    hatalı veriler (örn. boş isim veya eksi yaş) hiçbir kontrole takılmadan 
    nesnenin durumunu bozardı.
  - Yapıcı içine aynı kontrolleri tekrar yazmak kod tekrarına yol açar.
  - Doğrulama mantığı 'setAd' ve 'setYas' içine bir kez yazılır; yapıcı metot 
    da doğum anında bu metotları çağırarak doğrulamayı tek merkezden işletir.

--------------------------------------------------------------------------------
2. ALAN ÇEŞİTLERİ VE ERİŞİM STRATEJİLERİ
--------------------------------------------------------------------------------
* Salt-Okunur Alan (Immutable Field):
  - 'ogrenciNo': 'final' tanımlanmıştır ve yalnızca 'getter' metodu vardır.
    Doğum anında bir kez atanır, çalışma zamanında bir daha asla değiştirilemez.

* Korumalı Alanlar (Guarded Mutators):
  - 'ad' ve 'yas': 'private' tanımlanmıştır. Yalnızca iş kurallarından (null/boşluk 
    kontrolü, 17..70 yaş sınırı) onay alan değerler içeriye yazılır. Hatalı istek 
    geldiğinde eski geçerli durum korunur.

* Türetilmiş / Hesaplanmış Alan (Derived Getter):
  - 'getNotOrtalamasi()': Sınıf içinde 'notOrtalamasi' adında bir değişken YOKTUR.
    Ortalama, 'notToplami / notAdedi' formülüyle anlık hesaplanır.
  - Neden?: Durum senkronizasyonu hatasını (State Inconsistency) önlemek için.
    Aynı veriyi iki farklı alanda saklamak ileride verilerin birbiriyle çelişmesine 
    yol açar.

--------------------------------------------------------------------------------
3. ANLAMLI METOTLAR VS. KÖRÜ KÖRÜNE SETTER
--------------------------------------------------------------------------------
* Neden 'setNotOrtalamasi()' Yok?:
  - Bir öğrencinin not ortalaması elle keyfi bir sayıya eşitlenemez.
  - Ortalama ancak geçerli bir sınav notu girilerek değişebilir.
  - Bu yüzden dışarıya körü körüne 'set' açmak yerine iş kuralını yöneten 
    'notEkle(double puan)' operasyonu sunulmuştur.
================================================================================
*/



/*
================================================================================
NESNE YÖNELİMLİ PROGRAMLAMA - TEORİK MİMARİ VE TASARIM NOTU
KONU: GETTER VE SETTER KARAR AĞACI (NE ZAMAN, NASIL VE NEDEN YAZILIR?)
Dosya / Konu: KapsullemeTasarimRehberi.md / Vize Hazırlık Notu
================================================================================

--------------------------------------------------------------------------------
1. TEMEL MİMARİ İLKE: EZBERİN BOZULMASI
--------------------------------------------------------------------------------
* Temel Kural: Sınıfın tüm durum değişkenleri (fields) İSTİSNASIZ 'private' yapılır.
* Büyük Hata (Düzey 2 Kapsülleme): 
  - IDE'deki "Generate Getters and Setters" tuşuna basıp her alana hem get hem set
    açmak, o alanları 'public' yapmaktan farksızdır.
  - Kapsülleme "veriye get/set yazmak" değildir; veriyi korumak ve iş kurallarını
    nesnenin içine gömmektir.
* Altın Kural: "Varsayılan olarak HİÇBİR ŞEY açma; sadece zorunlu olan kapıları,
  gerektiği kadar aç."

--------------------------------------------------------------------------------
2. GETTER KARAR AĞACI: "DIŞ DÜNYANIN BU VERİYİ GÖRMESİ GEREKLİ Mİ?"
--------------------------------------------------------------------------------
Soru: "Dışarıdaki bir kod bu alanı ekranda göstermek, faturaya basmak veya
karar vermek için okumak ZORUNDA MI?"

[EVET] --> Doğrudan 'getter' aç:
           Örn: getAd(), getOgrenciNo(), getDeger()
           * Amaç: Dış dünya nesnenin durumunu öğrenir.

[HAYIR] -> 'getter' ASLA YAZILMAZ:
           A) Güvenlik ve Gizlilik:
              - Örn: 'sifreHash', 'krediKartiCvv', 'pinKodu'.
              - Dışarıya şifre verilmez; bunun yerine 'boolean sifreDogrula(girilen)'
                metodu sunulur ("Tell, Don't Ask" ilkesi).
           B) İç Hesaplama / Yardımcı Alanlar:
              - Örn: 'notToplami', 'notAdedi', 'redSayisi'.
              - Dışarısı bu ham sayaçları değil, nihai sonucu merak eder.

[TÜRETİLMİŞ (DERIVED)] -> Karşılığı olmayan sanal 'getter' yaz:
           - Sınıfta alan (field) yoktur, değer anlık hesaplanır.
           - Örn: getNotOrtalamasi() -> return notToplami / notAdedi;
           - Örn: getFahrenheit()    -> return (celsius * 1.8) + 32;
           - Neden?: "Tek Doğruluk Kaynağı" (Single Source of Truth). Aynı veriyi
             iki farklı değişkende tutup senkronizasyon hatasına düşmemek için.

--------------------------------------------------------------------------------
3. SETTER KARAR AĞACI: "BU VERİ NESNE DOĞDUKTAN SONRA DEĞİŞEBİLİR Mİ?"
--------------------------------------------------------------------------------
Değişkenler 3 kategoriye ayrılır:

KATEGORİ A: KİMLİK VE SABİTLER (Salt-Okunur / Immutable)
* Nitelik: Nesnenin kimliğidir, doğumda yapıcıda (constructor) atanır.
* Örnekler: 'ogrenciNo', 'tcKimlikNo', 'iban', 'sensorKodu', 'birim'.
* Karar   : 'final' yapılır, 'setter' KESİNLİKLE YAZILMAZ.
* Açıklama: Bir öğrencinin numarası ya da TC'si keyfi olarak değiştirilemez.

KATEGORİ B: BİR SÜREÇ VEYA OLAY SONUCU DEĞİŞEN VERİLER (İş Metodu)
* Nitelik: Değer değişir; ancak bu değişim doğrudan bir sayıyı atayarak değil,
  bir eylem, fiziksel kural veya finansal işlem sonucu gerçekleşir.
* Örnekler: 'bakiye', 'hiz', 'sicaklikDegeri', 'notOrtalamasi', 'siparisDurumu'.
* Karar   : 'setter' YAZILMAZ. Yerine ANLAMLI İŞ METODU (Domain Method) yazılır.
* Karşılaştırma:
  - YANLIŞ: setBakiye(50000);       -> Hileye açık, kural yok!
  - DOĞRU : yatir(miktar); cek(miktar); -> Limit ve bakiye kontrollü.
  - YANLIŞ: setHiz(120);            -> Gaza basmadan araba hızlanmaz!
  - DOĞRU : gazaBas(pedalKuvveti); freneBas();
  - YANLIŞ: setNotOrtalamasi(85);   -> Sınava girmeden ortalama artmaz!
  - DOĞRU : notEkle(puan);

KATEGORİ C: DOĞRUDAN GÜNCELLENEBİLİR NİTELİKLER (Korumalı / Guarded Setter)
* Nitelik: Kullanıcının veya sistemin doğrudan güncellemesi meşru olan bilgilerdir.
* Örnekler: 'adres', 'telefonNo', 'eposta', 'yas', 'sesSeviyesi'.
* Karar   : 'setter' YAZILIR; fakat ASLA KORUMASIZ / ÇIPLAK YAZILMAZ!
* Kural   : Gelen veri süzgeçten geçirilir (Guard Clauses).
  public void setYas(int yeniYas) {
      if (yeniYas < 17 || yeniYas > 70) {
          // RED: Değer içeri alınmaz, eski geçerli durum korunur.
          return;
      }
      this.yas = yeniYas;
  }

--------------------------------------------------------------------------------
4. PRATİK MİMARİ ÖZET TABLOSU
--------------------------------------------------------------------------------
Alan Türü              | Örnek Alan     | Getter? | Setter? | Tercih Edilen Yol
-----------------------+----------------+---------+---------+--------------------
Kimlik / Değişmez      | ogrenciNo, iban| EVET    | HAYIR   | final + Constructor
Hassas / Gizli Veri    | sifre, pinKodu | HAYIR   | HAYIR   | sifreDogrula()
Dahili Sayaç / Durum   | notToplami     | HAYIR   | HAYIR   | Sınıf içi private
Türetilmiş Değer       | notOrtalamasi  | EVET    | HAYIR   | Formülle hesaplanan get
Süreç Bağımlı Durum    | bakiye, deger  | EVET    | HAYIR   | yatir(), olc()
Doğrudan Ayar / Nitelik| ad, telefon    | EVET    | EVET    | Doğrulamalı (Guarded) set

--------------------------------------------------------------------------------
5. VİZE & MÜLAKAT CEVAP REHBERİ
--------------------------------------------------------------------------------
Soru: "Neden Sensor sınıfında setDeger() veya BankaHesabi sınıfında setBakiye() yok?"
Cevap: "Çünkü bu alanlar dışarıdan keyfi bir atama ile belirlenemez. Sıcaklık
fiziksel bir ölçümün sonucudur ('olc()' metodu kullanılır); bakiye ise bankacılık
kurallarına bağlı finansal bir işlemin sonucudur ('yatir()' ve 'cek()' metotları
kullanılır). Bu alanlara 'set' yazmak kapsüllemenin iş kuralı muhafızlığını yıkar."
================================================================================
*/

/*
================================================================================
NESNE YÖNELİMLİ PROGRAMLAMA - HAFTA 04 DERS NOTLARI
BÖLÜM 3.0 - 3.5: KAPSÜLLEME, GETTER/SETTER SÖZLEŞMESİ VE TÜRETİLMİŞ KAPILAR
================================================================================

1. KAPSÜLLEMENİN TEMEL TANIMI VE İKİ BİLEŞENİ
--------------------------------------------------------------------------------
* Kapsülleme (Encapsulation): Veriyi dış dünyadan saklayıp, ona erişimi yalnızca 
  sınıfın kendi denetimli kapılarından sunmaktır.
* Kapsüllemenin 2 Ayrılmaz Parçası:
  1. Veriyi gizlemek (alanları 'private' yapmak).
  2. Veriyi değiştiren kuralı sınıfın içine taşımak (Guarded setter / Domain method).
  UYARI: Yalnızca 1. adımı yapıp her alana denetimsiz get/set açmak kapsülleme 
  değildir (Düzey 2 Sahte Kapsülleme).

2. BÖLÜM 3.1: EN KÜÇÜK KAPSÜLLEME VE DOĞRULAMA MERKEZİ
--------------------------------------------------------------------------------
* Magic Numbers (Sihirli Sayılar) Yasağı:
  - Yaş sınırları kodun içine 17 veya 70 olarak gömülmez.
  - 'public static final int EN_KUCUK_YAS = 17;' sabitleri kullanılır.
* Yapıcıda Setter Çağırma İlkesi:
  - Yapıcı içinde 'this.ad = ad;' yazmak yerine 'setAd(ad);' çağrılır.
  - Amaç: Doğrulama kurallarını iki kez yazmamak (DRY İlkesi) ve nesnenin 
    doğarken de kurallara uymasını garanti altına almak.

3. BÖLÜM 3.2: GETTER — OKUMA KAPISI
--------------------------------------------------------------------------------
* Kapılar çift olmak zorunda DEĞİLDİR.
* 'getOgrenciNo()' vardır, fakat 'setOgrenciNo()' YOKTUR.
* Bir değişkene yalnızca getter açmak o alanı "Salt-Okunur (Read-Only)" yapar.

4. BÖLÜM 3.3: SETTER — DENETİMLİ YAZMA KAPISI
--------------------------------------------------------------------------------
* Setter bir kapı bekçisidir (Guard Clause).
* Geçersiz veri geldiğinde iki işlem yapar:
  1. Hata/ret uyarısı verir.
  2. 'return;' ile metottan çıkarak atama satırını çalıştırmaz.
* Kritik Sonuç: Nesne geçersiz duruma düşmez; ESKİ GEÇERLİ DEĞERİNİ KORUR.

5. BÖLÜM 3.4: TÜRETİLMİŞ GETTER (DERIVED GETTER)
--------------------------------------------------------------------------------
* Bir getter'ın arkasında bellekte fiziksel bir alan olması şart değildir.
* 'Ogrenci' sınıfında 'notOrtalamasi' alanı YOKTUR.
* Ortalama, 'getNotOrtalamasi()' metodu çağrıldığı anda 'notToplami / notAdedi' 
  şeklinde dinamik hesaplanır.
* İki Büyük Mühendislik Avantajı:
  1. Tek Doğruluk Kaynağı (Single Source of Truth): Ortalama ile notlar asla çelişmez.
  2. Korumasız Atama Engellenir: Dışarıdan kimse kafasına göre ortalama atayamaz.
* 'notEkle()' Metodu:
  - Bu bir setter değildir; bir İŞ METODUDUR (Domain Method).
  - Alan adını değil, gerçekleşen iş olayını modeller (puan sınırı: 0..100).

6. BÖLÜM 3.5: DIŞA AÇIK SÖZLEŞME (TABLO 4.3 ÖZETİ)
--------------------------------------------------------------------------------
Alan            | Erişim       | Okuma Kapısı         | Yazma Kapısı
----------------+--------------+----------------------+-------------------------
ogrenciNo       | private final| getOgrenciNo()       | YOK (Doğumda mühürlü)
ad              | private      | getAd()              | setAd() [Boş olamaz]
yas             | private      | getYas()             | setYas() [17..70 sınır]
notToplami/Adet | private      | getNotOrtalamasi()   | notEkle() [İş Metodu]

VİZE İPUCU:
Hoca sınavda "Neden ogrenciNo'nun setter'ı yok, ortalamanın neden alanı yok?" 
diye sorarsa; "ogrenciNo kimliktir salt-okunurdur, ortalama ise tek doğruluk 
kaynağı ilkesi gereği türetilmiş bir getter olarak tasarlanmıştır" yanıtı 
tam puan getirir.
================================================================================
*/