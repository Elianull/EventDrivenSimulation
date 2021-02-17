package CSE464.Program9;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

@SuppressWarnings("restriction")
public class Particle3D extends Sphere {
	private static double mass = 10;
	private static double vx = 0, vy = 0, vz = 0;
	
	public Particle3D() {
		this(Util.getRandomInt(1, 100), Util.getRandomInt(1, 1000), Util.getRandomInt(1, 1000), Util.getRandomInt(1, 1000),
				Util.getRandomInt(1, 10), Util.getRandomInt(1, 10), Util.getRandomInt(1, 10));
	}
	public Particle3D(double radius, int x, int y, int z, double vx, double vy, double vz, Color color, double mass) {
		this.setRadius(radius);
		this.setTranslateX(x);
		this.setTranslateY(y);
		this.setTranslateZ(z);
		this.setMaterial(new PhongMaterial(color));
		Particle3D.setVx(vx);
		Particle3D.setVy(vy);
		Particle3D.setVz(vz);
		Particle3D.mass = mass;
	}
	public Particle3D(double radius, int x, int y, int z, double vx, double vy, double vz) {
		this(radius, x, y, z, vx, vy, vz, Color.RED, Util.getRandomInt(0, 10));
	}
	public static double getMass() {
		return mass;
	}
	public static double getVx() {
		return vx;
	}
	public static void setVx(double vx) {
		Particle3D.vx = vx;
	}
	public static double getVy() {
		return vy;
	}
	public static void setVy(double vy) {
		Particle3D.vy = vy;
	}
	public static double getVz() {
		return vz;
	}
	public static void setVz(double vz) {
		Particle3D.vz = vz;
	}
}
