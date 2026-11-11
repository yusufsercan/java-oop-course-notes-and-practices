    

public class Personel extends Kisi {

    private final String sicilNo;
    private String unvan;

    public Personel(String ad, String tcNo, int dogumYili,
                    String sicilNo, String unvan) {

        super(ad, tcNo, dogumYili);

        System.out.println("Personel constructor calisti");

        this.sicilNo = sicilNo;
        this.unvan = unvan;
    }

    @Override
    public String kimlikKarti() {
        return super.kimlikKarti()
                + " | PERSONEL " + sicilNo
                + " (" + unvan + ")";
    }
}