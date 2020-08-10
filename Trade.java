package test;

import java.util.Date;

public class Trade
{
	private String tradeId;
	private Integer version;
	private String counterPartyId;
	private String bookId;
	private String maturityDate;
	private String createDate;
	private String expired;
	
	public Trade(String tradeId, Integer version, String counterPartyId, String bookId, String maturityDate, String createDate, String expired)
	{
		this.tradeId = tradeId;
		this.version = version;
		this.counterPartyId = counterPartyId;
		this.bookId = bookId;
		this.maturityDate = maturityDate;
		this.createDate = createDate;
		this.expired = expired;
	}
	
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getCounterPartyId() {
		return counterPartyId;
	}
	public void setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getExpired() {
		return expired;
	}
	public void setExpired(String expired) {
		this.expired = expired;
	}
	
	public String toString(){
		return this.tradeId + "\t\t" + this.version + "\t\t" + this.counterPartyId + "\t\t" + this.bookId + "\t\t" + this.maturityDate + "\t\t" + this.createDate + "\t\t" + this.expired;
	}
	
}