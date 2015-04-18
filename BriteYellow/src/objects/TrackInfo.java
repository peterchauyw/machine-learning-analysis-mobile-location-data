package objects;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import distribution.StatsGenerator;

@DynamoDBTable(tableName = "REPLACED_BY_VALUE_IN_PROPERTIES_FILE")
public class TrackInfo extends DataBaseObject {
	
	private String id;
	private double pathLength;
	private double timeStopped; 
	private double noStops; 
	private double timeSpent; 
	private double inactiveTime; 
	private double sThetaChange; 
	private double sThetaIn; 
	private double sThetaOut; 
	private double sThetaInOut;
	private double timePerStop;
	private double totAvrgSpeed;
	private double timesStoppedHere;
	private double pathPerShortest;
	private double timePerShortest;
	private double speedLessThan3;
	private double speedLessThan2;
	private double speedLessThan1;
	private double anglelargerthan5;
	private double anglelargerthan10;
	private double anglelargerthan15;
	private double anglelargerthan20;
	
	

	private double speedLargerThan10;
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	private int characteristic;
	@DynamoDBAttribute (attributeName = "SpeedBelow2")
	public double getSpeedLessThan2() {
		return speedLessThan2;
	}
	public void setSpeedLessThan2(double speedLessThan2) {
		this.speedLessThan2 = speedLessThan2;
	}
	@DynamoDBAttribute (attributeName = "SpeedBelow1")
	public double getSpeedLessThan1() {
		return speedLessThan1;
	}
	public void setSpeedLessThan1(double speedLessThan1) {
		this.speedLessThan1 = speedLessThan1;
	}

	@DynamoDBAttribute (attributeName = "AngleBelow5")
	public double getAngleLargerThan5() {
		return anglelargerthan5;
	}
	public void setAngleLargerThan5(double anglelargerthan5) {
		this.anglelargerthan5 = anglelargerthan5;
	}
	@DynamoDBAttribute (attributeName = "AngleBelow10")
	public double getAngleLargerThan10() {
		return anglelargerthan10;
	}
	public void setAngleLargerThan10(double anglelargerthan10) {
		this.anglelargerthan10 = anglelargerthan10;
	}
	@DynamoDBAttribute (attributeName = "AngleBelow15")
	public double getAngleLargerThan15() {
		return anglelargerthan15;
	}
	public void setAngleLargerThan15(double anglelargerthan15) {
		this.anglelargerthan15 = anglelargerthan15;
	}
	@DynamoDBAttribute (attributeName = "AngleBelow20")
	public double getAngleLargerThan20() {
		return anglelargerthan20;
	}
	public void setAngleLargerThan20(double anglelargerthan20) {
		this.anglelargerthan20 = anglelargerthan20;
	}
	
	@DynamoDBAttribute (attributeName = "SpeedBelow3")
	public double getSpeedLessThan3(){
		return speedLessThan3;
	}
	public void setSpeedLessThan3(double speedLessThan3){
		this.speedLessThan3 = speedLessThan3;
	}
	@DynamoDBAttribute (attributeName = "SpeedAbove10")
	public double getSpeedLargerThan10(){
		return speedLargerThan10;
	}
	public void setSpeedLargerThan10(double speedLargerThan10){
		this.speedLargerThan10 = speedLargerThan10;
	}
	@DynamoDBAttribute (attributeName = "Path per shortest")
	public double getPathPerShortest() {
		return pathPerShortest;
	}
	public void setPathPerShortest(double pathPerShortest) {
		this.pathPerShortest = pathPerShortest;
	}
	@DynamoDBAttribute (attributeName = "Time per Shortest")
	public double getTimePerShortest() {
		return timePerShortest;
	}
	public void setTimePerShortest(double timePerShortest) {
		this.timePerShortest = timePerShortest;
	}
	@DynamoDBAttribute (attributeName = "Characteristic")
	public int getCharacteristic() {
		return characteristic;
	}
	public void setCharacteristic(int characteristic) {
		this.characteristic = characteristic;
	}
	
    @DynamoDBHashKey(attributeName = "ID")
    @DynamoDBAutoGeneratedKey
    public String getId() { return id; }
    public void setId(String ID) { this.id = ID; } 

	@DynamoDBIndexHashKey(attributeName= "Phone_ID", globalSecondaryIndexName="Phone_Index")
	public String getPHONE_ID() {
		return phone_id;
	}
	public void setPHONE_ID(String phone_id){
		this.phone_id = phone_id;
	}
	
	@DynamoDBAttribute (attributeName = "TIMES_STOPPED_HERE")
	public double getTIMESSTOPPEDHERE() {
		return timesStoppedHere;
	}
	public void setTIMESSTOPPEDHERE(double timesStoppedHere){
		this.timesStoppedHere = timesStoppedHere;
	}
	
	
	@DynamoDBAttribute (attributeName = "TOTAL_AVRG_SPEED")
	public double getTOTAVRGSPEED() {
		return totAvrgSpeed;
	}	
	public void setTOTAVRGSPEED(double totAvrgSpeed){
		this.totAvrgSpeed = totAvrgSpeed;
	}
	
	
	@DynamoDBAttribute (attributeName = "TIME_PER_STOP")
	public double getTIMEPERSTOP() {
		return timePerStop;
	}
	public void setTIMEPERSTOP(double timePerStop){
		this.timePerStop = timePerStop;
	}
	
	
	@DynamoDBIndexRangeKey(attributeName = "Track_no" , globalSecondaryIndexName="Phone_Index")
	public int getTRACK_NO() {
		return track_no;
	}
	public void setTRACK_NO(int track_no) {
		this.track_no = track_no;
	}
	
	@DynamoDBAttribute (attributeName = "X1")
	public double getX1() {
		return x1;
	}
	public void setX1(double x1) {
		this.x1 = x1;
	}
	
	@DynamoDBAttribute (attributeName = "Y1")
	public double getY1() {
		return y1;
	}
	public void setY1(double y1) {
		this.y1 = y1;
	}
	
	@DynamoDBAttribute (attributeName = "X2")
	public double getX2() {
		return x2;
	}
	public void setX2(double x2) {
		this.x2 = x2;
	}
	
	@DynamoDBAttribute (attributeName = "Y2")
	public double getY2() {
		return y2;
	}
	public void setY2(double y2) {
		this.y2 = y2;
	}
	
	@DynamoDBAttribute (attributeName = "PATH_LENGTH")
	public double getPATH_LENGTH() {
		return pathLength;
	}
	public void setPATH_LENGTH(double pathLength) {
		this.pathLength = pathLength;
	}
	
	@DynamoDBAttribute (attributeName = "TIME_STOPPED")
	public double getTIME_STOPPED() {
		return timeStopped;
	}
	public void setTIME_STOPPED(double timeStopped) {
		this.timeStopped = timeStopped;
	}
	
	@DynamoDBAttribute (attributeName = "NO_STOPS")
	public double getNO_STOPS() {
		return noStops;
	}
	public void setNO_STOPS(double noStops) {
		this.noStops = noStops;
	}
	
	@DynamoDBAttribute (attributeName = "TIME_SPENT")
	public double getTIME_SPENT() {
		return timeSpent;
	}
	public void setTIME_SPENT(double timeSpent) {
		this.timeSpent = timeSpent;
	}
	
	@DynamoDBAttribute (attributeName = "INACTIVE_TIME")
	public double getINACTIVE_TIME() {
		return inactiveTime;
	}
	public void setINACTIVE_TIME(double inactiveTime) {
		this.inactiveTime = inactiveTime;
	}
	
	@DynamoDBAttribute (attributeName = "S_THETA_CHANGE")
	public double getSTHETACHANGE() {
		return sThetaChange;
	}
	public void setSTHETACHANGE(double sThetaChange) {
		this.sThetaChange = sThetaChange;
	}
	
	@DynamoDBAttribute (attributeName = "S_THETA_IN")
	public double getSTHETAIN() {
		return sThetaIn;
	}
	public void setSTHETAIN(double sThetaIn) {
		this.sThetaIn = sThetaIn;
	}
	
	@DynamoDBAttribute (attributeName = "S_THETA_OUT")
	public double getSTHETAOUT() {
		return sThetaOut;
	}
	public void setSTHETAOUT(double sThetaOut) {
		this.sThetaOut = sThetaOut;
	}
	
	@DynamoDBAttribute (attributeName = "S_THETA_IN_OUT")
	public double getSTHETAINOUT() {
		return sThetaInOut;
	}
	public void setSTHETAINOUT(double sThetaInOut) {
		this.sThetaInOut = sThetaInOut;
	}
	@Override
	public String toString() {
		return "TrackInfo [id=" + id + ", pathLength=" + pathLength
				+ ", timeStopped=" + timeStopped + ", noStops=" + noStops
				+ ", timeSpent=" + timeSpent + ", inactiveTime=" + inactiveTime
				+ ", sThetaChange=" + sThetaChange + ", sThetaIn=" + sThetaIn
				+ ", sThetaOut=" + sThetaOut + ", sThetaInOut=" + sThetaInOut
				+ ", timePerStop=" + timePerStop + ", totAvrgSpeed="
				+ totAvrgSpeed + ", timesStoppedHere=" + timesStoppedHere
				+ ", pathPerShortest=" + pathPerShortest + ", timePerShortest="
				+ timePerShortest + ", x1=" + x1 + ", y1=" + y1 + ", x2=" + x2
				+ ", y2=" + y2 + ", characteristic=" + characteristic + "]";
	}
	
	public String toCSV() {
		return phone_id + "," + track_no + "," + pathLength + ","
				+ timeStopped + "," + noStops + ","
				+ timeSpent + "," + inactiveTime
				+ "," + sThetaChange + "," + sThetaIn
				+ "," + sThetaOut + "," + sThetaInOut
				+ "," + timePerStop + ","
				+ totAvrgSpeed + "," + timesStoppedHere
				+ "," + x1 + "," + y1 + "," + x2 + "," + y2
				 ;
	}
	
	public String toCSVNoRef() {
		return 	pathLength + ","
				+ timeStopped + "," + noStops + ","
				+ timeSpent + "," + inactiveTime
				+ "," + sThetaChange + "," + sThetaIn
				+ "," + sThetaOut + "," + sThetaInOut
				+ "," + timePerStop + ","
				+ totAvrgSpeed + "," + timesStoppedHere
				+ "," + x1 + "," + y1 + "," + x2 + "," + y2
				 ;
	}
	
	public String toCSVNoXY() {
		return phone_id + "," + track_no + "," + pathLength + ","
				+ timeStopped + "," + noStops + ","
				+ timeSpent + "," + inactiveTime
				+ "," + sThetaChange + "," + sThetaIn
				+ "," + sThetaOut + "," + sThetaInOut
				+ "," + timePerStop + ","
				+ totAvrgSpeed + "," + timesStoppedHere;
	}
	
	public String toCSVNoXYNoRef() {
		return pathLength + ","
				+ timeStopped + "," + noStops + ","
				+ timeSpent + "," + inactiveTime
				+ "," + sThetaChange + "," + sThetaIn
				+ "," + sThetaOut + "," + sThetaInOut
				+ "," + timePerStop + ","
				+ totAvrgSpeed + "," + timesStoppedHere;
	}
	
	

}
