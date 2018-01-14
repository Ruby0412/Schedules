import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Event represents events to be held in .
 */
public class Event implements Interval{
	private long start;
	private long end;
	private String name;
	private String resource;
	private String organization;
	private String description;

	Event(long start, long end, String name, String resource, String organization, String description){
		if (start < 0 || end < 0 || start > end) {
			throw new IllegalArgumentException();
		}
		this.start = start;
		this.end = end;
		this.name = name;
		this.resource = resource;
		this.organization = organization;
		this.description = description;
	}

	@Override
	public long getStart(){
		return start;
	}

	@Override
	public long getEnd(){
		return end;
	}

	public String getName() {
		return name;
	}

	public String getResource() {
		return resource;
	}

	public String getOrganization(){
		return organization;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public int compareTo(Interval i) {
		if (start <= i.getStart())
			return -1;
		return 1;
	}


	public boolean equals(Event e) {
		if (start == e.getStart())
			return true;
		return false;
	}

	@Override
	public boolean overlap(Interval i) {
		if (start >= i.getEnd() || i.getStart() >= end) {
			return false;
		} else if (start >= i.getStart() && end <= i.getEnd() ||
				i.getStart() >= start && i.getEnd() <= end) {
			return true;
		} else {
			return true;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public String toString(){
		SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("MM/dd/yyyy,HH:mm");
		Date dateStart = new Date(start);
		Date dateEnd = new Date(end);
		return name + "\nBy: " + organization + "\nIn: " + resource
				+ "\nStart: " + formatter.format(dateStart)
				+ "\nEnd: " + formatter.format(dateEnd) + "\n" + description;
	}
}
