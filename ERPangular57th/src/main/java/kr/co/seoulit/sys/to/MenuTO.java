package kr.co.seoulit.sys.to;


import java.util.List;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="gds_menu")
public class MenuTO extends BaseTO{

	String menuCode,
	superMenuCode,
	menuName,
	menuUrl,
	usingStatus,
	level,
	label,
	link
	;

	List<MenuTO> menuListLv2;
	List<MenuTO> menuListLv3;
	List<MenuTO> items;

	public List<MenuTO> getItems() {
		return items;
	}

	public void setItems(List<MenuTO> items) {
		this.items = items;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<MenuTO> getMenuListLv2() {
		return menuListLv2;
	}

	public void setMenuListLv2(List<MenuTO> menuListLv2) {
		this.menuListLv2 = menuListLv2;
	}

	public List<MenuTO> getMenuListLv3() {
		return menuListLv3;
	}

	public void setMenuListLv3(List<MenuTO> menuListLv3) {
		this.menuListLv3 = menuListLv3;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getSuperMenuCode() {
		return superMenuCode;
	}

	public void setSuperMenuCode(String superMenuCode) {
		this.superMenuCode = superMenuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getUsingStatus() {
		return usingStatus;
	}

	public void setUsingStatus(String usingStatus) {
		this.usingStatus = usingStatus;
	}

}
