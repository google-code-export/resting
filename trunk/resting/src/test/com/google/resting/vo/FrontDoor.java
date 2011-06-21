package test.com.google.resting.vo;

public class FrontDoor extends Door {
    private String keytype;

    public FrontDoor() {
        super();
    }

    public FrontDoor(String id, int height) {
        super(id, height);
    }

    public String getKeytype() {
        return keytype;
    }

    public void setKeytype(String keytype) {
        this.keytype = keytype;
    }
    
    public String toString(){
    	return "door id = "+id+"| door height: "+height+"| door keytype= "+keytype;
    }

}
