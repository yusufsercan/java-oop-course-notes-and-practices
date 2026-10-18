/*
 * Nesne Yonelimli Programlama - Hafta 02
 * Ornek 6: 'this' anahtar sozcugu ve alan golgeleme (shadowing)
 *
 * Parametre adi ile alan adi ayni oldugunda, metot icinde parametre
 * alani GOLGELER. Alana ulasmak icin 'this.' on eki gereklidir.
 */
public class GolgelemeOrnegi 
{
     private String ad;
     private int yas;
     
         /** YANLIS yaklasim: this yok, atama kendine yapiliyor. */
     public void yanlisAyarla(String ad, int yas)
     {
        ad = ad;
        yas=yas;
     }

         /** DOGRU yaklasim: this ile alan acikca belirtiliyor. */
     public void dogruAyarla(String ad, int yas)
     {
        this.ad = ad;
        this.yas =yas;
     }


         /** Alternatif: parametre adini farklilastirmak. */
     public void farklıAdlaAyarla(String yeniAd, int yeniYas)
     {
        ad=yeniAd;
        yas=yeniYas;
     }


         /** this, nesnenin kendisini dondurebilir (zincirleme kullanim). */
     public GolgelemeOrnegi yasArtir()
     {
        this.yas++;
        return this;
     }

     @Override 
     public String toString()
     {
        return "ad ="  +ad +", yas ="+yas;
     }

     public static void main(String[] args)
     {
            GolgelemeOrnegi k = new  GolgelemeOrnegi();

            k.yanlisAyarla("Ali", 20);
            System.out.println("\nyanlisAyarla sonrasi :" + k);

            k.dogruAyarla("Ali", 20);
            System.out.println("\ndogruAyarla sonrasi :" + k);

            k.farklıAdlaAyarla("Ayse", 22);
            System.out.println("\nfarkliAyarla sonrasi :" + k);

            k.yasArtir().yasArtir().yasArtir();
            System.out.println("\nuc kez yasiArtir() :" + k);
     }

    
}




/*

================================================================================
DEĞİŞKEN GÖLGELEME (SHADOWING) VE 'this' REFERANSI
================================================================================

1. DEĞİŞKEN GÖLGELEME (VARIABLE SHADOWING) NEDİR?
--------------------------------------------------------------------------------
Bir metot veya yapıcı (constructor) parametresine, sınıfın alanıyla (field) 
birebir AYNI İSİM verildiğinde ortaya çıkan duruma "Gölgeleme" denir.

* Kural: Dar kapsamlı olan değişken (parametre/yerel değişken), 
  geniş kapsamlı olan değişkenin (sınıf alanı) üzerini örter/gölgeler.
* Benzetme: Sınıfta iki tane "Ahmet" var. Biri ev sahibi (alan), diğeri misafir 
  (parametre). Sadece "Ahmet" dersen herkes misafire bakar; ev sahibi gölgede kalır. 
  "Bizim Ahmet" (this.ad) dersen kafa karışıklığı biter.


2. KOD ÖRNEĞİ VE 3 FARKLI SENARYO
--------------------------------------------------------------------------------
public class Ogrenci {
    private String ad;
    private int numara;

    // ÖRNEK YAPICI (CONSTRUCTOR)
    public Ogrenci(String ad, int numara) {
        // Hangi atama ne anlama gelir?
    }
}

--- SENARYO A: ad = ad;  ---> (EN TEHLİKELİ MANTIK HATASI)
* Gölgeleme Var mı?  : EVET.
* Derleme Hatası     : YOKTUR! Kod derlenir (kırmızı çizgi çıkmaz).
* Sarı Uyarı         : "The assignment to variable ad has no effect"
* Ne Olur?           : Gelen parametre yine parametrenin içine atanır.
* Bellek Sonucu      : Heap'teki nesnenin asıl 'ad' alanına hiçbir şey yazılmaz. 
                       Alan varsayılan değerinde (null) kalır!
* Sınav Puanı        : 0 (Öğrenci nesnesi sahte başlatılmış olur).

--- SENARYO B: this.ad = ad;  ---> (PROFESYONEL MÜHENDİSLİK STANDARDI)
* Gölgeleme Var mı?  : EVET, gölgeleme vardır ama 'this' ile aşılmıştır.
* Derleme Hatası     : YOKTUR.
* Ne Olur?           : Sol taraftaki 'this.ad' Heap'te o an yaşayan nesnenin alanını,
                       sağ taraftaki yalın 'ad' ise dışarıdan gelen parametreyi temsil eder.
* Bellek Sonucu      : Parametre güvenle nesnenin kendi belleğine yazılır.

--- SENARYO C: ad = yeniAd;  ---> (GÖLGELEMENİN OLMADIĞI DURUM)
* Metot İmzası       : public Ogrenci(String yeniAd, int yeniNumara)
* Gölgeleme Var mı?  : HAYIR, KESİNLİKLE YOKTUR!
* Ne Olur?           : Parametre adı (yeniAd) ile alan adı (ad) farklı olduğu için 
                       çakışma yaşanmaz. Java 'ad' gördüğünde doğrudan sınıf alanını anlar.
* Tercih Edilmeme Nedeni: Parametrelere 'yeni...', 'p_...', 'a' gibi yapay ekler takmak 
                         kod kalitesini düşürür. Standart olan Senaryo B'dir.


3. 'this' NEDİR, ARKA PLANDA NASIL ÇALIŞIR?
--------------------------------------------------------------------------------
* 'this', o an üzerinde işlem yapılan nesnenin Heap'teki bellek adresini tutan 
  GİZLİ bir referans kumandasıdır.
* o1 = new Ogrenci("Ali", 101);  çağrıldığında:
  - Yapıcı içindeki 'this', Heap'te yeni açılan 'o1' nesnesini işaret eder.
  - this.ad = ad  işlemi: "Bu nesnenin belleğindeki 'ad' kutusuna git ve 
    parametre olarak gelen değeri yaz" demektir.


4. "BAK ŞÖYLE YAPARSAN HATA ALIRSIN" (SINAV VE MÜLAKAT TUZAKLARI)
--------------------------------------------------------------------------------
[TUZAK 1] Soru: "ad = ad; yazarsak derleyici syntax hatası verir mi?"
          Cevap: HAYIR! Java bunu geçerli bir C/Java ataması sayar ve derler. 
                 Hata çalışma anında (runtime) ortaya çıkar; nesne alanları 
                 null veya 0 kaldığı için ileride NullPointerException patlar!

[TUZAK 2] Soru: "Static metot içinde this.ad kullanabilir miyiz?"
          Cevap: KESİNLİKLE HAYIR! Derleme hatası verir: 
                 "Cannot use this in a static context". 
                 Çünkü static metot sınıfa aittir; çağrıldığında ortada henüz 
                 'new' ile üretilmiş bir nesne (this) olmayabilir.

[TUZAK 3] Soru: "this.ad = ad ile this(...) aynı şey midir?"
          Cevap: HAYIR!
                 - this.ad = ad;       -> Nesnenin ALANINA erişim sağlar.
                 - this("Ali", 100);   -> Aynı sınıftaki BAŞKA BİR YAPICIYI çağırır 
                                          (Zincirleme) ve mutlaka İLK SATIRDA olmalıdır.


5. HATIRLATMA TABLOSU
--------------------------------------------------------------------------------
Yazım Şekli       | Gölgeleme Var mı? | Alan Dolar mı? | Sektör Standardı mı?
------------------|-------------------|----------------|---------------------
ad = ad;          | Evet              | HAYIR (null)   | Asla Kullanılmaz (Hata)
ad = yeniAd;      | Hayır             | Evet           | Acemi Yaklaşımı
this.ad = ad;     | Evet (Çözülmüş)   | Evet           | Tam Standart (Doğru)
================================================================================


    1. this.alan = parametre; (Alan Ataması)

    Ne yapıyor? Parametre ile sınıf alanının adı çakıştığında (gölgeleme olduğunda), nesnenin kendi tapulu alanını belirtir.   
    Ne zaman zorunlu? Az önce yaptığımız gibi; parametreye ad, alana da ad dediysen zorunludur. 


    2. this.metot(); (Aynı Sınıfın Metodunu Çağırmak)

    Ne yapıyor? Sınıfın içindeki bir metodun içindeyken, yine aynı sınıftaki başka bir metodu çağırır.
    Gerekli mi? Aslında başına this. koymasan da Java anlar. Ama bazı yazılımcılar kod okurken "Bu metot dışarıdan bir kütüphaneden mi geldi, yoksa bizim sınıfın kendi metodu mu?" diye tereddüt etmemek için alışkanlıktan this.ortalamaHesapla(); diye yazar. Okunabilirlik içindir


    3. return this; (Zincirleme Çağrı / Fluent API) $\rightarrow$










*/