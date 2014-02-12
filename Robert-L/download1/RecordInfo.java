package se2.day07.download1;

import java.io.Serializable;

public class RecordInfo implements Serializable {
	/**
	 * 请求类型分两种 1.filename表示从服务端获取文件长度的请求 2.download表示下载本数据块的请求
	 */
	private static final long serialVersionUID = 1L;
	private String requestType;// 请求的类型
	private long fileSize;// 文件的长度
	private Record record;// 记录当前块的信息

	public RecordInfo() {
		record = new Record();
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

}
