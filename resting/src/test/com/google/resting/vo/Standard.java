package test.com.google.resting.vo;

import java.util.Date;

public class Standard {
		String id;
		String lft;
		String rgt;
		String parentId;
		String title;
		String parentTitle;
		String level;
		
		public String toString(){
			return "id: "+id+"| parentId: "+parentId+"| title: "+title+"| parentTitle: "+parentTitle;
		}


}
