package module.engine;


public class BasicEngine extends Engine{

	public BasicEngine(int size) {
		super(size);
		mass=3*size;
		thrust=1.25*size;
		health=size;
		power=size*-.5;
	}

}
