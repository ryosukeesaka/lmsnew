package jp.co.sss.lms.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sss.lms.dto.LoginUserDto;
import jp.co.sss.lms.exception.NoLoginException;

@Component
public class LoginUserUtil {
	@Autowired
	LoginUserDto loginUserDto;	
	
	/**
     * ログインユーザーのユーザーIDを取得する。
     * @return
     */
    public Integer getLoginUserId() {
        return getLoginUserDto().getUserId();
    }

    /**
     * ログインユーザーのLMSユーザーIDを取得する。
     * @return
     */
    public Integer getLoginLmsUserId() {
        return getLoginUserDto().getLmsUserId();
    }

    /**
     * ログインユーザーのアカウントIDを取得する。
     * @return
     */
    public Integer getLoginAccountId() {
        return getLoginUserDto().getAccountId();
    }

    /**
     * ログインしているユーザーが管理者の場合true
     * @return
     */
    public boolean isAdmin() {
        return Constants.CODE_VAL_ROLL_ADMIN.equals(getLoginUserDto().getRole());
    }

    /**
     * ログインしているユーザーが受講生の場合true
     * @return
     */
    public boolean isStudent() {
        return Constants.CODE_VAL_ROLL_STUDENT.equals(getLoginUserDto().getRole());

    }

    /**
     * ログインしているユーザーが講師の場合true
     * @return
     */
    public boolean isTeacher() {
        return Constants.CODE_VAL_ROLL_TEACHER.equals(getLoginUserDto().getRole());
    }

    /**
     * ログインしているユーザーが企業担当者の場合true
     * @return
     */
    public boolean isCompany() {
        return Constants.CODE_VAL_ROLL_COMPANY.equals(getLoginUserDto().getRole());
    }

    /**
     * ログインしているユーザーが育成担当者の場合true
     * @return
     */
    public boolean isTraining() {
        return Constants.CODE_VAL_ROLL_TRAINING.equals(getLoginUserDto().getRole());
    }
	
	/**
     * ログイン情報を取得する。
     * @throws NoLoginException ログイン情報が存在しない場合、例外を送出する。
     * @return
     */
    public LoginUserDto getLoginUserDto() {
        if (!isLogin()) {
            throw new NoLoginException();
        }
        return loginUserDto;
    }
    
    /**
     * ログインチェック
     * @return
     */
    public boolean isLogin() {
        return !(loginUserDto == null || loginUserDto.getUserId() == null || loginUserDto.getLmsUserId() == null || loginUserDto.getRole() == null);
    }

}
