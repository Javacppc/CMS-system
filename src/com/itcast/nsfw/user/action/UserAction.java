package com.itcast.nsfw.user.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.itcast.nsfw.user.entity.User;
import com.itcast.nsfw.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	@Autowired
	private UserService userService;
	/**
	 * 用於列出用戶信息的列表
	 */
	private List<User> userList;
	/**
	 * 用於接收用戶的輸入
	 */
	private User user;
	/**
	 * 用於批量刪除時用
	 */
	private String[] selectedRow;
	//上傳文件的三大要素
	private File headImg;
	private String headImgContentType;
	private String headImgFileName;
	/**
	 * 將用戶上傳的文件上傳到本服務器的地址目錄
	 */
	private String savePath;
	
	
	
	
	public String getSavePath() {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public File getHeadImg() {
		return headImg;
	}
	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}
	public String getHeadImgContentType() {
		return headImgContentType;
	}
	public void setHeadImgContentType(String headImgContentType) {
		this.headImgContentType = headImgContentType;
	}
	public String getHeadImgFileName() {
		return headImgFileName;
	}
	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}
	public String[] getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	//列表頁面
	public String listUI() {
		userList = userService.findAll();
		return "listUI";
	}
	//跳轉到新增頁面
	public String addUI() {
		return "addUI";
	}
	//保存新增
	public String add() {
		try {
			if (user != null) {
				//如果上傳了圖片
				if (headImg != null) {
					//設置文件名稱
					String fileName = UUID.randomUUID().toString() 
							+ headImgFileName.substring(headImgFileName.lastIndexOf("."));
					//保存文件到服務器
					FileUtils.copyFile(headImg, new File(getSavePath(), fileName));
					//設置用戶圖像路徑
					user.setHeadImg("user/" + fileName);
				}
				
				userService.save(user);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "list";
	}
	//跳轉到編輯頁面
	public String editUI() {
		if (user != null && user.getId() != null) 
			user = userService.findById(user.getId());
		return "editUI";
	}
	//保存編輯
	public String edit() {
		try {
			if (user != null) {
				//如果上傳了圖片
				if (headImg != null) {
					//設置文件名稱
					String fileName = UUID.randomUUID().toString() 
							+ headImgFileName.substring(headImgFileName.lastIndexOf("."));
					//保存文件到服務器
					FileUtils.copyFile(headImg, new File(getSavePath(), fileName));
					//設置用戶圖像路徑
					user.setHeadImg("user/" + fileName);
				}				
				userService.update(user);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "list";
	}
	//刪除
	public String delete() {
		if (user != null && user.getId() != null) {
			userService.delete(user.getId());
		}
		return "list";
	}
	/**
	 * 批量刪除
	 * @return
	 */
	public String deleteSelected() {
		if (selectedRow != null) {
			for (String id : selectedRow) {
				userService.delete(id);
			}
		}
		return "list";
	}
	/**
	 * 導出用戶列表
	 */
	public void exportExcel() {
		try {
			//查找用戶列表
			List<User> listUser = userService.findAll();
			//導出
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/x-execel");
			//以附件形式下載
			response.setHeader("Content-Disposition", "attachment;filename="+new String("用戶列表.xls".getBytes(), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			userService.exportExcel(listUser, outputStream);
			//如果開發人員在exportExcel方法中將outputStream中關閉了，那麼這裡的代碼就起作用了
			if (outputStream != null) {
				outputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 導入用戶列表
	 * @return 導入Excel用戶數據以後要跳轉到的頁面
	 */
	public String importExcel() {
		//如果獲取的文件不為空
		if (headImg != null) {
			//判斷上傳的文件是否是Excel文件
			if (headImgFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
				//導入用戶列表
				userService.importExcel(headImg, headImgFileName);
			}
		}
		return "list";
	}
}
