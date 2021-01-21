package jp.co.sss.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.lms.dto.OperatorDto;
import jp.co.sss.lms.entity.MOperator;
import jp.co.sss.lms.mapper.MOperatorMapper;

@Service
public class SupportService {
	
	@Autowired
	private MOperatorMapper mOperatorMapper;
	
	public List<OperatorDto> getOperator(Integer operatorType) {
		
		List<MOperator> mOperatorList = mOperatorMapper.findByOperatorType(operatorType);
		System.out.println(mOperatorList.get(0).getOperatorName());
		
		List<OperatorDto> operatorDtoList = new ArrayList<>();
		
		for (MOperator mOperator : mOperatorList) {
			OperatorDto operatorDto = new OperatorDto();
			BeanUtils.copyProperties(mOperator, operatorDto);
			operatorDtoList.add(operatorDto);
		}
		
		return operatorDtoList;
		
	}

}
