package composition.java.patterns.service_locator.examples;

/*
* Copyright 2002 Sun Microsystems, Inc. All rights reserved.
* 
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions
* are met:
* 
* - Redistributions of source code must retain the above copyright
*   notice, this list of conditions and the following disclaimer.
* 
* - Redistribution in binary form must reproduce the above copyright
*   notice, this list of conditions and the following disclaimer in
*   the documentation and/or other materials provided with the
*   distribution.
* 
* Neither the name of Sun Microsystems, Inc. or the names of
* contributors may be used to endorse or promote products derived
* from this software without specific prior written permission.
* 
* This software is provided "AS IS," without a warranty of any
* kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
* WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
* EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES
* SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
* DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN
* OR ITS LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR
* FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR
* PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF
* LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE SOFTWARE,
* EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
* 
* You acknowledge that Software is not designed, licensed or intended
* for use in the design, construction, operation or maintenance of
* any nuclear facility.
*/

import javax.ejb.EntityContext;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.ejb.CreateException;

@Stateless
public class ProfileEJB implements ProfileRemote {

	private EntityContext context = null;

	// getters and setters for CMP fields
	// ====================================

	// EJB create method
	// ===================
	public Object ejbCreate(String preferredLanguage, String favoriteCategory, boolean myListPreference,
			boolean bannerPreference) throws CreateException {
		setPreferredLanguage(preferredLanguage);
		setFavoriteCategory(favoriteCategory);
		setMyListPreference(myListPreference);
		setBannerPreference(bannerPreference);
		return null;
	}

	// Misc Method
	// =============
	public void ejbPostCreate(String preferredLanguage, String favoriteCategory, boolean myListPreference,
			boolean bannerPreference) throws CreateException {
	}

	public void setEntityContext(EntityContext c) {
		setContext(c);
	}

	public void unsetEntityContext() {
		setContext(null);
	}

	public void ejbRemove() throws RemoveException {
	}

	public void ejbActivate() {
	}

	public void ejbPassivate() {
	}

	public void ejbStore() {
	}

	public void ejbLoad() {
	}

	@Override
	public String funciona() {

		System.out.println("El ejemplo ha funcionado correctamente");
		return "funciona";
	}

	@Override
	public String getPreferredLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPreferredLanguage(String preferredLanguage) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getFavoriteCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFavoriteCategory(String category) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getMyListPreference() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setMyListPreference(boolean myListPreference) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getBannerPreference() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setBannerPreference(boolean bannerPreference) {
		// TODO Auto-generated method stub

	}

	public EntityContext getContext() {
		return context;
	}

	public void setContext(EntityContext context) {
		this.context = context;
	}

}