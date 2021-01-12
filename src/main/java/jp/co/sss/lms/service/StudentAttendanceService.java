package jp.co.sss.lms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.AttendanceManagementDto;
import jp.co.sss.lms.dto.CourseServiceSectionDto;
import jp.co.sss.lms.dto.StudentAttendanceDto;
import jp.co.sss.lms.entity.TStudentAttendance;
import jp.co.sss.lms.enums.AttendanceStatusEnum;
import jp.co.sss.lms.repository.TStudentAttendanceRepository;
import jp.co.sss.lms.util.AttendanceUtil;
import jp.co.sss.lms.util.DateUtil;
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
	 * @param studentAttendanceDtoMap 勤怠情報DTOマップ
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
				}
			}

			attendanceManagementDto.setTrainingDate(courseServiceSectionDto.getDate());

			// DTOの日付を形式に変換
			String trainingDateStr = dateUtil.toString(courseServiceSectionDto.getDate());
			// 当日かどうかの判定結果をセット
			attendanceManagementDto.setToday((today.equals(trainingDateStr)));
			attendanceManagementDto.setSectionName(courseServiceSectionDto.getSectionName());
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
}
