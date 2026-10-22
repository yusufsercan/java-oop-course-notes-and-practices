public class CarDemo 
{
    public static void main(String[] args)
    {
        Car car1 = new Car("Gumus","Reanult Megane" , 1.6,4);
        Car car2 =new Car();
        Car car3 =new Car("Siyah","Mercedes");

        car1.showInfos();
        car2.showInfos();
        car3.showInfos();

        
    }
    
}

/*
--------------------------------------------------------------------------------

1.  "MADEM DIŞARI AÇACAĞIZ, NEDEN EN BAŞTA GİZLİYORUZ?"

--------------------------------------------------------------------------------

Her yazılımcı adayının haklı olarak sorduğu soru şudur:

"Değişkeni 'private' yapıp gizliyoruz, sonra altına gidip 'public get' ve

'public set' yazıyoruz. E madem herkes erişebilecek, neden doğrudan

'public int doors;' yapıp 10 satır fazladan kod yazmaktan kurtulmuyoruz?"



Cevap çok nettir:

Bir değişkeni 'public' yapmak, bir evin sokak kapısını menteşelerinden söküp

atmak demektir. İsteyen içeri girer, istediği eşyayı çalar ya da çöp döker.



Değişkeni 'private' yapıp önüne 'getter/setter' koymak ise kapıya ÇELİK KAPI ve

GÜVENLİK GÖREVLİSİ koymaktır:

- İçeriye girmek isteyen (Setter) kimliğini gösterir ve güvenlik kontrolünden geçer.

- İçeriden bilgi almak isteyen (Getter) sadece izin verilen pencereden bakar.

--------------------------------------------------------------------------------

2. SENARYO: GETTER / SETTER OLMADAN DOĞRUDAN 'PUBLIC' KULLANIRSAK NE OLUR?

--------------------------------------------------------------------------------

Diyelim ki Car sınıfında alanları doğrudan public yaptık:



    public class Car {

        public String color;

        public double engine;

        public int doors;

    }
Bu durumda araba sınıfını kullanan herhangi bir yazılımcı (veya test dosyası)

şunu rahatlıkla yapabilir:



    Car c = new Car();

    c.doors = -5;         // Eksi 5 kapılı araba!

    c.engine = -999.0;    // Negatif motor hacmi!

    c.color = "";         // Boşluktan oluşan renk!



Java derleyicisi buna ASLA itiraz etmez. Çünkü -5 de geçerli bir 'int' sayısıdır,

-999.0 da geçerli bir 'double' sayısıdır.

Sonuç: Bellekte mantıksal olarak sakat, çöp bir araba nesnesi gezinir.



İşte bu felaketi engellemenin tek yolu KAPSÜLLEMEDİR (Encapsulation).

--------------------------------------------------------------------------------

3. SETTER NEDİR? (GÜVENLİK FİLTRESİ VE VERİ BÜTÜNLÜĞÜ)

--------------------------------------------------------------------------------

Setter metodu sadece "değişkene değer yazan bir kod" değildir.

Setter bir GÜVENLİK SÜZGECİDİR (Validation Gate).

Senin kodundaki şu harika satırlara bak:



    public void setDoor(int doors) {

        if (doors == 2 || doors == 4) {

            this.doors = doors;

        } else {

            System.out.println("Kapi sayisi 2 veya 4 olabilir yalnizca.");

        }

    }


Burada dışarıdan gelen veriye hemen güvenmiyorsun. Nesne kendini savunuyor:

"Bana 8 kapı veya -5 kapı veremezsin; ben kuralları olan bir varlığım."



Eğer veri geçerliyse hafızaya yazılır, geçersizse reddedilir.

Buna yazılım mimarisinde VERİ BÜTÜNLÜĞÜ (Data Integrity) denir.

--------------------------------------------------------------------------------

4. SETTER YAZMAZSAK NE OLUR? (SALT-OKUNUR / IMMUTABLE ALANLAR)

--------------------------------------------------------------------------------

Bütün değişkenlerin setter'ı olmak ZORUNDA DEĞİLDİR!

Hatta iyi bir yazılımcı gerekmedikçe setter yazmaz.


Örnek Gerçek Hayat Senaryosu:

Bir arabanın rengi sonradan sanayide değiştirilebilir -> setColor OLMALIDIR.

Ama bir arabanın ŞASİ NUMARASI (veya bir personelin T.C. Kimlik / Sicil Numarası)

üretildikten sonra bir daha ASLA değiştirilemez!



    public class Car {

        private final String sasiNo;



        public Car(String sasiNo) {

            this.sasiNo = sasiNo;

        }



        // SADECE GETTER VAR, SETTER ASLA YAZILMAZ:

        public String getSasiNo() {

            return this.sasiNo;

        }

    }



Bunu yaptığın an ne elde ettin?

Dış dünyadaki herkes şasi numarasını okuyabilir (getSasiNo), ama dünyadaki

hiçbir yazılımcı bu numarayı sonradan değiştiremez (Salt-Okunur / Read-Only).

Eğer sasiNo 'public' olsaydı bu kontrolü tamamen kaybederdin.

--------------------------------------------------------------------------------

5. "showInfos() VARKEN GETTER'A NE GEREK VAR?" (EKRANA YAZMAK VS DEĞERİ ALMAK)

--------------------------------------------------------------------------------

Kafanı en çok karıştıran nokta şuydu:

"Hocam showInfos() metodu zaten bilgileri ekrana basıyor, o zaman getEngine()

veya getColor() ne işe yarayacak?"



showInfos() metodunun tipi nedir? 'void'.

'void' ne demektir? "Ben işimi yapar bitiririm, sana geriye hiçbir şey teslim etmem."



Şimdi şu senaryoyu düşün:

Vergi Dairesi için bir program yazıyorsun. Motor hacmine göre Motorlu Taşıtlar

Vergisi (MTV) hesaplayacaksın:



    Car car1 = new Car("Gumus", "Megane", 1.6, 4);



    // SORU: Arabanın motor hacmiyle matematiksel çarpma işlemi yapman lazım.

    // car1.showInfos() ile bunu yapabilir misin?

    // ASLA YAPAMAZSIN! Çünkü showInfos() ekrana siyah konsol yazısı basar.

    // Konsoldaki yazıyı alıp 4500 TL ile çarpamazsın!



Ama elinde bir GETTER varsa:



    double motor = car1.getEngine();       // Değeri aldın, cebine koydun!

    double vergi = motor * 4500.0;         // Matematiksel işlem yaptın.



    if (car1.getColor().equals("Kirmizi")) {

        System.out.println("Kırmızı araçlara özel indirim!");

    }

ÖZET:

- showInfos(): Sadece insan gözü terminalde görsün diye ekrana yazı basar.

- getter (): Programın içindeki diğer kodlar o veriyi alıp HESAPLAMALARDA,

            ŞARTLARDA (if) veya VERİ TABANINA KAYDETMEKTE kullansın diye

            veriyi elden teslim eder (return).

--------------------------------------------------------------------------------

6. "YAPICI (CONSTRUCTOR) VARKEN SETTER'A NE GEREK VAR?"

--------------------------------------------------------------------------------

Dedin ki:

"E zaten new Car("Gumus", "Megane", 1.6, 4) diyerek bütün değerleri verip

üretiyoruz. O zaman setter niye var?"



Buradaki ayrım: DOĞUM vs YAŞAM DÖNGÜSÜ'dür.



- YAPICI (Constructor):

  Nesnenin DOĞUMHANESİDİR. Hayatta yalnızca bir kez çalışır.

  Araba banttan inerken parçaları birleştirir.



- SETTER:

  Nesne DOĞDUKTAN SONRA hayatına devam ederken bir özelliğinin güncellenmesidir.

Senaryo:

Araba üretildi, 3 yıl bindi. Rengini maviye boyatmak istiyor.

Eğer setter kullanmazsan ne yapman gerekir?



    // YANLIŞ VE SAÇMA ÇÖZÜM:

    car1 = new Car("Mavi", "Megane", 1.6, 4);


Bunu yaptığın an ne oldu biliyor musun?

Sen eski arabayı boyatmadın! Eski arabayı hurdalığa atıp yok ettin, gidip bayiden

sıfır kilometre YENİ BİR ARABA satın aldın! Arabanın kilometresi, eski hasar kaydı,

plakası her şeyi sıfırlandı.


Halbuki bizim istediğimiz aynı arabayı korumak, sadece rengini tazelemek:

    // DOĞRU ÇÖZÜM:

    car1.setColor("Mavi"); // Nesne aynı nesne, sadece rengi güncellendi!
------------------------------------------------------------------------------

7. 'this' ANAHTAR KELİMESİ VE SINIRLAR: NEREDE ÇALIŞIR, NEREDE ÇALIŞMAZ?

--------------------------------------------------------------------------------
Kodunda dedin ki: "showInfos yazarken 'this.color' kullandık, niye get kullanmadık?"

Çünkü SINIFIN İÇİ ile SINIFIN DIŞI farklı dünyalardır:


1. SINIFIN İÇİ (Car.java dosyası):

   Burası arabanın kaputunun altıdır.

   showInfos(), setColor(), getDoors() gibi metotlar arabanın İÇ ORGANLARIDIR.

   Araba kendi iç organlarına doğrudan dokunabilir. Bu yüzden sınıfın içinde

   'this.color', 'this.doors' yazmak tamamen doğaldır ve serbesttir.

2. SINIFIN DIŞI (CarDemo.java dosyası - main metodu):

   Burası arabanın dışıdır, arabanın sürücü koltuğudur.

   Burada 'this' diyemezsin, çünkü 'this' "benim şu anki nesnem" demektir ve

   CarDemo bir Car değildir!

   Ayrıca değişkenler 'private' olduğu için:

       car1.color = "Kirmizi"; // DERLENMEZ! "color has private access in Car"

   Dışarıdaki adam kaputun altına doğrudan tornavida sokamaz. Mecburen kapıdaki

   güvenliği çağırmak zorundadır:


       car1.setColor("Kirmizi"); // ÇALIŞIR!

-----------------------------------------------------------------------------

8. USTADAN KRİTİK BİR UYARI: YAPICIDA SETTER KULLANMA DİSİPLİNİ

--------------------------------------------------------------------------------

Senin yazdığın 4 parametreli yapıcıya dikkat et:

    public Car(String color, String model, double engine, int doors) {

        this.color = color;

        this.model = model;

        this.engine = engine;

        this.doors = doors; // <--- TEHLİKE BURADA!

    }

Eğer biri CarDemo içinde şöyle bir kod yazarsa:



    Car hataliAraba = new Car("Gri", "Megane", 1.6, -99);


Sen 'setDoor' içine dünyanın en iyi kontrolünü yazsan bile, yapıcıda doğrudan

'this.doors = doors;' dediğin için o kontrol ÇALIŞMAZ!

Ve bellekte -99 kapılı bir araba doğar!


Usta Çözümü:

Yapıcıların içinde de doğrudan atamak yerine güvenlik kontrollerini işletmek

ya da setter'ı çağırmaktır:



    public Car(String color, String model, double engine, int doors) {

        this.color = color;

        this.model = model;

        this.engine = engine;

        this.setDoor(doors); // Kontrol filtresinden geçirerek ata!

    }

--------------------------------------------------------------------------------

9. ALTIN ÖZET TABLOSU

--------------------------------------------------------------------------------

Kavram             Ne İşe Yarar?                       Örnek Kullanım

-----------------  ----------------------------------  -------------------------

private            Değişkeni dış dünyadan kilitler     private int doors;

Constructor        Nesne ilk doğarken RAM'de kurar     new Car("Gumus", 1.6);

this.alan          Sınıfın İÇİNDE kendi alanını seçer  this.color = color;

getter             Veriyi BAŞKA kodlara hesap için sunar double m = car.getEngine();

setter             Veriyi KONTROL EDEREK günceller     car.setDoor(4);

showInfos()        Sadece EKRANA konsol yazısı döker   car.showInfos();

================================================================================
*/