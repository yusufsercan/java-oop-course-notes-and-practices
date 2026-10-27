public class Sensor {
    // 1. & 2. Kural: private ve doğumdan sonra değişmeyecek alanlar (final)
    private final String kod;
    private final String birim;
    private double deger; // varsayılan olarak 0.0 başlar

    // 5. Kural: Her sensörün kendi sayacı (static DEĞİL!)
    private int olcumSayisi = 0;
    private int redSayisi = 0;

    // 3. Kural: Herkese açık sabitler
    public static final double EN_DUSUK = -50.0;
    public static final double EN_YUKSEK = 150.0;

    // Kurucu metot
    public Sensor(String kod, String birim) {
        this.kod = kod;
        this.birim = birim;
    }

    // 4. Kural: Ölçüm ve doğrulama kuralı
    public boolean olc(double deger) {
        if (deger < EN_DUSUK || deger > EN_YUKSEK) {
            System.out.printf("  RED  %s: %.1f %s araligin disinda [%.1f, %.1f]%n",
                              kod, deger, birim, EN_DUSUK, EN_YUKSEK);
            this.redSayisi++;
            return false;
        }

        this.deger = deger;
        this.olcumSayisi++;
        return true;
    }

    // 6. Kural: Yalnızca getter metotları (setter ASLA yok!)
    public String getKod() {
        return kod;
    }

    public String getBirim() {
        return birim;
    }

    public double getDeger() {
        return deger;
    }

    public int getRedSayisi() {
        return redSayisi;
    }

    public int getOlcumSayisi() {
        return olcumSayisi;
    }

    // 7. Kural: toString() biçimlendirmesi
    @Override
    public String toString() {
        return String.format("%s = %5.1f %s  (kabul=%d, red=%d)",
                             kod, deger, birim, olcumSayisi, redSayisi);
    }
}