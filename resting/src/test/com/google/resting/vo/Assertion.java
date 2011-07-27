package test.com.google.resting.vo;

public class Assertion {

	public Concept concept1;
	public Concept concept2;
	public Frequency frequency;
	public Language language;
	public Relation relation;
	public String resource_uri;
	public int score;

	public static class Frequency {
		public Language language;
		public String resource_uri;
		public String text;
		public String value;
	}

	public static class Relation {
		public String name;
	}
}
