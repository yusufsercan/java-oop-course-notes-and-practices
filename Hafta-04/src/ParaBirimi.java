/*
 * Nesne Yonelimli Programlama - Hafta 04
 * Ornek 6: DEGISMEZ (immutable) sinif
 *
 * Degismezligin dort kurali bu sinifta uygulanmistir:
 *  1) Sinif final    -> davranis mirasla degistirilemez
 *  2) Alanlar private final
 *  3) Setter yok
 *  4) "Degistiren" metotlar nesneyi degil YENI BIR NESNE dondurur
 *
 * Sonuc: bir ParaBirimi nesnesi paylasilabilir, anahtar olarak kullanilabilir
 * ve hicbir kod onu arkadan degistiremez.
 */

public final class ParaBirimi 
{
     private final long kurus;
     private final  String kod;

     public ParaBirimi(long kurus, String kod) 
     {
        if (kod== null || kod.length() != 3)// kod 3 harfli olmalidir, null olamaz
            {
                 throw new IllegalArgumentException("Kod 3 harfli olmalidir");
            } 
            this.kurus = kurus;
            this.kod = kod.toUpperCase();// kodu buyuk harfe ceviriyoruz, kucuk harf kabul etmiyoruz toUpperCase() metodu, String sınıfının bir metodudur ve bir String nesnesinin tüm karakterlerini büyük harfe dönüştürür. Bu sayede, ParaBirimi nesnesi oluşturulurken verilen kodun her zaman büyük harflerle saklanmasını sağlar. Örneğin, "usd" veya "Usd" gibi küçük harflerle girilen bir kod, "USD" olarak saklanır. Bu, para birimi kodlarının standart bir formatta tutulmasını ve karşılaştırmaların doğru yapılmasını sağlar.

     }
    
     public static ParaBirimi lira (double tutar)// bu bir static metot, yani sınıfın kendisine ait ve nesne oluşturmadan çağrılabilir. Bu metot, double türünde bir tutar alır ve bu tutarı kuruş cinsine çevirerek yeni bir ParaBirimi nesnesi oluşturur. Metot, "TRY" kodu ile birlikte ParaBirimi nesnesini döndürür. Örneğin, lira(12.34) çağrısı, 1234 kuruş ve "TRY" kodu ile bir ParaBirimi nesnesi oluşturur.
     // eğer static olmasaydı, bu metodu çağırmak için önce bir ParaBirimi nesnesi oluşturmak gerekirdi. Ancak static olduğu için, doğrudan sınıf adıyla çağrılabilir: ParaBirimi.lira(12.34).
     {
        return new ParaBirimi(Math.round(tutar*100.0), "TRY");// Math.round(tutar*100.0) ifadesi, verilen tutarı kuruş cinsine çevirir ve en yakın tam sayıya yuvarlar. Örneğin, 12.345 TL için Math.round(12.345*100.0) = 1235 olur. Bu sayede, ParaBirimi nesnesi oluşturulurken kuruş cinsinden doğru bir değer elde edilir.
     }
     
     public long getKurus() 
     {
        return kurus;
     }
     public String getKod() 
     {
        return kod;
     }
     public double getTutar() // getTutar() metodu, ParaBirimi nesnesinin kuruş değerini TL cinsine çevirir ve double türünde döndürür. Bu sayede, kullanıcılar ParaBirimi nesnesinin değerini daha anlaşılır bir formatta görebilirler. Örneğin, 1234 kuruş için getTutar() metodu 12.34 TL olarak döner.
     {
        return kurus / 100.0;
     }
     
    
     public ParaBirimi topla(ParaBirimi diger)// yeni nesne donduruyor, degistirmiyor yani degismez (immutable) sinif kurallarina uygun o yüzden ParaBirimi sinifinda topla metodu, mevcut nesneyi değiştirmek yerine yeni bir ParaBirimi nesnesi döndürür. Bu, degismez (immutable) sınıf kurallarına uygun bir davranıştır. Örneğin, eğer elimizde 100 kuruş ve "TRY" koduna sahip bir ParaBirimi nesnesi varsa ve bu nesneye 200 kuruş ve "TRY" koduna sahip başka bir ParaBirimi nesnesini toplarsak, mevcut nesne değişmez ve yeni bir ParaBirimi nesnesi oluşturulur. Bu sayede, orijinal nesneler korunur ve yan etkilerden kaçınılır.
     {
        birimKontrol(diger);// bu metot, iki ParaBirimi nesnesinin aynı para birimi koduna sahip olup olmadığını kontrol eder. Eğer kodlar farklıysa, IllegalArgumentException fırlatır. Bu sayede, farklı para birimlerini toplama işlemi engellenir ve hata oluşur.
        return new ParaBirimi(this.kurus + diger.kurus, this.kod);// bu ifade, iki ParaBirimi nesnesinin kuruş değerlerini toplar ve aynı kod ile yeni bir ParaBirimi nesnesi oluşturur. Örneğin, 100 kuruş ve 200 kuruş olan iki ParaBirimi nesnesi için, topla metodu 300 kuruş ve aynı kod ile yeni bir ParaBirimi nesnesi döndürür.
     }

     public ParaBirimi carp(int katsayi)
     {
        return new ParaBirimi(this.kurus * katsayi, this.kod);// bu ifade, mevcut ParaBirimi nesnesinin kuruş değerini verilen katsayı ile çarpar ve aynı kod ile yeni bir ParaBirimi nesnesi oluşturur. Örneğin, 100 kuruş olan bir ParaBirimi nesnesi için, carp(3) metodu 300 kuruş ve aynı kod ile yeni bir ParaBirimi nesnesi döndürür.
     }

     public ParaBirimi cikar(ParaBirimi diger)
     {
        birimKontrol(diger);
        return new ParaBirimi(this.kurus - diger.kurus, this.kod);
     }

     private void birimKontrol(ParaBirimi diger)// bu metot, iki ParaBirimi nesnesinin aynı para birimi koduna sahip olup olmadığını kontrol eder. Eğer kodlar farklıysa, IllegalArgumentException fırlatır. Bu sayede, farklı para birimlerini toplama veya çıkarma işlemi engellenir ve hata oluşur.
     {
        if (!this.kod.equals(diger.kod)) // this.kod.equals(diger.kod) ifadesi, mevcut ParaBirimi nesnesinin kodu ile diğer ParaBirimi nesnesinin kodunu karşılaştırır. Eğer kodlar eşit değilse, yani farklı para birimleri ise, if bloğu çalışır ve IllegalArgumentException fırlatılır. Bu sayede, farklı para birimlerini toplama veya çıkarma işlemi engellenir ve hata oluşur.
        // buradaki equals() metodu, String sınıfının bir metodudur ve iki String nesnesinin içeriklerini karşılaştırır. Eğer içerikler aynı ise true, farklı ise false döner. Bu sayede, para birimi kodlarının eşit olup olmadığını doğru bir şekilde kontrol edebiliriz.
        {
            throw new IllegalArgumentException("Farkli para birimleri toplanamaz: " + this.kod + " ve " + diger.kod);
        }
     }
     
      @Override
    public String toString() {
        return String.format("%.2f %s", getTutar(), kod);
    }



}


/*
 * ============================================================
 * HAFTA 04 - PARABIRIMI.JAVA
 * KONU: FINAL, IMMUTABLE, NESNE VE REFERANS MANTIGI
 * ============================================================
 *
 * 1. KODUN AMACI
 * ------------------------------------------------------------
 *
 * ParaBirimi sinifi, parasal bir tutari ve para birimi
 * kodunu temsil etmek icin yazilmistir.
 *
 * Temel amac, IMMUTABLE (degismez) sinif mantigini
 * gostermektir.
 *
 * Bir ParaBirimi nesnesi olusturulduktan sonra icindeki
 * kurus ve kod degerleri degistirilemez.
 *
 * Toplama, cikarma ve carpma islemlerinde mevcut nesne
 * degistirilmez, yeni bir ParaBirimi nesnesi olusturulur.
 *
 *
 * 2. FINAL VE IMMUTABLE ARASINDAKI FARK
 * ------------------------------------------------------------
 *
 * public final class ParaBirimi
 *
 * final class:
 * - Bu siniftan extends ile miras alinmasini engeller.
 * - Immutable tasarimi korumaya yardimci olur.
 *
 * private final long kurus;
 * private final String kod;
 *
 * private:
 * - Alanlara sinif disindan dogrudan erisimi engeller.
 *
 * final:
 * - Alana ilk deger atandiktan sonra tekrar atama
 *   yapilmasini engeller.
 *
 * DIKKAT:
 * final ile immutable ayni sey DEGILDIR!
 *
 * final bir referansin yeniden atanmasini engeller.
 * Immutable ise NESNENIN durumunun degismesini engeller.
 *
 * Ornek:
 *
 * final int[] sayilar = {10, 20};
 * sayilar[0] = 99; // CALISIR
 *
 * final, dizinin icerigini degismez yapmaz.
 *
 * Immutable sinif icin:
 * - Sinif final olmali.
 * - Alanlar private final olmali.
 * - Setter bulunmamali.
 * - Islem metotlari yeni nesneler dondurmeli.
 * - Degistirilebilir nesne alanlari varsa disaridan
 *   degistirilmeleri engellenmelidir.
 *
 * ParaBirimi orneginde long ilkel turdur.
 * String ise zaten immutable oldugu icin bu iki alan
 * uzerinden degismez bir yapi kurulabilir.
 *
 *
 * 3. CONSTRUCTOR (YAPICI METOT)
 * ------------------------------------------------------------
 *
 * public ParaBirimi(long kurus, String kod)
 *
 * Nesne olusturulurken ilk degerleri belirler.
 *
 * if (kod == null || kod.length() != 3)
 *
 * - kod null olamaz.
 * - kod tam 3 karakter uzunlugunda olmalidir.
 * - || operatoru VEYA anlamina gelir.
 *
 * Burada Java, || operatorunun kisa devre davranisi
 * sayesinde kod null ise length() metodunu calistirmaz.
 *
 * Aksi halde null uzerinden length() cagirmak hata verir.
 *
 * throw new IllegalArgumentException(...)
 *
 * Gecersiz parametre verildiginde istisna firlatir.
 * Boylece gecersiz kodla nesne olusturulmasi engellenir.
 *
 * this.kurus = kurus;
 * this.kod = kod.toUpperCase();
 *
 * this.kurus -> Nesnenin alanini ifade eder.
 * kurus      -> Yapiciya gelen parametredir.
 *
 * toUpperCase() -> Metni buyuk harfe donusturur.
 *
 * Ornek: "usd" -> "USD"
 *
 * DIKKAT:
 * toUpperCase() kucuk harfi reddetmez, donusturur.
 * Ayrica length() == 3 kontrolu, karakterlerin
 * gercekten harf oldugunu tek basina garanti etmez.
 *
 *
 * 4. STATIC METOT - lira()
 * ------------------------------------------------------------
 *
 * public static ParaBirimi lira(double tutar)
 *
 * static oldugu icin bir nesne olusturmadan sinif
 * adi uzerinden cagrilabilir.
 *
 * ParaBirimi.lira(100);
 *
 * Bu metot TL degerini kurusa cevirerek
 * yeni bir ParaBirimi nesnesi dondurur.
 *
 * return new ParaBirimi(
 *     Math.round(tutar * 100.0), "TRY"
 * );
 *
 * Ornek:
 *
 * 100 TL -> 10000 kurus
 * 12.50 TL -> 1250 kurus
 *
 * Math.round() en yakin tam sayiya yuvarlar.
 *
 * Para tutarinin long kurus olarak saklanmasi,
 * double ile yapilan islemlerde gorulebilecek
 * bazi kayan nokta hassasiyeti sorunlarini azaltir.
 *
 * NOT:
 * double girdinin kendisi yaklasik temsil edilebilir.
 * Math.round ile kurusa cevirme bu ornekte kullanilan
 * yontemdir.
 *
 *
 * 5. GETTER METOTLARI
 * ------------------------------------------------------------
 *
 * getKurus() -> Kurus degerini dondurur.
 * getKod()   -> Para birimi kodunu dondurur.
 * getTutar() -> Kurusu 100.0'a bolerek tutari hesaplar.
 *
 * Ornek:
 *
 * kurus = 1250
 * getTutar() = 12.5
 *
 * 100.0 kullanilmasi onemlidir.
 * Boylece bolme islemi double turunde gerceklesir.
 *
 * Getter metotlari nesnenin durumunu degistirmez.
 *
 * Bu sinifta SETTER BULUNMAZ!
 *
 *
 * 6. TOPLA, CIKAR VE CARP METOTLARI
 * ------------------------------------------------------------
 *
 * public ParaBirimi topla(ParaBirimi diger)
 *
 * birimKontrol(diger);
 *
 * return new ParaBirimi(
 *     this.kurus + diger.kurus, this.kod
 * );
 *
 * Isleyis:
 *
 * 1. Iki nesnenin para birimi kodlari kontrol edilir.
 * 2. Uygunsa kurus degerleri toplanir.
 * 3. new ile YENI bir ParaBirimi nesnesi olusturulur.
 * 4. return ile bu yeni nesne geri dondurulur.
 *
 * cikar() -> Ayni mantikla cikarma yapar.
 * carp()  -> Kurus degerini katsayi ile carpar.
 *
 * Bu metotlar mevcut nesneleri DEGISTIRMEZ.
 *
 * ONEMLI:
 * return new ParaBirimi(...) yeni nesne olusturur.
 *
 *
 * 7. BIRIMKONTROL() VE EQUALS()
 * ------------------------------------------------------------
 *
 * private void birimKontrol(ParaBirimi diger)
 *
 * Bu metot toplama ve cikarma oncesinde para
 * birimlerinin uyumlu olup olmadigini denetler.
 *
 * if (!this.kod.equals(diger.kod))
 *
 * equals() -> String iceriklerini karsilastirir.
 *
 * ! -> Mantiksal sonucu tersine cevirir.
 *
 * Ornek:
 *
 * "TRY".equals("TRY")  -> true
 * "TRY".equals("USD")  -> false
 *
 * Basindaki ! nedeniyle farkli kodlarda kosul saglanir.
 *
 * Farkli para birimleri toplanmaya veya cikarilmaya
 * calisilirsa IllegalArgumentException firlatilir.
 *
 * NEDEN == YERINE equals()?
 *
 * == iki referansin ayni nesneyi gosterip
 * gostermedigini kontrol eder.
 *
 * equals() String iceriklerini karsilastirir.
 *
 * EK BILGI:
 * diger.kod private olsa bile ayni ParaBirimi sinifinin
 * icindeki metot, diger nesnenin private alanina erisebilir.
 *
 * private erisim NESNEYE degil SINIFA baglidir.
 *
 *
 * 8. TOSTRING() METODU
 * ------------------------------------------------------------
 *
 * @Override
 * public String toString()
 *
 * Nesnenin ekrana nasil yazdirilacagini belirler.
 *
 * String.format("%.2f %s", getTutar(), kod)
 *
 * %.2f -> Sayiyi iki ondalik basamakla bicimlendirir.
 * %s   -> String degerini yerlestirir.
 *
 * Ornek:
 *
 * 100.00 TRY
 *
 * Sistem dil ayarina gore ondalik ayirici virgul
 * olarak da gorunebilir (100,00 TRY).
 *
 * System.out.println(nesne) kullanildiginda
 * toString() metodu otomatik olarak cagrilir.
 *
 *
 * ============================================================
 * 9. PEKISTIRME - IMMUTABLE VE REFERANSLAR
 * ============================================================
 *
 * ORNEK 1: DONEN NESNEYI SAKLAMAZSAK
 *
 * ParaBirimi a = ParaBirimi.lira(100);
 * ParaBirimi b = ParaBirimi.lira(50);
 *
 * a.topla(b);
 * System.out.println(a);
 *
 * SONUC: 100 TL
 *
 * NEDEN?
 *
 * topla() yeni bir 150 TL'lik nesne olusturur.
 * Ancak donen nesneyi bir degiskene atamadik.
 *
 * a hala 100 TL'lik eski nesneyi gosterir.
 *
 * Sonucu saklamak istersek:
 *
 * ParaBirimi c = a.topla(b);
 *
 * c -> 150 TL
 * a -> 100 TL
 * b -> 50 TL
 *
 *
 * ORNEK 2: REFERANSI YENI NESNEYE ATAMAK
 *
 * ParaBirimi a = ParaBirimi.lira(200);
 * ParaBirimi b = ParaBirimi.lira(50);
 *
 * ParaBirimi c = a.topla(b);
 *
 * a = a.carp(2);
 *
 * SONUC:
 *
 * a -> 400 TL
 * b -> 50 TL
 * c -> 250 TL
 *
 * Burada a = a.carp(2) immutable kuralini BOZMAZ.
 *
 * carp(2), 400 TL'lik yeni bir nesne olusturur.
 * a referansi bu yeni nesneyi gostermeye baslar.
 *
 * Eski 200 TL'lik nesnenin ici degistirilmez.
 *
 * KURAL:
 * Referansi degistirmek ile nesnenin icerigini
 * degistirmek ayni sey DEGILDIR!
 *
 *
 * ORNEK 3: IKI REFERANS AYNI NESNEYI GOSTERIYOR
 *
 * ParaBirimi a = ParaBirimi.lira(100);
 * ParaBirimi b = a;
 *
 * a = a.topla(ParaBirimi.lira(50));
 *
 * SONUC:
 *
 * a -> 150 TL
 * b -> 100 TL
 *
 * NEDEN?
 *
 * b = a; yazildigi anda iki referans ayni nesneyi
 * gostermeye baslar.
 *
 * Ancak bu, aralarinda surekli guncellenen bir
 * baglanti olusturmaz.
 *
 * Daha sonra a baska bir nesneye atanirsa
 * b otomatik olarak onu takip ETMEZ.
 *
 * b eski 100 TL'lik nesneyi gostermeye devam eder.
 *
 *
 * ORNEK 4: b = c ATAMASI
 *
 * ParaBirimi a = ParaBirimi.lira(100);
 * ParaBirimi b = a;
 *
 * ParaBirimi c = a.topla(ParaBirimi.lira(50));
 *
 * b = c;
 *
 * SONUC:
 *
 * a -> 100 TL
 * b -> 150 TL
 * c -> 150 TL
 *
 * b ve c AYNI nesneyi gosterir.
 * a ise farkli bir nesneyi gosterir.
 *
 * b = c; satiri yeni nesne olusturmaz.
 * Yalnizca b referansini c'nin gosterdigi
 * nesneye yonlendirir.
 *
 *
 * ORNEK 5: FINAL REFERANS
 *
 * final ParaBirimi a = ParaBirimi.lira(100);
 *
 * ParaBirimi b = a;
 * ParaBirimi c = a.topla(ParaBirimi.lira(50));
 *
 * b = c; // CALISIR
 *
 * SONUC:
 *
 * a -> 100 TL
 * b -> 150 TL
 * c -> 150 TL
 *
 * NEDEN CALISIR?
 *
 * final olan referans a'dir.
 * b final olmadigi icin baska bir nesneye atanabilir.
 *
 * Ancak:
 *
 * a = c; // DERLEME HATASI!
 *
 * Cunku final referans yeniden atanamaz.
 *
 * Eger b de final olarak tanimlansaydi:
 *
 * final ParaBirimi b = a;
 * b = c; // DERLEME HATASI!
 *
 *
 * ============================================================
 * 10. SINAVDA DIKKAT EDILECEKLER
 * ============================================================
 *
 * - final class miras alinmasini engeller.
 *
 * - private final alanlar disaridan dogrudan erisilemez
 *   ve ilk atamadan sonra yeniden atanamaz.
 *
 * - final bir referansi dondurur, nesnenin icerigini
 *   tek basina degismez yapmaz.
 *
 * - Immutable siniflarda setter bulunmaz.
 *
 * - return new ifadesi yeni nesne olusturur.
 *
 * - Yeni nesne dondurulup saklanmazsa orijinal
 *   nesne degismeden kalir.
 *
 * - a = a.topla(b) immutable kuralini bozmaz.
 *   Ancak a final ise yeniden atama yapilamaz.
 *
 * - b = a; yeni bir nesne olusturmaz.
 *
 * - b = a; iki referansi o anda ayni nesneye yoneltir.
 *   Sonraki atamalar otomatik olarak birbirini takip etmez.
 *
 * - Referans turu, referans ve gercek nesne
 *   kavramlarini birbirine karistirma.
 *
 * - private alanlara ayni sinifin metotlari icinden,
 *   baska nesneler uzerinden de erisilebilir.
 *
 * - String icerigi karsilastirirken equals() kullan.
 *
 * - Para birimi islemlerinden once birimKontrol()
 *   ile kodlarin ayni oldugu denetlenir.
 *
 *
 * SINAV ICIN COZUM YONTEMI:
 *
 * ADIM 1: Kac tane new ile nesne olusturuluyor?
 *
 * ADIM 2: Hangi referans hangi nesneyi gosteriyor?
 *
 * ADIM 3: Metot mevcut nesneyi mi degistiriyor,
 *         yoksa yeni bir nesne mi donduruyor?
 *
 * ADIM 4: Donen nesne bir degiskene ataniyor mu?
 *
 * ADIM 5: Yeniden atama yapilan referans final mi?
 *
 * ADIM 6: println hangi referansi yazdiriyor?
 *
 *
 * GENEL SONUC:
 *
 * IMMUTABLE -> Nesnenin durumu degismez.
 * FINAL     -> Bir referansa yeniden atama yapilamaz.
 * NEW       -> Yeni bir nesne olusturur.
 * RETURN    -> Metottan sonuc dondurur.
 * STATIC    -> Metodun sinif uzerinden cagrilmasini saglar.
 * THIS      -> Mevcut nesneyi ifade eder.
 *
 * EN ONEMLI KURAL:
 *
 * NESNENIN DEGISMESI ile REFERANSIN BASKA NESNEYI
 * GOSTERMEYE BASLAMASI farkli islemlerdir.
 *
 * ============================================================
 */
