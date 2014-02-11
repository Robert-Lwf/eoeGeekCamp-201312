package se2.day06.socket1;

import java.io.Serializable;

public class FileInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public FileInfo(String fileName) {
		super();
		this.fileName = fileName;
	}
}
