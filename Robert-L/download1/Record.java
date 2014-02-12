package se2.day07.download1;

import java.io.Serializable;

/**
 * 记录每一块下载的起始、结束以及下载文件名的信息
 * 
 * @author 1x2h33x
 * 
 */
public class Record implements Serializable {
	private static final long serialVersionUID = 1L;
	private long startPos;// 块的起始位置
	private long endPos;// 块的结束位置
	private String fileName;// 下载文件名

	public long getStartPos() {
		return startPos;
	}

	public void setStartPos(long startPos) {
		this.startPos = startPos;
	}

	public long getEndPos() {
		return endPos;
	}

	public void setEndPos(long endPos) {
		this.endPos = endPos;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
