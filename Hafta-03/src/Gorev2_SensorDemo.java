public class Gorev2_SensorDemo {
    public static void main(String[] args) {
        Sensor s = new Sensor("TRB-01", "C");

        System.out.println("Baslangic : " + s);
        System.out.println();

        System.out.println("--- Gecerli olcumler ---");
        System.out.println("  olc(21.5)  -> " + s.olc(21.5));
        System.out.println("  olc(-12.0) -> " + s.olc(-12.0));
        System.out.println("Durum: " + s);
        System.out.println();

        System.out.println("--- Aralik disi olcumler ---");
        System.out.println("  olc(999.0) -> " + s.olc(999.0));
        System.out.println("  olc(-80.0) -> " + s.olc(-80.0));
    }
}

/*
================================================================================
NESNE YÖNELİMLİ PROGRAMLAMA - HAFTA 03 / LABORATUVAR
GÖREV 2: KAPSÜLLEME İHLALİNİ ONARMA, KONTROL SORULARI VE VİZE HAZIRLIK NOTU
Dosya: Sensor.java / Gorev2_SensorDemo.java
================================================================================

--------------------------------------------------------------------------------
1. GÖREVİN ÖZÜ VE DÜZEY 3 KAPSÜLLEME DÖNÜŞÜMÜ
--------------------------------------------------------------------------------
* Eski (Bozuk) Tasarımın Açıkları:
  - Bütün alanlar 'public' tanımlanmıştı; dışarıdan 's.deger = 9999.0;' gibi 
    fiziksel olarak anlamsız sıcaklık değerleri atanabiliyordu[cite: 7].
  - Hiç ölçüm yapılmadan 's.olcumSayisi = 100;' yazılarak sayaçlar hileyle 
    doldurulabiliyordu[cite: 7].
  - 's.kod = "";' denilerek sensörün fabrika kimliği silinebiliyordu[cite: 7].

* Yeni (Düzey 3 Kapsüllenmiş) Mimari:
  - Bütün alanlar 'private' yapılarak dış dünyaya kapatıldı[cite: 7].
  - Sınıf kendi verisini ve bu veriyi koruyan iş kurallarını (business rules) 
    bizzat kendi bünyesine aldı[cite: 7, 8].


--------------------------------------------------------------------------------
2. KODDAKİ KRİTİK MÜHENDİSLİK DETAYLARI VE PÜF NOKTALARI
--------------------------------------------------------------------------------

A) SAYAÇLARDA 'static' TUZAĞI (En Büyük Tasarım Hatası):
   - Eğer 'private static int olcumSayisi;' yazılsaydı, bu sayaç tek tek üretilen 
     sensörlere değil, doğrudan 'Sensor' sınıfının ortak havuzuna ait olurdu.
   - Fabrikadaki 'TRB-01' sensörü 5 ölçüm yaptığında, yeni takılan 'IST-02' 
     sensörünün de sayacı 5 görünürdü.
   - Her sensörün kendi kabul ve red sayısını bağımsız tutabilmesi için alanlar 
     kesinlikle 'static' DEĞİL, nesne değişkeni (instance variable) olmalıdır[cite: 7].

B) 'this.deger = deger' (YAZMA) vs. 'return deger' (OKUMA):
   - 'olc(double deger)' metodunda:
     'this.deger = deger;' satırı bir ATAMA (assignment / yazma) işlemidir[cite: 9].
     Gelen yeni geçerli ölçüm RAM'deki 'deger' kutusuna yazılır[cite: 7, 9].
   - 'getDeger()' metodunda:
     'return deger;' (veya 'return this.deger;') satırı bir OKUMA işlemidir[cite: 9].
     Kutuya dokunulmaz, sadece içindeki sayının bir kopyası dışarıya döndürülür[cite: 9].
   - 'deger' alanı 'final' olmadığı için gün içinde binlerce kez yeni değer atanabilir; 
     asla derleme hatası vermez.

C) 'final' İLE DEĞİŞTİRİLEMEZLİK (IMMUTABILITY):
   - 'kod' ve 'birim' alanları nesne doğarken (constructor içinde) bir kez atanır 
     ve 'final' kelimesiyle kilitlenir[cite: 7].
   - Sensörün modeli veya birimi (örn: "TRB-01", "C") sonradan değiştirilemez[cite: 7].

D) NEDEN 'setDeger()' YAZILMADI? (Mühendislik Refleksi):
   - Her 'private' alana ezbere 'setter' yazmak profesyonel kodlamada yanlıştır[cite: 7, 8].
   - Ölçüt: "Bu alanı dışarıdan keyfi bir değere ayarlamak anlamlı mıdır?"[cite: 8]
   - Bir sıcaklık sensörünün değeri elle ayarlanmaz; sensör fiziksel ortamı ölçer[cite: 8].
   - Bu yüzden metot adı 'setDeger' değil, iş kuralını işleten 'olc(deger)' olmalıdır[cite: 7, 8].

E) 'toString()' İÇİNDE DİNAMİK FORMATLAMA:
   - 'toString()' içinde sensör kodunu ("TRB-01") veya değerini ("-12.0") elle 
     yazmak (hardcode) büyük bir hatadır[cite: 7].
   - Metot, sınıfın kendi 'kod', 'deger', 'birim', 'olcumSayisi' ve 'redSayisi' 
     değişkenlerini String.format ile dinamik olarak birleştirmelidir[cite: 7, 8].


--------------------------------------------------------------------------------
3. LABORATUVAR FÖYÜ KONTROL SORULARI VE RESMİ YANITLARI
--------------------------------------------------------------------------------

[KONTROL SORUSU 1]
Soru : 'kod' alanını 'private' yapmak yetmez, ayrıca 'final' de yaptık. 
       'final' olmasaydı hangi kapı açık kalırdı?[cite: 8]

Cevap: Sadece 'private' yapılsaydı, alan yalnızca sınıf dışına kapatılmış olurdu[cite: 7, 8].
       Fakat sınıfın kendi içine ileride yazılabilecek hatalı bir metot (örneğin 
       yanlışlıkla eklenecek bir 'setKod()' veya 'sifirla()' metodu) sensörün 
       kimliğini çalışma zamanında değiştirebilirdi[cite: 7, 8]. 
       'final' ekleyerek bu alanı 'immutable' (değiştirilemez) yaptık; nesne 
       doğduktan sonra kimliğinin bir daha asla değiştirilemeyeceğini derleyici 
       seviyesinde garanti altına aldık[cite: 7, 8].

--------------------------------------------------------------------------------

[KONTROL SORUSU 2]
Soru : 'olc()' metodunun 'boolean' döndürmesi, 'void' döndürmesine göre 
       çağıran tarafa ne kazandırır?[cite: 8]

Cevap: İşlemin başarılı olup olmadığının geri bildirimini kazandırır[cite: 7, 8].
       Çağıran kod ('main' veya ana kontrol yazılımı), gönderdiği ölçümün kabul 
       edilip edilmediğini dönen 'true' veya 'false' değerine bakarak anında öğrenir[cite: 7, 8].
       Bu sayede ölçüm geçersizse alarm tetikleyebilir, log tutabilir veya ekrana 
       hata basabilir ('if (!s.olc(deger)) alarmCal();')[cite: 7, 8]. 
       Eğer metot 'void' olsaydı, çağıran taraf ölçümün durumundan habersiz kalırdı[cite: 8].

--------------------------------------------------------------------------------

[KONTROL SORUSU 3]
Soru : 'EN_DUSUK' ve 'EN_YUKSEK' sabitlerini 'public' yapmak kapsüllemeyi 
       ihlal eder mi? Gerekçelendirin.[cite: 8]

Cevap: HAYIR, KESİNLİKLE İHLAL ETMEZ[cite: 7, 8].
       Çünkü bu iki alan 'final' anahtar sözcüğü ile tanımlanmıştır[cite: 7, 8].
       Dışarıdan hiç kimse 'Sensor.EN_YUKSEK = 500.0;' diyerek bu sınırları 
       değiştiremez (derleyici hata verir)[cite: 7, 8].
       Değeri değiştirilemeyen sabitlerin (constants) 'public' yapılması ver
 */