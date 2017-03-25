package com.taxsys.nsfw.user.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.taxsys.core.action.BaseAction;
import com.taxsys.core.exception.ActionException;
import com.taxsys.core.exception.ServiceException;
import com.taxsys.nsfw.role.service.RoleService;
import com.taxsys.nsfw.user.entity.User;
import com.taxsys.nsfw.user.entity.UserRole;
import com.taxsys.nsfw.user.service.UserService;

public class UserAction extends BaseAction{
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	/**
	 * 用於列出用戶信息的列表
	 */
	private List<User> userList;
	/**
	 * 用於接收用戶的輸入
	 */
	private User user;
	
	//上傳文件的三大要素
	private File headImg;
	private String headImgContentType;
	private String headImgFileName;
	/**
	 * 將用戶上傳的文件上傳到本服務器的地址目錄
	 */
	private String savePath;
	/**
	 * 用於接受系統管理員選擇的用戶角色複選框中的內容
	 */
	private String[] roleIds;
	
	
	
	
	
	public String[] getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
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
	public String listUI() throws Exception{
		try {
			userList = userService.findAll();
		} catch (ServiceException e) {
			throw new ActionException("Action層出現異常，異常信息：" + e.getMessage());
		}
		return "listUI";
		//用於測試StrutsResultSupport用的
		//return "error";
		//測試異常的
		/*try {
			//userList = userService.findAll();
			int a = 1 / 0;
		} catch (Exception e) {
			throw new Exception("Action層出現異常，異常信息：" + e.getMessage());
		}
		return "listUI";*/
	}
	//跳轉到新增頁面
	public String addUI() throws Exception{
		try {
			ActionContext.getContext().getContextMap().put("roleList", roleService.findAll());
		} catch (Exception e) {
			throw new ActionException(e.getMessage());
		}
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
				userService.saveUserAndRole(user,roleIds);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "list";
	}
	//跳轉到編輯頁面
	public String editUI() throws Exception{
		try {
			ActionContext.getContext().getContextMap().put("roleList", roleService.findAll());
			if (user != null && user.getId() != null) {
				user = userService.findById(user.getId());
				//處理角色回顯
				List<UserRole> list = userService.findUserRoleByUserId(user.getId());
				if (list != null && list.size() > 0) {
					roleIds = new String[list.size()];
					for (int i = 0; i < list.size(); ++i) {
						roleIds[i] = list.get(i).getRole().getRoleId();
					}
				}
			}
		} catch (ServiceException e) {
			throw new ActionException(e.getMessage());
		}
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
				userService.updateUserAndRole(user,roleIds);
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
	public void exportExcel() throws ActionException{
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
		} catch (Exception e) {
			throw new ActionException("Action層出現錯誤，原因是：" + e.getMessage());
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
	/**
	 * 驗證帳戶是否已經存在
	 */
	public void verifyAccount() {
		//1.獲取帳號
		//2.在數據庫中查找是否有同名的帳號
		if (user != null && StringUtils.isNotBlank(user.getAccount())) {
			try {
				List<User> userList = userService.findUserByIdAndAccount(user.getId(), user.getAccount());
				String strResult = "true";
				if (userList != null && userList.size() > 0) {
					//說明帳號已經存在
					strResult = "false";
				}
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html");
				ServletOutputStream output = response.getOutputStream();
				//將返回的結果輸出到瀏覽器(前端)
				output.write(strResult.getBytes());
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
