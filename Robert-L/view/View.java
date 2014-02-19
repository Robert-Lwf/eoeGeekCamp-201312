package com.eoe.se2.day13.view;

public class View {
	private String id;
	private String layout_height;
	private String layout_width;
	private String orientation;
	private String background;

	// 返回属性数量
	private static int getCount() {
		return 5;
	}

	public View(String id, String layout_height, String layout_width,
			String orientation, String background) {
		this.id = id;
		this.layout_height = layout_height;
		this.layout_width = layout_width;
		this.orientation = orientation;
		this.background = background;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLayout_height() {
		return layout_height;
	}

	public void setLayout_height(String layout_height) {
		this.layout_height = layout_height;
	}

	public String getLayout_width() {
		return layout_width;
	}

	public void setLayout_width(String layout_width) {
		this.layout_width = layout_width;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public String getBackground() {
		return background;
	}

	public void setBackgroud(String background) {
		this.background = background;
	}

	public View() {
		// TODO Auto-generated constructor stub
	}

	protected View(String background) {
		this.background = background;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	// 重写toString(),返回所有属性的值
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getName());
		sb.delete(0, sb.lastIndexOf(".") + 1);
		return "View [地址=" + id + ", 高度=" + layout_height + ", 宽度="
				+ layout_width + ", 布局方向=" + orientation + ",背景=" + background
				+ "]";
	}

}
