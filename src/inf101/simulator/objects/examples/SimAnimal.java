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

		context.drawImage(MediaHelper.getImage("images/pusheen.png"), 0, 0, getWidth(), getHeight());
		super.drawBar(context, weight, 0, Color.RED, Color.AQUA);

		super.draw(context);

	}

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
		} else if (weight < 40) {
			accelerateTo(2 * defaultSpeed, 2);
			SimEvent death = new SimEvent(this, "I'm hungry", null, null);
			habitat.triggerEvent(death);
		} else if (weight < 10) {
			super.destroy();
			SimEvent death = new SimEvent(this, "I'm dying", null, null);
			habitat.triggerEvent(death);
		} else if (weight > normalWeight) {

			SimEvent fat = new SimEvent(this, "I'm fat", null, null);
			habitat.triggerEvent(fat);
			accelerateTo(0.2, 0.1);
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

		for (ISimObject obj : habitat.nearbyObjects(this, getRadius() + 400)) {
			Direction dir1 = this.directionTo(obj);
			Direction dir2 = obj.getDirection();

			if (obj instanceof IEdibleObject && (Math.abs(dir1.toAngle()) - dir2.toAngle()) < 90) {
				dir = dir.turnTowards(directionTo(obj), 2);
				if (distanceTo(obj) < 3) {
					SimEvent eat = new SimEvent(this, "nom", null, null);
					habitat.triggerEvent(eat);
					((IEdibleObject) obj).eat(10);
					weight += 4;

				}
			} else if (obj instanceof SimRepellant && (Math.abs(dir1.toAngle() - dir2.toAngle()) < 120)) {

				Direction turnBack = dir1.turnTowards(directionTo(obj), 10).turnBack();
				dir = dir.turnTowards(turnBack, 2);

			}
			else if(obj instanceof SimSmallerAnimal){
				dir = dir.turnTowards(directionTo(obj), 2);
				if(distanceTo(obj)<3)
				obj.destroy();
			}

		}

		accelerateTo(defaultSpeed, 0.1);

		super.step();
	}

	ISimObjectFactory myFactory = new ISimObjectFactory() {
		public ISimObject create(Position pos, Habitat hab) {
			return null;
		}
	};

	@Override
	public void eventHappened(SimEvent event) {
		super.say(event.getType());
	}
}
