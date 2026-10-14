
/*
 * Nesne Yonelimli Programlama - Hafta 01
 * Ornek 3b: Ayni problemin NESNE YONELIMLI cozumu
 *
 * Veri (bakiye) ve onu degistiren davranislar tek bir sinifta toplanmistir.
 * Bakiye disaridan dogrudan degistirilemez; kural sinifin icindedir.
 */


public class Hesap 
{
    private  String sahip;
    private  double bakiye;

    public Hesap(String sahip, double baslangicBakiyesi)
    {
         this.sahip =sahip;
         this.bakiye =(baslangicBakiyesi > 0) ? baslangicBakiyesi : 0.0;
    }
   

public void paraYatır(double miktar)
{
    if(miktar <= 0)
    {
        System.out.println("Hata: yatirilicak tutar pozitif olmak zorundadir.");
        return;
    }
    bakiye += miktar;
}

public void paraCek(double miktar)
{
  if(miktar <0 || miktar > bakiye)
  {
    System.out.println("Hata: cekilcek tutar yetersiz.");
    return;
  }
  bakiye -= miktar;

}

public double getBakiye()
{
    return bakiye;
}
public void durumYaz()
{
    System.out.printf("%-12s : %10.2f TL%n",sahip, bakiye);
}
}

/*
================================================================================
DERS NOTU: HESAP SINIFI MİMARİSİ VE 'this' REFERANSI ANALİZİ
Modül: Hafta-01 / Örnek 3b (Hesap.java)
================================================================================

1. 'this' ANAHTAR KELİMESİ NEDİR VE NEDEN KULLANILIR?
--------------------------------------------------------------------------------
- Tanım: 'this', o an üzerinde işlem yapılan nesnenin Heap belleğindeki
  bizzat kendisini işaret eden referanstır.
- Temel Görevi (Değişken Gölgeleme - Shadowing Engeli):
  Sınıf alanı (field) ile metoda parametre gelen yerel değişken aynı ada sahip
  olduğunda isim çakışması yaşanır:
  
      this.sahip = sahip;
      └──┬───┘     └──┬─┘
         │            └── Dışarıdan parametre gelen yerel veri
         └─────────────── Nesnenin Heap'teki asıl 'sahip' alanı

- Sınav Tuzağı:
  Eğer 'sahip = sahip;' yazarsan derleme hatası ALMAZSIN! Ancak parametre 
  kendi kendine atanır, nesnenin asıl alanı 'null' kalır. Bu tehlikeli bir 
  mantık hatasıdır (Logic Error).


2. KAPSÜLLEME (ENCAPSULATION) KALKANI: 'private'
--------------------------------------------------------------------------------
- 'private String sahip;' ve 'private double bakiye;'
  Bu iki satır sayesinde veriler dış dünyaya kilitlenmiştir.
- Sürücü sınıftan (NesneYaklasimi) doğrudan erişim YASAKTIR:
  ali.bakiye = -9999.0;  --> DERLEYİCİ ENGELİ: 'bakiye has private access'
- Veriyi okumak için sadece kontrollü 'getBakiye()' metodu sunulmuştur (Read-Only).


3. İŞ KURALLARI VE SAVUNMACI PROGRAMLAMA (DEFENSIVE PROGRAMMING)
--------------------------------------------------------------------------------
- Constructor (Yapıcı Metot) Koruması:
  'this.bakiye = (baslangicBakiyesi > 0) ? baslangicBakiyesi : 0.0;'
  Biri negatif bakiye ile hesap açmaya kalkarsa ternary (? :) operatörü 
  sayesinde bakiye otomatikman 0.0 yapılır. Nesne daha doğarken korunur.

- Çekim Kontrolü (paraCek):
  'if(miktar <= 0 || miktar > bakiye)'
  1. Çekilecek para 0 veya negatif olamaz.
  2. Hesapta var olan paradan (bakiye) fazlası çekilemez.
  Kural çiğnenirse 'return;' ile metot derhal sonlandırılır; bakiye düşülmez.


4. NEDEN METOTLARIN BAŞINDA 'static' YOK?
--------------------------------------------------------------------------------
- 3a'daki YapisalYaklasim kodunda her metot 'static void paraYatir(int no, ...)'
  şeklindeydi çünkü veriler sahipsiz küresel dizilerde duruyordu.
- Burada ise 'public void paraYatir(double miktar)' yazar. 'static' YOKTUR.
  Çünkü para yatırma veya para çekme davranışı nesneye özgüdür.
  'ali.paraYatir(250)' çağrıldığında, JVM 'this' üzerinden sadece Ali'nin 
  bakiyesini 250 artırır; Ayşe'nin parasına dokunmaz.
================================================================================

 */