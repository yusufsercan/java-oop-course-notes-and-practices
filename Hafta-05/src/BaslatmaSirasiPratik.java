
class Makine {

    // 1. SINIF DUZEYI
    static int uretilen = izle("[S1] Static alan", 0);

    static {
        System.out.println("[S2] Static blok");
    }

    // 2. NESNE DUZEYI
    int kapasite;

    int ikiKat = izle("[O1] Ornek alan", kapasite * 2);

    {
        System.out.println("[O2] Ornek baslatma blogu");
    }

    int durum = izle("[O3] Diger ornek alan", 1);

    // 3. CONSTRUCTOR ZINCIRI
    Makine() {
        this(10);
        System.out.println("[Y2] Parametresiz yapici");
    }

    Makine(int kapasite) {
        System.out.println("[Y1] Ana yapici");
        this.kapasite = kapasite;
        uretilen++;
    }

    private static int izle(String mesaj, int deger) {
        System.out.println(mesaj);
        return deger;
    }
}

public class BaslatmaSirasiPratik {

    public static void main(String[] args) {

        System.out.println("Main basladi");

        Makine a = new Makine(5);

        System.out.println("A kapasite: " + a.kapasite);
        System.out.println("A ikiKat: " + a.ikiKat);

        Makine b = new Makine();

        System.out.println("B kapasite: " + b.kapasite);
        System.out.println("B ikiKat: " + b.ikiKat);

        System.out.println("Toplam makine: " + Makine.uretilen);
    }
}

/*
 * ============================================================
 * HAFTA 05 - 4.4 NESNE BASLATMA SIRASI
 * ============================================================
 *
 * 1. KONUNUN AMACI
 * ------------------------------------------------------------
 * Java'da bir sinif ilk kez kullanildiginda ve
 * yeni bir nesne olusturuldugunda hangi islemlerin
 * hangi sirayla calistigini ogrenmek.
 *
 *
 * 2. STATIC ALAN VE STATIC BLOK
 * ------------------------------------------------------------
 * static alanlar sinifa aittir.
 *
 * static {
 *     ...
 * }
 *
 * bir static baslatma blogudur.
 *
 * Sinif baslatildiginda static alan baslaticilari
 * ve static bloklar yazim sirasiyla calisir.
 *
 * Bu islemler sinif basina bir kez yapilir.
 *
 * Her new ifadesinde tekrarlanmaz.
 *
 *
 * 3. ORNEK ALAN VE ORNEK BLOK
 * ------------------------------------------------------------
 * int kapasite;
 *
 * Her nesnenin kendi kapasite alani vardir.
 *
 * {
 *     ...
 * }
 *
 * seklindeki normal baslatma blogu her yeni
 * nesne icin calisir.
 *
 * Ornek alan baslaticilari ve ornek bloklar
 * kaynak koddaki yazim sirasiyla calisir.
 *
 *
 * 4. BASLATMA SIRASI
 * ------------------------------------------------------------
 *
 * ILK SINIF KULLANIMI:
 *
 * 1) Static alan baslaticilari
 * 2) Static bloklar
 *
 * HER YENI NESNE:
 *
 * 3) Bellek ayrilir, varsayilan degerler atanir
 * 4) Ornek alan baslaticilari
 *    ve ornek bloklar yazim sirasiyla calisir
 * 5) Constructor govdesi calisir
 *
 *
 * 5. VARSAYILAN DEGERLER
 * ------------------------------------------------------------
 *
 * int       -> 0
 * double    -> 0.0
 * boolean   -> false
 * referans  -> null
 *
 * Bunlar nesnenin alanlarinin baslangic
 * degerleridir.
 *
 * Yerel degiskenlere otomatik olarak bu
 * degerler verilmez.
 *
 *
 * 6. CONSTRUCTOR ZINCIRI
 * ------------------------------------------------------------
 *
 * Makine() {
 *     this(10);
 * }
 *
 * Parametresiz constructor ana constructor'a
 * yonlendirilir.
 *
 * Ana constructor once tamamlanir.
 *
 * Cagiran constructor'in kalan satirlari
 * daha sonra calisir.
 *
 * this(...) zinciri nedeniyle ornek alan
 * baslaticilari tekrar tekrar calismaz.
 *
 *
 * 7. SIK YAPILAN HATA
 * ------------------------------------------------------------
 *
 * int kapasite;
 * int ikiKat = kapasite * 2;
 *
 * kapasite henuz constructor'da atanmadigi
 * icin baslangicta 0 degerindedir.
 *
 * Dolayisiyla ikiKat da 0 olur.
 *
 * Constructor daha sonra kapasiteyi degistirse
 * bile ikiKat kendiliginden yeniden hesaplanmaz.
 *
 * COZUM:
 *
 * Birbirine bagimli alanlari constructor'da,
 * dogru sirayla hesaplamak daha guvenlidir.
 *
 *
 * 8. STATIC VE FINAL KARISTIRILMAMALI
 * ------------------------------------------------------------
 *
 * static:
 * Sinifa ait ortak alani belirtir.
 * Degerinin degismesini engellemez.
 *
 * final:
 * Ilk atamadan sonra yeniden atamayi engeller.
 *
 * static final:
 * Sinifa ait, yeniden atanamayan alandir.
 *
 *
 * 9. VIZE ICIN KAZANIM
 * ------------------------------------------------------------
 *
 * - Static baslatma sinif basina bir kez olur.
 * - Ornek baslatma her yeni nesnede tekrarlanir.
 * - Nesne alanlari once varsayilan degerleri alir.
 * - Alan baslaticilari ve bloklar yazim
 *   sirasini takip eder.
 * - Constructor govdesi daha sonra calisir.
 * - this(...) zinciri ornek baslatmayi tekrarlamaz.
 * - Constructor'a bagimli hesaplamalari
 *   constructor icinde yapmak daha guvenlidir.
 *
 *
 * GENEL KAZANIM:
 *
 * JAVA'DA BIR NESNE OLUSTURULURKEN
 * KODLARIN CALISMA SIRASINI TAHMIN EDEBILMEK.
 *
 * ============================================================
 */
