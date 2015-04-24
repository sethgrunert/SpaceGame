package ai;

public class ChaseBehavior extends TurretBehavior {
	public int getAccelY(){
		
		if(target!=null){
			double deltaTheta = target.getPos().getAngle(currentLocation, true)-currentFacing;
			if(Math.abs(deltaTheta)<Math.PI/4 && target.getPos().getDistance(currentLocation)>range/2)
				return 1;
			else if(Math.abs(deltaTheta)>Math.PI*3/4)
				return -1;
		}
		return 0;
	}
	
	public int getAccelX(){
		if(target!=null){
			double deltaTheta = target.getPos().getAngle(currentLocation, true)-currentFacing;
			if(deltaTheta>Math.PI/4 && deltaTheta<Math.PI*3/4)
				return 1;
			else if(deltaTheta>Math.PI*5/4 && deltaTheta<Math.PI*7/4)
				return -1;
		}
		return 0;
	}
}
