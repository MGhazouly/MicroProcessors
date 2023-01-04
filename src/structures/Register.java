package structures;

public class Register{
	private int name;
	private double data;
	private StationId stationId;

	public Register(int name) {
		this.name = name;
		this.data = 0;
		this.stationId = null;
	}

	public int getName() {
		return name;
	}

	public double getData() {
		return data;
	}

	public void setData(double data) {
		this.data = data;
	}
	
	public void setName(int name) {
		this.name = name;
	}
	
	public StationId getStationId() {
		return stationId;
	}
	
	public void setStationId(StationId stationId) {
		this.stationId = stationId;
	}
	
}
