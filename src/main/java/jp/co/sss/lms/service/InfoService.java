package jp.co.sss.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.InfoDto;
import jp.co.sss.lms.entity.TInfo;
import jp.co.sss.lms.repository.TInfoRepository;

@Service
public class InfoService {
	
	@Autowired
	private TInfoRepository repository;
	
	public InfoDto getInfo() {
		TInfo tInfo = repository.findBySingleResult();
		InfoDto infoDto = new InfoDto();
		infoDto.setInfoId(tInfo.getInfoId());
		infoDto.setContent(tInfo.getContent());
		infoDto.setLastModifiedDate(tInfo.getLastModifiedDate());
		return infoDto;
	}

}
