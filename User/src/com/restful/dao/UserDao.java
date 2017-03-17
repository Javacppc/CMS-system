package com.restful.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.restful.entity.User;

public class UserDao {
	public List<User> getAllUsers() {
		List<User> userList = null;
		try {
			File file = new File("Users.dat");
			if (!file.exists()) {
				User user = new User(1, "张三", "教师");
				userList = new ArrayList<User>();
				userList.add(user);
				saveUserList(userList);
			}
			else {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				userList = (List<User>) ois.readObject();
				ois.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}
	private void saveUserList(List<User> userList) {
		try {
			File file = new File("Users.bat");
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(userList);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
