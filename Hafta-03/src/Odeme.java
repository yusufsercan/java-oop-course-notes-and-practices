/*
 * Nesne Yonelimli Programlama - Hafta 03
 * Ornek 7: SOYUTLAMA icin arayuz (interface)
 *
 * Arayuz, bir NE sozlesmesidir: "odeme yapilabilen her sey su iki metoda
 * sahiptir". NASIL yapildigi arayuzde yer almaz. Ayrintilar Hafta 10'da.
 */
public interface Odeme {

    /** Verilen tutari tahsil etmeye calisir; basarili ise true doner. */
    boolean ode(double tutar);

    /** Odeme saglayicisinin gorunen adi. */
    String saglayici();
}

/*
================================================================================

    JAVA VE OOP DERS NOTLARI: ARAYÜZLER (INTERFACES) VE SOYUTLAMA

================================================================================

Konu   : Arayüz (interface) Mantığı, "Ne" ile "Nasıl"ın Ayrılması

Örnek  : Odeme arayüzü, KrediKarti/Havale/Kapora sınıfları ve Kasa mimarisi

İlke   : "Gerçeklemeye değil, arayüze programla" (Program to an interface)

================================================================================



--------------------------------------------------------------------------------

1. O EN HAKLI ŞÜPHE: "ARAYÜZE NE GEREK VAR, İŞİ NİYE DOLANDIRIYORUZ?"

--------------------------------------------------------------------------------

Birçok öğrencinin bu konuya ilk baktığında aklına gelen düşünce şudur:

" elimde zaten KrediKarti sınıfı var. İçine gider 'public boolean ode(...)' 

yazarım. 'kart.ode(100)' der paramı çekerim. Neden araya içi bomboş, metotlarının 

gövdesi dahi olmayan 'interface Odeme' diye bir dosya açıyoruz? Neden bir de 'Kasa' 

diye aracı bir sınıf uyduruyoruz?"



Bu soru ilk başta çok mantıklıdır; çünkü 20-30 satırlık okul ödevlerinde arayüzler 

gereksiz bir bürokrasi gibi görünür.



Ancak kurumsal ve büyük yazılımlarda arayüzler OLMADAN kod yazmak, temeli olmayan 

bir gökdelen dikmeye benzer. Şimdi arayüz yazmasaydık başımıza ne gelirdi, 

ona bakalım.





--------------------------------------------------------------------------------

2. KÂBUS SENARYOSU: ARAYÜZ (interface) OLMASAYDI NE OLURDU?

--------------------------------------------------------------------------------

Diyelim ki 'interface Odeme' diye bir sözleşme yazmadın.

Sadece somut sınıfların var: 'KrediKarti', 'Havale', 'Kapora'.



Şimdi bir mağazanın tahsilat yapan 'Kasa' sınıfını kodlamaya çalış:



    class Kasa {

        // Kredi kartı için metot yazmak zorundasın:

        public static void tahsilEt(KrediKarti kart, double tutar) {

            kart.ode(tutar);

        }



        // Havale için AYRI bir metot yazmak zorundasın:

        public static void tahsilEt(Havale havale, double tutar) {

            havale.ode(tutar);

        }



        // Kapora için AYRI bir metot yazmak zorundasın:

        public static void tahsilEt(Kapora kapora, double tutar) {

            kapora.ode(tutar);

        }

    }



Bu Kötü Tasarımın 3 Büyük Felaketi:



1) ÇÖPLÜĞE DÖNEN KASA (Spagetti Kod):

   Yarın şirket yönetimi geldi: "Sisteme ApplePay, FastPay, Sodexo ve KriptoPara 

   ile ödeme de ekliyoruz" dedi.

   Ne yapacaksın? Tıkır tıkır çalışan, test edilmiş 'Kasa.java' dosyasını açıp 

   içine 4 tane daha aşırı yüklenmiş (overloaded) 'tahsilEt' metodu ekleyeceksin.

   Kasa sınıfı zamanla yüzlerce satırlık bir yük vagonuna döner.



2) FARKLI ŞEYLERİ TEK SEPETE KOYAMAMAK:

   Müşteri bir alışveriş yaptı. Ödemenin 500 TL'sini Kredi Kartıyla, 300 TL'sini 

   Havaleyle yapacak.

   Bunları TEK BİR DİZİDE toplayamazsın!

   Çünkü Java'da 'KrediKarti' ile 'Havale' tamamen farklı iki dünyadır, ortak bir 

   çatıları yoktur:

       KrediKarti[] kartlar = { ... };  // Sadece kart alır!

       Havale[] havaleler   = { ... };  // Sadece havale alır!

   Her ödeme türü için ayrı ayrı 'for' döngüleri yazmak zorunda kalırsın.





--------------------------------------------------------------------------------

3. GERÇEK HAYAT ANALOJİSİ: DUVARDAKİ ELEKTRİK PRİZİ VE CİHAZLAR

--------------------------------------------------------------------------------

Evin duvarındaki 220V elektrik prizini gözünün önüne getir:



- PRİZ BİR ARAYÜZDÜR (interface Odeme):

  Duvardaki prizin tek bir kuralı (sözleşmesi) vardır:

  "Bana standart iki uçlu fiş takarsan, sana 220V elektrik veririm."



- EVİN ELEKTRİK PANOSU BİR KASADIR (class Kasa):

  Prizleri bağlayan elektrik ustası, duvarın arkasındaki kabloları çekerken 

  şunu düşünmez: "Acaba bu odaya Arçelik marka ütü mü takılacak, Philips TV mi, 

  yoksa iPhone şarj adaptörü mü?"

  Pano sadece standarda (prize) bakar; prize ne takıldığı onu hiç ilgilendirmez.



- CİHAZLAR SOMUT SINIFLARDIR (KrediKarti, Havale, Kapora):

  Ütüyü üreten mühendis de evin duvarındaki elektrik kablolarının nereden 

  geçtiğini bilmez. Tek bildiği şey: İki uçlu fiş standardına (arayüze) 

  uygun bir kablo üretmektir.



İşte yazılımda da:

- 'Odeme' bir prizdir.

- 'Kasa' duvardaki panodur.

- 'KrediKarti' ve 'Havale' ise o prize takılan cihazlardır.





--------------------------------------------------------------------------------

4. DERS KODLARININ MİMARİ ANATOMİSİ

--------------------------------------------------------------------------------



A) SÖZLEŞME (Odeme.java):

-------------------------

    public interface Odeme {

        boolean ode(double tutar);

        String saglayici();

    }

- İçinde süslü parantez '{ }' yoktur, kod gövdesi yoktur.

- Bir kanundur: "Bu sistemde ödeme olmak isteyen her sınıf, 'ode' ve 'saglayici' 

  adında iki metodu kendi kafasına göre doldurmak ZORUNDADIR!"





B) SÖZLEŞMEYİ KULLANAN KÖR MÜŞTERİ (Kasa.java):

------------------------------------------------

    class Kasa {

        static void tahsilEt(Odeme yontem, double tutar) {

            boolean sonuc = yontem.ode(tutar); // KASA KİMİNLE KONUŞTUĞUNU BİLMEZ!

            System.out.printf("%-14s %8.2f TL -> %s%n",

                yontem.saglayici(), tutar, sonuc ? "ONAY" : "RED");

        }

    }

- DİKKAT: Metodun parametresi 'KrediKarti' değil, 'Odeme yontem'dir!

- 'Kasa' sınıfının içinde KrediKarti, Havale, Kapora kelimeleri ASLA GEÇMEZ!

- Kasa şuna güvenir: "Bana ne gelirse gelsin, 'Odeme' sözleşmesini imzalamışsa 

  üzerinde 'ode()' metodu vardır; basarım düğmeye çalıştırırım!"





C) SÖZLEŞMEYİ DOLDURANLAR (implements Odeme):

----------------------------------------------

- KrediKarti : Kendi içinde kart numarasını ve LIMIT tutar. Limiti kontrol eder.

- Havale     : Kendi içinde IBAN ve HESAP BAKİYESİ tutar.

- Kapora     : Kendi içinde ÖNCEDEN YATAN TUTARI tutar.

Hepsinin içi, mantığı, kuralları bambaşkadır; ama dışarıya aynı 'ode()' yüzünü gösterirler.





D) ORTAK SEPET VE ÇOK BİÇİMLİ DÖNGÜ (OdemeDemo.java):

-----------------------------------------------------

    Odeme[] yontemler = {

        new KrediKarti("5312 **** 4471", 1200.0),

        new Havale("TR33 0001 2222", 600.0),

        new Kapora(250.0)

    };



    double[] sepet = { 350.0, 500.0, 300.0 };



    for (int i = 0; i < yontemler.length; i++) {

        Kasa.tahsilEt(yontemler[i], sepet[i]); // TEK BİR DÖNGÜDE HER ŞEY ÇALIŞIR!

    }





--------------------------------------------------------------------------------

5. BİZE NE KATTI? (BÜYÜK MÜHENDİSLİK KAZANCI)

--------------------------------------------------------------------------------

Diyelim ki yarın hocan veya şirket yöneticin dedi ki:

"Gençler, sisteme 'HediyeCeki' ile ödeme özelliği ekleyin."



Kötü Sistemde Ne Olurdu?

- Kasa sınıfını açardın, kodlarını kurcalardın, hata yapma riskin tavan yapardı.



Arayüzlü (Soyutlanmış) Sistemde Ne Olur?

1. 'Kasa.java' dosyasına TEK BİR HARF BİLE DOKUNMAZSIN!

2. Sadece yeni bir dosya açarsın:

   

       public class HediyeCeki implements Odeme {

           private double cekTutari;

           

           public HediyeCeki(double tutar) { this.cekTutari = tutar; }



           @Override

           public boolean ode(double tutar) {

               if (tutar == cekTutari) { return true; } // Kısmi ödeme yok

               return false;

           }



           @Override

           public String saglayici() { return "Hediye Ceki"; }

       }



3. Fişi prize takar gibi sisteme eklersin:

   Kasa.tahsilEt(new HediyeCeki(100.0), 100.0);

   Ve sistem tıkır tıkır çalışır!



Buna yazılım mühendisliğinde SOLID ilkelerinin ikincisi olan:

"OPEN/CLOSED PRINCIPLE (Açık/Kapalı Prensibi)" denir:

Kodlar YENİ ÖZELLİK EKLEMEYE AÇIK, VAR OLAN KODU DEĞİŞTİRMEYE KAPALI olmalıdır!





--------------------------------------------------------------------------------

6. VİZE KARŞILAŞTIRMASI: SOYUT SINIF (abstract class) vs ARAYÜZ (interface)

--------------------------------------------------------------------------------

Kriter               Soyut Sınıf (abstract class Sekil)  Arayüz (interface Odeme)

-------------------  ----------------------------------  -------------------------------

Anahtar Kelime       extends (Kalıtım)                   implements (Gerçekleme)

Cevapladığı Soru     "Bu NEDİR?" (IS-A)                  "Bu NE YAPABİLİR?" (CAN-DO)

Örnek                "Daire bir Şekildir."               "Kredi Kartı ödeme yapabilir."

Ortak Durum (Alan)   Evet (ad, renk gibi değişken tutar) Hayır (Sadece static final sabit)

Metot Gövdesi        Hem gövdeli hem gövdesiz metot olur Sadece gövdesiz imza (Hafta 10 hariç)

Yapıcı (Constructor) Vardır (super(...) ile çağrılır)    KESİNLİKLE YOKTUR!

Miras Kısıtı         Java'da TEK BİR sınıftan türersin.  BİRDEN FAZLA arayüz takabilirsin.





--------------------------------------------------------------------------------

7. AKILDA KALACAK ALTIN CÜMLE

--------------------------------------------------------------------------------

"Gerçeklemeye değil, arayüze programla!"

(Program to an interface, not an implementation.)



Yani: Kod yazarken bir sınıfın parametresine veya değişken tipine somut sınıfı 

('KrediKarti') değil; daima en genel arayüzü ('Odeme') ver. 

Böylece arkada kimin çalıştığı seni hiç bağlamaz, kodun rüzgârda kırılmayan 

esnek bir çınar gibi sağlam kalır.

================================================================================
*/