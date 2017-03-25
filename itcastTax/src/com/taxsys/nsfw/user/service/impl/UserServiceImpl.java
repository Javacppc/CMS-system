package com.taxsys.nsfw.user.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taxsys.core.exception.ServiceException;
import com.taxsys.core.util.ExcelUtil;
import com.taxsys.nsfw.role.entity.Role;
import com.taxsys.nsfw.user.dao.UserDao;
import com.taxsys.nsfw.user.entity.User;
import com.taxsys.nsfw.user.entity.UserRole;
import com.taxsys.nsfw.user.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	@Override
	public User get(Serializable id) {
		return userDao.get(id);
	}

	@Override
	public Serializable save(User entity) {
		return userDao.save(entity);
	}

	@Override
	public void update(User entity) {
		userDao.update(entity);
	}

	@Override
	public void delete(User entity) {
		userDao.delete(entity);
	}

	@Override
	public void delete(Serializable id) {
		//刪除用戶
		userDao.delete(id);
		//刪除用戶所對應的所有角色（否則UserRole中的數據將永遠留存在數據庫中）
		userDao.deleteUserRoleByUserId(id);
	}
	
	@Override
	public List<User> findAll() throws ServiceException{
		/*try {
			int a = 1 / 0;
		} catch (Exception e) {
			throw new ServiceException("Service層出現問題，問題原因：" + e.getMessage());
		}*/
		return userDao.findAll();
	}

	@Override
	public long findCount() {
		return userDao.findCount();
	}

	@Override
	public User findById(Serializable id) throws ServiceException{
		return userDao.findById(id);		
	}

	@Override
	public void exportExcel(List<User> listUser, ServletOutputStream outputStream) {
		ExcelUtil.exportExcel(listUser, outputStream);
	}

	@Override
	public void importExcel(File headImg, String headImgFileName) {
		try {
			FileInputStream input = new FileInputStream(headImg);
			boolean is03Excel = headImgFileName.matches("^.+\\.(?i)(xls)$");
			//1.讀取工作部
			Workbook workbook = is03Excel ? new HSSFWorkbook(input) : new XSSFWorkbook(input);
			//2.讀取工作表
			Sheet sheet = workbook.getSheetAt(0);
			//3.讀取行
			//如果用戶上傳的Excel文檔大於2行（代表有數據）
			if (sheet.getPhysicalNumberOfRows() > 2) {
				User user = null;
				for (int k = 2; k < sheet.getPhysicalNumberOfRows(); ++k) {
					//4.讀取單元格
					Row row = sheet.getRow(k);
					user = new User();
					Cell cell0 = row.getCell(0);
					user.setName(cell0.getStringCellValue());
					Cell cell1 = row.getCell(1);
					user.setAccount(cell1.getStringCellValue());
					Cell cell2 = row.getCell(2);
					user.setDept(cell2.getStringCellValue());
					Cell cell3 = row.getCell(3);
					user.setGender(cell3.getStringCellValue().equals("男"));
					Cell cell4 = row.getCell(4);
					String mobile = "";
					try {
						mobile = cell4.getStringCellValue();
					} catch (Exception e) {
						mobile = BigDecimal.valueOf(cell4.getNumericCellValue()).toString();
					}
					user.setMobile(mobile);
					Cell cell5 = row.getCell(5);
					user.setEmail(cell5.getStringCellValue());
					Cell cell6 = row.getCell(6);
					if (cell6.getDateCellValue() != null) {
						user.setBirthday(cell6.getDateCellValue());
					}
					user.setPassword("123456");
					user.setState(User.USER_STATE_VALID);
					//5.保存用戶
					save(user);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> findUserByIdAndAccount(String id, String account) {
		return userDao.findUserByIdAndAccount(id, account);
	}
	//級聯保存
	@Override
	public void saveUserAndRole(User user, String...roleIds) {
		//1.保存用戶
		save(user);
		//2.保存用戶對應的角色
		//如果系統管理員根本就沒有勾選角色這個欄目的任何選項
		if (roleIds != null) {
			for (String roleId : roleIds) {
				userDao.saveUserRole(new UserRole(new Role(roleId), user.getId()));
			}
		}
	}

	@Override
	public void updateUserAndRole(User user, String... roleIds) {
		//1.刪除該用戶對應的所有角色
		userDao.deleteUserRoleByUserId(user.getId());
		//2.更新用戶
		update(user);
		//3.保存用戶對應的角色
		if (roleIds != null) {
			for (String roleId : roleIds) {
				userDao.saveUserRole(new UserRole(new Role(roleId), user.getId()));
			}
		}
	}
	
	@Override
	public List<UserRole> findUserRoleByUserId(String id) {
		return userDao.findUserRoleByUserId(id);
	}

	@Override
	public List<User> findUserByAccountAndPass(String account, String password) {
		return userDao.findUserByAccountAndPass(account, password);
	}
}
