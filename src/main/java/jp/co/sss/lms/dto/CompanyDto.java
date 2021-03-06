package jp.co.sss.lms.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import jp.co.sss.lms.util.TrainingTime;


public class CompanyDto implements Serializable {

    /**  */
    private static final long serialVersionUID = 811514106645476122L;

    /** 企業ID */
    public Integer companyId;

    /** 企業名 */
    public String companyName;

    /** 始業時刻 */
    public TrainingTime workStartTime;

    /** 終業時刻 */
    public TrainingTime workEndTime;

    /** 休憩開始時刻 */
    public TrainingTime restStartTime;

    /** 休憩終了時刻 */
    public TrainingTime restEndTime;

    /** 休日 */
    public String holiday;

    /** 最終更新日時 */
    public Date lastModifiedDate;

    /** 電話番号1 */
    public String phoneNumber1;

    /** 電話番号2 */
    public String phoneNumber2;

    /** 電話番号3 */
    public String phoneNumber3;

    /** 代表者名 */
    public String representativeName;

	/** 住所 */
	public String address;

	/** 企業名+住所 */
	public String companyNameAndAddress;

    /**
     * 助成金情報がすべて入力されているかどうか
     * @return
     */
    public boolean isCompleteJoseiKinInfo() {
        if ((this.workStartTime == null || this.workStartTime.isBlank()) ||
                (this.workEndTime == null || this.workEndTime.isBlank()) ||
                (this.restStartTime == null || this.restStartTime.isBlank()) ||
                (this.restEndTime == null || this.restEndTime.isBlank()) ||
                StringUtils.isBlank(this.holiday)) {
            return false;
        }
        return true;
    }

    public String getCompanyNameAndAddress() {
		return companyNameAndAddress;
	}

	public void setCompanyNameAndAddress(String companyNameAndAddress) {
		this.companyNameAndAddress = companyNameAndAddress;
	}


	public void setWorkStartTime(TrainingTime workStartTime) {
		this.workStartTime = workStartTime;
	}

	public void setWorkEndTime(TrainingTime workEndTime) {
		this.workEndTime = workEndTime;
	}

	public void setRestStartTime(TrainingTime restStartTime) {
		this.restStartTime = restStartTime;
	}

	public void setRestEndTime(TrainingTime restEndTime) {
		this.restEndTime = restEndTime;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

	public void setPhoneNumber3(String phoneNumber3) {
		this.phoneNumber3 = phoneNumber3;
	}

	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
     * 企業IDを取得します。
     * @return 企業ID
     */
    public Integer getCompanyId() {
        return companyId;
    }
    
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 企業名を取得します。
     * @return 企業名
     */
    public String getCompanyName() {
        return companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 始業時刻を取得します。
     * @return 始業時刻
     */
    public TrainingTime getWorkStartTime() {
        return workStartTime;
    }

    /**
     * 終業時刻を取得します。
     * @return 終業時刻
     */
    public TrainingTime getWorkEndTime() {
        return workEndTime;
    }

    /**
     * 休憩開始時刻を取得します。
     * @return 休憩開始時刻
     */
    public TrainingTime getRestStartTime() {
        return restStartTime;
    }

    /**
     * 休憩終了時刻を取得します。
     * @return 休憩終了時刻
     */
    public TrainingTime getRestEndTime() {
        return restEndTime;
    }

    /**
     * 休日を取得します。
     * @return 休日
     */
    public String getHoliday() {
        return holiday;
    }

    /**
     * 最終更新日時を取得します。
     * @return 最終更新日時
     */
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * 電話番号1を取得します。
     * @return 電話番号1
     */
    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    /**
     * 電話番号2を取得します。
     * @return 電話番号2
     */
    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    /**
     * 電話番号3を取得します。
     * @return 電話番号3
     */
    public String getPhoneNumber3() {
        return phoneNumber3;
    }

    /**
     * 代表者名を取得します。
     * @return 代表者名
     */
    public String getRepresentativeName() {
        return representativeName;
    }

	/**
	 * 住所を取得します
	 * @return 住所
	 */
	public String getAddress() {
		return address;
	}

	/**
     * 企業名と住所を取得します
     * @return 企業名+住所
     */
    public String getcompanyNameAndAddress() {
        return companyNameAndAddress;
    }
}
