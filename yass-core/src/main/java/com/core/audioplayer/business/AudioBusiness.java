package com.core.audioplayer.business;

import com.core.audioplayer.utils.LogUtil;
import com.czt.mp3recorder.MP3Recorder;

import java.io.File;
import java.util.UUID;


/**
 *
 * @author zlc
 *
 */
public class AudioBusiness{

	private MP3Recorder mRecorder;
	private String mDir;             // 文件夹的名称
	private String mCurrentFilePath;
	private static AudioBusiness mInstance;

	private boolean isPrepared; // 标识MediaRecorder准备完毕
	private AudioBusiness(String dir) {
		mDir = dir;
		LogUtil.e("AudioBusiness=",mDir);
	}

	/**
	 * 回调“准备完毕”
	 * @author zlc
	 */
	public interface AudioStateListenter {
		void wellPrepared();    // prepared完毕
	}

	public AudioStateListenter mListenter;

	public void setOnAudioStateListenter(AudioStateListenter audioStateListenter) {
		mListenter = audioStateListenter;
	}

	/**
	 * 使用单例实现 AudioBusiness
	 * @param dir
	 * @return
	 */
	public static AudioBusiness getInstance(String dir) {
		if (mInstance == null) {
			synchronized (AudioBusiness.class) {   // 同步
				if (mInstance == null) {
					mInstance = new AudioBusiness(dir);
				}
			}
		}
		return mInstance;
	}

	/**
	 * 准备录音
	 */
	public void prepareAudio(String mp3name) {

		try {
			isPrepared = false;
			File dir = new File(mDir);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			//String fileName = GenerateFileName(); // 文件名字
			String fileName = mp3name + ".mp3"; // 文件名字
			File file = new File(dir, fileName);  // 路径+文件名字
			//MediaRecorder可以实现录音和录像。需要严格遵守API说明中的函数调用先后顺序.
			mRecorder = new MP3Recorder(file);
			mCurrentFilePath = file.getAbsolutePath();
//			mMediaRecorder = new MediaRecorder();
//			mCurrentFilePath = file.getAbsolutePath();
//			mMediaRecorder.setOutputFile(file.getAbsolutePath());    // 设置输出文件
//			mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);    // 设置MediaRecorder的音频源为麦克风
//			mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);    // 设置音频的格式
//			mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);    // 设置音频的编码为AMR_NB
//			mMediaRecorder.prepare();
//			mMediaRecorder.start();
			mRecorder.start();   //开始录音
			isPrepared = true; // 准备结束
			if (mListenter != null) {
				mListenter.wellPrepared();
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.e("prepareAudio",e.getMessage());
		}

	}

	/**
	 * 随机生成文件名称
	 * @return
	 */
	private String GenerateFileName() {
		// TODO Auto-generated method stub
		return UUID.randomUUID().toString() + ".mp3"; // 音频文件格式
	}


	/**
	 * 获得音量等级——通过mMediaRecorder获得振幅，然后换算成声音Level
	 * maxLevel最大为7；
	 * @return
	 */
	public int getVoiceLevel(int maxLevel) {
		if (isPrepared) {
			try {
				mRecorder.getMaxVolume();
				return maxLevel * mRecorder.getMaxVolume() / 32768 + 1;
			} catch (Exception e) {
				 e.printStackTrace();
			}
		}
		return 1;
	}

	/**
	 * 释放资源
	 */
	public void release() {
		if(mRecorder != null) {
			mRecorder.stop();
			mRecorder = null;
		}
	}

	/**
	 * 停止录音
	 */
	public void stop(){
		if(mRecorder!=null && mRecorder.isRecording()){
			mRecorder.stop();
		}
	}

	/**
	 * 取消（释放资源+删除文件）
	 */
	public void delete() {
		release();
		if (mCurrentFilePath != null) {
			File file = new File(mCurrentFilePath);
			file.delete();    //删除录音文件
			mCurrentFilePath = null;
		}
	}

	public String getCurrentFilePath() {
		return mCurrentFilePath;
	}

	public int getMaxVolume(){
		return mRecorder.getMaxVolume();
	}

	public int getVolume(){
		return mRecorder.getVolume();
	}
}
