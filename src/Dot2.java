
public class Dot2 extends Dot {
    private double z;

    public Dot2(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getZ() {
        return this.z;
    }

    @Override
    public String toString() {
        return "{" + getX() + ";" + getY() + ";" + this.z + "}";
    }
}
