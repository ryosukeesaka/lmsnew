package jp.co.sss.lms.util;

//ダウンロード処理関連、フォルダ処理関連のインポート文はコメントアウト
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
//import java.net.URL;
import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
import java.util.List;

//import javax.servlet.http.HttpServletResponse;
//import jp.co.sss.lms.entity.TDeliverablesResult;
//import org.apache.commons.io.IOUtils;
//import net.lingala.zip4j.*;

import org.springframework.web.multipart.MultipartFile;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest.KeyVersion;
//import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
//import com.amazonaws.services.s3.model.ListObjectsRequest;
//import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
//import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;

/**
 * Amazon S3のユーティリティ
 * 
 * ダウンロード処理、フォルダ関連の処理はコメントアウトし未実装。
 * zip処理にはzip4jを使うこと（pom.xmlに設定済み）。
 * 
 * @author 東　茉奈
 *
 */
public class AWSS3Util {
	/** マルチパートアップロード最大サイズ(1GB) */
    private static final long PART_SIZE = 10L * 1024L * 1024L * 1024L;
    private static Regions region =  Regions.AP_NORTHEAST_1;

    /**
     * Amazon S3にファイルをアップロードする。
     * @param uploadFile
     * @param filePath
     */
    public static void upload(MultipartFile uploadFile, String filePath) {

        // メッセージリソースからバケット名を取得する。
        // （バケット名が間違っていると403が発生する）
        String bucketName = SettingUtil.getProperty("setting.fileshare.bucketName");

        try (InputStream is = uploadFile.getInputStream()) {
        	AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                    .withRegion(region)
                    .withCredentials(new DefaultAWSCredentialsProviderChain())
                    .build();

            TransferManager manager = TransferManagerBuilder.standard().withMinimumUploadPartSize(PART_SIZE).withS3Client(s3).build();
            ObjectMetadata putMetadata = new ObjectMetadata();
            putMetadata.setContentLength(uploadFile.getSize());

            // ファイルアップロード
            Upload upload = manager.upload(bucketName, filePath, is, putMetadata);

            // アップロードが完了するまで、状態を取得し続ける
            while(!upload.isDone()) {
                upload.getProgress();
            }
            upload.waitForCompletion();

        } catch(IOException e) {
            throw new RuntimeException(e);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Amazon S3からファイルをダウンロードするURLを生成する。
     * @param filePath
     * @return
     */
//    public static String makeDowloadUrl(String filePath) {
//        URL url = getFileUrl(filePath);
//        return url.toString();
//    }

    /**
     * Amazon S3のファイルをダウンロードするURLを生成する。
     * @param filePath
     * @return
     */
//    public static InputStream getFileStream(String filePath) throws IOException {
//        URL url = getFileUrl(filePath);
//        return url.openStream();
//    }

//    /**
//     *
//     * @param filePath
//     * @return
//     */
//    private static URL getFileUrl(String filePath) {
//        String bucketName = SettingUtil.getProperty("setting.fileshare.bucketName");
//        String timeLimit = SettingUtil.getProperty("setting.fileshare.urltimelimit");
//
//        if (AWSS3Util.isNumber(timeLimit) == false) {
//            return null;
//        }
//
//        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
//                .withRegion(region)
//                .withCredentials(new DefaultAWSCredentialsProviderChain())
//                .build();
//
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.MILLISECOND, Integer.parseInt(timeLimit));
//        Date limit = cal.getTime();
//
//        URL url = s3.generatePresignedUrl(new GeneratePresignedUrlRequest(bucketName, filePath).withExpiration(limit));
//
//        return url;
//    }

    /**
     * Amazon S3からローカルにファイルを保存する
     */
//    public static void dlDeliverablesFiles(List<TDeliverablesResult> tDeliverablesResultList, HttpServletResponse response) throws IOException {
//        try (ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())) {
//            zos.setEncoding("MS932");
//            for (TDeliverablesResult tDeliverablesResult : tDeliverablesResultList) {
//
//                // Zip内ディレクトリパス([成果物名]/[ユーザ名(ID)]/[アップロードファイル名])
//                String[] splitFilePath = tDeliverablesResult.getFilePath().split("/");
//                String uploadFileName = splitFilePath[splitFilePath.length - 1];
//                String path = tDeliverablesResult.tDeliverablesSection.mDeliverables.deliverablesName
//                		+ "/" + tDeliverablesResult.mLmsUser.mUser.userName
//                        + "(" + tDeliverablesResult.mLmsUser.lmsUserId + ")"
//                        + "/" + uploadFileName;
//
//                InputStream is = AWSS3Util.getFileStream(tDeliverablesResult.getFilePath());
//
//                String zipName = tDeliverablesResult.tDeliverablesSection.mDeliverables.deliverablesName;
//                String fileName = new String((zipName + "_" + new Date().getTime()).getBytes("Windows-31J"), "ISO-8859-1");
//                fileName += ".zip";
//                response.setContentType("application/octet-stream");
//                response.setHeader("Content-Disposition", "filename=\"" + fileName + "\"");
//
//                byte[] binary = IOUtils.toByteArray(is);
//
//                zos.putNextEntry(new ZipEntry(path));
//                zos.write(binary, 0, binary.length);
//                zos.closeEntry();
//            }
//        }
//    	
//    }

    /**
     * 複数ファイルを削除する。
     * @param fileIdList
     */
    public static void deleteMultipleFile(List<String> fileIdList) {
        String bucketName = SettingUtil.getProperty("setting.fileshare.bucketName");
        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
        DeleteObjectsRequest multiObjectDeleteRequest = new DeleteObjectsRequest(bucketName);

        List<KeyVersion> keys = new ArrayList<KeyVersion>();
        for (String fileId : fileIdList) {
            keys.add(new KeyVersion(fileId));
        }

        multiObjectDeleteRequest.setKeys(keys);

        // DeleteObjectsResult delObjRes = s3.deleteObjects(multiObjectDeleteRequest);
        s3.deleteObjects(multiObjectDeleteRequest);
    }

    /**
     * Amazon S3から指定したbucketNameの指定ディレクトリのフォルダを取得する
     * @param bucketName
     * @param prefix
     * @param delimiter
     * @return List<String>
     */
//    public static List<String> getDirectoryNames(String bucketName, String prefix, String delimiter){
//        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
//                .withRegion(region)
//                .withCredentials(new DefaultAWSCredentialsProviderChain())
//                .build();
//
//        ListObjectsRequest request = new ListObjectsRequest()
//          .withBucketName(bucketName)
//          .withPrefix(prefix)
//          .withDelimiter(delimiter);
//
//        ObjectListing list = s3.listObjects(request);
//        List<String> folders = list.getCommonPrefixes();
//        return folders;
//    }

    /**
     * Amazon S3から指定したbucketNameの指定ディレクトリのフォルダ名を変更する
     * @param bucketName
     * @param oldPrefix
     * @param newPrefix
     */
//    public static void changeDirectoryName(String bucketName, String oldPrefix, String newPrefix){
//        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
//                .withRegion(region)
//                .withCredentials(new DefaultAWSCredentialsProviderChain())
//                .build();
//
//        ObjectListing listing = s3.listObjects(bucketName, oldPrefix);
//
//        for (S3ObjectSummary summary: listing.getObjectSummaries()) {
//            String oldKey = summary.getKey();
//            String newKey = new StringBuilder().append(newPrefix).append(oldKey.substring(oldPrefix.length())).toString();
//            s3.copyObject(bucketName, oldKey, bucketName, newKey);
//            s3.deleteObject(bucketName, oldKey);
//        }
//    }

    /**
     * Amazon S3の指定したbucketNameに指定名のフォルダが存在するかチェック
     * @param bucketName
     * @param　prefix
     */
//    public static boolean isExistDirectory(String bucketName, String prefix){
//        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
//                .withRegion(region)
//                .withCredentials(new DefaultAWSCredentialsProviderChain())
//                .build();
//
//       ObjectListing listing = s3.listObjects(bucketName, prefix);
//       List<S3ObjectSummary> list = listing.getObjectSummaries();
//       boolean result = list.size() == 0? false : true;
//       return result;
//    }

    /**
     * Amazon S3の指定したbucketNameの指定名のフォルダ直下にファイルがいくつ存在するかカウント
     * @param bucketName
     * @param　prefix
     */
//    public static int countFileAndDirectory(String bucketName, String prefix){
//        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
//                .withRegion(region)
//                .withCredentials(new DefaultAWSCredentialsProviderChain())
//                .build();
//
//        ObjectListing listing = s3.listObjects(bucketName, prefix);
//        List<S3ObjectSummary> list = listing.getObjectSummaries();
//
//        return list.size();
//    }

    /**
     * Amazon S3の指定したbucketNameの指定名のフォルダを削除する
     * @param bucketName
     * @param　prefix
     */
//    public static void deleteDirectory(String bucketName, String prefix){
//        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
//                .withRegion(region)
//                .withCredentials(new DefaultAWSCredentialsProviderChain())
//                .build();
//
//       ObjectListing listing = s3.listObjects(bucketName, prefix);
//
//       for (S3ObjectSummary summary: listing.getObjectSummaries()) {
//         String key = summary.getKey();
//         s3.deleteObject(bucketName, key);
//       }
//    }

    /**
     * 教材ダウンロードのパス作成処理
     * @param bucketName
     * @param prefix
     * @return multiMap
     */
//    public static MultiValueMap<String, String> makeTeachingMaterialDownloadPath(String bucketName, String prefix){;
//        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
//            .withRegion(region)
//            .withCredentials(new DefaultAWSCredentialsProviderChain())
//            .build();
//
//        ObjectListing listing = s3.listObjects(bucketName, prefix);
//        MultiValueMap<String, String> multiMap =  new LinkedMultiValueMap<String, String>();
//
//        for (S3ObjectSummary summary: listing.getObjectSummaries()) {
//            String key = summary.getKey();
//            if(!key.substring(key.length() - 1).equals("/")){
//                String[] keyArray = key.split("/");
//                String currentKey = "";
//                for (int i = 0; i < keyArray.length - 1; i++){
//                    currentKey = currentKey + keyArray[i] + "/";
//                }
//                currentKey = currentKey.substring(0, currentKey.length() - 1);
//                multiMap.add(currentKey, keyArray[keyArray.length - 1]);
//            }
//        }
//
//        return multiMap;
//    }

    /**
     * Amazon S3から対象ファイルのInputStreamを作成
     * @param filePath
     * @return InputStream
     * @throws IOException
     */
//    public static InputStream makeDowloadFileInputStream(String bucketName, String key) throws IOException {
//        String timeLimit = SettingUtil.getProperty("setting.fileshare.urltimelimit");
//        if (AWSS3Util.isNumber(timeLimit) == false) {
//            return null;
//        }
//        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
//                .withRegion(region)
//                .withCredentials(new DefaultAWSCredentialsProviderChain())
//                .build();
//
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.MILLISECOND, Integer.parseInt(timeLimit));
//        Date limit = cal.getTime();
//        URL url = s3.generatePresignedUrl(new GeneratePresignedUrlRequest(bucketName, key).withExpiration(limit));
//
//        try {
//            return url.openStream();
//        } catch (FileNotFoundException e) {
//            return null;
//        }
//    }

    /**
     * Amazon S3の対象ファイルの存在チェック
     * @param bucketName
     * @param key
     * @return boolean
     */
    public static boolean isExistFile(String bucketName, String key) {
        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
        try {
            s3.getObjectMetadata(bucketName, key);
            return true;
        } catch(AmazonServiceException e) {
            if (e.getStatusCode() != 404) {
                throw e;
            }
            return false;
        }
    }
    
    /**
     * 文字列が数値のみで構成されているかチェック
     * @author 東　茉奈
     * @param num 数値のみで構成されているはずの文字列
     * @return boolean 数値のみならばtrue、そうでなければfalse
     */
//    public static boolean isNumber(String num) {
//        try {
//            Integer.parseInt(num);
//            return true;
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }
}
