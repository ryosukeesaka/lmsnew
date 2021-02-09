package jp.co.sss.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.ExamServiceExamDto;
import jp.co.sss.lms.dto.ExamServiceExamResultDto;
import jp.co.sss.lms.dto.ExamServiceQuestionDto;
import jp.co.sss.lms.entity.MExam;
import jp.co.sss.lms.entity.MLmsUser;
import jp.co.sss.lms.entity.MQuestion;
import jp.co.sss.lms.entity.TExamResult;
import jp.co.sss.lms.entity.TExamResultDetail;
import jp.co.sss.lms.form.ExamPlayForm;
import jp.co.sss.lms.repository.MExamRepository;
import jp.co.sss.lms.repository.TExamResultDetailRepository;
import jp.co.sss.lms.repository.TExamResultRepository;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.DateUtil;

/**
 * 試験情報サービス<br>
 * 
 * 試験情報に関するビジネスロジックを提供<br>
 * 
 * @author 植草 大徳
 *
 */
@Service
public class ExamService {
	
	@Autowired
	MExamRepository mExamRepository;
	@Autowired
	TExamResultRepository tExamResultRepository;
	@Autowired
	TExamResultDetailRepository tExamResultDetailRepository;
	@Autowired
	DateUtil dateUtil;
	/**
	 * 試験情報取得
	 * 
	 * @param form 試験開始form
	 * @return mExam 試験情報Entity
	 */
	public ExamServiceExamDto getExam(ExamPlayForm form, Integer lmsUserId) {

		// examSectionId・lmsUserIdで検索、検索結果を試験情報Entityに保存
		Integer examSectionId = form.getExamSectionId();
		MExam mExam = mExamRepository.findByExamSectionIdAndLmsUserId(examSectionId, lmsUserId);

		// 試験情報Entityから試験情報Dtoへ詰め替えして返す
		return getExam(mExam);
	}

	/**
	 * 試験結果情報取得
	 * 
	 * @param examSectionId 試験セクションID
	 * @return examResultDtoList 試験結果Dtoリスト
	 */
	public List<ExamServiceExamResultDto> getExamResult(Integer examSectionId, Integer lmsUserId, Integer accountId) {

		// examSectionId・lmsUserId・accountIdで検索、検索結果を試験結果Entityリストに保存
		List<TExamResult> tExamResultList = tExamResultRepository
				.findByExamSectionIdAndLmsUserIdAndAccountId(examSectionId, lmsUserId, accountId);

		// 試験結果Entityリストを試験結果Dtoリストへ詰め替え
		List<ExamServiceExamResultDto> examResultDtoList = new ArrayList<>();
		
		for (TExamResult tExamResult : tExamResultList) {
			ExamServiceExamResultDto examResultDto = new ExamServiceExamResultDto();
			examResultDto.setExamResultId(tExamResult.getExamResultId());
			examResultDto.setScore((double) tExamResult.getScore());
			examResultDto.setDate(tExamResult.getFirstCreateDate());
			examResultDto.setLmsUserId(tExamResult.getMLmsUser().getLmsUserId());
			examResultDtoList.add(examResultDto);
		}

		// 試験結果Dtoリストを返す
		return examResultDtoList;
	}

	/**
	 * 試験問題・解答情報取得
	 * 
	 * @param form 試験開始form
	 * @return mExam 試験情報Entity
	 */
	public ExamServiceExamDto getExamQuestionAndAnswer(ExamPlayForm form) {

		// examIdで検索、検索結果を試験情報Entityに保存
		MExam mExam = mExamRepository.getSindleResultByExamId(form.getExamId(), form.getAccountId());

		// 試験情報Entityから試験情報Dtoへ詰め替えして返す
		return getExam(mExam);
	}

	/**
	 * 試験情報を試験IDで取得
	 * 
	 * @param examId 試験ID
	 * @return mExam 試験情報Entity
	 */
	public ExamServiceExamDto getExam(Integer examId, Integer accountId) {

		// examIdとaccountIdで検索、検索結果を試験情報Entityに保存
		MExam mExam = mExamRepository.getSindleResultByExamId(examId, accountId);
		
		// 試験情報Entityから試験情報Dtoへ詰め替えして返す
		return getExam(mExam);
	}

	/**
	 * 平均点取得
	 * 
	 * @param examId 試験ID
	 * @return 平均点検索結果
	 */
	public Double getExamScoreAvg(Integer examId) {

		// 試験情報Entityで検索、検索結果を試験結果Entityリストに保存
		List<TExamResult> tExamResultList = tExamResultRepository.findByExamId(examId);

		// 検索結果がnullまたは空の場合nullを返す
		if (tExamResultList == null || tExamResultList.isEmpty()) {
			return Double.NaN;
		}
		// 検索結果がnullまたは空でない場合、試験IDで平均点を検索して返す
		return tExamResultRepository.selectExamScoreAvg(examId);
	}

	/**
	 * 試験結果登録
	 * 
	 * @param form 試験開始form
	 * @return examResultDto 試験結果情報Dto
	 */
	public ExamServiceExamResultDto registExamResult(ExamPlayForm form) {

		// formのexamSectionIdから試験問題・解答情報取得
		ExamServiceExamDto examDto = getExamQuestionAndAnswer(form);

		// 取得したexamSectionId,lmsUserId,accountIdを、試験結果Entityにセット
		TExamResult tExamResult = new TExamResult();
		MLmsUser mLmsUser = new MLmsUser();
		tExamResult.setExamSectionId(form.getExamSectionId());
		mLmsUser.setLmsUserId(form.getLmsUserId());
		tExamResult.setMLmsUser(mLmsUser);
		tExamResult.setAccountId(form.getAccountId());
		tExamResult.setDeleteFlg(Constants.DB_FEEDBACK_FLG_FALSE);
		tExamResult.setFirstCreateUser(form.getLmsUserId());
		tExamResult
		.setFirstCreateDate(dateUtil.stringToTimestamp(dateUtil.getCurrentDateString(), "yyyy/MM/dd HH:mm:ss"));
		tExamResult.setLastModifiedUser(form.getLmsUserId());
		tExamResult
		.setLastModifiedDate(dateUtil.stringToTimestamp(dateUtil.getCurrentDateString(), "yyyy/MM/dd HH:mm:ss"));

		// 時間が空でない場合、formから時間取得し、試験結果Entityにセット
		if (form.getTime() != null) {
			tExamResult.setTime(form.getTime());
		}

		// ログインユーザが受講生の場合
		if (Constants.CODE_VAL_ROLL_STUDENT.equals(form.getRole())) {
			// 試験結果情報登録
			tExamResultRepository.save(tExamResult);
		}

		// 得点記録用変数作成
		short score = 0;
		// 回答記録用配列作成
		Integer[] answer = form.getAnswer();

		// 採点処理
		for (int i = 0; i < examDto.getQuestionDtoList().size(); i++) {

			// 問題リストから試験問題Dto取得
			ExamServiceQuestionDto examServiceQuestionDto = examDto.getQuestionDtoList().get(i);

			// 取得したquestionId,examResultId,lmsUserIdを、試験結果詳細Entityにセット
			TExamResultDetail tExamResultDetail = new TExamResultDetail();
			tExamResultDetail.setQuestionId(examServiceQuestionDto.getQuestionId());
			tExamResultDetail.setTExamResult(tExamResult);
			tExamResultDetail.setLmsUserId(form.getLmsUserId());
			tExamResult.setDeleteFlg(Constants.DB_FEEDBACK_FLG_FALSE);
			tExamResult.setFirstCreateUser(form.getLmsUserId());
			tExamResult
			.setFirstCreateDate(dateUtil.stringToTimestamp(dateUtil.getCurrentDateString(), "yyyy/MM/dd HH:mm:ss"));
			tExamResult.setLastModifiedUser(form.getLmsUserId());
			tExamResult
			.setLastModifiedDate(dateUtil.stringToTimestamp(dateUtil.getCurrentDateString(), "yyyy/MM/dd HH:mm:ss"));

			// 回答が空でない場合回答取得、空の場合0代入
			if (answer != null && answer[i] != null && !answer[i].toString().isEmpty()) {
				tExamResultDetail.setReply(Short.parseShort(answer[i].toString()));
			} else {
				tExamResultDetail.setReply((short) 0);
			}

			// 取得したaccountIdを、試験結果詳細Entityにセット
			tExamResultDetail.setAccountId(form.getAccountId());

			// ログインユーザが受講生の場合
			if (Constants.CODE_VAL_ROLL_STUDENT.equals(form.getRole())) {
				// 試験結果詳細情報登録
				tExamResultDetailRepository.save(tExamResultDetail);
			}

			// 受講生の回答が問題の解答と一致する場合、得点に1加算
			if (tExamResultDetail.getReply().equals(examServiceQuestionDto.getAnswerNum())) {
				score += 1;
			}
		}
		// 採点処理後の得点を取得し、試験結果Entityにセット
		tExamResult.setScore(score);

		// ログインユーザが受講生の場合
		if (Constants.CODE_VAL_ROLL_STUDENT.equals(form.getRole())) {
			// 検索結果を試験結果Entityリストに保存
			List<TExamResult> tExamResultList = tExamResultRepository.findByExamSectionIdAndLmsUserIdAndAccountId(
					tExamResult.getExamSectionId(), tExamResult.getMLmsUser().getLmsUserId(),
					tExamResult.getAccountId());
			// リストの件数が1の場合、評点フラグに1を代入
			if (tExamResultList.size() == 1) {
				tExamResult.setMarkFlg(Constants.DB_FLG_TRUE);
				// 1以外の場合、評点フラグに0を代入
			} else {
				tExamResult.setMarkFlg(Constants.DB_FLG_FALSE);
			}
			// 得点、評点フラグを更新
			tExamResult = tExamResultRepository.save(tExamResult);
		}
		// 得点をformにセット
		form.setScore(tExamResult.getScore());

		// 取得したexamResultId,得点,登録日時を試験結果情報Dtoにセット
		ExamServiceExamResultDto examResultDto = new ExamServiceExamResultDto();
		examResultDto.setExamResultId(tExamResult.getExamResultId());
		examResultDto.setScore((double) tExamResult.getScore());
		examResultDto.setDate(tExamResult.getFirstCreateDate());

		// 試験結果情報Dtoを返す
		return examResultDto;
	}

	/**
	 * 試験結果詳細情報取得
	 * 
	 * @param examResultId 試験結果ID
	 * @return examResultDto 試験結果情報Dto
	 */
	public ExamServiceExamResultDto getExamResultWithQuestion(Integer examResultId, Integer accountId, Integer lmsUserId) {
		// 試験結果IDを基に、試験結果情報を取得
		TExamResult tExamResult = tExamResultRepository.findByExamResultId(examResultId,
				accountId);

		// 試験結果情報Dtoに値を代入する
		ExamServiceExamResultDto examResultDto = new ExamServiceExamResultDto();
		examResultDto.setExamResultId(tExamResult.getExamResultId());
		examResultDto.setLmsUserId(lmsUserId);
		examResultDto.setScore((double) tExamResult.getScore());
		examResultDto.setDate(tExamResult.getFirstCreateDate());

		// 試験IDを基に、試験情報Dtoを取得する
		ExamServiceExamDto examDto = getExam(tExamResult.getTExamSection().getExamId(), accountId);

		// 試験情報Dtoに代入するための、試験問題Dtoのリストを作成する
		List<MQuestion> questionList = tExamResult.getTExamSection().getMExam().getMQuestionList();
		List<ExamServiceQuestionDto> questionDtoList = new ArrayList<>();
		// ループカウンタ
		int count = 0;

		// 問題数の分、試験問題Dtoリストにオブジェクトを追加する
		for (MQuestion question : questionList) {
			ExamServiceQuestionDto questionDto = new ExamServiceQuestionDto();
			BeanUtils.copyProperties(question, questionDto);
			questionDto.setGenreDetailName(question.getmGenreDetail().getGenreDetailName());
			questionDto.setReply(tExamResult.getTExamResultDetailList().get(count).getReply());
			questionDtoList.add(questionDto);
			count++;
		}

		// 質問Dtoの問題リストに選択肢リストセット
		for (int i = 0; i < questionList.size(); i++) {
			List<String> answerList = new ArrayList<>();
			answerList.add(questionList.get(i).getChoice1());
			answerList.add(questionList.get(i).getChoice2());
			answerList.add(questionList.get(i).getChoice3());
			answerList.add(questionList.get(i).getChoice4());
			questionDtoList.get(i).setAnswerList(answerList);
		}

		// 試験情報Dtoに試験問題Dtoリストをセット
		examDto.setQuestionDtoList(questionDtoList);

		// 試験結果情報Dtoに試験情報Dtoをセット
		examResultDto.setExamDto(examDto);

		// 試験結果情報Dtoを返す
		return examResultDto;
	}
	
	
	/**
	 * 試験情報詰め替え
	 * 
	 * @param mExam 試験情報Entity
	 * @return examDto 試験情報Dto
	 */
	public ExamServiceExamDto getExam(MExam mExam) {

		ExamServiceExamDto examDto = new ExamServiceExamDto();
		if (mExam != null) {
			// examId,examName,limitTimeを試験情報Dtoへ詰め替え
			BeanUtils.copyProperties(mExam, examDto);

			// 取得したcourseId,sectionId,examSectionIdを、試験情報Dtoにセット
			examDto.setCourseId(mExam.getTExamSection().getMSection().getMCourse().getCourseId());
			examDto.setSectionId(mExam.getTExamSection().getMSection().getSectionId());
			examDto.setExamSectionId(mExam.getTExamSection().getExamSectionId());

			// 質問Entityリストを質問Dtoリストへ詰め替え
			List<MQuestion> mQuestionList = mExam.getMQuestionList();
			List<ExamServiceQuestionDto> questionDtoList = new ArrayList<>();
			
			if (mQuestionList.size() > 0) {
				for (MQuestion mQuestion : mQuestionList) {
					ExamServiceQuestionDto questionDto = new ExamServiceQuestionDto();
					BeanUtils.copyProperties(mQuestion, questionDto);
					questionDto.setGenreDetailName(mQuestion.getmGenreDetail().getGenreDetailName());
					questionDtoList.add(questionDto);
				}

				// 質問Dtoの問題リストに選択肢リストセット
				for (int i = 0; i < mQuestionList.size(); i++) {
					List<String> answerList = new ArrayList<>();
					answerList.add(mQuestionList.get(i).getChoice1());
					answerList.add(mQuestionList.get(i).getChoice2());
					answerList.add(mQuestionList.get(i).getChoice3());
					answerList.add(mQuestionList.get(i).getChoice4());
					questionDtoList.get(i).setAnswerList(answerList);
				}

			}

			// 詰め替え後の質問Dtoリストを試験情報Dtoにセット
			examDto.setQuestionDtoList(questionDtoList);

			// 詰め替え後の質問Dtoリストから問題数取得し、試験情報Dtoにセット
			examDto.setNumOfQuestion(questionDtoList.size());
		}
		// 試験情報Dtoを返す
		return examDto;

	}

}