package com.taxsys.nsfw.role.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.taxsys.core.action.BaseAction;
import com.taxsys.core.constant.Constant;
import com.taxsys.core.exception.ActionException;
import com.taxsys.core.exception.ServiceException;
import com.taxsys.nsfw.role.entity.Role;
import com.taxsys.nsfw.role.entity.RolePrivilege;
import com.taxsys.nsfw.role.service.RoleService;
/**
 * 處理角色的Action
 * @author zhuxiaodong
 *
 */
public class RoleAction extends BaseAction{
	@Autowired
	private RoleService roleService;
	/**
	 * 用於列出角色信息的列表
	 */
	private List<Role> roleList;
	/**
	 * 用於接受設置角色
	 */
	private Role role;
	/**
	 * 接受用戶選擇的權限內容（接收用戶勾選的複選框的值）
	 */
	private String[] privilegeIds;
	
	
	

	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	public String[] getPrivilegeIds() {
		return privilegeIds;
	}
	public void setPrivilegeIds(String[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	/**
	 * 列表頁面
	 * @return
	 * @throws Exception
	 */
	public String listUI() throws Exception{
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
		try {
			roleList = roleService.findAll();
		} catch (ServiceException e) {
			throw new ActionException("Action層出現異常，異常信息：" + e.getMessage());
		}
		return "listUI";
		//用於測試StrutsResultSupoort用的
		//return "error";
		//測試異常的
		/*try {
			//roleList = roleService.findAll();
			int a = 1 / 0;
		} catch (Exception e) {
			throw new Exception("Action層出現異常，異常信息：" + e.getMessage());
		}
		return "listUI";*/
	}
	//跳轉到新增頁面
	public String addUI() {
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
		return "addUI";
	}
	//保存新增
	public String add() {
		if (role != null) {
			//權限保存（級聯）在保存角色的同時也保存權限
			if (privilegeIds != null) {
				Set<RolePrivilege> set = new HashSet<>();
				for (int i = 0; i < privilegeIds.length; ++i) {
					set.add(new RolePrivilege(role, privilegeIds[i]));
				}
				role.setRolePrivileges(set);
			}
			roleService.save(role);
		}
		return "list";
	}
	/**
	 * 跳轉到編輯頁面
	 * @return
	 * @throws Exception
	 */
	public String editUI() throws Exception{
		ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
		try {
			if (role != null && role.getRoleId() != null) 
				role = roleService.findById(role.getRoleId());
			//處理權限回顯
			if (role.getRolePrivileges() != null) {
				privilegeIds = new String[role.getRolePrivileges().size()];
				int i = 0;
				for (RolePrivilege rp : role.getRolePrivileges()) {
					privilegeIds[i++] = rp.getCode();
				}
			}
		} catch (ServiceException e) {
			throw new ActionException(e.getMessage());
		}
		return "editUI";
	}
	
	/**
	 * 保存編輯
	 * @return
	 */
	public String edit() {
		if (role != null) {
			//權限保存（級聯）在保存角色的同時也保存權限
			if (privilegeIds != null) {
				Set<RolePrivilege> set = new HashSet<>();
				for (int i = 0; i < privilegeIds.length; ++i) {
					set.add(new RolePrivilege(role, privilegeIds[i]));
				}
				role.setRolePrivileges(set);
			}
			roleService.update(role);
		}
		return "list";
	}
	/**
	 * 刪除
	 * @return
	 */
	public String delete() {
		if (role != null && role.getRoleId() != null) {
			roleService.delete(role.getRoleId());
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
				roleService.delete(id);
			}
		}
		return "list";
	}
}
