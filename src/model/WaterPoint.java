package model;

/**
 * @author anjoroge
 *
 *         A WaterPoint object represents attributes of a single water point
 *         from the JSON file.
 */
public class WaterPoint {

	private String communityVillages;
	private boolean isFunctioning;
	private String uuid;

	public String getCommunityVillages() {
		return communityVillages;
	}

	public void setCommunityVillages(String communityVillages) {
		this.communityVillages = communityVillages;
	}

	public boolean isFunctioning() {
		return isFunctioning;
	}

	public void setFunctioning(boolean isFuntioning) {
		this.isFunctioning = isFuntioning;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "WaterPoint [communityVillages=" + communityVillages
				+ ", isFunctioning=" + isFunctioning + ", uuid=" + uuid + "]\n";
	}
}
