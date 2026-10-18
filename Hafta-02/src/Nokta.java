/*
 * Nesne Yonelimli Programlama - Hafta 02
 * Ornek 4: Yapici asiri yukleme (constructor overloading)
 *
 * Ayni sinifta birden cok yapici bulunabilir; ayirt edici olan
 * PARAMETRE LISTESIDIR (sayi, tur, sira). Donus turu yoktur.
 */


public class Nokta 
{
    private double x;
    private  double y;

    public Nokta()
    {
        this(0.0 , 0.0);
    }

    public Nokta(double kosegen)
    {
        this(kosegen, kosegen);
    }

    public Nokta(double x, double y)
    {
        this.x=x;
        this.y=y;
    }   

    public Nokta(Nokta digeri)
    {
        this(digeri.x,digeri.y);
    }

    public double orijineUzaklik()
    {
        return Math.sqrt(x*x+y*y);
    }

    public double uzaklik(Nokta digeri)
    {
        double dx =this.x - digeri.x;
        double dy =this.y - digeri.y;
        return  Math.sqrt(dx*dx+dy*dy);
    }

    public void tasi(double dx, double dy)
    {
        this.x += dx;
        this.y += dy;   
    }

    @Override 
    public String toString()
    {
        return String.format("%.2f, %.2f",x,y);
    }
    
}



/*
1. SINIFIN AMACI VE KAPSÜLLENMİŞ ALANLAR (FIELDS)
--------------------------------------------------------------------------------
Analitik düzlemdeki 2 boyutlu bir noktayı (x, y) temsil eder.

    private double x;
    private double y;

* Neden 'private'? Koordinatların dışarıdan rastgele veya geçersiz değerlerle 
  doğrudan bozulmasını engellemek; sadece kontrollü metotlarla (tasi gibi) 
  güncellenmesini sağlamak (Encapsulation).


2. 4 FARKLI YAPICI VE PATRON-ÇIRAK MİMARİSİ (CONSTRUCTOR CHAINING)
--------------------------------------------------------------------------------
Bu sınıfta 4 yapıcı bulunur; ancak işi fiilen yapan SADECE 1 TANEDİR:

A) Patron Yapıcı (Full Constructor):
   public Nokta(double x, double y) {
       this.x = x;
       this.y = y;
   }
   -> Sınıfta 'this.x = ...' yazan TEK YER burasıdır. Asıl kurulumu bu yapar.

B) Parametresiz / Orijin Yapıcısı:
   public Nokta() {
       this(0.0, 0.0); // Patronu çağırır
   }

C) Tek Parametreli / Köşegen Yapıcısı:
   public Nokta(double kosegen) {
       this(kosegen, kosegen); // Patronu çağırır
   }
   -> Koordinat düzleminde x = y doğrusu üzerindeki noktaları üretir.

D) Kopya Yapıcı (Copy Constructor):
   public Nokta(Nokta digeri) {
       this(digeri.x, digeri.y); // Patronu çağırır
   }
   -> Var olan bir Nokta'nın bağımsız ikizini Heap'te üretir. 
      Kopya üzerinde yapılan değişiklikler orijinal nesneyi etkilemez.

* DRY Prensibi (Don't Repeat Yourself):
  Tüm yapıcılar patron yapıcıya bağlandığı için ileride bir doğrulama kuralı 
  gelirse (örn. koordinatlar negatif olamaz), değişiklik yalnızca patron 
  yapıcıya yazılır; 4 yapıcı birden otomatik olarak korunur.


3. DAVRANIŞLAR VE MATEMATİKSEL METOTLAR
--------------------------------------------------------------------------------
A) Orijine Uzaklık:
   public double orjineUzaklik() {
       return Math.sqrt(x * x + y * y);
   }
   -> (0,0) noktasına olan mesafeyi Pisagor bağıntısıyla hesaplar. 
      'Math.sqrt' karekök alır.

B) İki Nesne Arasındaki Uzaklık (Nesneler Arası Etkileşim):
   public double uzaklik(Nokta digeri) {
       double dx = this.x - digeri.x;
       double dy = this.y - digeri.y;
       return Math.sqrt(dx * dx + dy * dy);
   }
   -> 'this.x' metodu çağıran nesnenin koordinatıdır; 'digeri.x' ise parametre 
      olarak gelen karşı nesnenin koordinatıdır. İki nokta arası uzaklığı döner.

C) Öteleme / Konum Değiştirme:
   public void tasi(double dx, double dy) {
       this.x += dx;
       this.y += dy;
   }
   -> Noktayı eksenlerde kaydırır (mevcut değere ekleme yapar).

D) Metin Gösterimi:
   @Override
   public String toString() {
       return String.format("%.2f, %.2f", x, y);
   }
   -> 'System.out.println(n1)' dendiğinde RAM adresi yerine "3.00, 4.00" gibi 
      okunabilir bir format üretir.


4. "BAK ŞÖYLE YAPARSAN HATA ALIRSIN" (SINAV VE MÜLAKAT TUZAKLARI)
--------------------------------------------------------------------------------
[TUZAK 1] private Alana Başka Nesne Üzerinden Erişmek:
          Soru: "uzaklik metodunda 'digeri.x' yazdık; hani x private idi, 
                 neden hata vermedi?"
          Cevap: Java'da 'private' kuralı NESNEYE değil, SINIF DÜZEYİNE aittir. 
                 Nokta sınıfının süslü parantezleri içinde olduğun sürece, 
                 ister kendi alanın (this.x) olsun ister başka bir Nokta'nın 
                 alanı (digeri.x) olsun erişebilirsin. Ama 'main' içinde yazarsan 
                 derleme hatası alırsın!

[TUZAK 2] NullPointerException Tehlikesi:
          n1.uzaklik(null);  çağrılırsa 'digeri.x' satırında program patlar! 
          Savunmacı mühendislik için metodun başına 'if (digeri == null)' 
          kontrolü konulmalıdır.

[TUZAK 3] this(...) Çağrısının Yeri:
          public Nokta() {
              System.out.println("Orijin olusturuluyor");
              this(0.0, 0.0); // DERLEME HATASI!
          }
          KURAL: 'this(...)' yapıcı zinciri gövdedeki İLK SATIR olmak zorundadır.


5. VİZE ÖNCESİ 10 SANİYELİK KONTROL TABLOSU
--------------------------------------------------------------------------------
Kullanım               | Parametre Türü / Sayısı | Çağırdığı Yer
-----------------------|-------------------------|------------------------------
new Nokta()            | Parametresiz (0 adet)   | this(0.0, 0.0) -> Patron
new Nokta(5.0)         | double (1 adet)         | this(5.0, 5.0) -> Patron
new Nokta(3.0, 4.0)    | double, double (2 adet) | Alanlara doğrudan atama
new Nokta(eskiNokta)   | Nokta nesnesi (1 adet)  | this(d.x, d.y) -> Patron
================================================================================
*/