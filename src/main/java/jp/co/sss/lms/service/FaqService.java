package jp.co.sss.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.FaqCategoryDto;
import jp.co.sss.lms.dto.FaqDto;
import jp.co.sss.lms.entity.MFrequentlyAskedQuestion;
import jp.co.sss.lms.entity.MFrequentlyAskedQuestionCategory;
import jp.co.sss.lms.form.FaqSearchForm;
import jp.co.sss.lms.repository.MFrequentlyAskedQuestionCategoryRepository;
import jp.co.sss.lms.repository.MFrequentlyAskedQuestionRepository;

/**
 * よくある質問サービス
 * 
 * @author 菅原 俊大
 */
@Service
public class FaqService {

	@Autowired
	private MFrequentlyAskedQuestionRepository mFrequentlyAskedQuestionRepository;

	@Autowired
	private MFrequentlyAskedQuestionCategoryRepository mFrequentlyAskedQuestionCategoryRepository;

	/**
	 * よくある質問カテゴリを全件取得して返す
	 * 
	 * @return よくある質問カテゴリのDTOリスト
	 */
	public List<FaqCategoryDto> getFaqCategoryList() {

		// カテゴリを全件取得する
		List<MFrequentlyAskedQuestionCategory> mFrequentlyAskedQuestionCategoryList = mFrequentlyAskedQuestionCategoryRepository
				.findAllFrequentlyAskedQuestionCategory();

		// DTOにカテゴリIDとカテゴリ名をセットする
		List<FaqCategoryDto> faqCategoryDtoList = new ArrayList<>();
		for (MFrequentlyAskedQuestionCategory mFrequentlyAskedQuestionCategory : mFrequentlyAskedQuestionCategoryList) {

			FaqCategoryDto faqCategoryDto = new FaqCategoryDto();
			BeanUtils.copyProperties(mFrequentlyAskedQuestionCategory,faqCategoryDto);
			faqCategoryDtoList.add(faqCategoryDto);
		}

		return faqCategoryDtoList;

	}

	/**
	 * よくある質問を取得して返す
	 * 
	 * @param form よくある質問入力フォーム
	 * @return よくある質問のDTOリスト
	 */
	public List<FaqDto> getFaqDtoList(FaqSearchForm form) {

		List<MFrequentlyAskedQuestion> mFrequentlyAskedQuestionList = new ArrayList<>();

		// カテゴリIDとキーワードが両方nullの場合は全件取得を行う
		if (form.getFrequentlyAskedQuestionCategoryId() == null && form.getKeyword() == null) {
			mFrequentlyAskedQuestionList = mFrequentlyAskedQuestionRepository.findAllFrequentlyAskedQuestion();
		}
		// カテゴリIDがnull以外の場合はIDによる検索を行う
		else if (form.getFrequentlyAskedQuestionCategoryId() != null) {
			mFrequentlyAskedQuestionList = mFrequentlyAskedQuestionRepository.findByFrequentlyAskedQuestionCategoryId(
					Integer.parseInt(form.getFrequentlyAskedQuestionCategoryId()));
		}
		// キーワードがnull以外の場合はキーワードによる検索を行う
		else if (form.getKeyword() != null) {
			mFrequentlyAskedQuestionList = mFrequentlyAskedQuestionRepository
					.findByKeyword("%" + form.getKeyword() + "%");
		}

		// DTOにFAQIDと質問内容と回答内容をセットする
		List<FaqDto> faqDtoList = new ArrayList<>();
		for (MFrequentlyAskedQuestion mFrequentlyAskedQuestion : mFrequentlyAskedQuestionList) {

			FaqDto faqDto = new FaqDto();
			BeanUtils.copyProperties(mFrequentlyAskedQuestion,faqDto);
			faqDtoList.add(faqDto);
		}

		return faqDtoList;

	}

}
