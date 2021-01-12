package jp.co.sss.lms.util;

import org.springframework.stereotype.Component;

/**
 * 勤怠管理のユーティリティクラス
 * 
 * @author Shin
 */
@Component
public class AttendanceUtil {

	/**
	 * 中抜け時間を時(hour)と分(minute)に変換
	 *
	 * @param min 中抜け時間
	 * @return 時(hour)と分(minute)に変換したクラス
	 */
	public TrainingTime calcBlankTime(int min) {
		int hour = min / 60;
		int minute = min % 60;
		TrainingTime total = new TrainingTime(hour, minute);
		return total;
	}

}
