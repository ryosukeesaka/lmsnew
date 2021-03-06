package jp.co.sss.lms.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.AttendanceManagementDto;
import jp.co.sss.lms.dto.CourseServiceSectionDto;
import jp.co.sss.lms.dto.StudentAttendanceDto;
import jp.co.sss.lms.entity.TStudentAttendance;
import jp.co.sss.lms.enums.AttendanceStatusEnum;
import jp.co.sss.lms.form.AttendanceForm;
import jp.co.sss.lms.form.DailyAttendanceForm;
import jp.co.sss.lms.repository.TStudentAttendanceRepository;
import jp.co.sss.lms.util.AttendanceUtil;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.DateUtil;
import jp.co.sss.lms.util.MessageUtil;
import jp.co.sss.lms.util.TrainingTime;

/**
 * 勤怠情報(受講生)サービスクラス
 * 
 * @author ogura
 */
@Service
public class StudentAttendanceService {

	@Autowired
	private TStudentAttendanceRepository repository;
	@Autowired
	private DateUtil dateUtil;
	@Autowired
	private AttendanceUtil attendanceUtil;

	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private CourseService courseService;

	/**
	 * 勤怠情報取得
	 * 
	 * @param lmsUserId LMSユーザID
	 * @return 勤怠情報DTOリスト
	 */
	public List<StudentAttendanceDto> getStudentAttendanceList(Integer lmsUserId) {

		// LMSユーザIDから勤怠情報リストを作成
		List<TStudentAttendance> tStudentAttendanceList = repository.findByLmsUserId(lmsUserId);

		// 勤怠情報(受講生)DTOのリストを作成
		List<StudentAttendanceDto> tStudentAttendanceDtoList = new ArrayList<>();

		// 勤怠情報リストの情報を1つずつ取り出し、エンティティに格納
		for (TStudentAttendance tStudentAttendance : tStudentAttendanceList) {
			// エンティティの情報をDTOに代入
			AttendanceManagementDto dto = convertEntityToDto(tStudentAttendance);
			tStudentAttendanceDtoList.add(dto);
		}
		return tStudentAttendanceDtoList;
	}

	/**
	 * 勤怠管理画面用のDTOを作成
	 * 
	 * @param courseServiceSectionDtoList セクションDTOリスト
	 * @param studentAttendanceDtoMap     勤怠情報DTOマップ
	 * @return 勤怠情報(画面表示用)DTOリスト
	 */
	public List<AttendanceManagementDto> createAttendanceManagementDto(
			List<CourseServiceSectionDto> courseServiceSectionDtoList,
			Map<String, StudentAttendanceDto> studentAttendanceDtoMap) {

		List<AttendanceManagementDto> attendanceManagementDtoList = new ArrayList<AttendanceManagementDto>();

		// 当日の日付を形式に変換
		String today = dateUtil.toString(new Date());

		for (CourseServiceSectionDto courseServiceSectionDto : courseServiceSectionDtoList) {

			AttendanceManagementDto attendanceManagementDto = new AttendanceManagementDto();

			// このセクションに紐づく勤怠情報を取得
			StudentAttendanceDto dto = studentAttendanceDtoMap.get(getDtoMapKey(courseServiceSectionDto.getDate()));

			if (dto != null) {
				BeanUtils.copyProperties(dto, attendanceManagementDto);
				TrainingTime blankTime = new TrainingTime();

				if (dto.getBlankTime() != null) {
					blankTime = attendanceUtil.calcBlankTime(dto.getBlankTime());
					attendanceManagementDto.setBlankTimeValue(String.valueOf(blankTime));
					attendanceManagementDto.setBlankTimeSelect(AttendanceUtil.convertBlankTime(dto.getBlankTime()));
				}
			}

			attendanceManagementDto.setTrainingDate(courseServiceSectionDto.getDate());

			// DTOの日付を形式に変換
			String trainingDateStr = dateUtil.toString(courseServiceSectionDto.getDate());
			// 当日かどうかの判定結果をセット
			attendanceManagementDto.setToday((today.equals(trainingDateStr)));
			attendanceManagementDto.setSectionName(courseServiceSectionDto.getSectionName());
			attendanceManagementDto.setBlankTimes(AttendanceUtil.setBlankTime());
			attendanceManagementDtoList.add(attendanceManagementDto);
		}
		return attendanceManagementDtoList;
	}

	/**
	 * 勤怠情報を全件、yyyyMMdd日付文字列をキーにセットされたMapで取得
	 * 
	 * @param lmsUserId LMSユーザID
	 * @return 日付をキーに勤怠情報がセットされたMap
	 */
	public Map<String, StudentAttendanceDto> getStudentAttendanceDtoMap(int lmsUserId) {

		List<StudentAttendanceDto> dtoList = this.getStudentAttendanceList(lmsUserId);
		// ListをMapに並び替えて返却

		Map<String, StudentAttendanceDto> dtoMap = new HashMap<>();

		for (StudentAttendanceDto dto : dtoList) {
			String key = getDtoMapKey(dto.getTrainingDate());
			dtoMap.put(key, dto);
		}
		return dtoMap;
	}

	/**
	 * DTOのMapキーを取得
	 * 
	 * @param 研修日
	 * @return 文字列形式の日付
	 */
	public String getDtoMapKey(Date trainingDate) {
		DateUtil dateUtil = new DateUtil();
		String key = dateUtil.toString(trainingDate);
		return key;
	}

	/**
	 * エンティティからDTOに変換
	 * 
	 * @param tStudentAttendance 勤怠情報のエンティティ
	 * @return 勤怠情報(画面表示用)DTOリスト
	 */
	private AttendanceManagementDto convertEntityToDto(TStudentAttendance tStudentAttendance) {

		AttendanceManagementDto studentAttendanceDto = new AttendanceManagementDto();
		BeanUtils.copyProperties(tStudentAttendance, studentAttendanceDto);
		AttendanceStatusEnum statusEnum = AttendanceStatusEnum.getEnum(tStudentAttendance.getStatus());
		if (statusEnum != null) {
			studentAttendanceDto.setStatusDispName(statusEnum.name);
		}
		return studentAttendanceDto;
	}

	/**
	 * 出勤情報登録
	 * 
	 * @param lmsUserId    LMSユーザID
	 * @param trainingDate 日付
	 * @param courseId     コースID
	 * @param accountId    企業ID
	 * @return 更新完了メッセージ
	 */
	public void punchIn(int lmsUserId, Date trainingDate, int courseId, int accountId) {
		TStudentAttendance tStudentAttendance = repository.findByLmsUserIdAndTrainingDate(lmsUserId, trainingDate);

		boolean errors = validPunchIn(lmsUserId, trainingDate, courseId);

		if (tStudentAttendance == null) {
			// 存在しなければ、レコードを新規作成する
			tStudentAttendance = new TStudentAttendance();
			// entityにデフォルト値をセット
			// DeleteFlgをセット
			tStudentAttendance.setDeleteFlg(Constants.DB_FLG_FALSE);
			// LMSユーザIDをセット
			tStudentAttendance.setLmsUserId(lmsUserId);
			// 日付をセット
			tStudentAttendance.setTrainingDate(trainingDate);
			// 退勤時間を空白でセット
			tStudentAttendance.setTrainingEndTime("");
			// 備考を空白でセット
			tStudentAttendance.setNote("");
			// accountIdをセット
			tStudentAttendance.setAccountId(accountId);
		}
		// 現在時刻
		TrainingTime trainingStartTime = new TrainingTime();
		// zero paddingされた、HH:mm形式の文字列
		String trainingStartTimeStr = trainingStartTime.toString();
		// 出勤時間をセット
		tStudentAttendance.setTrainingStartTime(trainingStartTimeStr);

		// 退勤時間
		TrainingTime endTime = null;
		if (tStudentAttendance.getTrainingEndTime().length() != 0) {
			endTime = new TrainingTime(tStudentAttendance.getTrainingEndTime());
		}

		// ステータスを設定
		AttendanceStatusEnum attendanceStatusEnum = AttendanceUtil
				.getStatus(new TrainingTime(tStudentAttendance.getTrainingStartTime()), endTime);
		tStudentAttendance.setStatus(attendanceStatusEnum.code);

		// 情報を更新
		if (errors == true) {
			repository.save(tStudentAttendance);
		}
	}

	/**
	 * 退勤情報登録
	 * 
	 * @param lmsUserId
	 * @param trainingDate
	 * @param courseServiceSectionDtoList
	 * @return 更新完了メッセージ
	 */
	public void punchOut(int lmsUserId, Date trainingDate, List<CourseServiceSectionDto> courseServiceSectionDtoList) {
		TStudentAttendance tStudentAttendance = repository.findByLmsUserIdAndTrainingDate(lmsUserId, trainingDate);

		boolean errors = validPunchOut(lmsUserId, trainingDate, courseServiceSectionDtoList);

		// INSERT
		if (errors == true) {

			// 現在時刻
			TrainingTime trainingEndTime = new TrainingTime();
			// zero paddingされた、HH:mm形式の文字列
			String trainingEndTimeStr = trainingEndTime.toString();
			// 退勤時間をセット
			tStudentAttendance.setTrainingEndTime(trainingEndTimeStr);
			// ステータスを設定
			AttendanceStatusEnum attendanceStatusEnum = AttendanceUtil.getStatus(
					new TrainingTime(tStudentAttendance.getTrainingStartTime()),
					new TrainingTime(tStudentAttendance.getTrainingEndTime()));
			tStudentAttendance.setStatus(attendanceStatusEnum.code);
			System.out.println(tStudentAttendance);

			// 情報を保存
			if (errors == true) {
				repository.save(tStudentAttendance);
			}
		}
		;
	}

	/**
	 * 出勤のエラーチェック
	 * 
	 * @param lmsUserId    LMSユーザID
	 * @param trainingDate 日付
	 * @param courseId     コースID
	 * @return エラーメッセージ
	 */
	public boolean validPunchIn(int lmsUserId, Date trainingDate, int courseId) {

		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date now = new Date();
		String nowStr = sdf.format(now);
		String dtoDate = "";

		// sectionの研修日取得
		List<CourseServiceSectionDto> courseServiceSectionDtoList = courseService.getSectionDtoList(courseId);
		for (CourseServiceSectionDto dto : courseServiceSectionDtoList) {
			dtoDate = sdf.format(dto.getDate());
			if (nowStr.equals(dtoDate)) {
				date = dto.getDate();
			}
		}

		// 今日が研修日か
		String dateStr = sdf.format(date);
		String trainingDateStr = sdf.format(trainingDate);

		boolean errors = true;
		// 研修日が今日ではないとき
		if (!dateStr.equals(trainingDateStr)) {
			errors = false;
		}

		return errors;
	}

	/**
	 * 退勤のエラーチェック
	 * 
	 * @param lmsUserId                   LMSユーザID
	 * @param trainingDate                日付
	 * @param courseServiceSectionDtoList コース情報サービス セクションDTO
	 * @return エラーメッセージ
	 */
	public boolean validPunchOut(int lmsUserId, Date trainingDate,
			List<CourseServiceSectionDto> courseServiceSectionDtoList) {
		boolean errors = true;
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date now = new Date();
		String nowStr = sdf.format(now);
		String dtoDate = "";
		// sectionの研修日取得
		for (CourseServiceSectionDto dto : courseServiceSectionDtoList) {
			dtoDate = sdf.format(dto.getDate());
			if (nowStr.equals(dtoDate)) {
				date = dto.getDate();
			}
		}

		String dateStr = sdf.format(date);
		// 今日が研修日か
		String trainingDateStr = sdf.format(trainingDate);
		if (!dateStr.equals(trainingDateStr)) {
			errors = false;
		}
		TStudentAttendance tStudentAttendance = repository.findByLmsUserIdAndTrainingDate(lmsUserId, trainingDate);
		if (tStudentAttendance == null) {
			// 出勤情報がないため退勤情報を入力出来ません
			errors = false;
		}
		String trainingStartTime = tStudentAttendance.getTrainingStartTime();
		TrainingTime trainingEndTime = new TrainingTime();
		// zero paddingされた、HH:mm形式の文字列
		String trainingEndTimeStr = trainingEndTime.toString();

		if (trainingStartTime != null && trainingStartTime.compareTo(trainingEndTimeStr) > 0) {
			errors = false;
		}
		if (tStudentAttendance != null && !StringUtils.isBlank(tStudentAttendance.getTrainingEndTime())) {
			// 既に入力されているのでエラー「本日の勤怠情報は既に入力されています」
			errors = false;
		}
		return errors;
	}

	/**
	 * 直接入力された勤怠情報を更新(一部修正必要箇所あり)
	 * 
	 * 未解決エラー1件：「DBのt_student_attendance」にない日付の勤怠情報を直接変更・更新しようとすると、更新ができない」
	 *　対応方法：勤怠管理画面の出退勤メソッド(このサービスクラスの上部に記載あり)にある、「// 該当の日付が存在しなければ、レコードを新規作成する」メソッドを追記する。
	 *　直接変更画面用にメソッドに変更を加える必要あり。
	 * 
	 * 
	 * @param attendanceForm 勤怠情報のフォーム
	 * @return 更新完了メッセージ
	 */
	public String update(AttendanceForm form) {

		// 登録用リスト
		List<TStudentAttendance> tStudentAttendanceList = new ArrayList<>();

		// ログインユーザ情報の取得
		// LoginUserDto loginUser = (LoginUserDto) session.getAttribute("loginUserDto");

		// LMSユーザIDから勤怠情報リストを作成
		List<TStudentAttendance> list = repository.findByLmsUserId(form.getLmsUserId());

		// 登録用エンティティのリストをフォームから作成
		for (DailyAttendanceForm attendanceForm : form.getAttendanceList()) {

			TStudentAttendance tStudentAttendance = new TStudentAttendance();

			BeanUtils.copyProperties(attendanceForm, tStudentAttendance);

			// ステータスをセット
			if (attendanceForm.getStatus() != null && !(attendanceForm.getStatus().equals(""))) {
				tStudentAttendance.setStatus((short) (Integer.parseInt(attendanceForm.getStatus())));
			}

			// 研修日の形式をDate型に変換してセット
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				tStudentAttendance.setTrainingDate(sdFormat.parse(attendanceForm.getTrainingDate()));
			} catch (ParseException e) {

				e.printStackTrace();
			}

			// 勤怠情報の日付とエンティティの日付が一致した場合エンティティにコピー
			for (TStudentAttendance entity : list) {
				if (entity.getTrainingDate().getTime() == tStudentAttendance.getTrainingDate().getTime()) {
					tStudentAttendance = entity;
					break;
				}
			}

			// LMSユーザIDとアカウントIDの項目をセット
			tStudentAttendance.setLmsUserId(form.getLmsUserId());
			tStudentAttendance.setAccountId(form.getAccountId());

			// 出勤時刻を整形してセット
			TrainingTime trainingStartTime = null;
			trainingStartTime = new TrainingTime(attendanceForm.getTrainingStartTime());
			tStudentAttendance.setTrainingStartTime(trainingStartTime.getFormattedString());

			// 退勤時刻を整形してセット
			TrainingTime trainingEndTime = null;
			trainingEndTime = new TrainingTime(attendanceForm.getTrainingEndTime());
			tStudentAttendance.setTrainingEndTime(trainingEndTime.getFormattedString());

			// 中抜け時間を整形してセット
			tStudentAttendance.setBlankTime(attendanceForm.getBlankTime());

			// 出勤・退勤どちらか入力されている時のみステータスを設定
			if ((trainingStartTime == null && trainingEndTime == null)
					|| (trainingStartTime.isEmpty() && trainingEndTime.isEmpty())) {
				tStudentAttendance.setStatus(null);
			} else if (trainingStartTime != null || !(trainingStartTime.isEmpty()) || trainingEndTime != null
					|| !(trainingEndTime.isEmpty())) {
				AttendanceStatusEnum attendanceStatusEnum = AttendanceUtil.getStatus(trainingStartTime,
						trainingEndTime);
				tStudentAttendance.setStatus(attendanceStatusEnum.code);
				// delFlag = false;
			}

			// 備考をセット
			tStudentAttendance.setNote(attendanceForm.getNote());

			// 削除フラグをセット
			tStudentAttendance.setDeleteFlg(Constants.DB_SCORE_FLG_FALSE);

			// 登録用リストへ追加
			tStudentAttendanceList.add(tStudentAttendance);

		}

		// 1件ずつ登録作業を行う。IDの有無でINSERT or UPDATEを判断
		for (TStudentAttendance tStudentAttendance : tStudentAttendanceList) {
			repository.save(tStudentAttendance);
		}

		// 更新完了メッセージを返す
		return messageUtil.getMessage(Constants.PROP_KEY_ATTENDANCE_UPDATE_NOTICE);
	}

}