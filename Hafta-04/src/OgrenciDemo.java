/*
 * Nesne Yonelimli Programlama - Hafta 04
 * Ornek 3: Kapsullenmis Ogrenci sinifinin davranisi
 *
 * Ayni "kotu" atamalar bu kez sinifin kapisinda durdurulur.
 */
public class OgrenciDemo {

    public static void main(String[] args) {
        Ogrenci o = new Ogrenci("376201", "Elif Kaya", 20);
        System.out.println("Baslangic : " + o);

        System.out.println();
        System.out.println("--- Ayni hatali atamalar deneniyor ---");
        o.setYas(-3);
        o.setAd("");
        o.notEkle(350.0);
        System.out.println("Sonra     : " + o);

        System.out.println();
        System.out.println("--- Gecerli islemler ---");
        o.notEkle(78.0);
        o.notEkle(64.0);
        o.setYas(21);
        System.out.printf("Ortalama  : %.2f (%d not)%n", o.getNotOrtalamasi(),
                o.getNotAdedi());
        System.out.println("Gecti mi  : " + o.gecerMi());
        System.out.println("Son durum : " + o);

        System.out.println();
        System.out.println("--- Salt-okunur alan ---");
        System.out.println("Ogrenci no okunabilir : " + o.getOgrenciNo());
        // o.setOgrenciNo("999");  // DERLEME HATASI: boyle bir metot yok
        // o.ogrenciNo = "999";    // DERLEME HATASI: ogrenciNo private
        System.out.println("Yazma yolu ise hic acilmamistir.");
    }
}

/*
================================================================================
NESNE YÖNELİMLİ PROGRAMLAMA - HAFTA 04 / TEORİK DERS NOTLARI
BÖLÜM 2.2 & 2.3: KAPSÜLLENMİŞ SINIF, BAKIM MALİYETİ VE MİMARİ ESNEKLİK
Dosya: OgrenciDemo.java
================================================================================

--------------------------------------------------------------------------------
1. SORUMLULUĞUN YER DEĞİŞTİRMESİ (INVERSION OF RESPONSIBILITY)
--------------------------------------------------------------------------------
* Eski Tasarım (Düzey 1 - Açık Alan):
  Kuralı çağıran kod bilmek zorundaydı:
  if (yas >= 17 && yas <= 70) { o.yas = yas; }
  Bu yaklaşımda çağıran taraf dalgınlık yaparsa sistem zehirlenir.

* Yeni Tasarım (Düzey 3 - Kapsüllenmiş):
  Kuralı bizzat nesnenin kendisi bilir:
  o.setYas(yas);
  Çağıran kod kuralı bilmese bile hatalı veri gönderdiğinde nesne kendini korur.
  "Nesne geçerli doğar, geçerli yaşar, geçerli ölür."

--------------------------------------------------------------------------------
2. KAPSÜLLEME = GÜVENLİK DEĞİL, BİR "BAKIM (MAINTENANCE)" STRATEJİSİDİR!
--------------------------------------------------------------------------------
* Değişimin Yayılma Alanı (Blast Radius):
  - Açık Alan: Bir kural değiştiğinde (örn. yaş sınırının 16-75 olması),
    bu alana veri yazan N farklı dosyanın tek tek taranıp güncellenmesi gerekir.
    Bir dosya unutulursa sessizce veri bozulur.
  - Kapsüllenmiş Alan: Kural sadece 'setYas()' metodunun içinde 1 satırda değişir.
    Dışarıdaki hiçbir istemci modüle (Kayıt, Sınav, Rapor) dokunulmaz.

--------------------------------------------------------------------------------
3. TABLO 4.2 ÖZETİ: KAPSÜLLEME KARARININ SOMUT SONUÇLARI
--------------------------------------------------------------------------------
Ölçüt                             | public Alan        | private Alan + Kapı
----------------------------------+--------------------+-------------------------
Kuralın yazıldığı yer sayısı      | N yerde (Dağınık)  | 1 yerde (Merkezi)
Kural değişince dokunulacak dosya | N dosya            | Yalnızca 1 sınıf dosyası
Kuralın unutulma riski            | Çok yüksek         | Sıfır (Kapı zorunlu)
Geçersiz nesne oluşabilir mi?     | Evet               | Hayır (İstek reddedilir)
Hatanın anında tespiti            | Yok (Gecikmiş hata)| Var (Anında ret iletisi)
İç gösterimi değiştirme özgürlüğü | İMKÂNSIZ (Kod kırılır)| TAMAMEN SERBEST

--------------------------------------------------------------------------------
4. İÇ GÖSTERİMİ DEĞİŞTİRME ÖZGÜRLÜĞÜ (REPRESENTATION INDEPENDENCE)
--------------------------------------------------------------------------------
* Dış dünya yalnızca 'getNotOrtalamasi()' kapısını bilir.
* Sınıfın yazarının canı isterse içeride 'double notOrtalamasi' tutar;
  canı isterse bu değişkeni silip 'notToplami' ve 'notAdedi' tutarak
  (notToplami / notAdedi) şeklinde dinamik hesaplatır.
* Dış dünyadaki çağıran kodların hiçbiri bu iç değişimden etkilenmez, kırılmaz.
================================================================================
*/


















//ÖNEMLİ !!!

/*
================================================================================

NESNE YONELIMLI PROGRAMLAMA (OOP) CALISMA REHBERI

KONU: GETTER VE SETTER MIMARISI, KAPSULLEME FELSEFESI VE KARAR AGACI

Hedef: Vize, Final ve Teknik Mulakat Hazirlik Dokumani

Format: Duz Metin (Plain Text)

================================================================================



--------------------------------------------------------------------------------

1. GIRIS: GETTER VE SETTER EZBERININ YIKILMASI

--------------------------------------------------------------------------------

Yazilim dunyasinda yeni baslayanlarin en cok dustugu tuzak sudur:

"Sinifin basindaki degiskenleri 'private' yap, sonra IDE'den otomatik olarak 

her degiskene hem get hem de set metodu olustur (Generate Getters/Setters)."



Bu yaklasim Nesne Yonelimli Programlama (OOP) degildir; buna literaturde 

"Duzey 2: Sahte Kapsulleme" denir. Cunku bir degiskeni 'private' yapip altina 

hicbir kontrol olmadan calisan bir 'set' metodu acarsaniz, o degiskeni 

'public' birakmaktan hicbir farki kalmaz.



Altin Ilke:

"Kapsulleme (Encapsulation), sinifa get ve set yazmak degildir. Kapsulleme, 

veriyi dis dunyaya karsi korumak ve veriyi isleyen is kurallarini (business rules) 

bizzat nesnenin icine gomerek nesnenin gecersiz bir duruma dusmesini onlemektir."





--------------------------------------------------------------------------------

2. GETTER VE SETTER HAKKINDAKI 3 BUYUK YANILGI VE GERCEKLER

--------------------------------------------------------------------------------



[YANILGI 1]: "Getter yazmazsam private bir alan konsola ya da ekrana basilamaz."

--------------------------------------------------------------------------------

* GERCEK: 

  Getter metodu bir "ekrana yazdirici" veya "formatlayici" degildir. Getter'in 

  tek gorevi, Heap bellekteki bir degeri disaridaki baska bir koda kurye gibi 

  teslim etmektir. Ekrana basma islemini yalnizca 'System.out.println()' yapar.



* KAPSULLEME KURALI:

  Bir degisken 'private' yapildiginda sadece DISARIDAKI yabanci kodlara 

  kapanir. Sinifin KENDI ICINDEKI tum metotlar o degiskene hicbir get/set 

  metoduna ihtiyac duymadan dogrudan erisebilir!



* KANIT KOD:

  public class Personel {

      private String ad = "Ahmet";

      private double maas = 35000.0;



      // Sinifta TEK BIR TANE BILE getter veya setter YOKTUR!



      // Sinifin kendi metodu icerideki private alanlara dogrudan erisir:

      public void bilgileriYazdir() {

          System.out.println("Personel: " + this.ad + " | Maas: " + this.maas);

      }



      @Override

      public String toString() {

          return "Personel: " + this.ad + " | Maas: " + this.maas;

      }

  }



  // Kullanim:

  Personel p = new Personel();

  p.bilgileriYazdir();       // Calisir! (Getter olmadan)

  System.out.println(p);     // Calisir! (Getter olmadan)



* GETTER NE ZAMAN GEREKIR?:

  Yalnizca disaridaki bir kod o degiskeni eline alip baska bir mantiksal 

  hesapta, dongude veya if kontrolunde kullanmak zorundaysa yazilir:

  if (personel.getMaas() > 30000.0) { ... }





[YANILGI 2]: "Bir degiskene Getter yazildiysa, mecbur Setter da yazilmalidir."

--------------------------------------------------------------------------------

* GERCEK:

  Okumak ile degistirmek tamamen bagimsiz iki farkli yetkidir. Bir nesnenin 

  durumunu disari gostermek istemeniz, o nesnenin disaridan kurcalanmasina 

  izin vereceginiz anlamina gelmez.



* GERCEK HAYAT METAFIORLARI:

  a) Banka ATM'si:

     ATM ekranina baktiginizda bakiyenizi okursunuz (getBakiye() vardir). 

     Fakat ekrana dokunup "benim bakiyem 1.000.000 TL olsun" diye elle sayi 

     yazamazsiniz (setBakiye() KESINLIKLE YOKTUR).

  

  b) Nufus Cuzdani (TC Kimlik No):

     Polis kimliginizi kontrol ederken numaranizi okur (getTcKimlikNo() vardir).

     Ancak polis kimliginizin uzerindeki numarayi tukenmez kalemle karalayip 

     degistiremez (setTcKimlikNo() KESINLIKLE YOKTUR).



  c) Termometre / Sicaklik Sensoru:

     Havadaki sicakligi okursunuz (getDeger() vardir).

     Fakat termometreye "sen su an 50 derece goster" diyemezsiniz (setDeger() 

     yoktur; deger ancak ortam degisince fiziksel olc() metoduyla guncellenir).



* SALT-OKUNUR (READ-ONLY) TASARIM:

  Bir alana yalnizca 'getter' yazip 'setter' yazmazsaniz, o alan "Salt-Okunur" 

  olur. OOP mimarisinde en guvenli ve arzulanan alanlar bunlardir.





[YANILGI 3]: "Sinifta tanimlanan her private degisken icin bir Getter yazilmalidir."

--------------------------------------------------------------------------------

* GERCEK:

  Sinifin icinde tanimlanan bazi degiskenler sadece o sinifin mutfagindaki 

  ara hesaplama araclari veya gizli guvenlik bilgileridir. Dis dunyayi 

  kesinlikle ilgilendirmez.



* ORNEK A (Hassas Veriler - Guvenlik):

  public class Kullanici {

      private String kullaniciAdi;

      private String sifreHash;



      public String getKullaniciAdi() { return kullaniciAdi; }



      // getSifreHash() KESINLIKLE YAZILMAZ! Sifre disari teslim edilmez.

      // Yerine sadece dogrulama operasyonu sunulur:

      public boolean sifreDogruMu(String girilenSifre) {

          return this.sifreHash.equals(hashYap(girilenSifre));

      }

  }



* ORNEK B (Dahili Sayaclar - Is Mantigi):

  Ogrenci sinifindaki 'notToplami' ve 'notAdedi' alanlarini dis dunya ne yapsin?

  Dis dunyadaki karne modulu ogrencinin girdigi sinav notlarinin toplamini degil, 

  ortalamasini ister. 

  Bu yuzden getNotToplami() yazilmaz; yerine dynamic hesaplama yapan 

  'getNotOrtalamasi()' sunulur.





--------------------------------------------------------------------------------

3. MIMARI KARAR AGACI: HANGI ALANA NE YAZILMALI?

--------------------------------------------------------------------------------

Bir sinifa yeni bir degisken eklediginizde su 3 soruyu sirayla sorun:



[SORU 1]: "Dis dunyadaki baska bir kod bu degeri bilmek zorunda mi?"

  |-- HAYIR --> Kesinlikle GETTER YAZMA! (Iceride private olarak kalsin).

  |-- EVET  --> Soru 2'ye gec.



[SORU 2]: "Bu deger hafizada bir degiskende mi saklanmali, yoksa anlik hesaplanabilir mi?"

  |-- ANLIK HESAPLANABILIR --> TURETILMIS GETTER (Derived Getter) yaz!

  |   Ornek: getNotOrtalamasi() { return notToplami / notAdedi; }

  |   Ornek: getFahrenheit()    { return (celsius * 1.8) + 32; }

  |   (Sebep: Single Source of Truth - Tek Dogruluk Kaynagi ilkesi. Iki ayri 

  |    degisken tutup verilerin celismesini onlemek).

  |-- HAM / TEMEL VERI     --> Standart GETTER yaz. (return this.alan;)



[SORU 3]: "Bu deger nesne dogduktan sonra disaridan keyfi olarak atanabilir mi?"

  |-- KESINLIKLE HAYIR --> SETTER YAZMA!

  |   Kimlikler (ogrenciNo, tcNo, iban): Yapicida 'final' atanir, mühürlenir.

  |

  |-- BIR SUREC VEYA EYLEM SONUCU DEGISMELI --> SETTER YAZMA! IS METODU YAZ!

  |   Bakiye   --> yatir(miktar), cek(miktar)

  |   Ortalama --> notEkle(puan)

  |   Hiz      --> gazaBas(), freneBas()

  |   Sensor   --> olc(yeniDeger)

  |

  |-- DOGRUDAN AYARLANABILIR NITELIK --> KONTROLLU (GUARDED) SETTER YAZ!

      Adres, telefon, yas, ad:

      Setter yazilir; ancak mutlaka 'if' filtreleri (Guard Clauses) konularak 

      hatali veriler kapida reddedilir (Orn: yas 17..70 arasi olmali).





--------------------------------------------------------------------------------

4. PRATIK MIMARI TASARIM MATRISI

--------------------------------------------------------------------------------

Alan Tanimi          | Alan Ornekleri     | Getter? | Setter? | Dogru OOP Mimarisi

---------------------+--------------------+---------+---------+----------------------------

Kimlik / Degismez    | ogrenciNo, iban    | EVET    | HAYIR   | final + Yapici (Read-only)

Guvenlik / Hassas    | sifre, pinKodu     | HAYIR   | HAYIR   | sifreDogrula() operasyonu

Dahili Sayac / Durum | notToplami, redAdet| HAYIR   | HAYIR   | Sinif ici private mutfak

Turetilen Bilgi      | ortalama, cevre    | EVET    | HAYIR   | Turetilen Getter (Formul)

Surec Bagimli Durum  | bakiye, sicaklik   | EVET    | HAYIR   | yatir(), cek(), olc()

Profil / Ayar        | eposta, yas, ad    | EVET    | EVET    | Dogrulamali Guarded Setter





--------------------------------------------------------------------------------

5. VIZE VE TEKNIK MULAKAT SORU-CEVAP BANKASI

--------------------------------------------------------------------------------



Soru 1: Bir siniftaki butun alanlari private yapip hepsine dogrudan getter ve 

        setter yazarsak ne olur?

Cevap : Duzey 2 (Sahte Kapsulleme) yapilmis olur. Alanlar private olsa bile, 

        kontrolsuz setter metotlari yuzunden disarisi nesnenin durumunu keyfince 

        bozabilir. Veri ile is kuralinin birlesmesi ilkesi cignenmis olur.



Soru 2: BankaHesabi sinifinda neden 'setBakiye(double bakiye)' metodu bulunmaz?

Cevap : Cunku gercek hayatta bakiye keyfi bir sayiya esitlenemez; bakiye ancak 

        finansal bir islemle degisir. 'setBakiye' acilirsa negatif bakiye atama 

        veya hesaba kural disi para ekleme gibi guvenlik aciklari dogar. Bunun 

        yerine limitleri ve yeterli bakiyeyi denetleyen 'yatir()' ve 'cek()' 

        is metotlari sunulur.



Soru 3: "Turetilen Getter (Derived Getter)" nedir? Bir ornekle aciklayiniz.

Cevap : Sinif icinde karsiligi olan bir alan (field) bulunmayan, degeri cagrildigi 

        anda var olan diger alanlardan hesaplanan metottur.

        Ornek: Ogrenci sinifinda 'double notOrtalamasi' alani tutulmaz. Bunun yerine 

        'getNotOrtalamasi()' metodu cagrildiginda '(notToplami / notAdedi)' anlik 

        hesaplanir. Boylece durum tutarsizligi (state inconsistency) onlenir.



Soru 4: Yapici metotta (Constructor) neden 'this.ad = ad' yerine 'setAd(ad)' 

        cagrisi tercih edilir?

Cevap : DRY (Don't Repeat Yourself - Kendini Tekrar Etme) ilkesi geregince. 

        Dogrulama mantigi (bos ad engelleme, trim yapma) tek bir yerde, yani 

        'setAd' icinde yazilir. Yapici da bu metodu cagirarak ayni kurallari 

        nesne dogarken de isletmis olur; boylece hatali nesne uretimi onlenir.

================================================================================
*/