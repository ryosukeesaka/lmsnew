package jp.co.sss.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.PlaceDto;
import jp.co.sss.lms.entity.MLmsUser;
import jp.co.sss.lms.entity.MPlace;
import jp.co.sss.lms.repository.MLmsUserRepository;
import jp.co.sss.lms.repository.MPlaceRepository;

/**
 * 会場サービス（評価レポート採点一覧画面から呼び出しているメソッドのみ実装）
 * 
 * @author 東　茉奈
 *
 */
@Service
public class PlaceService {
	
	@Autowired
	MPlaceRepository mPlaceRepository;
	@Autowired
	MLmsUserRepository mLmsUserRepository;

	/**
	 * 会場DTO取得処理
	 * 
	 * @author 東　茉奈
	 * 
	 * @param placeId　会場ID
	 * @return placeDto　会場DTO
	 */
	public PlaceDto getPlaceDto(Integer placeId) {

        MPlace mPlace = mPlaceRepository.getMPlaceById(placeId);
        PlaceDto placeDto = new PlaceDto();
        // 会場マスタエンティティを会場DTOに詰め替える。
        BeanUtils.copyProperties(mPlace, placeDto);

        return placeDto;
    }
	
	public List<PlaceDto> getCompanyDtoList(Integer userId) {
		List<PlaceDto> placeDtoList = new ArrayList<PlaceDto>();
		List<MPlace> mPlaceList = new ArrayList<MPlace>();
		MLmsUser mLmsUser = mLmsUserRepository.getUserWithCompany(userId);
        
        mPlaceList = mPlaceRepository.findByAccountId(mLmsUser.getAccountId());
        
        for (MPlace mPlace : mPlaceList) {
        	PlaceDto placeDto = new PlaceDto();
        	BeanUtils.copyProperties(mPlace, placeDto);
        	placeDto.setPlaceName(mPlace.getPlaceName());
        	placeDto.setPlaceId(mPlace.getPlaceId());
        	
            placeDtoList.add(placeDto);
        }

        return placeDtoList;
	}
}
