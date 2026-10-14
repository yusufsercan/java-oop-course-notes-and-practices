/*
 * Nesne Yonelimli Programlama - Hafta 01
 * Ornek 3a: Ayni problemin YAPISAL (C tarzi) cozumu
 * 
 * Veri (diziler) ile onu isleyen fonksiyonlar birbirinden ayridir.
 * Kurallari uygulamak cagiran tarafin sorumlulugundadir.
 */


public class YapisalYaklasim 
{
    static String[] sahipler = new String[100];
    static double[] bakiyeler = new double[100];
    static int hesapSayisi =0;

    public static void main(String[] args)
    {
       int ali = hesapAc("Ali Yilmaz",500.0);
       int ayse = hesapAc("Ayse Demir",1200.0);

       paraYatir(ali,250.0);
       paraCek(ayse,300.0);
       durumYaz(ali);
       durumYaz(ayse);

       System.out.println("--- Kural disi kullanim ---");
       bakiyeler[ali] = -9999.0;
       durumYaz(ali);

    }
    static int hesapAc(String  sahip, double baslangıc)
    {
       sahipler[hesapSayisi] = sahip;
       bakiyeler[hesapSayisi] = baslangıc;
       return hesapSayisi++;
    }
    
    static void paraYatir(int no, double miktar)
    {
        if(miktar <= 0)
        {
            System.out.println("Hata: Yatirilicak Tutar pozitif olmalıdır.");
            return;
        }
        bakiyeler[no] += miktar;
    }
    static void paraCek(int no, double miktar)
    {
        if(miktar <=0 || miktar > bakiyeler[no])
        {
            System.out.println("Hata: cekilecek tutar gecersiz.");
            return;
        }
        bakiyeler[no] -= miktar;
    }
    static void durumYaz(int no)
    {
        System.out.printf("%-12s : %10.2f TL%n", sahipler[no], bakiyeler[no]);
    }
    
}

/*
1. KODUN YAZILMA AMACI VE TEMEL FELSEFESİ

--------------------------------------------------------------------------------

Bu kod, Nesne Yönelimli Programlama (OOP) ilkelerini kullanmaz. Amacı; C dilinde 

alışık olduğumuz "prosedürel / yapısal" yaklaşımın Java sözdizimiyle nasıl 

çalıştığını ve yazılım büyüdükçe nasıl BÜYÜK BİR GÜVENLİK VE YÖNETİM KRİZİNE 

dönüştüğünü somut olarak göstermektir.

NOT:
"Java öğrenirken sınıfların içine static diziler ve static metotlar yığarak yine C gibi kod yazabilirsiniz. 
Ama bunu yaparsanız ne kapsülleme kalır, ne güvenlik kalır, ne de nesne yönelimli mimarinin avantajlarından faydalanabilirsiniz."


================================================================================

2. PARALEL DİZİLER MİMARİSİ (PARALLEL ARRAYS)

--------------------------------------------------------------------------------

Kodun tepesindeki veri yapısı:

    static String[] sahipler = new String[100];

    static double[] bakiyeler = new double[100];

    static int hesapSayisi = 0;



- Buradaki Kritik Problem:

  "Ali Yilmaz" metni ile onun parası olan "500.0" TL arasında bellekte hiçbir 

  organik/yapısal bağ yoktur!

- İkisi tamamen ayrı dünyalardır; tek ortak noktaları tesadüfen aynı indis 

  numarasını (ali = 0) paylaşmalarıdır.

- C'deki Karşılığı:

  C dilinde struct kullanmadan iki ayrı bağımsız dizi tanımlayıp verileri 

  indisler üzerinden takip etmeye benzer.





================================================================================

3. EN BÜYÜK MİMARÎ ZAAF: KURAL DIŞI KULLANIM (NO ENCAPSULATION)

--------------------------------------------------------------------------------

Kodun içindeki en can alıcı satır:

    bakiyeler[ali] = -9999.0; // HİÇBİR ENGEL YOK!



- Neden Derlenir?

  Çünkü 'bakiyeler' dizisi 'public' gibi açıktır, önünde bir koruma kalkanı yoktur.

- Neden Felakettir?

  Normalde 'paraCek()' metodunda 'if (miktar > bakiyeler[no])' şeklinde harika 

  bir güvenlik kuralı vardır. Ancak yapısal yaklaşımda bu kural bir ZORUNLULUK değil, 

  çağıranın insafına kalmış bir TAVSİYEDİR.

  Kötü niyetli veya dikkatsiz bir yazılımcı, vezneyi ('paraCek') atlayıp doğrudan 

  kasaya ('bakiyeler[ali]') elini sokarak hesabı eksiye batırabilir. 

  Sistem buna engel olamaz!





================================================================================

4. KODDAKİ 'printf' VE BİÇİMLENDİRME DETAYLARI (SINAV ANALİZİ)

--------------------------------------------------------------------------------

Satır:

    System.out.printf("%-12s : %10.2f TL%n", sahipler[no], bakiyeler[no]);



* %-12s :

  - Birinci parametredir, 'sahipler[no]' (String) ile eşleşir.

  - '12' : Minimum 12 karakterlik sütun genişliği ayırır.

  - '-'  : Metni SOLA yaslar, boşlukları sağa ekler (Ali Yilmaz  ).

* ' : ' :

  - Format dışı sabit karakterdir; ekrana olduğu gibi iki nokta ve boşluk basar.

* %10.2f :

  - İkinci parametredir, 'bakiyeler[no]' (double) ile eşleşir.

  - 'f'  : Ondalıklı sayı (float/double) formatıdır.

  - '.2' : Virgülden/noktadan sonra sabit 2 basamak gösterir (900.00).

  - '10' : Sayının noktası dahil toplam genişliğidir. Eksi olmadığı için SAĞA yaslar.

* %n :

  - Platformdan bağımsız güvenli satır sonu karakteridir (Windows/Linux uyumlu \n).





================================================================================

5. NEDEN BÜTÜN METOTLAR 'static'?

--------------------------------------------------------------------------------

- Kodda 'hesapAc', 'paraYatir', 'paraCek', 'durumYaz' metotlarının hepsi 'static'tir.

- Sebep: Ortada bir nesne (Object) YOKTUR. 

  'main' metodu statik olduğu için, nesne üretmeden doğrudan çağrılacak metotların 

  ve dizilerin de 'static' olması mecburidir.

- Bu durum C dilindeki global fonksiyonlar ve global değişkenler düzeninin 

  birebir kopyasıdır.





================================================================================

6. VİZE VE FİNAL SINAVLARI İÇİN ÖZET SORULAR

--------------------------------------------------------------------------------

Soru: YapisalYaklasim.java sınıfındaki temel eksiklik / güvenlik açığı nedir?

Cevap: Kapsülleme (Encapsulation) ilkesinin olmamasıdır. Veri (bakiyeler dizisi) 

       ile iş mantığı (paraCek kontrolü) birbirinden ayrıdır. Veri dış dünyaya 

       açık olduğu için kuralları baypas ederek doğrudan tutarsız değerler 

       (-9999.0) atanabilir.



Soru: 'ali' değişkeni bellekte bir nesne midir?

Cevap: Hayır, 'ali' sadece bir tamsayıdır (int indis). Heap'te bir hesap nesnesini 

       değil, Stack'te 0 değerini tutar.

================================================================================
*/