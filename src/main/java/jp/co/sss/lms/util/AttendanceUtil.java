package jp.co.sss.lms.util;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Component;

import jp.co.sss.lms.enums.AttendanceStatusEnum;

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

	/**
	 * SSS定時・出退勤時間を元に、遅刻早退を判定をする
	 * 
	 * @param trainingStartTime 開始時刻
	 * @param trainingEndTime   終了時刻
	 * @return
	 */
	public static AttendanceStatusEnum getStatus(TrainingTime trainingStartTime, TrainingTime trainingEndTime) {
		return getStatus(trainingStartTime, trainingEndTime, Constants.SSS_WORK_START_TIME,
				Constants.SSS_WORK_END_TIME);
	}

	/**
	 * 与えられた定時・出退勤時間を元に、遅刻早退を判定する
	 * 
	 * @param trainingStartTime 開始時刻
	 * @param trainingEndTime   終了時刻
	 * @param workStartTime     定時開始時刻
	 * @param workEndTime       定時終了時刻
	 * @return
	 */
	public static AttendanceStatusEnum getStatus(TrainingTime trainingStartTime, TrainingTime trainingEndTime,
			TrainingTime workStartTime, TrainingTime workEndTime) {
		// 定時が不明な場合、NONEを返却する
		if (workStartTime == null || workStartTime.isBlank() || workEndTime == null || workEndTime.isBlank()) {
			return AttendanceStatusEnum.NONE;
		}
		boolean isLate = false, isEarly = false;
		// 定時より1分以上遅く出社していたら遅刻(＝はセーフ)
		if (trainingStartTime != null && trainingStartTime.isNotBlank()) {
			isLate = (trainingStartTime.compareTo(workStartTime) > 0);
		}
		// 定時より1分以上早く退社していたら早退(＝はセーフ)
		if (trainingEndTime != null && trainingEndTime.isNotBlank()) {
			isEarly = (trainingEndTime.compareTo(workEndTime) < 0);
		}
		if (isLate && isEarly) {
			return AttendanceStatusEnum.TARDY_AND_LEAVING_EARLY;
		}
		if (isLate) {
			return AttendanceStatusEnum.TARDY;
		}
		if (isEarly) {
			return AttendanceStatusEnum.LEAVING_EARLY;
		}
		return AttendanceStatusEnum.NONE;
	}

	/**
	 * 中抜け時間のプルダウンマップを生成する
	 * 
	 * @return 15分刻みの時間(数値)と〇〇時〇〇分の文字列がセットになったマップ
	 */
	public static LinkedHashMap<Integer, String> setBlankTime() {
		LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
		map.put(0, "");
		for (int i = 15; i < 480;) {
			int hour = i / 60;
			int minute = i % 60;
			String time;

			if (hour == 0) {
				time = minute + "分";

			} else if (minute == 0) {
				time = hour + "時間";
			} else {
				time = hour + "時" + minute + "分";
			}

			map.put(i, time);

			i = i + 15;

		}
		return map;
	}

	/**
	 * プルダウン初期表示用の中抜け時間を返す
	 * 
	 * @param blankTime 中抜け時間
	 * @return 中抜け時間（〇〇時〇〇分の文字列）
	 */
	public static String convertBlankTime(Integer blankTime) {

		String time;
		int hour = blankTime / 60;
		int minute = blankTime % 60;

		time = hour + "時" + minute + "分";

		if (hour == 0) {
			time = minute + "分";
		} else if (minute == 0) {
			time = hour + "時間";
		} else {
			time = hour + "時" + minute + "分";
		}

		return time;
	}

}
