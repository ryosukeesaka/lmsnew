package jp.co.sss.lms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sss.lms.entity.MCourse;
import jp.co.sss.lms.entity.TCourseTeachingMaterial;
import jp.co.sss.lms.service.TeachingMaterialService;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.MessageUtil;

/**
 * 教材ダウンロードコントローラ
 * 
 * @author kaneda
 */

@RestController
public class TeachingMaterialListController {

	@Autowired
	TeachingMaterialService teachingMaterialService;
	@Autowired
	private MessageUtil messageUtil;

	@RequestMapping("/download/teachingMaterialList")
	public String showTeachingMaterialList(Model model, HttpSession session) {

		// コースIDの改竄チェックの結果、問題がある場合、リクエストスコープにエラーメッセージを保存
		if (!teachingMaterialService.checkCourseId()) {
			model.addAttribute("token", messageUtil.getMessage(Constants.VALID_KEY_TOKEN));
		}

		// mCourseにコース情報を入れる
		MCourse mCourse = teachingMaterialService.getCourseId();

		// コースIDがあった場合、リクエストスコープに取得したコース名を保存
		model.addAttribute("courseName", mCourse.getCourseName());

		// teachingMateriaLlistに教材の紐づけ情報を入れる
		List<TCourseTeachingMaterial> teachingMateriaLlist = teachingMaterialService
				.getCourseTeachingMaterialDownloadUrlDtoList();

		// 教材の紐づけができなかった場合、リクエストスコープにエラーメッセージを保存
		if (CollectionUtils.isEmpty(teachingMateriaLlist)) {
			model.addAttribute("notFoundTeachingMaterials",
					messageUtil.getMessage(Constants.VALID_KEY_NOTFOUNDTEACHINGMATERIALS));
		} else {
			// 教材の紐づけができた場合、リクエストスコープに取得した教材の情報を保存
			model.addAttribute("teachingMaterialList", teachingMateriaLlist);
		}

		// teachingMaterialList.htmlに遷移
		return "course/download/teachingMaterialList";
	}

}
