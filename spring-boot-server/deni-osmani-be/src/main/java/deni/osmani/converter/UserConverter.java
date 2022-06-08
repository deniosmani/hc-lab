package deni.osmani.converter;

import org.springframework.stereotype.Component;

import deni.osmani.dto.UserDto;
import deni.osmani.entity.UserEntity;

@Component
public class UserConverter implements GenericConverter<UserDto, UserEntity> {

	@Override
	public UserEntity toEntity(UserDto userDto) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(userDto.getId());
		userEntity.setFirstname(userDto.getFirstname());
		userEntity.setLastname(userDto.getLastname());
		userEntity.setUsername(userDto.getUsername());
		userEntity.setPassword(userDto.getPassword());
		return userEntity;
	}

	@Override
	public UserDto toDto(UserEntity userEntity) {
		UserDto userDto = new UserDto();
        userDto.setId( userEntity.getId() );
        userDto.setFirstname( userEntity.getFirstname() );
        userDto.setLastname( userEntity.getLastname() );
        userDto.setUsername( userEntity.getUsername() );
        userDto.setPassword( userEntity.getPassword() );
        return userDto;
	}

}
