

public class VadeliHesap extends Hesap {

    private final int vadeGunu;
    private int gecenGun;

    public VadeliHesap(String sahip, double acilisBakiyesi,
                       int vadeGunu) {

        super(sahip, acilisBakiyesi);

        if (vadeGunu <= 0) {
            throw new IllegalArgumentException("Vade pozitif olmali.");
        }

        this.vadeGunu = vadeGunu;
        this.gecenGun = 0;
    }

    public void gunIlerlet(int gun) {

        if (gun < 0) {
            throw new IllegalArgumentException("Gun negatif olamaz.");
        }

        gecenGun += gun;
    }

    public boolean vadeDoldumu() {
        return gecenGun >= vadeGunu;
    }

    @Override
    public boolean cek(double miktar) {

        if (!vadeDoldumu()) {
            System.out.println("RET: Vade dolmadi.");
            return false;
        }

        return super.cek(miktar);
    }

    @Override
    protected double kesintiOrani() {
        return 0.005;
    }

    public double faizIsle() {

        double faiz = bakiye * 0.05 * gecenGun / 365.0;
        bakiye += faiz;

        return faiz;
    }
}