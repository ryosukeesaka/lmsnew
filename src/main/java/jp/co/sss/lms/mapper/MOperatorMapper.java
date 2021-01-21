package jp.co.sss.lms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.sss.lms.entity.MOperator;

@Mapper
public interface MOperatorMapper {
	
	List<MOperator> findByOperatorType(@Param("operatorType") Integer operatorType);

}
