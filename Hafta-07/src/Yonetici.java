public class Yonetici extends Personel {

    private final String birim;
    private final int astSayisi;

    public Yonetici(String ad, String tcNo, int dogumYili,
                    String sicilNo, String birim, int astSayisi) {

        super(ad, tcNo, dogumYili, sicilNo, "Yonetici");

        System.out.println("Yonetici constructor calisti");

        this.birim = birim;
        this.astSayisi = Math.max(0, astSayisi);
    }

    @Override
    public String kimlikKarti() {
        return super.kimlikKarti()
                + " | " + birim
                + " birimi, " + astSayisi + " ast";
    }
}