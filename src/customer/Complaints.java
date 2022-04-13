package customer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import javax.management.remote.SubjectDelegationPermission;
import javax.swing.JOptionPane;
import connector.DatabaseConnection;

public class Complaints extends Customer implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cNo;
	private String category;
	private String details;
	private String date;
	private String status;
	private String assignedTo;
	private String repResponseDate;
	private String repRespondent;
	private String repResponse;
	private String techResponseDate;
	private String techRespondent;
	private String techResponse;
	private String visitDate;

	private static Connection dbConnect = DatabaseConnection.getConnection();
	
	public Complaints() 
	{
		super();
		cNo = "";
		id = "";
		category = "";
		details = "";
		date = "";
		status = "Unresolved";
		repResponseDate = "N/A";
		repRespondent = "N/A";
		repResponse = "N/A";
		techResponseDate = "N/A";
		techRespondent = "N/A";
		techResponse = "N/A";
		assignedTo = "N/A";
		visitDate = "N/A";
	}
	
	public Complaints(String cNo, String customerId, String category, String details, String date, String status, String assignedTo,String repResponseDate, String repRespondent, String repResponse, String techResponseDate,String techRespondent, String techResponse, String visitDate)
	{
		super(customerId);
		this.cNo = cNo;
		this.category = category;
		this.details = details;
		this.date = date;
		this.status = status;
		this.assignedTo = assignedTo;
		this.repResponseDate = repResponseDate;
		this.repRespondent = repRespondent;
		this.repResponse = repResponse;
		this.techResponseDate = techResponseDate;
		this.techRespondent = techRespondent;
		this.techResponse = techResponse;
		this.visitDate = visitDate;
	}
	//Accessors
	public String getcNo() 
	{
		return cNo;
	}
	public String getCategory() 
	{
		return category;
	}
	public String getRepResponseDate() {
		return repResponseDate;
	}
	public void setRepResponseDate(String repResponseDate) {
		this.repResponseDate = repResponseDate;
	}
	public String getRepRespondent() {
		return repRespondent;
	}
	public void setRepRespondent(String repRespondent) {
		this.repRespondent = repRespondent;
	}
	public String getRepResponse() {
		return repResponse;
	}
	public void setRepResponse(String repResponse) {
		this.repResponse = repResponse;
	}
	public String getTechResponseDate() {
		return techResponseDate;
	}
	public void setTechResponseDate(String techResponseDate) {
		this.techResponseDate = techResponseDate;
	}
	public String getTechRespondent() {
		return techRespondent;
	}
	public void setTechRespondent(String techRespondent) {
		this.techRespondent = techRespondent;
	}
	public String getTechResponse() {
		return techResponse;
	}
	public void setTechResponse(String techResponse) {
		this.techResponse = techResponse;
	}
//	public String getCustomerId() {
//		return customerId;
//	}
//	public void setCustomerId(String customerId) {
//		this.customerId = customerId;
//	}
	public String getDetails() 
	{
		return details;
	}
	public String getDate() 
	{
		return date;
	}
	public String getStatus() 
	{
		return status;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}
	//Mutators
	public void setcNo(String cNo) 
	{
		this.cNo = cNo;
	}
	public void setCategory(String category) 
	{
		this.category = category;
	}
	public void setDetails(String details) 
	{
		this.details = details;
	}
	public void setDate(String date) 
	{
		this.date = date;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}
	@Override
	public String toString() {
		return "Complaints [cNo=" + cNo + ", category=" + category + ", details=" + details + ", date=" + date
				+ ", status=" + status + ", assignedTo=" + assignedTo + ", repResponseDate=" + repResponseDate
				+ ", repRespondent=" + repRespondent + ", repResponse=" + repResponse + ", techResponseDate="
				+ techResponseDate + ", techRespondent=" + techRespondent + ", techResponse=" + techResponse
				+ ", visitDate=" + visitDate + "]";
	}
}
