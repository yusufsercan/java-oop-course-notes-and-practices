/*
 * Nesne Yonelimli Programlama - Hafta 03
 * Ornek 8: Ayni sozlesme, uc farkli gerceklemesi
 *
 * Kasa sinifi hicbir odeme turunu ADIYLA bilmez; yalnizca Odeme sozlesmesini
 * bilir. Yeni bir odeme yontemi eklendiginde Kasa DEGISMEZ.
 */
public class OdemeDemo {

    public static void main(String[] args) {
        Odeme[] yontemler = {
            new KrediKarti("5312 **** **** 4471", 1200.0),
            new Havale("TR33 0001 2222", 600.0),
            new Kapora(250.0)
        };

        double[] sepet = {350.0, 500.0, 300.0};

        System.out.println("--- Kasa: tek dongu, uc farkli odeme yolu ---");
        for (int i = 0; i < yontemler.length; i++) {
            Kasa.tahsilEt(yontemler[i], sepet[i]);
        }

        System.out.println();
        System.out.println("--- Sozlesmeye uymayan tutar ---");
        Kasa.tahsilEt(yontemler[0], -50.0);

        System.out.println();
        System.out.println("Kasa sinifi KrediKarti, Havale, Kapora adlarini hic gecirmez.");
    }
}

/** Odeme sozlesmesini bilen; gerceklemeleri bilmeyen cagiran taraf. */
class Kasa {
    static void tahsilEt(Odeme yontem, double tutar) {
        boolean sonuc = yontem.ode(tutar);
        System.out.printf("  %-14s %8.2f TL -> %s%n",
                          yontem.saglayici(), tutar, sonuc ? "ONAY" : "RED");
    }
}

class KrediKarti implements Odeme {
    private final String numara;
    private double limit;

    KrediKarti(String numara, double limit) {
        this.numara = numara;
        this.limit = limit;
    }

    @Override
    public boolean ode(double tutar) {
        if (tutar <= 0 || tutar > limit) {
            return false;
        }
        limit -= tutar;
        return true;
    }

    @Override
    public String saglayici() {
        return "Kredi karti";
    }
}

class Havale implements Odeme {
    private final String iban;
    private double bakiye;

    Havale(String iban, double bakiye) {
        this.iban = iban;
        this.bakiye = bakiye;
    }

    @Override
    public boolean ode(double tutar) {
        if (tutar <= 0 || tutar > bakiye) {
            return false;
        }
        bakiye -= tutar;
        return true;
    }

    @Override
    public String saglayici() {
        return "Havale";
    }
}

class Kapora implements Odeme {
    private double kalan;

    Kapora(double kalan) {
        this.kalan = kalan;
    }

    @Override
    public boolean ode(double tutar) {
        if (tutar <= 0 || tutar > kalan) {
            return false;
        }
        kalan -= tutar;
        return true;
    }

    @Override
    public String saglayici() {
        return "Kapora";
    }
}



/*
--------------------------------------------------------------------------------

1. KODUN BÜYÜK FOTOĞRAFI: KİM, HANGİ ROLÜ OYNUYOR?

--------------------------------------------------------------------------------

Sistemde 4 temel aktör vardır:



1) SÖZLEŞME / ŞARTNAME (interface Odeme):

   - Sistemdeki tüm ödeme araçlarının sağlaması gereken ortak asgari standarttır.

   - Sadece iki imza içerir: 

       boolean ode(double tutar);

       String saglayici();

   - İçinde gövde yoktur, durum (alan) tutmaz, yapıcısı yoktur.



2) SÖZLEŞMEYİ İMZALAYANLAR (implements Odeme):

   - KrediKarti : Kendi 'numara' ve 'limit' alanlarını tutar.

   - Havale     : Kendi 'iban' ve 'bakiye' alanlarını tutar.

   - Kapora     : Kendi 'kalan' bakiye alanını tutar.

   Üçünün de iç veri yapısı bambaşkadır; ancak üçü de dışarıya aynı 'ode' ve 

   'saglayici' kapılarını açar.



3) SÖZLEŞMEYE GÜVENEN TARAF (class Kasa):

   - 'static void tahsilEt(Odeme yontem, double tutar)'

   - Kasa sınıfı alt sınıfların (KrediKarti, Havale, Kapora) hiçbirini tanımaz!

   - Kasa sadece 'Odeme' tipini bilir ve doğrudan 'yontem.ode(tutar)' der.



4) ORKESTRA ŞEFİ (class OdemeDemo -> main):

   - Nesneleri bellekte 'new' ile üretir.

   - Hepsini 'Odeme[]' dizisinde toplar (Çok Biçimlilik).

   - Tek bir for döngüsüyle Kasa'ya teslim eder.





================================================================================

2. VİZENİN EN KRİTİK 6 MİMARİ VE SÖZDİZİMİ AYRINTISI

================================================================================



--------------------------------------------------------------------------------

AYRINTI 1: TEK DOSYADA NEDEN BİRDEN FAZLA SINIF VAR? (Java Sözdizimi Kuralı)

--------------------------------------------------------------------------------

Dosya adı: 'OdemeDemo.java'

Dosyanın içindeki sınıflar:

- public class OdemeDemo  -> Dosyada SADECE BİR TANE 'public' sınıf olabilir!

- class Kasa              -> Başında public YOK (package-private).

- class KrediKarti        -> Başında public YOK (package-private).

- class Havale            -> Başında public YOK (package-private).

- class Kapora            -> Başında public YOK (package-private).



VİZE SORU TUZAĞI:

"Bu dosyadaki 'class KrediKarti' satırının başına 'public' yazarsak ne olur?"

CEVAP:

KOD DERLENMEZ! Java kuralı gereği public tanımlanan her sınıf kendi adını taşıyan 

bağımsız bir dosyada (KrediKarti.java) yer almak zorundadır. Hoca derste hepsini 

tek ekranda göstermek için package-private (varsayılan erişimli) yapmıştır.





--------------------------------------------------------------------------------

AYRINTI 2: ÇOK BİÇİMLİ DİZİ (Polymorphic Array)

--------------------------------------------------------------------------------

Satır:

    Odeme[] yontemler = {

        new KrediKarti("5312 ****", 1200.0),

        new Havale("TR33 0001", 600.0),

        new Kapora(250.0)

    };



Soru: "KrediKarti, Havale ve Kapora tamamen farklı sınıflardır. Nasıl aynı 

       diziye konulabiliyorlar?"

Cevap:

Çünkü üç sınıf da 'implements Odeme' diyerek Odeme sözleşmesini imzalamıştır.

Java tip sistemine göre bir sınıf uyguladığı arayüzün tipini taşır (IS-A / CAN-DO).

- Dizinin Bildirim Türü (Statik Türü) : Odeme

- Elemanların Gerçek Türü (Dinamik Türü): KrediKarti, Havale, Kapora





--------------------------------------------------------------------------------

AYRINTI 3: DİNAMİK BAĞLAMA (Dynamic Dispatch) - "Switch Olmadan Farklı Davranış"

--------------------------------------------------------------------------------

Döngü satırı:

    for (int i = 0; i < yontemler.length; i++) {

        Kasa.tahsilEt(yontemler[i], sepet[i]);

    }



Kasa'nın içi:

    boolean sonuc = yontem.ode(tutar);



VİZE SORUSU:

"yontem.ode(tutar) tek bir satırdır. Program switch/case kullanmadan hangi nesnenin 

ode() metodunu çalıştıracağını nasıl anlar?"

CEVAP:

Derleyici derleme anında sadece 'Odeme' arayüzünde 'ode(double)' imzasının var 

olup olmadığını denetler (tip güvenliği).

Çalışma zamanında (runtime) JVM, 'yontem' referansının Heap bellekte işaret ettiği 

gerçek nesnenin sınıfına bakar:

- i = 0 iken gerçek nesne KrediKarti -> KrediKarti.ode() çalışır.

- i = 1 iken gerçek nesne Havale     -> Havale.ode() çalışır.

- i = 2 iken gerçek nesne Kapora     -> Kapora.ode() çalışır.

Bu olaya DİNAMİK BAĞLAMA (Dynamic Binding) denir.





--------------------------------------------------------------------------------

AYRINTI 4: KASA SINIFININ DÜNYADAN HABERSİZ OLMASI (Decoupling / Gevşek Bağlılık)

--------------------------------------------------------------------------------

Kasa sınıfının koduna bak:

    class Kasa {

        static void tahsilEt(Odeme yontem, double tutar) { ... }

    }



Buradaki mühendislik dehası şudur:

Kasa sınıfında 'KrediKarti', 'Havale', 'Kapora' kelimeleri ASLA GEÇMEZ!



VİZE SENARYO SORUSU:

"Sisteme yarın dördüncü bir ödeme türü olarak 'KriptoPara' eklense Kasa sınıfında 

nasıl bir değişiklik yapılması gerekir?"

CEVAP:

KASA SINIFINDA HİÇBİR DEĞİŞİKLİK YAPILMAZ!

Sadece yeni bir 'KriptoPara implements Odeme' sınıfı yazılır. Kasa sınıfı doğrudan 

bu yeni türle çalışmaya devam eder. 

Buna yazılım mimarisinde "Open/Closed Principle" (Gelişime açık, değişime kapalı) denir.





--------------------------------------------------------------------------------

AYRINTI 5: KAPSÜLLEME VE -50 TL TUZAĞI

--------------------------------------------------------------------------------

Main'deki şu satır:

    Kasa.tahsilEt(yontemler[0], -50.0); // Kredi kartına -50 TL gönderiliyor



Sonuç: RED

NEDEN?

Kredi kartının limiti 1200 TL'dir; yani para yetersizliğinden değil, 

KrediKarti.java içindeki şu iş kuralı nedeniyle reddedilir:

    if (tutar <= 0 || tutar > limit) { return false; }



Kritik Tasarım Dersi:

Kasa sınıfı tutarın pozitif olup olmadığını denetlemek zorunda kalmaz. 

Her nesne kendi veri bütünlüğünü kendi metodu içinde korur (Kapsülleme).





--------------------------------------------------------------------------------

AYRINTI 6: VS CODE'DA ÇIKAN SARI ÇİZGİLER (Warning: The field is not used)

--------------------------------------------------------------------------------

'numara' ve 'iban' alanlarının altında sarı çizgi çıkmasının sebebi:

Bu alanlar 'private final String' olarak yapıcıda doldurulmuş, ancak sınıfın 

içindeki metotlarda (ode veya saglayici) hiç okunmamıştır.

- Bir hata (error) DEĞİLDİR; program sorunsuz derlenir ve çalışır.

- Gerçek hayatta bu alanlar dekont basarken veya log kaydederken kullanılır.





================================================================================

3. VİZE SINAVI İÇİN "HATA BULMA VE KOD OKUMA" SORULARI

================================================================================



SORU 1:

Aşağıdaki satır derlenir mi? Neden?

    Odeme o = new Odeme();

CEVAP:

DERLENMEZ! Odeme bir arayüzdür (interface). Arayüzler ve soyut sınıflar gövdesiz 

metotlar içerdiğinden eksik yapılardır; 'new' ile doğrudan örnekleri/nesneleri 

üretilemez.



SORU 2:

Aşağıdaki kod derlenir mi?

    Odeme o = new KrediKarti("1234", 1000.0);

    o.ode(200.0);

    System.out.println(o.numara);

CEVAP:

DERLENMEZ! İki sebeple:

1. 'numara' alanı private'tır, dışarıdan erişilemez.

2. 'o' referansının statik türü 'Odeme'dir. Odeme arayüzünde 'numara' diye bir alan 

   tanımlı değildir! Üst tür referansıyla alt türün özel alanlarına erişilemez.



SORU 3:

KrediKarti sınıfında '@Override public boolean ode(double tutar)' yerine 

yanlışlıkla '@Override public boolean Ode(double tutar)' yazarsak ne olur?

CEVAP:

DERLEME HATASI VERİR! Çünkü @Override notasyonu derleyiciye 

"Ben Odeme arayüzündeki metodu eziyorum" der. Arayüzde büyük 'O' ile başlayan 'Ode' 

olmadığı için derleyici anında yazım hatasını yakalar.





================================================================================

4. AKILDA KALACAK VİZE ÖZETİ

================================================================================

1. interface = Şartnamedir (Sadece metot imzaları vardır, gövde yoktur).

2. implements = Bir sınıfın o şartnameye uyacağını garanti etmesidir.

3. Kasa = Arayüze bağımlıdır, somut sınıfları bilmez (Decoupling).

4. Tek Döngü = Farklı sınıflar aynı arayüz dizisinde toplanıp tek for döngüsüyle 

   polimorfik olarak yürütülür.

5. Yeni Tür Ekleme = Var olan Kasa ve Odeme koduna dokunmadan sadece yeni bir 

   sınıf yazılarak sisteme entegre edilir.

================================================================================
*/