package model;

/**
 * @author anjoroge 
 * 
 * A CommunityStatistics object contains statistical details for
 *         a given community. The given statistics include 1. Num of Functional
 *         water points in the community 2. Total num of water points in the
 *         community 3. % of broken water points in the community
 */
public class CommunityStatistics {

	private int numFunctional;
	private int numOfWaterPoints;
	private double percentOfBrokenPoints;

	public int getNumFunctional() {
		return numFunctional;
	}

	public void setNumFunctional(int numFunctional) {
		this.numFunctional = numFunctional;
	}

	public int getNumOfWaterPoints() {
		return numOfWaterPoints;
	}

	public void setNumOfWaterPoints(int numOfWaterPoints) {
		this.numOfWaterPoints = numOfWaterPoints;
	}

	public double getPercentOfBrokenPoints() {
		return percentOfBrokenPoints;
	}

	public void setPercentOfBrokenPoints(double percentOfBrokenPoints) {
		this.percentOfBrokenPoints = percentOfBrokenPoints;
	}

	@Override
	public String toString() {
		return "CommunityStatistics [numFunctional=" + numFunctional
				+ ", numOfWaterPoints=" + numOfWaterPoints
				+ ", percentOfBrokenPoints=" + percentOfBrokenPoints + "%]\n";
	}

}
