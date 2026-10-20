public class Kitap {
    // 1. Kapsüllenmiş Alanlar (private)
    private String baslik;
    private String yazar;
    private int sayfaSayisi;
    private int basimYili;
    private double fiyat;

    // --- YAPICILAR (CONSTRUCTORS) ---

    // A) Tam Yapıcı (Tüm alanları doğrular - Ana Patron)
    public Kitap(String baslik, String yazar, int sayfaSayisi, int basimYili, double fiyat) {
        this.baslik = (baslik == null || baslik.isBlank()) ? "İsimsiz" : baslik;
        this.yazar = (yazar == null || yazar.isBlank()) ? "Bilinmiyor" : yazar;
        this.sayfaSayisi = (sayfaSayisi <= 0) ? 1 : sayfaSayisi;
        this.basimYili = (basimYili < 1450 || basimYili > 2100) ? 2026 : basimYili;
        this.fiyat = (fiyat < 0.0) ? 0.0 : fiyat;
    }

    // B) Fiyatı Bilinmeyen Kitap Yapıcısı
    public Kitap(String baslik, String yazar, int sayfaSayisi, int basimYili) {
        this(baslik, yazar, sayfaSayisi, basimYili, 0.0);
    }

    // C) Yalnızca Başlık ve Yazarı Bilinen Kitap Yapıcısı
    public Kitap(String baslik, String yazar) {
        this(baslik, yazar, 1, 2026, 0.0);
    }

    // D) Kopya Yapıcı (Copy Constructor)
    public Kitap(Kitap kaynak) {
        this(kaynak.baslik, kaynak.yazar, kaynak.sayfaSayisi, kaynak.basimYili, kaynak.fiyat);
    }

    // --- GETTER METOTLARI ---
    public String getBaslik() { return baslik; }
    public String getYazar() { return yazar; }
    public int getSayfaSayisi() { return sayfaSayisi; }
    public int getBasimYili() { return basimYili; }
    public double getFiyat() { return fiyat; }

    // --- DENETİMLİ SETTER VE DAVRANIŞ METOTLARI ---
    public void setFiyat(double fiyat) {
        if (fiyat < 0.0) {
            System.out.println("Uyari: Fiyat negatif olamaz; degisiklik yapilmadi.");
            return;
        }
        this.fiyat = fiyat;
    }

    public boolean kalinMi() {
        return this.sayfaSayisi > 400;
    }

    public double sayfaBasinaFiyat() {
        return this.fiyat / this.sayfaSayisi;
    }

    public void indirimUygula(double yuzde) {
        if (yuzde < 0.0 || yuzde > 100.0) {
            System.out.println("Uyari: Gecersiz indirim orani (0-100 arasi olmali).");
            return;
        }
        this.fiyat -= this.fiyat * (yuzde / 100.0);
    }

    public int yasi(int buguninYili) {
        return buguninYili - this.basimYili;
    }
    // 4. Biçimli toString Metodu
    @Override
    public String toString() {
        String kalinEtiketi = (this.sayfaSayisi >= 500) ? " [kalin]" : "";
        
        // %-28s : Sola dayalı 28 karakterlik metin
        // %-16s : Sola dayalı 16 karakterlik metin
        // %4d   : Sağa dayalı tam sayı
        // %8.2f : 2 ondalıklı para formatı
        return String.format("%-28s %-16s %3d sf. %4d %9.2f TL%s",
                this.baslik, this.yazar, this.sayfaSayisi, this.basimYili, this.fiyat, kalinEtiketi);
    }
}   


/*
1. KAVRAMLARIN GÖREV VE YÖN TABLOSU
--------------------------------------------------------------------------------
Kavram        | İngilizce Anlamı | Gerçek Görevi                         | Ne Zaman Çalışır?
--------------|------------------|---------------------------------------|-------------------
Constructor   | İnşa Edici       | Nesneyi kurar, ilk verileri içeri alır| Sadece 'new' anında (doğumda)
Getter        | Alıcı / Getirici | İçerideki veriyi dışarıya OKUTUR      | İstenen her an (okuma)
Setter        | Ayarlayıcı       | İçerideki veriyi GÜNCELLER            | İstenen her an (yazma/değiştirme)

* DİKKAT: Getter veya Setter ekrana yazı yazdırma ('System.out.println') 
  fonksiyonu DEĞİLDİR! Yalnızca bellekteki (RAM) veriye erişim köprüsüdür.


2. VERİ GİRİŞİ NEREDE YAPILDI?
--------------------------------------------------------------------------------
"Veri girişi nerede alınıyor?" sorusunun cevabı: TAM YAPICI (Constructor) içindedir.
Nesne Heap bellekte yaratıldığı an dış dünyadan parametreler alınır ve doğrulanarak 
'private' alanlara kilitlenir:

    Kitap k = new Kitap("Veri Yapilari", "A. Demir", 310, 2019, 150.0);
    // Veri girişi bu parantezin içinde gerçekleşti ve bitti!


3. FÖYDE NEDEN "SETTER YAZILMAZ" DENİLDİ? (DEĞİŞMEZLİK / IMMUTABILITY)
--------------------------------------------------------------------------------
Föydeki kural: "Beş alan için de getter yazılır (setter yazılmaz)".

Mühendislik Sebebi:
* Bir kitap basıldıktan sonra yazar adı, basım yılı veya sayfa sayısı değişmez.
* Eğer bu alanlara 'setSayfaSayisi()' veya 'setYazar()' yazsaydık; programın 
  herhangi bir yerinde kitabın kimliği bozulabilirdi.
* Setter yazmayarak nesne "Salt Okunur" (Read-Only / Immutable) hale getirilir:
  -> "Bilgileri doğarken bir kez ver (Yapıcı),
  -> Dışarıdan sadece okunmasına izin ver (Getter),
  -> Ama kimsenin sonradan değiştirmesine izin verme (Setter yok)!"


4. GÜNLÜK HAYAT BENZETMESİ: KİMLİK KARTI
--------------------------------------------------------------------------------
* Yapıcı (Constructor) : Nüfus müdürlüğünün kimliği ilk kez basması (Ad, doğum yılı).
* Getter (getAd)       : Polisin kimliğe bakıp adını OKUMASI (Veriyi dışarı verir).
* Setter (setDogumYili): Kimlikteki yılı silip elle yeni yıl yazmaktır. Devlet buna 
                         izin vermez; bu yüzden kimlik nesnesinde setter OLMAZ!


5. "BAK ŞÖYLE DÜŞÜNÜRSEN HATA ALIRSIN" (YAYGIN ÖĞRENCİ YANILGILARI)
--------------------------------------------------------------------------------
[TUZAK 1] "Setter ekrana yazdırmaktır":
          Setter 'print' demek değildir. 'setFiyat(200)' nesnenin içindeki 
          fiyat değişkenine yeni değer atar.

[TUZAK 2] "Getter veri alıyorsa kullanıcıdan girdi alan metottur":
          Getter klavyeden ('Scanner' ile) girdi almaz! Nesnenin halihazırda 
          RAM'de tuttuğu değeri çağıran yere teslim eder.

[TUZAK 3] "Her private alana otomatik olarak getter ve setter yazılmalıdır":
          Bu bir ezberdir ve yanlıştır! Sadece okunması gereken değerlere setter 
          yazılmaz. Gereksiz setter yazmak kapsüllemeyi zayıflatır.
================================================================================

isEmpty() vs isBlank()
================================================================================
Metot        | Karakter Sayısı 0 ise? | Sadece Boşluk ("   ") ise?
-------------|------------------------|---------------------------
isEmpty()    | true                   | false (Karakter var sanır!)
isBlank()    | true                   | true  (Boşlukları da boş sayar)

KURAL: Form/kullanıcı girdisi doğrulanırken her zaman:
       (metin == null || metin.isBlank()) kalıbı kullanılır.
================================================================================

*/