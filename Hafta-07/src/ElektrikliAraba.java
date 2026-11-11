
public class ElektrikliAraba extends Araba {

    private int sarjYuzdesi;

    public ElektrikliAraba(String plaka, Motor motor, int sarjYuzdesi) {

        super(plaka, motor);

        this.sarjYuzdesi =
                Math.min(100, Math.max(0, sarjYuzdesi));
    }

    @Override
    public boolean surus(int km) {

        if (km <= 0) {
            return false;
        }

        int gerekenSarj = Math.max(1, (int) Math.ceil(km / 5.0));

        if (sarjYuzdesi < gerekenSarj) {
            System.out.println(plaka() + ": Sarj yetersiz.");
            return false;
        }

        if (!super.surus(km)) {
            return false;
        }

        sarjYuzdesi -= gerekenSarj;

        System.out.println("Kalan sarj: %" + sarjYuzdesi);

        return true;
    }

    @Override
    public String bilgi() {
        return super.bilgi() + " | Sarj %" + sarjYuzdesi;
    }
}