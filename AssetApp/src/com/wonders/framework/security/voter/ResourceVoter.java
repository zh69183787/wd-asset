package com.wonders.framework.security.voter;

import java.util.Iterator;

import org.springframework.security.Authentication;
import org.springframework.security.ConfigAttribute;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.vote.AccessDecisionVoter;

/**
 * 如果"投票"通过，那么资源就能被访问
 * 
 * @author liming.feng
 * 
 */
public class ResourceVoter implements AccessDecisionVoter {

	/**
	 * 角色的前缀
	 */
	private String rolePrefix = "ROLE_";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.vote.AccessDecisionVoter#vote
	 *      (org.springframework.security.Authentication, java.lang.Object,
	 *      org.springframework.security.ConfigAttributeDefinition)
	 */
	@SuppressWarnings("unchecked")
	public int vote(Authentication authentication, Object object,
			ConfigAttributeDefinition config) {		
		int result = ACCESS_ABSTAIN;
		Iterator iter = config.getConfigAttributes().iterator();
		GrantedAuthority[] authorities = authentication.getAuthorities();

		while (iter.hasNext()) {
			ConfigAttribute attribute = (ConfigAttribute) iter.next();

			if (this.supports(attribute)) {
				result = ACCESS_DENIED;
				// Attempt to find a matching granted authority
				for (int i = 0; i < authorities.length; i++) {
					// logger.info("----- vote Authority:" +
					// authorities[i].getAuthority());

					if (attribute.getAttribute().equals(
							authorities[i].getAuthority())) {
						// logger.info("------=== vote ACCESS_GRANTED
						// ---=======");
						return ACCESS_GRANTED;
					}
				}
			}
		}
		return result;
	}

	/**
	 * @return
	 */
	public String getRolePrefix() {
		return rolePrefix;
	}

	/**
	 * @param rolePrefix
	 */
	public void setRolePrefix(String rolePrefix) {
		this.rolePrefix = rolePrefix;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.vote.AccessDecisionVoter#supports
	 *      (org.springframework.security.ConfigAttribute)
	 */
	public boolean supports(ConfigAttribute attribute) {
		if ((attribute.getAttribute() != null)
				&& attribute.getAttribute().startsWith(getRolePrefix())) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.vote.AccessDecisionVoter#supports
	 *      (java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
		return true;
	}
}