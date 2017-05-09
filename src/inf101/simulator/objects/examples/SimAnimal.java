package inf101.simulator.objects.examples;

import inf101.simulator.Direction;
import inf101.simulator.GraphicsHelper;
import inf101.simulator.Habitat;
import inf101.simulator.MediaHelper;
import inf101.simulator.Position;
import inf101.simulator.SimMain;
import inf101.simulator.objects.AbstractMovingObject;
import inf101.simulator.objects.CompareFood;
import inf101.simulator.objects.IEdibleObject;
import inf101.simulator.objects.ISimListener;
import inf101.simulator.objects.ISimObject;
import inf101.simulator.objects.ISimObjectFactory;
import inf101.simulator.objects.SimEvent;
import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SimAnimal extends AbstractMovingObject implements ISimListener {
	private static final double defaultSpeed = 1.0;
	private Habitat habitat;
	private double normalWeight = 100;
	private double weight = 70;

	public SimAnimal(Position pos, Habitat hab) {
		super(new Direction(0), pos, defaultSpeed);
		this.habitat = hab;
		hab.addListener(this, this);
	}

	@Override
	public void draw(GraphicsContext context) {

		double direction = getDirection().toAngle();

		if (direction < 90 && direction > -90) {
			context.translate(0, this.getHeight());
			context.scale(1.0, -1.0);
		}

		context.drawImage(MediaHelper.getImage("images/yellowfish.png"), 0, 0, getWidth(), getHeight());
		super.drawBar(context, weight, 0, Color.RED, Color.AQUA);

		super.draw(context);

	}
	 /* Method to check for best food. Gets class CompareFood to use later in code
	  * Gets an empty IEdibleObject. First there is a for-loop that checks all
	  * objects nearby in a radius of (getRadius() +400). If the object is an
	  * instance of IEdibleObject -> checks first if bestfood is empty, then it
	  * sets best food to the object it found in the loop. Then continues to
	  * the next if, which compares a new object and bestfood to see which
	  * is better. Then returns bestfood. 
	  * */
	public IEdibleObject getBestFood() {
		IEdibleObject first = null;
		CompareFood compare = new CompareFood();
		for (ISimObject obj : habitat.nearbyObjects(this, getRadius() + 400)) {
			if (obj instanceof IEdibleObject) {
				if (first == null) {
					first = (IEdibleObject) obj;
					continue;
				}

				if (compare.compare((IEdibleObject) obj, first) == 1) {
					first = (IEdibleObject) obj;
				}
			}
		}

		return first;
	}

	public IEdibleObject getClosestFood() {
		for (ISimObject obj : habitat.nearbyObjects(this, getRadius() + 400)) {

			if (obj instanceof IEdibleObject)
				return (IEdibleObject) obj;
		}

		return null;
	}

	@Override
	public double getHeight() {
		return 50;
	}

	@Override
	public double getWidth() {
		return 50;
	}

	@Override
	public void step() {

		if (normalWeight > weight) {
			weight -= 0.005;
		}  else if (weight < 10) {
			super.destroy();
			SimEvent death = new SimEvent(this, "I'm dying", null, null);
			habitat.triggerEvent(death);
		} 
		// by default, move slightly towards center
		dir = dir.turnTowards(directionTo(habitat.getCenter()), 0.5);

		// go towards center if we're close to the border
		if (!habitat.contains(getPosition(), getRadius() * 1.2)) {
			dir = dir.turnTowards(directionTo(habitat.getCenter()), 5);
			if (!habitat.contains(getPosition(), getRadius())) {
				// we're actually outside
				accelerateTo(5 * defaultSpeed, 0.3);
			}
		}
		/*Checks objects in a nearby radius.  There are ifs that 
		 * checks which object obj is. */
		for (ISimObject obj : habitat.nearbyObjects(this, getRadius() + 400)) {
			Direction dir1 = this.directionTo(obj);
			Direction dir2 = obj.getDirection();

			if (obj instanceof IEdibleObject && (Math.abs(dir1.toAngle()) - dir2.toAngle()) < 90) {
				dir = dir.turnTowards(directionTo(obj), 2);
				if (distanceTo(obj) < 3) {
					SimEvent eatFood = new SimEvent(this, "nom", null, null);
					habitat.triggerEvent(eatFood);
					((IEdibleObject) obj).eat(10);
					weight += 4;

				}
			} else if (obj instanceof SimRepellant && (Math.abs(dir1.toAngle() - dir2.toAngle()) < 120)) {

				Direction turnBack = dir1.turnTowards(directionTo(obj), 10).turnBack();
				dir = dir.turnTowards(turnBack, 2);

			}


		}

		accelerateTo(defaultSpeed, 0.1);

		super.step();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((habitat == null) ? 0 : habitat.hashCode());
		result = prime * result + ((myFactory == null) ? 0 : myFactory.hashCode());
		long temp;
		temp = Double.doubleToLongBits(normalWeight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimAnimal other = (SimAnimal) obj;
		if (habitat == null) {
			if (other.habitat != null)
				return false;
		} else if (!habitat.equals(other.habitat))
			return false;
		if (myFactory == null) {
			if (other.myFactory != null)
				return false;
		} else if (!myFactory.equals(other.myFactory))
			return false;
		if (Double.doubleToLongBits(normalWeight) != Double.doubleToLongBits(other.normalWeight))
			return false;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}

	ISimObjectFactory myFactory = new ISimObjectFactory() {
		public ISimObject create(Position pos, Habitat hab) {
			return null;
		}
	};

	@Override
	public void eventHappened(SimEvent event) {
		this.say(event.getType());
	}
}
