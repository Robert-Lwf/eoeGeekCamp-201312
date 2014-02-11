package se2.day06.download1;

import java.io.Serializable;

public class FileInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String fileName;
	private long position;// 断点位置

	public FileInfo() {
		// TODO Auto-generated constructor stub
	}

	public FileInfo(String fileName, long position) {
		super();
		this.fileName = fileName;
		this.position = position;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}

}
