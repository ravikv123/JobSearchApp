package com.demo.JobSearchApp.dto.worker;

import java.util.List;

public class WorkerDTO {

		private int rating;
		private boolean isActive;
		private List<String> certificates;
		private List<String> skills;
		private JobSearchAddress jobSearchAddress;
		private String transportation;
		private boolean hasDriversLicense;
		private List<Availability> availability;
		private String phone;
		private String email;
		private Name name;
		private int age;
		private String guid;
		private int userId;
		public int getRating() {
			return rating;
		}
		public void setRating(int rating) {
			this.rating = rating;
		}
		public boolean getIsActive() {
			return isActive;
		}
		public void setIsActive(boolean isActive) {
			this.isActive = isActive;
		}
		public List<String> getCertificates() {
			return certificates;
		}
		public void setCertificates(List<String> certificates) {
			this.certificates = certificates;
		}
		public List<String> getSkills() {
			return skills;
		}
		public void setSkills(List<String> skills) {
			this.skills = skills;
		}
		public JobSearchAddress getJobSearchAddress() {
			return jobSearchAddress;
		}
		public void setJobSearchAddress(JobSearchAddress jobSearchAddress) {
			this.jobSearchAddress = jobSearchAddress;
		}
		public String getTransportation() {
			return transportation;
		}
		public void setTransportation(String transportation) {
			this.transportation = transportation;
		}
		public boolean isHasDriversLicense() {
			return hasDriversLicense;
		}
		public void setHasDriversLicense(boolean hasDriversLicense) {
			this.hasDriversLicense = hasDriversLicense;
		}
		public List<Availability> getAvailability() {
			return availability;
		}
		public void setAvailability(List<Availability> availability) {
			this.availability = availability;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public Name getName() {
			return name;
		}
		public void setName(Name name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getGuid() {
			return guid;
		}
		public void setGuid(String guid) {
			this.guid = guid;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		
		
		
}
