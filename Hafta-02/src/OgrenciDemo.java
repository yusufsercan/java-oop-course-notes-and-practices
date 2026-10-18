/*
 * Nesne Yonelimli Programlama - Hafta 02
 * Ornek 3: Yapicili sinifin kullanimi
 */
public class OgrenciDemo 
{
    public static void main(String[] args)
    {
        Ogrenci o1 = new Ogrenci("Ali","Yilmaz", 33001,65,78);
        Ogrenci o2 = new  Ogrenci("Ayse","Demir",331002);

        System.out.println("--- Baslangic ---");
        System.out.println(o1);
        System.out.println(o2);

        o2.notlariGuncelle(40, 52 );
        System.out.println("\n--- Aysenin Notlari Girildi ---");
        System.out.println(o2);

        Ogrenci o3 = new Ogrenci("Mehmet", "Kaya", 331003,250,-40);
        System.out.println("\n--- Gecersiz Not Denemesi(250 ve -40) ---");
        System.out.println(o3);

        Ogrenci kopya = new Ogrenci(o1);
        kopya.notlariGuncelle(95, 95);
        System.out.println("\n--- Kopya yapici deneyi ---");
        System.out.println("Ozgun : " + o1);
        System.out.println("Kopya : " + kopya);
        System.out.println("o1 == kopya -> " + (o1 == kopya));

        Ogrenci[] sinif = { o1, o2, o3 };
        double toplam = 0;
        for (Ogrenci o : sinif) {
            toplam += o.ortalamaHesapla();
        }
        System.out.printf("%nSinif ortalamasi: %.2f%n", toplam / sinif.length);

    }
    
}
/*
Not:System.out.println(o1) çağrısında o1 bir nesnedir, metin değildir. Java bu durumda otomatik olarak
nesnenin toString() metodunu çağırır. Biz bu metodu Ogrenci sınıfında ezdiğimiz için okunabilir bir satır
görüyoruz.

# 3.4. Bir Nesnenin Doğum Aşamaları ve Bellek Mimarisi

### Bir Nesne Doğarken Ne Olur? (new Ogrenci(...))
1. Bellek Ayrılır (Heap): Nesnenin kaplayacağı alan kadar bellek açılır.
2. Varsayılan Değerler Kurulur: Yapıcı çalışmadan ÖNCE JVM tüm alanları sıfırlar.
3. Yapıcı Çalışır: Constructor gövdesi yürütülür, alanlar asıl değerlerini alır.
4. Referans Döner: Heap'teki nesnenin adresi Stack'teki referans değişkenine atanır.

---

### Alanların Varsayılan Değerleri (Heap Alanı İçin)
* byte, short, int, long    -> 0
* float, double             -> 0.0
* boolean                   -> false
* char                      -> '\u0000' (boş karakter)
* String ve Nesne Dizileri  -> null

---

### C ile Java Karşılaştırması:
* C (malloc): Bellek temizlenmez; rastgele "çöp değerler" (garbage values) barındırır.
* Java (new): Bellek yapıcı çalışmadan önce sıfırlanır; tanımsız çöp değer ASLA bulunmaz. Atanmayan alan null veya 0 kalır.

---

### SINAV TUZAĞI (Alan vs. Yerel Değişken Ayrımı):
1. Nesne Alanları (Instance Fields):
   - Tanımlandığında değer verilmese bile varsayılan değer (0, null, false) alır.
   - Hata vermez.

2. Yerel Değişkenler (Local Variables - Metot İçi):
   - Varsayılan değer ALMAZLAR!
   - İlk değer atanmadan kullanılmaya çalışılırsa derleyici hata verir:
     "variable x might not have been initialized"






================================================================================
     CALISMA NOTU: Java'da Nesne Dizileri (Object Arrays) ve For-Each Dongusu
================================================================================
1. INCELENEN KOD BLOGU
--------------------------------------------------------------------------------

Ogrenci[] sinif = { o1, o2, o3 };

double toplam = 0;

for (Ogrenci o : sinif) {

    toplam += o.ortalamaHesapla();

}

System.out.printf("%nSinif ortalamasi: %.2f%n", toplam / sinif.length);


2. SATIR SATIR MUHENDISLIK VE BELLEK ANALIZI
--------------------------------------------------------------------------------

A) Nesne Dizisi Tanimlama: Ogrenci[] sinif = { o1, o2, o3 };

   * Bellek Durumu (Stack vs. Heap):

     Bu satir bellekte yeni Ogrenci nesneleri URETMEZ (cunku 'new Ogrenci' denmedi).

   * Referans Duzeyi:

     Heap'te zaten yasayan o1, o2 ve o3 nesnelerinin bellek adreslerini

     tek bir dizi icinde toplar.

   * Dizi hucreleri nesnelerin kendisini degil, onlara baglanan referans

     kumandalarini tasir.


B) Gelismis For Dongusu (For-Each): for (Ogrenci o : sinif)

   * Anlami / Okunusu:

     "sinif dizisindeki her bir Ogrenci elemanini sirayla al, adini gecici

     olarak 'o' koy ve suslu parantezin icini calistir."

   * Calisma Adimlari:

     - 1. Tur: 'o' degiskeni o1 nesnesini gosterir -> o.ortalamaHesapla() cagrilir.

     - 2. Tur: 'o' degiskeni o2 nesnesini gosterir -> o.ortalamaHesapla() cagrilir.

     - 3. Tur: 'o' degiskeni o3 nesnesini gosterir -> o.ortalamaHesapla() cagrilir.

     - Dizi sonuna gelindiginde dongu otomatik olarak durur.

   * Klasik For ile Farki:

     Klasik 'for (int i = 0; i < sinif.length; i++)' yapisina kiyasla

     sayac degiskeni ($i = 0$) ve indis takip zahmetini ortadan kaldirir.



C) Bicimli Yazdirma ve Dizi Boyutu: System.out.printf(...)

   * sinif.length:

     Dizide kac eleman oldugunu verir (bu ornekte $3$).

   * toplam / sinif.length:

     Toplam notu ogrenci sayisina bolerek sinif ortalamasini hesaplar.

   * %n:

     Isletim sistemine uygun yeni satir karakteridir (\n yerine tercih edilir).

   * %.2f:

     Ondalikli sayiyi virgulden sonra 2 basamakla sinirlar (ornek: 53.33).


3. "BAK SOYLE YAPARSAN HATA ALIRSIN" (SINAV VE MULAKAT TUZAKLARI)
--------------------------------------------------------------------------------
1. sinif.length() Tuzagi:

   - Yanlis: sinif.length()

   - Dogru : sinif.length

   - Neden? Dizilerde 'length' bir metot degil, dizinin yerlesik bir alanidir

     (property/field). Parantez koyarsan kod derlenmez.

     (Not: String'lerde ise karakter sayisi icin metot olan 'metin.length()' kullanilir).


2. null Referans Tehlikesi (NullPointerException):

   - Eger dizi 'new Ogrenci[3]' seklinde acilip elemanlari atanmazsa, hucreler

     varsayilan olarak null kalir.

   - For-each icinde 'o.ortalamaHesapla()' dendigi anda program coker

     (NullPointerException).


3. Indeks Takibi Gereksinimi:

   - Ekrana "1. Ogrenci", "2. Ogrenci" gibi sira numarasi basmak gerekiyorsa

     veya dizi elemanlarinin yerleri degistirilecekse for-each yetersiz kalir;

     klasik 'for (int i = 0; ...)' tercih edilmelidir.


4. ALTIN OZET KURAL

--------------------------------------------------------------------------------

- Nesne dizileri nesneleri degil, kumandalarini saklar.

- For-each yalnizca sirali okuma islemlerinde en guvenli ve temiz yoldur.

- Dizilerde boyut 'length', String'lerde ise 'length()' ile alinir.

*/