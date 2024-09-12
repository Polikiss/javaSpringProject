package itmo.polikiss.dto;

public record UserDto(int id, String username, String password, Role role, long ownerId) {
}
