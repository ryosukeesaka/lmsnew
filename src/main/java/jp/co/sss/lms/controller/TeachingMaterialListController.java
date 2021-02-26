package jp.co.sss.lms.controller;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jp.co.sss.lms.entity.MCourse;
import jp.co.sss.lms.entity.TCourseTeachingMaterial;
import jp.co.sss.lms.service.TeachingMaterialService;
import jp.co.sss.lms.util.Constants;
import jp.co.sss.lms.util.MessageUtil;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 教材ダウンロードコントローラ
 * 
 * @author kaneda
 */

@RestController
@RequestMapping("/download")
public class TeachingMaterialListController {

	@Autowired
	TeachingMaterialService teachingMaterialService;
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private TeachingMaterialService downloadService;

	@RequestMapping("/teachingMaterialList")
	public ResponseEntity<Model> showTeachingMaterialList(@RequestParam("courseId") Integer courseId,
			@RequestParam("lmsUserId") Integer lmsUserId, Model model) {

		// コースIDの改竄チェックの結果、問題がある場合、リクエストスコープにエラーメッセージを保存
		if (!teachingMaterialService.checkCourseId(courseId, lmsUserId)) {
			model.addAttribute("token", messageUtil.getMessage(Constants.VALID_KEY_TOKEN));
		}

		// mCourseにコース情報を入れる
		MCourse mCourse = teachingMaterialService.getCourseId(courseId);

		// コースIDがあった場合、リクエストスコープに取得したコース名を保存
		model.addAttribute("courseName", mCourse.getCourseName());

		// teachingMateriaLlistに教材の紐づけ情報を入れる
		List<TCourseTeachingMaterial> teachingMateriaLlist = teachingMaterialService
				.getCourseTeachingMaterialDownloadUrlDtoList(courseId);

		// 教材の紐づけができなかった場合、リクエストスコープにエラーメッセージを保存
		if (CollectionUtils.isEmpty(teachingMateriaLlist)) {
			model.addAttribute("notFoundTeachingMaterials",
					messageUtil.getMessage(Constants.VALID_KEY_NOTFOUNDTEACHINGMATERIALS));
		} else {
			// 教材の紐づけができた場合、リクエストスコープに取得した教材の情報を保存
			model.addAttribute("teachingMaterialList", teachingMateriaLlist);
		}

		// teachingMaterialList.htmlに遷移
		return new ResponseEntity<>(model, HttpStatus.OK);
	}
	
		@GetMapping("/teachingMaterialList/{fileName}")
		public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName, HttpServletResponse response) throws IOException {	
		//リクエストされたファイル名を取得、ダウンロードサービスで処理
		byte [] data = downloadService.downloadFile(fileName);
		ByteArrayResource resource = new ByteArrayResource (data);
		//ダウンロードしたファイル名をUTF-8に設定
		return ResponseEntity
				.ok()
				.contentLength(data.length)
				.header("Content-type", "application/octet-stream")
				.header("Content-disposition", "attachment; filename*=UTF-8\"" + fileName + "\"")
				.body(resource);
		
	}
	/**
	 * 2021年02月26日でAWSS3 ダウンロード可、フォルダー以下のファイルはダウンロード不可
	 * 納期に間に合わないため実装保留
	 * @author kyaw soelin
	 * */
}
