/*
 * Bir sinif; ozellikler (alanlar) ve davranislar (metotlar) icerir.
 * Bu asamada tum alanlar public birakilmistir; Hafta 4'te kapsulleme ile
 * bu tasarimin neden sakincali oldugunu ve nasil duzeltilecegini gorecegiz.
 */


// bu kod ogrencidemo.java ya aittir; ogrenci.java dosyasini kullanir; yani ogrenci sinifindan nesne uretir ve metotlarini cagirir.

public class Ogrenci {

    // --- Ozellikler (attributes / fields) ---
    public String ad;
    public String soyad;
    public int numara;
    public double vizeNotu;
    public double finalNotu;

    // --- Davranislar (behaviours / methods) ---

    public double ortalamaHesapla() {
        return vizeNotu * 0.4 + finalNotu * 0.6;
    }

    public boolean gectiMi() {
        return ortalamaHesapla() >= 50.0;
    }

    public void bilgiYaz() {
        System.out.printf("%-8d %-10s %-10s  vize:%5.1f  final:%5.1f  ort:%6.2f  %s%n",
                numara, ad, soyad, vizeNotu, finalNotu,
                ortalamaHesapla(), gectiMi() ? "GECTI" : "KALDI"); // buradaki ?: operatoru, if-else'in kisa yazimidir. ayrıca String.format() ile de ayni islem yapilabilir.
    }
}
/*


1. BİÇİM BELİRTECİNİN GENEL ANATOMİSİ
--------------------------------------------------------------------------------
Bir belirteç temel olarak şu sırayla parçalanır:

    % [bayrak] [genişlik] [.küsürat] [tür]

Örnekler:
  %-8d    -> Bayrak: '-', Genişlik: 8, Küsürat: yok, Tür: 'd' (Tam sayı)
  %6.2f   -> Bayrak: yok, Genişlik: 6, Küsürat: .2, Tür: 'f' (Ondalıklı sayı)


================================================================================
2. PARÇALARIN ANLAMI VE İŞLEVİ
--------------------------------------------------------------------------------
1. '%' (Belirteç Başlangıcı):
   Biçimlendirilecek bir alanın başladığını derleyiciye/JVM'e bildirir.

2. '-' Bayrağı (Sola Yaslama):
   - Varsayılan Durum: Sayılar sütunda varsayılan olarak SAĞA yaslanır.
   - '-' Konulduğunda: Veri SOLA yaslanır (Metin gibi hizalanır).

3. Genişlik (Width - '8', '10', '6'):
   - O veri için ekranda ayrılacak TOPLAM karakter sayısını (sütun genişliğini) belirtir.
   - Değer ayrılan alandan kısa ise boşluklarla tamamlanır; uzun ise veri kesilmez,
     genişlik esnetilerek yazdırılır.

4. Küsürat / Duyarlılık (.precision - '.1', '.2'):
   - SADECE ondalıklı sayılarda ('f') geçerlidir.
   - Noktadan sonra virgülden kaç basamak gösterileceğini belirler.
   - Yuvarlama kuralı geçerlidir (örn: 75.67 için %.1f -> 75.7 basar).

5. Dönüştürücü Karakterler (Conversion Characters):
   - 'd' (decimal integer) : int, long, short, byte türleri için tam sayı.
   - 'f' (floating-point)  : double, float türleri için ondalıklı sayı.
   - 's' (string)          : Metinler ve nesnelerin toString() çıktıları.
   - 'c' (character)       : char (tek karakter).
   - 'b' (boolean)         : true ya da false mantıksal değerleri.


================================================================================
3. '%n' İLE '\n' ARASINDAKİ KRİTİK FARK (VİZE TUZAĞI)
--------------------------------------------------------------------------------
- '\n' : Linux ve macOS sistemlerinde alt satıra geçer (LF - Line Feed).
         Windows ortamında ise alt satır standardı '\r\n' (CRLF)'dir.
- '%n' : Platformdan bağımsızdır. Program hangi işletim sisteminde çalışıyorsa
         oraya ait doğru alt satır karakterini otomatik seçer.
* Kural: printf içinde her zaman '\n' yerine '%n' kullanmak Java standartıdır.


================================================================================
4. KODUMUZDAKİ SATIRIN SÜTUN SÜTUN ÇÖZÜMLEMESİ
--------------------------------------------------------------------------------
Satır:
System.out.printf("%-8d %-10s %-10s vize:%5.1f final:%5.1f ort:%6.2f %s%n",
                  numara, ad, soyad, vizeNotu, finalNotu, ort, durum);

- %-8d     -> 'numara': 8 karakterlik alan ayır, sola yasla, tam sayı bas.
              (101 yazılırsa: '101     ' şeklinde 5 boşluk ekler).
- %-10s    -> 'ad' ve 'soyad': 10 karakterlik alanda sola yaslı metin sütunları.
- vize:%5.1f -> 5 karakter toplam alan, virgülden sonra 1 basamak (örn: ' 75.0').
- ort:%6.2f  -> 6 karakter toplam alan, virgülden sonra 2 basamak (örn: ' 81.00').
- %s       -> durum ("GECTI" veya "KALDI").
- %n       -> Satırı bitirip imleci platforma uygun şekilde alt satıra at.


================================================================================
5. C DİLİNDEN GELENLER İÇİN TÜR UYUŞMAZLIĞI FARKI
--------------------------------------------------------------------------------
C dilinde %d yerine double gönderirsen bellekteki baytları yanlış okur ve saçma
bir sayı basar.

Java'da ise tip güvenliği (Type Safety) vardır:
%d beklenen yere double (örn: 75.5) verirsen derleme anında değil, çalışma
anında JVM şu hatayı fırlatır:
-> java.util.IllegalFormatConversionException: d != java.lang.Double
================================================================================

*/