/* 
The following jars needs to be included to be able to create pojo classes
Gson » 2.8.6
https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.11.0
1. Jackson Databind,  com.fasterxml.jackson.core » jackson-annotations, com.fasterxml.jackson.core » jackson-core

Created POJO classes based on the response json file
*/

package pojo;

public class GetCourse {
	
	private String url;
	private String services;
	private String instructor;
	private Courses courses;
	private String expertise;
	private String linkedin;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public Courses getCourses() {
		return courses;
	}
	public void setCourses(Courses courses) {
		this.courses = courses;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public String getLinkedin() {
		return linkedin;
	}
	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}
}
