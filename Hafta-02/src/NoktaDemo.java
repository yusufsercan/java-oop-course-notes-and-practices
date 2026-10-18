/*
 * Nesne Yonelimli Programlama - Hafta 02
 * Ornek 5: Asiri yuklenmis yapicilarin kullanimi
 */
public class NoktaDemo 
{
    public static void main(String[] args)
    { 
       Nokta orijin = new Nokta();
       Nokta kosegen = new Nokta(3.0);
       Nokta serbest = new Nokta(4.0,3.0);
       Nokta kopya = new Nokta(serbest);

       System.out.println("orijin = " + orijin);
       System.out.println("kosegen =" + kosegen);
       System.out.println("serbest = "+ serbest);
       System.out.println("kopya = " + kopya);   

      System.out.printf("%nserbest'in orijine uzakligi: %.2f%n",
                serbest.orijineUzaklik());
        System.out.printf("kosegen ile serbest arasi   : %.2f%n",
                kosegen.uzaklik(serbest));

        // Kopya gercekten bagimsiz mi?
        kopya.tasi(10, 10);
        System.out.println("\n--- kopya.tasi(10, 10) sonrasi ---");
        System.out.println("serbest = " + serbest);   // degismedi
        System.out.println("kopya   = " + kopya);
        System.out.println("serbest == kopya -> " + (serbest == kopya));
    }
}

/*

1. AŞIRI YÜKLEME (OVERLOADING) NEDİR?
--------------------------------------------------------------------------------
Aynı sınıf içinde, AYNI İSME sahip fakat PARAMETRE LİSTESİ FARKLI birden fazla 
yapıcı (constructor) veya metot bulunmasıdır.

* Ne İşe Yarar? Nesneyi farklı senaryolara göre (bilgisi tam olan, bilgisi eksik 
  olan veya kopyalanacak nesne) farklı yollardan hayata başlatmayı sağlar.
* Karar Zamanı: Derleme anında (Compile Time) derleyici tarafından belirlenir.


2. DERLEYİCİ NEYE BAKAR, NEYE BAKMAZ?
--------------------------------------------------------------------------------
DERLEYİCİNİN BAKTIĞI (Ayırt Edici Olanlar):
1. Parametre Sayısı  -> Nokta() vs Nokta(double x)
2. Parametre Türü    -> Nokta(double x) vs Nokta(int x)
3. Parametre Sırası  -> Nokta(double x, int y) vs Nokta(int y, double x)

DERLEYİCİNİN BAKMADIĞI (HATA VERENLER):
1. Parametre Adı:
   Nokta(double x) ile Nokta(double kosegen) AYNI kabul edilir -> HATA!
2. Metot Dönüş Türü:
   int topla(int a) ile double topla(int a) AYNI kabul edilir -> HATA!


3. SLAYTTAKİ GEÇERLİ / GEÇERSİZ ÖRNEKLER (TABLO 2.7)
--------------------------------------------------------------------------------
İmza Çifti                           | Durum    | Nedeni
-------------------------------------|----------|-------------------------------
Nokta() / Nokta(double)              | GEÇERLİ  | Parametre sayısı farklı
Nokta(double) / Nokta(int)           | GEÇERLİ  | Parametre türü farklı
Nokta(double, int) / Nokta(int, dbl) | GEÇERLİ  | Parametre sırası farklı
Nokta(double x) / Nokta(double ksg)  | GEÇERSİZ | Yalnızca değişken adı farklı!
Nokta(Nokta) / Nokta(Ogrenci)        | GEÇERLİ  | Parametre nesne türü farklı

Not: mantığını kavra.


4. KOPYA YAPICI (COPY CONSTRUCTOR) BAĞIMSIZLIĞI
--------------------------------------------------------------------------------
Nokta kopya = new Nokta(serbest);
* Bu işlem bellekte yepyeni, bağımsız ikinci bir nesne üretir.
* 'kopya.tasi(10, 10)' çağrıldığında sadece kopya taşınır; 'serbest' nesnesi 
  yerinde kalır. (serbest == kopya kontrolü FALSE döner).



"Sen bu iki yapıcıyı (veya metodu) aynı .java dosyasının içine yan yana yazdığında, Java derleyicisi (compiler) bunu kabul eder mi, etmez mi?"

GEÇERLİ: Java derleyicisi der ki: "Tamam, ben bu ikisini birbirinden rahatça ayırt edebilirim, kodda sorun yok." Kod sorunsuz derlenir.

GEÇERSİZ: Java derleyicisi kırmızı çizgiyi çeker ve hata verir (method is already defined). Der ki: "Sen benimle dalga mı geçiyorsun? Yarın biri bu nesneyi üretmeye kalktığında ben hangisini çalıştıracağımı nereden bileyim?" Kod asla derlenmez.


*/