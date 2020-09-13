package Model01.ex01;

import java.sql.Date;

public class WordVo {
	private String UserId;
	private String wORD;
	private String Mean;
	private String saveDate;
	private String M_wORD;
	private String M_Mean;
	public String getM_wORD() {
		return M_wORD;
	}
	public void setM_wORD(String m_wORD) {
		M_wORD = m_wORD;
	}
	public String getM_Mean() {
		return M_Mean;
	}
	public void setM_Mean(String m_Mean) {
		M_Mean = m_Mean;
	}
	public String getwORD() {
		return wORD;
	}
	public void setwORD(String wORD) {
		this.wORD = wORD;
	}
	public String getMean() {
		return Mean;
	}
	public void setMean(String mean) {
		this.Mean = mean;
	}

	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getSaveDate() {
		return saveDate;
	}
	public void setSaveDate(String today) {
		this.saveDate = today;
	}
	
	
}
