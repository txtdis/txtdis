package ph.txtdis.service;

import java.util.List;

import ph.txtdis.domain.UserEntity;
import ph.txtdis.dto.User;
import ph.txtdis.type.UserType;

public interface ServerUserService //
		extends SavedReferencedKeyedService<UserEntity, User, String> {

	User findByEmail(String e);

	List<User> list();

	List<User> listNamesByRole(List<UserType> types);
}
