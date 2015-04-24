package module.powerplant;



public class NuclearReactor extends PowerPlant{

	public NuclearReactor(int size) {
		super(size);
		health=1.0*size;
		mass=5.0*size;
		power=2.0*size;
		powerCap+=5.0*size;
	}

}
