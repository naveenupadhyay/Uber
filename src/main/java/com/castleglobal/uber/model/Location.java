package com.castleglobal.uber.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.castleglobal.uber.Application;

@Component
public class Location implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4126782391518134872L;
	/*
	 * 
	 *  Locations are stored as a set of polygons ( square in this case ) as against storing for each x,y co-ordinate by exact lat-long match
	 *   as the use case is to show cabs in an area and not necessarily by their exact co-ordinates. 
	 *  Therefore  it becomes easier if there locations are stored in a bigger polygon  to begin with.
	 *  
	 *  Benifits - 
	 *    - when a user asks for a cab he wants cabs in his area and not at this exact co-ordinate.
	 *    - cabs can frequently change their x,y location as they are very granular and hence there location should be maintained in a bigger polygon.
	 *    - if a user requests for a cab in his location and there is no car in that polygon then we do one level BFS in nearby polygons and return available
	 *      cabs.
	| | | | | | | |
	|		      |
	|			  |
	|			  |
	|			  |
	| | | | | | | |
	  In this example if bottom left is (0,0) so this polygon encompasses x -> 0 to 7 y -> 0 ,7 set of co-ordinates
	  
	*/
	 private Long polygonId =1L;	
	 private static Map<Coordinates,Long> coordinatesToLocationPolygonMap = new HashMap<Coordinates, Long>();
	 private static boolean init = false;
	 
	 private final static Logger LOG = LoggerFactory.getLogger(Location.class);
	
	
	 @PostConstruct
	 void  init(){
		 
		  int xmin = 0;
		  int ymin = 0;
		  int count = 4;
		  for(int level = 0; level < Math.ceil((Application.xyMax - Application.xyMin)/count ); level ++){
			  int polygonSize = 4*4;
			  int currentCount =0;
			
			  
			for (long x = xmin; x < Application.xyMax; x++) {
				if(currentCount == polygonSize){
					currentCount = 0;
					polygonId++;
				}
				  int yCount =0;
				for (long y = ymin ; y < Application.xyMax && yCount < count; y++,yCount ++) {
					Coordinates coordinates = new Coordinates(x, y);
					
					LOG.info("Adding coordiante {} to {} ", coordinates, polygonId);
					
					coordinatesToLocationPolygonMap.put(coordinates, polygonId);
					currentCount++;
					}
				}
			polygonId ++;
			ymin += count;
		  }
				
	 }
	 
	 Long getPolygonIdByCoordinates(Coordinates xy){
	
		 return coordinatesToLocationPolygonMap.get(xy);
	 }
	
	 public Long getPolygonId() {
		return polygonId;
	}

	public void setPolygonId(Long polygonId) {
		this.polygonId = polygonId;
	}

	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Location(Coordinates xy){
		 if(!init)
			 init();
		 this.polygonId = coordinatesToLocationPolygonMap.get(xy);
	 }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (polygonId ^ (polygonId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (polygonId != other.polygonId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Location [polygonId=" + polygonId + "]";
	}
	 
	 
}
