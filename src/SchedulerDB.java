import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;



public class SchedulerDB {
	HashMap<String, Resource> database;
	
	SchedulerDB(){
		database = new HashMap<String, Resource>();
	}
	
	public boolean addResource(String resource){
		if (database.containsKey(resource))
			return false;
		database.put(resource, new Resource(resource));
		return true;
	}
	
	public boolean removeResource(String r){
		if (!database.containsKey(r))
			return false;
		database.remove(r);
		return true;
	}
	
	public boolean addEvent(String r, long start, long end, String name, 
			String organization, String description){
		if (database.containsKey(r)) {
			return database.get(r).addEvent(new Event(start, end, name, r, organization, description));
		} else {
			return false;
		}
	}
	
	public boolean deleteEvent(long start, String resource){
		if (database.containsKey(resource)) {
			return database.get(resource).deleteEvent(start);
		} else {
			return false;
		}
	}
	
	public Resource findResource(String r){
		if (database.containsKey(r)) {
			return database.get(r);
		} else {
			return null;
		}
	}
	
	public List<Resource> getResources(){
		List<Resource> list = new ArrayList<Resource>();
		Iterator iter = database.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry)(iter.next());
			list.add((Resource)entry.getValue());
		}
		return list;
	}
	
	public List<Event> getEventsInResource(String resource){
		if (database.containsKey(resource)) {
			List<Event> events = new ArrayList<Event>();
			Iterator iter = database.get(resource).iterator();
			while(iter.hasNext()) {
				events.add((Event)iter.next());
			}
			return events;
		} else {
			return null;
		}
	}
	
	public List<Event> getEventsInRange(long start, long end){
		List<Event> events = new ArrayList<Event>();
		Iterator iter = database.entrySet().iterator();
		Resource res = null;
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry)(iter.next());
			res = (Resource)entry.getValue();
			Iterator iter_event = res.iterator();
			while (iter_event.hasNext()) {
				Event event = (Event) iter_event.next();
				Event event_aux = new Event(start, end, "", "", "", "");
				if (event.overlap(event_aux)) {
					events.add(event);
				}
			}
		}
		return events;
	}	
	
	public List<Event> getEventsInRangeInResource(long start, long end, String resource){
		if (!database.containsKey(resource))
			return null;
		List<Event> events = new ArrayList<Event>();
		Iterator<Event> iter = database.get(resource).iterator();
		while (iter.hasNext()) {
			Event event = (Event) iter.next();
			Event event_aux = new Event(start, end, "", "", "", "");
			if (event.overlap(event_aux)) {
				events.add(event);
			}
		}
		return events;
	}
	
	public List<Event> getAllEvents(){
		List<Event> events = new ArrayList<Event>();
		Iterator iter = database.entrySet().iterator();
		Resource res = null;
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry)(iter.next());
			res = (Resource)entry.getValue();
			Iterator iter_event = res.iterator();
			while (iter_event.hasNext()) {
				Event event = (Event) iter_event.next();
				events.add(event);
			}
		}
		return events;
	}
	
	public List<Event> getEventsForOrg(String org){
		List<Event> events = new ArrayList<Event>();
		Iterator iter = database.entrySet().iterator();
		Resource res = null;
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry)(iter.next());
			res = (Resource)entry.getValue();
			Iterator iter_event = res.iterator();
			while (iter_event.hasNext()) {
				Event event = (Event) iter_event.next();
				if (event.getOrganization().equals(org))
					events.add(event);
			}
		}
		return events;
	}
}
