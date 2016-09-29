package edu.jsu.mcis;

public class ShapeEvent {
    private boolean hexSelected;
	private boolean octSelected;
	
    public ShapeEvent() {
        this(false, false);
    }
    public ShapeEvent(boolean hexSelected, boolean octSelected) {
        this.hexSelected = hexSelected;
		this.octSelected = octSelected;
    }
    public boolean isHexSelected() { return hexSelected; }
	public boolean isOctSelected() { return octSelected; }
}