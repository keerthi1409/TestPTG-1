package com.trinet.aboutme.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import antlr.StringUtils;

import com.trinet.aboutme.beans.Address;
import com.trinet.aboutme.beans.Contact;
import com.trinet.aboutme.beans.Name;

import com.trinet.aboutme.dao.EmployeeInfoDAO;
import com.trinet.aboutme.dtos.AddressDTO;

public class EmployeeInfoDAOImpl extends HibernateDaoSupport implements
		EmployeeInfoDAO {


		@Override
	public List<Address> getAddress(Integer employeeId) {
		 DetachedCriteria criteria = DetachedCriteria
				.forClass(Address.class);
		 criteria.add(Restrictions.eq("employeeID", employeeId));
		List<Address> addressList = (List<Address>)getHibernateTemplate().findByCriteria(criteria);
		return addressList;
	}

	@Override
	public List<Contact> getContact(Integer employeeId) {
		 DetachedCriteria criteria = DetachedCriteria
					.forClass(Contact.class);
		 criteria.add(Restrictions.eq("employeeID", employeeId));
		List<Contact> findByCriteria = (List<Contact>)getHibernateTemplate().findByCriteria(criteria);
		return findByCriteria;
	}

	@Override
	public List<Name> getName(Integer employeeId) {
		 DetachedCriteria criteria = DetachedCriteria
					.forClass(Name.class);
		 criteria.add(Restrictions.eq("employeeID", employeeId));
		List<Name> findByCriteria = (List<Name>)getHibernateTemplate().findByCriteria(criteria);
		return findByCriteria;
	}

	/* (non-Javadoc)
	 * @see com.trinet.aboutme.dao.EmployeeInfoDAO#maintaniAddress(com.trinet.aboutme.dtos.AddressDTO)
	 */
	@Override
	public List<Address> maintaniAddress(AddressDTO addressDTO) {
		List<Address> addressList = getAddressByAddressId(addressDTO.getAddressID());
		Address address= new Address();
		if(CollectionUtils.isNotEmpty(addressList))
		{
			address =  addressList.get(0);
		}
		populateAddress(addressDTO, address);
		getHibernateTemplate().saveOrUpdate(address);
		return getAddress(address.getAddressID());
	}

	private void populateAddress(AddressDTO addressDTO, Address address) {
		if(!addressDTO.getAddressLine1().isEmpty()){
		address.setAddressLine1(addressDTO.getAddressLine1());
		}
		if(!addressDTO.getAddressLine2().isEmpty()){
		address.setAddressLine2(addressDTO.getAddressLine2());
		}
		if(!addressDTO.getAddressLine3().isEmpty()){
		address.setAddressLine3(addressDTO.getAddressLine3());
		}
		if(!addressDTO.getAddressType().isEmpty()){
		address.setAddressType(addressDTO.getAddressType());
		}
		if(!addressDTO.getCity().isEmpty()){
			address.setCity(addressDTO.getCity());
		}
		if(!addressDTO.getCountry().isEmpty()){
			address.setCountry(addressDTO.getCountry());
		}
		if(!addressDTO.getCounty().isEmpty()){
			address.setCounty(addressDTO.getCounty());
		}
		if(!addressDTO.getEffectiveDate().isEmpty()){
			address.setEffectiveDate(addressDTO.getEffectiveDate());
		}
		if(addressDTO.getEmployeeID()!=0)
		{
			address.setEmployeeID(addressDTO.getEmployeeID());
		}
		if(!addressDTO.getPostalCode().isEmpty()){
			address.setPostalCode(addressDTO.getPostalCode());
		}
		if(!addressDTO.getStateOrProvince().isEmpty()){
			address.setStateOrProvince(addressDTO.getStateOrProvince());
		}
	}

	public List<Address> getAddressByAddressId(Integer addrId) {
		 DetachedCriteria criteria = DetachedCriteria
				.forClass(Address.class);
		 criteria.add(Restrictions.eq("addressID", addrId));
		List<Address> addressList = (List<Address>)getHibernateTemplate().findByCriteria(criteria);
		return addressList;
	}
	
}
