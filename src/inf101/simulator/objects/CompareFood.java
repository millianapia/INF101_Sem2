package inf101.simulator.objects;

import java.util.Comparator;

public class CompareFood implements Comparator<IEdibleObject> {

	@Override
	public int compare(IEdibleObject arg0, IEdibleObject arg1) {
		int food1 = (int) arg0.getNutritionalValue();
		int food2 = (int) arg1.getNutritionalValue();

		if (food1 > food2)
			return 1;
		else if (food1 == food2)
			return 0;
		else if (food1 < food2)
			return -1;
		return 0;
	
	}

}
