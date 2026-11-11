

public class Araba {

    private final String plaka;
    private final Motor motor;

    private int kmSayaci;

    public Araba(String plaka, Motor motor) {

        if (plaka == null || plaka.isBlank() || motor == null) {
            throw new IllegalArgumentException("Gecersiz araba bilgisi.");
        }

        this.plaka = plaka;
        this.motor = motor;
        this.kmSayaci = 0;
    }

    public String plaka() {
        return plaka;
    }

    public void kontagiAc() {
        System.out.println(plaka + ": Kontak acildi");
        motor.calistir();
    }

    public boolean surus(int km) {

        if (km <= 0 || !motor.calisiyorMu()) {
            return false;
        }

        kmSayaci += km;

        System.out.println(plaka + ": " + km + " km suruldu");

        return true;
    }

    public void kontagiKapat() {
        motor.durdur();
    }

    public String bilgi() {
        return plaka + " | " + motor.gucHp()
                + " HP | " + kmSayaci + " km";
    }
}