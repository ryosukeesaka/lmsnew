package jp.co.sss.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.EvReportDetailDto;
import jp.co.sss.lms.dto.EvReportDto;
import jp.co.sss.lms.dto.EvReportScoringSearchDto;
import jp.co.sss.lms.entity.MEvReport;
import jp.co.sss.lms.entity.MEvReportDetail;
import jp.co.sss.lms.form.EvReportScoringForm;
import jp.co.sss.lms.repository.MEvReportRepository;

/**
 * EvReportService
 * 評価レポートサービス
 * 
 * @author 東　茉奈
 *
 */
@Service
public class EvReportService {

	@Autowired
	MEvReportRepository mEvReportRepository;
	
	/**
	 * 評価レポートDTOリスト取得処理
	 * 
	 * @param accountId　企業アカウントID
	 * @param placeId　会場ID
	 * @return evReportDtoList　評価レポートDTOリスト
	 */
	public List<EvReportDto> getEvReportDtoList(Integer accountId, Integer placeId) {
		List<EvReportDto> evReportDtoList = new ArrayList<EvReportDto>();
        List<MEvReport> mEvReportList = mEvReportRepository.findPlaceId(accountId, placeId);
        for (MEvReport mEvReport : mEvReportList) {
            evReportDtoList.add(getEvReportDto(mEvReport));
        }
        return evReportDtoList;
	}
	
	/**
	 * 評価レポートDTO取得処理
	 * 
	 * @param mEvReport　評価レポートマスタエンティティ
	 * @return evReportDto　評価レポートDTO
	 */
	public EvReportDto getEvReportDto(MEvReport mEvReport) {

        EvReportDto evReportDto = new EvReportDto();
        // 評価レポートマスタエンティティを評価レポートDTOに詰め替える。
        BeanUtils.copyProperties(mEvReport, evReportDto);
        
        if (mEvReport.mEvReportDetailList != null
                && !mEvReport.mEvReportDetailList.isEmpty()) {
            List<EvReportDetailDto> evReportDetailDtoList = new ArrayList<EvReportDetailDto>();
            for (MEvReportDetail mEvReportDetail : mEvReport.mEvReportDetailList) {

            	EvReportDetailDto evReportDetailDto = new EvReportDetailDto();
            	// 評価レポート詳細マスタエンティティを評価レポート詳細DTOに詰め替える。
            	BeanUtils.copyProperties(mEvReportDetail, evReportDetailDto);
            	
                evReportDetailDtoList.add(evReportDetailDto);
            }
            evReportDto.evReportDetailDtoList = evReportDetailDtoList;
        }
        return evReportDto;
    }

	/**
	 * 評価レポート採点状況検索DTOリスト取得処理
	 * 
	 * @param evReportScoringForm　評価レポート採点一覧画面のフォーム
	 * @return
	 */
	public List<EvReportScoringSearchDto> getEvReportScoringList(EvReportScoringForm evReportScoringForm) {
		
		return null;
	}
	
	public void scoringCsvDownload(EvReportScoringForm evReportScoringForm) {
		
	}

}
