
/*
 * Nesne Yonelimli Programlama - Hafta 01
 * Ornek 3c: Hesap sinifinin kullanimi
 */


public class NesneYaklasimi 
{
    public static void main(String[] args)
    {
        Hesap ali = new Hesap("Ali Yılmaz",500.0);
        Hesap ayse = new Hesap("Ayse Demir",1200.0 );

        ali.paraYatır(250.0);
        ayse.paraCek(300.0);
        ali.durumYaz();
        ayse.durumYaz();
        
        System.out.println("--- Kural Dısı Kullanim Denemesi ---");
        ali.paraCek(999999.0);
        ali.durumYaz();

        System.out.println("Ali nin bakiyesi okunabilir: " + ali.getBakiye()+ "TL");
    }
    
}

/*
================================================================================
NESNE YÖNELİMLİ YAKLAŞIM VE KAPSÜLLEME (HESAP & NESNEYAKLASIMI ANALİZİ)
Ders Notu: Hafta 01 - Örnek 3b & 3c İncelemesi
================================================================================

1. MİMARÎ AYRIM: MODEL (Hesap) vs SÜRÜCÜ (NesneYaklasimi)
--------------------------------------------------------------------------------
- Hesap.java (Model / Blueprint):
  * İçinde main() metodu YOKTUR.
  * Tek başına çalıştırılamaz; sadece nesne üretmek için bir şablondur.
  * Veriyi (sahip, bakiye) ve o veriyi koruyan metotları (paraYatir, paraCek)
    tek çatı altında birleştirir.

- NesneYaklasimi.java (Driver / Sürücü):
  * İçinde 'public static void main' vardır; program buradan başlar.
  * 'new Hesap(...)' diyerek Heap bölgesinde yaşayan somut nesneler üretir.
  * Nesnelerin 'public' metotlarını çağırarak sistemi yürütür.


2. KAPSÜLLEME (ENCAPSULATION) NASIL SAĞLANDI?
--------------------------------------------------------------------------------
- 3a (Yapısal) Kodunda:
  bakiyeler[ali] = -9999.0;  --> Kural yok, doğrudan diziye yazıp sistemi bozduk.

- 3b/3c (OOP) Kodunda:
  private double bakiye;    --> Değişken dış dünyaya kilitlendi!
  ali.bakiye = -9999.0;      --> DERLEME HATASI! (bakiye has private access)

- Değer Okuma ve Değiştirme Kuralı:
  * Değer Okumak İçin: 'getBakiye()' kullanılır (Read-only kontrolü).
  * Değer Değiştirmek İçin: Sadece 'paraYatir()' ve 'paraCek()' gibi kuralları
    olan iş metotları kullanılır.


3. SINIF vs NESNE BELLEK MİMARİSİ
--------------------------------------------------------------------------------
- Stack:
  'ali' ve 'ayse' referans değişkenleri (uzaktan kumandalar) Stack'te yaşar.
- Heap:
  'new Hesap("Ali Yılmaz", 500.0)' çağrısıyla açılan gerçek oda Heap'tedir.
  Her hesabın kendi bakiyesi o odanın içinde saklanır; 3a'daki gibi ortak
  bir static diziye ihtiyaç kalmaz.


4. VİZE SINAVI İÇİN KRİTİK UYARILAR
--------------------------------------------------------------------------------
- Neden metotların başında 'static' yok?
  Çünkü 'paraCek()' veya 'paraYatir()' genel birer yardımcı fonksiyon değildir;
  o anki nesnenin (this) kendi bakiyesini değiştiren nesneye özgü davranışlardır.
- Constructor (Yapıcı Metot):
  Sınıf adıyla birebir aynı olan, geri dönüş tipi (void bile) olmayan metottur.
  Nesne 'new' ile doğarken ilk ayarlarını güvenli biçimde yapmak için kullanılır.
================================================================================

*/